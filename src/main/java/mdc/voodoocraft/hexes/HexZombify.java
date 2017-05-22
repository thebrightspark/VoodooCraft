package mdc.voodoocraft.hexes;


import mdc.voodoocraft.VoodooCraft;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class HexZombify extends HexEntry {
    public HexZombify(){
        super("zombify");
    }

    @Override
    public ItemStack activeUse(ItemStack stackIn, World world, EntityPlayer player, EnumHand hand, int strength, @Nullable EntityLivingBase target) {
        if(world.isRemote) {
            //Gets the result of the raytrace intersection that is 10 blocks away
            RayTraceResult result = Minecraft.getMinecraft().objectMouseOver;
            //Gets the entity from the raytrace result
            Entity e = result.entityHit;
            //Do what you need with the entity variable
            if (e instanceof EntityZombie) {
                EntityVillager villager = new EntityVillager(world);
                villager.copyLocationAndAnglesFrom(e);
                villager.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(villager)), null);
                world.removeEntity(e);
                //TODO: Particles ;)
//                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, false, e.getPosition().getX(), e.getPosition().getY(), e.getPosition().getZ(), 0.5D, 0.1D, Math.random());
                world.spawnEntity(villager);
            } else if (e instanceof EntityVillager) {
                EntityZombie zombie = new EntityZombie(world);
                zombie.copyLocationAndAnglesFrom(e);
                zombie.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(zombie)), null);
                world.removeEntity(e);
                //TODO: Particles ;)
//                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, false, e.getPosition().getX(), e.getPosition().getY(), e.getPosition().getZ(), 0.5D, 0.1D, Math.random());
                world.spawnEntity(zombie);
            }
        }
        return super.activeUse(stackIn, world, player, hand, strength, target);
    }
}
