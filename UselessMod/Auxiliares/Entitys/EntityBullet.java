package UselessMod.Auxiliares.Entitys;

import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import UselessMod.Configuracion.Datos;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityBullet extends Entity {
	private int xTile = -0;
	private int yTile = -0;
	private int zTile = 0;
	private int inTile = 0;
	private boolean inGround = false;
	public double accelerationX;
	public double accelerationY;
	public double accelerationZ;
	public int ticksInAir = 0;
	EntityPlayer player = null;

	public EntityBullet(World par1World) {
		super(par1World);
	}

	public EntityBullet(World par1World, EntityPlayer par3EntityPlayer,
			double par3, double par5, double par7) {
		super(par1World);
		this.motionX = this.motionY = this.motionZ = 0.0D;
		double d3 = (double) MathHelper.sqrt_double(par3 * par3 + par5 * par5
				+ par7 * par7);
		this.accelerationX = par3 / d3 * 0.3D;
		this.accelerationY = par5 / d3 * 0.3D;
		this.accelerationZ = par7 / d3 * 0.3D;
		this.motionX += this.accelerationX;
		this.motionY += this.accelerationY;
		this.motionZ += this.accelerationZ;
		this.setLocationAndAngles(par3EntityPlayer.posX + motionX * 3,
				par3EntityPlayer.posY + motionY * 3, par3EntityPlayer.posZ
						+ motionZ * 3, par3EntityPlayer.rotationYaw,
				par3EntityPlayer.rotationPitch);

	}

	public EntityBullet(World par2World, double posX, double posY, double posZ) {
		super(par2World);
		this.setLocationAndAngles(posX, posY, posZ, 0, 0);
		this.setPosition(posX, posY, posZ);
		this.motionX = this.motionY = this.motionZ = 0.0D;
	}

	public EntityBullet(World par1World, EntityLivingBase par3EntityPlayer,
			double par3, double par5, double par7) {
		super(par1World);
		this.motionX = this.motionY = this.motionZ = 0.0D;
		double d3 = (double) MathHelper.sqrt_double(par3 * par3 + par5 * par5
				+ par7 * par7);
		this.accelerationX = par3 / d3 * 0.3D;
		this.accelerationY = par5 / d3 * 0.3D;
		this.accelerationZ = par7 / d3 * 0.3D;
		this.motionX += this.accelerationX;
		this.motionY += this.accelerationY;
		this.motionZ += this.accelerationZ;
		this.setLocationAndAngles(par3EntityPlayer.posX + motionX * 3,
				par3EntityPlayer.posY + motionY * 3, par3EntityPlayer.posZ
						+ motionZ * 3, par3EntityPlayer.rotationYaw,
				par3EntityPlayer.rotationPitch);
	}

	protected void entityInit() {
	}

	@SideOnly(Side.CLIENT)
	public float getShadowSize() {
		return 0.0F;
	}

	@SideOnly(Side.CLIENT)
	public boolean isInRangeToRenderDist(double par1) {
		double d1 = this.boundingBox.getAverageEdgeLength() * 4.0D;
		d1 *= 64.0D;
		return par1 < d1 * d1;
	}

	public void onUpdate() {
		posX += motionX;
		posY += motionY;
		posZ += motionZ;
		this.setPosition(this.posX, this.posY, this.posZ);
	}

	public boolean esImpacto() {
		Vec3 vec3 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX,
				this.posY, this.posZ);
		Vec3 vec31 = this.worldObj.getWorldVec3Pool().getVecFromPool(
				this.posX + this.motionX, this.posY + this.motionY,
				this.posZ + this.motionZ);
		MovingObjectPosition movingobjectposition = this.worldObj.clip(vec3,
				vec31);
		vec3 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX,
				this.posY, this.posZ);
		vec31 = this.worldObj.getWorldVec3Pool().getVecFromPool(
				this.posX + this.motionX, this.posY + this.motionY,
				this.posZ + this.motionZ);

		if (movingobjectposition != null) {
			vec31 = this.worldObj.getWorldVec3Pool().getVecFromPool(
					movingobjectposition.hitVec.xCoord,
					movingobjectposition.hitVec.yCoord,
					movingobjectposition.hitVec.zCoord);
		}
		Entity entity = null;

		if (!this.worldObj.isRemote) {
			List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(
					this,
					this.boundingBox.addCoord(this.motionX, this.motionY,
							this.motionZ).expand(1.0D, 1.0D, 1.0D));
			double d0 = 0.0D;

			for (int j = 0; j < list.size(); ++j) {
				Entity entity1 = (Entity) list.get(j);
				if (entity1.canBeCollidedWith() && (this.ticksInAir >= 5)) {
					float f = 0.3F;
					AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(
							(double) f, (double) f, (double) f);
					MovingObjectPosition movingobjectposition1 = axisalignedbb
							.calculateIntercept(vec3, vec31);

					if (movingobjectposition1 != null) {
						double d1 = vec3
								.distanceTo(movingobjectposition1.hitVec);

						if (d1 < d0 || d0 == 0.0D) {
							entity = entity1;
							d0 = d1;
						}
					}
				}
			}

			if (entity != null) {
				movingobjectposition = new MovingObjectPosition(entity);
			}
		}

		if (movingobjectposition != null) {
			int block = this.worldObj.getBlockId((int) this.posX,
					(int) this.posY, (int) this.posZ);

			if ((movingobjectposition.typeOfHit == EnumMovingObjectType.ENTITY && !(EntityPlayer.class
					.isAssignableFrom(entity.getClass())))
					|| (block != Block.portal.blockID && block != 0)) {
				onImpact(movingobjectposition);
				return true;
			}

		}
		return false;
	}

	protected float getMotionFactor() {
		return 0.95F;
	}

	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		par1NBTTagCompound.setShort("xTile", (short) this.xTile);
		par1NBTTagCompound.setShort("yTile", (short) this.yTile);
		par1NBTTagCompound.setShort("zTile", (short) this.zTile);
		par1NBTTagCompound.setByte("inTile", (byte) this.inTile);
		par1NBTTagCompound.setByte("inGround", (byte) (this.inGround ? 1 : 0));
		par1NBTTagCompound.setTag(
				"direction",
				this.newDoubleNBTList(new double[] { this.motionX,
						this.motionY, this.motionZ }));

	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		this.xTile = par1NBTTagCompound.getShort("xTile");
		this.yTile = par1NBTTagCompound.getShort("yTile");
		this.zTile = par1NBTTagCompound.getShort("zTile");
		this.inTile = par1NBTTagCompound.getByte("inTile") & 255;
		this.inGround = par1NBTTagCompound.getByte("inGround") == 1;

		if (par1NBTTagCompound.hasKey("direction")) {
			NBTTagList nbttaglist = par1NBTTagCompound.getTagList("direction");
			this.motionX = ((NBTTagDouble) nbttaglist.tagAt(0)).data;
			this.motionY = ((NBTTagDouble) nbttaglist.tagAt(1)).data;
			this.motionZ = ((NBTTagDouble) nbttaglist.tagAt(2)).data;
		} else {
			this.setDead();
		}
	}

	public float getCollisionBorderSize() {
		return 1.0F;
	}

	protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {

	}

	public boolean canBeCollidedWith() {
		return false;
	}

}
