package items;

import main.Champion;

public class Shadowflame extends Item
{
	@Override
	public boolean itemMythical()
	{
		return false;
	}

	@Override
	public int itemCost()
	{
		return 3000;
	}

	@Override
	public int itemBaseAP()
	{
		return 100;
	}

	@Override
	public int itemBaseHEALTH()
	{
		return 200;
	}
	
	@Override
	public String itemImg() {
		return "data/item/4645.png";
	}

	@Override
	public void itemExtraStatus(Champion c)
	{
		c.addFlatMagicPen(15);
	}
	
	
}
