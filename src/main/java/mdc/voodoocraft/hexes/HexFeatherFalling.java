package mdc.voodoocraft.hexes;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class HexFeatherFalling  extends HexEntry{

    @SubscribeEvent
    public static void featherFall(LivingFallEvent event){
        if(!event.getEntity().world.isRemote){
            if(event.getEntity() instanceof EntityPlayerMP){
                event.setCanceled(true);
            }
        }
    }
}
