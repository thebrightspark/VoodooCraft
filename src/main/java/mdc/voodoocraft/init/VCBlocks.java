package mdc.voodoocraft.init;

import mdc.voodoocraft.blocks.BlockGlyph;
import mdc.voodoocraft.blocks.BlockDollPedestal;
import mdc.voodoocraft.blocks.BlockShrine;
import mdc.voodoocraft.blocks.BlockTotem;
import mdc.voodoocraft.tile.TileDollPedestal;
import mdc.voodoocraft.tile.TileEntityGlyph;
import mdc.voodoocraft.tile.TileTotem;
import mdc.voodoocraft.tile.render.TileDollPedestalRender;
import mdc.voodoocraft.tile.render.TileTotemRender;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class VCBlocks
{

    public static final Block GLYPH = new BlockGlyph();
    public static final Block DOLL_PEDESTAL = new BlockDollPedestal();
    public static final Block SHRINE = new BlockShrine();
    public static final Block TOTEM = new BlockTotem();

    public static void registerTileEntities()
    {
        //Register Tile Entities
    	regTE(TileDollPedestal.class, DOLL_PEDESTAL);
    	regTE(TileEntityGlyph.class, GLYPH);
    	regTE(TileTotem.class, TOTEM);
    }
    
    public static void registerTileEntityRenders() {
    	//register TESRs
    	ClientRegistry.bindTileEntitySpecialRenderer(TileDollPedestal.class, new TileDollPedestalRender());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileTotem.class, new TileTotemRender());
    }
    
	private static void regTE(Class<? extends TileEntity> teClass, Block block)
    {
    	ResourceLocation loc = block.getRegistryName();
        GameRegistry.registerTileEntity(teClass, loc.getResourceDomain() + "_" + loc.getResourcePath());
    }
}
