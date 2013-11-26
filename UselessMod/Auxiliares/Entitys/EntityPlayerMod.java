package UselessMod.Auxiliares.Entitys;

import java.util.Collection;
import java.util.Iterator;

import UselessMod.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeInstance;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.CombatTracker;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class EntityPlayerMod extends EntityAnimal {

	public void onLivingUpdate() {
		if (this.worldObj.isRemote && player == null) {
			setDead();
			return;
		}
		super.onLivingUpdate();

	}

	public EntityPlayer player;
	public boolean deadPlayer = true;

	public EntityPlayerMod(World worldObj) {
		super(worldObj);
		if (worldObj.isRemote) {
			player = (EntityPlayer) worldObj.playerEntities.get(0);
		} else {
			player = Minecraft.getMinecraft().thePlayer;
		}
		this.yOffset = 1.62F;
		this.setSize(0.6F, 1.8F);
	}

	public EntityPlayerMod(EntityPlayer play, World worldObj) {
		super(worldObj);
		// this.yOffset = 1.62F;
		// this.ySize=1.62f;

		player = play;
		// func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(player.func_110143_aJ());

		this.setSize(0.6F, 1.8F);
	}

	@Override
	protected void func_110147_ax() {
		super.func_110147_ax();

		// func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0);
		// //Speed
	}

	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEFINED;
	}

	protected boolean canDespawn() {
		return true;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable entityageable) {
		return null;
	}

	public void addPotionEffect(PotionEffect par1PotionEffect) {
		player.addPotionEffect(par1PotionEffect);
	}

	public boolean isPotionActive(int par1) {
		return player.isPotionActive(par1);
	}

	public boolean isPotionActive(Potion par1Potion) {
		return player.isPotionActive(par1Potion);
	}

	public Collection getActivePotionEffects() {
		return player.getActivePotionEffects();
	}

	public PotionEffect getActivePotionEffect(Potion par1Potion) {
		return player.getActivePotionEffect(par1Potion);
	}

	public void removePotionEffectClient(int par1) {
		player.removePotionEffectClient(par1);
	}

	public void removePotionEffect(int par1) {
		player.removePotionEffect(par1);
	}

	public void heal(float par1) {
		super.heal(par1);
		player.heal(par1);
	}

	public void setEntityHealth(float par1) {
		super.setEntityHealth(par1);
		// player.setEntityHealth(par1);
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		boolean b = super.attackEntityFrom(par1DamageSource, par2);
		if (player == null) {
			return b;
		}
		return player.attackEntityFrom(par1DamageSource, par2);
	}

	public void onDeath(DamageSource par1DamageSource) {
		System.out.println("player death " + player);

		if (player == null && !this.isDead) {
			return;
		}
		player.setPosition(this.posX, this.posY, this.posZ);
		// player.onDeath(par1DamageSource);
		if (deadPlayer) {
			player.onDeath(par1DamageSource);
		}
		System.out.println("player death " + player + " to " + posX + " "
				+ posY + " " + posZ);

		this.setDead();
	}

	public void knockBack(Entity par1Entity, float par2, double par3,
			double par5) {
		super.knockBack(par1Entity, par2, par3, par5);
		player.knockBack(par1Entity, par2, par3, par5);
	}

	public int getTotalArmorValue() {
		return player.getTotalArmorValue();
	}

	@SideOnly(Side.CLIENT)
	public void handleHealthUpdate(byte par1) {
		player.handleHealthUpdate(par1);
		super.handleHealthUpdate(par1);
	}

	public ItemStack getHeldItem() {
		return player.getHeldItem();
	}

	public ItemStack getCurrentItemOrArmor(int i) {
		if (player == null) {
			this.setDead();
			return null;
		}
		return player.getCurrentItemOrArmor(i);
	}

	public ItemStack[] getLastActiveItems() {
		return player.getLastActiveItems();
	}

	@SideOnly(Side.CLIENT)
	public Icon getItemIcon(ItemStack par1ItemStack, int par2) {
		return player.getItemIcon(par1ItemStack, par2);
	}

	public void curePotionEffects(ItemStack curativeItem) {
		player.curePotionEffects(curativeItem);
	}

}
