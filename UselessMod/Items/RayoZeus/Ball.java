package UselessMod.Items.RayoZeus;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentThorns;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet70GameEvent;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumMovingObjectType;
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
	int vida = 800, level = 0;

	public Ball(World par1World, int lev) {
		super(par1World);
		this.setSize(1F, 1F);
		level = lev;
	}

	public Ball(World par1World, EntityPlayer par3EntityPlayer, double par3,
			double par5, double par7, int i) {
		super(par1World, par3EntityPlayer, par3, par5, par7);
		this.shootingEntity = par3EntityPlayer;
		this.setSize(1.0F, 1.0F);
		level = i;

	}

	public Ball(World par2World, double posX, double posY, double posZ, int i) {
		super(par2World, posX, posY, posZ);
		this.setSize(1.0F, 1.0F);
		level = i;
	}

	float dist = 0, difX = 0, difY = 0, difZ = 0;

	public void onUpdate() {
		switch (level) {
		case 0:
			lev1();
			break;
		case 1:
			lev2();
			break;
		case 3:
			lev3();
			break;
		}

	}

	public void lev1() {
		for (int i = 0; i < 50; i++) {
			super.onUpdate();
			this.ticksInAir++;
			if (this.ticksInAir > vida) {
				this.setDead();
			}
			if (esImpacto()) {
				this.Impacto((int) posX, (int) posY, (int) posZ);
				this.setDead();
			}
		}
	}

	public void lev2() {
		for (int i = 0; i < 5; i++) {
			if (!this.isDead) {
				super.onUpdate();
				this.ticksInAir++;
				this.Impacto((int) posX, (int) posY - 2, (int) posZ);
				if (this.ticksInAir > vida) {
					this.setDead();
				}

			}
		}
	}

	public void lev3() {
		for (int i = 0; i < 5; i++) {
			this.ticksInAir++;
			this.Impacto((int) posX, (int) posY - 2, (int) posZ);
		}
		this.setDead();
	}

	public void Impacto(int x, int y, int z) {
		EntityLightningBolt rayo = new EntityLightningBolt(worldObj, x, y, z);
		this.worldObj.spawnEntityInWorld(rayo);
	}

}