package mdc.voodoocraft.hexes;

import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Hex implements INBTSerializable<NBTTagCompound>
{
    private String unlocName;
    private int strength;

    public Hex() {}

    public Hex setUnlocName(String unlocName)
    {
        this.unlocName = unlocName;
        return this;
    }

    private String getUnlocKey()
    {
        return "Hex." + unlocName;
    }

    public String getUnlocName()
    {
        return getUnlocKey() + ".name";
    }

    @SideOnly(Side.CLIENT)
    public String getDesc()
    {
        return I18n.format(unlocName + "");
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        return null;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {

    }
}
