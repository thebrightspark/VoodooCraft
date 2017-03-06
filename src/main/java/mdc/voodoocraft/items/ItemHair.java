package mdc.voodoocraft.items;


import java.util.List;

import mdc.voodoocraft.util.NBTHelper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;


public class ItemHair extends VCItem {
    public ItemHair() {
        super("hair");
    }
    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("hair.type.name", NBTHelper.getOwnerName(stack)));
        if (GuiScreen.isShiftKeyDown()) {
        	tooltip.add("UUID: "+ NBTHelper.getOwnerUUID(stack));
        } else tooltip.add(TextFormatting.AQUA + I18n.format("press.for.info.name", "SHIFT")); //TODO: configurable key?
    }
}
