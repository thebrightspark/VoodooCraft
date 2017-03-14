package mdc.voodoocraft.hexes;


import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class HexZombify extends HexEntry {
    public HexZombify(){
        super("zombify");
    }

    @Override
    public ItemStack activeUse(ItemStack stackIn, World world, EntityPlayer player, EnumHand hand, int strength) {
        //Gets the result of the raytrace intersection that is 10 blocks away
        RayTraceResult result = player.rayTrace(10, 1);
        //Gets the entity from the raytrace result
        Entity target = result.entityHit;
        //Do what you need with the entity variable
        EntityVillager villager = new EntityVillager(world);
        if(target instanceof EntityZombie){
            System.out.println("Hit the if *******************");
        }



        return super.activeUse(stackIn, world, player, hand, strength);
    }
}
