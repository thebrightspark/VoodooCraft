package mdc.voodoocraft.handlers;

import mdc.voodoocraft.init.VCItems;
import mdc.voodoocraft.util.NBTHelper;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSaddle;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.UUID;

@Mod.EventBusSubscriber
public class VCEventHandler {

    @SubscribeEvent
    public void fluidWalking(TickEvent.PlayerTickEvent e) {
        BlockPos pos = e.player.getPosition();
        World world = e.player.world;
        Entity player = e.player;

        if (world.getBlockState(pos.down()).getMaterial() == Material.WATER) {
            player.motionY = 0.1D;
        } else if (world.getBlockState(pos.down()).getMaterial() == Material.LAVA) {
            player.motionY = 0.1D;
        }
    }

    @SubscribeEvent
    public void rightClickEntity(PlayerInteractEvent.EntityInteract e) {
        if (e.getHand() == EnumHand.MAIN_HAND && !e.getWorld().isRemote && e.getEntityPlayer().getHeldItemMainhand() != null) {

            EntityPlayer player = e.getEntityPlayer();
            Entity target = e.getTarget();
            World world = e.getWorld();
            Item heldItem = player.getHeldItemMainhand().getItem();


            if (target instanceof EntityLivingBase && heldItem == VCItems.TWEEZER) {
                EntityLivingBase targeted = (EntityLivingBase)target;
                tweezerHair(player, targeted);
                player.getHeldItemMainhand().damageItem(1, player);
            }
        }
    }

    public static void tweezerHair(EntityPlayer player, EntityLivingBase livingtarget)
    {
        ItemStack hair = new ItemStack(VCItems.HAIR, 1);
        String name = livingtarget.getClass().getSimpleName();
        UUID uuid = livingtarget.getPersistentID();

        //if(player.getHeldItemMainhand().getItem()==VCItems.TWEEZER){
            livingtarget.attackEntityFrom(DamageSource.causePlayerDamage(player), 0.5F);
            NBTHelper.getTagCompound(hair).setUniqueId("entityUUID", uuid);
            NBTHelper.getTagCompound(hair).setString("entityName", name);
            //player.getHeldItemMainhand().damageItem(1, player);
        //}

        EntityItem entity = new EntityItem(player.getEntityWorld(), livingtarget.getPosition().getX(), livingtarget.getPosition().getY(), livingtarget.getPosition().getZ(), hair);
        player.getEntityWorld().spawnEntity(entity);
    }
}