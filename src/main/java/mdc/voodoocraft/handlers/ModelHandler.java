package mdc.voodoocraft.handlers;

import mdc.voodoocraft.init.VCBlocks;
import mdc.voodoocraft.init.VCItems;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ModelHandler
{
    @SubscribeEvent
    public static void regModels(ModelRegistryEvent event)
    {
        //Register all item models
        for(Item item : VCItems.ITEMS.values())
            regModel(item);

        //Register block models
        for(Block block : VCBlocks.BLOCKS.values())
            regModel(Item.getItemFromBlock(block));
    }

    private static void regModel(Item item)
    {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
