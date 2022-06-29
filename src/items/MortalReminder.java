package items;

public class MortalReminder extends Item
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
	public int itemBaseAD()
	{
		return 25;
	}

	@Override
	public int itemBaseAS()
	{
		return 25;
	}

	@Override
	public int itemBaseCRIT()
	{
		return 20;
	}
	
	@Override
	public String itemImg() {
		return "data/item/3033.png";
	}
	
}
