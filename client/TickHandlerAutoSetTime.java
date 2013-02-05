package leCraft.client;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.server.FMLServerHandler;

public class TickHandlerAutoSetTime implements ITickHandler{

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		if(type.contains(TickType.SERVER)){
			Minecraft mc = FMLClientHandler.instance().getClient();
			System.out.println(mc.theWorld.getTotalWorldTime());
		}
		
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EnumSet<TickType> ticks() {
		// TODO Auto-generated method stub
		return EnumSet.of(TickType.SERVER);
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "AutoTimeSet";
	}

}
