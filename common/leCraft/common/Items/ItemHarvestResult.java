package leCraft.common.Items;

import java.util.HashMap;
import java.util.List;

import leCraft.common.Blocks.BlocksLeC;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemHarvestResult extends ItemLeC {
	
	/** 
	 * key - Metadata | Value - name*/
	private static HashMap<Integer, Boolean> millables = new HashMap<Integer, Boolean>();
	public static HashMap<Integer, String> names = new HashMap<Integer, String>();

	public ItemHarvestResult(int par1) {
		super(par1);
		addSubtype(0, "Blazing Wheat", false);
		addSubtype(1, "Blazing Seeds", true);
		addSubtype(2, "Chives", false);
		addSubtype(3, "Chives' Seeds", false);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setTextureFile("/ElementCraft2/res/Items.png");
		
	}
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
		if(par1ItemStack.getItemDamage()==2){
			par3World.setBlockAndMetadataWithNotify(par4, par5+1, par6, BlocksLeC.leccrops.blockID, 0);
		}
        return false;
    }
	
	public String getItemNameIS(ItemStack par1ItemStack)
    {
        //int var2 = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 4);
        return "ItemHarvestResult" + par1ItemStack.getItemDamage();
    }
	
	@SideOnly(Side.CLIENT)
	public int getIconFromDamage(int par1)
    {
		return par1+18;
    }
	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int var4 = 0; var4 < names.size(); ++var4)
        {
            par3List.add(new ItemStack(par1, 1, var4));
        }
    }
	
	public void addSubtype(int meta, String name, boolean millable){
		names.put(meta, name);
		if(millable){
			millables.put(meta, true);
		}
	}
	
	public static boolean isMillable(int meta){
		return millables.containsKey(meta);
	}

}
