package mdc.voodoocraft.hexes;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class HexInsomnia extends HexEntry {

    public HexInsomnia(){
        super ("insomnia");
    }

    @Override
    public ItemStack activeUse(ItemStack stackIn, World world, EntityPlayer player, EnumHand hand, int strength) {
        if (world.getWorldTime() >= 11615) {
            world.setWorldTime(1000);
        }
        return super.activeUse(stackIn, world, player, hand, strength);
    }


}
