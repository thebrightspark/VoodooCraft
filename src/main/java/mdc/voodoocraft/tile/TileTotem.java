package mdc.voodoocraft.tile;

import mdc.voodoocraft.util.EnumGlyphType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class TileTotem extends TileEntity
{
    private Map<EnumFacing, EnumGlyphType> SIDES = new HashMap<>();

    public TileTotem()
    {
        //Fill the SIDES with glyphs as null
        for(EnumFacing side : EnumFacing.VALUES)
            SIDES.put(side, null);
    }

    public void setSide(EnumFacing side, @Nullable EnumGlyphType glyph)
    {
        SIDES.put(side, glyph);
    }

    public EnumGlyphType getSide(EnumFacing side)
    {
        return SIDES.get(side);
    }
}
