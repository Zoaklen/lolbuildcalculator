package items;

import main.Champion;
import main.Main;
import main.DamageTester.Damage;

public class LordDominikRegards extends Item
{
	int seryldaIndex;
	Item serylda;
	
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
	public int itemBaseAD()
	{
		return 30;
	}

	@Override
	public int itemBaseCRIT()
	{
		return 20;
	}

	@Override
	public int itemBasePHYSPEN()
	{
		return 30;
	}
	
	@Override
	public void onDamage(Champion c, Champion target, Damage d)
	{
		if(d.physMagicTrue == 0)
		{
			float bonus = Math.max(Math.min(0.15f, 0.15f*(target.getTotalHealth()-c.getTotalHealth())/2000f), 0f);
			d.baseValue *= 1f + bonus;
		}
	}
	
	@Override
	public void beforeProcedure()
	{
		seryldaIndex = -1;
		for(int i = 0;i < Main.legendaryItemList.length;i++)
		{
			if(Main.legendaryItemList[i] != null && Main.legendaryItemList[i].getClass() == SeryldaGrudge.class)
			{
				seryldaIndex = i;
				serylda = Main.legendaryItemList[i];
				Main.legendaryItemList[i] = null;
			}
		}
	}
	
	@Override
	public void afterProcedure()
	{
		if(seryldaIndex >= 0)
			Main.legendaryItemList[seryldaIndex] = serylda;
	}
}
