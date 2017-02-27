package mdc.voodoocraft.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockChalk extends VCBlock{

	private final static AxisAlignedBB hitbox = new AxisAlignedBB(0.0, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D);
	
	public BlockChalk() {
		super("chalksymbol", Material.CIRCUITS);
		this.setHardness(0);
	}

	@Override
	public boolean isVisuallyOpaque()
    {
		return false;
    }
	@Override
	public boolean isFullCube(IBlockState state)
    {
        return false;
    }
	@Override
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return hitbox;
    }
}
