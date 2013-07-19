package UselessMod.Blocks;

import UselessMod.Configuracion.Datos;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;



public class BlockTransparente extends Block {
	
	 public  BlockTransparente(int par1)
	    {
	        super(par1, Material.piston);
	        
	        this.setCreativeTab(Datos.tabMod);
	        this.setBlockBounds(0F, 0F, 0F, 0F, 0F, 0F);
	        
	    }

	 public boolean isOpaqueCube(){
		 return false;
	 }
	 @Override
	 public boolean isCollidable(){
		 return false;
	 }
	 public boolean renderAsNormalBlock()
	    {
	        return false;
	    }
	 public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	    {
	        return null;
	    }
	  public boolean isBlockReplaceable(World world, int x, int y, int z)
	    {
	        return true;
	    }
}
