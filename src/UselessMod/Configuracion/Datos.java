package UselessMod.Configuracion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.src.ModLoader;

import org.apache.commons.lang3.tuple.Pair;

import UselessMod.Auxiliares.Tab;

public class Datos {
	///////////////////////////////
	//////////Constantes//////////
	//////////////////////////////
	public static final String nameMod="UselessMod";
	public static Hashtable<Integer, String> names;
	public static final CreativeTabs tabMod = 
			new Tab(nameMod);


	//////////////////////////
	//////////GLOBAL//////////
	//////////////////////////
	public static String Idioma;
	public static final String FIdioma="ES";
	public static final String RIdioma="Idioma";

	///////////////////////////
	////////////IDS////////////
	///////////////////////////
	public static int idBaston;
	public static String NBaston;
	public static final int FidBaston=558;
	public static final String RidBaston="id_Baston";

	public static int idTransparente;
	public static final int FidTransparente=559;
	public static final String RidTransparente="id_Transparente";
	public static String NTransparente;
	
	public static int idAmbar;
	public static final int FidAmbar=600;
	public static final String RidAmbar="id_Ambar";
	public static String NAmbar;
	
	public static int idBomba;
	public static final int FidBomba=601;
	public static final String RidBomba="id_Bomba";
	public static String NBomba;
	
	public static int idPie;
	public static final int FidPie=602;
	public static final String RidPie="id_Pie";
	public static String NPie;
	

	/////////////////////////////
	/////////Baston Vars/////////
	/////////////////////////////

	public static int Baston_TamX;
	public static final int FBaston_TamX=2;
	public static final String RBaston_TamX="Baston_X";
	
	public static int Baston_TamY;
	public static final int FBaston_TamY=20;
	public static final String RBaston_TamY="Baston_Y";
	
	public static int Baston_TamZ;
	public static final int FBaston_TamZ=800;
	public static final String RBaston_TamZ="Baston_Z";
	
	////////////////////////////
	/////////Ambar Vars/////////
	////////////////////////////
	
	public static int Ambar_Radio;
	public static final int FAmbar_Radio=20;
	public static final String RAmbar_Radio="Ambar_Radio";
	
	public static int Ambar_Tiempo;
	public static final int FAmbar_Tiempo=20;
	public static final String RAmbar_Tiempo="Ambar_Tiempo";

	public static Boolean Ambar_Cubo;
	public static final Boolean FAmbar_Cubo=true;
	public static final String RAmbar_Cubo="Ambar_Cubo";
	
	public static float Ambar_blockHardness;
	public static final float FAmbar_blockHardness=50.0f;
	public static final String RAmbar_blockHardness="Ambar_blockHardness";
	
	///////////////////////////
	/////////Pies Vars/////////
	///////////////////////////

	public static int Pies_Armor;
	public static final int FPies_Armor=50;
	public static final String RPies_Armor="Pies_Armor";
	
	public static int Pies_Empuje;
	public static final int FPies_Empuje=2;
	public static final String RPies_Empuje="Pies_Empuje";
	
	public static int Pies_Durability;
	public static final int FPies_Durability=50;
	public static final String RPies_Durability="Pies_Durability";
}
