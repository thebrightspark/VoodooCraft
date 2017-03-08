package mdc.voodoocraft.items;

import mdc.voodoocraft.util.NBTHelper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemShard extends VCItem {
    public ItemShard() {
        super("shard");
        this.setMaxStackSize(1);
        this.addPropertyOverride(new ResourceLocation("shardfull"), new IItemPropertyGetter()
        {
            @Override
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                    if (entityIn == null)
                    {
                        return 1.0F;
                    }else if(checkNBTInfo(stack)){
                        return 0.0F;
                    }
                    else{
                        return 1.0F;
                    }
            }
        });
        this.setMaxDamage(0);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        if(checkNBTInfo(stack)) {
                tooltip.add(I18n.format("shard.type.empty.name"));
        }else {
            tooltip.add(I18n.format("shard.type.name", NBTHelper.getOwnerName(stack)));
            if (GuiScreen.isShiftKeyDown()) {
                tooltip.add("UUID: " + NBTHelper.getOwnerUUID(stack));
            } else
                tooltip.add(TextFormatting.AQUA + I18n.format("press.for.info.name", "SHIFT")); //TODO: configurable key?
        }
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target,
                                            EnumHand hand) {
        World world = player.getEntityWorld();
        if (!world.isRemote) {
            if (checkNBTInfo(stack)) {
                if (target != null) {
                    NBTHelper.setOwnerTag(stack, target);
                    target.attackEntityFrom(DamageSource.causePlayerDamage(player), 0.5F);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkNBTInfo(ItemStack stack){
        if(NBTHelper.getOwnerName(stack).contains("INVALID"))
        {
            return true;
        }else
            return false;
    }
}
