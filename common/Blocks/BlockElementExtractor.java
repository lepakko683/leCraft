package leCraft.common.Blocks;

import leCraft.common.LeCraft;
import leCraft.common.entity.tile.TileEntityElementExtractor;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;


public class BlockElementExtractor extends BlockContainer {

	public BlockElementExtractor(int par1, int par2, Material par2Material) {
		super(par1, par2, par2Material);
		this.setCreativeTab(LeCraft.tabEC2);
		
	}
	
	public String getTextureFile()
    {
        return "/ElementCraft2/res/Blocks.png";
    }
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        if (par1World.isRemote)
        {
        	
            return true;
            
        }
        else
        {
        	
        	
        	if(par1World.getBlockTileEntity(par2, par3, par4)==null){
        		return false;
        	}
            	par5EntityPlayer.openGui(LeCraft.getInstance(), 0, par1World, par2, par3, par4);
            

            return true;
        }
    }
	
	@Override
    public TileEntity createNewTileEntity(World world) {
		return new TileEntityElementExtractor();
    }

}
