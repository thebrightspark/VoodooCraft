package mdc.voodoocraft.hexes;

import javax.annotation.Nullable;

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
}
