package items;

import main.Champion;
import main.DamageTester;
import main.DamageTester.Damage;

public class DeadManPlate extends Item
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
	public int itemBaseHEALTH()
	{
		return 300;
	}

	@Override
	public int itemBaseARMOR()
	{
		return 45;
	}
	
	@Override
	public void onHit(Champion c, Champion target, Damage d)
	{
		this.enableEffects = false;
		d.baseValue += DamageTester.applyDamage(target, d.build, 1, 40 + c.ad, 0f, 0f, 0f, 0f, 0f, 0f, 0, false);
	}
	
	@Override
	public String itemImg() {
		return "data/item/3742.png";
	}
}
