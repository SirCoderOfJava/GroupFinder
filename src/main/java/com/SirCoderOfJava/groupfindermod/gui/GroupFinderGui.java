package com.SirCoderOfJava.groupfindermod.gui;


import com.SirCoderOfJava.groupfindermod.gui.buttons.*;
import com.SirCoderOfJava.groupfindermod.gui.textfields.GroupFinderTextField;
import com.SirCoderOfJava.groupfindermod.gui.textfields.NameFilterTextField;
import com.SirCoderOfJava.groupfindermod.util.TickDelay;
import com.google.gson.JsonObject;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.SirCoderOfJava.groupfindermod.util.ChatMessageColorizer.replaceCodes;

/**
 * Main Gui for the group display
 */
public class GroupFinderGui extends GuiScreen {

    final int CLOSE_BUTTON_ID = 0;
    final int REFRESH_BUTTON_ID = 1;
    final int INCREMENT_PAGE_BUTTON_ID = 100;
    final int DECREMENT_PAGE_BUTTON_ID = 101;
    final int NAME_FILTER_TEXT_FIELD_ID = 102;

    final int SIDE_BUFFER = 5;
    private final int VERTICAL_PAGE_TEXT_SHIFT = 34;
    private final int OPAQUE_WHITE = 0xFFFFFFFF;
    private final int OPAQUE_BLACK = 0xFF000000;
    private final int HALF_TRANSPARENT_BLACK = 0x80000000;
    private final int NEW_LINE_Y_INCREMENT = 150;
    private final int STARTING_GROUP_BUTTON_X_POS = 225;
    private final int STARTING_GROUP_BUTTON_Y_POS = 50;

    private final int FILTER_X_OFFSET = 10;
    private final int FILTER_Y_OFFSET = 50;
    private final int FILTER_WIDTH = 190;
    private int FILTER_HEIGHT = height - 2 * FILTER_Y_OFFSET;

    public boolean focused = false;

    public static PageHandler pageHandler;
    private int currentPage = 1;

    private ArrayList<GroupFinderTextField> textFields;

    private NameFilterTextField nameFilterTextField;

    /**
     * Runs every time the gui is loaded or resized: Resets the text fields, page handlers, and updates all the buttons on the screen
     */
    @Override
    public void initGui() {
        textFields = new ArrayList<GroupFinderTextField>();
        pageHandler = new PageHandler();

        nameFilterTextField = new NameFilterTextField(NAME_FILTER_TEXT_FIELD_ID, fontRendererObj);
        nameFilterTextField.setMaxStringLength(23);

        textFields.add(nameFilterTextField);

        updateButtons();
        super.initGui();
    }

    /**
     * Rendering function for the gui
     * @param mouseX x coordinate of the mouse
     * @param mouseY y coordinate of the mouse
     * @param partialTicks partial ticks, not needed for our rendering but must be passed to superclass
     */
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int centerX = width / 2;
        FILTER_HEIGHT = height - 2 * FILTER_Y_OFFSET;

        drawDefaultBackground();

        //Title rendering block
        GlStateManager.pushMatrix();
        {
            float titleScaleFactor = 2.0F;
            //enable transparent rendering
            GlStateManager.enableAlpha();
            //increase rendering scale for title
            GlStateManager.scale(titleScaleFactor, titleScaleFactor, titleScaleFactor);
            //Draw the title
            //Opaque white color allows color codes to work normally
            drawCenteredString(fontRendererObj, replaceCodes("&cGroup Finder"), (int) (centerX / titleScaleFactor), 5, OPAQUE_WHITE);
        }
        GlStateManager.popMatrix();

        //Page navigation rendering block
        GlStateManager.pushMatrix();
        {
            GlStateManager.enableAlpha();

            int numPages = pageHandler.getNumberOfPages();
            drawCenteredString(fontRendererObj, "Page: " + currentPage + " / " + numPages, centerX, height - VERTICAL_PAGE_TEXT_SHIFT, OPAQUE_WHITE);
        }
        GlStateManager.popMatrix();

        //Filter rendering block
        GlStateManager.pushMatrix();
        {
            GlStateManager.enableAlpha();

            //translate renderer to upper left corner of the rendering block
            GlStateManager.translate(FILTER_X_OFFSET, FILTER_Y_OFFSET, 0);

            //Draw background of filter
            drawRect(0, 0, FILTER_WIDTH, FILTER_HEIGHT, HALF_TRANSPARENT_BLACK);

            //Draw filter outline
            drawHorizontalLine(0, FILTER_WIDTH, 0, OPAQUE_BLACK);
            drawHorizontalLine(0, FILTER_WIDTH, FILTER_HEIGHT, OPAQUE_BLACK);
            drawVerticalLine(0, 0, FILTER_HEIGHT, OPAQUE_BLACK);
            drawVerticalLine(FILTER_WIDTH, 0, FILTER_HEIGHT, OPAQUE_BLACK);
        }
        GlStateManager.popMatrix();

