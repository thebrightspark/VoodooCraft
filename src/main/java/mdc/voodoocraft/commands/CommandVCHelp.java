package mdc.voodoocraft.commands;

import java.util.*;

import net.minecraft.command.*;
import net.minecraft.server.*;
import net.minecraft.util.math.*;
import net.minecraft.util.text.*;
import net.minecraft.world.*;

public class CommandVCHelp extends CommandBase{

	@Override
	public String getName() {
		return "vdcraft";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/vdcraft help <feature>";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		World world = sender.getEntityWorld();
		
		if(world.isRemote){
			System.out.println("Not processing on Client Side");
		}else{
			System.out.println("Processing on Server Side");
			if(args.length == 0){
				sendMessageToChat(sender, TextFormatting.RED + "Invalid argument");
				return;
			}
			
			if(args[0].equals("help")){
				if(args[1].equals("hexes")){
					sendMessageToChat(sender, TextFormatting.LIGHT_PURPLE + 
							"Hexes are what you use to cast either a light or a dark \'spell\' on an entity. Hexes are categorized into two different kinds of hexes, as suggested: light and dark." +
							"Light hexes consist of any hexes that don\'t cause any kind of harm to another entity. Dark hexes consist of any hexes that DO cause harm to another entity." +
							"To apply a hex, you must first have a certain totem poll and symbols drawn on the ground surrounding the pedestal. For more information on rituals, do /vdcraft help ritual or go to our wiki page here: <TODO: Create wiki page and provide link>."
						);
				}
			}
			
		}
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos) {
		return null;
	}
	
	public void sendMessageToChat(ICommandSender sender, String message){
		sender.sendMessage(new TextComponentString(message));
	}
}
