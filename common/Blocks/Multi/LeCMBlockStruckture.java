package leCraft.common.Blocks.Multi;

import java.util.ArrayList;
import java.util.List;

import leCraft.common.Blocks.BlocksLeC;
import leCraft.common.Blocks.LeCBlockPos;
import leCraft.misc.LeCStruckt;

import net.minecraft.world.World;

public abstract class LeCMBlockStruckture {
	List<LeCBlockPos> parts = new ArrayList<LeCBlockPos>();
	//LeCBlockPos[] parts = new LeCBlockPos[8];
	
	protected int partId = BlocksLeC.lecmutipart.blockID;
	
	protected boolean isBuilt;
	
	public abstract boolean canCreate(World wo, LeCStruckt st, LeCBlockPos tpos);
	
	public abstract void create();

}
