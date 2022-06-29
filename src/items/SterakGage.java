package items;

import main.Champion;

public class SterakGage extends Item
{

	@Override
	public boolean itemMythical()
	{
		return false;
	}

	@Override
	public int itemCost()
	{
		return 3100;
	}

	@Override
	public int itemBaseHEALTH()
	{
		return 400;
	}

	@Override
	public void itemExtraStatus(Champion c)
	{
		c.addAd((int) (c.ad * 0.4f));
	}
	
	@Override
	public String itemImg() {
		return "data/item/3053.png";
	}
}
