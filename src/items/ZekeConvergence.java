package items;

public class ZekeConvergence extends Item
{

	@Override
	public boolean itemMythical()
	{
		return false;
	}

	@Override
	public int itemCost()
	{
		return 2400;
	}

	@Override
	public int itemBaseHEALTH()
	{
		return 250;
	}

	@Override
	public int itemBaseMANA()
	{
		return 250;
	}

	@Override
	public int itemBaseARMOR()
	{
		return 25;
	}

	@Override
	public int itemBaseCDR()
	{
		return 20;
	}
	
}
