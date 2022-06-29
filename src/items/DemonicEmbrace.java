package items;

import main.Champion;
import main.DamageTester;
import main.DamageTester.Damage;

public class DemonicEmbrace extends Item
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
		return 60;
	}

	@Override
	public int itemBaseHEALTH()
	{
		return 450;
	}

	@Override
	public void itemExtraStatus(Champion c)
	{
		c.addAp((int) (c.getTotalHealth()*0.02f));
	}
	
	@Override
	public void onSpell(Champion c, Champion target, Damage d)
	{
		if(c.ranged)
		{
			d.baseValue += DamageTester.applyDamageNoEffect(target, d.build, 1, (int)(target.getTotalHealth() * 0.01f * 4f));
		}
		else
		{
			d.baseValue += DamageTester.applyDamageNoEffect(target, d.build, 1, (int)(target.getTotalHealth() * 0.018f * 4f));
		}
	}
}
