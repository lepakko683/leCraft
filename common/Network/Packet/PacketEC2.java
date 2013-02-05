package leCraft.common.Network.Packet;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import leCraft.common.Network.PacketTypeH;

import net.minecraft.network.INetworkManager;

import cpw.mods.fml.common.INetworkHandler;
import cpw.mods.fml.common.network.Player;

public class PacketEC2 {
	public PacketTypeH packType;
	public boolean isChunkDataPack;
	
	public PacketEC2(PacketTypeH packT, boolean isChunkDataPacket) {
		this.packType = packT;
		this.isChunkDataPack = isChunkDataPacket;
	}
	
	public byte[] pop() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		
		try{
			dos.writeByte(packType.ordinal());
			this.writeData(dos);
		}catch(Exception es){
			es.printStackTrace(System.err);
		}
		
		return baos.toByteArray();
	}
	
	public void readPop(DataInputStream data){
		try{
			this.readData(data);
		}catch(Exception es){
			es.printStackTrace(System.err);
		}
	}
	
	public void readData(DataInputStream data) throws IOException{}
	
	public void writeData(DataOutputStream dout) throws IOException{}
	
	public void exec(INetworkManager netW, Player player){}
}
