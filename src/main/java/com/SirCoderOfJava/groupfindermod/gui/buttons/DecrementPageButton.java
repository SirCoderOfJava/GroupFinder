package com.SirCoderOfJava.groupfindermod.gui.buttons;

import com.SirCoderOfJava.groupfindermod.gui.GroupFinderGui;
import net.minecraft.client.gui.FontRenderer;

public class DecrementPageButton extends GroupFinderButton{

    private GroupFinderGui context;
    public DecrementPageButton(int buttonId, int screenWidth, int screenHeight, FontRenderer fontRenderer, GroupFinderGui context) {
        super(buttonId, (screenWidth / 2) - (fontRenderer.getStringWidth("Page " + GroupFinderGui.pageHandler.getNumberOfPages() + " / " + GroupFinderGui.pageHandler.getNumberOfPages()) / 2) - 20, screenHeight - 38, 14, 14, "-", fontRenderer);
        this.context = context;
    }

    public void runClickAction() {
        int currPage = context.getCurrentPage();
        if(currPage > 1) {
            context.setCurrentPage(currPage - 1);
        }
    }
}
