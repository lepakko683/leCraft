package leCraft.common.entity.tile;

import java.util.ArrayList;
import java.util.Random;

import leCraft.common.MelterHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityMelter extends TileEntity {
	
	private ArrayList containedItems = new ArrayList();
	
	private short stackAmount;
	
	
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
		short stackAmountLoaded = par1NBTTagCompound.getShort("SavedStacks");
		this.stackAmount = stackAmountLoaded;
		this.loadItemStacks(stackAmount, par1NBTTagCompound);
    }
	
	private void loadItemStacks(short par1, NBTTagCompound par2NBTTagCompound){
		if(par1>0){
			for(int var2=0;var2<par1;var2++){
				
				String stackName = "Stack" + var2;
				int[] loadArray = new int[3];
				loadArray = par2NBTTagCompound.getIntArray(stackName);
				int itemid = loadArray[0];
				int itemamount = loadArray[1];
				int itemdamage = loadArray[2];
				
				ItemStack loadStack = new ItemStack(itemid, itemamount, itemdamage);
				this.containedItems.add(loadStack);
			}
		}
	}

    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
    	par1NBTTagCompound.setShort("SavedStacks", this.stackAmount);
        this.saveItemStacks(par1NBTTagCompound);
    }
    
    private void saveItemStacks(NBTTagCompound par1NBTTagCompound)
    {
    	if(containedItems.size()>0){
    		for(int var1=0;var1<containedItems.size();var1++)
    		{
    			String stackName = "Stack" + var1;
    			ItemStack saveStack = (ItemStack)containedItems.get(var1);
    			int[] saveArray = new int[3];
    			saveArray[0]=saveStack.itemID;
    			saveArray[1]=saveStack.stackSize;
    			saveArray[2]=saveStack.getItemDamage();
    			par1NBTTagCompound.setIntArray(stackName, saveArray);
    		}
    	}
    }
    
    public void addItemStack(ItemStack stack){
    	int lengthL = this.containedItems.size();
    	boolean added = false;
    	if(lengthL > 0){
    		for(int var1=0;var1<lengthL;var1++){
    			int stackIId = stack.itemID;
    			int stackSize1 = stack.stackSize;
    			int stackDmg = stack.getItemDamage();
    			ItemStack selStack = (ItemStack)this.containedItems.get(var1);
    			
    			if(selStack.itemID == stackIId && (stackSize1+selStack.stackSize) <= selStack.getMaxStackSize()&&stackDmg == selStack.getItemDamage())
    			{
    				selStack.stackSize += stackSize1;
    				added = true;
    				System.out.println("added new items to existing stack!");
    			}
    				
    			
    		}
    		
    	}
    	if(!added){
			this.containedItems.add(stack);
			System.out.println("Added new itemstack!");
		}
    	
    }
    public ItemStack getContainedItem(int index){
    	if(!this.containedItems.isEmpty()){
    		return (ItemStack)this.containedItems.get(index);
    	}
    	return null;
    }
    
    public void doCraft(World par1, int x, int y, int z){
    	System.out.println("doCraft action");
    	ItemStack recResult = MelterHandler.getInstance().craft(par1, this.containedItems);
    	Random rand = new Random();
    	
    	if(recResult != null){
    		float var6 = 0.7F;
            double var7 = (double)(par1.rand.nextFloat() * var6) + (double)(1.0F - var6) * 0.5D;
            double var9 = (double)(par1.rand.nextFloat() * var6) + (double)(1.0F - var6) * 0.5D;
            double var11 = (double)(par1.rand.nextFloat() * var6) + (double)(1.0F - var6) * 0.5D;
            EntityItem var13 = new EntityItem(par1, (double)x + var7, (double)y + var9+0.7F, (double)z + var11, recResult);
            var13.delayBeforeCanPickup = 1;
            par1.spawnEntityInWorld(var13);
    	}
    	this.containedItems.clear();
    }
    private void spawnEntity(World par1, int eId, int x, int y, int z){
    	if(EntityList.entityEggs.containsKey(eId))
    	{
    		Entity ent = null;
    		
    		ent = EntityList.createEntityByID(eId, par1);
    		
    		if(ent !=null){
    			ent.setLocationAndAngles(x+0.5, y+2, z+0.5, 90.0F, 0.0F);
    			((EntityLiving)ent).initCreature();
    			par1.spawnEntityInWorld(ent);
    		}
    	}
    	
    }
    
    public void updateEntity() 
    {
    }
}
