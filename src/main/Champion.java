package main;

public class Champion
{
	public int ad;
	public float asBase;
	public float asRatio;
	public int asExtraBase;
	public int health;
	public int damageHealth;
	public int mana;
	public int armor;
	public int mr;
	public boolean ranged;
	public float critMult;
	
	private int totalAd;
	private int totalAp;
	private int totalAs;
	private int totalCrit;
	private int totalHealth;
	private int totalMana;
	private int totalArmor;
	private int totalMr;
	private int totalCdr;
	private int totalLifesteal;
	private int totalOmnivamp;
	private int totalLethality;
	private int totalPhysPen;
	private int totalMagicPen;
	private int totalFlatMagicPen;
	
	public Champion()
	{
		
	}
	
	public Champion(int health, int armor, int mr)
	{
		this.health = health;
		this.armor = armor;
		this.mr = mr;
	}
	
	public void resetStatus()
	{
		this.setCritMult(1.75f);
		this.setTotalAd(this.ad);
		this.setTotalAp(0);
		this.setTotalAs(this.asExtraBase);
		this.setTotalCrit(0);
		this.setTotalHealth(this.health);
		this.setTotalMana(this.mana);
		this.setTotalArmor(this.armor);
		this.setTotalMr(this.mr);
		this.setTotalCdr(0);
		this.setTotalLifesteal(0);
		this.setTotalOmnivamp(0);
		this.setTotalLethality(0);
		this.setTotalPhysPen(0);
		this.setTotalMagicPen(0);
		this.setTotalFlatMagicPen(0);
	}
	
	public float getFinalAttackSpeed()
	{
    	return Math.min(this.asBase + this.asRatio * this.getTotalAs()/100f, 2.5f);
	}

	public void addAd(int quant)
	{
		this.setTotalAd(this.getTotalAd() + quant);
	}

	public void addAp(int quant)
	{
		this.setTotalAp(this.getTotalAp() + quant);
	}

	public void addAs(int quant)
	{
		this.setTotalAs(this.getTotalAs() + quant);
	}

	public void addCrit(int quant)
	{
		this.setTotalCrit(this.getTotalCrit() + quant);
	}

	public void addHealth(int quant)
	{
		this.setTotalHealth(this.getTotalHealth() + quant);
	}

	public void addMana(int quant)
	{
		this.setTotalMana(this.getTotalMana() + quant);
	}

	public void addArmor(int quant)
	{
		this.setTotalArmor(this.getTotalArmor() + quant);
	}

	public void addMr(int quant)
	{
		this.setTotalMr(this.getTotalMr() + quant);
	}

	public void addCdr(int quant)
	{
		this.setTotalCdr(this.getTotalCdr() + quant);
	}

	public void addLifesteal(int quant)
	{
		this.setTotalLifesteal(this.getTotalLifesteal() + quant);
	}

	public void addOmnivamp(int quant)
	{
		this.setTotalOmnivamp(this.getTotalOmnivamp() + quant);
	}

	public void addLethality(int quant)
	{
		this.setTotalLethality(this.getTotalLethality() + quant);
	}

	public void addPhysPen(int quant)
	{
		this.setTotalPhysPen(this.getTotalPhysPen() + quant);
	}

	public void addMagicPen(int quant)
	{
		this.setTotalMagicPen(this.getTotalMagicPen() + quant);
	}

	public void addFlatMagicPen(int quant)
	{
		this.setTotalFlatMagicPen(this.getTotalFlatMagicPen() + quant);
	}

	public float getCritMult()
	{
		return critMult;
	}

	public void setCritMult(float critMult)
	{
		this.critMult = critMult;
	}

	public int getTotalAd()
	{
		return totalAd;
	}
	
	public int getBonusAd()
	{
		return this.getTotalAd()-this.ad;
	}

	public void setTotalAd(int totalAd)
	{
		this.totalAd = totalAd;
	}

	public int getTotalAp()
	{
		return totalAp;
	}

	public void setTotalAp(int totalAp)
	{
		this.totalAp = totalAp;
	}

	public int getTotalAs()
	{
		return totalAs;
	}

	public void setTotalAs(int totalAs)
	{
		this.totalAs = totalAs;
	}

	public int getTotalCrit()
	{
		return totalCrit;
	}

	public void setTotalCrit(int totalCrit)
	{
		this.totalCrit = totalCrit;
	}

	public int getTotalHealth()
	{
		return totalHealth;
	}
	
	public int getBonusHealth()
	{
		return this.getTotalHealth()-this.health;
	}

	public void setTotalHealth(int totalHealth)
	{
		this.totalHealth = totalHealth;
		this.damageHealth = totalHealth;
	}

	public int getTotalMana()
	{
		return totalMana;
	}

	public void setTotalMana(int totalMana)
	{
		this.totalMana = this.mana <= 0 ? 0 : totalMana;
	}

	public int getTotalArmor()
	{
		return totalArmor;
	}

	public void setTotalArmor(int totalArmor)
	{
		this.totalArmor = totalArmor;
	}

	public int getTotalMr()
	{
		return totalMr;
	}

	public void setTotalMr(int totalMr)
	{
		this.totalMr = totalMr;
	}

	public int getTotalCdr()
	{
		return totalCdr;
	}

	public void setTotalCdr(int totalCdr)
	{
		this.totalCdr = totalCdr;
	}

	public int getTotalLifesteal()
	{
		return totalLifesteal;
	}

	public void setTotalLifesteal(int totalLifesteal)
	{
		this.totalLifesteal = totalLifesteal;
	}

	public int getTotalOmnivamp()
	{
		return totalOmnivamp;
	}

	public void setTotalOmnivamp(int totalOmnivamp)
	{
		this.totalOmnivamp = totalOmnivamp;
	}

	public int getTotalLethality()
	{
		return totalLethality;
	}

	public void setTotalLethality(int totalLethality)
	{
		this.totalLethality = totalLethality;
	}

	public int getTotalPhysPen()
	{
		return totalPhysPen;
	}

	public void setTotalPhysPen(int totalPhysPen)
	{
		this.totalPhysPen = totalPhysPen;
	}

	public int getTotalMagicPen()
	{
		return totalMagicPen;
	}

	public void setTotalMagicPen(int totalMagicPen)
	{
		this.totalMagicPen = totalMagicPen;
	}

	public int getTotalFlatMagicPen()
	{
		return totalFlatMagicPen;
	}

	public void setTotalFlatMagicPen(int totalFlatMagicPen)
	{
		this.totalFlatMagicPen = totalFlatMagicPen;
	}
	
	
}
