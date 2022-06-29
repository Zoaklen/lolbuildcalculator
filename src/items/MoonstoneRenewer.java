package items;

public class MoonstoneRenewer extends Item
{

	@Override
	public boolean itemMythical()
	{
		return true;
	}

	@Override
	public int itemCost()
	{
		return 2500;
	}

	@Override
	public int itemBaseAP()
	{
		return 40;
	}

	@Override
	public int itemBaseHEALTH()
	{
		return 200;
	}

	@Override
	public int itemBaseCDR()
	{
		return 20;
	}
	
	@Override
	public String itemImg() {
		return "data/item/6617.png";
	}
	
}
