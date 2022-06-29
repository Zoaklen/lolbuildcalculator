package items;

public class BansheeVeil extends Item
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
	public int itemBaseMR()
	{
		return 45;
	}

	@Override
	public int itemBaseCDR()
	{
		return 10;
	}
	
	@Override
	public String itemImg() {
		return "data/item/3102.png";
	}
	
}
