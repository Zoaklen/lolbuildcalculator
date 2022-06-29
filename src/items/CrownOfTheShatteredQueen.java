package items;

import main.Champion;
import main.Main;

public class CrownOfTheShatteredQueen extends Item
{
	public static boolean enableShield = true;
	
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
	public int itemBaseAP()
	{
		return 60;
	}

	@Override
	public int itemBaseHEALTH()
	{
		return 250;
	}

	@Override
	public int itemBaseMANA()
	{
		return 600;
	}

	@Override
	public int itemBaseCDR()
	{
		return 20;
	}

	@Override
	public void itemExtraStatus(Champion c)
	{
		c.addAp(8 * (Main.QUANT-1));
		if(enableShield)
			c.addAp(40);
	}
	@Override
	public String itemImg() {
		return "data/item/4644.png";
	}
}
