package mdc.voodoocraft.init;

import mdc.voodoocraft.hexes.*;

public class VCHexes
{
	public static final HexEntry REGENERATION = new HexRegeneration();
	public static final HexEntry FEATHER = new HexFeatherFalling();
	public static final HexEntry HEALTH_BIND = new HexEntry("health_bind");
	public static final HexEntry GREEN_FINGERS = new HexEntry("green_fingers");
	public static final HexEntry DEATH = new HexEntry("death");
	public static final HexEntry FIRE_AURA = new HexEntry("fire_aura");
	public static final HexEntry SUFFOCATION = new HexEntry("suffocation");
	public static final HexEntry TELEPORT = new HexEntry("teleport");
	public static final HexEntry SPIRIT_WALK = new HexEntry("spirit_walk");
	public static final HexEntry FERTILITY = new HexEntry("fertiliy");
	public static final HexEntry WATER_BREATHING = new HexWaterBreathing();
	public static final HexEntry PROTECTION = new HexEntry("protection");
	public static final HexEntry NAP = new HexEntry("nap");
	public static final HexEntry INSOMNIA = new HexInsomnia();
	public static final HexEntry SAFETY = new HexEntry("safety");
	public static final HexEntry DANGER = new HexEntry("danger"); //what would this do??
	public static final HexEntry FREEZE = new HexEntry("freeze");
	public static final HexEntry FIRE_PROTECTION = new HexEntry("fire_protection");
	public static final HexEntry WATER_WALK = new HexWaterWalking();
	public static final HexEntry WITHER = new HexEntry("wither");
	public static final HexEntry ZOMBIE = new HexZombify();
	public static final HexEntry TIME_SPRINT = new HexEntry("time_sprint"); //HOW would we do this?
	public static final HexEntry LIFE_DRAIN = new HexEntry("life_drain"); //maybe make this slightly more powerful than regeneration, but have it only work for hostile mobs or players 
	public static final HexEntry FROM_ASHES = new HexEntry("from_ashes"); //what is this??
	public static final HexEntry WITHER_PROTECTION = new HexEntry("wither_protection");
	public static final HexEntry CHEST_DEPOSIT = new HexEntry("chest_deposit"); //???
	public static final HexEntry LONE_WOLF = new HexEntry("lone_wolf"); //???
	public static final HexEntry INTOXICATE = new HexEntry("intoxicate"); //???
	
	
}