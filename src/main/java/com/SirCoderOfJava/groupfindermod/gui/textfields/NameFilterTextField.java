package com.SirCoderOfJava.groupfindermod.gui.textfields;

import com.SirCoderOfJava.groupfindermod.gui.GroupFilter;
import com.SirCoderOfJava.groupfindermod.gui.GroupFinderGui;
import net.minecraft.client.gui.FontRenderer;

public class NameFilterTextField extends GroupFinderTextField {

    private static final int X_POSITION = 15;
    private static final int Y_POSITION = 70;
    private static final int WIDTH = 180;
    private static final int HEIGHT = 16;


    public NameFilterTextField(int componentId, FontRenderer fontrendererObj) {
        super(componentId, fontrendererObj, X_POSITION, Y_POSITION, WIDTH, HEIGHT);
    }

    @Override
    public void updateFilter() {
        //System.out.println("adding filter text box side");
        GroupFinderGui.pageHandler.addTextFilter(GroupFilter.FILTER_TYPES.NAME, this.getText());
    }
}