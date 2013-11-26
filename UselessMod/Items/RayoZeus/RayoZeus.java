package UselessMod.Items.RayoZeus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import UselessMod.Auxiliares.Registros;
import UselessMod.Configuracion.Datos;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;

public class RayoZeus extends Item {
	Icon[] iconArray;
	int key = Datos.getI("Rayo_key");

	private static int frames = 4, lev = 0, direciones = 5, sleep = 0;
	private double grados = 360 / direciones;
	boolean una = false;
	List pendientes = new ArrayList();
	Iterator it = pendientes.iterator();

	public RayoZeus(int par1) {
		super(par1);
		this.maxStackSize = 1;
		this.setMaxDamage(50);
		this.setCreativeTab(Datos.tabMod);
	}

	public void onUpdate(ItemStack ItemStack, World par2World,
			Entity par3Entity, int par4, boolean par5) {
		if (!par2World.isRemote) {
			return;
		}
		if (!una
				&&

				Keyboard.isKeyDown(key)
				&& ItemStack.isItemEqual(((EntityPlayer) par3Entity).inventory
						.getCurrentItem())) {
			lev++;
			if (lev == (frames)) {
				lev = 0;
			}
			this.itemIcon = iconArray[lev];
			una = true;
		}
		if (!Keyboard.isKeyDown(key)) {
			una = false;
		}
		sleep++;
		if (it.hasNext() && sleep >= 15) {
			sleep = 0;
			Entity mob = (Entity) it.next();
			EntityLightningBolt b3 = new EntityLightningBolt(par2World,
					mob.posX, mob.posY, mob.posZ);

			par2World.spawnEntityInWorld(b3);
			((EntityLivingBase) mob).onStruckByLightning(b3);
		}
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {

		int abs = 0;
		double motionX = (double) (-MathHelper.sin(par3EntityPlayer.rotationYaw
				/ 180.0F * (float) Math.PI) * MathHelper
				.cos(par3EntityPlayer.rotationPitch / 180.0F * (float) Math.PI));
		double motionZ = (double) (MathHelper.cos(par3EntityPlayer.rotationYaw
				/ 180.0F * (float) Math.PI) * MathHelper
				.cos(par3EntityPlayer.rotationPitch / 180.0F * (float) Math.PI));
		double motionY = (double) (-MathHelper
				.sin(par3EntityPlayer.rotationPitch / 180.0F * (float) Math.PI));
		switch (lev) {
		case 0:

			Ball b0 = new Ball(par2World, par3EntityPlayer, motionX, motionY,
					motionZ, 0);
			par2World.spawnEntityInWorld(b0);
			break;
		case 1:
			Ball b1 = new Ball(par2World, par3EntityPlayer, motionX, 0,
					motionZ, 1);
			par2World.spawnEntityInWorld(b1);
			break;
		case 2:
			for (double m = 0; m < 360; m += grados) {
				double sen = Math.sin(m);
				double cos = Math.cos(m);
				Ball b2 = new Ball(par2World, par3EntityPlayer, sen, 0, cos, 1);
				par2World.spawnEntityInWorld(b2);
			}
			break;
		case 3:
			pendientes = new ArrayList();
			Iterator enty = par2World.getLoadedEntityList().iterator();
			int max = 2,
			a = 0;
			while (enty.hasNext()) {
				Object mob = enty.next();

				if (EntityMob.class.isAssignableFrom(mob.getClass())) {
					a++;

					pendientes.add(mob);
				}
			}
			it = pendientes.iterator();

			break;
		}
		return par1ItemStack;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.iconArray = new Icon[frames];

		for (int i = 0; i < this.iconArray.length; ++i) {
			this.iconArray[i] = Registros.RegisterIcons(par1IconRegister,
					"rayo_" + i);
		}
		this.itemIcon = this.iconArray[0];
	}

	@SideOnly(Side.CLIENT)
	public Icon getItemIconForUseDuration(int par1) {
		this.itemIcon = this.iconArray[par1];
		return this.iconArray[par1];
	}

}
