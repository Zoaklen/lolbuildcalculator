package items;

import main.Champion;
import main.DamageTester;
import main.Main;
import main.DamageTester.Damage;

public class Eclipse extends Item
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
		return 55;
	}

	@Override
	public int itemBaseOMNIVAMP()
	{
		return 7;
	}

	@Override
	public int itemBaseLETHALITY()
	{
		return 18;
	}

	@Override
	public void itemExtraStatus(Champion c)
	{
		c.addPhysPen(4 * (Main.QUANT-1));
	}
	
	@Override
	public void onDamage(Champion c, Champion target, Damage d)
	{
		this.stacks++;
		if(this.stacks == 2)
		{
			float mult = !c.ranged ? 0.06f : 0.03f;
			d.baseValue += DamageTester.applyDamageNoEffect(target, d.build, 0, (int)(target.getTotalHealth() * mult));
		}
	}
	
	@Override
	public String itemImg() {
		return "data/item/6692.png";
	}
	
}
