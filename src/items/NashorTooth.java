package items;

import main.Champion;
import main.DamageTester;
import main.DamageTester.Damage;

public class NashorTooth extends Item
{
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
		return 100;
	}

	@Override
	public int itemBaseAS()
	{
		return 50;
	}

	@Override
	public void onHit(Champion c, Champion target, Damage d)
	{
		d.baseValue += DamageTester.applyDamageNoEffect(target, d.build, 1, (int)(15 + c.getTotalAp() * 0.2f));
	}
}
