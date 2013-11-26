package UselessMod.proxies.Client;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

import UselessMod.base;
import UselessMod.Auxiliares.Entitys.EntityPlayerMod;
import UselessMod.Auxiliares.Renders.RenderEntityThrowable;
import UselessMod.Auxiliares.Renders.RenderEnte;
import UselessMod.Auxiliares.Renders.RenderPlayerMod;

import UselessMod.Configuracion.Datos;
import UselessMod.Items.BombAmbar.BombAmbar;
import UselessMod.Items.BombAmbar.EntityBomb;
import UselessMod.Items.MTele.Misil;
import UselessMod.Items.MTele.ModelMisil;
import UselessMod.proxies.Server.CommonProxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
import net.minecraft.src.ModLoader;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderThings() {
		RenderingRegistry
				.registerEntityRenderingHandler(
						EntityBomb.class,
						new RenderEntityThrowable(base.items.get(Datos
								.getI("idBomba"))));

		RenderingRegistry.registerEntityRenderingHandler(EntityPlayerMod.class,
				new RenderPlayerMod());

		RenderingRegistry.registerEntityRenderingHandler(Misil.class,
				new RenderEnte(new ModelMisil(), "misil"));

	}

	@Override
	public void postInit() {
		super.postInit();

	}

	@Override
	public int addArmors(String s) {
		return ModLoader.addArmor(s);
	}

	@Override
	public void initTickHandlers() {
		super.initTickHandlers();
		tickHandlerClient = new TickHandlerClient();
		TickRegistry.registerTickHandler(tickHandlerClient, Side.CLIENT);
	}

}
