package mdc.voodoocraft.init;

import mdc.voodoocraft.items.ItemChalk;
import mdc.voodoocraft.items.ItemDoll;
import mdc.voodoocraft.util.EnumChalkType;
import net.minecraft.item.Item;

public class VCItems
{
    public static final Item DOLL = new ItemDoll();
    public static final Item CHALK_BASIC = new ItemChalk("chalkbasic", EnumChalkType.BASIC);
    
}
