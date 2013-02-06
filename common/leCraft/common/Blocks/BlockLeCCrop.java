package leCraft.common.Blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockLeCCrop extends LeCMBlock {
	public int[] growTimes = new int[]{16,6};

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
		System.out.println(getCrop(meta)*16+getGrowth(meta));
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
				
				System.out.println("q: " + q + " cv: "+ cv + " Growth: " + retValue + " Meta: " + meta);
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
				System.out.println("Crop: "+(q-1));

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
	
	public ItemStack getMainDrop(int crop){
		return crop==0 ? new ItemStack(Item.wheat, 1, 0) : new ItemStack(Item.snowball, 1, 0);
	}
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
    	if(par5EntityPlayer.isSneaking() && !par1World.isRemote){
    		int meta = par1World.getBlockMetadata(par2, par3, par4);
    		int next = getCrop(meta)*16+getGrowth(meta+1);
    		par1World.setBlockMetadataWithNotify(par2, par3, par4, next);
    		System.out.println("Blocks metadata: " + meta);
    	}
        return false;
    }
	
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
    	//TODO: update
    }
    
    public void onNeighborBlockChange(World par1World, int x, int y, int z, int neid) {
    	//TODO: neighbor block update
    	
    }
    
    public boolean isValidSoil(World world, int x, int y, int z){
    	return false;
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
