package items;

public class YoumuuGhostblade extends Item
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
		return 55;
	}

	@Override
	public int itemBaseCDR()
	{
		return 15;
	}

	@Override
	public int itemBaseLETHALITY()
	{
		return 18;
	}
	
	@Override
	public String itemImg() {
		return "data/item/3142.png";
	}
}
