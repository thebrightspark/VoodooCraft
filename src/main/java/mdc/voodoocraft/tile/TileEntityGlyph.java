package mdc.voodoocraft.tile;

import java.util.Random;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;

public class TileEntityGlyph extends TileEntity implements ITickable {

	private final Random random = new Random();
	
	@Override
	public void update() {
		if(this.hasWorld()) {
			World world = this.getWorld();
			if(world.getTotalWorldTime() % 10 == random.nextInt(20)) {
				for(int i = 0; i < 2; i++) {
					spawnParticle(world, EnumParticleTypes.FLAME);
				}
			}
			if(world.getTotalWorldTime() % 10 == 0) {
				for(int i = 0; i < 7; i++) {
					spawnParticle(world, EnumParticleTypes.SMOKE_NORMAL);
				}
			}
		}
	}
	
	public void spawnParticle(World world, EnumParticleTypes type) {
		if(world.isRemote) {
			world.spawnParticle(type, false, this.pos.getX() + random.nextDouble(), this.pos.getY() + 0.1D, this.pos.getZ() + random.nextDouble(), 0.001D, 0.002D, 0.001D);
		}
		else {
			world.spawnParticle(type, this.pos.getX() + random.nextDouble(), this.pos.getY() + 0.1D, this.pos.getZ() + random.nextDouble(), 0.001D, 0.002D, 0.001D);
		}
	}

}
