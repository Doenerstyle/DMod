package makamys.dmod.future.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import makamys.dmod.future.entity.EntityLivingFuture;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class ItemFuture extends Item {
	
	public boolean onStackClicked(ItemStack stack, Slot slot, int button, EntityPlayer player) {
		return false;
	}

	public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, int button, EntityPlayer player) {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public void appendTooltip(ItemStack stack, World world, List<String> tooltip) {}
	
	public static ItemStack finishUsing(Item dis, ItemStack stack, World world, EntityLiving user) {
		return dis instanceof ItemFood ? EntityLivingFuture.eatFood(user, world, stack) : stack;
	}
	
	public static boolean canBeNested(Item dis) {
		return true; // TODO don't allow shulker box, configurable blacklist?
	}
	
}
