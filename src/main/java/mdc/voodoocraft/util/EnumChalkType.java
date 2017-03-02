package mdc.voodoocraft.util;

import net.minecraft.util.IStringSerializable;

public enum EnumChalkType implements IStringSerializable {
	BASIC("basic_chalk");
	
	private String name; 
	private EnumChalkType(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
	
	public static EnumChalkType byName(String name) {
		for(EnumChalkType type : EnumChalkType.values()) {
			if(type.getName().equals(name)) return type;
		}
		throw new EnumConstantNotPresentException(EnumChalkType.class, name);
	}

}
