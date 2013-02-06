package leCraft.common.Blocks.Multi;

import leCraft.common.Blocks.LeCBlockPos;


public class LeCMultiBlockSPart {
	public LeCBlockPos mainBlock;
	
	public LeCMultiBlockSPart(int x, int y, int z){
		mainBlock = new LeCBlockPos(x,y,z);
	}
	public LeCMultiBlockSPart(LeCBlockPos pos){
		mainBlock = pos;
	}
}
