package items;

import main.Champion;
import main.DamageTester.Damage;

public class BlackCleaver extends Item
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
		return 45;
	}

	@Override
	public int itemBaseHEALTH()
	{
		return 350;
	}

	@Override
	public int itemBaseCDR()
	{
		return 30;
	}
	
	@Override
	public void onDamage(Champion c, Champion target, Damage d)
	{
		if(d.physMagicTrue == 0)
		{
			if(this.stacks < 6)
			{
				target.addArmor((int) (-target.armor * 0.05f));
				this.stacks++;
			}
		}
	}
}
