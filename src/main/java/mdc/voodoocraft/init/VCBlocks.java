package mdc.voodoocraft.init;

import mdc.voodoocraft.blocks.BlockChalk;
import mdc.voodoocraft.blocks.BlockDollPedestal;
import mdc.voodoocraft.blocks.BlockShrine;
import mdc.voodoocraft.tile.TileDollPedestal;
import mdc.voodoocraft.tile.render.TileDollPedestalRender;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class VCBlocks
{

    public static Block CHALK_BASIC_SYMBOL = new BlockChalk("chalkbasicsymbol");
    public static final Block DOLL_PEDESTAL = new BlockDollPedestal();
    public static final Block SHRINE = new BlockShrine();

    public static void registerTileEntities()
    {
        //Register Tile Entities
    	regTE(TileDollPedestal.class, DOLL_PEDESTAL);
    }
    
    public static void registerTileEntityRenders() {
    	//register TESRs
    	ClientRegistry.bindTileEntitySpecialRenderer(TileDollPedestal.class, new TileDollPedestalRender());
    }
    
    @SuppressWarnings("unused")
	private static void regTE(Class<? extends TileEntity> teClass, Block block)
    {
    	ResourceLocation loc = block.getRegistryName();
        GameRegistry.registerTileEntity(teClass, loc.getResourceDomain() + "_" + loc.getResourcePath());
    }
}

import mdc.voodoocraft.blocks.BlockChalk;
import mdc.voodoocraft.blocks.BlockDollPedestal;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class VCBlocks
{

    public static Block CHALK_BASIC_SYMBOL = new BlockChalk("chalkbasicsymbol");
    public static final Block DOLL_PEDESTAL = new BlockDollPedestal();

    public static void registerTileEntities()
    {
        //Register Tile Entities
    }
    
    public static void registerTileEntityRenders() {
    	//register TESRs
    }
    
    @SuppressWarnings("unused")
	private static void regTE(Class<? extends TileEntity> teClass, Block block)
    {
    	ResourceLocation loc = block.getRegistryName();
        GameRegistry.registerTileEntity(teClass, loc.getResourceDomain() + "_" + loc.getResourcePath());
    }
}
