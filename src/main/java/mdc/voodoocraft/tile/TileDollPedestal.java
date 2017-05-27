package mdc.voodoocraft.tile;

import mdc.voodoocraft.handlers.DollTracker;
import mdc.voodoocraft.items.ItemDoll;
import mdc.voodoocraft.util.NBTHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileDollPedestal extends TileEntity implements ITickable{
	
	public TileDollPedestal(){super();}

	/**
	 * Should be used to tick the doll inside of it.
	 */
	@Override
	public void update()
	{
		if(world.isRemote) return;
		IItemHandler tileinv = this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		ItemStack stack = tileinv.getStackInSlot(0);
		if(stack!=null&&stack.getItem() instanceof ItemDoll)
		{
			//TODO Tick the doll inside
		}
			
	}
	
	@Override
	public void onLoad()
    {
        TileDollPedestal tile = (TileDollPedestal)this.getWorld().getTileEntity(this.getPos());
        
        DollTracker.updateTileEntry(tile);
    }
	
    private ItemStackHandler itemStackHandler = 
    		new ItemStackHandler(1){
        @Override
        protected void onContentsChanged(int slot) {
        	DollTracker.updateTileEntry(TileDollPedestal.this);
        	TileDollPedestal.this.markDirty();
        }
    };

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (compound.hasKey("items")) {
            itemStackHandler.deserializeNBT((NBTTagCompound) compound.getTag("items"));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound){
        super.writeToNBT(compound);
        compound.setTag("items", itemStackHandler.serializeNBT());
        return compound;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @SuppressWarnings("unchecked") //need this so eclipse doesn't complain
	@Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return (T) itemStackHandler;
        }
        return super.getCapability(capability, facing);
    }

}
