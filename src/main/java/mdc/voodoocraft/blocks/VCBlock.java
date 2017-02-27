package mdc.voodoocraft.blocks;

import mdc.voodoocraft.VoodooCraft;
import mdc.voodoocraft.util.Refs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class VCBlock extends Block
{
    public VCBlock(String name, Material mat)
    {
        super(mat);
        setUnlocalizedName(Refs.MODID + name);
        setRegistryName(name);
        setCreativeTab(VoodooCraft.CREATIVE_TAB);
    }
    public VCBlock(String name)
    {
    	this(name, Material.ROCK);
    }
}
