package mdc.voodoocraft.tile.render;

import mdc.voodoocraft.tile.TileTotem;
import mdc.voodoocraft.util.EnumGlyphType;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec2f;
import org.lwjgl.opengl.GL11;

import java.awt.*;

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

            //TODO: Do Render
            //Using EnderIO as reference: https://github.com/SleepyTrousers/EnderIO/blob/1.10/src/main/java/crazypants/enderio/machine/capbank/render/IoDisplay.java
            //bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

            Tessellator tes = Tessellator.getInstance();
            VertexBuffer buf = tes.getBuffer();
            buf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);

            //drawSquare(buf, );

            tes.draw();

            GlStateManager.popMatrix();
        }
    }

    private static void drawSquare(Vec2f minPos, Vec2f maxPos, float z, Color colour)
    {
        GlStateManager.disableTexture2D();
        GlStateManager.color(colour.getRed(), colour.getGreen(), colour.getBlue(), colour.getAlpha());
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer tes = tessellator.getBuffer();
        tes.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
        tes.pos(minPos.x, minPos.y, z).endVertex();
        tes.pos(maxPos.x, minPos.y, z).endVertex();
        tes.pos(maxPos.x, maxPos.y, z).endVertex();
        tes.pos(minPos.x, maxPos.y, z).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
    }
}
