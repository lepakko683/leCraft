package leCraft.common;

public enum EnumElementStorGemType {
	WATERG(0),
	FIREG(1),
	EARTHG(2),
	MAGICG(3);
	
	private final int gemType;
	
	private EnumElementStorGemType(int type)
    {
        this.gemType = type;

    }
	
	public int getGemType(){
		return gemType;
	}
}
