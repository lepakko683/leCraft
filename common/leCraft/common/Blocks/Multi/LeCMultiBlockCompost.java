package leCraft.common.Blocks.Multi;

import leCraft.common.Blocks.LeCBlockPos;
import leCraft.common.Blocks.Multi.LeCBLayout.BLayoutShape;
import leCraft.misc.LeCStruckt;
import net.minecraft.world.World;

public class LeCMultiBlockCompost extends LeCMBlockStruckture {
	int partMeta=2;

	@Override
	public boolean canCreate(World wo, LeCStruckt st, LeCBlockPos t) {
		if(st.equals(LeCStruckt.COMPOST)){
			for(int x=-1;x<2;x++){
				for(int y=-1;y<2;y++){
					for(int z=-1;z<2;z++){
						if(wo.getBlockId(x+t.posX(), y+t.posY(), z+t.posZ())==partId && wo.getBlockMetadata(x+t.posX(), y+t.posY(), z+t.posZ())==partMeta){
							this.parts.add(new LeCBlockPos(x+t.posX(), y+t.posY(), z+t.posZ()));
						}
					}
				}
			}
			if(this.parts.size()==8 && LeCBLayout.isValidShape(BLayoutShape.CUBE, this.parts)){
				return true;
			}else{
				parts.clear();
				return false;
			}
		}
		return false;
	}

	@Override
	public void create() {
		this.isBuilt=true;
	}

}
