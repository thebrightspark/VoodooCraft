package mdc.voodoocraft.util;

import net.minecraft.util.IStringSerializable;

public enum EnumGlyphType implements IStringSerializable {
	BASIC("basic_glyph"),
	AIR("air_glyph"),
	EARTH("earth_glyph"),
	FIRE("fire_glyph"),
	WATER("water_glyph"),
	LIFE("life_glyph"),
	DEATH("death_glyph"),
	LIGHT("light_glyph"),
	DARK("dark_glyph"),
	TIME("time_glyph"),
	SPEED("speed_glyph"),
	POWER("power_glyph"),
	LINK("link_glyph");
	
	private String name; 
	private EnumGlyphType(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
	
	/**
	 * Deprecated. use {@link Enum#ordinal()} instead!
	 */
	@Deprecated
	public int getIndex() {
		return this.ordinal();
	}
	
	public static EnumGlyphType byName(String name) {
		for(EnumGlyphType type : EnumGlyphType.values()) {
			if(type.getName().equals(name)) return type;
		}
		throw new EnumConstantNotPresentException(EnumGlyphType.class, name);
	}
	
	public static EnumGlyphType byIndex(int index) {
		return EnumGlyphType.values()[index];
	}
	
	public EnumGlyphType next() {
		int index = this.ordinal() + 1;
		if(index >= EnumGlyphType.values().length) index = 0;
		return EnumGlyphType.byIndex(index);
	}

}
