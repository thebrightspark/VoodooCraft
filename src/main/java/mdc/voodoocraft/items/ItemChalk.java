package mdc.voodoocraft.items;

import mdc.voodoocraft.init.VCBlocks;
import mdc.voodoocraft.util.EnumChalkType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemChalk extends VCItem{

	private EnumChalkType type;
	
	public ItemChalk(String name, EnumChalkType type)
	{
		super(name);
		this.type = type;
		this.setFull3D();
	}

	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		if(!(stack.getItem() instanceof ItemChalk)||!worldIn.getBlockState(pos).isFullBlock()) return EnumActionResult.PASS;
		
		if(worldIn.isAirBlock(pos.up()) && worldIn.isSideSolid(pos, EnumFacing.UP) && !worldIn.isRemote)
		{
			worldIn.setBlockState(pos.up(), this.getChalkBlock());
			return EnumActionResult.SUCCESS;
		}
        return EnumActionResult.PASS;
    }
	
	/**
	 * calculate the chalk block based on the {@link EnumChalkType}
	 * @return the appropriate {@link IBlockState}
	 */
	public IBlockState getChalkBlock() {
		switch (this.type) {
		case BASIC:
			return VCBlocks.CHALK_BASIC_SYMBOL.getDefaultState();
		}
		return null;
	}
	
}
