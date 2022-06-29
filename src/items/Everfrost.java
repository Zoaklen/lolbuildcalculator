package items;

import main.Champion;
import main.Main;

public class Everfrost extends Item
{

	@Override
	public boolean itemMythical()
	{
		return true;
	}

	@Override
	public int itemCost()
	{
		return 2800;
	}

	@Override
	public int itemBaseAP()
	{
		return 70;
	}

	@Override
	public int itemBaseHEALTH()
	{
		return 250;
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
		c.addAp(10 * (Main.QUANT-1));
	}
	
	@Override
	public String itemImg() {
		return "data/item/6656.png";
	}
}
