package mdc.voodoocraft.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

import org.apache.logging.log4j.Logger;

import com.google.common.collect.Lists;

import mdc.voodoocraft.VoodooCraft;
import mdc.voodoocraft.blocks.VCBlock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;
import net.minecraftforge.fml.relauncher.Side;

public class RegistryUtils {
	
	private static final Logger log;
	
	static {
		log = VoodooCraft.log;
	}

    /**
     * Registers items or blocks. Creates ItemBlocks if necessary.
     * 
     * @param blocks
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T extends IForgeRegistryEntry<T>> void createRegistryEntries(RegistryEvent.Register<T> event, Class clazz, String modid, CreativeTabs tab) {
        
        int count = 0;
        for ( Field f : clazz.getDeclaredFields() ) {
            if ( IForgeRegistryEntry.class.isAssignableFrom(f.getType()) ) {
                try {
                    T entry = (T) f.get(null);
                    // event.getRegistry().register(entry); //FIXME why doesn't this work?
                    GameRegistry.register(entry);
                    count++;
                    if ( entry instanceof Item ) {
                        Item item = (Item) entry;
                        item.setCreativeTab(tab);
                        if ( FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT ) registerRender(item);
                    }
                    else if ( entry instanceof Block ) {
                        Block block = (Block) entry;
                        block.setCreativeTab(tab);
                        if ( block instanceof VCBlock ) {
                            final Item item = ((VCBlock) block).item();
                            GameRegistry.register(item);
                            if ( FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT ) registerRender(item);
                            count++;
                        }
                    }
                }catch (Exception ignore) {
                }
                if ( !Modifier.isFinal(f.getModifiers()) )
                    log.info(clazz.getName() + ":" + f.getName() + " has no final modifier! Pleaes change this!");
            }
        }
        log.info("successfully registered " + count + " Objects.");
    }

    private static void registerRender(Item item) {
        if ( item.getHasSubtypes() ) {
            List<ItemStack> items = Lists.newArrayList();
            item.getSubItems(item, null, items);
            for ( ItemStack stack : items ) {
                int meta = stack.getMetadata();
                ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName() + "_" + meta, "inventory"));
            }
        }
        else {
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
        }

    }
}
