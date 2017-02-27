package mdc.voodoocraft.hexes;

import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Hex implements INBTSerializable<NBTTagCompound>
{
    public String unlocName;
    private int strength = 1;

    public Hex() {}

    public Hex setUnlocName(String unlocName)
    {
        this.unlocName = unlocName;
        return this;
    }

    public Hex setStrength(int strength)
    {
        this.strength = strength;
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
    public String getLocalName()
    {
        return I18n.format(getUnlocName());
    }

    @SideOnly(Side.CLIENT)
    public String getDesc()
    {
        return getLocalName() + " (" + strength + ")";
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("name", unlocName);
        nbt.setInteger("strength", strength);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {
        unlocName = nbt.getString("name");
        strength = nbt.getInteger("strength");
    }
}
