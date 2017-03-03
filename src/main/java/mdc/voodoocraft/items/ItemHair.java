package mdc.voodoocraft.items;


import mdc.voodoocraft.util.NBTHelper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import java.util.List;


public class ItemHair extends VCItem {
    public ItemHair(String name) {
        super(name);
    }
    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("Hair from: "+ NBTHelper.getTagCompound(stack).getString("entityName"));
        if (GuiScreen.isShiftKeyDown()) {
            if(stack.getTagCompound()!=null)
            {
                tooltip.add("UUID: "+ NBTHelper.getTagCompound(stack).getUniqueId("entityUUID"));
            }
        } else tooltip.add(TextFormatting.AQUA + "Press SHIFT for more information");
    }
}
