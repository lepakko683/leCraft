package leCraft.common.Blocks;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import leCraft.worldGen.WorldGenLeCTrees;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockLeCSapling extends LeCMBlock {

	public int sapGrow = 2;
	Random rand = new Random();
	public int leafGrow = 9;
	public int fruitGrow = 5;
	
	public Map<Integer, Integer> rootBlocks = new HashMap<Integer, Integer>();
	public final int[] treeHeightsMin = new int[]{6};
	public final int[] treeWidths = new int[]{5};
	public final int heightDiff = 2;
	
	public BlockLeCSapling(int id, int texIndex) {
		super(id, texIndex, Material.plants);
		this.setTextureFile("/ElementCraft2/res/BlocksFarming.png");
		//this.setTickRandomly(true);
		rootBlocks.put(2, 0);
		rootBlocks.put(3, 0);
		rootBlocks.put(12, 0);
		System.out.println("new!");
	}
	
	public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        return this.canGrowOnThisBlock(par1World.getBlockId(par2, par3, par4), par1World.getBlockMetadata(par2, par3, par4));
    }
	
	public int getBlockTextureFromSideAndMetadata(int side, int meta)
    {
		int g = (int) (Math.floor(meta/(sapGrow+1)));
		return 6*16+g;
    }
	
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
		return this.canGrowOnThisBlock(par1World.getBlockId(par2, par3-1, par4), par1World.getBlockMetadata(par2, par3, par4));
    }
	
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
    	if(!this.canGrowOnThisBlock(par1World.getBlockId(par2, par3-1, par4), par1World.getBlockMetadata(par2, par3, par4))){
    		par1World.setBlockWithNotify(par2, par3, par4, 0);
    	}
    	
    }
	
	@Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
    	int meta = par1World.getBlockMetadata(par2, par3, par4);
    	int lid = (int) Math.floor(meta/(sapGrow+1));
    	
    	if(meta < sapGrow+lid*(sapGrow+1)){
    		par1World.setBlockMetadataWithNotify(par2, par3, par4, meta+1);
    	}else{
    		
    			
    		
    		
    		
    	}
    }
	
	public void growTree(World world, int x, int y, int z, int treeId){
		if(treeId==0){ //apple tree
			
		}
	}
	
	public boolean canGrowOnThisBlock(int blockid, int metadata)
	{
		
		if(this.rootBlocks.containsKey(blockid)){
			return true;
			//return metadata==rootBlocks.get(blockid);
		}
		//System.out.println("not growing");
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
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
    	if(par5EntityPlayer.isSneaking()){
    		Random rand = new Random();
    		//par1World.setBlockMetadataWithNotify(par2, par3, par4, 5);
    		System.out.println("Blocks metadata: " + par1World.getBlockMetadata(par2, par3, par4));
    		if(!par1World.isRemote){
    			WorldGenLeCTrees gen = new WorldGenLeCTrees(7, 5, 0, 0, 1);
    			gen.generate(par1World, rand, par2, par3, par4);
    		}
    		
    	}
        return false;
    }

	
}
