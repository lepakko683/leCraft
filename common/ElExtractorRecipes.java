package leCraft.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public class ElExtractorRecipes {
	
	private static final ElExtractorRecipes extractBase = new ElExtractorRecipes();
	
	//TODO: change the way this works
	
	
	private Map waterOut = new HashMap();
	private Map fireOut = new HashMap();
	private Map earthOut = new HashMap();
	private Map magicOut = new HashMap();

	private Map experienceList = new HashMap();
	
	public static final ElExtractorRecipes extracting(){
		return extractBase;
	}
	
	private ElExtractorRecipes(){
		addExtracting(Item.slimeBall.itemID, 5, 0, 1, 0, 0.5F);
		addExtracting(Item.coal.itemID, 0, 8, 5, 0, 0.5F);
	}
	
	public void addExtracting(int inputId, int water, int fire, int earth, int magic, float exp){
		waterOut.put(inputId, water);
		fireOut.put(inputId, fire);
		earthOut.put(inputId, earth);
		magicOut.put(inputId, magic);
		experienceList.put(inputId, exp);

	}
	
	public int getExtractingResult(ItemStack item, int type) 
    {
        if (item == null)
        {
            return 0;
        }
        
        if(type==0 && waterOut.containsKey(item.itemID)){return (Integer) waterOut.get(item.itemID);}
        if(type==1 && fireOut.containsKey(item.itemID)){return (Integer) fireOut.get(item.itemID);}
        if(type==2 && earthOut.containsKey(item.itemID)){return (Integer) earthOut.get(item.itemID);}
        if(type==3 && magicOut.containsKey(item.itemID)){return (Integer) magicOut.get(item.itemID);}
        
		return 0;
    }
	
	public float getExperience(ItemStack item)
    {
        if (item == null || item.getItem() == null)
        {
            return 0;
        }
        float ret = item.getItem().getSmeltingExperience(item);
        if (ret < 0 && experienceList.containsKey(item.itemID))
        {
            ret = ((Float)experienceList.get(item.itemID)).floatValue();
        }
        return (ret < 0 ? 0 : ret);
    }
}
