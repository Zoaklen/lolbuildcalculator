package items;

public class Galeforce extends Item
{

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
		return 60;
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
	public String itemImg() {
		return "data/item/6671.png";
	}
	
}
