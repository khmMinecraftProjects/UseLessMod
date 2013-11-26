package UselessMod;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.network.NetworkModHandler;
import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.Mod.Instance;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.util.StringTranslate;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
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
import UselessMod.Auxiliares.Registros;
import UselessMod.Auxiliares.Entitys.EntityPlayerMod;
import UselessMod.Blocks.Ambar;
import UselessMod.Blocks.BlockTransparente;
import UselessMod.Configuracion.CargarConfiguracion;
import UselessMod.Configuracion.Datos;
import UselessMod.Configuracion.Info;
import UselessMod.Items.BastonMoises.BastonMoises;
import UselessMod.Items.BombAmbar.BombAmbar;
import UselessMod.Items.BombAmbar.EntityBomb;
import UselessMod.Items.MTele.Misil;
import UselessMod.Items.PiesJesus.PiesJesus;
import UselessMod.Items.RayoZeus.RayoZeus;
import UselessMod.proxies.ConnectionHandler;
import UselessMod.proxies.MapPacketHandler;
import UselessMod.proxies.Client.PacketHandlerClient;
import UselessMod.proxies.Server.CommonProxy;
import UselessMod.proxies.Server.PacketHandlerServer;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod;

@Mod(modid = "UselessMod", useMetadata = true)
@NetworkMod(clientSideRequired = true, serverSideRequired = false, connectionHandler = ConnectionHandler.class, tinyPacketHandler = MapPacketHandler.class, clientPacketHandlerSpec = @SidedPacketHandler(channels = { "UseLess" }, packetHandler = PacketHandlerClient.class), serverPacketHandlerSpec = @SidedPacketHandler(channels = { "UseLess" }, packetHandler = PacketHandlerServer.class))
public class base {

	public static Hashtable<Integer, Block> blocks;
	public static Hashtable<Integer, Item> items;

	@Mod.Instance("UselessMod")
	public static base instance;
	@SidedProxy(clientSide = "UselessMod.proxies.Client.ClientProxy", serverSide = "UselessMod.proxies.Server.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preLoad(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new UselessMod.proxies.EventListen());

	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		CargarConfiguracion.load(new File(event.getModConfigurationDirectory()
				.getAbsolutePath() + File.separator + "UselessMod.cfg"));

	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		proxy.initTickHandlers();

		int id = EntityRegistry.findGlobalUniqueEntityId();

		EntityRegistry
				.registerGlobalEntityID(EntityPlayerMod.class, "Play", id);
		EntityRegistry.registerModEntity(EntityPlayerMod.class, "Play", 1,
				this, 64, 10, true);
		EntityList.entityEggs.put(Integer.valueOf(id), new EntityEggInfo(id,
				0x99999, 0x000000));

		id = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerModEntity(Misil.class, "Misl", 2, this, 64, 10,
				true);

		EntityRegistry.registerModEntity(EntityBomb.class, "EntityBomb", 3,
				this, 64, 10, true);

		GameRegistry.registerPlayerTracker(new ConnectionHandler());

	}

	@EventHandler
	public void Postload(FMLPostInitializationEvent event) {
		proxy.postInit();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
			throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Registros r = new Info();
		items = r.initItems();
		blocks = r.initBlocks();

	}

	@Mod.EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		proxy.registerRenderThings();
	}

}
