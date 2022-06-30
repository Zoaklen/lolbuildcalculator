package items;

import main.Champion;
import main.Main;

public class SunfireAegis extends Item
{

	@Override
	public boolean itemMythical()
	{
		return true;
	}

	@Override
	public int itemCost()
	{
		return 3200;
	}

	@Override
	public int itemBaseHEALTH()
	{
		return 450;
	}

	@Override
	public int itemBaseARMOR()
	{
		return 35;
	}

	@Override
	public int itemBaseMR()
	{
		return 35;
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
	}
	
	@Override
	public String itemImg() {
		return "data/item/3068.png";
	}
}

