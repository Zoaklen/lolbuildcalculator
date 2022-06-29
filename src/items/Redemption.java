package items;

public class Redemption extends Item
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
		return 200;
	}

	@Override
	public int itemBaseCDR()
	{
		return 15;
	}
	@Override
	public String itemImg() {
		return "data/item/3107.png";
	}
}
