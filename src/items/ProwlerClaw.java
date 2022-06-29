package items;

import main.Champion;
import main.Main;

public class ProwlerClaw extends Item
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
		return 60;
	}

	@Override
	public int itemBaseCDR()
	{
		return 20;
	}

	@Override
	public int itemBaseLETHALITY()
	{
		return 18;
	}

	@Override
	public void itemExtraStatus(Champion c)
	{
		c.addLethality(5 * (Main.QUANT-1));
	}
	
	@Override
	public String itemImg() {
		return "data/item/6693.png";
	}
	
}
