package leCraft.common.Blocks;

import java.util.ArrayList;
import java.util.Random;

import leCraft.common.Items.ItemsLeC;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockLeCCrop extends LeCMBlock {
	public int[] growTimes = new int[]{16,6};
	public int[] dropMetas = new int[]{0,2};

	public BlockLeCCrop(int id) {
		super(id,0, Material.plants);
		this.setRequiresSelfNotify();
		this.setTickRandomly(true);
		this.setHardness(0.1F);
		this.setResistance(0.1F);
		this.setBlockBounds(0f, 0f, 0f, 1f, 0.4f, 1f);
		this.setTextureFile("/ElementCraft2/res/BlocksCrops.png");
	}
	
	public int getBlockTextureFromSideAndMetadata(int side, int meta)
    {
		//System.out.println(getCrop(meta)*16+getGrowth(meta));
		return getCrop(meta)*16+getGrowth(meta);
    }
	
	public int getGrowth(int meta){
		int cv = growTimes[0]-1;
		int retValue=0;
		for(int q=1;q<growTimes.length;q++){
			if(meta>cv+1){
				cv += growTimes[q];
			}else{
				retValue = cv-meta;
				if(q-1==0){
					retValue++;
				}
				retValue = growTimes[q-1] - retValue;
				
				//System.out.println("q: " + q + " cv: "+ cv + " Growth: " + retValue + " Meta: " + meta);
				return retValue;
			}
		}
		return 0;
	}
	public int getCrop(int meta){
		int cv = growTimes[0]-1;
		
		for(int q=1;q<growTimes.length;q++){
			if(meta>cv+1){
				cv+=growTimes[q];
			}else{
				//System.out.println("Crop: "+(q-1));

				return q-1;
			}
		}
		return 0;
	}
	
	public int quantityDropped(Random par1Random)
    {
        return 0;
    }
	
	@Override 
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int meta, int fortune)
    {
        ArrayList<ItemStack> ret = super.getBlockDropped(world, x, y, z, meta, fortune);
        int growTime = growTimes[getCrop(meta)];
        if(getCrop(meta)==0){
        	growTime-=1;
        }
        
        boolean isGrown = this.getGrowth(meta)==growTime;
        if(isGrown){
        	for(int dro=0; dro < (1 + world.rand.nextInt(2) + fortune);dro++){
        		if(world.rand.nextInt(10)<4 && dro==0){
        			ret.add(new ItemStack(Item.blazePowder, 1 + world.rand.nextInt(fortune+2), 0));
        		}
        		ret.add(getMainDrop(getCrop(meta)));
        	}
        }

        return ret;
    }
	
	private ItemStack getMainDrop(int crop){
		return new ItemStack(ItemsLeC.harvres, 1, crop*2);
		//if(dropMetas.length >= crop){
		//	return new ItemStack(ItemsLeC.harvres, 1, dropMetas[getCrop(crop)]);
		//}else{
		//	
		//}
	}
	
	private float growSpeed(World world, int x, int y, int z){
		int radius = 1;
		int meta = world.getBlockMetadata(x, y, z);
		int crop = this.getCrop(meta);
		int earth = world.getBlockId(x, y-1, z);
		
		float speed = 0f;
		
		for(int x1=x-radius;x1<x+radius;x1++){
			for(int z1=z-radius;z1<z+radius;z1++){
				if(x1 != x && z1 != z){
					if(world.getBlockId(x1, y, z1)==BlocksLeC.leccrops.blockID && this.getCrop(world.getBlockMetadata(x1, y, z1))==crop){
						speed += 1f;
					}
				}
			}
		}
		if(blocksList[earth].isFertile(world, x, y-1, z)){
			speed += 4f;
		}
		if(world.canBlockSeeTheSky(x, y, z)){
			speed += 2f;
		}
		return speed;
	}
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
    	if(par5EntityPlayer.isSneaking() && !par1World.isRemote){
    		int meta = par1World.getBlockMetadata(par2, par3, par4);
    		int next = getCrop(meta)*16+getGrowth(meta+1);
    		par1World.setBlockMetadataWithNotify(par2, par3, par4, next);
    		//System.out.println("Blocks metadata: " + meta);
    	}
        return false;
    }
	public int getGrowTime(int meta){
		int crop = this.getCrop(meta);
		return crop == 0 ? this.growTimes[crop]-1 : this.growTimes[crop];
	}
	
	public void updateTick(World world, int x, int y, int z, Random rand) {
		int meta = world.getBlockMetadata(x, y, z);
    	if(world.getBlockLightValue(x, y, z) >= 9){
    		if(rand.nextInt(16)+1 < this.growSpeed(world, x, y, z)){
    			
    			int growth=this.getGrowth(meta);
    			int crop=this.getCrop(meta);
    			if(world.isRaining() && this.getGrowTime(meta)>=growth+2){
    				if(rand.nextInt(2)==0){
    					world.setBlockMetadataWithNotify(x, y, z, meta+2);
    				}	
    			}else{
    				if(this.getGrowTime(meta)>=growth+1){
    					world.setBlockMetadataWithNotify(x, y, z, meta+1);
    				}
    			}
    			//System.out.println("Grow! " + growth + " " + crop);
    		}
    	}
    }
    
    public void onNeighborBlockChange(World par1World, int x, int y, int z, int neid) {
    	//TODO: neighbor block update
    	
    }
    
    public boolean isValidSoil(int blcid){
    	return blcid == Block.tilledField.blockID;
    }
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }
	
	public boolean isOpaqueCube()
    {
        return false;
    }
	
	public boolean renderAsNormalBlock()
    {
        return false;
    }
	
	public int getRenderType()
    {
        return 6;
    }
	

}
