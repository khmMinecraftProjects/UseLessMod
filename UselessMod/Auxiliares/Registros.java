package UselessMod.Auxiliares;

import static net.minecraftforge.common.Configuration.CATEGORY_GENERAL;

import java.awt.Color;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import UselessMod.Blocks.Ambar;
import UselessMod.Blocks.BlockTransparente;
import UselessMod.Configuracion.Datos;
import UselessMod.Configuracion.Info;
import UselessMod.Items.BastonMoises.BastonMoises;
import UselessMod.Items.BombAmbar.BombAmbar;
import UselessMod.Items.PiesJesus.PiesJesus;
import UselessMod.Items.RayoZeus.RayoZeus;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.util.Icon;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Registros {
	protected static Configuration config;
	protected static Hashtable<Integer, Item> items = new Hashtable<Integer, Item>();
	protected static Hashtable<Integer, Block> blocks = new Hashtable<Integer, Block>();

	public static void creaItem(Class<? extends Item> clas, int id)
			throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Constructor it = clas.getConstructors()[0];

		Item item = (Item) ((Item) it.newInstance(id)).setUnlocalizedName(clas
				.getSimpleName());
		items.put(id, item);
	}

	public static void creaBlock(Class<? extends Block> clas, int id)
			throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		Constructor it = clas.getConstructors()[0];
		Block block = (Block) ((Block) it.newInstance(id))
				.setUnlocalizedName(clas.getSimpleName());
		GameRegistry.registerBlock(block, clas.getSimpleName());
		blocks.put(id, block);
	}

	public void creaMob(Class<? extends Entity> mob, String name, int id,
			int idM, Color a, Color b) {
		EntityRegistry.registerModEntity(mob, name, idM, this, 80, 3, true);
		if (id != 0) {
			EntityList.IDtoClassMapping.put(id, mob);
			EntityList.entityEggs.put(id,
					new EntityEggInfo(id, a.getRGB(), b.getRGB()));
		}
		LanguageRegistry.instance().addStringLocalization(
				"entity." + Datos.nameMod + "." + name + ".name", name);

	}

	public static Icon RegisterIcons(IconRegister reg, String name) {
		return reg.registerIcon(Datos.nameMod.toLowerCase() + ":" + name);
	}

	public static void addConf(String s, String p, String cn) {
		Datos.add(s, config.get(s, p, cn).getString());
	}

	public static void addConf(String s, String p, int cn) {
		Datos.add(s, config.get(s, p, cn).getInt());

	}

	public static void addConf(String s, String p, boolean cn) {
		Datos.add(s, config.get(s, p, cn).getBoolean(true));
	}

	public static void addConf(String s, String p, double cn) {
		Datos.add(s, config.get(s, p, cn).getDouble(0));
	}

	public Hashtable<Integer, Item> initItems() throws NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		return items;
	}

	public Hashtable<Integer, Block> initBlocks() throws NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		return blocks;
	}

	public void initMobs() throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

	}

}
