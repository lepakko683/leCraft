package leCraft.common.entity.tile;

import java.util.ArrayList;
import java.util.List;

import leCraft.common.ElExtractorRecipes;
import leCraft.common.Items.ItemsLeC;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ISidedInventory;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class TileEntityElementExtractor extends TileEntity implements IInventory, ISidedInventory
{
    /**
     * The ItemStacks that hold the items currently being used in the furnace
     */
    private ItemStack[] extractorItemStacks = new ItemStack[2];

    /** The number of ticks that the current item has been extracted for */
    public int extractorExtractTime = 0;
    
    /** The number of ticks  */
    public int extractorExtractTimeLeft = 0;
    
    /** The amount of water element in the extractor */
    public int extractorAmountWater = 0;
    
    /** The amount of fire element in the extractor */
    public int extractorAmountFire = 0;
    
    /** The amount of earth element in the extractor */
    public int extractorAmountEarth = 0;
    
    /** The amount of magic element in the extractor */
    public int extractorAmountMagic = 0;
    
    /** Is the extractor extracting */
    public boolean extracting = false;
    
    /** The speed that the gem charges */
    public final int chargeSpeed = 1;

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return this.extractorItemStacks.length;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int par1)
    {
        return this.extractorItemStacks[par1];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.extractorItemStacks[par1] != null)
        {
            ItemStack var3;

            if (this.extractorItemStacks[par1].stackSize <= par2)
            {
                var3 = this.extractorItemStacks[par1];
                this.extractorItemStacks[par1] = null;
                return var3;
            }
            else
            {
                var3 = this.extractorItemStacks[par1].splitStack(par2);

                if (this.extractorItemStacks[par1].stackSize == 0)
                {
                    this.extractorItemStacks[par1] = null;
                }

                return var3;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int par1)
    {
        if (this.extractorItemStacks[par1] != null)
        {
            ItemStack var2 = this.extractorItemStacks[par1];
            this.extractorItemStacks[par1] = null;
            return var2;
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.extractorItemStacks[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return "container.elementExtractor";
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
        this.extractorItemStacks = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
            byte var5 = var4.getByte("Slot");

            if (var5 >= 0 && var5 < this.extractorItemStacks.length)
            {
                this.extractorItemStacks[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }

        this.extractorExtractTime = par1NBTTagCompound.getShort("ExtractTime");
        this.extractorExtractTimeLeft = par1NBTTagCompound.getShort("ExtractTimeLeft");
        this.extractorAmountWater = par1NBTTagCompound.getShort("ExtractorWater");
        this.extractorAmountFire = par1NBTTagCompound.getShort("ExtractorFire");
        this.extractorAmountEarth = par1NBTTagCompound.getShort("ExtractorEarth");
        this.extractorAmountMagic = par1NBTTagCompound.getShort("ExtractorMagic");
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("ExtractTime", (short)this.extractorExtractTime);
        par1NBTTagCompound.setShort("ExtractTimeLeft", (short)this.extractorExtractTimeLeft);
        par1NBTTagCompound.setShort("ExtractorWater", (short)this.extractorAmountWater);
        par1NBTTagCompound.setShort("ExtractorFire", (short)this.extractorAmountFire);
        par1NBTTagCompound.setShort("ExtractorEarth", (short)this.extractorAmountEarth);
        par1NBTTagCompound.setShort("ExtractorMagic", (short)this.extractorAmountMagic);
        NBTTagList var2 = new NBTTagList();

        for (int var3 = 0; var3 < this.extractorItemStacks.length; ++var3)
        {
            if (this.extractorItemStacks[var3] != null)
            {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)var3);
                this.extractorItemStacks[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }

        par1NBTTagCompound.setTag("Items", var2);
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns an integer between 0 and the passed value representing how close the current item is to being completely
     * cooked
     */
    public int getExtractProgressLeft(int par1)
    {
        return this.extractorExtractTimeLeft * par1 / 100;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns an integer between 0 and the passed value representing how much burn time is left on the current fuel
     * item, where 0 means that the item is exhausted and the passed value means that the item is fresh
     */
    

    /**
     * Returns true if the furnace is currently burning
     */
    public boolean isExtracting()
    {
        return this.extractorExtractTime > 0;
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        boolean running = false;

        if (this.extractorExtractTimeLeft > 0)
        {
            --this.extractorExtractTimeLeft;
        }
        
        if(this.extractorItemStacks[1]!=null){
        	chargeGem();
        }

        if (!this.worldObj.isRemote)
        {
            if (this.extractorExtractTimeLeft <= 0 && this.canExtract())
            {
            	extractItem();
                this.extractorExtractTimeLeft+=60;
                

                if (this.extractorExtractTimeLeft > 0)
                {
                    running = true;

                }
            }
        }

        if (running)
        {
            this.onInventoryChanged();
        }
    }

    /**
     * Returns true if the furnace can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canExtract()
    {
        if (this.extractorItemStacks[0] == null)
        {
            return false;
        }
        else
        {
            int waterOut = ElExtractorRecipes.extracting().getExtractingResult(this.extractorItemStacks[0], 0);
            int fireOut = ElExtractorRecipes.extracting().getExtractingResult(this.extractorItemStacks[0], 1);
            int earthOut = ElExtractorRecipes.extracting().getExtractingResult(this.extractorItemStacks[0], 2);
            int magicOut = ElExtractorRecipes.extracting().getExtractingResult(this.extractorItemStacks[0], 3);
            
            if (waterOut == 0 && fireOut == 0 && earthOut == 0 && magicOut == 0) return false;            
            
            int totalWaterOut = waterOut + extractorAmountWater;
            int totalFireOut = fireOut + extractorAmountFire;
            int totalEarthOut = earthOut + extractorAmountEarth;
            int totalMagicOut = magicOut + extractorAmountMagic;
            
            if(totalWaterOut > 256){return false;}
            if(totalFireOut > 256){return false;}
            if(totalEarthOut > 256){return false;}
            if(totalMagicOut > 256){return false;}
            
        }
        return true;
    }
    
    private boolean canChargeGem(int charge){
    	if(this.extractorAmountWater < charge && this.extractorAmountFire < charge && this.extractorAmountEarth < charge && this.extractorAmountMagic < charge){return false;}
    	
    	int itemIdInSlot = this.extractorItemStacks[1].itemID;
    	int totalCharge;
    	
    	totalCharge = this.extractorItemStacks[1].getItemDamage() + charge;
    	if(itemIdInSlot == ItemsLeC.elementStorGemW.itemID && totalCharge >= 0){
    		return true;
    	}
    	if(itemIdInSlot == ItemsLeC.elementStorGemF.itemID && totalCharge >= 0){
    		return true;
    	}
    	if(itemIdInSlot == ItemsLeC.elementStorGemE.itemID && totalCharge >= 0){
    		return true;
    	}
    	if(itemIdInSlot == ItemsLeC.elementStorGemM.itemID && totalCharge >= 0){
    		return true;
    	}
    	return false;
    }
    
    public void chargeGem(){
    	if(this.canChargeGem(1)){
    		int itemId = this.extractorItemStacks[1].itemID;
    		int nextCharge = this.extractorItemStacks[1].getItemDamage()-this.chargeSpeed;

    		if(itemId == ItemsLeC.elementStorGemW.itemID && this.extractorAmountWater >= 0){
    			this.extractorItemStacks[1].setItemDamage(nextCharge);
    			this.extractorAmountWater -= chargeSpeed;
    		}
    		if(itemId == ItemsLeC.elementStorGemF.itemID && this.extractorAmountFire >= 0){
    			this.extractorItemStacks[1].setItemDamage(nextCharge);
    			this.extractorAmountFire -= chargeSpeed;
    		}
    		if(itemId == ItemsLeC.elementStorGemE.itemID && this.extractorAmountEarth >= 0){
    			this.extractorItemStacks[1].setItemDamage(nextCharge);
    			this.extractorAmountEarth -= chargeSpeed;
    		}
    		if(itemId == ItemsLeC.elementStorGemM.itemID && this.extractorAmountMagic >= 0){
    			this.extractorItemStacks[1].setItemDamage(nextCharge);
    			this.extractorAmountMagic -= chargeSpeed;
    		}
    		
    		
    		
    	}
    }

    /**
     * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
     */
    public void extractItem()
    {
        if (this.canExtract())
        {
        	int waterOut = ElExtractorRecipes.extracting().getExtractingResult(this.extractorItemStacks[0], 0);
            int fireOut = ElExtractorRecipes.extracting().getExtractingResult(this.extractorItemStacks[0], 1);
            int earthOut = ElExtractorRecipes.extracting().getExtractingResult(this.extractorItemStacks[0], 2);
            int magicOut = ElExtractorRecipes.extracting().getExtractingResult(this.extractorItemStacks[0], 3);
            
            --this.extractorItemStacks[0].stackSize;

            this.extractorAmountWater += waterOut;
            this.extractorAmountFire += fireOut;
            this.extractorAmountEarth += earthOut;
            this.extractorAmountMagic += magicOut;
            
            
            if(this.extractorItemStacks[0].stackSize <= 0){
            	this.extractorItemStacks[0]=null;
            }
        }
    }

    

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    public void openChest() {}

    public void closeChest() {}

    @Override
    public int getStartInventorySide(ForgeDirection side)
    {
        if (side == ForgeDirection.UP) return 0; 
        return 0;
    }

    @Override
    public int getSizeInventorySide(ForgeDirection side)
    {
        return 1;
    }
}
