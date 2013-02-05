package leCraft.lib;

import leCraft.common.Items.ItemElementStorage;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;


public class EC2Misc {
	/**
	 * The damage that is added to items when used
	 * Will be based on difficulty
	 */
	public static int DamageItems = 1;
	
	public static int MaxDMGCrusher = 64;
	
	public static int MaxDMGFBag = 512;
	
	public static int FoodBagSlots = 5;
	
	public static boolean isItemValidSlotFoodBag(ItemStack par1){
		if(par1.getItem() instanceof ItemFood){
			return true;
		}
		return false;
	}
	public static boolean isItemValidSlotExtractorOutE(ItemStack par1){
		if(par1.getItem() instanceof ItemElementStorage){
			return true;
		}
		return false;
	}
	
	public static String NBTTagFoodBagItems = "FoodBagItems";
}
