package items;

public class SilvermereDawn extends Item
{

	@Override
	public boolean itemMythical()
	{
		return false;
	}

	@Override
	public int itemCost()
	{
		return 3000;
	}

	@Override
	public int itemBaseAD()
	{
		return 40;
	}

	@Override
	public int itemBaseHEALTH()
	{
		return 300;
	}

	@Override
	public int itemBaseMR()
	{
		return 35;
	}
	
	@Override
	public String itemImg() {
		return "data/item/6035.png";
	}
}
