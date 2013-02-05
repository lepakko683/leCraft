package leCraft.common.Items;

import javax.jws.Oneway;

import leCraft.common.LeCraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemNotebook extends Item {

	public ItemNotebook(int par1) {
		super(par1);
		this.setMaxStackSize(1);
		this.setCreativeTab(LeCraft.tabEC2);
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		//opengui
        return par1ItemStack;
    }

}
