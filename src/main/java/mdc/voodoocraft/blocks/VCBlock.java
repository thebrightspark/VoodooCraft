package mdc.voodoocraft.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class VCBlock extends Block
{
	 protected final Random BLOCK_RANDOM = new Random();
	 protected Item item;
	
    public VCBlock(String name, Material mat, boolean createItemBlock)
    {
        super(mat);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        if (createItemBlock) this.item = new ItemBlock(this).setRegistryName(this.getRegistryName()).setUnlocalizedName(this.getUnlocalizedName());
    }
    
    public VCBlock(String name, boolean createItemBlock)
    {
    	this(name, Material.ROCK, createItemBlock);
    }
    
    public VCBlock(String name) {
    	this(name, Material.ROCK, true);
	}
    
    public Item item() {
        return this.item != null ? this.item : Item.getItemFromBlock(this);
    }
}
