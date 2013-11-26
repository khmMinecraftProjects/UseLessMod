package UselessMod.proxies;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.client.multiplayer.NetClientHandler;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetServerHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet131MapData;
import cpw.mods.fml.common.network.ITinyPacketHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MapPacketHandler implements ITinyPacketHandler {

	@Override
	public void handle(NetHandler handler, Packet131MapData mapData) {
		int id = mapData.uniqueID;
		if (handler instanceof NetServerHandler) {
			handleServerPacket((NetServerHandler) handler, mapData.uniqueID,
					mapData.itemData, (EntityPlayerMP) handler.getPlayer());
		} else {
			handleClientPacket((NetClientHandler) handler, mapData.uniqueID,
					mapData.itemData);
		}
	}

	public void handleServerPacket(NetServerHandler handler, short id,
			byte[] data, EntityPlayerMP player) {
		DataInputStream stream = new DataInputStream(new ByteArrayInputStream(
				data));
		try {
			switch (id) {
			case 0: {
				byte key = stream.readByte();
				break;
			}
			}
		} catch (IOException e) {
		}

	}

	@SideOnly(Side.CLIENT)
	public void handleClientPacket(NetClientHandler handler, short id,
			byte[] data) {
		DataInputStream stream = new DataInputStream(new ByteArrayInputStream(
				data));
		try {
			switch (id) {
			case 0: {
				String user = stream.readUTF();

				try {
					Class prevClz = Class.forName(stream.readUTF());
					Class nextClz = Class.forName(stream.readUTF());

				} catch (Exception e) {
				}

				break;
			}
			}
		} catch (IOException e) {
		}
	}
}