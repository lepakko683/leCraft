package leCraft.common.Blocks;

import java.util.Random;

import leCraft.lib.LeCFarmingHelper;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockLeCFruit extends LeCMBlock {

	public int fruitMat = 5;
	
	public BlockLeCFruit(int id, int texIndex) {
		super(id, texIndex, Material.plants);
		float pix = 0.0625F*2 + 0.0625F/2;
        this.setBlockBounds(0.0F + pix, 0.0F, 0.0F + pix, 1.0F - pix, 1.0F, 1.0F - pix);
        //this.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		this.setTextureFile("/ElementCraft2/res/BlocksFarming.png");
		this.setTickRandomly(true);
		this.setRequiresSelfNotify();
	}
	
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
		return this.isAttachedTo(par1World, par2, par3, par4, BlocksLeC.lecleaves.blockID);
    }
	
	public int getBlockTextureFromSideAndMetadata(int side, int meta)
    {
		int g = (int) (Math.floor(meta/(fruitMat+1)));
		//System.out.println(meta + g);
		if(meta % (fruitMat + ((fruitMat+1)*g))==0 && meta != 0+(g*(fruitMat+1))){
			return 5*16+g;
		}
		return 4*16+g;
    }
	
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
    	if(!this.isAttachedTo(par1World, par2, par3, par4, BlocksLeC.lecleaves.blockID)){
    		par1World.setBlockWithNotify(par2, par3, par4, 0);
    	}else{
    		int meta = par1World.getBlockMetadata(par2, par3, par4);
    		
    		if(meta<LeCFarmingHelper.fruitMatTime){
    			par1World.setBlockMetadataWithNotify(par2, par3, par4, meta+1);
    		}
    		
    		int g = (int) (Math.floor(meta/(fruitMat+1)));
    		//System.out.println(meta + g);
    		//System.out.println("TEST: " + meta % (fruitMat + ((fruitMat+1)*g)));
    	}
    	
    	
    		
    }
    
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
    	if(!this.isAttachedTo(par1World, par2, par3, par4, BlocksLeC.lecleaves.blockID)){
    		par1World.setBlockWithNotify(par2, par3, par4, 0);
    	}
    	
    }

    
    public boolean isAttachedTo(World world, int x, int y, int z, int upId){
    	if(world.getBlockId(x, y+1, z)==upId){
    		return true;
    	}
    	
    	return false;
    }
    
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
    	if(par5EntityPlayer.isSneaking()){
    		//par1World.setBlockMetadataWithNotify(par2, par3, par4, 5);
    		System.out.println("Blocks metadata: " + par1World.getBlockMetadata(par2, par3, par4));
    	}
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
        return 1;
    }

}
