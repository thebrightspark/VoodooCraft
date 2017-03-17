package mdc.voodoocraft.util;

import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.Lists;

import mdc.voodoocraft.hexes.Hex;
import mdc.voodoocraft.items.ItemDoll;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;

public class HexHelper {

	private static final String KEY_HEXES = "hexes";
	
	@Nonnull
	public static List<Hex> getHexes(ItemStack stack) {
		List<Hex> hexes = Lists.newArrayList();
		if(stack.hasTagCompound()) {
			NBTTagCompound nbt = stack.getTagCompound();
			if(nbt.hasKey(KEY_HEXES, NBT.TAG_LIST)) {
				NBTTagList hexList = nbt.getTagList(KEY_HEXES, NBT.TAG_COMPOUND);
				if(!hexList.hasNoTags()) {
					for(int i = 0; i < hexList.tagCount(); i++) {
						hexes.add(new Hex(hexList.getCompoundTagAt(i)));
					}
				}
			}
		}
		return hexes;
	}
	
	public static ItemStack setHexes(ItemStack stackIn, List<Hex> hexes) {
		NBTTagCompound stackNBT = new NBTTagCompound();
		if(stackIn.hasTagCompound()) stackNBT = stackIn.getTagCompound();
		NBTTagList hexList = new NBTTagList();
		for(Hex h : hexes) {
			hexList.appendTag(h.writeToNBT());
		}
		stackNBT.setTag(KEY_HEXES, hexList);
		stackIn.setTagCompound(stackNBT); //update the actual compound
		return stackIn;
	}
	/**
	 * Should be used for hexes that are fired on right clicking nothing
	 * @return
	 */
	public static ItemStack activate(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
		List<Hex> hexes = getHexes(stack);
		if(!hexes.isEmpty()) {
			int multiplier = 1;
			for(Hex h : hexes) {
				stack = h.getEntry().activeUse(stack, world, player, hand, h.getStrength());
				multiplier += h.getCost();
	        }
			stack.damageItem(multiplier * hexes.size(), player);
	    }
		return stack;
	}
	/**
	 * Should be used for hexes that are fired on right clicking entities
	 * @return
	 */
	public static ItemStack activate(ItemStack stack, World world, EntityPlayer player, EnumHand hand, EntityLivingBase target) {
		List<Hex> hexes = getHexes(stack);
		if(!hexes.isEmpty()) {
			int multiplier = 1;
			for(Hex h : hexes) {
				stack = h.getEntry().entityActivate(stack, world, player, hand, h.getStrength(), target);
				multiplier += h.getCost();
	        }
			stack.damageItem(multiplier * hexes.size(), player);
	    }
		return stack;
	}
  
  	/**
	 * Checks the player's inventory for a Doll with a certain Hex and returns it.
	 */
	public static ItemStack getDollWithHex(EntityPlayer player, String hexUnlocName)
	{
		InventoryPlayer playerInv = player.inventory;
		for(ItemStack stack : playerInv.mainInventory)
			if(stack != null && stack.getItem() instanceof ItemDoll)
			{
				List<Hex> hexes = getHexes(stack);
				if(!hexes.isEmpty())
					for(Hex h : hexes)
						if(h.getEntry().getUnlocalizedName().equals(hexUnlocName))
							return stack;
			}
		return null;
	}
}