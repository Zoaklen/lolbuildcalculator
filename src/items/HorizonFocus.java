package items;

public class HorizonFocus extends Item
{
	public static boolean enablePassive = true;
	
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
	public int itemBaseAP()
	{
		return 85;
	}

	@Override
	public int itemBaseHEALTH()
	{
		return 150;
	}

	@Override
	public int itemBaseCDR()
	{
		return 15;
	}

	
}
