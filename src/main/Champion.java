package main;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
	
	public static final Pattern perLevelPattern = Pattern.compile("\\d+\\.?\\d*");
	public static final Pattern nameExtractPattern = Pattern.compile("Health_([a-zA-Z]+)");
	
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
		this.setTotalPhysPen((int)Math.round((1f - ((1f - (this.getTotalPhysPen()/100f)) * (1f - quant/100f))) * 100f));
	}

	public void addMagicPen(int quant)
	{
		this.setTotalMagicPen((int)Math.round((1f - ((1f - (this.getTotalMagicPen()/100f)) * (1f - quant/100f))) * 100f));
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
	
	public static Champion tryGetChampionData(String name) throws MalformedURLException, IOException
	{
		name = name.replaceAll(" ", "_");
		Champion c = new Champion();
		Matcher m;
		Document doc = Jsoup.parse(new URL("https://leagueoflegends.fandom.com/wiki/"+name+"/LoL"), 10000);
		
		name = doc.selectFirst("*[id^='Health_']").id();
		m = nameExtractPattern.matcher(name);
		m.find();
		name = m.group(1);
		System.out.println(name);
		Element health = doc.getElementById("Health_"+name);
		Element healthLv = doc.getElementById("Health_"+name+"_lvl");

		Element mana = doc.getElementById("ResourceBar_"+name);
		Element manaLv = doc.getElementById("ResourceBar_"+name+"_lvl");
		
		Elements baseAs = doc.getElementsByAttributeValue("data-source", "attack speed");

		Elements asRatio = doc.getElementsByAttributeValue("data-source", "as ratio");

		Element baseBonusAs = doc.getElementById("AttackSpeedBonus_"+name+"_lvl");

		Element armor = doc.getElementById("Armor_"+name);
		Element armorLv = doc.getElementById("Armor_"+name+"_lvl");

		Element mr = doc.getElementById("MagicResist_"+name);
		Element mrLv = doc.getElementById("MagicResist_"+name+"_lvl");

		Elements ranged = doc.select("*[data-source=rangetype] > div > span > a:nth-child(2)");

		System.out.println(health.text() + " " + healthLv.text());
		try
		{
			System.out.println(mana.text() + " " + manaLv.text());
		}
		catch(Exception ex)
		{}
		System.out.println(baseAs.text());
		System.out.println(asRatio.text());
		System.out.println(baseBonusAs.text());
		System.out.println(armor.text() + " " + armorLv.text());
		System.out.println(mr.text() + " " + mrLv.text());
		System.out.println(ranged.text());
		
		m = perLevelPattern.matcher(healthLv.text());
		m.find();
		c.health = (int) (Float.parseFloat(health.text()) + Float.parseFloat(m.group()) * 17);

		try
		{
			m = perLevelPattern.matcher(manaLv.text());
			m.find();
			c.mana = (int) (Float.parseFloat(mana.text()) + Float.parseFloat(m.group()) * 17);
		}
		catch(Exception ex) {c.mana = 0;}
		
		m = perLevelPattern.matcher(armorLv.text());
		m.find();
		c.armor = (int) (Float.parseFloat(armor.text()) + Float.parseFloat(m.group()) * 17);
		
		m = perLevelPattern.matcher(mrLv.text());
		m.find();
		c.mr = (int) (Float.parseFloat(mr.text()) + Float.parseFloat(m.group()) * 17);

		m = perLevelPattern.matcher(baseAs.text());
		m.find();
		c.asBase = Float.parseFloat(m.group());
		
		m = perLevelPattern.matcher(asRatio.text());
		if(m.find())
			c.asRatio = Float.parseFloat(m.group());
		else
			c.asRatio = 1f;
		
		m = perLevelPattern.matcher(baseBonusAs.text());
		m.find();
		c.asExtraBase = (int)(Float.parseFloat(m.group()) * 17);

		c.ranged = ranged.text().equalsIgnoreCase("Ranged");
		return c;
	}
}
