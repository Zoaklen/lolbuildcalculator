package items;

import main.Champion;

public class Fimbulwinter extends Item
{

	@Override
	public boolean itemMythical()
	{
		return false;
	}

	@Override
	public int itemCost()
	{
		return 2700;
	}

	@Override
	public int itemBaseHEALTH()
	{
		return 350;
	}

	@Override
	public int itemBaseMANA()
	{
		return 860;
	}

	@Override
	public int itemBaseCDR()
	{
		return 15;
	}

	@Override
	public void itemExtraStatus(Champion c)
	{
		c.addHealth((int) (c.getTotalMana() * 0.08f));
	}
	@Override
	public String itemImg() {
		return "data/item/3121.png";
	}
	
}
