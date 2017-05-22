package mdc.voodoocraft.hexes;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class HexWaterBreathing extends HexEntry{
    public HexWaterBreathing() {
        super("waterbreathing");
    }

    @Override
    public ItemStack activeUse(ItemStack stackIn, World world, EntityPlayer player, EnumHand hand, int strength, @Nullable EntityLivingBase target) {
        if(!world.isRemote){
            player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 3000));
        }

        return super.activeUse(stackIn, world, player, hand, strength, target);
    }
}
