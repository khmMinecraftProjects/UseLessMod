package UselessMod.Lang;

import java.util.Hashtable;

import UselessMod.Configuracion.Datos;

public class EN {
	static Hashtable<Integer, String> names= new 
			Hashtable<Integer, String>();

	public static void inicializa(){
		names.put(Datos.idBaston, "Baston de moises");
		names.put(Datos.idTransparente, "Null");
		names.put(Datos.idAmbar, "Ambar");
		names.put(Datos.idBomba, "Bomba de Ambar");
		names.put(Datos.idPie, "Pie de Jesus");

	}
	public static Hashtable<Integer, String> getNames(){
		inicializa();
		return names;
	}
}
