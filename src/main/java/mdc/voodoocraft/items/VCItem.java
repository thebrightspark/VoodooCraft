package mdc.voodoocraft.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class VCItem extends Item
{
	private boolean HasEffect;
	
    public VCItem(String name)
    {
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
    }
    
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return super.hasEffect(stack) || this.HasEffect;
    }
}
