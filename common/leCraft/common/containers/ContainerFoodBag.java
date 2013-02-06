package leCraft.common.containers;

import leCraft.lib.EC2Misc;
import leCraft.misc.EC2Slot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerFoodBag extends Container {
	private final String setting = "FB";
	
	private int numSlots = EC2Misc.FoodBagSlots;
	
	public ContainerFoodBag(IInventory inv){
		for(int s=0;s<EC2Misc.FoodBagSlots;s++){
			this.addSlotToContainer(new EC2Slot(inv, s, 44 + s*16, 35, this.setting));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int plrSlot)
    {
        ItemStack retStack = null;
        Slot fromSlot = (Slot)this.inventorySlots.get(plrSlot);

        if (fromSlot != null && fromSlot.getHasStack()) //if fromSlot has a stack
        {
            ItemStack fromStack = fromSlot.getStack();
            retStack = fromStack.copy();
            
            if(!EC2Misc.isItemValidSlotFoodBag(fromStack)){
            	return null;
            }

            if (plrSlot < 5)
            {
                if (!this.mergeItemStack(fromStack, numSlots, this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(fromStack, 0, numSlots, false))
            {
                return null;
            }

            if (fromStack.stackSize == 0)
            {
            	fromSlot.putStack((ItemStack)null);
            }
            else
            {
            	fromSlot.onSlotChanged();
            }
        }

        return retStack;
    }

}
