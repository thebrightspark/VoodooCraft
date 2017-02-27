package mdc.voodoocraft.init;

import mdc.voodoocraft.hexes.Hex;

import java.util.HashMap;
import java.util.Map;

public class VCHexes
{
    public static Map<String, Hex> HEXES = new HashMap<>();

    private static void regHex(Hex hex)
    {
        HEXES.put(hex.unlocName, hex);
    }

    static
    {
        regHex(new Hex().setUnlocName("regen"));
    }
}
