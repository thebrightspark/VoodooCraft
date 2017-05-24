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
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;

public class VCBlocks
{
    public static List<Block> BLOCKS = new ArrayList<>();
    public static List<ItemBlock> ITEM_BLOCKS = new ArrayList<>();

    public static final Block GLYPH, DOLL_PEDESTAL, SHRINE, TOTEM;

    static
    {
        //Add the blocks - these will get registered later in RegHandler and models registered in ModelHandler
        addBlock(GLYPH = new BlockGlyph());
        addBlock(DOLL_PEDESTAL = new BlockDollPedestal());
        addBlock(SHRINE = new BlockShrine());
        addBlock(TOTEM = new BlockTotem());
    }

    public static void registerTileEntities()
    {
        //Register Tile Entities
        regTE(TileDollPedestal.class, DOLL_PEDESTAL);
        regTE(TileEntityGlyph.class, GLYPH);
        regTE(TileTotem.class, TOTEM);
    }

    public static void registerTileEntityRenders()
    {
        //Register TESRs
        regTESR(TileDollPedestal.class, new TileDollPedestalRender());
        regTESR(TileTotem.class, new TileTotemRender());
    }

    private static void addBlock(Block block)
    {
        addBlock(block, (ItemBlock) new ItemBlock(block).setRegistryName(block.getRegistryName()));
    }

    private static void addBlock(Block block, ItemBlock itemBlock)
    {
        BLOCKS.add(block);
        ITEM_BLOCKS.add(itemBlock);
    }

    private static void regTE(Class<? extends TileEntity> teClass, Block block)
    {
        GameRegistry.registerTileEntity(teClass, block.getRegistryName().getResourcePath());
    }

    private static <T extends TileEntity> void regTESR(Class<? extends T> teClass, TileEntitySpecialRenderer<? super T> tesr)
    {
        ClientRegistry.bindTileEntitySpecialRenderer(teClass, tesr);
    }
}
