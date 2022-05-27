package items;

import main.Champion;
import main.DamageTester;
import main.Main;
import main.DamageTester.Damage;

public class FrostfireGauntlet extends Item
{

	@Override
	public boolean itemMythical()
	{
		return true;
	}

	@Override
	public int itemCost()
	{
		return 2800;
	}

	@Override
	public int itemBaseHEALTH()
	{
		return 350;
	}

	@Override
	public int itemBaseARMOR()
	{
		return 25;
	}

	@Override
	public int itemBaseMR()
	{
		return 25;
	}

	@Override
	public int itemBaseCDR()
	{
		return 20;
	}

	@Override
	public void itemExtraStatus(Champion c)
	{
		c.addHealth(100 * (Main.QUANT-1));
	}
	
	@Override
	public void onHit(Champion c, Champion target, Damage d)
	{
		this.enableEffects = false;
		int damage = 85;
		if(c.ranged)
			damage = 42;
		d.baseValue += DamageTester.applyDamageNoEffect(target, d.build, 1, damage);
	}
	
}
