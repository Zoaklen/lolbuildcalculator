package items;

public class UmbralGlaive extends Item
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
	public int itemBaseAD()
	{
		return 50;
	}

	@Override
	public int itemBaseCDR()
	{
		return 15;
	}

	@Override
	public int itemBaseLETHALITY()
	{
		return 10;
	}
	
	@Override
	public String itemImg() {
		return "data/item/3179.png";
	}
	
}
