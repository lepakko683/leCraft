package leCraft.common.Blocks;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import leCraft.client.BlockRendererMelter;
import leCraft.common.LeCraft;
import leCraft.common.entity.tile.TileEntityMelter;

public class BlockMelter extends BlockContainer {

	public BlockMelter(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setCreativeTab(LeCraft.tabEC2);
		this.blockIndexInTexture=1;
		this.setTextureFile("/ElementCraft2/res/Blocks.png");
	}
	
	public void addCollidingBlockToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity)
    {
		float height = 0.9375F;
		float var8 = 0.125F;
		
		float begin = 0.0625F;
		float end = 0.9375F;
		
        this.setBlockBounds(begin, 0.0F, begin, end, 0.3125F, end);
        super.addCollidingBlockToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        this.setBlockBounds(begin, 0.0F, begin, end, height, begin+var8);
        super.addCollidingBlockToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        this.setBlockBounds(end-var8, 0.0F, begin, end, height, end);
        super.addCollidingBlockToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        this.setBlockBounds(begin, 0.0F, end-var8, end, height, end);
        super.addCollidingBlockToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        this.setBlockBounds(begin, 0.0F, begin, begin+var8, height, end);
        super.addCollidingBlockToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        this.setBlockBoundsForItemRender();
    }
	
	public void setBlockBoundsForItemRender()
    {
        this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.9375F, 0.9375F);
    }
	
	public int getBlockTextureFromSide(int par1)
    {
		if(par1==0){
			return 3;
		}else if(par1==1){
			return 2;
		}else{
			return 1;
		}
    }
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		if(par1World.isRemote)
		{
			return true;
		}else
		{
			ItemStack currIt = par5EntityPlayer.inventory.getCurrentItem();
			int metaDa = par1World.getBlockMetadata(par2, par3, par4);
			TileEntityMelter melterTile = (TileEntityMelter) par1World.getBlockTileEntity(par2, par3, par4);
			
			if(currIt==null){
				if(metaDa>0){
					if(melterTile != null && par5EntityPlayer.isSneaking()){
						par1World.setBlockMetadataWithNotify(par2, par3, par4, 0);
						melterTile.doCraft(par1World, par2, par3, par4);
						
					}
				}
				return true;
			}else{
				
				
				if(currIt.itemID == Item.bucketWater.itemID){
					if(metaDa < 1){
						if(!par5EntityPlayer.capabilities.isCreativeMode){
							par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, new ItemStack(Item.bucketEmpty));
						}
						par1World.setBlockMetadataWithNotify(par2, par3, par4, 1);
					}
					return true;
					
				}
				else if(currIt.itemID == Item.bucketEmpty.itemID){
					if(metaDa > 0){
						if(!par5EntityPlayer.capabilities.isCreativeMode){
							par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, new ItemStack(Item.potato,10));
						}
						par1World.setBlockMetadataWithNotify(par2, par3, par4, 0);
					}
					return true;
				}
			}
		}
		return true;
		
    }
	/*
	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random){
	}*/
	
	/*
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
		List entList = null;
		int metadata = par1World.getBlockMetadata(par2, par3, par4);
        if (!par1World.isRemote && metadata > 0)
        {
        	TileEntityMelter melterTile = (TileEntityMelter) par1World.getBlockTileEntity(par2, par3, par4);
        	
            entList = par1World.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getAABBPool().addOrModifyAABBInPool(par2+0.125, par3+0.1, par4+0.125, par2+0.875, par3+1, par4+0.875));
            
            if(!entList.isEmpty()){
            	//System.out.println("contiuned");
            	Iterator ite = entList.iterator();
            	while(ite.hasNext()){
            		EntityItem eItem = (EntityItem)ite.next();
            		ItemStack sItem = ERROR!;
            		melterTile.addItemStack(sItem);
            		eItem.setDead();
            	}
            }
        }
    }*/
	
	@Override
    public TileEntity createNewTileEntity(World world) {
            return new TileEntityMelter();
    }
	
	public int getRenderType()
    {
		int a = BlockRendererMelter.instance.getRenderId();
        return a;
    }
	
	public boolean isOpaqueCube()
    {
        return false;
    }
	
	public boolean renderAsNormalBlock()
    {
        return false;
    }
	
	

}
