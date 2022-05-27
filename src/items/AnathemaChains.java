package items;

public class AnathemaChains extends Item
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
	public int itemBaseHEALTH()
	{
		return 650;
	}

	@Override
	public int itemBaseCDR()
	{
		return 20;
	}
	
}
