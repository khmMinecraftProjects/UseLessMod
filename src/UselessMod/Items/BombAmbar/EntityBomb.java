package UselessMod.Items.BombAmbar;

import UselessMod.base;
import UselessMod.Configuracion.Datos;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityBomb extends EntityThrowable{

	public EntityBomb(World par1World)
    {
        super(par1World);
        this.setSize(1.0F, 1.00F);
    }


	  public EntityBomb (World par1World, EntityPlayer par2EntityPlayer)
	  {
	    super(par1World, par2EntityPlayer);
	  }
	boolean haExplotado,cubo=true;
	int ticks=0,tiempo=20,radio=20,max=tiempo*radio;
	public EntityBomb(World par1World, EntityLivingBase par2EntityLivingBase
			,boolean Explotado) {
		super(par1World, par2EntityLivingBase);
		haExplotado=Explotado;
		this.worldObj=par2EntityLivingBase.worldObj;
		tiempo=Datos.Ambar_Tiempo;
		radio=Datos.Ambar_Radio;
		cubo=Datos.Ambar_Cubo;
	}
	@Override
	public void onUpdate()
    {
		
		boolean op=false;
		if(haExplotado){
			if(ticks>max){
				this.setDead();
			}
			ticks++;
			
			if(ticks%5==0){
			
			int posicion=ticks/tiempo,pxy=posicion;
			for(int y=-posicion;y<=posicion;y++){
				if(!cubo){
				pxy=Math.abs(posicion-Math.abs(y));
				
				for(int x=-pxy;x<pxy;x++){
					for(int z=-pxy;z<pxy;z++){
					      
					spawnParticles(x,y,z);
					
					Ambarizar(x,y,z);
				

					}}
				
				}else{
				op=false;
			if(posicion==Math.abs(y)){
				op=true;

			}else{

			}
			
			for(int x=-pxy;x<=pxy;x++){
			if(op||pxy==Math.abs(x)){
			for(int z=-pxy;z<=pxy;z++){
			      
			spawnParticles(x,y,z);

			Ambarizar(x,y,z);
		

			}
			}else{

				spawnParticles(x,y,posicion);
				Ambarizar(x,y,posicion);
				spawnParticles(x,y,-posicion);
				Ambarizar(x,y,-posicion);
			}}
			
			}}
		
			}}else{
				super.onUpdate();
			}
		
		}
    
	public void Ambarizar(int x,int y,int z){
		if(!this.worldObj.isRemote){
		if(this.worldObj.getBlockId((int)(posX+x), 
				(int)(posY+y),
				(int)(posZ+z))==0){
			this.worldObj.setBlock((int)(posX+x), 
					(int)(posY+y), (int)(posZ+z), Datos.idAmbar);
		}}
	}
	@Override
	protected void onImpact(MovingObjectPosition movingobjectposition) {
		haExplotado=true;
		
	}

	public void spawnParticles(int x,int y,int z){
		   for (int i=-1;i<2;i+=2){	
			   for (int o=0;o<5;o++){
	      double r = 1;

	      double parX = Math.random() * r;
	      double parY = Math.random() * r;
	      double parZ = Math.random() * r;

	      this.worldObj.spawnParticle("smoke", 
	    		  posX+x + parX+i, 
	    		  posY+y + parY+i, 
	    		  posZ+z + parZ+i, 0.0D, 0.0D, 0.0D);
	   }}
	}
	 public void readFromNBT(NBTTagCompound NBT)
	    {
		 		super.readFromNBT(NBT);
		 		
		 ticks=NBT.getInteger("ticks");
	    }
	 public void writeToNBT(NBTTagCompound NBT)
	    {
	 		super.writeToNBT(NBT);
	 		NBT.setInteger("ticks", ticks);
	    }
}
