package items;

import main.Champion;
import main.DamageTester.Damage;

public class CosmicDrive extends Item
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
		return 65;
	}

	@Override
	public int itemBaseHEALTH()
	{
		return 300;
	}

	@Override
	public int itemBaseCDR()
	{
		return 30;
	}
	
	@Override
	public void onSpell(Champion c, Champion target, Damage d)
	{
		this.stacks++;
		if(this.stacks == 3)
		{
			c.addAp(40);
		}
	}
	
	@Override
	public String itemImg() {
		return "data/item/4629.png";
	}
}
