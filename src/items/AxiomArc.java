package items;
 
public class AxiomArc extends Item
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
	public int itemBaseLETHALITY()
	{
		return 10;
	}

	@Override
	public int itemBaseCDR()
	{
		return 25;
	}
	
	@Override
	public String itemImg() {
		return "data/item/6696.png";
	}
	
}
