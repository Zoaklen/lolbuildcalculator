package items;

import main.Champion;
import main.DamageTester.Damage;

public abstract class Item
{
	public String name;
	public boolean mythic;
	public int cost;
	public boolean enableEffects = true;
	
	protected int stacks = 0;

	public Item()
	{
		this.name = this.getName();
		this.cost = this.itemCost();
		this.mythic = this.itemMythical();
	}
	
	private String getName()
	{
		StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
		for(int i = 1;i < builder.length();i++)
		{
			if(Character.isUpperCase(builder.charAt(i)))
			{
				builder.insert(i, ' ');
				i++;
			}
		}
		return builder.toString();
	}
	
	public void resetStatus()
	{
		this.enableEffects = true;
		this.stacks = 0;
	}
	
	public abstract boolean itemMythical();
	public abstract int itemCost();

	public int itemBaseAD() {return 0;}
	public int itemBaseAP() {return 0;}
	public int itemBaseAS() {return 0;}
	public int itemBaseCRIT() {return 0;}
	public int itemBaseHEALTH() {return 0;}
	public int itemBaseMANA() {return 0;}
	public int itemBaseARMOR() {return 0;}
	public int itemBaseMR() {return 0;}
	public int itemBaseCDR() {return 0;}
	public int itemBaseLIFESTEAL() {return 0;}
	public int itemBaseOMNIVAMP() {return 0;}
	public int itemBaseLETHALITY() {return 0;}
	public int itemBasePHYSPEN() {return 0;}
	public int itemBaseMAGICPEN() {return 0;}
	public int itemBaseFLATMAGICPEN() {return 0;}

	public void itemExtraStatus(Champion c) {}

	public void beforeProcedure() { }

	public void afterProcedure() { }

	public void onHit(Champion c, Champion target, Damage d) { }
	public void onSpell(Champion c, Champion target, Damage d) { }
	public void onDamage(Champion c, Champion target, Damage d) { }
	public void startEffect(Champion c, Champion target) { }
}
