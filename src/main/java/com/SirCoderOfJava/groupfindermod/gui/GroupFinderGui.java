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

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int centerX = width / 2;
        FILTER_HEIGHT = height - 2 * FILTER_Y_OFFSET;

        drawDefaultBackground();

        //Title rendering block
        GlStateManager.pushMatrix();
        {
            float titleScaleFactor = 2.0F;
            GlStateManager.enableBlend();
            GlStateManager.enableAlpha();
            GlStateManager.scale(titleScaleFactor, titleScaleFactor, titleScaleFactor);
            drawCenteredString(fontRendererObj, replaceCodes("&cGroup Finder"), (int) (centerX / titleScaleFactor), 5, OPAQUE_WHITE);
        }
        GlStateManager.popMatrix();

        //Page navigation rendering block
        GlStateManager.pushMatrix();
        {
            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();

            if(!focused) {
                int numPages = pageHandler.getNumberOfPages();
                drawCenteredString(fontRendererObj, "Page: " + currentPage + " / " + numPages, centerX, height - VERTICAL_PAGE_TEXT_SHIFT, OPAQUE_WHITE);
            }
        }
        GlStateManager.popMatrix();

        //Filter rendering block
        GlStateManager.pushMatrix();
        {
            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.translate(FILTER_X_OFFSET, FILTER_Y_OFFSET, 0);

            if(!focused) {
                drawRect(0, 0, FILTER_WIDTH, FILTER_HEIGHT, HALF_TRANSPARENT_BLACK);
                drawHorizontalLine(0, FILTER_WIDTH, 0, OPAQUE_BLACK);
                drawHorizontalLine(0, FILTER_WIDTH, FILTER_HEIGHT, OPAQUE_BLACK);
                drawVerticalLine(0, 0, FILTER_HEIGHT, OPAQUE_BLACK);
                drawVerticalLine(FILTER_WIDTH, 0, FILTER_HEIGHT, OPAQUE_BLACK);
            }
        }
        GlStateManager.popMatrix();

        //Text Box Rendering block
        GlStateManager.pushMatrix();
        {
            drawString(fontRendererObj, "Name Filter:", 15, 55, OPAQUE_WHITE);
            nameFilterTextField.drawTextBox();
        }
        GlStateManager.popMatrix();

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        for(GuiButton b : buttonList) {
            if(b instanceof RefreshGroupsButton && b.id == button.id) {
                pageHandler.groupFilter.clearTextFilters();
                for(GroupFinderTextField textField : textFields) {
                    textField.updateFilter();
                }
            }
            if(b instanceof GroupFinderButton && b.id == button.id) {
                ((GroupFinderButton) button).runClickAction();
                new TickDelay(new Runnable() {
                    @Override
                    public void run() {
                        updateButtons(); //this has to have a small delay to prevent a concurrentmodificationexception because it modifies the button list through which we are currently iterating
                    }
                }, 1);
                break;
            }else if(b instanceof UnfocusedGroupButton && b.id == button.id) {
                ((UnfocusedGroupButton) button).expand();
            }

        }
        super.actionPerformed(button);
    }



    public void updateButtons() {
        pageHandler.update();

        buttonList.clear();
        buttonList.add(new CloseButton(CLOSE_BUTTON_ID, fontRendererObj));
        buttonList.add(new RefreshGroupsButton(REFRESH_BUTTON_ID, fontRendererObj));


        if(!focused) {
            buttonList.add(new IncrementPageButton(INCREMENT_PAGE_BUTTON_ID, width, height, fontRendererObj, this));
            buttonList.add(new DecrementPageButton(DECREMENT_PAGE_BUTTON_ID, width, height, fontRendererObj, this));

            int currentButtonXPos = STARTING_GROUP_BUTTON_X_POS;
            int currentButtonYPos = STARTING_GROUP_BUTTON_Y_POS;

            int currentButtonID = 2;

            ArrayList<JsonObject> page = pageHandler.getPages().get(currentPage - 1);
            for (JsonObject group : page) {
                List<String> lines = pageHandler.getDisplayLinesFromGroup(group);
                int projectedButtonEndX = currentButtonXPos + fontRendererObj.getStringWidth(pageHandler.getLongestLineInGroupLines(lines)) + (2 * SIDE_BUFFER);
                if (projectedButtonEndX > (width - 200)) {
                    currentButtonXPos = 225;
                    currentButtonYPos += NEW_LINE_Y_INCREMENT;
                }
                UnfocusedGroupButton button = new UnfocusedGroupButton(currentButtonID, currentButtonXPos, currentButtonYPos, lines, fontRendererObj);
                buttonList.add(button);
                currentButtonXPos += button.width + (2 * SIDE_BUFFER);
            }
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

    @Override
    public void updateScreen() {
        super.updateScreen();
        nameFilterTextField.updateCursorCounter();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int btn) throws IOException {
        super.mouseClicked(mouseX, mouseY, btn);
        for (GroupFinderTextField textField : textFields) {
            textField.mouseClicked(mouseX, mouseY, btn);
        }
    }

}
