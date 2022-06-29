package items;

public class NavoriQuickblades extends Item
{
	@Override
	public boolean itemMythical()
	{
		return false;
	}

	@Override
	public int itemCost()
	{
		return 3400;
	}

	@Override
	public int itemBaseAD()
	{
		return 60;
	}

	@Override
	public int itemBaseCRIT()
	{
		return 20;
	}

	@Override
	public int itemBaseCDR()
	{
		return 30;
	}
	
	@Override
	public String itemImg() {
		return "data/item/6675.png";
	}
	
}
