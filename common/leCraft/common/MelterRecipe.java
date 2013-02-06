package leCraft.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MelterRecipe {
	private final ItemStack result;
	
	private List<ItemStack> meltings = new ArrayList<ItemStack>();
	
	public MelterRecipe(ItemStack result, List items){
		this.result = result;
		this.meltings = items;
	}
	
	public ItemStack getResult(int var1){
		ItemStack retStack = result.copy();
		retStack.stackSize = retStack.stackSize * var1;
		return retStack;
	}
	
	public int matches(List itemsList, World world){
		ArrayList melts = (ArrayList) this.meltings;
		ArrayList currRecipe = (ArrayList) itemsList;
		boolean containsItem;
		
		List<Integer> testList = new ArrayList<Integer>();
		
		System.out.println(melts.size());
		System.out.println(currRecipe.size());
		
		if(currRecipe.size() == 0 || melts.size() == 0){
			return 0;
		}
		
		for(int var1=0;var1<melts.size();var1++){
			containsItem=false;
			for(int var2=0;var2<currRecipe.size();var2++){
				ItemStack is1 = (ItemStack) melts.get(var1);
				ItemStack is2 = (ItemStack) currRecipe.get(var2);

				if(is2.itemID == is1.itemID && is2.getItemDamage() == is1.getItemDamage()){
					int number = (int) Math.floor(is2.stackSize / is1.stackSize);
					testList.add(number);
					containsItem=true;
				}
			}
			if(!containsItem){
				return 0;
			}
		}
		
		//getLowest
		int lowest = -1;
		for(int var3=0;var3<testList.size();var3++){
			if(testList.get(var3)<lowest || lowest < 0){
				lowest = testList.get(var3);
			}
		}
		
		return lowest;
	}
}
