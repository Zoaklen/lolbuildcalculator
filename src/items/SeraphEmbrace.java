package items;

import main.Champion;

public class SeraphEmbrace extends Item
{

	@Override
	public boolean itemMythical()
	{
		return false;
	}

	@Override
	public int itemCost()
	{
		return 2600;
	}

	@Override
	public int itemBaseAP()
	{
		return 80;
	}

	@Override
	public int itemBaseHEALTH()
	{
		return 250;
	}

	@Override
	public int itemBaseMANA()
	{
		return 860;
	}

	@Override
	public void itemExtraStatus(Champion c)
	{
		c.addCdr((int) ((c.getTotalMana() - c.mana) * 0.013f));
	}
}
