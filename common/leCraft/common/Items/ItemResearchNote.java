package leCraft.common.Items;

import java.util.List;

import leCraft.common.LeCraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemResearchNote extends Item {

	public ItemResearchNote(int par1) {
		super(par1);
		this.setCreativeTab(LeCraft.tabEC2);
		this.setMaxStackSize(0);
	}
	
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
    	par3List.add("ElementCraft 2 Research Note");
    }

	
	
	
}
