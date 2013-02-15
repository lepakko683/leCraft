package leCraft.common.entity.tile;

import java.util.ArrayList;

import leCraft.common.Blocks.LeCBlockPos;

import net.minecraft.tileentity.TileEntity;

public class TileEntityCompost extends TileEntity {
	
	public ArrayList<LeCBlockPos> parts = new ArrayList<LeCBlockPos>();
	
	public void onCreate(ArrayList<LeCBlockPos> prts){
		this.parts = prts;
	}
	
	private int count = 0;
	public void updateEntity() {
		if(count >= 19){
			System.out.println("hello!");
			count = 0;
		}
		count++;
	}

}
