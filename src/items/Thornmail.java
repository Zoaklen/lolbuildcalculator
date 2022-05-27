package items;

public class Thornmail extends Item
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
		return 350;
	}

	@Override
	public int itemBaseARMOR()
	{
		return 60;
	}
	
}
