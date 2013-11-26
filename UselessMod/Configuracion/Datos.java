package UselessMod.Configuracion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.src.ModLoader;

import org.apache.commons.lang3.tuple.Pair;

import UselessMod.Auxiliares.Tab;

public class Datos<V> {

	// /////////////////////////////
	// ////////Constantes//////////
	// ////////////////////////////
	public static final String nameMod = "UselessMod";
	public static final CreativeTabs tabMod = new Tab(nameMod);

	public static Hashtable<String, Boolean> booleans = new Hashtable<String, Boolean>();
	public static Hashtable<String, Integer> ints = new Hashtable<String, Integer>();
	public static Hashtable<String, Double> doubles = new Hashtable<String, Double>();
	public static Hashtable<String, String> strings = new Hashtable<String, String>();

	public static Integer getI(String string) {
		return (Integer) ints.get(string);
	}

	public static boolean getB(String string) {
		return (Boolean) booleans.get(string);
	}

	public static String getS(String string) {
		return (String) strings.get(string);
	}

	public static double getD(String string) {
		return (Double) doubles.get(string);
	}

	public static void add(String s, int i) {
		ints.put(s, i);
	}

	public static void add(String s, boolean i) {
		booleans.put(s, i);
	}

	public static void add(String s, double i) {

		doubles.put(s, i);
	}

	public static void add(String s, String i) {
		strings.put(s, i);
	}

}
