package leCraft.common.entity.tile;

import java.util.ArrayList;

import leCraft.common.Blocks.BlockLeCMultiPart;
import leCraft.common.Blocks.BlocksLeC;
import leCraft.common.Blocks.LeCBlockPos;
import leCraft.common.Blocks.Multi.LeCBLayout;
import leCraft.common.Blocks.Multi.LeCBLayout.BLayoutShape;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMBPart extends TileEntity{
	private LeCBlockPos rootBlock;
	private boolean canCalcBlocks=false;
	private boolean isCalcing=false;
	private int lastAWith=0;
	
	public boolean canUpdate(){
		return false;
	}
	
	public LeCBlockPos setAsPartOf(LeCBlockPos root){
		this.rootBlock=root;
		return new LeCBlockPos(xCoord, yCoord, zCoord);
	}
	
	public void onBlockUpdate(boolean clicked, int itemid){
		if(!clicked){
			if(rootBlock != null){
				System.out.println(worldObj.getBlockId(xCoord, yCoord, zCoord));
			}
			System.out.println("works!");
		}else{
			lastAWith = itemid;
			if(itemid==Item.arrow.itemID && !isCalcing){
				this.canCalcBlocks=true;
			}
			System.out.println("clicked");
		}
		
		if(canCalcBlocks){
			isCalcing=true;
			ArrayList<LeCBlockPos> mbpartsaround = new ArrayList<LeCBlockPos>();
			for(int x=-5;x<=5;x++){
				for(int y=-5;y<=5;y++){
					for(int z=-5;z<=5;z++){
						System.out.println("Pos: " + x + " " + y + " " + z);
						if(worldObj.getBlockId(x+xCoord, y+yCoord, z+zCoord)==BlocksLeC.lecmutipart.blockID){
							System.out.println("here");
							mbpartsaround.add(new LeCBlockPos(x+xCoord, y+yCoord, z+zCoord));
						}
					}
				}
			}
			if(LeCBLayout.isValidShape(BLayoutShape.CUBE, mbpartsaround)){
				(BlockLeCMultiPart) Block.blocksList[BlocksLeC.lecmutipart].;
			}
			isCalcing=false;
			this.canCalcBlocks=false;
		}
	}
	
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
		int x0 = par1NBTTagCompound.getInteger("rootx");
		int y0 = par1NBTTagCompound.getInteger("rooty");
		int z0 = par1NBTTagCompound.getInteger("rootz");
		this.rootBlock = new LeCBlockPos(x0,y0,z0);
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
    	if(rootBlock != null){
    		par1NBTTagCompound.setInteger("rootx", rootBlock.posX());
    		par1NBTTagCompound.setInteger("rooty", rootBlock.posY());
    		par1NBTTagCompound.setInteger("rootz", rootBlock.posZ());
    	}
    }
}
