package UselessMod.Blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

import UselessMod.Auxiliares.Registros;
import UselessMod.Configuracion.Datos;


public class Ambar extends Block{
	Icon ic;
	public Ambar(int id) {
		super(id,Material.glass);
        this.setCreativeTab(Datos.tabMod);
        this.blockHardness=Datos.Ambar_blockHardness;
	}
	public int quantityDropped(Random par1Random)
    {
        return 0;
    }
	 public int getRenderBlockPass()
	    {
	        return 1;
	    }
	public boolean isOpaqueCube(){
		 return false;
	 }
	  public void registerIcons(IconRegister par1IconRegister)
	    {
	        this.blockIcon = Registros.RegisterIcons(par1IconRegister,"ambar");
	    }
	  public Icon getIcon(int side)
	  {
	    return this.blockIcon;
	  }
}
