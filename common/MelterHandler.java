package leCraft.common;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;



public class MelterHandler {
	
	private static final MelterHandler instance = new MelterHandler();
	
	private List recipes = new ArrayList();
	
	public static final MelterHandler getInstance(){
		return instance;
	}
	
	private MelterHandler(){

		//AddRecipes();
	}
	
	
	public void AddRecipes(){
		this.addRecipe(new ItemStack(Block.blockEmerald,2), new Object[]{new ItemStack(Item.arrow,2), new ItemStack(Block.dirt,3)});
		this.addRecipe(new ItemStack(Block.blockDiamond,1), new Object[]{new ItemStack(Block.dirt,1), new ItemStack(Item.poisonousPotato,2)});
	}
	
	public void addRecipe(ItemStack par1, Object ... par2Array){
		ArrayList var1 = new ArrayList();
		Object[] craftItems = par2Array;
		int var2 = par2Array.length;
		
		for(int var3=0;var3<var2;var3++){
			
			Object var4 = craftItems[var3];
			
			if(var4 instanceof ItemStack){
				var1.add(((ItemStack)var4).copy());
			}else{
				throw new RuntimeException("Invalid object type at MelterHandler:addRecipe");
			}
		}
		
		this.recipes.add(new MelterRecipe(par1, var1));
		
	}
	
	public ItemStack craft(World world, List items)
	{
		ArrayList craftItems = (ArrayList) items;
		
		if(this.recipes.size()==0)
		{
			return null;
		}
		
		for(int i=0;i<this.recipes.size();i++){
			MelterRecipe var2 = (MelterRecipe)this.recipes.get(i);
			int matches = var2.matches(craftItems, world);
			if(matches>0){
				return var2.getResult(matches);
			}
		}
		
		return null;
	}
}
