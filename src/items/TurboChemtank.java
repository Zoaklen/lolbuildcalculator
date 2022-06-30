package items;

import main.Champion;
import main.Main;

public class TurboChemtank extends Item
{

	@Override
	public boolean itemMythical()
	{
		return true;
	}

	@Override
	public int itemCost()
	{
		return 2800;
	}

	@Override
	public int itemBaseHEALTH()
	{
		return 450;
	}

	@Override
	public int itemBaseARMOR()
	{
		return 25;
	}

	@Override
	public int itemBaseMR()
	{
		return 25;
	}

	@Override
	public int itemBaseCDR()
	{
		return 20;
	}

	@Override
	public void itemExtraStatus(Champion c)
	{
		c.addHealth(50 * (Main.QUANT-1));
		c.addCdr(5 * (Main.QUANT-1));
	}
	
	@Override
	public String itemImg() {
		return "data/item/6664.png";
	}
	
}
