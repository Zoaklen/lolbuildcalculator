package items;

import main.Champion;
import main.DamageTester;
import main.Main;
import main.DamageTester.Damage;

public class DivineSunderer extends Item
{
	@Override
	public boolean itemMythical()
	{
		return true;
	}

	@Override
	public int itemCost()
	{
		return 3300;
	}

	@Override
	public int itemBaseAD()
	{
		return 40;
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
		c.addPhysPen(5 * (Main.QUANT-1));
		c.addMagicPen(5 * (Main.QUANT-1));
	}

	@Override
	public void onHit(Champion c, Champion target, Damage d)
	{
		this.enableEffects = false;
		float life = 0.12f;
		if(c.ranged)
			life = 0.09f;

		int dmg = (int)(target.getTotalHealth() * life);
		this.stacks++;
		if(Main.itemInBuild(EssenceReaver.class, d.build))
		{
			if((int)(c.ad + c.getBonusAd() * 0.4f) > dmg)
				return;
		}
		if(Main.itemInBuild(LichBane.class, d.build))
		{
			int damage = (int)(c.ad * 0.75f + c.getTotalAp() * 0.5f);
			if(damage > dmg)
				return;
		}
		if(Main.itemInBuild(TrinityForce.class, d.build))
		{
			if(c.ad*2 > dmg)
				return;
		}
		d.baseValue += DamageTester.applyDamageNoEffect(target, d.build, 0, dmg);
	}
}
