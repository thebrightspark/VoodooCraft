package mdc.voodoocraft.util;

import java.util.UUID;

import net.minecraft.util.math.BlockPos;

public class DollTrackerObj {

	private UUID entityUUID;
	private int Dim;
	private BlockPos Pos;
	
	public DollTrackerObj(UUID id, int dimension, BlockPos pos)
	{
		entityUUID = id;
		Dim = dimension;
		Pos = pos;
	}
	public UUID getID()
	{
		return this.entityUUID;
	}
	public int getDim()
	{
		return this.Dim;
	}
	public BlockPos getPos()
	{
		return this.Pos;
	}
}
