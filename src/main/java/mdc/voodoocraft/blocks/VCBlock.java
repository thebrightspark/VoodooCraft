package mdc.voodoocraft.blocks;

import mdc.voodoocraft.VoodooCraft;
import mdc.voodoocraft.registry.Refs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class VCBlock extends Block
{
    public VCBlock(String name)
    {
        super(Material.ROCK);
        setUnlocalizedName(Refs.MODID + name);
        setRegistryName(name);
        setCreativeTab(VoodooCraft.CREATIVE_TAB);
    }
}
