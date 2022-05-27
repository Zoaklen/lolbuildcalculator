package items;

public class SpiritVisage extends Item
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
	public int itemBaseHEALTH()
	{
		return 450;
	}

	@Override
	public int itemBaseMR()
	{
		return 40;
	}

	@Override
	public int itemBaseCDR()
	{
		return 10;
	}
	
}
