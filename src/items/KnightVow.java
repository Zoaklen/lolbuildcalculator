package items;

public class KnightVow extends Item
{

	@Override
	public boolean itemMythical()
	{
		return false;
	}

	@Override
	public int itemCost()
	{
		return 2300;
	}

	@Override
	public int itemBaseHEALTH()
	{
		return 400;
	}

	@Override
	public int itemBaseCDR()
	{
		return 10;
	}
	@Override
	public String itemImg() {
		return "data/item/3109.png";
	}
}
