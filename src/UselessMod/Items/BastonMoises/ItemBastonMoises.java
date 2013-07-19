package UselessMod.Items.BastonMoises;

import java.util.Random;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import UselessMod.Auxiliares.Registros;
import UselessMod.Configuracion.Datos;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class ItemBastonMoises extends Item {
	Ball ball;
	public ItemBastonMoises(int par1) {
		super(par1);
        this.setCreativeTab(Datos.tabMod);
        this.maxStackSize=1;
        
	}
	
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 72000;
    }

	public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = Registros.RegisterIcons(par1IconRegister,"baston");

    }
  public Icon getIcon(int side)
  {
    return this.itemIcon;
  }
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
		        {
			
		    	 double motionX = (double)(-MathHelper.sin(par3EntityPlayer.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(par3EntityPlayer.rotationPitch / 180.0F * (float)Math.PI));
		         double motionZ = (double)(MathHelper.cos(par3EntityPlayer.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(par3EntityPlayer.rotationPitch / 180.0F * (float)Math.PI));
		         double motionY = (double)(-MathHelper.sin(par3EntityPlayer.rotationPitch / 180.0F * (float)Math.PI));
		             
		                 
		             
		 		if (!par2World.isRemote)
		 		{
		 		   ball=new Ball(par2World, 
		 				   par3EntityPlayer,
		 				   motionX,motionY,motionZ);
		 		    par2World.spawnEntityInWorld(ball);
		 		   
		 		}
		 return par1ItemStack;
	}
	
	@Override
	 public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4) 
	 {
		ball.setDead();
		 ball=null;
	 }
}
