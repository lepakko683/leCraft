package leCraft.common.Items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

import leCraft.common.LeCraft;

public class ItemMaterialEC2 extends Item {
	
	public static final int subtypeCount = 5;
	
	public static final String[] elementNames = new String[] {"Fire", "Water", "Earth", "Magic", "Air"};


	public ItemMaterialEC2(int par1) {
		super(par1);
		this.setTextureFile("/ElementCraft2/res/Items.png");
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(LeCraft.tabEC2);
	}
	
	public String getItemNameIS(ItemStack par1ItemStack)
    {
        int var2 = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 4);
        return super.getItemName() + "." + elementNames[var2];
    }
	
	@SideOnly(Side.CLIENT)
	public int getIconFromDamage(int par1)
    {
		return par1+9;
    }
	
	@SideOnly(Side.CLIENT)
    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int var4 = 0; var4 < subtypeCount; ++var4)
        {
            par3List.add(new ItemStack(par1, 1, var4));
        }
    }
	

}
