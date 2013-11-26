package UselessMod.Auxiliares.Renders;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import UselessMod.Configuracion.Datos;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderEnte extends Render {
	ModelBase model;
	ResourceLocation resource;

	public RenderEnte(ModelBase par1ModelBase, String pn) {
		model = par1ModelBase;
		resource = new ResourceLocation(Datos.nameMod.toLowerCase()
				+ ":textures/entity/" + pn + ".png");
	}

	public void doRender(Entity par1Entity, double par2, double par4,
			double par6, float par8, float par9) {

		GL11.glPushMatrix();
		GL11.glDisable(2884);
		Minecraft.getMinecraft().renderEngine.func_110577_a(resource);

		GL11.glTranslatef((float) par2, (float) par4, (float) par6);
		float f1 = (float) (Math.PI * (par1Entity.rotationPitch / 360));
		float f2 = (float) (Math.PI * (par1Entity.rotationYaw / 360));

		GL11.glRotatef(180 - par1Entity.rotationYaw, 0, 1, 0);
		GL11.glRotatef(-par1Entity.rotationPitch, 1, 0, 0);

		GL11.glScaled(.1, .1, .1);
		model.render(par1Entity, (float) 0, (float) 0, (float) 0, (float) 0,
				(float) 0, 1);

		GL11.glPopMatrix();

	}

	@Override
	protected ResourceLocation func_110775_a(Entity entity) {
		return TextureMap.field_110576_c;
	}

}
