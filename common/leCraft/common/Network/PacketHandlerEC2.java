package leCraft.common.Network;

import leCraft.common.Network.Packet.PacketEC2;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;


public class PacketHandlerEC2 implements IPacketHandler{

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		
		PacketEC2 pack = PacketTypeH.buildPack(packet.data);
		
		pack.exec(manager, player);
	}

}
