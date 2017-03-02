package mdc.voodoocraft.tile.render;

import mdc.voodoocraft.tile.TileDollPedestal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class TileDollPedestalRender extends TileEntitySpecialRenderer<TileDollPedestal>{

	@Override
	public void renderTileEntityAt(TileDollPedestal te, double x, double y, double z, float partialTick, int destroyStage)
	{
		IItemHandler tileinv = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		ItemStack stack = tileinv.getStackInSlot(0);
		if(tileinv!=null&&stack!=null)
		{
			GlStateManager.pushMatrix();
			{
				GlStateManager.translate(x, y, z);
				GlStateManager.translate(0.5, 0.95, 0.5);
				float angle = (((float)te.getWorld().getTotalWorldTime() + partialTick) / 20.0F) * (180F / (float)Math.PI);
				GlStateManager.rotate(angle, 0, 1, 0);
				Minecraft.getMinecraft().getRenderItem().renderItem(stack, TransformType.GROUND);
			}
			GlStateManager.popMatrix();
		}
	}
}