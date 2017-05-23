package mdc.voodoocraft.blocks;

import mdc.voodoocraft.init.VCItems;
import mdc.voodoocraft.tile.TileTotem;
import mdc.voodoocraft.util.EnumGlyphType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockTotem extends VCBlockContainer
{
    public BlockTotem()
    {
        super("totem", Material.ROCK);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileTotem();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if(heldItem != null && heldItem.getItem() == VCItems.CHALK_BASIC)
        {
            TileTotem te = (TileTotem) worldIn.getTileEntity(pos);
            if(te == null) return false;
            if(playerIn.isSneaking())
                //Clear glyph on side
                te.setSide(side, null);
            else
            {
                //Cycle to next glyph on side
                EnumGlyphType glyph = te.getSide(side);
                if(glyph == null)
                    te.setSide(side, EnumGlyphType.BASIC);
                else
                    te.setSide(side, glyph.next());
            }
            return true;
        }
        return false;
    }
}
