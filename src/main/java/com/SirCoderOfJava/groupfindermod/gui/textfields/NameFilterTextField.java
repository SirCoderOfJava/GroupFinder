package com.SirCoderOfJava.groupfindermod.gui.textfields;

import com.SirCoderOfJava.groupfindermod.gui.GroupFilter;
import com.SirCoderOfJava.groupfindermod.gui.GroupFinderGui;
import net.minecraft.client.gui.FontRenderer;

/**
 * This class represents the text box for the name filter
 */
public class NameFilterTextField extends GroupFinderTextField {

    private static final int X_POSITION = 15;
    private static final int Y_POSITION = 70;
    private static final int WIDTH = 180;
    private static final int HEIGHT = 16;


    /**
     * Implements superclass constructor with constants specific to this component
     * @param componentId internal id for the component stored by the mod
     * @param fontrendererObj {@link FontRenderer} instance to pass to superclass for rendering
     */
    public NameFilterTextField(int componentId, FontRenderer fontrendererObj) {
        super(componentId, fontrendererObj, X_POSITION, Y_POSITION, WIDTH, HEIGHT);
    }

    /**
     * When the filter is updated, add a name filter to the filter list
     */
    @Override
    public void updateFilter() {
        GroupFinderGui.pageHandler.addTextFilter(GroupFilter.FILTER_TYPES.NAME, this.getText());
    }
}