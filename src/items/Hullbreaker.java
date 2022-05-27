package items;

import main.Champion;

public class Hullbreaker extends Item
{
	public static boolean teamTogether = false;
	
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
	public int itemBaseAD()
	{
		return 50;
	}

	@Override
	public int itemBaseHEALTH()
	{
		return 400;
	}

	@Override
	public void itemExtraStatus(Champion c)
	{
		if(teamTogether)
			return;
		
		int armor = 75;
		int mr = 75;
		if(c.ranged)
		{
			armor /= 2;
			mr /= 2;
		}
	}
	
}
