package leCraft.recipes;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import leCraft.common.Items.ItemsLeC;
import cpw.mods.fml.common.registry.GameRegistry;

public class InitEC2Recipes {
	
	public static InitEC2Recipes instance = new InitEC2Recipes();
	
	private ItemStack Crusher = new ItemStack(ItemsLeC.crusher, 1, -1);
	
	public void Init(){
		initCrusherRecipes();
	}
	
	private void initCrusherRecipes(){
		
		GameRegistry.addShapelessRecipe(new ItemStack(ItemsLeC.ec2mat, 1, 1), new Object[]{
			Crusher, Block.dirt
		});
	}
	
	//private void addDRecipe
}
