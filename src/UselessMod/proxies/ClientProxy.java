package UselessMod.proxies;

import java.io.File;

import UselessMod.base;
import UselessMod.Auxiliares.Renders.RenderEntityThrowable;
import UselessMod.Configuracion.Datos;
import UselessMod.Items.BombAmbar.BombAmbar;
import UselessMod.Items.BombAmbar.EntityBomb;


import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;

import net.minecraft.item.Item;
import net.minecraft.src.ModLoader;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;


public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderThings(){
		RenderingRegistry.registerEntityRenderingHandler(EntityBomb.class, new RenderEntityThrowable(base.Bomba));

	}
	@Override
	public int addArmors(String s) {
		return ModLoader.addArmor(s);
	}
	
}
