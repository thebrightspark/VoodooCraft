package mdc.voodoocraft.items;

import mdc.voodoocraft.VoodooCraft;
import mdc.voodoocraft.registry.Refs;
import net.minecraft.item.Item;

public class VCItem extends Item
{
    public VCItem(String name)
    {
        setUnlocalizedName(Refs.MODID + name);
        setRegistryName(name);
        setCreativeTab(VoodooCraft.CREATIVE_TAB);
    }
}
