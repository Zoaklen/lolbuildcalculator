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
	
}
