package leCraft.worldGen;

import cpw.mods.fml.common.registry.GameRegistry;

public class WorldGenLeCraft {
	public static WorldGenLeCraft instance = new WorldGenLeCraft();
	WorldGenLeCTrees treeGen;
	
	public WorldGenLeCraft(){
		treeGen = new WorldGenLeCTrees(6, 5, 0, 0, 2);
	}
	public void registerWGen(){
		GameRegistry.registerWorldGenerator(treeGen);
	}
}
