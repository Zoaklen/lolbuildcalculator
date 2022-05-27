package main;

import items.GuinsooRageblade;
import items.HorizonFocus;
import items.Item;
import items.LiandryAnguish;

public class DamageTester
{
	public static class Damage
	{
		public int physMagicTrue;
		public int baseValue;
		public int applyEffects;
		public Item[] build;
		
		public Damage(int physMagicTrue, int baseValue, int applyEffects, Item[] build)
		{
			super();
			this.physMagicTrue = physMagicTrue;
			this.baseValue = baseValue;
			this.applyEffects = applyEffects;
			this.build = build;
		}
	}
	
	public static int applyDamageNoEffect(Champion target, Item[] build, int physMagicTrue, int damage)
	{
		return applyDamage(target, build, physMagicTrue, damage, 0, 0, 0, 0, 0, 0, 0, false);
	}
	
	public static int applyDamage(Champion target, Item[] build, int physMagicTrue, int baseDamage, float apScalar, float adScalar, float bonusAdScalar, float healthScalar, float armorScalar, float mrScalar, int applyEffects, boolean critable)
	{
		Champion c = Main.c;
		
		float damage = baseDamage;
		damage += apScalar * c.getTotalAp();
		damage += adScalar * c.getTotalAd();
		damage += bonusAdScalar * c.getBonusAd();
		damage += healthScalar * c.getTotalHealth();
		damage += armorScalar * c.getTotalArmor();
		damage += mrScalar * c.getTotalMr();
		
		Damage d = new Damage(physMagicTrue, (int)damage, applyEffects, build);
		
		for(Item i : build)
		{
			if(i != null && i.enableEffects && applyEffects > 0)
			{
				if(applyEffects == 1 || applyEffects == 3)
					i.onHit(c, target, d);
				if(applyEffects == 2 || applyEffects == 3)
					i.onSpell(c, target, d);
				
				i.onDamage(c, target, d);
			}
		}
		
		damage = d.baseValue;
		
		if(HorizonFocus.enablePassive && Main.itemInBuild(HorizonFocus.class, build))
			damage *= 1.1f;
		
		if(Main.itemInBuild(LiandryAnguish.class, build))
			damage *= Math.max(1f, Math.min(1f + target.getBonusHealth()/1250f*0.12f, 1.12f));
		
		int defense = 0;
		if(physMagicTrue == 0)
		{
			defense = (int)((target.getTotalArmor()*(1f - c.getTotalPhysPen()/100f))-c.getTotalLethality());
		}
		else if(physMagicTrue == 1)
		{
			defense = (int)((target.getTotalMr()*(1f - c.getTotalMagicPen()/100f))-c.getTotalFlatMagicPen());
		}
		defense = Math.max(0, defense);
		damage *= 100f/(100f+defense);
		
		if(critable)
		{
			boolean guinsoo = Main.itemInBuild(GuinsooRageblade.class, build);
			if(!guinsoo)
				damage += (int)(damage*Main.c.getTotalCrit()/100f*(Main.c.critMult-1f));
		}
		
		target.damageHealth -= damage;
		return (int)damage;
	}

	public static int basicAttack(Champion target, Item[] build)
	{
		int damage = Main.c.getTotalAd();
		
		return applyDamage(target, build, 0, damage, 0, 0, 0, 0, 0, 0, 1, true);
	}
}
