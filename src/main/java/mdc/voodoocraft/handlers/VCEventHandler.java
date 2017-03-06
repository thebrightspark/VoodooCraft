package mdc.voodoocraft.handlers;

import mdc.voodoocraft.blocks.BlockGlyph;
import mdc.voodoocraft.init.VCBlocks;
import mdc.voodoocraft.util.EnumGlyphType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber
public class VCEventHandler {

    @SubscribeEvent
    public static void fluidWalking(TickEvent.PlayerTickEvent e) {
        BlockPos pos = e.player.getPosition();
        World world = e.player.world;
        Entity player = e.player;

        if (world.getBlockState(pos.down()).getMaterial() == Material.WATER) {
            player.motionY = 0.1D;
        } else if (world.getBlockState(pos.down()).getMaterial() == Material.LAVA) {
            player.motionY = 0.1D;
        }
    }
    
    @SubscribeEvent
    public static void createDeathGlyphs(LivingDeathEvent event) {
    	if(event.getEntityLiving() instanceof EntityPlayer) {
    		EntityPlayer player = (EntityPlayer) event.getEntityLiving();
    		World world = player.getEntityWorld();
    		if(world.isRemote) return;
    		BlockPos pos = player.getPosition();
    		int toAdd = 0;
    		boolean up = false;
    		BlockPos pos2;
    		do {
    			pos2 = up? pos.up(toAdd) : pos.down(toAdd);
    			up = !up;
    			if(up) toAdd++;
    		}while(!VCBlocks.GLYPH.canPlaceBlockAt(world, pos2));
    		world.setBlockState(pos2, ((BlockGlyph) VCBlocks.GLYPH).setGlyphType(EnumGlyphType.DEATH));
    		System.out.println("placed death glyph at " + pos2.getX() + ", " + pos2.getY() + ", " + pos2.getZ());
    	}
    }
}