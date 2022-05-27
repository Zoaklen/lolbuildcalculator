package items;

import main.Champion;
import main.Main;

public class Evenshroud extends Item
{

	@Override
	public boolean itemMythical()
	{
		return true;
	}

	@Override
	public int itemCost()
	{
		return 2500;
	}

	@Override
	public int itemBaseHEALTH()
	{
		return 200;
	}

	@Override
	public int itemBaseARMOR()
	{
		return 30;
	}

	@Override
	public int itemBaseMR()
	{
		return 30;
	}

	@Override
	public int itemBaseCDR()
	{
		return 20;
	}

	@Override
	public void itemExtraStatus(Champion c)
	{
		c.addArmor(5 * (Main.QUANT-1));
		c.addMr(5 * (Main.QUANT-1));
	}
	
}
