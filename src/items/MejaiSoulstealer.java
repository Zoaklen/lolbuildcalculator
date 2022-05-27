package items;

public class MejaiSoulstealer extends Item
{
	public static int mejaiStacks = 25;
	@Override
	public boolean itemMythical()
	{
		return false;
	}

	@Override
	public int itemCost()
	{
		return 1600;
	}

	@Override
	public int itemBaseAP()
	{
		return 20 + mejaiStacks * 5;
	}

	@Override
	public int itemBaseHEALTH()
	{
		return 100;
	}
	
}
