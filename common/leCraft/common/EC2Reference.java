package leCraft.common;

import java.util.HashMap;
import java.util.Map;

public class EC2Reference {
	
	public static final String mDustGlass = "Glass Dust";
	public static final String mDustEnder = "Ender Dust";
	public static final String mDustCrystalline = "Crystalline Dust";
	public static final String mColorlessChunk = "Colorless Chunk";
	public static final String mCrystalline = "Crystalline";

	static Map<Integer, String> matName = new HashMap<Integer, String>();
	
	public static void Init(){
		matName.put(0, "Glass Dust");
		matName.put(1, "Ender Dust");
		matName.put(2, "Crystalline Dust");
		matName.put(3, "Colorless Chunk");
		matName.put(4, "Crystalline");
	}
	
	public static String getMaterialName(int par1){
		return matName.get(par1);
	}
	
}
