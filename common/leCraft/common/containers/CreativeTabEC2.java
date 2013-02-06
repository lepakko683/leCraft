package leCraft.common.containers;

import leCraft.common.Items.ItemsLeC;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabEC2 extends CreativeTabs {

	public CreativeTabEC2(int par1, String par2Str) {
		super(par1, par2Str);
	}
	
	@SideOnly(Side.CLIENT)

    /**
     * the itemID for the item to be displayed on the tab
     */
    public int getTabIconItemIndex()
    {
        return ItemsLeC.elementStorGemW.itemID;
    }

}
