package mdc.voodoocraft.handlers;

import mdc.voodoocraft.blocks.*;
import mdc.voodoocraft.init.*;
import mdc.voodoocraft.util.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import net.minecraft.util.text.*;
import net.minecraft.world.*;
import net.minecraftforge.event.entity.*;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.eventhandler.*;

@Mod.EventBusSubscriber
public class VCEventHandler {
    
    @SubscribeEvent
    public static void displayWelcomeChat(EntityJoinWorldEvent event){
    	if(!event.getWorld().isRemote){
			if (event.getEntity() instanceof EntityPlayerMP) {
				EntityPlayerMP player = (EntityPlayerMP) event.getEntity();
				player.sendMessage(new TextComponentString(TextFormatting.GOLD + "Welcome to VoodooCraft by ModDev-Cafe Modding Team!"));
				player.sendMessage(new TextComponentString(TextFormatting.GOLD + "For Tips and Tricks About the Mod"));
				player.sendMessage(new TextComponentString(TextFormatting.GOLD + "Use the Following Command: "));
				player.sendMessage(new TextComponentString(TextFormatting.GOLD + "/vdcraft help <feature>"));
			}
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