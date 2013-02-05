package leCraft.common.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import leCraft.common.LeCraft;

public class LeCMBlock extends Block {

	public LeCMBlock(int id, int texIndex, Material par3Material) {
		super(id, texIndex, par3Material);
		this.setCreativeTab(LeCraft.tabEC2);

	}

}
