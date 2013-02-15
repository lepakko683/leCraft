package leCraft.lib;

import java.util.HashMap;

import leCraft.lib.enums.EnumPlant;
import leCraft.lib.enums.EnumPlantTypeE;

public class LeCPlantBase {
	public static LeCPlantBase instance = new LeCPlantBase();
	
	public HashMap<Integer, EnumPlant> plant = new HashMap<Integer, EnumPlant>(EnumPlant.class.getEnumConstants().length);
	public void initPlants(){
		
		for(EnumPlant plnt : EnumPlant.class.getEnumConstants()){
			this.addPlant(plnt);
		}
		
	}
	public LeCPlantBase(){
		this.initPlants();
	}
	
	private void addPlant(EnumPlant plt){
		boolean contains = false;
		for(EnumPlantTypeE tp : EnumPlantTypeE.class.getEnumConstants()){
			if(tp.equals(plt.getType())){
				contains = true;
			}
		}
		if(contains){
			plant.put(plt.getId(), plt);
		}
	}
}
