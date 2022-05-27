package items;

public class FrozenHeart extends Item
{
	@Override
	public boolean itemMythical()
	{
		return false;
	}

	@Override
	public int itemCost()
	{
		return 2500;
	}

	@Override
	public int itemBaseMANA()
	{
		return 400;
	}

	@Override
	public int itemBaseARMOR()
	{
		return 80;
	}

	@Override
	public int itemBaseCDR()
	{
		return 20;
	}
	
}
