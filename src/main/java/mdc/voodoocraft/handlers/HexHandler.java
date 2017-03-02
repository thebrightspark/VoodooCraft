package mdc.voodoocraft.handlers;

import mdc.voodoocraft.hexes.HexHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.FoodStats;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.UUID;

@Mod.EventBusSubscriber
public class HexHandler
{
    //Used to store the cooldown start times for the protection hex for every player that uses it
    private static HashMap<UUID, Long> protectionCooldowns = new HashMap<>();

    private static ItemStack getPlayerHex(Entity entity, String hexName)
    {
        return entity == null || !(entity instanceof EntityPlayer) ? null : HexHelper.getPlayerHex((EntityPlayer) entity, hexName);
    }

    /**
     * Feather Hex
     */
    @SubscribeEvent
    public static void fallEvent(LivingFallEvent event)
    {
        //Feather - Removes fall damage
        ItemStack stack = getPlayerHex(event.getEntityLiving(), "feather");
        if(stack != null)
        {
            event.setDistance(0);
            event.setDamageMultiplier(0);
            //Damage the doll
            stack.damageItem(1, event.getEntityLiving());
        }
    }

    /**
     * Fire Aura Hex
     * Water Breathing Hex
     */
    @SubscribeEvent
    public static void attackEvent(LivingAttackEvent event)
    {
        EntityLivingBase entity = event.getEntityLiving();
        ItemStack stack = null;

        //Fire Aura - Sets attacking entity on fire
        if(event.getSource().getSourceOfDamage() != null && (stack = getPlayerHex(entity, "fireAura")) != null)
            event.getSource().getSourceOfDamage().setFire(2);

        //Water Breathing - Uses player's hunger if possible when they're drowning
        if(event.getSource().equals(DamageSource.drown) && (stack = getPlayerHex(entity, "waterBreathing")) != null)
        {
            FoodStats food = ((EntityPlayer) entity).getFoodStats();
            if(food.getFoodLevel() > 0)
            {
                food.addStats(-1, 0);
                event.setCanceled(true);
            }
        }

        //Damage the doll
        if(stack != null)
            stack.damageItem(1, entity);
    }

    /**
     * Protection Hex
     */
    @SubscribeEvent
    public static void hurtEvent(LivingHurtEvent event)
    {
        EntityLivingBase entity = event.getEntityLiving();
        ItemStack stack;

        //Protection - Absorbs some damage, with a 10s cooldown
        if(event.getSource().getSourceOfDamage() != null && (stack = getPlayerHex(entity, "protection")) != null)
        {
            UUID uuid = entity.getUniqueID();
            Long cooldownStart = protectionCooldowns.get(uuid);
            long worldTime = entity.world.getTotalWorldTime();

            //Check if hex is ready to protect
            if(cooldownStart == null || (worldTime - cooldownStart) >= 200) //200 ticks == 10 seconds
            {
                //Protect from up to 5 hearts of damage
                float protection = 10f;
                float damage = event.getAmount();
                event.setAmount(damage <= protection ? 0 : damage - protection);

                //Sets the new cooldown start
                protectionCooldowns.put(uuid, worldTime);

                //Damage the doll
                stack.damageItem(1, entity);
            }
        }
    }
}
