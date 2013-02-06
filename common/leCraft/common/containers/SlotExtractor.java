package leCraft.common.containers;

import leCraft.common.ElExtractorRecipes;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import leCraft.common.Items.ItemElementStorage;

public class SlotExtractor extends Slot {

	private EntityPlayer thePlayer;
	private int field_75228_b;
	
	public SlotExtractor(EntityPlayer par0EntityPlayer, IInventory par1iInventory, int par2, int par3, int par4) {
		super(par1iInventory, par2, par3, par4);
		thePlayer = par0EntityPlayer;
	}
	
	
	public boolean isItemValid(ItemStack par1ItemStack)
    {
		if(par1ItemStack.getItem() instanceof ItemElementStorage){return true;}
        return false;
    }
	
	public ItemStack decrStackSize(int par1)
    {
        if (this.getHasStack())
        {
            this.field_75228_b += Math.min(par1, this.getStack().stackSize);
        }

        return super.decrStackSize(par1);
    }
	
	public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack)
    {
        this.onCrafting(par2ItemStack);
        super.onPickupFromSlot(par1EntityPlayer, par2ItemStack);
    }
	
	protected void onCrafting(ItemStack par1ItemStack, int par2)
    {
        this.field_75228_b += par2;
        this.onCrafting(par1ItemStack);
    }
	
	protected void onCrafting(ItemStack par1ItemStack)
    {
        par1ItemStack.onCrafting(this.thePlayer.worldObj, this.thePlayer, this.field_75228_b);

        if (!this.thePlayer.worldObj.isRemote)
        {
            int var2 = this.field_75228_b;
            float var3 = ElExtractorRecipes.extracting().getExperience(par1ItemStack);
            int var4;

            if (var3 == 0.0F)
            {
                var2 = 0;
            }
            else if (var3 < 1.0F)
            {
                var4 = MathHelper.floor_float((float)var2 * var3);

                if (var4 < MathHelper.ceiling_float_int((float)var2 * var3) && (float)Math.random() < (float)var2 * var3 - (float)var4)
                {
                    ++var4;
                }

                var2 = var4;
            }

            while (var2 > 0)
            {
                var4 = EntityXPOrb.getXPSplit(var2);
                var2 -= var4;
                this.thePlayer.worldObj.spawnEntityInWorld(new EntityXPOrb(this.thePlayer.worldObj, this.thePlayer.posX, this.thePlayer.posY + 0.5D, this.thePlayer.posZ + 0.5D, var4));
            }
        }


        this.field_75228_b = 0;
    }

}
