package items;

import main.Main;

public class SeryldaGrudge extends Item
{
	int dominikIndex;
	Item dominik;
	
	@Override
	public boolean itemMythical()
	{
		return false;
	}

	@Override
	public int itemCost()
	{
		return 3200;
	}

	@Override
	public int itemBaseAD()
	{
		return 45;
	}

	@Override
	public int itemBaseCDR()
	{
		return 20;
	}

	@Override
	public int itemBasePHYSPEN()
	{
		return 30;
	}
	
	@Override
	public void beforeProcedure()
	{
		dominikIndex = -1;
		for(int i = 0;i < Main.legendaryItemList.length;i++)
		{
			if(Main.legendaryItemList[i] != null && Main.legendaryItemList[i].getClass() == LordDominikRegards.class)
			{
				dominikIndex = i;
				dominik = Main.legendaryItemList[i];
				Main.legendaryItemList[i] = null;
			}
		}
	}
	
	@Override
	public void afterProcedure()
	{
		if(dominikIndex >= 0)
			Main.legendaryItemList[dominikIndex] = dominik;
	}
	
	@Override
	public String itemImg() {
		return "data/item/6694.png";
	}
	
}
