package UselessMod;



import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;


import cpw.mods.fml.common.Mod.Instance;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.util.StringTranslate;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.relauncher.SideOnly;
import UselessMod.Blocks.Ambar;
import UselessMod.Blocks.BlockTransparente;
import UselessMod.Configuracion.CargarConfiguracion;
import UselessMod.Configuracion.Datos;
import UselessMod.Items.BastonMoises.ItemBastonMoises;
import UselessMod.Items.BombAmbar.BombAmbar;
import UselessMod.Items.BombAmbar.EntityBomb;
import UselessMod.Items.PiesJesus.PiesJesus;
import UselessMod.proxies.ClientProxy;
import UselessMod.proxies.CommonProxy;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod;
@Mod(modid= "UselessMod",useMetadata=true)



@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class base {
	  
	String fir;
	public static Item Baston,Bomba,Pies;
	public static Block Transparente,Ambar;
	@Mod.Instance("UselessMod")
	  public static base instance;
	@SidedProxy(clientSide="UselessMod.proxies.ClientProxy", serverSide="UselessMod.proxies.CommonProxy")
	public static CommonProxy proxy;

	  @Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) 
	{
		CargarConfiguracion.load(
				new File(event.getModConfigurationDirectory().getAbsolutePath()+File.separator+"UselessMod.cfg"));
		preRegister();
	}

	  private void preRegister() {
		EntityRegistry.registerModEntity(EntityBomb.class, "Bomba", ModLoader.getUniqueEntityId(), base.instance, 128, 1, true);		
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		Baston=new ItemBastonMoises(Datos.idBaston).setUnlocalizedName("baston");
		LanguageRegistry.addName(Baston, Datos.names.get(Datos.idBaston));
		
		Transparente=new BlockTransparente(Datos.idTransparente).setUnlocalizedName("Transparente");
		LanguageRegistry.addName(Transparente, Datos.names.get(Datos.idTransparente));

		Ambar=new Ambar(Datos.idAmbar).setUnlocalizedName("Ambar");
	    GameRegistry.registerBlock(Ambar, "ambar");
		LanguageRegistry.addName(Ambar, Datos.names.get(Datos.idAmbar));
		
		Bomba=new BombAmbar(Datos.idBomba).setUnlocalizedName("Bomba");
		LanguageRegistry.addName(Bomba, Datos.names.get(Datos.idBomba));
		
		Pies=new PiesJesus(Datos.idPie,proxy.addArmors("pies")).setUnlocalizedName("Pies");
		LanguageRegistry.addName(Pies, Datos.names.get(Datos.idPie));
		
	}

	  @Mod.EventHandler
	public static void postInit(FMLPostInitializationEvent event)
	{
	        proxy.registerRenderThings();
	}









}
