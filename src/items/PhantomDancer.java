package items;

import main.Champion;
import main.DamageTester.Damage;

public class PhantomDancer extends Item
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
	public int itemBaseAD()
	{
		return 20;
	}

	@Override
	public int itemBaseAS()
	{
		return 25;
	}

	@Override
	public int itemBaseCRIT()
	{
		return 20;
	}
	
	@Override
	public void onHit(Champion c, Champion target, Damage d)
	{
		this.stacks++;
		if(this.stacks <= 3)
		{
			c.addAs(10);
		}
	}
	
}
