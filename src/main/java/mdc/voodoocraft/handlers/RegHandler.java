package mdc.voodoocraft.handlers;

import mdc.voodoocraft.Reference;
import mdc.voodoocraft.init.VCBlocks;
import mdc.voodoocraft.init.VCItems;
import mdc.voodoocraft.init.VCMisc.VCTabs;
import mdc.voodoocraft.util.RegistryUtils;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * put all registries here
 * @author UpcraftLP
 *
 */
@EventBusSubscriber
public class RegHandler {

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        RegistryUtils.createRegistryEntries(event, VCBlocks.class, Reference.MODID, VCTabs.VOODOO_TAB);
    }
    
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        RegistryUtils.createRegistryEntries(event, VCItems.class, Reference.MODID, VCTabs.VOODOO_TAB);
    }
}
