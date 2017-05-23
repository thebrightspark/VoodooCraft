package mdc.voodoocraft.proxy;

import mdc.voodoocraft.commands.*;
import mdc.voodoocraft.config.*;
import mdc.voodoocraft.init.VCAchievements;
import mdc.voodoocraft.init.*;
import net.minecraftforge.fml.common.event.*;

public class CommonProxy
{

    public void preInit(FMLPreInitializationEvent e)
    {
    	VoodooConfig.init(e.getSuggestedConfigurationFile());
        VCEntities.init();
        VCSoundHandler.init();
        VCBlocks.registerTileEntities();
    }

    public void init(FMLInitializationEvent e)
    {
        VCAchievements.registerAchievements();
        VCRecipes.init();
    }

    public void postInit(FMLPostInitializationEvent e){
    	
    }
    
    public void serverStarting(FMLServerStartingEvent e) {
    	e.registerServerCommand(new CommandVCHelp());
    }
    
    public void serverStopping(FMLServerStoppingEvent e) {
    	
    }
}
