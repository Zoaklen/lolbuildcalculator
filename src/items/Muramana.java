package items;

import main.Champion;
import main.DamageTester;
import main.DamageTester.Damage;

public class Muramana extends Item
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
	public int itemBaseAD()
	{
		return 35;
	}

	@Override
	public int itemBaseMANA()
	{
		return 860;
	}

	@Override
	public int itemBaseCDR()
	{
		return 15;
	}

	@Override
	public void itemExtraStatus(Champion c)
	{
		c.addAd((int)(c.getTotalMana() * 0.025f));
	}
	
	@Override
	public void onHit(Champion c, Champion target, Damage d)
	{
		d.baseValue += DamageTester.applyDamageNoEffect(target, d.build, 0, (int)(c.getTotalMana() * 0.015f));
	}
	
	@Override
	public void onSpell(Champion c, Champion target, Damage d)
	{
		float scaling = 0.035f;
		if(c.ranged)
			scaling = 0.027f;
		d.baseValue += DamageTester.applyDamageNoEffect(target, d.build, 0, (int)(c.getTotalMana() * scaling + c.getTotalAd() * 0.06f));
	}
}
