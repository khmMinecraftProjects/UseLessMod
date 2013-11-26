package UselessMod.proxies.Server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import UselessMod.base;
import UselessMod.Items.BombAmbar.EntityBomb;
import UselessMod.proxies.Client.TickHandlerClient;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.src.ModLoader;

public class CommonProxy {

	public void registerRenderThings() {
	}

	public void postInit() {

	}

	public int addArmors(String s) {
		return 0;
	}

	public void initTickHandlers() {
		tickHandlerServer = new TickHandlerServer();
		TickRegistry.registerTickHandler(tickHandlerServer, Side.SERVER);
	}

	public TickHandlerClient tickHandlerClient;
	public TickHandlerServer tickHandlerServer;

}
