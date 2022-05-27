package items;

import main.Champion;
import main.DamageTester;
import main.Main;
import main.DamageTester.Damage;

public class Riftmaker extends Item
{
	public static boolean fullyStacked = true;
	
	@Override
	public boolean itemMythical()
	{
		return true;
	}

	@Override
	public int itemCost()
	{
		return 3200;
	}

	@Override
	public int itemBaseAP()
	{
		return 80;
	}

	@Override
	public int itemBaseHEALTH()
	{
		return 300;
	}

	@Override
	public int itemBaseCDR()
	{
		return 15;
	}

	@Override
	public int itemBaseOMNIVAMP()
	{
		return 8;
	}

	@Override
	public void itemExtraStatus(Champion c)
	{
		c.addOmnivamp(2 * (Main.QUANT-1));
		c.addAp(8 * (Main.QUANT-1));
	}
	
	@Override
	public void onDamage(Champion c, Champion target, Damage d)
	{
		if(fullyStacked)
			d.baseValue += DamageTester.applyDamageNoEffect(target, d.build, 2, (int)(d.baseValue * 0.09f));
	}
}
