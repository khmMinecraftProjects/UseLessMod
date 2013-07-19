package UselessMod.Items.PiesJesus;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import UselessMod.Auxiliares.Registros;
import UselessMod.Configuracion.Datos;
import UselessMod.Items.BastonMoises.Ball;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class PiesJesus extends ItemArmor{
	int []damageReduceAmount;
	int empuje;
	public PiesJesus(int par1,int armor) {
		super(par1, EnumArmorMaterial.DIAMOND,  armor
				, 3);
		
		this.damageReduceAmount =new int []{0,0,Datos.Pies_Armor,0};

		this.empuje=Datos.Pies_Empuje;	
		
	    this.setMaxDamage(Datos.Pies_Durability);

        this.setCreativeTab(Datos.tabMod);
	}

	  public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack)
	    {
		  int id=world.getBlockId((int)player.posX,(int)(player.posY),(int)player.posZ);
		  if(player.isInWater()||id==10||id==11)
		  {
			  player.moveEntity(0, empuje, 0);
			  
		  }
		  else if(Ball.esLiquido(world,(int)player.posX,(int)(player.posY-1.9),(int)player.posZ)&&player.motionY<-0.001)
		  {

			  player.motionY=0;
			if(player.worldObj.isRemote){
			if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
				  player.motionY=0.5;
			}}
		  }
		  
	    }
	 @SideOnly(Side.CLIENT)
	    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
	    {
	        return new ModelPie();
	 
	    }
	 @SideOnly(Side.CLIENT)
	    public void registerIcons(IconRegister Register)
	    {
	        this.itemIcon = Registros.RegisterIcons(Register,"pie");
	    }
}
