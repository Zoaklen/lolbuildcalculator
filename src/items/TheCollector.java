package items;

public class TheCollector extends Item
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
	public int itemBaseAD()
	{
		return 55;
	}

	@Override
	public int itemBaseCRIT()
	{
		return 20;
	}

	@Override
	public int itemBaseLETHALITY()
	{
		return 12;
	}
	@Override
	public String itemImg() {
		return "data/item/6676.png";
	}
}
