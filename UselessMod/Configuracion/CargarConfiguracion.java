package UselessMod.Configuracion;

import java.io.File;
import java.lang.reflect.TypeVariable;
import java.util.Enumeration;
import java.util.Hashtable;

import org.objectweb.asm.Type;

import UselessMod.base;

import static net.minecraftforge.common.Configuration.CATEGORY_GENERAL;

import net.minecraft.util.StringTranslate;
import net.minecraftforge.common.Configuration;

public class CargarConfiguracion {
	public static Configuration config;

	public static void load(File fil) {
		config = new Configuration(fil);
		try {
			config.load();
			Info.initConfig(config);

		} catch (Exception e) {

		} finally {
			config.save();
		}
	}

}
