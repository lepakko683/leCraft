package leCraft.common.containers;

import leCraft.lib.EC2Misc;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryFBag extends InventoryBasic {

	public InventoryFBag(String par1Str, int par2) {
		super("container.enderchest", EC2Misc.FoodBagSlots);
	}
	
	public void loadInventoryFromNBT(NBTTagList par1NBTTagList)
    {
        int cslot;

        for (cslot = 0; cslot < this.getSizeInventory(); ++cslot)
        {
            this.setInventorySlotContents(cslot, (ItemStack)null);
        }

        for (cslot = 0; cslot < par1NBTTagList.tagCount(); ++cslot)
        {
            NBTTagCompound var3 = (NBTTagCompound)par1NBTTagList.tagAt(cslot);
            int var4 = var3.getByte("Slot") & 255;

            if (var4 >= 0 && var4 < this.getSizeInventory())
            {
                this.setInventorySlotContents(var4, ItemStack.loadItemStackFromNBT(var3));
            }
        }
    }
	
	public NBTTagList saveInventoryToNBT()
    {
        NBTTagList saverList = new NBTTagList(EC2Misc.NBTTagFoodBagItems);

        for (int var2 = 0; var2 < this.getSizeInventory(); ++var2)
        {
            ItemStack saveStack = this.getStackInSlot(var2);

            if (saveStack != null)
            {
                NBTTagCompound saver = new NBTTagCompound();
                saver.setByte("Slot", (byte)var2);
                saveStack.writeToNBT(saver);
                saverList.appendTag(saver);
            }
        }

        return saverList;
    }

}
