package leCraft.common.entity.tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import leCraft.common.Blocks.BlocksLeC;
import leCraft.lib.enums.EnumPlant;
import leCraft.lib.enums.EnumPlantTypeE;
import leCraft.lib.LeCPlantBase;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityPlants extends TileEntity {
	EnumPlant plant;
	int radius = 1;
	protected int plantId = 0;
	protected int plantGrowth = 0;
	
	public TileEntityPlants(int id){
		this.plantId = id;
		plant = LeCPlantBase.instance.plant.get(this.plantId);
	}
	
	public boolean canUpdate(){
		return false;
	}
	
	public void onPlaced(int id){
		this.plantId = id;
	}
	
	public float getCropGrowSpeed(World world){
		int earth = world.getBlockId(this.xCoord, this.yCoord-1, this.zCoord);
		int crop = this.plantId;
		float speed = 0f;
		
		for(int x1=this.xCoord-radius;x1<this.xCoord+radius;x1++){
			for(int z1=this.zCoord-radius;z1<this.zCoord+radius;z1++){
				if(x1 != this.xCoord && z1 != this.zCoord){
					TileEntityPlants oPlant = (TileEntityPlants) world.getBlockTileEntity(x1, this.yCoord, z1);
					if(world.getBlockId(x1, this.yCoord, z1)==BlocksLeC.leccrops.blockID && oPlant.plantId==crop){
						speed += 1f;
					}
				}
			}
		}
		if(Block.blocksList[earth].isFertile(world, this.xCoord, this.yCoord-1, this.zCoord)){
			speed += 4f;
		}
		if(world.canBlockSeeTheSky(this.xCoord, this.yCoord, this.zCoord)){
			speed += 2f;
		}
		System.out.println("GSpeed: " + speed);
		return speed;
	}
	
	
	
	public int getPlantTexture(){
		return plantId*16+plantGrowth;
	}
	
	public void growPlant(int amount){
		if(this.plantGrowth + amount <= plant.getGrowTime()-1){
			this.plantGrowth += amount;
		}else{
			this.plantGrowth += amount - this.plantGrowth + amount - plant.getGrowTime()-1;
		}
	}
	
	private boolean isGrown(){
		return plant.getGrowTime()-1 == this.plantGrowth;
	}

	public ArrayList<ItemStack> getPlantDrops(int meta, int fortune, Random rand, ArrayList<ItemStack> blockDropped) {
		ArrayList<ItemStack> ret = blockDropped;
        int growTime = plant.getGrowTime();
        
        if(this.isGrown()){
        	for(int dro=0; dro < (1 + rand.nextInt(2) + fortune);dro++){
        		ret.add(plant.getMainDrop());
        	}
        }
		return ret;
	}
	
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
		this.plantId = par1NBTTagCompound.getInteger("plantId");
		this.plantGrowth = par1NBTTagCompound.getInteger("plantGrowth");
		this.plant = LeCPlantBase.instance.plant.get(this.plantId);
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        par1NBTTagCompound.setInteger("plantId", this.plantId);
        par1NBTTagCompound.setInteger("plantGrowth", this.plantGrowth);
    }

	public void onBlockUpdate(Random rand, World world) {
		if(world.getBlockLightValue(this.xCoord, this.yCoord, this.zCoord) >= 9){
    		if(rand.nextInt(16)+1 < this.getCropGrowSpeed(world)){
    			if(world.isRaining() && this.plant.getGrowTime()>=plantGrowth+2){
    				if(rand.nextInt(2)==0){
    					this.growPlant(2);
    				}	
    			}else{
    				if(this.plant.getGrowTime()>=plantGrowth+1){
    					this.growPlant(1);
    				}
    			}
    			//System.out.println("Grow! " + growth + " " + crop);
    		}
    	}
		
	}
}
