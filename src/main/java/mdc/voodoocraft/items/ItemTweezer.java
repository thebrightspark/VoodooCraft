package mdc.voodoocraft.items;


import mdc.voodoocraft.init.VCItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

import java.util.UUID;

public class ItemTweezer extends VCItem {
    public static UUID uid;
    String name;
    public ItemTweezer(String name) {
        super(name);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        uid = target.getPersistentID();
        name = target.getName();
        target.dropItem(new ItemHair(name), 1);
        System.out.println("The UUID is" + uid);
        return super.hitEntity(stack, target, attacker);
    }
}
