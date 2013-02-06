package leCraft.common.Items;

import leCraft.common.EC2Reference;
import leCraft.common.EnumElementStorGemType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemsLeC {
	//public static Item element = new ItemElement(26830).setTextureFile("/ElementCraft2/res/Items.png");
	public static Item elementStorGemW = new ItemElementStorage(26831, EnumElementStorGemType.WATERG.getGemType()).setItemName("wsg").setTextureFile("/ElementCraft2/res/Items.png").setIconIndex(4);
	public static Item elementStorGemF = new ItemElementStorage(26832, EnumElementStorGemType.FIREG.getGemType()).setItemName("fsg").setTextureFile("/ElementCraft2/res/Items.png").setIconIndex(5);
	public static Item elementStorGemE = new ItemElementStorage(26833, EnumElementStorGemType.FIREG.getGemType()).setItemName("esg").setTextureFile("/ElementCraft2/res/Items.png").setIconIndex(6);
	public static Item elementStorGemM = new ItemElementStorage(26834, EnumElementStorGemType.FIREG.getGemType()).setItemName("msg").setTextureFile("/ElementCraft2/res/Items.png").setIconIndex(7);
	public static Item crusher = new ItemToolCrusher(26835).setItemName("crusher").setTextureFile("/ElementCraft2/res/Items.png").setIconIndex(8);
	public static Item ec2mat = new ItemMaterialEC2(26836).setItemName("ec2material");
	//public static Item oreCounter = new ItemOreCounterEC2(26837).setItemName("orec").setTextureFile("/ElementCraft2/res/Items.png").setIconIndex(14);
	//public static Item foodBag = new ItemFoodBag(26838).setItemName("fob").setIconIndex(15);
	//public static Item notebookEC2 = new ItemNotebook(26838).setItemName("notbokec2");
	//public static Item reseNote = new ItemResearchNote(26839).setItemName("resNot");
	
	
	//Items through research
	public static Item nerdgazmsOK = new ItemNerdgazmsOinkyKnife(26840).setItemName("oinkknife").setIconIndex(16).setTextureFile("/ElementCraft2/res/Items.png");
	public static Item harvres = new ItemHarvestResult(26841).setItemName("lecharvres");
	public static void InitNames(){
		LanguageRegistry.addName(elementStorGemW, "Water Storage Gem");
		LanguageRegistry.addName(elementStorGemF, "Fire Storage Gem");
		LanguageRegistry.addName(elementStorGemE, "Earth Storage Gem");
		LanguageRegistry.addName(elementStorGemM, "Magic Storage Gem");
		LanguageRegistry.addName(crusher, "Crusher");
		//LanguageRegistry.addName(oreCounter, "Ore Counting Orb");
		//LanguageRegistry.addName(foodBag, "Food Bag");
		LanguageRegistry.addName(nerdgazmsOK, "Nerdgazm's Oinky Knife");

		for(int i1=0;i1<ItemMaterialEC2.subtypeCount;i1++){
			LanguageRegistry.addName(new ItemStack(ec2mat, 1, i1), EC2Reference.getMaterialName(i1));
		}
		for(int i2=0;i2<ItemHarvestResult.names.size();i2++){
			LanguageRegistry.addName(new ItemStack(harvres, 1, i2), ItemHarvestResult.names.get(i2));
		}
		/*
		LanguageRegistry.addName(new ItemStack(element, 1, 0), "Water Element");
		LanguageRegistry.addName(new ItemStack(element, 1, 1), "Fire Element");
		LanguageRegistry.addName(new ItemStack(element, 1, 2), "Earth Element");
		LanguageRegistry.addName(new ItemStack(element, 1, 3), "Magic Element");
		*/
	}
}
