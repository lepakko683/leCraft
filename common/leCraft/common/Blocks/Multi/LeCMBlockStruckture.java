package leCraft.common.Blocks.Multi;

import java.util.ArrayList;
import java.util.List;

import leCraft.common.Blocks.BlocksLeC;
import leCraft.common.Blocks.LeCBlockPos;
import leCraft.misc.LeCStruckt;

import net.minecraft.world.World;

public abstract class LeCMBlockStruckture {
	ArrayList<LeCBlockPos> parts = new ArrayList<LeCBlockPos>();
	
	protected int partId = BlocksLeC.lecmutipart.blockID;
	
	protected boolean isBuilt;
	
	public abstract boolean canCreate(World wo, LeCStruckt st, LeCBlockPos tpos);
	
	public abstract void create();

}
