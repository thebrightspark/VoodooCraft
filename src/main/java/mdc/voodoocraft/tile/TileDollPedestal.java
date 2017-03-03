package mdc.voodoocraft.tile;

import mdc.voodoocraft.items.ItemDoll;
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
import net.minecraftforge.common.DimensionManager;
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
		if(this.world.isRemote) return;
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
		/*
		if(DimensionManager.getWorld(0).equals(this.getWorld())) System.out.println("It has the world");;
        TileDollPedestal tile = (TileDollPedestal)this.getWorld().getTileEntity(this.getPos());
        IItemHandler tileinv = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        if(tileinv.getStackInSlot(0)!=null)
        {
        	//TODO Add tile entity to list
        }
        */
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

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return (T) itemStackHandler;
        }
        return super.getCapability(capability, facing);
    }

}
