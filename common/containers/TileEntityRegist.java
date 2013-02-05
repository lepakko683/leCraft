package leCraft.common.containers;

import leCraft.common.entity.tile.TileEntityElementExtractor;
import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntityRegist {
	public static void Register(){
		GameRegistry.registerTileEntity(TileEntityElementExtractor.class, "0");
	}
}
