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
	
}
