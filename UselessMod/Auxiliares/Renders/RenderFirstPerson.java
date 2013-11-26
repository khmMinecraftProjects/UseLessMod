package UselessMod.Auxiliares.Renders;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.ObfuscationReflectionHelper;

import UselessMod.Auxiliares.UpdatePlayerMod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class RenderFirstPerson extends RenderPlayer {

	public RenderPlayer rend;

	public ModelBiped modelBipedMainMod;

	public ResourceLocation resource;

	public boolean ini;

	public RenderFirstPerson() {
		ini = false;

	}

	public void ini() {

		Minecraft mc = Minecraft.getMinecraft();

		rend = (RenderPlayer) RenderManager.instance
				.getEntityRenderObject(mc.thePlayer);

		resource = UpdatePlayerMod.getTexture(rend, rend.getClass(),
				mc.thePlayer);

		modelBipedMainMod = (ModelBiped) ObfuscationReflectionHelper
				.getPrivateValue(RenderPlayer.class, rend,
						UpdatePlayerMod.modelBipedMain);

		ini = true;
	}

	@Override
	public void renderFirstPersonArm(EntityPlayer par1EntityPlayer) {

		float f = 1.0F;

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor3f(f, f, f);

		this.modelBipedMainMod.onGround = 0.0F;
		this.modelBipedMainMod.setRotationAngles(0, 0, 0F, 0.0F, 0.0F, 0.0625F,
				par1EntityPlayer);
		this.modelBipedMainMod.bipedRightArm.render(0.0625F);
		GL11.glDisable(GL11.GL_BLEND);

	}
}
