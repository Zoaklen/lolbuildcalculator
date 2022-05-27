package items;

import main.Champion;

public class InfinityEdge extends Item
{
	@Override
	public boolean itemMythical()
	{
		return false;
	}

	@Override
	public int itemCost()
	{
		return 3400;
	}

	@Override
	public int itemBaseAD()
	{
		return 70;
	}

	@Override
	public int itemBaseCRIT()
	{
		return 20;
	}
	
	@Override
	public void itemExtraStatus(Champion c)
	{
		if(c.getTotalCrit() >= 60)
		{
			c.critMult += 0.35f;
		}
	}
}
