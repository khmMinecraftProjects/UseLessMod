package UselessMod.Items.BastonMoises;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentThorns;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet70GameEvent;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import UselessMod.Auxiliares.Entitys.EntityBullet;
import UselessMod.Configuracion.Datos;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class Ball extends EntityBullet {
	public EntityPlayer shootingEntity = null;
	int vida = 800;
	int r = 2, ry = 20;
	int idT = Datos.getI("idTransparente");

	public Ball(World par1World) {
		super(par1World);
		this.setSize(1.0F, 1.0F);
		r = Datos.getI("Baston_TamX");
		ry = Datos.getI("Baston_TamY");
		vida = Datos.getI("Baston_TamZ");
	}

	public Ball(World par1World, EntityPlayer par3EntityPlayer, double par3,
			double par5, double par7) {
		super(par1World, par3EntityPlayer, par3, par5, par7);
		this.shootingEntity = par3EntityPlayer;
		this.setSize(1.0F, 1.0F);

	}

	float dist = 0, difX = 0, difY = 0, difZ = 0;

	public void onUpdate() {
		for (int i = 0; i < 5; i++) {
			super.onUpdate();
			abrirMar();
			this.ticksInAir++;
			if (this.ticksInAir > vida) {
				this.setDead();
			}
		}
	}

	public void abrirMar() {
		for (int i = -ry; i <= ry; i++) {
			for (int o = -r; o <= r; o++) {
				for (int e = -r; e <= r; e++) {
					Bloque((int) posX + o, (int) posY + i, (int) posZ + e);
				}
			}
		}
	}

	public void Bloque(int x, int y, int z) {

		if (esLiquido(worldObj, x, y, z)) {
			this.worldObj.setBlock(x, y, z, idT);
		}
	}

	public static boolean esLiquido(World world, int x, int y, int z) {
		List<Integer> ids = Arrays.asList(8, 9, 10, 11);
		int id = world.getBlockId(x, y, z);
		if (ids.contains(id)) {
			return true;
		}
		return false;
	}

	protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {

	}

}