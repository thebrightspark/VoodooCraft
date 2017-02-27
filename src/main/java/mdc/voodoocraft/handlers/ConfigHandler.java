package mdc.voodoocraft.handlers;

import mdc.voodoocraft.util.Refs;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class ConfigHandler
{
	public static Configuration config;

	public static void init(File configFile)
	{
		//Create configuration object from the given configuration file
		if(config == null)
		{
			config = new Configuration(configFile);
			loadConfiguration();
		}

		//Not sure about these... someone else added them XD
		config.addCustomCategoryComment("Tweaks", "Tweak certian things");
		config.addCustomCategoryComment("Items", "Set any values to false to disable the item");
	}

	private static void loadConfiguration()
	{
		//Load configs here



		if(config.hasChanged())
			config.save();
	}

	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if(event.getModID().equalsIgnoreCase(Refs.MODID))
			//Resync configs
			loadConfiguration();
	}
}
