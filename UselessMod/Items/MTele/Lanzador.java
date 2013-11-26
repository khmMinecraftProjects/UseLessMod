package UselessMod.Items.MTele;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import UselessMod.base;
import UselessMod.Auxiliares.Registros;
import UselessMod.Auxiliares.UpdatePlayerMod;
import UselessMod.Auxiliares.Entitys.EntityPlayerMod;
import UselessMod.Configuracion.Datos;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class Lanzador extends Item {

	public Lanzador(int par1) {
		super(par1);
		this.setCreativeTab(Datos.tabMod);

	}

	public boolean lanzado = false;
	public Misil misil;
	public boolean s = false;
	public EntityPlayerMod playerMod;
	private Icon[] iconArray;
	private boolean action = false;

	public void onUpdate(ItemStack par1ItemStack, World par2World,
			Entity par3Entity, int par4, boolean par5) {
	}

	@SideOnly(Side.CLIENT)
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3Entity) {

		if (par2World.isRemote
				|| base.proxy.tickHandlerClient.lanzadores
						.containsKey(((EntityPlayer) par3Entity).username)) {
			return par1ItemStack;
		}

		System.out.println(((EntityPlayer) par3Entity).username + "Remoto "
				+ base.proxy.tickHandlerClient.lanzadores);

		double motionX = (double) (-MathHelper.sin(par3Entity.rotationYaw
				/ 180.0F * (float) Math.PI) * MathHelper
				.cos(par3Entity.rotationPitch / 180.0F * (float) Math.PI));
		double motionZ = (double) (MathHelper.cos(par3Entity.rotationYaw
				/ 180.0F * (float) Math.PI) * MathHelper
				.cos(par3Entity.rotationPitch / 180.0F * (float) Math.PI));
		double motionY = (double) (-MathHelper.sin(par3Entity.rotationPitch
				/ 180.0F * (float) Math.PI));

		playerMod = new EntityPlayerMod(par3Entity, par2World);

		playerMod.setPositionAndRotation(par3Entity.posX, par3Entity.posY,
				par3Entity.posZ, par3Entity.rotationYaw,
				par3Entity.rotationPitch);

		misil = new Misil(par2World, playerMod, motionX, motionY, motionZ);
		base.proxy.tickHandlerClient.lanzadores.put(
				((EntityPlayer) par3Entity).username, this);

		par2World.spawnEntityInWorld(playerMod);
		par2World.spawnEntityInWorld(misil);
		return par1ItemStack;

	}

	@SideOnly(Side.CLIENT)
	public Icon getItemIconForUseDuration(int par1) {
		this.itemIcon = this.iconArray[par1];
		return this.iconArray[par1];
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.iconArray = new Icon[2];

		this.iconArray[0] = Registros.RegisterIcons(par1IconRegister,
				"lanzador");
		this.iconArray[1] = Registros.RegisterIcons(par1IconRegister, "nil");

		this.itemIcon = this.iconArray[0];
	}

	public void Dead(boolean var) {

		playerMod.deadPlayer = var;
		if (var) {
			misil.setDead();
		}

	}

}
