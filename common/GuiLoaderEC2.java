package leCraft.common;

import leCraft.common.containers.ContainerElementExtractor;
import leCraft.common.entity.tile.TileEntityElementExtractor;
import leCraft.gui.GuiExtractor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiLoaderEC2 implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileE = world.getBlockTileEntity(x, y, z);
		if(tileE instanceof TileEntityElementExtractor){
			return new ContainerElementExtractor(player.inventory, (TileEntityElementExtractor) tileE);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileE = world.getBlockTileEntity(x, y, z);
		
		if(tileE instanceof TileEntityElementExtractor){
            return new GuiExtractor(player.inventory, (TileEntityElementExtractor) tileE);
		}
		return null;
	}
	
}
