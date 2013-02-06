package leCraft.common.Blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockLeCLeaves extends LeCMBlock{
	public int fruitGrow=9;

	public BlockLeCLeaves(int id, int texIndex) {
		super(id, texIndex, Material.plants);
		this.setTickRandomly(true);
		this.setTextureFile("/ElementCraft2/res/BlocksFarming.png");
	}
	
	public int getBlockTextureFromSideAndMetadata(int side, int meta)
    {
		int g = (int) (Math.floor(meta/(fruitGrow+1)));
		return 2*16+g;
		
		//return (int) (2*16+Math.floor(par2/LeCFarmingHelper.fruitGrowTime));
    }
	
	@Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
    	int meta = par1World.getBlockMetadata(par2, par3, par4);
    	int lid = (int) Math.floor(meta/(fruitGrow+1));
    	
    	if(meta < fruitGrow+lid*(fruitGrow+1)){
    		par1World.setBlockMetadataWithNotify(par2, par3, par4, meta+1);
    	}else{
    		
    		if(par1World.isAirBlock(par2, par3-1, par4)){
    			
    			par1World.setBlockAndMetadataWithNotify(par2, par3-1, par4, BlocksLeC.lecfruits.blockID, 0); // farmhelper
    			par1World.setBlockMetadataWithNotify(par2, par3, par4, 0);
    		}
    		
    	}
    }


}
