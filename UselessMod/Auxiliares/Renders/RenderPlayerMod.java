package UselessMod.Auxiliares.Renders;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED;
import static net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D;
import net.minecraft.block.Block;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.opengl.GL11;

import UselessMod.base;
import UselessMod.Auxiliares.Entitys.EntityPlayerMod;
import UselessMod.Configuracion.Datos;
import UselessMod.Items.BotasAntiGrav.Botas;

public class RenderPlayerMod extends RenderPlayerModBase {

	private static final ResourceLocation field_110826_a = new ResourceLocation(
			"textures/entity/steve.png");
	private ModelBiped modelBipedMain;
	private ModelBiped modelArmorChestplate;
	private ModelBiped modelArmor;

	public RenderPlayerMod() {
		super();
	}

	/**
	 * Set the specified armor model as the player model. Args: player,
	 * armorSlot, partialTick
	 */
	protected int setArmorModel(AbstractClientPlayer par1AbstractClientPlayer,
			int par2, float par3) {
		return super.setArmorModel(par1AbstractClientPlayer, par2, par3);
	}

	protected void func_130220_b(AbstractClientPlayer par1AbstractClientPlayer,
			int par2, float par3) {
		super.func_130220_b(par1AbstractClientPlayer, par2, par3);
	}

	public void func_130009_a(AbstractClientPlayer par1AbstractClientPlayer,
			double par2, double par4, double par6, float par8, float par9) {
		super.func_130009_a(par1AbstractClientPlayer, par2, par4, par6, par8,
				par9);
	}

	protected ResourceLocation func_110817_a(
			AbstractClientPlayer par1AbstractClientPlayer) {
		return super.func_110817_a(par1AbstractClientPlayer);
	}

	/**
	 * Method for adding special render rules
	 */
	protected void renderSpecials(
			AbstractClientPlayer par1AbstractClientPlayer, float par2) {
		super.renderSpecials(par1AbstractClientPlayer, par2);
	}

	protected void renderPlayerScale(
			AbstractClientPlayer par1AbstractClientPlayer, float par2) {
		super.renderPlayerScale(par1AbstractClientPlayer, par2);
	}

	protected void func_96450_a(AbstractClientPlayer par1AbstractClientPlayer,
			double par2, double par4, double par6, String par8Str, float par9,
			double par10) {
		super.func_96450_a(par1AbstractClientPlayer, par2, par4, par6, par8Str,
				par9, par10);
	}

	public void renderFirstPersonArm(EntityPlayer par1EntityPlayer) {
		super.renderFirstPersonArm(par1EntityPlayer);
	}

	/**
	 * Renders player with sleeping offset if sleeping
	 */
	protected void renderPlayerSleep(
			AbstractClientPlayer par1AbstractClientPlayer, double par2,
			double par4, double par6) {
		super.renderPlayerSleep(par1AbstractClientPlayer, par2, par4, par6);
	}

	/**
	 * Rotates the player if the player is sleeping. This method is called in
	 * rotateCorpse.
	 */
	protected void rotatePlayer(AbstractClientPlayer par1AbstractClientPlayer,
			float par2, float par3, float par4) {
		super.rotatePlayer(par1AbstractClientPlayer, par2, par3, par4);
	}

	protected void func_96449_a(EntityLivingBase par1EntityLivingBase,
			double par2, double par4, double par6, String par8Str, float par9,
			double par10) {
		super.func_96449_a(par1EntityLivingBase, par2, par4, par6, par8Str,
				par9, par10);
	}

	/**
	 * Allows the render to do any OpenGL state modifications necessary before
	 * the model is rendered. Args: entityLiving, partialTickTime
	 */
	protected void preRenderCallback(EntityLivingBase par1EntityLivingBase,
			float par2) {
		super.preRenderCallback(par1EntityLivingBase, par2);
	}

	protected void func_82408_c(EntityLivingBase par1EntityLivingBase,
			int par2, float par3) {
		super.func_82408_c(par1EntityLivingBase, par2, par3);
	}

	/**
	 * Queries whether should render the specified pass or not.
	 */
	protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase,
			int par2, float par3) {
		return super.shouldRenderPass(par1EntityLivingBase, par2, par3);
	}

	protected void renderEquippedItems(EntityLivingBase par1EntityLivingBase,
			float par2) {
		super.renderEquippedItems(par1EntityLivingBase, par2);
	}

	protected void rotateCorpse(EntityLivingBase par1EntityLivingBase,
			float par2, float par3, float par4) {
		super.rotateCorpse(par1EntityLivingBase, par2, par3, par4);
	}

	/**
	 * Sets a simple glTranslate on a LivingEntity.
	 */
	protected void renderLivingAt(EntityLivingBase par1EntityLivingBase,
			double par2, double par4, double par6) {
		super.renderLivingAt(par1EntityLivingBase, par2, par4, par6);
	}

	public void func_130000_a(EntityLivingBase par1EntityLivingBase,
			double par2, double par4, double par6, float par8, float par9) {
		super.func_130000_a(par1EntityLivingBase, par2, par4, par6, par8, par9);
	}

	protected ResourceLocation func_110775_a(Entity par1Entity) {
		return super.func_110775_a(par1Entity);
	}

	/**
	 * Actually renders the given argument. This is a synthetic bridge method,
	 * always casting down its argument and then handing it off to a worker
	 * function which does the actual work. In all probabilty, the class Render
	 * is generic (Render<T extends Entity) and this method has signature public
	 * void doRender(T entity, double d, double d1, double d2, float f, float
	 * f1). But JAD is pre 1.5 so doesn't do that.
	 */
	public void doRender(Entity par1Entity, double par2, double par4,
			double par6, float par8, float par9) {
		if (par1Entity != null && par1Entity instanceof EntityPlayerMod) {
			super.doRender(
					(AbstractClientPlayer) ((EntityPlayerMod) par1Entity).player,
					par2, par4 + 1.62F, par6, par8, par9);
		}
	}

}
