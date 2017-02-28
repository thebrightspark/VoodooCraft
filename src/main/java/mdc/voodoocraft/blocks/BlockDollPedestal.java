package mdc.voodoocraft.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockDollPedestal extends VCModelBlock{

	private final static AxisAlignedBB hitbox = new AxisAlignedBB(0.1875, 0.0D, 0.1875D, 0.8125D, 0.875D, 0.8125D);
	
	public BlockDollPedestal() {
		super("dollpedestal");
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return hitbox;
	}
	
}
