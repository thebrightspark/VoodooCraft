package mdc.voodoocraft.items;

import net.minecraft.item.Item;

public class VCDamageItem extends Item {
    public VCDamageItem(String name, int damage)
    {
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setMaxDamage(damage);
    }

}
