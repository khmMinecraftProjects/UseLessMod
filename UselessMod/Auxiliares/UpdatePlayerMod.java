package UselessMod.Auxiliares;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class UpdatePlayerMod {

	public static final String getEntityTextureObf = "func_110775_a";
	public static final String getEntityTextureDeobf = "getEntityTexture";
	public static boolean obfuscation;
	public static final ResourceLocation locationStevePng = new ResourceLocation(
			"textures/entity/steve.png");
	public static final String[] modelBipedMain = new String[] { "f",
			"field_77109_a", "modelBipedMain" };

	public static void detectObfuscation() {
		try {
			Class.forName("net.minecraft.world.World");
			obfuscation = false;
		} catch (Exception e) {
			obfuscation = true;
		}

	}

	public static ResourceLocation getTexture(Render rend, Class clz,
			EntityLivingBase ent) {
		try {
			while (rend instanceof RendererLivingEntity
					&& clz != RendererLivingEntity.class) {
				clz = clz.getSuperclass();
			}
			Method m = clz.getDeclaredMethod(obfuscation ? getEntityTextureObf
					: getEntityTextureDeobf, Entity.class);
			m.setAccessible(true);
			return (ResourceLocation) m.invoke(rend, ent);
		} catch (Exception e) {

		}
		return locationStevePng;
	}
}
