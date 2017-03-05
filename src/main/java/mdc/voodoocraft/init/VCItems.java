package mdc.voodoocraft.init;

import mdc.voodoocraft.items.ItemChalk;
import mdc.voodoocraft.items.ItemDoll;
import mdc.voodoocraft.items.ItemHair;
import mdc.voodoocraft.items.ItemTweezer;
import mdc.voodoocraft.util.EnumGlyphType;
import net.minecraft.item.Item;

import java.util.UUID;

public class VCItems
{
    public static final Item DOLL = new ItemDoll();
    public static final Item CHALK_BASIC = new ItemChalk("chalkbasic");
    public static final Item HAIR = new ItemHair("hair");
    public static final Item TWEEZER = new ItemTweezer("tweezer", 5);
}