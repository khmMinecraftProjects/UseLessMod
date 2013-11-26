package UselessMod.proxies.Server;

import java.io.IOException;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map.Entry;

import UselessMod.base;
import UselessMod.Auxiliares.Entitys.EntityPlayerMod;
import UselessMod.Configuracion.Datos;
import UselessMod.Items.BotasAntiGrav.Botas;
import UselessMod.Items.MTele.Lanzador;
import UselessMod.Items.MTele.Misil;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet131MapData;
import net.minecraft.world.WorldServer;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;

public class TickHandlerServer implements ITickHandler {
	public long clock;
	public Hashtable<String, Lanzador> lanzadores = new Hashtable<String, Lanzador>();

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {

		if (type.equals(EnumSet.of(TickType.WORLD))) {
			preWorldTick((WorldServer) tickData[0]);
		} else if (type.equals(EnumSet.of(TickType.PLAYER))) {
			prePlayerTick(
					(WorldServer) ((EntityPlayerMP) tickData[0]).worldObj,
					(EntityPlayerMP) tickData[0]);
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		if (type.equals(EnumSet.of(TickType.WORLD))) {
			worldTick((WorldServer) tickData[0]);
		} else if (type.equals(EnumSet.of(TickType.PLAYER))) {
			playerTick((WorldServer) ((EntityPlayerMP) tickData[0]).worldObj,
					(EntityPlayerMP) tickData[0]);
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.WORLD, TickType.PLAYER);
	}

	@Override
	public String getLabel() {
		return "TickHandlerServerUseLess";
	}

	public void worldTick(WorldServer world) {
		if (clock != world.getWorldTime()) {
			clock = world.getWorldTime();
		}
	}

	public void preWorldTick(WorldServer world) {

	}

	public void prePlayerTick(WorldServer world, EntityPlayerMP player) {

	}

	public void playerTick(WorldServer world, EntityPlayerMP player) {
		ItemStack item = new ItemStack(base.items.get(Datos.getI("idBotas")));
		ItemStack it = player.inventory.armorInventory[0];
		if (it != null && it.isItemEqual(item)
				&& ((Botas) it.getItem()).getGrav()) {
			int x = (int) (player.posX + 0.99 * (player.posX / Math
					.abs(player.posX)));
			int y = (int) (player.posY + 1.8);
			int z = (int) (player.posZ + 0.99 * (player.posZ / Math
					.abs(player.posZ)));
			if (player.worldObj.getBlockMaterial(x, (int) (y), (int) (z))
					.isSolid()) {
				player.isAirBorne = true;
			} else {
				player.isAirBorne = false;
				player.motionY = -0.9016000175476074;

			}

		}

	}

}