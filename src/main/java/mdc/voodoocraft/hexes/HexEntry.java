package mdc.voodoocraft.hexes;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import mdc.voodoocraft.handlers.RegHandler;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class HexEntry extends IForgeRegistryEntry.Impl<HexEntry>
{
	private String unlocalizedName;
    
    public HexEntry() {
    	//nothing here atm.
	}
    
    public HexEntry(String name) {
    	this();
    	this.setRegistryName(name);
    	this.setUnlocalizedName(name);
    }
    
    public HexEntry setUnlocalizedName(String name) {
    	this.unlocalizedName = name;
    	return this;
    }

    @Nonnull
    @SideOnly(Side.CLIENT)
    public String getDescription()
    {
        return I18n.format("desc." + this.getUnlocalizedName());
    }
    
    @Nonnull
    public String getUnlocalizedName() {
    	return "hex." + this.unlocalizedName + ".name";
    }
    
    @SideOnly(Side.CLIENT)
    public String getFormattedName() {
    	return I18n.format(this.getUnlocalizedName());
    }
    
    @Nullable
    public static HexEntry byName(String name) {
    	return byName(new ResourceLocation(name));
    }

    @Nullable
	public static HexEntry byName(ResourceLocation location) {
		return RegHandler.getHexRegistry().getObject(location);
	}

    /**
     * @param strength the strength of the {@link Hex}
     * @return the individual damage this single Entry does to the {@link ItemStack}.
     * <br/>will be multiplied with the amount of {@link Hex}es in total.
     * <br/><b>remember: 0 cost means level 1</b>
     */
    @Nonnegative
	public int getCost(int strength) {
		return 0;
	}

    /**
     * called by the {@link Hex} to execute the active use of this hex
     * @param strength 
     * @return the ItemStack to update the player's active ItemStack
     */
	public ItemStack activeUse(ItemStack stackIn, World world, EntityPlayer player, EnumHand hand, int strength) {
		return stackIn;
	}
	
	//TODO: how would we go about passive uses?
	//I think we could make each HexEntry handle that by itself using events, I guess
    
}
