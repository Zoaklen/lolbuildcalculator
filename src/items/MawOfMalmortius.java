package items;

public class MawOfMalmortius extends Item
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
	public int itemBaseAD()
	{
		return 55;
	}

	@Override
	public int itemBaseMR()
	{
		return 50;
	}

	@Override
	public int itemBaseCDR()
	{
		return 20;
	}
	
	@Override
	public String itemImg() {
		return "data/item/3156.png";
	}
}
