package mdc.voodoocraft.hexes;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class HexSuffocate extends HexEntry{
    public HexSuffocate() {
        super("suffocate");
    }

    @Override
    public ItemStack activeUse(ItemStack stackIn, World world, EntityPlayer player, EnumHand hand, int strength, @Nullable EntityLivingBase target) {
        if(!world.isRemote){
            RayTraceResult result = Minecraft.getMinecraft().objectMouseOver;
            Entity e = result.entityHit;
            if(e instanceof EntityPlayerMP){
                EntityPlayer p = (EntityPlayer)e;
                for(int i = 0; i < p.getMaxHealth(); i++){
                    if(!p.capabilities.isCreativeMode) {
                        int dif = world.getDifficulty().getDifficultyId();
                        p.setHealth(p.getHealth() - dif);
                        p.performHurtAnimation();
                    }
                }
            }
        }

        return super.activeUse(stackIn, world, player, hand, strength, target);
    }
}
