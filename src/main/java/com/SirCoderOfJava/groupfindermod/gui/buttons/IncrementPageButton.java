package com.SirCoderOfJava.groupfindermod.gui.buttons;

import com.SirCoderOfJava.groupfindermod.gui.GroupFinderGui;
import net.minecraft.client.gui.FontRenderer;

public class IncrementPageButton extends GroupFinderButton {

    public static final int EXTRA_RIGHT_SHIFT = 5;
    public static final int VERTICAL_SHIFT = 38;
    public static final int WIDTH = 14;
    public static final int HEIGHT = 14;
    private GroupFinderGui context;
    public IncrementPageButton(int buttonId, int screenWidth, int screenHeight, FontRenderer fontRenderer, GroupFinderGui context) {
        super(buttonId, (screenWidth / 2) + (fontRenderer.getStringWidth("Page " + GroupFinderGui.pageHandler.getNumberOfPages() + " / " + GroupFinderGui.pageHandler.getNumberOfPages()) / 2) + EXTRA_RIGHT_SHIFT, screenHeight - VERTICAL_SHIFT, WIDTH, HEIGHT, "+", fontRenderer);
        this.context = context;
    }

    public void runClickAction() {
        int currPage = context.getCurrentPage();
        int totalPages = GroupFinderGui.pageHandler.getNumberOfPages();
        if(currPage < totalPages) {
            context.setCurrentPage(currPage + 1);
        }
    }
}
