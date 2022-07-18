package items;

public class ChempunkChainsword extends Item
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
	public int itemBaseHEALTH()
	{
		return 250;
	}

	@Override
	public int itemBaseCDR()
	{
		return 25;
	}
	
	@Override
	public String itemImg() {
		return "data/item/6609.png";
	}
	
}
