package UselessMod.Configuracion;

import java.awt.List;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import UselessMod.Auxiliares.Registros;
import UselessMod.Blocks.Ambar;
import UselessMod.Blocks.BlockTransparente;
import UselessMod.Items.BastonMoises.BastonMoises;
import UselessMod.Items.BombAmbar.BombAmbar;
import UselessMod.Items.BotasAntiGrav.Botas;
import UselessMod.Items.MTele.Lanzador;
import UselessMod.Items.PiesJesus.PiesJesus;
import UselessMod.Items.RayoZeus.RayoZeus;

import org.lwjgl.input.Keyboard;
import org.objectweb.asm.Type;

public class Info extends Registros {
	public static void initConfig(Configuration confi) {
		config = confi;
		addConf("Idioma", "CATEGORY_GENERAL", "ES");

		addConf("idBaston", "IDS", 558);
		addConf("idTransparente", "IDS", 559);
		addConf("idAmbar", "IDS", 560);
		addConf("idBomba", "IDS", 561);
		addConf("idPie", "IDS", 562);
		addConf("idRayo", "IDS", 563);
		addConf("idBotas", "IDS", 564);
		addConf("idLanzador", "IDS", 565);

		addConf("Baston_TamX", "Baston", 2);
		addConf("Baston_TamY", "Baston", 20);
		addConf("Baston_TamZ", "Baston", 800);

		addConf("Ambar_Radio", "Ambar", 20);
		addConf("Ambar_Tiempo", "Ambar", 20);
		addConf("Ambar_Cubo", "Ambar", true);
		addConf("Ambar_blockHardness", "Ambar", (double) 50);

		addConf("Pies_Armor", "Pies", 0);
		addConf("Pies_Empuje", "Pies", 2);
		addConf("Pies_Durability", "Pies", 50);

		addConf("Rayo_key", "Rayo", Keyboard.KEY_F);

		addConf("Botas_key", "Botas", Keyboard.KEY_G);
		addConf("Botas_Velocidad", "Botas", 18);

	}

	public Hashtable initItems() throws NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		creaItem(BombAmbar.class, Datos.getI("idBomba"));
		creaItem(BastonMoises.class, Datos.getI("idBaston"));
		creaItem(PiesJesus.class, Datos.getI("idPie"));
		creaItem(RayoZeus.class, Datos.getI("idRayo"));
		creaItem(Botas.class, Datos.getI("idBotas"));
		creaItem(Lanzador.class, Datos.getI("idLanzador"));
		return items;

	}

	public Hashtable initBlocks() throws NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		creaBlock(Ambar.class, Datos.getI("idAmbar"));
		creaBlock(BlockTransparente.class, Datos.getI("idTransparente"));
		return blocks;
	}

	public void initMobs() throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

	}

}
