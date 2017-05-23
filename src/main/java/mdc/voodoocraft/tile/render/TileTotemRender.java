package mdc.voodoocraft.tile.render;

import mdc.voodoocraft.tile.TileTotem;
import mdc.voodoocraft.util.EnumGlyphType;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;

public class TileTotemRender extends TileEntitySpecialRenderer<TileTotem>
{
    public void renderTileEntityAt(TileTotem te, double x, double y, double z, float partialTicks, int destroyStage)
    {
        for(EnumFacing side : EnumFacing.VALUES)
        {
            EnumGlyphType glyph = te.getSide(side);
            if(glyph == null) continue;

            //Render glyph on side
            GlStateManager.pushMatrix();
            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.disableTexture2D();

            //TODO: Do Render
            //Using EnderIO as reference: https://github.com/SleepyTrousers/EnderIO/blob/1.10/src/main/java/crazypants/enderio/machine/capbank/render/IoDisplay.java

            GlStateManager.enableTexture2D();
            GlStateManager.popMatrix();
        }
    }
}
