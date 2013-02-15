package leCraft.common.Blocks;

import leCraft.common.entity.tile.TileEntityCompost;
import leCraft.common.entity.tile.TileEntityMBPart;
import leCraft.lib.LeCFarmingHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BlocksLeC {
	public static Block melter = new BlockMelter(3602, Material.rock).setBlockName("mltr").setHardness(1F).setResistance(3F).setStepSound(Block.soundStoneFootstep);
	public static Block lecwood = new BlockLeCWood(3603, 0, Material.wood).setBlockName("mlcwd").setHardness(1F).setResistance(1F).setStepSound(Block.soundWoodFootstep);
	public static Block lecleaves = new BlockLeCLeaves(3604, 2).setBlockName("mlclv").setHardness(1F).setResistance(1F).setStepSound(Block.soundGrassFootstep);
	public static Block lecfruits = new BlockLeCFruit(3605, 4*16).setBlockName("mlcfr").setHardness(0.1F).setResistance(0.1F).setStepSound(Block.soundGrassFootstep);
	public static Block lecsapling = new BlockLeCSapling(3606, 6*16).setBlockName("mlcsp").setHardness(0.1F).setResistance(0.1F).setStepSound(Block.soundGrassFootstep);
	public static Block leccrops = new BlockLeCCrop(3607).setBlockName("mlccr").setHardness(0.1F).setResistance(0.1F).setStepSound(Block.soundGrassFootstep);
	public static Block lecmutipart = new BlockLeCMultiPart(3608, 2, Material.rock).setBlockName("mlcmp").setStepSound(Block.soundStoneFootstep);
	
	public static void Init(){
		GameRegistry.registerBlock(melter, "meltar");
		GameRegistry.registerBlock(lecwood, "lecwod");
		GameRegistry.registerBlock(lecleaves, "leclvs");
		GameRegistry.registerBlock(lecfruits, "lecfrt");
		GameRegistry.registerBlock(lecsapling, "lecsap");
		GameRegistry.registerBlock(leccrops, "leccrp");
		GameRegistry.registerBlock(lecmutipart, "lecmup");
		
		LanguageRegistry.addName(melter, "Melter");
		LanguageRegistry.addName(lecwood, LeCFarmingHelper.TreeTypes[0] + " Tree Wood");
		LanguageRegistry.addName(lecleaves, LeCFarmingHelper.TreeTypes[0] + " Tree Leaves");
		LanguageRegistry.addName(lecfruits, LeCFarmingHelper.TreeTypes[0]);
		LanguageRegistry.addName(lecsapling, "Banhammer!");
		LanguageRegistry.addName(leccrops, "Crops");
		LanguageRegistry.addName(lecmutipart, "Parts!");
		
		TileEntity.addMapping(TileEntityCompost.class, "compostEntity");
		TileEntity.addMapping(TileEntityMBPart.class, "lecmbpart");
	}
}
