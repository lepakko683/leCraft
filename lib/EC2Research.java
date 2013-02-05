package leCraft.lib;

public class EC2Research {
	public static EC2Research instance = new EC2Research();
	
	public static final String prefix = "Research: ";
	public static final String prefixForgotten = "Forgotten Research: ";
	public static final String prefixLost = "Lost Research: ";
	public static final String prefixUnknown = "Unknown Research";
	public static final String prefixElemental = "Elemental Research";
	
	
	public static final String[] noteTitle = new String[]
		{
			"Nerdgazm's Oinky knife", "Orb of Creeper Detection",
			"Food Bag", "Ore Counting Orb"
		};
	
	public static final String[] [] noteDesc = new String[] []
		{
			{
				"This little pink knife is",
				"made of pork and iron.",
				"If you hit a pig with it",
				"there is a chance of the pig",
				"dying instantly and dropping",
				"more pork than normally."
			},
			{
				"A magical orb that can",
				"see through walls. In",
				"this case it warns you",
				"about creeper beging near."
			}
			
		};
}
