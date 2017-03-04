package mdc.voodoocraft.init;

import mdc.voodoocraft.blocks.BlockGlyph;
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

    public static final Block CHALK_BASIC_SYMBOL = new BlockGlyph("chalkbasicsymbol");
    public static final Block GLYPH_AIR = new BlockGlyph("glyphair");
    public static final Block GLYPH_FIRE = new BlockGlyph("glyphfire");
    public static final Block GLYPH_EARTH = new BlockGlyph("glyphearth");
    public static final Block GLYPH_WATER = new BlockGlyph("glyphwater");
    public static final Block GLYPH_LIFE = new BlockGlyph("glyphlife");
    public static final Block GLYPH_DEATH = new BlockGlyph("glyphdeath");
    public static final Block GLYPH_LIGHT = new BlockGlyph("glyphlight");
    public static final Block GLYPH_DARK = new BlockGlyph("glyphdark");
    public static final Block GLYPH_GIVE = new BlockGlyph("glyphgive");
    public static final Block GLYPH_TAKE = new BlockGlyph("glyphtake");
    public static final Block GLYPH_TIME = new BlockGlyph("glyphtime");
    public static final Block GLYPH_SPEED = new BlockGlyph("glyphspeed");
    public static final Block GLYPH_SOUL = new BlockGlyph("glyphsoul");
    public static final Block GLYPH_POWER = new BlockGlyph("glyphpower");
    public static final Block GLYPH_TOX = new BlockGlyph("glyphtox");
    public static final Block GLYPH_LINK = new BlockGlyph("glyphlink");
    
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
    
	private static void regTE(Class<? extends TileEntity> teClass, Block block)
    {
    	ResourceLocation loc = block.getRegistryName();
        GameRegistry.registerTileEntity(teClass, loc.getResourceDomain() + "_" + loc.getResourcePath());
    }
}
