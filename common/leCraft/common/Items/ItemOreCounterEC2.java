package leCraft.common.Items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import leCraft.common.LeCraft;
import leCraft.common.OreIdsEC2;

public class ItemOreCounterEC2 extends Item {

	public ItemOreCounterEC2(int par1) {
		super(par1);
		this.setMaxDamage(8);
		this.setMaxStackSize(1);
		this.setCreativeTab(LeCraft.tabEC2);
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		ItemStack retStack = par1ItemStack.copy();
		if(par2World.isRemote){
			return retStack;
		}else{
			/*
			if(!par3EntityPlayer.capabilities.isCreativeMode){
				retStack.setItemDamage(retStack.getItemDamage()+1);
			}
			
			List oreCount = this.getOreCount(par2World, par3EntityPlayer);
			
			//ItemStack ggg = new ItemStack(Block.blockDiamond, oreCount);
			//EntityItem itemsd = new EntityItem(par2World, par3EntityPlayer.posX, par3EntityPlayer.posY+1, par3EntityPlayer.posZ, ggg);
			//itemsd.delayBeforeCanPickup=1;
			//par2World.spawnEntityInWorld(itemsd);
			for(int i=0;i<oreCount.size();i++){
				if(oreCount.get(i)!=null){
					System.out.println("Found: " + oreCount.get(i) + OreIdsEC2.getNameOID(i));
				}
			}
			*/
			
		}
        return retStack;
    }
	
	private List getOreCount(World world, EntityPlayer player){
		List<Integer> countO = new ArrayList<Integer>();
		long count=0;
		double playerX = player.posX;
		double playerY = player.posY;
		double playerZ = player.posZ;
		System.out.println(playerX + " " + playerY + " " + playerZ);
		//final int diamondBId = 56;
		
		int beginX = (int) (Math.floor(playerX/16)*16);
		int beginY = 0;
		int beginZ = (int) (Math.floor(playerZ/16)*16);
		
		System.out.println(beginX + " " + beginY + " " + beginZ);
		
		for(int y=0;y<world.getHeight();y++){
			for(int z=0;z<16;z++){
				for(int x=0;x<16;x++){
					int oreX=x+beginX;
					int oreZ=z+beginZ;
					
					int oreIdF = getOreId(world, oreX, y, oreZ);
					
					if(oreIdF != 0){
						
						if(countO.size()==0){
							countO.add(oreIdF, 1);
						}else if(countO.get(oreIdF)>0){
							countO.set(oreIdF, countO.get(oreIdF)+1);
						}else{
							//add something?
						}
					
					}
					
				}
			}
		}
		
		return countO;
	}
	
	private int getOreId(World world, int x, int y, int z){
		int blockId = world.getBlockId(x, y, z);
		
		if(OreIdsEC2.containsOre(blockId)){
			return OreIdsEC2.getOreId(blockId);
		}
		else{
			return 0;
		}
		
	}
	
	
	
}
