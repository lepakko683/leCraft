package leCraft.common.Blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import leCraft.common.Items.ItemsLeC;
import leCraft.common.entity.tile.TileEntityPlants;
import leCraft.lib.enums.EnumPlantTypeE;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLeCCrop extends LeCMBlock {
	public int[] growTimes = new int[]{16,6};
	public int[] dropMetas = new int[]{0,2};

	public BlockLeCCrop(int id) {
		super(id,0, Material.plants);
		this.isBlockContainer=true;
		this.setRequiresSelfNotify();
		this.disableStats();
		this.setTickRandomly(true);
		this.setHardness(0.1F);
		this.setResistance(0.1F);
		this.setBlockBounds(0f, 0f, 0f, 1f, 0.4f, 1f);
		this.setTextureFile("/ElementCraft2/res/BlocksCrops.png");
	}
	/*
	public int getBlockTextureFromSideAndMetadata(int side, int meta)
    {
		System.out.println("--"+(getCrop(meta)*16+getGrowth(meta)));
		return getCrop(meta)*16+getGrowth(meta);
    }*/
	
	@SideOnly(Side.CLIENT)
    public int getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
		int x = 0;
		try{
			TileEntityPlants plant = (TileEntityPlants)par1IBlockAccess.getBlockTileEntity(par2, par3, par4);
			x = plant.getPlantTexture();
		}catch(Exception e){
			System.out.println("No TileE Found");
			e.printStackTrace();
		}
        return x;
    }
	
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
		/*
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
        */
		//for(int q=0; q<growTimes.length;q++){
			//par3List.add(new ItemStack(par1, 1, this.getMetaMaxC(q)));
		//}
		par3List.add(new ItemStack(par1, 1, 15));
		par3List.add(new ItemStack(par1, 1, 21));
		//System.out.println(this.getCrop(16));
    }
	
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		super.onBlockAdded(par1World, par2, par3, par4);
		par1World.setBlockTileEntity(par2, par3, par4, new TileEntityPlants(par1World.getBlockMetadata(par2, par3, par4)));
		TileEntityPlants tilE = (TileEntityPlants) par1World.getBlockTileEntity(par2, par3, par4);
		
	}
	
	
	public int quantityDropped(Random par1Random)
    {
        return 0;
    }
	
	@Override 
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int meta, int fortune)
    {
		TileEntityPlants plts = (TileEntityPlants) world.getBlockTileEntity(x, y, z);
		if(plts != null){
			return plts.getPlantDrops(meta, fortune, world.rand, super.getBlockDropped(world, x, y, z, meta, fortune));
		}else{
			System.out.println("No TileE");
			return super.getBlockDropped(world, x, y, z, meta, fortune);
		}
    }
	
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
        par1World.removeBlockTileEntity(par2, par3, par4);
    }
	
	private float growSpeed(World world, int x, int y, int z){
		TileEntityPlants plts = (TileEntityPlants) world.getBlockTileEntity(x, y, z);
		return plts.getCropGrowSpeed(world);
	}
	
	public void updateTick(World world, int x, int y, int z, Random rand) {
		TileEntityPlants plts = (TileEntityPlants) world.getBlockTileEntity(x, y, z);
		//plts.onBlockUpdate(rand, world);
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
