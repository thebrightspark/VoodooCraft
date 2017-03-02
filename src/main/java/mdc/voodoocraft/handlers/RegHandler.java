package mdc.voodoocraft.handlers;

import java.util.Map;

import com.google.common.collect.BiMap;

import mdc.voodoocraft.Reference;
import mdc.voodoocraft.hexes.HexEntry;
import mdc.voodoocraft.init.VCBlocks;
import mdc.voodoocraft.init.VCHexes;
import mdc.voodoocraft.init.VCItems;
import mdc.voodoocraft.init.VCMisc.VCTabs;
import mdc.voodoocraft.util.RegistryUtils;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.FMLControlledNamespacedRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistry;
import net.minecraftforge.fml.common.registry.RegistryBuilder;

/**
 * put all registries here
 * @author UpcraftLP
 *
 */
@EventBusSubscriber
public class RegHandler {

	public static final ResourceLocation HEX_REGISTRY_LOCATION = new ResourceLocation(Reference.MODID, "hexes");
	
	private static FMLControlledNamespacedRegistry<HexEntry> hexRegistry;
	
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        RegistryUtils.createRegistryEntries(event, VCBlocks.class, Reference.MODID, VCTabs.VOODOO_TAB);
    }
    
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        RegistryUtils.createRegistryEntries(event, VCItems.class, Reference.MODID, VCTabs.VOODOO_TAB);
    }
    
    @SubscribeEvent
    public static void registerHexes(RegistryEvent.Register<HexEntry> event) {
    	RegistryUtils.createRegistryEntries(event, VCHexes.class, Reference.MODID, null);
    }
    
    
    
    
    
    
    
    
    
    @SubscribeEvent
    public static void createRegistries(RegistryEvent.NewRegistry event) {
    	hexRegistry = (FMLControlledNamespacedRegistry<HexEntry>) new RegistryBuilder<HexEntry>()
    			.setName(HEX_REGISTRY_LOCATION)
    			.setType(HexEntry.class)
    			.setIDRange(0, Integer.MAX_VALUE - 1)
    			.addCallback(HexCallbacks.INSTANCE)
    			.create();
    }
    
    
    
    
    
    
    
    
    public static FMLControlledNamespacedRegistry<HexEntry> getHexRegistry() {
    	return hexRegistry;
    }
    
    public static class HexCallbacks implements IForgeRegistry.AddCallback<HexEntry>, IForgeRegistry.ClearCallback<HexEntry>, IForgeRegistry.CreateCallback<HexEntry>, IForgeRegistry.SubstitutionCallback<HexEntry> {

    	static final HexCallbacks INSTANCE = new HexCallbacks();
    	
		@Override
		public void onSubstituteActivated(Map<ResourceLocation, ?> slaveset, HexEntry original, HexEntry replacement,
				ResourceLocation name) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onCreate(Map<ResourceLocation, ?> slaveset,
				BiMap<ResourceLocation, ? extends IForgeRegistry<?>> registries) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onClear(IForgeRegistry<HexEntry> is, Map<ResourceLocation, ?> slaveset) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onAdd(HexEntry obj, int id, Map<ResourceLocation, ?> slaveset) {
			// TODO Auto-generated method stub
			
		}
    	
    	
    }
}
