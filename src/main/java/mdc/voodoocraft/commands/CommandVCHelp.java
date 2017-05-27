package mdc.voodoocraft.commands;

import java.util.*;

import mdc.voodoocraft.config.*;
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
					sendMessageToChat(sender, TextFormatting.LIGHT_PURPLE + VoodooConfig.commandResultDetails[0]);
				}else if(args[1].equals("rituals")){
					sendMessageToChat(sender, TextFormatting.BLUE + VoodooConfig.commandResultDetails[1]);
				}else if(args[1].equals("items")){
					sendMessageToChat(sender, TextFormatting.GREEN+VoodooConfig.commandResultDetails[2]);
					if(args[2].equals("chalk")){
						commandFindItem(sender, "chalk");
					}
					else if(args[2].equals("dolls")){
						commandFindItem(sender, "dolls");
					}
					else if(args[2].equals("shard")){
						commandFindItem(sender, "shard");
					}
				}
			}
			
		}
	}
	
	private void commandFindItem(ICommandSender sender, String itemName){
		switch(itemName){
		case "chalk":
			sendMessageToChat(sender, VoodooConfig.commandResultDetails[3]);
			break;
		case "dolls":
			sendMessageToChat(sender, VoodooConfig.commandResultDetails[4]);
			break;
		case "shard":
			sendMessageToChat(sender, VoodooConfig.commandResultDetails[5]);
			break;
		}
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos) {
		if(args.length == 1){
			return getListOfStringsMatchingLastWord(args, new String[]{"help"});
		}else{
			if(args.length == 2){
				return getListOfStringsMatchingLastWord(args, new String[]{"hexes", "rituals", "items"});
			}if(args.length == 3){
				return getListOfStringsMatchingLastWord(args, new String[]{"chalk", "dolls", "shard"});
			}
		}
		return Collections.<String>emptyList();
	}
	
	public boolean sendMessageToChat(ICommandSender sender, String message){
		sender.sendMessage(new TextComponentString(message));
		return true;
	}
}
