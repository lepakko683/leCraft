package leCraft.common.Items;

import leCraft.common.LeCraft;
import leCraft.lib.EC2Misc;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemToolCrusher extends Item{

	public ItemToolCrusher(int par1) {
		super(par1);
		this.setMaxStackSize(1);
		this.setMaxDamage(EC2Misc.MaxDMGCrusher);
		this.setContainerItem(ItemsLeC.crusher);
		this.setCreativeTab(LeCraft.tabEC2);
	}
	
	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack par1ItemStack)
    {
        return false;
    }
	
	
	@Override
	public ItemStack getContainerItemStack(ItemStack itemStack)
    {
        ItemStack retStack = itemStack;
        
        if((retStack.getItemDamage()+EC2Misc.DamageItems) > EC2Misc.MaxDMGCrusher){
        	return new ItemStack(Item.stick, 1);
        }
        retStack.setItemDamage(retStack.getItemDamage()+EC2Misc.DamageItems); //damages the item by specified amount
        return retStack;
    }

}
