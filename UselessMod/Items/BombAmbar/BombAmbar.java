package UselessMod.Items.BombAmbar;

import ibxm.Player;
import UselessMod.Auxiliares.Registros;
import UselessMod.Configuracion.Datos;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BombAmbar extends Item {

	public BombAmbar(int par1) {
		super(par1);
		this.setCreativeTab(Datos.tabMod);

	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		if (!par3EntityPlayer.capabilities.isCreativeMode) {
			--par1ItemStack.stackSize;
		}

		if (!par2World.isRemote) {

			par2World.spawnEntityInWorld(new EntityBomb(par2World,
					par3EntityPlayer, false));
		}

		return par1ItemStack;
	}

	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = Registros.RegisterIcons(par1IconRegister, "Bomba");

	}

}
