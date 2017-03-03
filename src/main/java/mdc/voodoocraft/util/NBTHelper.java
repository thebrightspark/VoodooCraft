//Credits to Pahimar for this class

package mdc.voodoocraft.util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class NBTHelper
{
	public static final String KEY_OWNER = "owner";
	
    /**
     * Initializes the NBT Tag Compound for the given ItemStack if it does not exist already
     *
     * @param itemStack
     *         The ItemStack for which its NBT Tag Compound is being checked for initialization
     */
    private static void initNBTTagCompound(@Nonnull ItemStack itemStack)
    {
        if (!itemStack.hasTagCompound())
        {
            itemStack.setTagCompound(new NBTTagCompound());
        }
    }

    public static void setOwner(ItemStack stack, EntityPlayer player)
	{
    	initNBTTagCompound(stack);
    	stack.getTagCompound().setTag(KEY_OWNER, NBTUtil.createUUIDTag(player.getPersistentID()));
	}
	
    @Nullable
	public static EntityPlayerMP getOwner(@Nonnull ItemStack stack)
	{
    	initNBTTagCompound(stack);
		NBTTagCompound stackNBT = stack.getTagCompound();
		if(stackNBT.hasKey(KEY_OWNER)) {
			//FIXME: check if player is online!
			return FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUUID(NBTUtil.getUUIDFromTag(stackNBT.getCompoundTag(KEY_OWNER)));
		}
		return null;
	}

	//method for checking and creating an NBTTag
	public static NBTTagCompound getTagCompound(ItemStack stack) {
		NBTTagCompound tag = stack.getTagCompound();
		if (tag == null) {
			tag = new NBTTagCompound();
			stack.setTagCompound(tag);
		}
		return tag;
	}

}
