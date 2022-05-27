package items;

public class RylaiCrystalScepter extends Item
{

	@Override
	public boolean itemMythical()
	{
		return false;
	}

	@Override
	public int itemCost()
	{
		return 2600;
	}

	@Override
	public int itemBaseAP()
	{
		return 75;
	}

	@Override
	public int itemBaseHEALTH()
	{
		return 400;
	}
	
}
