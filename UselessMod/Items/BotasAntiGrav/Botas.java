package UselessMod.Items.BotasAntiGrav;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import UselessMod.base;
import UselessMod.Auxiliares.Registros;
import UselessMod.Configuracion.Datos;
import UselessMod.Items.PiesJesus.ModelPie;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumHelper;

public class Botas extends ItemArmor {
	int[] damageReduceAmount;
	int key = Datos.getI("Botas_key");
	int velocidad = Datos.getI("Botas_Velocidad");

	public Botas(int par1) {
		super(par1, EnumArmorMaterial.GOLD, base.proxy.addArmors("botas"), 3);
		this.damageReduceAmount = new int[] { 0, 0, Datos.getI("Pies_Armor"), 0 };

		rotate = 0;
		antiGrav = false;
		this.setMaxDamage(Datos.getI("Pies_Durability"));

		this.setCreativeTab(Datos.tabMod);
	}

	boolean rest = true;

	public void onArmorTickUpdate(World world, EntityPlayer player,
			ItemStack itemStack) {

		if (!world.isRemote) {
			return;
		}
		if (Keyboard.isKeyDown(key)) {
			if (rest) {
				ChangeGrav();
				rest = false;

			}
		} else {
			rest = true;
		}

		rotate();

	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister Register) {
		this.itemIcon = Registros.RegisterIcons(Register, "botas");
	}

	int rotate;
	boolean antiGrav;

	public void setGrav(boolean b) {
		antiGrav = b;
	}

	public int getRotation() {
		return rotate;
	}

	public int rotate() {
		if (antiGrav && rotate < 180) {
			rotate += velocidad;
		}

		if (!antiGrav && rotate > 0) {
			rotate -= velocidad;
		}
		return rotate;
	}

	public void ChangeGrav() {
		antiGrav = !antiGrav;

	}

	public boolean getGrav() {
		return antiGrav;
	}

}
