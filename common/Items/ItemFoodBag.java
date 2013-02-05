package leCraft.common.Items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import leCraft.common.LeCraft;

public class ItemFoodBag extends Item {

	public ItemFoodBag(int par1) {
		super(par1);
		this.setMaxStackSize(1);
		this.canRepair=false;
		//this.setMaxDamage(EC2Misc.MaxDMGFBag);
		this.setCreativeTab(LeCraft.tabEC2);
		this.setTextureFile("/ElementCraft2/res/Items.png");
	}
	
	/*
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		FoodBagData data = (FoodBagData)par2World.loadItemData(FoodBagData.class, ("foodBag_"+par3EntityPlayer.username));
		ItemFood nextFood = data.getNextFood();
		if(nextFood != null){
			
			if (par3EntityPlayer.canEat(false) && data.isOwner(par3EntityPlayer))//temporary
	        {
	            par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
	        }

	        return par1ItemStack;
	        
		}
        return par1ItemStack;
    }
    
	
	public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		// TODO: add potion effects
		FoodBagData data = (FoodBagData)par2World.loadItemData(FoodBagData.class, ("foodBag_"+par3EntityPlayer.username));
		ItemFood food = data.getNextFood();
		ItemStack foodStack = data.getNextFoodStack();
        data.eatFood();
        par3EntityPlayer.getFoodStats().addStats(food);
        par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
        //this.func_77849_c(par1ItemStack, par2World, par3EntityPlayer);
        return foodStack;
    }
    */
	
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 32;
    }
	
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.eat;
    }

}
