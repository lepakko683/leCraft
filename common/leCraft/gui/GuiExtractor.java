package leCraft.gui;


import leCraft.common.containers.ContainerElementExtractor;
import leCraft.common.entity.tile.TileEntityElementExtractor;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;


public class GuiExtractor extends GuiContainer {

	public GuiExtractor(InventoryPlayer inventory, TileEntityElementExtractor tileE) {
		super(new ContainerElementExtractor(inventory, (TileEntityElementExtractor)tileE));
	}
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		fontRenderer.drawString("Element Extractor", 8, 8, 4210752);
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		
		int texture = mc.renderEngine.getTexture("/ElementCraft2/res/guiElementExtractor.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(texture);
		int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		
		
	}
	protected void drawGuiUpdateLayer(){
		System.out.println("ItWorks");
	}

}
