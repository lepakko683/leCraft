package leCraft.common.Blocks;

import net.minecraft.block.material.Material;

public class BlockLeCWood extends LeCMBlock {

	public BlockLeCWood(int id, int texIndex, Material par3Material) {
		super(id, texIndex, par3Material);
		this.setTextureFile("/ElementCraft2/res/BlocksFarming.png");
		
	}
	
	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public int getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
    	return par1 <= 1 ? par2 + 16 : par1 > 1 ? par2 : par2*16;
    }
	
	/**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return par1;
    }
	
	

}
