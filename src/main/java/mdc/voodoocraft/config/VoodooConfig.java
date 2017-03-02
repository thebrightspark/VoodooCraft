package mdc.voodoocraft.config;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import mdc.voodoocraft.Reference;

@EventBusSubscriber
public class VoodooConfig
{
	public static Configuration config;

	public static void init(File configFile)
	{
		config = new Configuration(configFile); //Create configuration object from the given configuration file

		syncConfiguration();
		
		/**CUSTOM CATEGORY COMMENTS**/
		config.addCustomCategoryComment("Tweaks", "Tweak certian things");
	}

	private static void syncConfiguration()
	{
		/**CONFIG START**/



		/**CONFIG END**/
		if(config.hasChanged())
			config.save();
	}

	@SubscribeEvent
	public static void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if(event.getModID().equalsIgnoreCase(Reference.MODID))
			//Resync configs
			syncConfiguration();
	}

	public static List<IConfigElement> getEntries() {
        List<IConfigElement> entries = new ArrayList<IConfigElement>();
        Set<String> categories = config.getCategoryNames();
        Iterator<String> i = categories.iterator();
        while (i.hasNext()) {
            String categoryName = i.next();
            ConfigCategory category = config.getCategory(categoryName);
            entries.addAll(new ConfigElement(category).getChildElements());
        }
        return entries;
    }
}
