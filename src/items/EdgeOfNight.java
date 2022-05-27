package items;

public class EdgeOfNight extends Item
{
	@Override
	public boolean itemMythical()
	{
		return false;
	}

	@Override
	public int itemCost()
	{
		return 2900;
	}

	@Override
	public int itemBaseAD()
	{
		return 50;
	}

	@Override
	public int itemBaseHEALTH()
	{
		return 325;
	}

	@Override
	public int itemBaseLETHALITY()
	{
		return 10;
	}
	
}
