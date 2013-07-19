package UselessMod.Auxiliares;

import UselessMod.base;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public class Tab extends CreativeTabs {

	public Tab(String label) {
		super(label);
		// TODO Auto-generated constructor stub
	}

	@SideOnly(Side.CLIENT)

    /**
     * the itemID for the item to be displayed on the tab
     */
    public int getTabIconItemIndex()
    {
        return base.Baston.itemID;
    }
}
