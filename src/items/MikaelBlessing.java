package items;

public class MikaelBlessing extends Item
{

	@Override
	public boolean itemMythical()
	{
		return false;
	}

	@Override
	public int itemCost()
	{
		return 2300;
	}

	@Override
	public int itemBaseMR()
	{
		return 50;
	}

	@Override
	public int itemBaseCDR()
	{
		return 15;
	}
	
	@Override
	public String itemImg() {
		return "data/item/3222.png";
	}
	
}
