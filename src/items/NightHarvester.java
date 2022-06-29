package items;

import main.Champion;
import main.DamageTester;
import main.Main;
import main.DamageTester.Damage;

public class NightHarvester extends Item
{

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
		return 90;
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
	public void itemExtraStatus(Champion c)
	{
		c.addCdr(5 * (Main.QUANT-1));
	}

	@Override
	public void onSpell(Champion c, Champion target, Damage d)
	{
		this.enableEffects = false;
		d.baseValue += DamageTester.applyDamageNoEffect(target, d.build, 1, (int)(125 + c.getTotalAp() * 0.15f));
	}
	
	@Override
	public String itemImg() {
		return "data/item/4636.png";
	}
}
