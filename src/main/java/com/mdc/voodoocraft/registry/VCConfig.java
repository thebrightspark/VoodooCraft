package com.mdc.voodoocraft.registry;

import net.minecraftforge.common.config.Configuration;

public class VCConfig {

	public static void init(Configuration config)
	{
		config.load();
		config.addCustomCategoryComment("Tweaks", "Tweak certian things");
		config.addCustomCategoryComment("Items", "Set any values to false to disable the item");
	}
}
