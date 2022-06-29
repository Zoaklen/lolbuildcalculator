package items;

import main.Champion;

public class GargoyleStoneplate extends Item
{

	@Override
	public boolean itemMythical()
	{
		return false;
	}

	@Override
	public int itemCost()
	{
		return 3200;
	}

	@Override
	public int itemBaseARMOR()
	{
		return 60;
	}

	@Override
	public int itemBaseMR()
	{
		return 60;
	}

	@Override
	public int itemBaseCDR()
	{
		return 15;
	}

	@Override
	public void itemExtraStatus(Champion c)
	{
		c.setTotalArmor((int) (c.getTotalArmor() * 1.25f));
		c.setTotalMr((int) (c.getTotalMr() * 1.25f));
	}
	
	@Override
	public String itemImg() {
		return "data/item/3193.png";
	}
}
