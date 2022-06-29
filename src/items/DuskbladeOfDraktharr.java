package items;

import main.Champion;
import main.DamageTester;
import main.Main;
import main.DamageTester.Damage;

public class DuskbladeOfDraktharr extends Item
{
	@Override
	public boolean itemMythical()
	{
		return true;
	}

	@Override
	public int itemCost()
	{
		return 3100;
	}

	@Override
	public int itemBaseAD()
	{
		return 60;
	}

	@Override
	public int itemBaseCDR()
	{
		return 20;
	}

	@Override
	public int itemBaseLETHALITY()
	{
		return 18;
	}

	@Override
	public void itemExtraStatus(Champion c)
	{
		c.addCdr(5 * (Main.QUANT-1));
	}
	
	@Override
	public void onHit(Champion c, Champion target, Damage d)
	{
		if(this.stacks == 0)
		{
			int damage = (int)(75 + c.getBonusAd()*0.03f);
			if(c.ranged)
				damage = (int)(55 + c.getBonusAd()*0.025f);
			d.baseValue += DamageTester.applyDamageNoEffect(target, d.build, 0, damage);
			this.stacks++;
		}
	}
	
	@Override
	public String itemImg() {
		return "data/item/6691.png";
	}
}
