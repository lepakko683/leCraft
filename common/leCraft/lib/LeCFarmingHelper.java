package leCraft.lib;

import java.util.HashMap;

import net.minecraft.block.Block;

public class LeCFarmingHelper {
	public static final String[] TreeTypes = new String[]{"Apple","Orange"};
	public static final String[] BushTypes = new String[]{"Strawberry","PineApple"};
	public static final String[] PlantTypes = new String[]{"Nettle"};
	
	public static final int fruitGrowTime = 9;
	public static final int fruitMatTime = 5;
	public static final int raw = 2;
	public static final int rew = 4;
	
	HashMap<Integer, Float> soilTypes = new HashMap<Integer, Float>();
	
	public void addSoilTypes(){
	}
	/**
	 * @param blockId - Block Id,
	 * @param growSpeed - The speed that plants grow on the soil 1:1*/
	public boolean addSoilType(int blockId, float growSpeed){
		if(soilTypes.containsKey(blockId)){
			return false;
		}else{
			soilTypes.put(blockId, 1f);
		}
		return false;
	}
}
