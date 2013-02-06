package leCraft.common.Network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import leCraft.common.Network.Packet.PacketEC2;
import leCraft.common.Network.Packet.PacketElExtractor;

import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;

public enum PacketTypeH {
	ELEX(PacketElExtractor.class);
	
	private Class<? extends PacketEC2> clas;
	
	PacketTypeH(Class<? extends PacketEC2> clas){
		this.clas = clas;
	}
	
	public static PacketEC2 buildPack(byte[] data) {
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		int sel = bais.read();
		DataInputStream dis = new DataInputStream(bais);
		
		PacketEC2 pack = null;
		
		try{
			pack = values()[sel].clas.newInstance();
		}catch(Exception es){
			es.printStackTrace(System.err);
		}
		
		pack.readPop(dis);
		return pack;
	}
	
	public static PacketEC2 buildPack(PacketTypeH pth){
		PacketEC2 pack = null;
		
		try{
			pack = values()[pth.ordinal()].clas.newInstance();
		}catch(Exception es){
			es.printStackTrace(System.err);
		}
		
		return pack;
	}
	
	public static Packet populatePack(PacketEC2 pack){
		byte[] data = pack.pop();
		
		Packet250CustomPayload pack250 = new Packet250CustomPayload();
		pack250.channel = "EC2";
		pack250.data = data;
		pack250.length = data.length;
		pack250.isChunkDataPacket = pack.isChunkDataPack;
		
		return pack250;
	}
}