        //Text Box Rendering block
        GlStateManager.pushMatrix();
        {
            //Draw labels for the text fields
            drawString(fontRendererObj, "Name Filter:", 15, 55, OPAQUE_WHITE);

            //Draw all the text boxes
            for(GroupFinderTextField textField : textFields) {
                textField.drawTextBox();
            }
        }
        GlStateManager.popMatrix();

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    /**
     * Runs whenever a button is clicked
     * @param button the button that was clicked
     * @throws IOException
     */
    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        for(GuiButton b : buttonList) {
            //If they clicked the refresh button, update all the filters
            if(b instanceof RefreshGroupsButton && b.id == button.id) {
                pageHandler.groupFilter.clearTextFilters();
                for(GroupFinderTextField textField : textFields) {
                    textField.updateFilter();
                }
            }

            //Process all group finder buttons
            if(b instanceof GroupFinderButton && b.id == button.id) {
                //run their built-in action method
                ((GroupFinderButton) button).runClickAction();

                //update the buttons after a small delay
                new TickDelay(new Runnable() {
                    @Override
                    public void run() {
                        updateButtons(); //this has to have a small delay to prevent a concurrentmodificationexception because it modifies the button list through which we are currently iterating
                    }
                }, 1);
                break;

            }else if(b instanceof UnfocusedGroupButton && b.id == button.id) {
                //If they clicked on one of the previews, expand the preview
                ((UnfocusedGroupButton) button).expand();
            }

        }
        super.actionPerformed(button);
    }


    /**
     * Update all the buttons
     */
    public void updateButtons() {
        //update all the group info and the pages for previews
        pageHandler.update();

        //clear the button list and repopulate with static buttons
        buttonList.clear();
        buttonList.add(new CloseButton(CLOSE_BUTTON_ID, fontRendererObj));
        buttonList.add(new RefreshGroupsButton(REFRESH_BUTTON_ID, fontRendererObj));
        buttonList.add(new IncrementPageButton(INCREMENT_PAGE_BUTTON_ID, width, height, fontRendererObj, this));
        buttonList.add(new DecrementPageButton(DECREMENT_PAGE_BUTTON_ID, width, height, fontRendererObj, this));

        //Populate group previews

        int currentButtonXPos = STARTING_GROUP_BUTTON_X_POS;
        int currentButtonYPos = STARTING_GROUP_BUTTON_Y_POS;

        int currentButtonID = 2;

        //generate current page
        ArrayList<JsonObject> page = pageHandler.getPages().get(currentPage - 1);
        for (JsonObject group : page) {
            //get display lines
            List<String> lines = pageHandler.getDisplayLinesFromGroup(group);

            //calculate projected end of the button, and reset to the left side on the next line if it goes past the allocated area
            int projectedButtonEndX = currentButtonXPos + fontRendererObj.getStringWidth(pageHandler.getLongestLineInGroupLines(lines)) + (2 * SIDE_BUFFER);
            if (projectedButtonEndX > (width - 200)) {
                currentButtonXPos = 225;
                currentButtonYPos += NEW_LINE_Y_INCREMENT;
            }

            //create the actual preview button and add it to the list
            UnfocusedGroupButton button = new UnfocusedGroupButton(currentButtonID, currentButtonXPos, currentButtonYPos, lines, fontRendererObj);
            buttonList.add(button);

            //increment to next button slot
            currentButtonXPos += button.width + (2 * SIDE_BUFFER);
        }
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
    }


    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int page) {
        currentPage = page;
        updateButtons();
    }

    /**
     * Process key presses in text boxes
     */
    @Override
    protected void keyTyped(char charTyped, int keyCode) throws IOException {
        for(GroupFinderTextField textField : textFields) {
            if(textField.isFocused()) {
                textField.textboxKeyTyped(charTyped, keyCode);
                return;
            }
        }

        super.keyTyped(charTyped, keyCode);
    }

    /**
     * Update cursor counter for text fields
     */
    @Override
    public void updateScreen() {
        super.updateScreen();
        for(GroupFinderTextField textField : textFields) {
            textField.updateCursorCounter();
        }
    }

    /**
     * Process mouse clicks in text fields
     */
    @Override
    protected void mouseClicked(int mouseX, int mouseY, int btn) throws IOException {
        super.mouseClicked(mouseX, mouseY, btn);
        for (GroupFinderTextField textField : textFields) {
            textField.mouseClicked(mouseX, mouseY, btn);
        }
    }

}
