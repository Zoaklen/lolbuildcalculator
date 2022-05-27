package items;

public class StaffOfFlowingWater extends Item
{
	@Override
	public boolean itemMythical()
	{
		return false;
	}

	@Override
	public int itemCost()
	{
		return 2300;
	}

	@Override
	public int itemBaseAP()
	{
		return 50;
	}
	
}
