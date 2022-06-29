package items;

import main.Champion;
import main.DamageTester;
import main.DamageTester.Damage;

public class Stormrazor extends Item
{

	@Override
	public boolean itemMythical()
	{
		return false;
	}

	@Override
	public int itemCost()
	{
		return 2700;
	}

	@Override
	public int itemBaseAD()
	{
		return 40;
	}

	@Override
	public int itemBaseAS()
	{
		return 15;
	}

	@Override
	public int itemBaseCRIT()
	{
		return 20;
	}

	@Override
	public void onHit(Champion c, Champion target, Damage d)
	{
		this.enableEffects = false;
		d.baseValue += DamageTester.applyDamageNoEffect(target, d.build, 1, 120);
	}
	
	@Override
	public String itemImg() {
		return "data/item/3095.png";
	}
}
