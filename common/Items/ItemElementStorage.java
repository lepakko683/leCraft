package leCraft.common.Items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import leCraft.common.LeCraft;

public class ItemElementStorage extends Item {
	
	private int storageGemType;

	public ItemElementStorage(int par1, int type) {
		super(par1);
		this.storageGemType = type;
		this.setMaxDamage(256);
		this.setMaxStackSize(1);
		this.setCreativeTab(LeCraft.tabEC2);
		this.setHasSubtypes(false);
	}
	/** Returns the type of the gem 0-3 */
	
	public int getGemType(){
		return storageGemType;
	}
	
	public int getGemCharge(ItemStack par1){
		return par1.getItemDamage();
	}
	
	/**Can the gem be charged
	 * @param par1 the gem
	 * @param par2 the amount of charge to add
	 * @param par3 the type of gem 0-3 */
	public boolean canCharge(ItemStack par1, int par2, int par3){
		int nextCharge = par1.getItemDamage() + par2;
		if(par3 == getGemType() && nextCharge <= par1.getMaxDamage()){
			return true;
		}
		return false;
	}
	
	/**Void to charge the gem
	 * @param par1 the gem
	 * @param par2 the amount of charge to add
	 * @param par3 the type of gem 0-3 */
	public void charge(ItemStack par1, int par2, int par3){
		int nextCharge = par1.getItemDamage() + par2;
		par1.setItemDamage(nextCharge);
	}
	
	@SideOnly(Side.CLIENT)

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
        par3List.add("Gem Charge: " + (par1ItemStack.getMaxDamage()-par1ItemStack.getItemDamage()));
    }
	

}
