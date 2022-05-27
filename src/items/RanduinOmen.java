package items;

public class RanduinOmen extends Item
{
	@Override
	public boolean itemMythical()
	{
		return false;
	}

	@Override
	public int itemCost()
	{
		return 2700;
	}

	@Override
	public int itemBaseHEALTH()
	{
		return 250;
	}

	@Override
	public int itemBaseARMOR()
	{
		return 80;
	}

	@Override
	public int itemBaseCDR()
	{
		return 10;
	}
	
}
