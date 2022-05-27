package items;

import main.Champion;
import main.Main;

public class ImmortalShieldbow extends Item
{
	int malmortiusIndex;
	int sterakIndex;
	Item malmortius;
	Item sterak;
	
	@Override
	public boolean itemMythical()
	{
		return true;
	}

	@Override
	public int itemCost()
	{
		return 3400;
	}

	@Override
	public int itemBaseAD()
	{
		return 50;
	}

	@Override
	public int itemBaseAS()
	{
		return 20;
	}

	@Override
	public int itemBaseCRIT()
	{
		return 20;
	}

	@Override
	public int itemBaseLIFESTEAL()
	{
		return 8;
	}

	@Override
	public void itemExtraStatus(Champion c)
	{
		c.addAd(5 * (Main.QUANT-1));
		c.addHealth(70 * (Main.QUANT-1));
	}
	
	@Override
	public void beforeProcedure()
	{
		malmortiusIndex = -1;
		sterakIndex = -1;
		for(int i = 0;i < Main.legendaryItemList.length;i++)
		{
			if(Main.legendaryItemList[i] != null && Main.legendaryItemList[i].getClass() == MawOfMalmortius.class)
			{
				malmortiusIndex = i;
				malmortius = Main.legendaryItemList[i];
				Main.legendaryItemList[i] = null;
			}
			else if(Main.legendaryItemList[i] != null && Main.legendaryItemList[i].getClass() == SterakGage.class)
			{
				sterakIndex = i;
				sterak = Main.legendaryItemList[i];
				Main.legendaryItemList[i] = null;
			}
		}
	}
	
	@Override
	public void afterProcedure()
	{
		if(malmortiusIndex >= 0)
			Main.legendaryItemList[malmortiusIndex] = malmortius;
		if(sterakIndex >= 0)
			Main.legendaryItemList[sterakIndex] = sterak;
	}
}
