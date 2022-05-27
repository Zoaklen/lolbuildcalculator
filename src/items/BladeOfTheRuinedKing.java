package items;

import main.Champion;
import main.DamageTester;
import main.DamageTester.Damage;

public class BladeOfTheRuinedKing extends Item
{
	@Override
	public boolean itemMythical()
	{
		return false;
	}

	@Override
	public int itemCost()
	{
		return 3300;
	}

	@Override
	public int itemBaseAD()
	{
		return 40;
	}

	@Override
	public int itemBaseAS()
	{
		return 25;
	}

	@Override
	public int itemBaseLIFESTEAL()
	{
		return 8;
	}

	@Override
	public void onHit(Champion c, Champion target, Damage d)
	{
		if(c.ranged)
			d.baseValue += DamageTester.applyDamage(target, d.build, 0, (int)(target.damageHealth * 0.08f), 0f, 0f, 0f, 0f, 0f, 0f, 0, false);
		else
			d.baseValue += DamageTester.applyDamage(target, d.build, 0, (int)(target.damageHealth * 0.12f), 0f, 0f, 0f, 0f, 0f, 0f, 0, false);
		
		if(stacks == 3)
			d.baseValue += DamageTester.applyDamage(target, d.build, 1, 150, 0f, 0f, 0f, 0f, 0f, 0f, 0, false);
	}
}
