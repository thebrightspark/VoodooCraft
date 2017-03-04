package mdc.voodoocraft.util;

import net.minecraft.util.IStringSerializable;

public enum EnumGlyphType implements IStringSerializable {
	BASIC("basic_glyph", 0),
	AIR("air_glyph", 1),
	EARTH("earth_glyph", 2),
	FIRE("fire_glyph", 3),
	WATER("water_glyph", 4),
	LIFE("life_glyph", 5),
	DEATH("death_glyph", 6),
	LIGHT("light_glyph", 7),
	DARK("dark_glyph", 8),
	GIVE("give_glyph", 9),
	TAKE("take_glyph", 10),
	TIME("time_glyph", 11),
	SPEED("speed_glyph", 12),
	SOUL("soul_glyph", 13),
	POWER("power_glyph", 14),
	TOX("tox_glyph", 15),
	LINK("link_glyph", 16);
	
	private String name; 
	private int index;
	private EnumGlyphType(String name, int index) {
		this.name = name;
		this.index = index;
	}

	@Override
	public String getName() {
		return name;
	}
	
	public int getIndex() {
		return index;
	}
	
	public static EnumGlyphType byName(String name) {
		for(EnumGlyphType type : EnumGlyphType.values()) {
			if(type.getName().equals(name)) return type;
		}
		throw new EnumConstantNotPresentException(EnumGlyphType.class, name);
	}
	
	public static EnumGlyphType byIndex(int index) {
		for(EnumGlyphType type : EnumGlyphType.values())
		{
			if(type.getIndex()==index) return type;
		}
		throw new EnumConstantNotPresentException(EnumGlyphType.class, "index");
	}
	
	public static EnumGlyphType getNext(EnumGlyphType glyph){
		if(glyph.getIndex()==(EnumGlyphType.values().length-1)) return BASIC;
		return byIndex(glyph.getIndex()+1);
	}

}
