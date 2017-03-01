package mdc.voodoocraft.init;

import mdc.voodoocraft.tile.TileDollPedestal;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class VCTileEntities {

	public static void init()
	{
		//Register Tile Entities here
		GameRegistry.registerTileEntity(TileDollPedestal.class, "TileDollPedestal");
		
	}
}
