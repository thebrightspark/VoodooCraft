package mdc.voodoocraft.rituals;

import java.util.ArrayList;
import java.util.List;

import mdc.voodoocraft.init.VCBlocks;
import mdc.voodoocraft.util.ModUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class SandToSoulsand {

	@SubscribeEvent
	public static void rightClickBlock(PlayerInteractEvent e)
	{
		if(!e.getWorld().isRemote&&e.getHand()==EnumHand.MAIN_HAND&&e.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND)==null)
		{
			if(e.getWorld().getBlockState(e.getPos()).getBlock()==VCBlocks.CHALK_BASIC_SYMBOL)
			{
				System.out.println("Knows I right clicked chalk");
				for(Entity ent : ModUtils.getEntitiesInRange(EntityItem.class, e.getWorld(), e.getPos().getX(), e.getPos().getY(), e.getPos().getZ(), 1))
				{
					if(ent instanceof EntityItem)
					{
						EntityItem entity = (EntityItem)ent;
						if(entity.getEntityItem().getItem() == Item.getItemFromBlock(Blocks.NETHERRACK))
						{
							System.out.println("Knows there is a netherrack");
							ItemStack stack = entity.getEntityItem();
							stack.stackSize--;
							if(stack.stackSize==0)
							{
								entity.setDead();
							}else entity.setEntityItemStack(stack);
							setSandBelowToSoul(e.getWorld(), e.getPos());
							break;
						}	
					}
				}
			}
		}	
	}
	
	public static void setSandBelowToSoul(World world, BlockPos pos)
	{
		List<BlockPos> list = new ArrayList<BlockPos>();
		list.addAll(threebyThreeAroundPos(pos.down()));
		list.addAll(threebyThreeAroundPos(pos.down(2)));
		list.addAll(threebyThreeAroundPos(pos.down(3)));
		
		for(BlockPos bPos : list)
		{
			if(world.getBlockState(bPos).getBlock()==Blocks.SAND)
			{
				world.setBlockState(bPos, Blocks.SOUL_SAND.getDefaultState());
			}
		}
	}
	public static List threebyThreeAroundPos(BlockPos pos)
	{
		List<BlockPos> list = new ArrayList<BlockPos>();
		list.add(pos);
		list.add(pos.east());
		list.add(pos.east().north());
		list.add(pos.east().south());
		list.add(pos.west());
		list.add(pos.west().north());
		list.add(pos.west().south());
		list.add(pos.north());
		list.add(pos.south());
		return list;
	}
}
