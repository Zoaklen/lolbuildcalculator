package items;

public class ForceOfNature extends Item
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
	public int itemBaseHEALTH()
	{
		return 350;
	}

	@Override
	public int itemBaseMR()
	{
		return 70;
	}
	
	@Override
	public String itemImg() {
		return "data/item/4401.png";
	}
}
