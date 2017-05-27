package mdc.voodoocraft.blocks;

import javax.annotation.Nullable;

import mdc.voodoocraft.tile.TileDollPedestal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class BlockDollPedestal extends VCModelBlock{

	private final static AxisAlignedBB hitbox = new AxisAlignedBB(0.1875, 0.0D, 0.1875D, 0.8125D, 0.875D, 0.8125D);
	//private final static AxisAlignedBB aboveBlock = new AxisAlignedBB(0.1875, 0.0D, 0.1875D, 0.8125D, 1.875D, 0.8125D);
	
	public BlockDollPedestal()
	{
		super("dollpedestal");
		isBlockContainer = true;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return hitbox;
	}
	
	public TileEntity createTileEntity(World world, IBlockState state)
    {
		return new TileDollPedestal();
    }
	@Override
	public boolean hasTileEntity(IBlockState state){return true;}
	
	/**
	 * Puts dolls in and out of the pedestal.
	 * @return
	 */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		if(hand==EnumHand.OFF_HAND||worldIn.getTileEntity(pos)==null) return false;
		TileDollPedestal tile = (TileDollPedestal)worldIn.getTileEntity(pos);
		IItemHandler tileinv = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		if(heldItem==null)
		{
			if(tileinv.getStackInSlot(0)!=null)
			{
				playerIn.setHeldItem(hand, tileinv.getStackInSlot(0));
				tileinv.extractItem(0, tileinv.getStackInSlot(0).stackSize, false);
				return true;
			}
		}else{
			if(tileinv.getStackInSlot(0)==null)
			{
				tileinv.insertItem(0, heldItem, false);
				playerIn.setHeldItem(hand, null);
				return true;
			}
		}
        return false;
    }
	/**
	 * Drops item in the pedestal
	 * @param worldIn
	 * @param pos
	 * @param state
	 */
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
		if(!worldIn.isRemote)
		{
			TileEntity te = worldIn.getTileEntity(pos);
			if(te!=null)
			{
				TileDollPedestal tile= (TileDollPedestal)te;
				IItemHandler tileinv = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
				ItemStack stack = tileinv.getStackInSlot(0);
				if(stack!=null)
				{
					EntityItem entItem = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack);
					worldIn.spawnEntity(entItem);
				}
			}
		}
		super.breakBlock(worldIn, pos, state);
    }
	/*
	public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
    }
    */
}
