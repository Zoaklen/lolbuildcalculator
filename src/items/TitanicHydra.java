package items;

import main.Champion;
import main.DamageTester;
import main.DamageTester.Damage;

public class TitanicHydra extends Item
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
		return 30;
	}

	@Override
	public int itemBaseHEALTH()
	{
		return 400;
	}

	@Override
	public void itemExtraStatus(Champion c)
	{
		c.addAd((int) (c.getBonusHealth() * 0.02f));
	}
	
	@Override
	public void onHit(Champion c, Champion target, Damage d)
	{
		int damage = (int)(4 + c.getTotalHealth() * 0.015f);
		if(c.ranged)
			damage = (int)(3 + c.getTotalHealth() * 0.01125f);
		d.baseValue += DamageTester.applyDamageNoEffect(target, d.build, 0, damage);
	}
	
	@Override
	public String itemImg() {
		return "data/item/3748.png";
	}
}
