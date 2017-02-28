package mdc.voodoocraft.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class VCMisc {

	public static void init() {
		
	}
	
	public static class VCTabs {
		private static final String NAME_SUFFIX = ".name"; //unifying the .lang file
		
		public static final CreativeTabs VOODOO_TAB = new CreativeTabs("tabVoodoo" + NAME_SUFFIX)
	    {
	        @Override
	        @SideOnly(Side.CLIENT)
	        public Item getTabIconItem()
	        {
	            return Items.SKULL;
	        }

	        @Override
	        public boolean hasSearchBar() {
	            return true;
	        }
	    }.setBackgroundImageName("item_search.png");
	}
}
