package mdc.voodoocraft;

import mdc.voodoocraft.registry.Refs;
import mdc.voodoocraft.proxy.CommonProxy;
import mdc.voodoocraft.registry.VCConfig;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Logger;

@Mod(modid = Refs.MODID, name = Refs.NAME, version = Refs.VERSION, acceptedMinecraftVersions = Refs.AVERSION)
public class VoodooCraft {

    @Mod.Instance(Refs.MODID)
    public static VoodooCraft instance;

    @SidedProxy(clientSide = Refs.CPROXY, serverSide = Refs.SPROXY)
    public static CommonProxy proxy;

    public static final CreativeTabs CREATIVE_TAB = new CreativeTabs(Refs.MODID)
    {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
            return Items.SKULL;
        }

        @Override
        public boolean hasSearchBar() {
            return true;
        }
    }.setBackgroundImageName("item_search.png");

    public static Logger log = FMLLog.getLogger();
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e){
        log.info("voodoocraft Pre-Init");
        Configuration config = new Configuration(e.getSuggestedConfigurationFile());
		VCConfig.init(config);
		
        proxy.preInit(e);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e){
        log.info("voodoocraft Init");
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e){
        log.info("voodoocraft Post-Init");
        proxy.postInit(e);
    }
}
