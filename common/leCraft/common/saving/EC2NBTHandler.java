package leCraft.common.saving;

import net.minecraft.nbt.NBTTagCompound;


public abstract class EC2NBTHandler{
	/**The name of data */
	public final String saveName;
	
	/**Does this object need saving */
	private boolean needSaving;
	
	/**@param saveName Name for data*/
	public EC2NBTHandler(String saveName){
		this.saveName = saveName;
	}
	
	
	
	/**
     * reads in data from the NBTTagCompound into this MapDataBase
     */
    public abstract void readFromNBT(NBTTagCompound var1);

    /**
     * write data to NBTTagCompound from this MapDataBase, similar to Entities and TileEntities
     */
    public abstract void writeToNBT(NBTTagCompound var1);
    
    public void makeNeedSave(){
    	this.setNeedSave(true);
    }
    
    public void setNeedSave(boolean par1){
    	this.needSaving=par1;
    }
    
    public boolean needsSave(){
    	return this.needSaving;
    }
}
