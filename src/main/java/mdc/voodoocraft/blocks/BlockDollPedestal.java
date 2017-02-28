package mdc.voodoocraft.blocks;

import javax.annotation.Nullable;

import mdc.voodoocraft.tile.TileDollPedestal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDollPedestal extends VCModelBlock{

	private final static AxisAlignedBB hitbox = new AxisAlignedBB(0.1875, 0.0D, 0.1875D, 0.8125D, 0.875D, 0.8125D);
	
	public BlockDollPedestal() {
		super("dollpedestal");
		this.isBlockContainer = true;
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
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		if(hand==EnumHand.OFF_HAND||worldIn.getTileEntity(pos)==null) return false;
		
		TileDollPedestal tile = (TileDollPedestal)worldIn.getTileEntity(pos);
		if(playerIn.getHeldItem(EnumHand.MAIN_HAND)==null)
		{
			//Take item out pedestal
		}else{
			//Put item in the pedestal
		}
        return false;
    }
	
}
