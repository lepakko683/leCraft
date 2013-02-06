package leCraft.common.Blocks;

public class LeCBlockPos {
	protected int x;
	protected int y;
	protected int z;
	
	public LeCBlockPos(int x, int y, int z){
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	public int posX(){
		return this.x;
	}
	public int posY(){
		return this.y;
	}
	public int posZ(){
		return this.z;
	}
}
