package mdc.voodoocraft.hexes;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.pattern.BlockMaterialMatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.fluids.FluidEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class HexWaterWalking extends HexEntry{
    public HexWaterWalking(){
        super("water_walking");
    }

    @SubscribeEvent
    public static void fluidWalking(PlayerEvent event) {
        EntityPlayerMP player = (EntityPlayerMP)event.player;
        World world = player.getEntityWorld();
        BlockPos position = player.getPosition();
        Material mat = world.getBlockState(position).getMaterial();
        if(mat == Material.WATER){
            float newY = position.getY() + 0.5F;
            event.player.setPosition(position.getX(), position.getY(), position.getZ());
        }
    }
}
