package mdc.voodoocraft.items;

import mdc.voodoocraft.VoodooCraft;
import mdc.voodoocraft.util.Refs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class VCItem extends Item
{
	private boolean HasEffect;
	
    public VCItem(String name, boolean hasEffect)
    {
        setUnlocalizedName(Refs.MODID + name);
        setRegistryName(name);
        setCreativeTab(VoodooCraft.CREATIVE_TAB);
    }
    
    public VCItem(String name)
    {
    	this(name, false);
    }
    
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return this.HasEffect;
    }
}
