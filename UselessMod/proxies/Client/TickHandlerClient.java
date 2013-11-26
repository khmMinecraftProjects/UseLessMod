package UselessMod.proxies.Client;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map.Entry;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import UselessMod.base;
import UselessMod.Auxiliares.UpdatePlayerMod;
import UselessMod.Auxiliares.Entitys.EntityPlayerMod;
import UselessMod.Auxiliares.Renders.RenderFirstPerson;
import UselessMod.Auxiliares.Renders.RenderPlayerModBase;
import UselessMod.Auxiliares.Renders.RenderView;
import UselessMod.Configuracion.Datos;
import UselessMod.Items.BotasAntiGrav.Botas;
import UselessMod.Items.MTele.Lanzador;
import UselessMod.Items.MTele.Misil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.main.Main;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.EntityRendererProxy;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.ForgeHooks;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.relauncher.ReflectionHelper;

public class TickHandlerClient implements ITickHandler {
	public RenderPlayerModBase render;
	public float renderTick;
	public Hashtable<String, Lanzador> lanzadores = new Hashtable<String, Lanzador>();

	public TickHandlerClient() {
		render = new RenderPlayerModBase();
		render.setRenderManager(RenderManager.instance);

	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.WORLD, TickType.PLAYER, TickType.RENDER);
	}

	@Override
	public String getLabel() {
		return "TickHandlerClientUseless";
	}

	public void preWorldTick(WorldServer world) {
	}

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		if (type.equals(EnumSet.of(TickType.CLIENT))) {
			if (Minecraft.getMinecraft().theWorld != null) {
				preWorldTick(Minecraft.getMinecraft(),
						Minecraft.getMinecraft().theWorld);
			}
		} else if (type.equals(EnumSet.of(TickType.PLAYER))) {
			prevPlayerTick((World) ((EntityPlayer) tickData[0]).worldObj,
					(EntityPlayer) tickData[0]);
		} else if (type.equals(EnumSet.of(TickType.RENDER))) {
			if (Minecraft.getMinecraft().theWorld != null) {
				preRenderTick(Minecraft.getMinecraft(),
						Minecraft.getMinecraft().theWorld, (Float) tickData[0]); // only
																					// ingame
			}
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		if (type.equals(EnumSet.of(TickType.CLIENT))) {
			if (Minecraft.getMinecraft().theWorld != null) {
				worldTick(Minecraft.getMinecraft(),
						Minecraft.getMinecraft().theWorld);
			}
		} else if (type.equals(EnumSet.of(TickType.PLAYER))) {
			if (tickData[0] instanceof EntityPlayer) {
				playerTick((World) ((EntityPlayer) tickData[0]).worldObj,
						(EntityPlayer) tickData[0]);
			}
		} else if (type.equals(EnumSet.of(TickType.RENDER))) {
			if (Minecraft.getMinecraft().theWorld != null) {
				renderTick(Minecraft.getMinecraft(),
						Minecraft.getMinecraft().theWorld, (Float) tickData[0]); // only
																					// ingame
			}
		}
	}

	public void preWorldTick(Minecraft mc, WorldClient world) {
		double d0 = (double) mc.playerController.getBlockReachDistance();
		mc.objectMouseOver = mc.renderViewEntity.rayTrace(d0, renderTick);
	}

	public void worldTick(Minecraft mc, WorldClient world) {
	}

	public boolean salto = false;
	int temp = 0;

	public void prevPlayerTick(World world, EntityPlayer player) {
		if (player.capabilities.allowFlying) {
			return;
		}
		ItemStack botas = new ItemStack(base.items.get(Datos.getI("idBotas")));
		ItemStack it = player.inventory.armorInventory[0];
		if (it != null && it.isItemEqual(botas)
				&& ((Botas) it.getItem()).getGrav()) {
			player.fallDistance = 0;
			int x = (int) (player.posX);
			int y = (int) (player.posY + 0.6);
			int z = (int) (player.posZ);

			if (salto) {
				player.motionY = -0.2;
				temp++;
				if (temp == 5) {
					salto = false;
					temp = 0;
				}
				return;
			}
			if (isAirbone(player)) {
				player.motionY += 0.2;

			} else {
				player.motionY = 0;
			}
			if (isPressed(Keyboard.KEY_SPACE) && !isAirbone(player)) {
				salto = true;
				temp = 0;
			}

		}

	}

	public static boolean isPressed(int key) {
		if (key < 0) {
			return Mouse.isButtonDown(key + 100);
		}
		return Keyboard.isKeyDown(key);
	}

	public void playerTick(World world, EntityPlayer player) {

	}

	public boolean isAirbone(EntityPlayer player) {
		int x = (int) (player.posX);
		int y = (int) (player.posY + 0.6);
		int z = (int) (player.posZ);
		if (player.worldObj.getBlockMaterial(x, (int) (y), (int) (z)).isSolid()) {
			return false;
		}
		return true;
	}

	public void preRenderTick(Minecraft mc, World world, float renderTick) {
		if (mc.entityRenderer instanceof EntityRenderer
				&& !(mc.entityRenderer instanceof RenderView)) {
			mc.entityRenderer = new RenderView(mc);
		}

		this.renderTick = renderTick;

	}

	public void renderTick(Minecraft mc, World world, float tickData) {
		this.renderTick = tickData;

	}

}