package items;

public class RunaanHurricane extends Item
{
	@Override
	public boolean itemMythical()
	{
		return false;
	}

	@Override
	public int itemCost()
	{
		return 2600;
	}

	@Override
	public int itemBaseAS()
	{
		return 45;
	}

	@Override
	public int itemBaseCRIT()
	{
		return 20;
	}
	@Override
	public String itemImg() {
		return "data/item/3085.png";
	}
	
}
