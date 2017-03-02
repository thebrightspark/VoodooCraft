package mdc.voodoocraft.tile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileDollPedestal extends TileEntity implements ITickable{
	
	private int waitTime;
	private boolean cachedHasItem = false;
	
	public TileDollPedestal(){super();}

	/**
	 * As of now this spawns an EntityItem version of the doll and keeps it there, removed in the breakBlock method
	 * of the BlockDollPedestal.
	 * There is a much better way to do this. All of this should be re-written or removed
	 */
	@Override
	public void update()
	{
		if(world.isRemote) return;
		IItemHandler tileinv = this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

		if(tileinv.getStackInSlot(0)!=null)
		{
			if(waitTime>499||!cachedHasItem)
			{
				EntityItem itementity = new EntityItem(this.getWorld(), pos.getX()+0.5, pos.getY()+1, pos.getZ()+0.5, tileinv.getStackInSlot(0));
				itementity.lifespan=500;
				itementity.setInfinitePickupDelay();
				itementity.setVelocity(0, 0, 0);
				itementity.hoverStart=0f;
				itementity.addTag("VCFakeItem"+this.getPos());
				this.getWorld().spawnEntity(itementity);
				this.waitTime=0;
			}else{
				++waitTime;
			}
			cachedHasItem = true;
		}
		else{
			cachedHasItem = false;
		}
	}
	
	public static void removeFakeItem(World worldIn, BlockPos pos)
	{
		TileDollPedestal tile = (TileDollPedestal)worldIn.getTileEntity(pos);
		if(tile!=null)
		{
			IItemHandler tileinv = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
			for(Entity entity : worldIn.getLoadedEntityList())
			{
				if(entity instanceof EntityItem)
				{
					EntityItem itementity = (EntityItem)entity;
					for(String tag : itementity.getTags())
					{
						if(tag.equals("VCFakeItem"+pos)||tag.equals("VCFakeItem"+pos.up()))
						{
							entity.setDead();
						}
					}
				}
			}
		}
	}
	
    private ItemStackHandler itemStackHandler = 
    		new ItemStackHandler(1){
        @Override
        protected void onContentsChanged(int slot) {
        	TileDollPedestal.this.markDirty();
        }
    };

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (compound.hasKey("items")) {
            itemStackHandler.deserializeNBT((NBTTagCompound) compound.getTag("items"));
        }
        this.cachedHasItem = compound.getBoolean("cachedHasItem");
        this.waitTime = compound.getInteger("waitTime");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound){
        super.writeToNBT(compound);
        compound.setTag("items", itemStackHandler.serializeNBT());
        compound.setBoolean("cachedHasItem", this.cachedHasItem);
        compound.setInteger("waitTime", this.waitTime);
        return compound;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return (T) itemStackHandler;
        }
        return super.getCapability(capability, facing);
    }

}
