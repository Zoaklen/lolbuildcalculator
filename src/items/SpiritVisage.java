package items;

public class SpiritVisage extends Item
{

	@Override
	public boolean itemMythical()
	{
		return false;
	}

	@Override
	public int itemCost()
	{
		return 2900;
	}

	@Override
	public int itemBaseHEALTH()
	{
		return 450;
	}

	@Override
	public int itemBaseMR()
	{
		return 50;
	}

	@Override
	public int itemBaseCDR()
	{
		return 10;
	}
	
	@Override
	public String itemImg() {
		return "data/item/3065.png";
	}
	
}
