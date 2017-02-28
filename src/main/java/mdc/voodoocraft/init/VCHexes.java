package mdc.voodoocraft.init;

import mdc.voodoocraft.hexes.Hex;

import java.util.HashMap;
import java.util.Map;

public class VCHexes
{
	@Deprecated //shouldn't we make a real registry?
    public static Map<String, Hex> HEXES = new HashMap<>();

    private static void regHex(Hex hex)
    {
        HEXES.put(hex.unlocName, hex);
    }

    private static void regHex(String name)
    {
        regHex(new Hex().setUnlocName(name));
    }

    private static void regHexes(String... names)
    {
        for(String name : names)
            regHex(name);
    }

    static
    {
        regHexes("regen",
                "feather",
                "healthBind",
                "greenFingers",
                "death",
                "fireAura",
                "suffocation",
                "teleport",
                "spiritWalk",
                "fertility",
                "waterBreathing",
                "protection",
                "nap",
                "insomnia",
                "safety",
                "danger",
                "freeze",
                "freeze",
                "heatProtect",
                "waterWalk",
                "witherfy",
                "zombify",
                "timeSprint",
                "lifeDrain",
                "fromAshes",
                "witherProtect",
                "chestDeposit",
                "loneWolf",
                "intoxicate");
    }
}
