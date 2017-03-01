package mdc.voodoocraft.hexes;

import javax.annotation.Nullable;

import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HexEntry extends IForgeRegistryEntry.Impl<HexEntry>
{
	private String name;
    
    public HexEntry() {
    	//nothing here atm.
	}
    
    public HexEntry setUnlocalizedName(String name) {
    	this.name = name;
    	return this;
    }

    @Nullable
    @SideOnly(Side.CLIENT)
    public String getDesc()
    {
        return null;
    }
    
    public String getUnlocalizedName() {
    	return "hex." + this.name + ".name";
    }
    
    @SideOnly(Side.CLIENT)
    public String getFormattedName() {
    	return I18n.format(this.getUnlocalizedName());
    }
    
    public NBTTagCompound toNBT() {
    	return new NBTTagCompound();
    }
    
}
