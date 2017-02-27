package mdc.voodoocraft.init;

import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;

public class VCItems
{
    public static Map<String, Item> ITEMS = new HashMap<String, Item>();

    public static Item Chalk;
    
    private static void regItem(Item item)
    {
        ITEMS.put(item.getRegistryName().getResourcePath().toLowerCase(), item);
    }

    public static void init()
    {
        //Make sure we only register once
        if(!ITEMS.isEmpty()) return;

        //Register items
        regItem(Chalk);
    }
}
