package mdc.voodoocraft.proxy;

import mdc.voodoocraft.init.VCBlocks;
import mdc.voodoocraft.init.VCEntities;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;

public class ClientProxy extends CommonProxy
{

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        VCBlocks.registerTileEntityRenders();
    }

    @Override
    public void init(FMLInitializationEvent e)
    {
        super.init(e);
        VCEntities.initRenders();
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }
    
    @Override
    public void serverStarting(FMLServerStartingEvent e) {
    	super.serverStarting(e);
    }
    
    @Override
    public void serverStopping(FMLServerStoppingEvent e) {
    	super.serverStopping(e);
    }
}
