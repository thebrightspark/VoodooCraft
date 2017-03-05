package mdc.voodoocraft.items;

import java.util.List;

import mdc.voodoocraft.blocks.BlockGlyph;
import mdc.voodoocraft.init.VCBlocks;
import mdc.voodoocraft.util.EnumGlyphType;
import mdc.voodoocraft.util.NBTHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemChalk extends VCItem{
	
	public ItemChalk(String name)
	{
		super(name);
		this.setFull3D();
		this.setMaxStackSize(1);
		this.setMaxDamage(200);
	}
	/**
	 * Shows what type of glyph is on the chalk, this should probably be changed so it is shown
	 * in the actual item name.
	 */
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
		if(stack.getTagCompound()==null) tooltip.add("Glyph Type: Basic");
		else{
			String uppercase = EnumGlyphType.byName(stack.getTagCompound().getString("glyphtype")).toString();
			String correctcase = uppercase.substring(0, 1) + uppercase.substring(1).toLowerCase();
			tooltip.add("Glyph Type: "+ correctcase);
		}
    }
	
	/**
	 * Changes glyphtype NBT, cycles through all in the {@link EnumGlyphType}
	 */
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        return new ActionResult(EnumActionResult.PASS, itemStackIn);
    }
	
	/**
	 * Places Glyph on block you right click
	 */
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		if(!(stack.getItem() instanceof ItemChalk)||!worldIn.getBlockState(pos).isFullBlock()) return EnumActionResult.PASS;
		
		if(playerIn.isSneaking())
		{
			EnumGlyphType newtype = EnumGlyphType.getNext(EnumGlyphType.byName(NBTHelper.getTagCompound(stack).getString("glyphtype")));
			stack.getTagCompound().setString("glyphtype", newtype.getName());
		}
		else if(worldIn.isAirBlock(pos.up()) && worldIn.isSideSolid(pos, EnumFacing.UP) && !worldIn.isRemote)
		{
			worldIn.setBlockState(pos.up(), this.getGlyphBlock(stack));
			return EnumActionResult.SUCCESS;
		}
        return EnumActionResult.PASS;
    }
	
	/**
	 * calculate the chalk block based on the {@link EnumGlyphType}
	 * @return the appropriate {@link IBlockState}
	 */
	public IBlockState getGlyphBlock(ItemStack stack) {
		EnumGlyphType type = EnumGlyphType.byName(NBTHelper.getTagCompound(stack).getString("glyphtype"));
		return BlockGlyph.getStateFromEnumGlyphType(type);
	}
	
	/**
	 * Makes sure the chalk will always have basic type set even if spawned in creative.
	 */
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
		if(stack.getTagCompound()==null)
		{
			NBTHelper.getTagCompound(stack).setString("glyphtype", "basic_glyph");
		}
    }
}
