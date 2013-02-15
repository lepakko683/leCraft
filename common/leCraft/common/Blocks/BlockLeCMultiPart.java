package leCraft.common.Blocks;

import java.util.ArrayList;

import leCraft.common.LeCraft;
import leCraft.common.entity.tile.TileEntityCompost;
import leCraft.common.entity.tile.TileEntityMBPart;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockEventData;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockLeCMultiPart extends BlockContainer {

	public BlockLeCMultiPart(int id, int texIndex, Material par3Material) {
		super(id, texIndex, par3Material);
		this.setCreativeTab(LeCraft.tabEC2);
	}
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		if(par1World.getBlockTileEntity(par2, par3, par4) == null){
			System.out.println("null");
		}
		if(par5EntityPlayer.inventory.getCurrentItem() != null && par1World.getBlockTileEntity(par2, par3, par4) instanceof TileEntityMBPart){
			((TileEntityMBPart) (par1World.getBlockTileEntity(par2, par3, par4))).onBlockUpdate(true, par5EntityPlayer.inventory.getCurrentItem().itemID);
			System.out.println(par5EntityPlayer.inventory.getCurrentItem().itemID + " " + Item.bucketEmpty.itemID);
		
			if(par5EntityPlayer.inventory.getCurrentItem().itemID == Item.bucketEmpty.itemID){
			
				if(!par5EntityPlayer.capabilities.isCreativeMode){
					System.out.println("test");
					par5EntityPlayer.inventory.consumeInventoryItem(Item.bucketEmpty.itemID);
					
				}
				System.out.println("exec");
				if(par1World.getBlockTileEntity(par2, par3, par4) == null && !(par1World.getBlockTileEntity(par2, par3, par4) instanceof TileEntityCompost)){
					par1World.setBlockTileEntity(par2, par3, par4, new TileEntityCompost());
					System.out.println("true");
					return true;
				}else{
					System.out.println("Null!");
				}
			}
			
    	}
		return false;
    }
	
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
		TileEntityMBPart tien = (TileEntityMBPart) par1World.getBlockTileEntity(par2, par3, par4);
		if(tien != null){
			tien.onBlockUpdate(false, 0);
		}
	}
	
	public void void012(World world, LeCBlockPos loc, TileEntity tile, ArrayList<LeCBlockPos> parts){
		world.setBlockTileEntity(loc.posX(), loc.posY(), loc.posZ(), new TileEntityCompost());
		if(world.getBlockTileEntity(loc.posX(), loc.posY(), loc.posZ())!=null){
			((TileEntityCompost)world.getBlockTileEntity(loc.posX(), loc.posY(), loc.posZ())).
		}
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityMBPart();
	}

}
