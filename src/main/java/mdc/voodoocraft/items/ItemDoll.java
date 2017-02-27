package mdc.voodoocraft.items;

import mdc.voodoocraft.hexes.Hex;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemDoll extends VCItem
{
    public ItemDoll()
    {
        super("doll");
        setMaxStackSize(1);
        setMaxDamage(1000);
    }

    /**
     * Adds a Hex to the given doll ItemStack
     */
    public static void addHex(ItemStack dollStack, Hex hex)
    {
        if(dollStack == null || !(dollStack.getItem() instanceof ItemDoll) || hex == null)
            return;

        NBTTagCompound stackNbt = dollStack.getTagCompound();
        if(stackNbt == null)
            stackNbt = new NBTTagCompound();
        stackNbt.setTag("hex", hex.serializeNBT());
        dollStack.setTagCompound(stackNbt);
    }

    /**
     * Gets the Hex from the given doll ItemStack
     */
    public static Hex getHex(ItemStack dollStack)
    {
        if(dollStack == null || !(dollStack.getItem() instanceof ItemDoll))
            return null;

        NBTTagCompound stackNbt = dollStack.getTagCompound();
        if(stackNbt == null)
            return null;
        Hex hex = new Hex();
        hex.deserializeNBT((NBTTagCompound) stackNbt.getTag("hex"));
        return hex;
    }

    /**
     * Allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        tooltip.add("Current Hexes applied:");
        Hex hex = getHex(stack);
        if(hex != null)
        {
            tooltip.add("");
        }
        else
            tooltip.add("None");
    }
}
