package leCraft.common.saving;

import leCraft.lib.EC2Misc;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class FoodBagData extends EC2NBTHandler {

	public FoodBagData(String saveName, EntityPlayer plr) {
		super(saveName);
		this.owner = plr.username;
	}
	
	public final String nbtPrefix = "slot";

	public byte bagColours[];
	
	public String owner;
	
	public ItemStack slots[] = new ItemStack[EC2Misc.FoodBagSlots];
	
	public ItemFood getNextFood(){
		for(int i=0;i<slots.length;i++){
			if(slots[i] != null && slots[i].stackSize>0){
				ItemFood retItem = (ItemFood) slots[i].getItem();
				return retItem;
			}
		}
		return null;
	}
	
	public ItemStack getNextFoodStack(){
		for(int i=0;i<slots.length;i++){
			if(slots[i] != null && slots[i].stackSize>0){
				return slots[i];
			}
		}
		return null;
	}
	
	public void eatFood(){
		this.getNextFoodStack().stackSize--;
		this.onStacksUpdate();
		
	}
	
	private void onStacksUpdate(){
		for(int i=0;i<slots.length;i++){
			if(slots[i].stackSize<=0){
				slots[i]=null;
			}
		}
	}
	
	public boolean isOwner(EntityPlayer plr){
		if(this.owner == plr.username){
			return true;
		}
		return false;
	}

	@Override
	public void readFromNBT(NBTTagCompound var1) {
		for(int slot=0;slot<EC2Misc.FoodBagSlots;slot++){
			int loadArray[] = new int[3];
			loadArray = var1.getIntArray(nbtPrefix + slot);
			if(loadArray != null){
				int itemId = loadArray[0];
				int amount = loadArray[1];
				int data = loadArray[2];
				ItemStack stack = new ItemStack(itemId, amount, data);
				this.slots[slot]=stack;
			}else{
				this.slots[slot]=null;
			}
			
		}
		this.owner = var1.getString("theOwner");
		
	}

	@Override
	public void writeToNBT(NBTTagCompound var1) {
		for(int slot=0;slot<EC2Misc.FoodBagSlots;slot++){
			int saveArray[] = new int[3];
			saveArray[0] = this.slots[slot].itemID;
			saveArray[1] = this.slots[slot].stackSize;
			saveArray[2] = this.slots[slot].getItemDamage();
			var1.setIntArray(nbtPrefix + slot, saveArray);
		}
		var1.setString("theOwner", this.owner);
		
	} 
}
