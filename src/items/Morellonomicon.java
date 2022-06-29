package items;

public class Morellonomicon extends Item
{
	@Override
	public boolean itemMythical()
	{
		return false;
	}

	@Override
	public int itemCost()
	{
		return 2500;
	}

	@Override
	public int itemBaseAP()
	{
		return 80;
	}

	@Override
	public int itemBaseHEALTH()
	{
		return 250;
	}
	
	@Override
	public String itemImg() {
		return "data/item/3165.png";
	}
}
