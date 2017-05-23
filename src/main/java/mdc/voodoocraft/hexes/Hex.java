package mdc.voodoocraft.hexes;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import mdc.voodoocraft.init.VCAchievements;
import mdc.voodoocraft.items.ItemDoll;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Hex {
	
	private static final String KEY_COMPOUND_TAG = "compoundTag";
	private static final String KEY_COST = "cost";
	private static final String KEY_NAME = "hex_entry";
	private static final String KEY_STRENGTH = "strength";
	private static final String KEY_DISPLAY_NAME = "display_name";

	private HexEntry entry;
	private NBTTagCompound hexNBT;
	
	//internal use only!
	private Hex() {
		this.hexNBT = new NBTTagCompound();
		this.hexNBT.setInteger(KEY_STRENGTH, 0);
	}
	
	public Hex(String resourcelocation, int strength) {
		this(HexEntry.byName(resourcelocation), strength);
	}
	
	public Hex(String resourcelocation) {
		this(HexEntry.byName(resourcelocation));
	}
	
	public Hex(HexEntry entry) {
		this(entry, 0);
	}
	
	public Hex(HexEntry entry, int strength) {
		this();
		this.entry = entry;
		this.hexNBT.setInteger(KEY_STRENGTH, strength);
	}
	
	public Hex(NBTTagCompound compoundTag) {
		this();
		if(compoundTag.hasKey(KEY_NAME, NBT.TAG_STRING)) this.entry = HexEntry.byName(compoundTag.getString(KEY_NAME));
		if(compoundTag.hasKey(KEY_COMPOUND_TAG, NBT.TAG_COMPOUND)) this.setTagCompound((NBTTagCompound) compoundTag.getCompoundTag(KEY_COMPOUND_TAG));
	}
	
	@Nullable
	public HexEntry getEntry() {
		return this.entry;
	}
	
	@Nonnull
	public NBTTagCompound getTagCompound() {
		return this.hexNBT;
	}
	
	public boolean hasTagCompound() {
		return !this.hexNBT.hasNoTags();
	}
	
	public NBTTagCompound writeToNBT() {
		NBTTagCompound nbt = new NBTTagCompound();
		if(this.entry != null) {
			nbt.setString(KEY_NAME, this.entry.getRegistryName().toString());
			nbt.setTag(KEY_COMPOUND_TAG, this.getTagCompound());
		}
		return nbt;
	}
	
	public void setTagCompound(NBTTagCompound nbt) {
		this.hexNBT = nbt;
	}

	@Nullable
	public String getDescription() {
		if(this.entry != null) return this.entry.getDescription();
		return null;
	}
	
	/**
	 * @return the hex's strength
	 */
	public int getStrength() {
		return this.hexNBT.getInteger(KEY_STRENGTH);
	}
	
	/**
     * Get this hex's additional cost multiplier, or 0 if no cost is defined.
     */
    public int getCost()
    {
    	if(this.hasTagCompound() && this.getTagCompound().hasKey(KEY_COST, NBT.TAG_INT)) {
    		return this.getTagCompound().getInteger(KEY_COST);
    	}
    	if(this.getEntry() != null)	return this.getEntry().getCost(this.getStrength());
    	return 0;
    }

    /**
     * Set this hex's repair cost.
     */
    public void setCost(int cost) //do we need this? ~Upcraft
    {
        if (!this.hasTagCompound())
        {
            this.hexNBT = new NBTTagCompound();
        }

        this.hexNBT.setInteger(KEY_COST, cost);
    }

    public static boolean isHexEqualTo(ItemStack stack1, ItemStack stack2){
    	if((stack1.getItem() instanceof ItemDoll) && (stack2.getItem() instanceof ItemDoll)){
    		if(stack1.hasTagCompound() && stack2.hasTagCompound()){
				return stack1.getTagCompound().getCompoundTag(KEY_NAME) == stack2.getTagCompound().getCompoundTag(KEY_NAME);
			}else{
    			return false;
			}
		}else{
    		return false;
		}
	}
    
    /**
	 * @return the formatted name of this Hex's {@link HexEntry} for simplification
	 */
	@SideOnly(Side.CLIENT)
	@Nullable
	public String getFormattedName() {
		if(this.hasCustomDisplayName()) return this.getDisplayName();
		if(this.entry != null) return this.entry.getFormattedName();
		return null;
	}
	
	public boolean hasCustomDisplayName() {
		return !this.getDisplayName().equals("");
	}
	
	@Nonnull
	public String getDisplayName() {
		return this.hexNBT.getString(KEY_DISPLAY_NAME);
	}
	
	public void setDisplayName(String name) {
		this.hexNBT.setString(KEY_DISPLAY_NAME, name);
	}

	public ItemStack activeUse(ItemStack stack, World world, EntityPlayer player, EnumHand hand, @Nullable EntityLivingBase target)
	{
		player.addStat(VCAchievements.achievementHexFirstUse);
		return this.entry.activeUse(stack, world, player, hand, this.getStrength(), target);
	}
}
