package mdc.voodoocraft.blocks;

import java.util.List;

import mdc.voodoocraft.init.VCBlocks;
import mdc.voodoocraft.init.VCItems;
import mdc.voodoocraft.util.EnumGlyphType;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGlyph extends VCModelBlock{

	private static final AxisAlignedBB hitbox = new AxisAlignedBB(0.0, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D);

	public BlockGlyph(String name) {
		super(name, Material.CIRCUITS, false);
		this.setHardness(0);
	}
	
	public static IBlockState getStateFromEnumGlyphType(EnumGlyphType type)
	{
		switch (type) {
		case BASIC:
			return VCBlocks.CHALK_BASIC_SYMBOL.getDefaultState();
		case AIR:
			return VCBlocks.GLYPH_AIR.getDefaultState();
		case DARK:
			VCBlocks.GLYPH_DARK.getDefaultState();
		case DEATH:
			VCBlocks.GLYPH_DEATH.getDefaultState();
		case EARTH:
			VCBlocks.GLYPH_EARTH.getDefaultState();
		case FIRE:
			VCBlocks.GLYPH_FIRE.getDefaultState();
		case GIVE:
			VCBlocks.GLYPH_GIVE.getDefaultState();
		case LIFE:
			VCBlocks.GLYPH_LIFE.getDefaultState();
		case LIGHT:
			VCBlocks.GLYPH_LIGHT.getDefaultState();
		case LINK:
			VCBlocks.GLYPH_LINK.getDefaultState();
		case POWER:
			VCBlocks.GLYPH_POWER.getDefaultState();
		case SOUL:
			VCBlocks.GLYPH_SOUL.getDefaultState();
		case SPEED:
			VCBlocks.GLYPH_SPEED.getDefaultState();
		case TAKE:
			VCBlocks.GLYPH_TAKE.getDefaultState();
		case TIME:
			VCBlocks.GLYPH_TIME.getDefaultState();
		case TOX:
			VCBlocks.GLYPH_TOX.getDefaultState();
		case WATER:
			VCBlocks.GLYPH_WATER.getDefaultState();
		}
		return null;
	}
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn)
    {
		if(!canBlockStay(worldIn, pos))
		{
			worldIn.destroyBlock(pos, false);
		}
    }
	
	public boolean canBlockStay(World worldIn, BlockPos pos)
    {
        return worldIn.isSideSolid(pos.down(), EnumFacing.UP);
    }
	
	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
		return null;
    }
	
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return hitbox;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
		return NULL_AABB;
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		return new ItemStack(VCItems.CHALK_BASIC); //TODO: different chalks or not?
	}
}
