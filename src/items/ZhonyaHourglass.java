package items;

public class ZhonyaHourglass extends Item
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
		return 65;
	}

	@Override
	public int itemBaseARMOR()
	{
		return 45;
	}

	@Override
	public int itemBaseCDR()
	{
		return 10;
	}
	
}
