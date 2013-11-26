package UselessMod.Auxiliares.Renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import UselessMod.base;

public class RenderEntityThrowable extends Render {
	private Item item;

	public RenderEntityThrowable(Item par1) {
		this.item = par1;
	}

	public void doRender(Entity par1Entity, double par2, double par4,
			double par6, float par8, float par9) {

		Icon icon = this.item.getIconFromDamage(0);
		if (icon != null) {
			GL11.glPushMatrix();
			GL11.glTranslatef((float) par2, (float) par4, (float) par6);
			GL11.glEnable(32826);
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			func_110777_b(par1Entity);
			Tessellator tessellator = Tessellator.instance;

			float f = icon.getMinU();
			float f1 = icon.getMaxU();
			float f2 = icon.getMinV();
			float f3 = icon.getMaxV();
			float f4 = 1.0F;
			float f5 = 0.5F;
			float f6 = 0.25F;
			GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F,
					0.0F);
			GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
			tessellator.startDrawingQuads();
			tessellator.setNormal(0.0F, 1.0F, 0.0F);
			tessellator.addVertexWithUV((double) (0.0F - f5),
					(double) (0.0F - f6), 0.0D, (double) f, (double) f3);
			tessellator.addVertexWithUV((double) (f4 - f5),
					(double) (0.0F - f6), 0.0D, (double) f1, (double) f3);
			tessellator.addVertexWithUV((double) (f4 - f5), (double) (f4 - f6),
					0.0D, (double) f1, (double) f2);
			tessellator.addVertexWithUV((double) (0.0F - f5),
					(double) (f4 - f6), 0.0D, (double) f, (double) f2);
			tessellator.draw();

			GL11.glDisable(32826);
			GL11.glPopMatrix();
		}
	}

	@Override
	protected ResourceLocation func_110775_a(Entity par1Entity) {
		return TextureMap.field_110576_c;
	}

}
