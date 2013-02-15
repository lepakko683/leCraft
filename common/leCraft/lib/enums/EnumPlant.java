package leCraft.lib.enums;

import net.minecraft.item.ItemStack;
import leCraft.common.Items.ItemsLeC;


public enum EnumPlant {
	BLAZEWHEAT(0, 16, "Blazing Wheat", new ItemStack(ItemsLeC.harvres, 1, 0), EnumPlantTypeE.CROP),
	CHIVES(1, 6, "Chives", new ItemStack(ItemsLeC.harvres, 1, 2), EnumPlantTypeE.CROP);
	
	protected final int pid;
	protected final int growTime;
	protected final String plantName;
	protected final EnumPlantTypeE pType;
	protected final ItemStack mainDrop;
	
	private EnumPlant(int id, int gt, String name, ItemStack drop, EnumPlantTypeE type){
		growTime = gt;
		plantName = name;
		mainDrop = drop;
		pType = type;
		pid = id;
	}
	public int getId(){
		return pid;
	}
	
	public int getGrowTime(){
		return growTime;
	}
	public String getName(){
		return plantName;
	}
	public ItemStack getMainDrop(){
		return mainDrop;
	}
	public EnumPlantTypeE getType(){
		return pType;
	}
}
