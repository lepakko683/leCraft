package leCraft.common.Blocks.Multi;

import java.util.ArrayList;

import leCraft.common.Blocks.LeCBlockPos;

public class LeCBLayout {
	public static enum BLayoutShape{
		CUBE;
	}
	protected BLayoutShape shape;
	
	protected int width;
	
	protected int height;
	
	protected int depth;
	
	//protected LeCBlockPos[] parts;
	
	
	public LeCBLayout(BLayoutShape shape, int width, int height, int depth){
		this.shape = shape;
		this.width = width;
		this.height = height;
		this.depth = depth;
	}
	
	public static boolean isValidShape(BLayoutShape shape, ArrayList<LeCBlockPos> parts){
		if(shape.equals(BLayoutShape.CUBE)){
			int lx=0, ly=0, lz=0;
			int mx=0, my=0, mz=0;
			
			int qw=0;
			for(LeCBlockPos pos : parts){
				if(qw==0){
					lx = pos.posX();
					ly = pos.posY();
					lz = pos.posZ();
					mx = pos.posX();
					my = pos.posY();
					mz = pos.posZ();
					qw++;
				}
				if(pos.posX() < lx){
					lx = pos.posX();
				}if(pos.posY() < ly){
					ly = pos.posY();
				}if(pos.posZ() < lz){
					lz = pos.posZ();
				}
				
				if(pos.posX() > mx){
					mx = pos.posX();
				}if(pos.posY() > my){
					my = pos.posY();
				}if(pos.posZ() > mz){
					mz = pos.posZ();
				}
			}
			LeCBlockPos begin = new LeCBlockPos(lx, ly, lz);
			LeCBlockPos end = new LeCBlockPos(mx, my, mz);
			System.out.println(begin.toString());
			System.out.println(end.toString());
			int ex = end.posX()-begin.posX(), ey = end.posY()-begin.posY(), ez = end.posZ()-begin.posZ();
			System.out.println("Size: " + parts.size());
			if((ex+ey+ez)/3==ex && parts.size() == (end.posX()-begin.posX()+1)*(end.posY()-begin.posY()+1)*(end.posZ()-begin.posZ()+1)){
				return true;
			}
		}
		return false;
	}
}
