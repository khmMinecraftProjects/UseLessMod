package UselessMod.proxies.Server;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import UselessMod.base;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandlerServer implements IPacketHandler {
	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player plyr) {
		if (packet.channel != "UseLess") {
			return;
		}
		EntityPlayerMP player = (EntityPlayerMP) plyr;
		DataInputStream stream = new DataInputStream(new ByteArrayInputStream(
				packet.data));

		try {
			int id = stream.readByte();
			switch (id) {
			case 0:
				double x = stream.readDouble(),
				y = stream.readDouble(),
				z = stream.readDouble();
				player.setPosition(x, y, z);

				if (!player.worldObj.isRemote) {
					return;
				}

				base.proxy.tickHandlerServer.lanzadores.put(player.username,
						null);
				break;

			default:
				break;
			}
		} catch (IOException e) {
		}
	}
}
