package items;

import main.Champion;
import main.DamageTester;
import main.Main;
import main.DamageTester.Damage;

public class LichBane extends Item
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
		return 75;
	}

	@Override
	public int itemBaseCDR()
	{
		return 15;
	}
	
	@Override
	public void onHit(Champion c, Champion target, Damage d)
	{
		this.enableEffects = false;
		int damage = (int)(c.ad * 0.75f + c.getTotalAp() * 0.5f);
		this.stacks++;

		if(Main.itemInBuild(EssenceReaver.class, d.build))
		{
			if((int)(c.ad + c.getBonusAd() * 0.4f) > damage)
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
		if(Main.itemInBuild(TrinityForce.class, d.build))
		{
			if(c.ad*2 > damage)
				return;
		}
		d.baseValue += DamageTester.applyDamageNoEffect(target, d.build, 1, damage);
	}
	
	@Override
	public String itemImg() {
		return "data/item/3100.png";
	}
}
