package mdc.voodoocraft.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemChalk extends VCItem{

	public ItemChalk()
	{
		super("chalk");
	}

	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		if(!(stack.getItem() instanceof ItemChalk)||facing!=EnumFacing.UP) return EnumActionResult.PASS;
		
		
		
        return EnumActionResult.PASS;
    }
}
