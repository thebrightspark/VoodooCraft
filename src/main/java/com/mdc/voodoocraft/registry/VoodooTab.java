package com.mdc.voodoocraft.registry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class VoodooTab extends CreativeTabs{

    public VoodooTab(int index, String label) {
        super(index, label);
        this.setBackgroundImageName("item_search.png");
    }

    @Override
    public Item getTabIconItem() {
        return Items.SKULL;
    }

    @Override
    public boolean hasSearchBar() {
        return true;
    }
}
