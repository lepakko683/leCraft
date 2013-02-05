package leCraft.client;

import leCraft.common.CommonProxyEC2;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.item.EnumRarity;
import net.minecraftforge.client.EnumHelperClient;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxyEC2 extends CommonProxyEC2
{
	@Override
    public void registerRenderThings()
    {
     MinecraftForgeClient.preloadTexture("/ElementCraft2/res/Blocks.png");
     MinecraftForgeClient.preloadTexture("/ElementCraft2/res/Items.png");
     MinecraftForgeClient.preloadTexture("/ElementCraft2/res/guiElementExtractor.png");
     MinecraftForgeClient.preloadTexture("/ElementCraft2/res/BlocksFarming.png");
     MinecraftForgeClient.preloadTexture("/ElementCraft2/res/BlocksCrops.png");
     
    }
	@Override
	public void registerDrawChunkBoundsHandler(){
		MinecraftForge.EVENT_BUS.register(new DrawChunkBoundsHandler());
	}
	@Override
	public void registerDrawCBTickHandler(){
		//TickRegistry.registerTickHandler(new TickHandlerRenderChunkBs(), Side.CLIENT);
	}
	@Override
	public EnumRarity getRarity(String rarity){
		for(EnumRarity ra : EnumRarity.class.getEnumConstants()){
			if(ra.name().equals(rarity)){
				return ra;
			}
		}
		return EnumRarity.common;
	}
	
	@Override
	public void addRarityTypes(){
		EnumHelperClient.addRarity("OINK", 12, "Oink");
		EnumHelperClient.addRarity("EPIC", 11, "Epic");
	}
	
}
