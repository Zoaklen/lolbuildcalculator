package items;

import main.Champion;
import main.DamageTester;
import main.Main;
import main.DamageTester.Damage;

public class TrinityForce extends Item
{

	@Override
	public boolean itemMythical()
	{
		return true;
	}

	@Override
	public int itemCost()
	{
		return 3333;
	}

	@Override
	public int itemBaseAD()
	{
		return 35;
	}

	@Override
	public int itemBaseAS()
	{
		return 30;
	}

	@Override
	public int itemBaseHEALTH()
	{
		return 300;
	}

	@Override
	public int itemBaseCDR()
	{
		return 20;
	}

	@Override
	public void itemExtraStatus(Champion c)
	{
		c.addAd(3 * (Main.QUANT-1));
		c.addCdr(3 * (Main.QUANT-1));
		c.addAd((int) (c.ad * 0.2f));
	}
	
	@Override
	public void onHit(Champion c, Champion target, Damage d)
	{
		this.enableEffects = false;
		int damage = (int)(c.ad * 2f);
		this.stacks++;

		if(Main.itemInBuild(EssenceReaver.class, d.build))
		{
			if((int)(c.ad + c.getBonusAd() * 0.4f) > damage)
				return;
		}
		if(Main.itemInBuild(LichBane.class, d.build))
		{
			int damage2 = (int)(c.ad * 0.75f + c.getTotalAp() * 0.5f);
			if(damage2 > damage)
				return;
		}
		if(Main.itemInBuild(DivineSunderer.class, d.build))
		{
			float life = 0.12f;
			if(c.ranged)
				life = 0.09f;

			int dmg = (int)(target.getTotalHealth() * life);
			if(dmg > damage)
				return;
		}

		d.baseValue += DamageTester.applyDamageNoEffect(target, d.build, 0, damage);
	}
	
	@Override
	public String itemImg() {
		return "data/item/3078.png";
	}
}
