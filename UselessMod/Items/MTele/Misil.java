package UselessMod.Items.MTele;

import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import UselessMod.base;
import UselessMod.Auxiliares.Entitys.EntityBullet;
import UselessMod.Auxiliares.Entitys.EntityPlayerMod;
import UselessMod.Configuracion.Datos;
import UselessMod.proxies.Client.PacketHandlerClient;

public class Misil extends EntityBullet {
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
	}

	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {

		this.setDead();

	}

	int vida = 500;
	EntityPlayer player = null;
	EntityPlayerMod playerm = null;

	public Misil(World par1World) {
		super(par1World);
		player = Minecraft.getMinecraft().thePlayer;

	}

	public Misil(World par1World, EntityPlayer par3EntityPlayer, double par3,
			double par5, double par7) {
		super(par1World, par3EntityPlayer, par3, par5, par7);
		player = par3EntityPlayer;
		this.setSize(1, 1);
	}

	public Misil(World par2World, EntityPlayerMod playerMod, double motionX,
			double motionY, double motionZ) {
		super(par2World, playerMod.player, motionX, motionY, motionZ);
		playerm = playerMod;
		player = playerMod.player;
		this.setSize(1, 1);
	}

	double fx, fy, fz;

	public void onUpdate() {
		player.fallDistance = 0;

		if (this.worldObj.isRemote && player == null) {
			setDead();
			return;
		}
		if (ticksInAir == 0) {
			fx = posX;
			fy = posY;
			fz = posZ;
		}

		if (this.ticksInAir > vida) {
			Dead = true;
		}

		super.onUpdate();

		if (this.worldObj.isRemote
				&& base.proxy.tickHandlerServer.lanzadores
						.containsKey(player.username)) {
			setDead();
		}
		if (!this.isDead && this.worldObj.isRemote) {
			player.setPosition(posX, posY, posZ);
		}
		player.fallDistance = 0;

		this.esImpacto();

		this.ticksInAir++;

		if (this.worldObj.isRemote
				&& this.rotationPitch != player.rotationPitch
				|| this.rotationYaw != player.rotationYaw) {
			motionX = (double) (-MathHelper.sin(player.rotationYaw / 180.0F
					* (float) Math.PI) * MathHelper.cos(player.rotationPitch
					/ 180.0F * (float) Math.PI));
			motionZ = (double) (MathHelper.cos(player.rotationYaw / 180.0F
					* (float) Math.PI) * MathHelper.cos(player.rotationPitch
					/ 180.0F * (float) Math.PI));
			motionY = (double) (-MathHelper.sin(player.rotationPitch / 180.0F
					* (float) Math.PI));
			this.rotationPitch = player.rotationPitch;
			this.rotationYaw = player.rotationYaw;
		}

	}

	boolean Dead = false;

	protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {
		setDead();

		if (playerm != null) {
			playerm.deadPlayer = false;
			playerm.setDead();
		}
		if (!worldObj.isRemote) {

			Minecraft.getMinecraft().thePlayer.setPosition(playerm.posX,
					playerm.posY, playerm.posZ);
			PacketHandlerClient.sendPacketPosition(playerm.posX, playerm.posY,
					playerm.posZ, Minecraft.getMinecraft().thePlayer);
			this.worldObj.createExplosion(this, this.posX, this.posY,
					this.posZ, 10, true);

			if (base.proxy.tickHandlerClient.lanzadores
					.containsKey(player.username)) {
				base.proxy.tickHandlerClient.lanzadores.get(player.username)
						.Dead(false);
			}
			base.proxy.tickHandlerClient.lanzadores.remove(player.username);

		}

	}

}
