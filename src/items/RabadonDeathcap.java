package items;

import main.Champion;

public class RabadonDeathcap extends Item
{

	@Override
	public boolean itemMythical()
	{
		return false;
	}

	@Override
	public int itemCost()
	{
		return 3600;
	}

	@Override
	public int itemBaseAP()
	{
		return 120;
	}

	@Override
	public void itemExtraStatus(Champion c)
	{
		c.setTotalAp((int) (c.getTotalAp() * 1.35f));
	}
	
}
