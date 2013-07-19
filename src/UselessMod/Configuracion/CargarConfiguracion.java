package UselessMod.Configuracion;

import java.io.File;
import java.util.Hashtable;

import static net.minecraftforge.common.Configuration.CATEGORY_GENERAL;

import net.minecraft.util.StringTranslate;
import net.minecraftforge.common.Configuration;
import UselessMod.Lang.EN;
import UselessMod.Lang.ES; 

public class CargarConfiguracion {
    public static Configuration config;
	public static void load(File fil){
		config=new Configuration(fil);
		try{
			config.load();
            Datos.Idioma = config.get(CATEGORY_GENERAL,Datos.RIdioma,Datos.FIdioma).getName();
           
            Datos.idBaston = config.get("IDS",Datos.RidBaston,Datos.FidBaston).getInt();
            Datos.idTransparente = config.get("IDS",Datos.RidTransparente,Datos.FidTransparente).getInt();
            BastonVar();
            
            Datos.idAmbar = config.get("IDS",Datos.RidAmbar,Datos.FidAmbar).getInt();
            Datos.idBomba = config.get("IDS",Datos.RidBomba,Datos.FidBomba).getInt();
            AmbarVar();
            
            Datos.idPie = config.get("IDS",Datos.RidPie,Datos.FidPie).getInt();
            PiesrVar();

            Datos.names=cargaLng();

		}catch(Exception e){
			
		}
		finally {
			config.save();
		}
	}
	public static Hashtable<Integer, String>  
							cargaLng(){
		
	      switch(Datos.Idioma.charAt(1)){
          case 'S':
          	return ES.getNames();
          case 'N':
          	return EN.getNames();
          default:
            return ES.getNames();
          
          }
	}
	public static void BastonVar(){
		Datos.Baston_TamX = config.get("Baston",Datos.RBaston_TamX,Datos.FBaston_TamX).getInt();
		Datos.Baston_TamY = config.get("Baston",Datos.RBaston_TamY,Datos.FBaston_TamY).getInt();
		Datos.Baston_TamZ = config.get("Baston",Datos.RBaston_TamZ,Datos.FBaston_TamZ).getInt();
	}
	public static void AmbarVar(){
		Datos.Ambar_Radio = config.get("Ambar",Datos.RAmbar_Radio,Datos.FAmbar_Radio).getInt();
		Datos.Ambar_Tiempo = config.get("Ambar",Datos.RAmbar_Tiempo,Datos.FAmbar_Tiempo).getInt();
		Datos.Ambar_Cubo = config.get("Ambar",Datos.RAmbar_Cubo,Datos.FAmbar_Cubo).getBoolean(true);
		Datos.Ambar_blockHardness = (float)(config.get("Ambar",Datos.RAmbar_blockHardness,Datos.FAmbar_blockHardness).getDouble(0.0));
	}
	
	public static void PiesrVar(){
	 Datos.Pies_Armor = config.get("Pies",Datos.RPies_Armor,Datos.FPies_Armor).getInt();
     Datos.Pies_Empuje = config.get("Pies",Datos.RPies_Empuje,Datos.FPies_Empuje).getInt();
     Datos.Pies_Durability = config.get("Pies",Datos.RPies_Durability,Datos.FPies_Durability).getInt();
	}
}
