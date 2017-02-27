package mdc.voodoocraft.proxy;

import mdc.voodoocraft.init.VCEntities;
import mdc.voodoocraft.init.VCRecipes;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy
{

    public void preInit(FMLPreInitializationEvent e)
    {
        VCEntities.init();
    }

    public void init(FMLInitializationEvent e)
    {
        VCRecipes.init();
    }

    public void postInit(FMLPostInitializationEvent e){ }
}
