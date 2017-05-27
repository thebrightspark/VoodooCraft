package mdc.voodoocraft.hexes;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;

import javax.annotation.Nullable;

public class HexDeath extends HexEntry{
    public HexDeath() {
        super("death");
    }

    @Override
    public ItemStack activeUse(ItemStack stackIn, World world, EntityPlayer player, EnumHand hand, int strength, @Nullable EntityLivingBase target) {
        if(!world.isRemote){
            RayTraceResult result = Minecraft.getMinecraft().objectMouseOver;
            Entity e = result.entityHit;
            e.setDead();
            if(e.isDead){

            }
        }
        return super.activeUse(stackIn, world, player, hand, strength, target);
    }
}
