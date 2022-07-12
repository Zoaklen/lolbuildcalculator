package items;

public class ChemtechPutrifier extends Item
{
	@Override
	public boolean itemMythical()
	{
		return false;
	}

	@Override
	public int itemCost()
	{
		return 2300;
	}

	@Override
	public int itemBaseAP()
	{
		return 60;
	}

	@Override
	public int itemBaseCDR()
	{
		return 20;
	}
	@Override
	public String itemImg() {
		return "data/item/3011.png";
	}
	
}
