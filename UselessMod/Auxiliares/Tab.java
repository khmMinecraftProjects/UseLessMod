package UselessMod.Auxiliares;

import UselessMod.base;
import UselessMod.Configuracion.Datos;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class Tab extends CreativeTabs {

	public Tab(String label) {
		super(label);
	}

	@SideOnly(Side.CLIENT)
	/**
	 * the itemID for the item to be displayed on the tab
	 */
	public int getTabIconItemIndex() {
		return base.items.get(Datos.getI("idRayo")).itemID;
	}
}
