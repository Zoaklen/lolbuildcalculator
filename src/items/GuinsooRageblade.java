package items;

import main.Champion;
import main.DamageTester;
import main.DamageTester.Damage;

public class GuinsooRageblade extends Item
{
	@Override
	public boolean itemMythical()
	{
		return false;
	}

	@Override
	public int itemCost()
	{
		return 2600;
	}

	@Override
	public int itemBaseAS()
	{
		return 45;
	}

	@Override
	public int itemBaseCRIT()
	{
		return 20;
	}
	
	@Override
	public void onHit(Champion c, Champion target, Damage d)
	{
		d.baseValue += DamageTester.applyDamageNoEffect(target, d.build, 0, c.getTotalCrit()*2);
		this.stacks++;
		if(this.stacks == 3)
		{
			this.stacks = 0;
			d.baseValue += DamageTester.applyDamageNoEffect(target, d.build, 0, c.getTotalCrit()*2);
			for(Item i : d.build)
			{
				if(i != null && i != this && i.enableEffects)
				{
					i.onHit(c, target, d);
				}
			}
		}
	}
	
	@Override
	public String itemImg() {
		return "data/item/3124.png";
	}
}
