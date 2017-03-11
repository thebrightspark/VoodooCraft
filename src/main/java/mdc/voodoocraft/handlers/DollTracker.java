package mdc.voodoocraft.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import mdc.voodoocraft.items.ItemDoll;
import mdc.voodoocraft.tile.TileDollPedestal;
import mdc.voodoocraft.util.DollTrackerObj;
import mdc.voodoocraft.util.NBTHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
/**
 * Class for keeping track of dolls in pedestals, so they can be found from events.<br>
 * 
 * Basically the Doll should have a UUID of the entity and a true boolean if it is a player. Then you just call
 * tryTileEntry to add a Doll Pedestal to its respective list.
 * @author TheUnderTaker11
 */
public class DollTracker {
	
	/**Any pedestals with a player's doll should be in this list */
	public static List<DollTrackerObj> playerList = new ArrayList<DollTrackerObj>();
	
	public static List<DollTrackerObj> entityList = new ArrayList<DollTrackerObj>();
	
	/**
	 * Gets blockpos of respective pedestal from a UUID, returns null if the given entity <br>
	 * is not on the list.
	 * @param id
	 * @param isPlayer Give true if the UUID comes from a player
	 * @return
	 */
	@Nullable
	public static BlockPos getBlockPosFromUUID(UUID id, boolean isPlayer)
	{
		if(isPlayer){
			for(DollTrackerObj obj : playerList)
			{
				if(obj.getID().equals(id))
				{
					return obj.getPos();
				}
			}
		}else{
			for(DollTrackerObj obj : entityList)
			{
				if(obj.getID().equals(id))
				{
					return obj.getPos();
				}
			}
		}
		return null;
	}
	/**
	 * Adds tile & UUID to the list if it should be, and removes them if nothing is in the pedestal.
	 * Called every time the TileDollPedestal inventory changes, and when it is loaded in.
	 * 
	 * @param tile
	 */
	public static void updateTileEntry(TileDollPedestal tile)
	{
		int dim = tile.getWorld().provider.getDimension();
		IItemHandler inv = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		ItemStack stack = inv.getStackInSlot(0);
        if(stack!=null&&stack.getItem() instanceof ItemDoll&&stack.getTagCompound()!=null
        		&&stack.getTagCompound().hasKey(NBTHelper.KEY_OWNER))
        {
        	if(stack.getTagCompound().getBoolean(NBTHelper.KEY_IS_PLAYER)==true)
        	{
        		addPlayerEntry(NBTUtil.getUUIDFromTag(stack.getTagCompound()), dim, tile.getPos());
        	}else{
        		addEntityEntry(NBTUtil.getUUIDFromTag(stack.getTagCompound()), dim, tile.getPos());
        	}
        }else{
        	for(DollTrackerObj obj : playerList)
        	{
        		if(obj.getPos()==tile.getPos()&&obj.getDim()==dim)
        			playerList.remove(obj);
        	}
        	for(DollTrackerObj obj : entityList)
        	{
        		if(obj.getPos()==tile.getPos()&&obj.getDim()==dim)
        			entityList.remove(obj);
        	}
        }
	}
	/**
	 * Adds an entry to the player list, if it is not already there.
	 * @param id
	 * @param dim
	 * @param pos
	 */
	public static void addPlayerEntry(UUID id, int dim,BlockPos pos)
	{
		for(DollTrackerObj obj : playerList)
		{
			if(obj.getID().equals(id))
			{
				return;
			}
		}
		playerList.add(new DollTrackerObj(id, dim, pos));
	}
	/**
	 * Adds an entry to the regular entity list, if not already there.
	 * @param id
	 * @param pos
	 * @param dim
	 */
	public static void addEntityEntry(UUID id, int dim,BlockPos pos)
	{
		for(DollTrackerObj obj : entityList)
		{
			if(obj.getID().equals(id))
			{
				return;
			}
		}
		entityList.add(new DollTrackerObj(id, dim, pos));
	}
}
