package UselessMod.Auxiliares;

import static net.minecraftforge.common.Configuration.CATEGORY_GENERAL;

import java.awt.Color;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

import UselessMod.Blocks.BlockTransparente;
import UselessMod.Configuracion.Datos;
import UselessMod.Items.BastonMoises.ItemBastonMoises;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.util.Icon;
import cpw.mods.fml.common.Mod.Item;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Registros {
	public static class config{
		String tipo,ini,name,id,categ;
		public config(String a,String b,String c,String d,String e){
			tipo=a;
			ini=b;
			name=c;
			id=d;
			categ=e;
		}
	}
	
	static config[] lista={ 
		new config("float","50.0f","AmbarblockHardness","Ambar_blockHardness","Ambar"),
					};
	public static Icon RegisterIcons(IconRegister reg,String name){
		return reg.registerIcon(Datos.nameMod.toLowerCase()+":"+name);
	}
	public void creaMob(Class<?extends Entity> mob,String name
			,int id,int idM,Color a,Color b){
		EntityRegistry.registerModEntity(mob,
				name,idM, this, 80, 3, true);
		if(id!=0){
		EntityList.IDtoClassMapping.put(id, mob);
		EntityList.entityEggs.put(id, new EntityEggInfo(id,
				a.getRGB(),
				b.getRGB()));}
		LanguageRegistry.instance().addStringLocalization("entity."+Datos.nameMod+"."
					+name+".name", name);

	}

	public static void main(String[] args) {
		System.out.println("//carga//");
		
		for(int i=0;i<lista.length;i++)
		{
		String tipo=lista[i].tipo,
				ini=lista[i].ini,
				name=lista[i].name,
				id=lista[i].id,
				categ=lista[i].categ;
		
		String funcion="";
		if(tipo=="String"){
			funcion="getName";
		}else {
			funcion="get"+tipo.toUpperCase().charAt(0)+tipo.substring(1);
		}
		String func="()";
		if(tipo.compareToIgnoreCase("boolean")==0){func="(true)";}
		
		
		System.out.println("Datos." +
				name+" = config.get(\""+categ+"\",Datos.R" +
						name+",Datos.F"+name+")."+funcion+func+";");
		}
		System.out.println("//datos//");
		
		for(int i=0;i<lista.length;i++)
		{
		String tipo=lista[i].tipo,
				ini=lista[i].ini,
				name=lista[i].name,
				id=lista[i].id,
				categ=lista[i].categ;
		System.out.println("public static "+tipo+" "+name+";\n"+
		"public static final "+tipo+" F"+name+"="+ini+";\n"+
		"public static final String R"+name+"=\""+id+"\";");
		boolean plus=false;
		String s = "";
		if(id.startsWith("id_")){
			plus=true;
			s=name.substring(2);
		}
		if(plus){
			System.out.println(
					"public static String N"+s+";");
		}
		System.out.println();
		}
	}

}
