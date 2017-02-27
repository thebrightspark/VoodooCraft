package mdc.voodoocraft.handlers;

import mdc.voodoocraft.init.VCBlocks;
import mdc.voodoocraft.init.VCItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

@Mod.EventBusSubscriber
public class RegHandler
{
    @SubscribeEvent
    public static void initItems(RegistryEvent.Register<Item> event)
    {
        //Register all item
        VCItems.init();
        IForgeRegistry<Item> registry = event.getRegistry();
        for(Item item : VCItems.ITEMS.values())
            registry.register(item);

        //Register item blocks
        VCBlocks.init();
        for(Item item : VCBlocks.ITEM_BLOCKS.values())
            registry.register(item);
    }

    @SubscribeEvent
    public static void regBlocks(RegistryEvent.Register<Block> event)
    {
        //Register all blocks
        VCBlocks.init();
        IForgeRegistry<Block> registry = event.getRegistry();
        for(Block block : VCBlocks.BLOCKS.values())
            registry.register(block);
    }
}
