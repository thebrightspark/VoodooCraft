package mdc.voodoocraft.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class VCModelBlock extends VCBlock{

	private final AxisAlignedBB Hitbox;
	
	public VCModelBlock(String name, AxisAlignedBB hitbox, Material mat)
	{
		super(name, mat);
		this.Hitbox = hitbox;
	}
	public VCModelBlock(String name, AxisAlignedBB hitbox)
	{
		this(name, hitbox,Material.ROCK);
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
        return this.Hitbox;
    }
}
