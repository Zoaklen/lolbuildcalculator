package items;

import main.Champion;
import main.DamageTester;
import main.Main;
import main.DamageTester.Damage;

public class LiandryAnguish extends Item
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
		return 80;
	}

	@Override
	public int itemBaseMANA()
	{
		return 600;
	}

	@Override
	public int itemBaseCDR()
	{
		return 20;
	}

	@Override
	public void itemExtraStatus(Champion c)
	{
		c.addCdr(5 * (Main.QUANT-1));
	}

	@Override
	public void onSpell(Champion c, Champion target, Damage d)
	{
		d.baseValue += DamageTester.applyDamageNoEffect(target, d.build, 1, (int)(60 + c.getTotalAp() * 0.06f + target.getTotalHealth() * 0.04f));
	}
	@Override
	public String itemImg() {
		return "data/item/6653.png";
	}
}
