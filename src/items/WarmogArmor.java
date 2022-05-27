package items;

public class WarmogArmor extends Item
{
	@Override
	public boolean itemMythical()
	{
		return false;
	}

	@Override
	public int itemCost()
	{
		return 3000;
	}

	@Override
	public int itemBaseHEALTH()
	{
		return 800;
	}

	@Override
	public int itemBaseCDR()
	{
		return 10;
	}
	
}
