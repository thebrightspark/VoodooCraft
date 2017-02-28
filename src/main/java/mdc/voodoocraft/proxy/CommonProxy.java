package mdc.voodoocraft.proxy;

import mdc.voodoocraft.config.VoodooConfig;
import mdc.voodoocraft.init.VCEntities;
import mdc.voodoocraft.init.VCRecipes;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;

public class CommonProxy
{

    public void preInit(FMLPreInitializationEvent e)
    {
    	VoodooConfig.init(e.getSuggestedConfigurationFile());
        VCEntities.init();
    }

    public void init(FMLInitializationEvent e)
    {
        VCRecipes.init();
    }

    public void postInit(FMLPostInitializationEvent e){
    	
    }
    
    public void serverStarting(FMLServerStartingEvent e) {
    	//register commands
    }
    
    public void serverStopping(FMLServerStoppingEvent e) {
    	
    }
}
