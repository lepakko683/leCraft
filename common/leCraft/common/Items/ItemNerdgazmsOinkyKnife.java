package leCraft.common.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import leCraft.common.LeCraft;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemNerdgazmsOinkyKnife extends Item {
	float pi;
	public ItemNerdgazmsOinkyKnife(int par1) {
		super(par1);
		pi = 0f;
		this.setMaxStackSize(1);
		this.setMaxDamage(64);
	}
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return LeCraft.cpec2.getRarity("OINK");
    }
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {/*
		if(!par2World.isRemote){
			int x;
			int y;
			int z;
			Vec3 v = par3EntityPlayer.getLookVec();
			System.out.println("X: " + v.xCoord + " Y: " + v.yCoord + " Z: " + v.zCoord);
			
			//par3EntityPlayer.playSound("", par2, par3)
			par2World.playSoundAtEntity(par3EntityPlayer, "mob.wither.spawn", 100.0f, 3f);


		}*/
        return par1ItemStack;
    }
	
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
		if(!par3World.isRemote){
			int x;
			int y;
			int z;
			Vec3 v = par2EntityPlayer.getLookVec();
			//System.out.println("X: " + v.xCoord + " Y: " + v.yCoord + " Z: " + v.zCoord);
			pi += 0.05f;
			//par3EntityPlayer.playSound("", par2, par3)
			par3World.playSoundAtEntity(par2EntityPlayer, "ambient.weather.thunder", 0.4f, 2.6f); // portal.travel!!!
			System.out.println("Block: " + par3World.getBlockId(par4, par5, par6));
			if(pi > 2.4f){
				pi = 0.4f;
			}
			destroyBlocks(par3World, par4, par5, par6, par2EntityPlayer, par7);
			return true;
		}
        return false;
    }
	
	private void destroyBlocks(World world, int x, int y, int z, EntityPlayer plr, int side){
		if(world.getBlockTileEntity(x, y, z) == null && world.blockExists(x, y, z) && world.getBlockId(x, y, z) != Block.bedrock.blockID){
			System.out.println(side);
			Vec3 facing = plr.getLookVec();
			System.out.println("X: " + facing.xCoord + " Y: " + facing.yCoord + " Z: " + facing.zCoord);
			int rx=-1, ry=-1, rz=-1;
			int width = 3;
			int lenght = 5;
			int id = world.getBlockId(x, y, z);
			int dmg = world.getBlockMetadata(x, y, z);
			System.out.println(dmg);
			ArrayList<ItemStack> dropsx = new ArrayList<ItemStack>();
			for(int f=0;f<lenght;f++){
					for(int w=0;w<width;w++){
						for(int w2=0;w2<width;w2++){
							switch(side){
								case 0:
									rx = x+(w-width/2);
									ry = y+f;
									rz = z+(w2-width/2);
									break;
								case 1:
									rx = x+(w-width/2);
									ry = y-f;
									rz = z+(w2-width/2);
									break;
								case 2:
									rx = x+(w-width/2);
									ry = y+(w2-width/2);
									rz = z+f;
									break;
								case 3:
									rx = x+(w-width/2);
									ry = y+(w2-width/2);
									rz = z-f;
									break;
								case 4:
									rx = x+f;
									ry = y+(w2-width/2);
									rz = z+(w-width/2);
									break;
								case 5:
									rx = x-f;
									ry = y+(w2-width/2);
									rz = z+(w-width/2);
									break;
							}
							
							if(world.getBlockId(rx, ry, rz)==id && world.getBlockMetadata(rx, ry, rz) == dmg){
								Block theblc = Block.blocksList[world.getBlockId(rx, ry, rz)];
								world.setBlockWithNotify(rx, ry, rz, 0);
								//plr.worldObj.spawnParticle("snowballpoof", x+(w-1), y+f, z+(w2-1), 0, 0, 0);
								ArrayList<ItemStack> xc = theblc.getBlockDropped(world, rx, ry, rz, dmg, 0);
								
								for(ItemStack stackxx : xc){
									dropsx.add(stackxx);
								}
								
							}
							
							
						}
					}
				}
				
			for(ItemStack isxc : dropsx){
				EntityItem drop = new EntityItem(world, plr.posX, plr.posY, plr.posZ, isxc);
				drop.delayBeforeCanPickup = 20;
				world.spawnEntityInWorld(drop);
			}
		}
		
			
		
	}
	
	public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving)
    {
		EntityPlayer plr = (EntityPlayer)par3EntityLiving;
		
		Random rand = new Random();
		int x = rand.nextInt(10);
		int quanity = rand.nextInt(4);
		
		if(!plr.worldObj.isRemote){
			
		if(par2EntityLiving instanceof EntityPig){
			
			
			
			if(x==0 && !par2EntityLiving.isChild()){
				if(!plr.capabilities.isCreativeMode){
					par1ItemStack.damageItem(16, par3EntityLiving);
				}
				
				this.dropItem(Item.porkRaw.itemID, 2 + quanity, par2EntityLiving);
				
				//par3EntityLiving.playSound("mob.zombie.remedy", 0.5f, 1.6f);
				plr.worldObj.playSoundAtEntity(plr, "mob.zombie.remedy", 0.5f, 1.6f);
				par2EntityLiving.attackEntityFrom(DamageSource.magic, 100);
				return true;
			}
			
		}
		if(!plr.capabilities.isCreativeMode){
			par1ItemStack.damageItem(1, par3EntityLiving);
		}
		}
        return false;
    }
	
	private void dropItem(int id, int qua, EntityLiving e){
		e.dropItem(id, qua);
	}
	
	public int getDamageVsEntity(Entity par1Entity)
    {
		if(par1Entity instanceof EntityPig){
			return 4;
		}
        return 1;
    }
}
