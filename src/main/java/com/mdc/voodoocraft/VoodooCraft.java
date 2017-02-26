package com.mdc.voodoocraft;

import com.mdc.voodoocraft.registry.Refs;
import com.mdc.voodoocraft.proxy.Common;
import com.mdc.voodoocraft.registry.VCConfig;
import com.mdc.voodoocraft.registry.VoodooTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Refs.MODID, name = Refs.NAME, version = Refs.VERSION, acceptedMinecraftVersions = Refs.AVERSION)
public class VoodooCraft {

    @Mod.Instance
    public static VoodooCraft instance;

    @SidedProxy(clientSide = Refs.CPROXY, serverSide = Refs.SPROXY)
    public static Common proxy;

    public static VoodooTab creativeTab;

    public static Logger log = FMLLog.getLogger();
    

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e){
        log.info("voodoocraft Pre-Init");
        Configuration config = new Configuration(e.getSuggestedConfigurationFile());
		VCConfig.init(config);
		
        proxy.preInit(e);

        creativeTab = new VoodooTab(CreativeTabs.getNextID(), "voodoo_tab");


    }


    @Mod.EventHandler
    public void init(FMLInitializationEvent e){
        log.info("voodoocraft Init");
        proxy.init(e);

        MinecraftForge.EVENT_BUS.register(instance);
    }



    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e){
        log.info("voodoocraft Post-Init");
        proxy.postInit(e);

    }
}
