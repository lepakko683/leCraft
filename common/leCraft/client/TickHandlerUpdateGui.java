package leCraft.client;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class TickHandlerUpdateGui implements ITickHandler {

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		Minecraft mc = FMLClientHandler.instance().getClient();
		World wo = mc.theWorld;
		EntityPlayer plr = mc.thePlayer;
		
		if(type.contains(TickType.CLIENTGUI)){
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENTGUI);
	}

	@Override
	public String getLabel() {
		return null;
	}

}
