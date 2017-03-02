package mdc.voodoocraft.handlers;


import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class VCEventHandler {

    @SubscribeEvent
    public void fluidWalking(TickEvent.PlayerTickEvent e){
        BlockPos pos = e.player.getPosition();
        World world = e.player.worldObj;
        Entity player = e.player;

        if(world.getBlockState(pos.down()).getMaterial() == Material.WATER){
            player.motionY = 0.1D;
        }else if(world.getBlockState(pos.down()).getMaterial() == Material.LAVA){
            player.motionY = 0.1D;
        }
    }
}