package leCraft.misc;

import leCraft.lib.EC2Misc;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class EC2Slot extends Slot {
	private String setting;

	public EC2Slot(IInventory par1iInventory, int par2, int par3, int par4, String setting) {
		super(par1iInventory, par2, par3, par4);
		this.setting = setting;
	}
	
	public boolean isItemValid(ItemStack par1ItemStack)
    {
		if(setting.equals("FB")){
			return EC2Misc.isItemValidSlotFoodBag(par1ItemStack);
		}else if(setting.equals("elExtOutE")){
			return EC2Misc.isItemValidSlotExtractorOutE(par1ItemStack);
		}
		return false;
    }

}
