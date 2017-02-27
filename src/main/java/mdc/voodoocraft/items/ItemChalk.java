package mdc.voodoocraft.items;

import mdc.voodoocraft.blocks.BlockChalk;
import mdc.voodoocraft.init.VCBlocks;
import mdc.voodoocraft.init.VCItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemChalk extends VCItem{

	public ItemChalk(String name)
	{
		super(name);
	}

	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		if(!(stack.getItem() instanceof ItemChalk)||facing!=EnumFacing.UP||!worldIn.getBlockState(pos).isFullBlock()) return EnumActionResult.PASS;
		
		if(worldIn.getBlockState(pos.up()).getBlock()==Blocks.AIR)
		{
			if(stack.getItem()==VCItems.chalkbasic)
			worldIn.setBlockState(pos.up(), VCBlocks.chalkbasicsymbol.getDefaultState());
			return EnumActionResult.SUCCESS;
		}
        return EnumActionResult.PASS;
    }
}
