package items;

import main.Champion;
import main.Main;

public class Goredrinker extends Item
{

	@Override
	public boolean itemMythical()
	{
		return true;
	}

	@Override
	public int itemCost()
	{
		return 3300;
	}

	@Override
	public int itemBaseAD()
	{
		return 55;
	}

	@Override
	public int itemBaseHEALTH()
	{
		return 300;
	}

	@Override
	public int itemBaseCDR()
	{
		return 20;
	}

	@Override
	public int itemBaseOMNIVAMP()
	{
		return 8;
	}

	@Override
	public void itemExtraStatus(Champion c)
	{
		c.addCdr(5 * (Main.QUANT-1));
	}
	
	@Override
	public String itemImg() {
		return "data/item/6630.png";
	}
	
}
