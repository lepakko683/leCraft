package leCraft.client;

import java.util.EnumSet;

import leCraft.common.Items.ItemsLeC;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class TickHandlerRenderChunkBs implements ITickHandler {

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		//System.out.println("Tick!");

		Minecraft mc = FMLClientHandler.instance().getClient();
		RenderEngine ren = mc.renderEngine;
		EntityPlayer plr = mc.thePlayer;
		World wrld = mc.theWorld;
		ItemStack cIStack = null;
		
		if(type.contains(TickType.RENDER)){
			if(plr != null){
				cIStack = plr.inventory.getCurrentItem();
				/*
				if((cIStack != null) && (mc.inGameHasFocus) && (cIStack.itemID == ItemsLeC.oreCounter.itemID)){
					double x=plr.posX;
					double y=plr.posY;
					double z=plr.posZ;
					DrawChunkBoundsHandler.drawChunkBounds(x,y,z, ren);
					//System.out.println("Tick!");
				}*/
			}
		}
		
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT, TickType.CLIENTGUI, TickType.RENDER);
	}

	@Override
	public String getLabel() {
		return "EC2TH1";
	}

}
