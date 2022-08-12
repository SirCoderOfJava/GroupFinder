package com.SirCoderOfJava.groupfindermod.gui.buttons;

import com.SirCoderOfJava.groupfindermod.gui.GroupFinderGui;
import net.minecraft.client.gui.FontRenderer;

public class DecrementPageButton extends GroupFinderButton{

    public static final int EXTRA_LEFT_SHIFT = 20;
    public static final int VERTICAL_SHIFT = 38;
    public static final int WIDTH = 14;
    public static final int HEIGHT = 14;
    public static final int FIRST_PAGE = 1;
    private GroupFinderGui context;
    public DecrementPageButton(int buttonId, int screenWidth, int screenHeight, FontRenderer fontRenderer, GroupFinderGui context) {
        super(buttonId, (screenWidth / 2) - (fontRenderer.getStringWidth("Page " + GroupFinderGui.pageHandler.getNumberOfPages() + " / " + GroupFinderGui.pageHandler.getNumberOfPages()) / 2) - EXTRA_LEFT_SHIFT, screenHeight - VERTICAL_SHIFT, WIDTH, HEIGHT, "-", fontRenderer);
        this.context = context;
    }

    public void runClickAction() {
        int currPage = context.getCurrentPage();
        if(currPage > FIRST_PAGE) {
            context.setCurrentPage(currPage - 1);
        }
    }
}
