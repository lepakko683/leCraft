package leCraft.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;


public class OreIdsEC2 {
	static List<Integer> ores = new ArrayList<Integer>();
	static Map<Integer, String> names = new HashMap<Integer, String>();
	
	public static void Init(){
		ores.add(getNewId(), Block.oreDiamond.blockID);
		names.put(Block.oreDiamond.blockID, "Diamond Ore");
		
		ores.add(getNewId(), Block.oreDiamond.blockID);
		names.put(Block.oreEmerald.blockID, "Emerald Ore");
		
		ores.add(getNewId(), Block.oreGold.blockID);
		names.put(Block.oreGold.blockID, "Gold Ore");
		
		ores.add(getNewId(), Block.oreIron.blockID);
		names.put(Block.oreIron.blockID, "Iron Ore");
		
		ores.add(getNewId(), Block.oreCoal.blockID);
		names.put(Block.oreCoal.blockID, "Coal Ore");
	}
	
	private static int getNewId(){
		if(ores.size()==0){
			return 0;
		}
		return ores.size();
	}
	
	public static boolean containsOre(int blockId){
		for(int var1=0;var1<ores.size();var1++){
			if(ores.get(var1)==blockId){
				return true;
			}
		}
		return false;
		
	}
	
	public String getName(int blockId){
		if(names.containsKey(blockId)){
			return names.get(blockId);
		}else{
			return "Ore:"+blockId;
		}
	}
	
	public static int getOreId(int blockId){
		return ores.indexOf(blockId);
	}
	
	public static String getNameOID(int oreId){
		if(ores.get(oreId)!=null){
			return names.get(ores.get(oreId));
		}else{
			return "Ore";
		}
	}
}
