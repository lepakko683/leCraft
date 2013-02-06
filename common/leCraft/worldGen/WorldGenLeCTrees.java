package leCraft.worldGen;

import java.util.Random;

import leCraft.common.Blocks.BlocksLeC;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenLeCTrees implements IWorldGenerator {
	
	/**The lower the value is, that higher the change to gen is*/
	private final int genChangePerChunk = 1;
	
	private final int minTreeHeight;
	
	private final int treeWidth;
	
	private final int leavesMeta;
	
	private final int woodMeta;
	
	private final int hDiff;
	
	public WorldGenLeCTrees(int height, int width, int leMeta, int woMeta, int diff){
		this.minTreeHeight = height;
		this.treeWidth = width;
		this.leavesMeta = leMeta;
		this.woodMeta = woMeta;
		this.hDiff = diff;
		
	}

	public boolean generate(World world, Random rand, int x, int y, int z) {
		int tHeight = this.minTreeHeight + rand.nextInt(hDiff+1);
		
		boolean canGrow=true;
		
		if(tHeight+y < 250){
			int wS=(this.treeWidth-1)/2;
			int cX;
			int cY;
			int cZ;
			int idWood = BlocksLeC.lecwood.blockID;
			int idLeaves = BlocksLeC.lecleaves.blockID;
			
			//TODO: check if valid place to grow
			
			for(cX=x-wS;cX<x+wS+1;cX++){
				for(cY=y;cY<y+tHeight;cY++){
					for(cZ=z-wS;cZ<z+wS+1;cZ++){
						if(cY>tHeight+y-5 && cY<tHeight+y-2){
							world.setBlockAndMetadataWithNotify(cX, cY, cZ, idLeaves, leavesMeta);
						
						}else if(cY>tHeight+y-3 && cX<x+wS && cX>x-wS && cZ<z+wS && cZ>z-wS){
							world.setBlockAndMetadataWithNotify(cX, cY, cZ, idLeaves, leavesMeta);
						}
						world.setBlockAndMetadataWithNotify(x, cY, z, idWood, woodMeta);
					}
				}
			}
			world.setBlockAndMetadataWithNotify(x, y+tHeight-1, z, idLeaves, leavesMeta);
			world.setBlockAndMetadataWithNotify(x-wS, y+tHeight-3, z-wS, 0, 0);
			world.setBlockAndMetadataWithNotify(x-wS, y+tHeight-3, z+wS, 0, 0);
			world.setBlockAndMetadataWithNotify(x+wS, y+tHeight-3, z-wS, 0, 0);
			world.setBlockAndMetadataWithNotify(x+wS, y+tHeight-3, z+wS, 0, 0);
			
			world.setBlockAndMetadataWithNotify(x-wS+1, y+tHeight-1, z-wS+1, 0, 0);
			world.setBlockAndMetadataWithNotify(x-wS+1, y+tHeight-1, z+wS-1, 0, 0);
			world.setBlockAndMetadataWithNotify(x+wS-1, y+tHeight-1, z-wS+1, 0, 0);
			world.setBlockAndMetadataWithNotify(x+wS-1, y+tHeight-1, z+wS-1, 0, 0);
			
			return true;
		}
		return false;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		if(random.nextInt(genChangePerChunk)==0){
			int tHeight = this.minTreeHeight + random.nextInt(hDiff+1);
			int x = 16*chunkX+random.nextInt(15);
			int z = 16*chunkZ+random.nextInt(15);
			int y = world.getFirstUncoveredBlock(x, z);
			
			if(tHeight+y < 250 && BlocksLeC.lecsapling.canBlockStay(world, x, y, z)){
				System.out.println("Generated tree at: " + x + " " + y + " " + z);
				
				int wS=(this.treeWidth-1)/2;
				int cX;
				int cY;
				int cZ;
				int idWood = BlocksLeC.lecwood.blockID;
				int idLeaves = BlocksLeC.lecleaves.blockID;
				
				//TODO: check if valid place to grow
				
				for(cX=x-wS;cX<x+wS+1;cX++){
					for(cY=y;cY<y+tHeight;cY++){
						for(cZ=z-wS;cZ<z+wS+1;cZ++){
							if(cY>tHeight+y-5 && cY<tHeight+y-2){
								world.setBlockAndMetadataWithNotify(cX, cY, cZ, idLeaves, leavesMeta);
							
							}else if(cY>tHeight+y-3 && cX<x+wS && cX>x-wS && cZ<z+wS && cZ>z-wS){
								world.setBlockAndMetadataWithNotify(cX, cY, cZ, idLeaves, leavesMeta);
							}
							world.setBlockAndMetadataWithNotify(x, cY, z, idWood, woodMeta);
						}
					}
				}
				world.setBlockAndMetadataWithNotify(x, y+tHeight-1, z, idLeaves, leavesMeta);
				world.setBlockAndMetadataWithNotify(x-wS, y+tHeight-3, z-wS, 0, 0);
				world.setBlockAndMetadataWithNotify(x-wS, y+tHeight-3, z+wS, 0, 0);
				world.setBlockAndMetadataWithNotify(x+wS, y+tHeight-3, z-wS, 0, 0);
				world.setBlockAndMetadataWithNotify(x+wS, y+tHeight-3, z+wS, 0, 0);
				
				world.setBlockAndMetadataWithNotify(x-wS+1, y+tHeight-1, z-wS+1, 0, 0);
				world.setBlockAndMetadataWithNotify(x-wS+1, y+tHeight-1, z+wS-1, 0, 0);
				world.setBlockAndMetadataWithNotify(x+wS-1, y+tHeight-1, z-wS+1, 0, 0);
				world.setBlockAndMetadataWithNotify(x+wS-1, y+tHeight-1, z+wS-1, 0, 0);
				
			}
		}
		//System.out.println("Failed");
	}

}
