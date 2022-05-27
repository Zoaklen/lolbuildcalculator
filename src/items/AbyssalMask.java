package items;

import main.Champion;

public class AbyssalMask extends Item
{
	@Override
	public boolean itemMythical()
	{
		return false;
	}

	@Override
	public int itemCost()
	{
		return 2700;
	}

	@Override
	public int itemBaseHEALTH()
	{
		return 450;
	}

	@Override
	public int itemBaseMR()
	{
		return 30;
	}

	@Override
	public int itemBaseCDR()
	{
		return 10;
	}
	
	@Override
	public void startEffect(Champion c, Champion target)
	{
		target.addMr((int) -Math.max(0, Math.min(5 + c.getBonusHealth()*0.012f, 25)));
	}
}
