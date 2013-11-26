package UselessMod.proxies;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import UselessMod.base;
import UselessMod.Auxiliares.UpdatePlayerMod;
import UselessMod.Configuracion.Datos;
import UselessMod.Items.BotasAntiGrav.Botas;
import UselessMod.Items.MTele.Lanzador;
import UselessMod.proxies.Client.TickHandlerClient;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerFlyableFallEvent;

public class EventListen {

	@SideOnly(Side.CLIENT)
	@ForgeSubscribe
	public void onSoundLoad(SoundLoadEvent event) {

		// event.manager.soundPoolSounds.addSound("useless:useless"+ ".ogg");

	}

	@SideOnly(Side.CLIENT)
	@ForgeSubscribe
	public void onRenderPlayer(RenderPlayerEvent.Pre event) {
		if (base.proxy.tickHandlerClient.lanzadores
				.containsKey(event.entityPlayer.username)) {
			event.setCanceled(true);
			return;
		}

		ItemStack botas = new ItemStack(base.items.get(Datos.getI("idBotas")));
		if (event.entity instanceof EntityPlayer) {

			EntityPlayer player = event.entityPlayer;

			if (player.inventory.armorInventory[0] != null
					&& player.inventory.armorInventory[0].isItemEqual(botas)) {

				AbstractClientPlayer abs = null;

				abs = (AbstractClientPlayer) player.worldObj
						.getPlayerEntityByName(player.username);

				if (Minecraft.getMinecraft().thePlayer.username
						.equals(abs.username)) {
					base.proxy.tickHandlerClient.render.doRender(abs, 0.0D,
							0.0D, 0.0D, 0,
							base.proxy.tickHandlerClient.renderTick);

					event.setCanceled(true);
					return;
				}

			}

		}

	}

	@SideOnly(Side.CLIENT)
	@ForgeSubscribe
	public void onRenderWorldLast(RenderWorldLastEvent event) {
		/*
		 * if(!base.proxy.tickHandlerClient.renderF.ini){ Minecraft
		 * mc=Minecraft.getMinecraft();
		 * 
		 * base.proxy.tickHandlerClient.renderF.ini();
		 * 
		 * RenderManager.instance.entityRenderMap.put(mc.thePlayer.getClass(),
		 * base.proxy.tickHandlerClient.renderF);
		 * 
		 * }
		 */
	}

}
