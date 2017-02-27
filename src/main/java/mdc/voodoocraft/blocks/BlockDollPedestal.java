package mdc.voodoocraft.blocks;

import net.minecraft.util.math.AxisAlignedBB;

public class BlockDollPedestal extends VCModelBlock{

	private final static AxisAlignedBB hitbox = new AxisAlignedBB(0.1875, 0.0D, 0.1875D, 0.8125D, 0.875D, 0.8125D);
	
	public BlockDollPedestal() {
		super("dollpedestal", hitbox);
	}

	
}
