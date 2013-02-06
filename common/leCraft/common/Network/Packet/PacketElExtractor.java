package leCraft.common.Network.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import leCraft.common.Network.PacketTypeH;
import leCraft.common.containers.ContainerElementExtractor;

import cpw.mods.fml.common.network.Player;


import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet;

public class PacketElExtractor extends PacketEC2 {
	
	public int x, y, z;
	public int w, f, e, m; //elements
	public int guiId;

	public PacketElExtractor() {
		super(PacketTypeH.ELEX, false);
	}
	public PacketElExtractor(String player, int x, int y, int z, int wa, int fi, int ea, int ma, int id) {
		super(PacketTypeH.ELEX, false);
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = wa;
		this.f = fi;
		this.e = ea;
		this.m = ma;
		this.guiId = id;
	}
	@Override
	public void writeData(DataOutputStream dout) throws IOException{
		dout.write(x);
		dout.write(y);
		dout.write(z);
		
		dout.write(w);
		dout.write(f);
		dout.write(e);
		dout.write(m);
		dout.write(guiId);
	}
	@Override
	public void readData(DataInputStream data) throws IOException{
		this.x = data.readInt();
		this.y = data.readInt();
		this.z = data.readInt();
		
		this.w = data.readInt();
		this.f = data.readInt();
		this.e = data.readInt();
		this.m = data.readInt();
		this.guiId = data.readInt();
	}

	@Override
	public void exec(INetworkManager netW, Player player){
		EntityClientPlayerMP plr = (EntityClientPlayerMP) player;
		
		if(plr.openContainer != null && plr.openContainer.windowId == this.guiId && plr.openContainer instanceof ContainerElementExtractor){
			ContainerElementExtractor con = (ContainerElementExtractor)plr.openContainer;
			con.updateValues(this);
		}
		
	}

}
