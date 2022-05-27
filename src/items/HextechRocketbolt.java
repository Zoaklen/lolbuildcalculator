package items;

import main.Champion;
import main.Main;

public class HextechRocketbolt extends Item
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
	public int itemBaseAP()
	{
		return 90;
	}

	@Override
	public int itemBaseHEALTH()
	{
		return 250;
	}

	@Override
	public int itemBaseCDR()
	{
		return 15;
	}

	@Override
	public int itemBaseFLATMAGICPEN()
	{
		return 6;
	}

	@Override
	public void itemExtraStatus(Champion c)
	{
		c.addFlatMagicPen(5 * (Main.QUANT-1));
	}
	
}
