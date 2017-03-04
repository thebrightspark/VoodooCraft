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
	GIVE("give_glyph"),
	TAKE("take_glyph"),
	TIME("time_glyph"),
	SPEED("speed_glyph"),
	SOUL("soul_glyph"),
	POWER("power_glyph"),
	TOX("tox_glyph"),
	LINK("link_glyph");
	
	private String name; 
	private EnumGlyphType(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
	
	public static EnumGlyphType byName(String name) {
		for(EnumGlyphType type : EnumGlyphType.values()) {
			if(type.getName().equals(name)) return type;
		}
		throw new EnumConstantNotPresentException(EnumGlyphType.class, name);
	}

}
