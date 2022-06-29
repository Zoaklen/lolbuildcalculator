package items;

public class Stridebreaker extends Item
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
		return 50;
	}

	@Override
	public int itemBaseAS()
	{
		return 20;
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
	public String itemImg() {
		return "data/item/6631.png";
	}
}
