package mdc.voodoocraft.init;

import mdc.voodoocraft.Reference;
import mdc.voodoocraft.VoodooCraft;
import mdc.voodoocraft.init.VCItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

import java.util.ArrayList;
import java.util.List;

public class VCAchievements {

    private static List<Achievement> achievementList = new ArrayList<Achievement>();

    public static Achievement achievementHexFirstTime = createAchievement("firstHexCreated", 0, 0, VCItems.DOLL, null);
    public static Achievement achievementHexFirstUse = createAchievement("firstHexUsed", 0, 1, VCItems.DOLL, achievementHexFirstTime);

    public static void registerAchievements(){
        Achievement[] achievementArray = new Achievement[achievementList.size()];
        for(Achievement a : achievementList){
            a.registerStat();
            achievementArray[achievementList.indexOf(a)] = a;
        }
        AchievementPage.registerAchievementPage(new AchievementPage(Reference.NAME, achievementArray));
    }

    /**
     * There has to be a better way to do this. For now, this is what we will use to incorporate D.R.Y
     * @param name The name of the achievement
     * @param column What column on the page this achievement is
     * @param row What row on the page this achievement is
     * @param o The icon of the achievement (either item, itemstack, or block)
     * @param parent What achievement is required before this achievement can be activated
     * @return The achievement
     */
    private static Achievement createAchievement(String name, int column, int row, Object o, Achievement parent){
        Achievement a;
        if(o instanceof Item){
            Item item = (Item)o;
            a = new Achievement("achievement."+name, name, column, row, item, parent);
            achievementList.add(a);
            return a;
        }else if(o instanceof Block){
            Block block = (Block)o;
            a = new Achievement("achievement."+name, name, column, row, block, parent);
            achievementList.add(a);
            return a;
        }else if(o instanceof ItemStack){
            ItemStack stack = (ItemStack)o;
            a = new Achievement("achievement."+name, name, column, row, stack, parent);
            achievementList.add(a);
            return a;
        }else{
            VoodooCraft.log.warn(o + " is not item, itemstack, nor block.");
            return null;
        }
    }
}
