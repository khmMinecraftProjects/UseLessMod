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

public class Ball extends Entity{
	ItemStack Proton;
    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private int inTile = 0;
    private boolean inGround = false;
    public EntityPlayer shootingEntity=null;
    private int ticksAlive;
    private int ticksInAir = 0;
    public double accelerationX;
    public double accelerationY;
    public double accelerationZ;
    int vida=800;
	int r=2,ry=20;

    public Ball(World par1World)
    {
        super(par1World);
        this.setSize(1.0F, 1.0F);
        r=Datos.Baston_TamX;
        ry=Datos.Baston_TamY;
        vida=Datos.Baston_TamZ;
    }

    protected void entityInit() {}

    @SideOnly(Side.CLIENT)

    /**
     * Checks if the entity is in range to render by using the past in distance and comparing it to its average edge
     * length * 64 * renderDistanceWeight Args: distance
     */
    public boolean isInRangeToRenderDist(double par1)
    {
        double d1 = this.boundingBox.getAverageEdgeLength() * 4.0D;
        d1 *= 64.0D;
        return par1 < d1 * d1;
    }


    public Ball(World par1World, EntityPlayer par3EntityPlayer, double par3, double par5, double par7)
    {
        super(par1World);
        this.shootingEntity = par3EntityPlayer;
        this.setSize(1.0F, 1.0F);
        this.setLocationAndAngles(par3EntityPlayer.posX, par3EntityPlayer.posY, par3EntityPlayer.posZ, par3EntityPlayer.rotationYaw, par3EntityPlayer.rotationPitch);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.yOffset = 0.0F;
        this.motionX = this.motionY = this.motionZ = 0.0D;
        
        double d3 = (double)MathHelper.sqrt_double(par3 * par3 + par5 * par5 + par7 * par7);
        this.accelerationX = par3 / d3 * 0.1D;
        this.accelerationY = par5 / d3 * 0.1D;
        this.accelerationZ = par7 / d3 * 0.1D;
        this.motionX += this.accelerationX;
        this.motionY += this.accelerationY;
        this.motionZ += this.accelerationZ;
        
    }

    /**
     * Called to update the entity's position/logic.
     */
	float dist=0,difX=0,difY=0,difZ=0;

    public void onUpdate()
    {
    	for(int i=0;i<5;i++){
        posX+=motionX;
        posY+=motionY;
        posZ+=motionZ;
        this.setPosition(this.posX,this.posY, this.posZ);
        abrirMar();
        this.ticksAlive++;
    	if(this.ticksAlive>vida){
            this.setDead(); 
    	}
    	}
    }

    public void abrirMar(){
    	for(int i=-ry;i<=ry;i++){
    	for(int o=-r;o<=r;o++){
    		for(int e=-r;e<=r;e++){
    		Bloque((int)posX+o,(int)posY+i,(int)posZ+e);
    	}}
    	}
    }
    public void Bloque(int x,int y,int z){

    	if(esLiquido(worldObj,x,y,z)){
    		this.worldObj.setBlock(x, y, z, Datos.idTransparente);
    	}
    }
    public static boolean esLiquido(World world,int x,int y,int z){
    	List<Integer> ids= Arrays.asList(8,9,10,11);
    	int id=world.getBlockId(x, y, z);
    	if(ids.contains(id)){
    		return true;
    	}
    	return false;
    }

    /**
     * Return the motion factor for this projectile. The factor is multiplied by the original motion.
     */
    protected float getMotionFactor()
    {
        return 0.95F;
    }

    /**
     * Called when this EntityFireball hits a block or entity.
     */

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        par1NBTTagCompound.setShort("xTile", (short)this.xTile);
        par1NBTTagCompound.setShort("yTile", (short)this.yTile);
        par1NBTTagCompound.setShort("zTile", (short)this.zTile);
        par1NBTTagCompound.setByte("inTile", (byte)this.inTile);
        par1NBTTagCompound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
        par1NBTTagCompound.setTag("direction", this.newDoubleNBTList(new double[] {this.motionX, this.motionY, this.motionZ}));

    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        this.xTile = par1NBTTagCompound.getShort("xTile");
        this.yTile = par1NBTTagCompound.getShort("yTile");
        this.zTile = par1NBTTagCompound.getShort("zTile");
        this.inTile = par1NBTTagCompound.getByte("inTile") & 255;
        this.inGround = par1NBTTagCompound.getByte("inGround") == 1;

        if (par1NBTTagCompound.hasKey("direction"))
        {
            NBTTagList nbttaglist = par1NBTTagCompound.getTagList("direction");
            this.motionX = ((NBTTagDouble)nbttaglist.tagAt(0)).data;
            this.motionY = ((NBTTagDouble)nbttaglist.tagAt(1)).data;
            this.motionZ = ((NBTTagDouble)nbttaglist.tagAt(2)).data;
        }
        else
        {
            this.setDead();
        }
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */


    public float getCollisionBorderSize()
    {
        return 1.0F;
    }

    @SideOnly(Side.CLIENT)
    public float getShadowSize()
    {
        return 0.0F;
    }

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness(float par1)
    {
        return 1.0F;
    }

    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float par1)
    {
        return 15728880;
    }
    
   
	protected void onImpact(MovingObjectPosition par1MovingObjectPosition)
     {

             


             if (!this.worldObj.isRemote)
             {
                     this.setDead();
             }
     }
     
  
    public boolean canBeCollidedWith()
	    {
	        return false;
	    }




}