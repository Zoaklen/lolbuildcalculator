package items;

public class DeathDance extends Item
{
	@Override
	public boolean itemMythical()
	{
		return false;
	}

	@Override
	public int itemCost()
	{
		return 3300;
	}

	@Override
	public int itemBaseAD()
	{
		return 55;
	}

	@Override
	public int itemBaseARMOR()
	{
		return 45;
	}

	@Override
	public int itemBaseCDR()
	{
		return 15;
	}
	
}
