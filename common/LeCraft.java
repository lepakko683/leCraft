package leCraft.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import leCraft.client.BlockRendererMelter;
import leCraft.common.Blocks.BlockElementExtractor;
import leCraft.common.Blocks.BlocksLeC;
import leCraft.common.Items.ItemsLeC;
import leCraft.common.containers.CreativeTabEC2;
import leCraft.common.containers.TileEntityRegist;
import leCraft.recipes.InitEC2Recipes;
import leCraft.worldGen.WorldGenLeCraft;
import leCraft.common.Network.PacketHandlerEC2;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@NetworkMod(channels="EC2", clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandlerEC2.class)

@Mod(modid="EC2", name="ElementCraft2", version="a0.1")
public class LeCraft {
	
	@Instance
	private static LeCraft INSTANCE = new LeCraft();
	
	public static final CreativeTabs tabEC2 = new CreativeTabEC2(CreativeTabs.getNextID(), "EC2");
	
	public static Block elExtractor = new BlockElementExtractor(3601, 0, Material.rock).setHardness(1F).setResistance(1F).setBlockName("elementExtractor");
	
	@SidedProxy(clientSide = "le683.ElementCraft.client.ClientProxyEC2", serverSide = "le683.ElementCraft.common.ClientProxyEC2")
	public static CommonProxyEC2 cpec2;
	
	
	@Init
	public void Load(FMLInitializationEvent ev){
		TileEntityRegist.Register();
		GameRegistry.registerBlock(elExtractor, "");
		LanguageRegistry.addName(elExtractor, "Element Extractor");
		LanguageRegistry.instance().addStringLocalization("itemGroup.EC2", "ElementCraft2");
		
		RenderingRegistry.registerBlockHandler(BlockRendererMelter.instance);
		
		WorldGenLeCraft.instance.registerWGen();
		MelterHandler.getInstance().AddRecipes();
		//OreIdsEC2.Init();
		EC2Reference.Init();
		BlocksLeC.Init();
		ItemsLeC.InitNames();
		InitEC2Recipes.instance.Init();
		cpec2.registerRenderThings();
		cpec2.addRarityTypes();
		//cpec2.registerDrawCBTickHandler();
		NetworkRegistry.instance().registerGuiHandler(this, new GuiLoaderEC2());
		
	}
	
	public static LeCraft getInstance(){
		return INSTANCE;
	}
}
