package items;

import main.Champion;
import main.DamageTester;
import main.DamageTester.Damage;

public class WitsEnd extends Item
{

	@Override
	public boolean itemMythical()
	{
		return false;
	}

	@Override
	public int itemCost()
	{
		return 3100;
	}

	@Override
	public int itemBaseAD()
	{
		return 40;
	}

	@Override
	public int itemBaseAS()
	{
		return 40;
	}

	@Override
	public int itemBaseMR()
	{
		return 40;
	}

	@Override
	public void onHit(Champion c, Champion target, Damage d)
	{
		d.baseValue += DamageTester.applyDamageNoEffect(target, d.build, 1, 80);
	}
	
	@Override
	public String itemImg() {
		return "data/item/3091.png";
	}
}
