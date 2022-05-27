package items;

import main.Champion;

public class VigilantWardstone extends Item
{
	@Override
	public boolean itemMythical()
	{
		return false;
	}

	@Override
	public int itemCost()
	{
		return 1100;
	}

	@Override
	public int itemBaseHEALTH()
	{
		return 150;
	}

	@Override
	public int itemBaseCDR()
	{
		return 15;
	}

	@Override
	public void itemExtraStatus(Champion c)
	{
		c.addAd((int) (c.getBonusAd()*0.12f));
		c.addHealth((int) (c.getBonusHealth()*0.12f));
		c.addAp((int) (c.getTotalAp() * 0.12f));
		c.addCdr((int) (c.getTotalCdr() * 0.12f));
	}
}
