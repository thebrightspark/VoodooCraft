package mdc.voodoocraft.items;

import mdc.voodoocraft.init.VCItems;
import mdc.voodoocraft.util.NBTHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemTweezer extends VCItem {
    
	public ItemTweezer() {
        super("tweezer");
        this.setMaxDamage(5);
    }
	
	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target,
			EnumHand hand) {
		World world = player.getEntityWorld();
		 if (!world.isRemote) {
	            if (target instanceof EntityLivingBase) {
	                stack.damageItem(1, player);
	                ItemStack hair = new ItemStack(VCItems.HAIR, 1);
	                target.attackEntityFrom(DamageSource.causePlayerDamage(player), 0.5F);
	                NBTHelper.setOwnerTag(hair, target);
	                target.entityDropItem(hair, 0.2f);
	                return true;
	            }
	        }
		return false;
	}
	

}
