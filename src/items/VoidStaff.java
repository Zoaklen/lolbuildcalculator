package items;

public class VoidStaff extends Item
{

	@Override
	public boolean itemMythical()
	{
		return false;
	}

	@Override
	public int itemCost()
	{
		return 2800;
	}

	@Override
	public int itemBaseAP()
	{
		return 65;
	}

	@Override
	public int itemBaseMAGICPEN()
	{
		return 45;
	}
	
}
