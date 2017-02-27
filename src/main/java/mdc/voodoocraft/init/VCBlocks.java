package mdc.voodoocraft.init;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.HashMap;
import java.util.Map;

import mdc.voodoocraft.blocks.BlockChalk;
import mdc.voodoocraft.blocks.BlockDollPedestal;

public class VCBlocks
{
    public static Map<String, Block> BLOCKS = new HashMap<String, Block>();
    public static Map<String, ItemBlock> ITEM_BLOCKS = new HashMap<String, ItemBlock>();

    public static Block chalkbasicsymbol;
    public static Block dollpedestal;
    
    private static void regBlock(Block block)
    {
        BLOCKS.put(block.getRegistryName().getResourcePath().toLowerCase(), block);
        ITEM_BLOCKS.put(block.getRegistryName().getResourcePath().toLowerCase(), (ItemBlock) new ItemBlock(block).setRegistryName(block.getRegistryName()));
    }

    private static void regTE(Class<? extends TileEntity> teClass, Block block)
    {
        GameRegistry.registerTileEntity(teClass, block.getRegistryName().getResourcePath());
    }

    public static void init()
    {
        //Make sure we only register once
        if(! BLOCKS.isEmpty()) return;

        //Register Blocks
        regBlock(chalkbasicsymbol = new BlockChalk("chalkbasicsymbol"));
        regBlock(dollpedestal = new BlockDollPedestal());
    }

    public static void initTileEntities()
    {
        //Register Tile Entities

    }
}
