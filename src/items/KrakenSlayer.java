package items;

import main.Champion;
import main.DamageTester;
import main.Main;
import main.DamageTester.Damage;

public class KrakenSlayer extends Item
{

	@Override
	public boolean itemMythical()
	{
		return true;
	}

	@Override
	public int itemCost()
	{
		return 3400;
	}

	@Override
	public int itemBaseAD()
	{
		return 65;
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
	public void itemExtraStatus(Champion c)
	{
		c.addAs(10 * (Main.QUANT-1));
	}
	
	@Override
	public void onHit(Champion c, Champion target, Damage d)
	{
		this.stacks++;
		if(this.stacks % 3 == 0)
		{
			d.baseValue += DamageTester.applyDamageNoEffect(target, d.build, 2, (int)(60 + c.getBonusAd()*0.45f));
		}
	}
}
