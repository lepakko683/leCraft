package leCraft.common.Items;

import java.util.HashMap;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

public class ItemHarvestResult extends ItemLeC {
	
	/** 
	 * key - Metadata | Value - name*/
	private static HashMap<Integer, Boolean> millables = new HashMap<Integer, Boolean>();
	public static HashMap<Integer, String> names = new HashMap<Integer, String>();

	public ItemHarvestResult(int par1) {
		super(par1);
		addSubtype(0, "Blazing Wheat", false);
		addSubtype(1, "Blazing Seeds", true);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setTextureFile("/ElementCraft2/res/Items.png");
		
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
