package mdc.voodoocraft.util;

import javax.annotation.Nullable;

import mdc.voodoocraft.hexes.Hex;
import mdc.voodoocraft.items.ItemDoll;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants.NBT;

public class HexHelper {

	private static final String KEY_HEX = "hex";
	
	@Nullable
	public static Hex getHex(ItemStack stack) {
		if(stack.hasTagCompound()) {
			NBTTagCompound nbt = stack.getTagCompound();
			if(nbt.hasKey(KEY_HEX, NBT.TAG_LIST)) {
				//return new Hex(nbt.getCompoundTag(KEY_HEX));
			}
		}
		return null;
	}

	/**
	 * Checks the player's inventory for a Doll with a certain Hex and returns it.
	 */
	public static ItemStack getPlayerHex(EntityPlayer player, String hexName)
	{
		InventoryPlayer playerInv = player.inventory;
		for(ItemStack stack : playerInv.mainInventory)
			if(stack != null && stack.getItem() instanceof ItemDoll)
			{
				Hex h = ItemDoll.getHex(stack);
				if(h != null && h.unlocName.equals(hexName))
					return stack;
			}
		return null;
	}
}
