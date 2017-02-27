package mdc.voodoocraft.items;

import mdc.voodoocraft.hexes.Hex;
import mdc.voodoocraft.init.VCHexes;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
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
        setHasSubtypes(true);
    }

    /**
     * Returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems)
    {
        //Dol without a Hex
        subItems.add(new ItemStack(itemIn));

        //Add a doll for every Hex
        ItemStack stack = new ItemStack(itemIn);
        for(Hex hex : VCHexes.HEXES.values())
        {
            ItemStack dollWithHex = stack.copy();
            addHex(dollWithHex, hex);
            subItems.add(dollWithHex);
        }
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

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        Hex hex = getHex(itemStackIn);
        if(hex != null && hex.unlocName.equals("regen"))
        {
            playerIn.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100));
            return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
        }
        return new ActionResult<>(EnumActionResult.PASS, itemStackIn);
    }

    /**
     * Allows items to add custom lines of information to the mouseover description
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        tooltip.add("Current Hex applied:");
        Hex hex = getHex(stack);
        if(hex != null)
            tooltip.add(hex.getDesc());
        else
            tooltip.add("None");
    }
}
