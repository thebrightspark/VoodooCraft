package mdc.voodoocraft.hexes;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class HexInsomnia extends HexEntry {

    public HexInsomnia(){
        super ("insomnia");
    }

    @Override
    public ItemStack activeUse(ItemStack stackIn, World world, EntityPlayer player, EnumHand hand, int strength, @Nullable EntityLivingBase target) {
        if(world.isRemote) {
            if (world.getWorldTime() >= 11615) {
                world.setWorldTime(1000);
            }
        }
        return super.activeUse(stackIn, world, player, hand, strength, target);
    }


}
