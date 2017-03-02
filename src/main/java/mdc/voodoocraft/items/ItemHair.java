package mdc.voodoocraft.items;


import net.minecraft.item.ItemStack;

public class ItemHair extends VCItem {
    public ItemHair(String name) {
        super(name);
    }
    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
}
