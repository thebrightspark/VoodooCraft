package mdc.voodoocraft.blocks;

import java.util.List;

import mdc.voodoocraft.init.VCItems;
import mdc.voodoocraft.items.ItemChalk;
import mdc.voodoocraft.tile.TileEntityGlyph;
import mdc.voodoocraft.util.EnumGlyphType;
import mdc.voodoocraft.util.NBTHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
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
	public static final PropertyEnum<EnumGlyphType> TYPE = PropertyEnum.create("type", EnumGlyphType.class); 
	
	public BlockGlyph()
	{
		super("glyph", Material.CIRCUITS);
		setHardness(0);
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return state.getValue(TYPE) == EnumGlyphType.DEATH;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		if(state.getValue(TYPE) == EnumGlyphType.DEATH) return new TileEntityGlyph();
		return super.createTileEntity(world, state);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[]{TYPE});
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer, ItemStack stack) {
		if(stack != null && stack.getItem() == VCItems.CHALK_BASIC) return this.getDefaultState().withProperty(TYPE, EnumGlyphType.byIndex(NBTHelper.getTagCompound(stack).getInteger(ItemChalk.KEY_GLYPH)));
		return this.getDefaultState();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(TYPE, EnumGlyphType.byIndex(meta));
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(TYPE).ordinal();
	}
	
	public IBlockState setGlyphType(EnumGlyphType type) {
		return this.getDefaultState().withProperty(TYPE, type);
	}
	
	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return super.canPlaceBlockAt(worldIn, pos) && this.canBlockStay(worldIn, pos);
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
        return worldIn.isSideSolid(pos.down(), EnumFacing.UP, false);
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
		ItemStack stack = new ItemStack(VCItems.CHALK_BASIC);
		NBTTagCompound nbt = NBTHelper.getTagCompound(stack);
		nbt.setInteger(ItemChalk.KEY_GLYPH, state.getValue(TYPE).ordinal());
		stack.setTagCompound(nbt);
		return stack;
	}
}
