package items;

public class SerpentFang extends Item
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
	public int itemBaseAD()
	{
		return 55;
	}

	@Override
	public int itemBaseLETHALITY()
	{
		return 12;
	}
	
	@Override
	public String itemImg() {
		return "data/item/6695.png";
	}
}
