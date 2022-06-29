package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubDarkContrastIJTheme;

import items.AbyssalMask;
import items.AnathemaChains;
import items.ArdentCenser;
import items.AxiomArc;
import items.BansheeVeil;
import items.BlackCleaver;
import items.BladeOfTheRuinedKing;
import items.Bloodthirster;
import items.ChempunkChainsword;
import items.ChemtechPutrifier;
import items.CosmicDrive;
import items.CrownOfTheShatteredQueen;
import items.DeadManPlate;
import items.DeathDance;
import items.DemonicEmbrace;
import items.DivineSunderer;
import items.DuskbladeOfDraktharr;
import items.Eclipse;
import items.EdgeOfNight;
import items.EssenceReaver;
import items.Evenshroud;
import items.Everfrost;
import items.Fimbulwinter;
import items.ForceOfNature;
import items.FrostfireGauntlet;
import items.FrozenHeart;
import items.Galeforce;
import items.GargoyleStoneplate;
import items.Goredrinker;
import items.GuardianAngel;
import items.GuinsooRageblade;
import items.HextechRocketbolt;
import items.HorizonFocus;
import items.Hullbreaker;
import items.ImmortalShieldbow;
import items.ImperialMandate;
import items.InfinityEdge;
import items.Item;
import items.KnightVow;
import items.KrakenSlayer;
import items.LiandryAnguish;
import items.LichBane;
import items.LocketOfTheIronSolari;
import items.LordDominikRegards;
import items.LudenTempest;
import items.MawOfMalmortius;
import items.MejaiSoulstealer;
import items.MercurialScimitar;
import items.MikaelBlessing;
import items.MoonstoneRenewer;
import items.Morellonomicon;
import items.MortalReminder;
import items.Muramana;
import items.NashorTooth;
import items.NavoriQuickblades;
import items.NightHarvester;
import items.PhantomDancer;
import items.ProwlerClaw;
import items.RabadonDeathcap;
import items.RanduinOmen;
import items.RapidFirecannon;
import items.RavenousHydra;
import items.Redemption;
import items.Riftmaker;
import items.RunaanHurricane;
import items.RylaiCrystalScepter;
import items.SeraphEmbrace;
import items.SerpentFang;
import items.SeryldaGrudge;
import items.Shadowflame;
import items.ShurelyaBattlesong;
import items.SilvermereDawn;
import items.SpiritVisage;
import items.StaffOfFlowingWater;
import items.SterakGage;
import items.Stormrazor;
import items.Stridebreaker;
import items.SunfireAegis;
import items.TheCollector;
import items.Thornmail;
import items.TitanicHydra;
import items.TrinityForce;
import items.TurboChemtank;
import items.UmbralGlaive;
import items.VigilantWardstone;
import items.VoidStaff;
import items.WarmogArmor;
import items.WitsEnd;
import items.YoumuuGhostblade;
import items.ZekeConvergence;
import items.ZhonyaHourglass;
import view.MainScreen;
import view.RuntimeHeuristic;

public class Main
{
	public static ArrayList<Class<? extends Item>> itemClassList = new ArrayList<>();
	public static ArrayList<Item> itemArrayList = new ArrayList<>();
	public static Item[] legendaryItemList;
	public static Item[] mythicItemList;
	public static Item[] combinedList;
    public static int mythicItems = 0;
    public static int legendaryItems = 0;
    public static Champion c = new Champion();
    public static Item[] forcingItem = new Item[6];
    
    public static int QUANT = 6;
    
    public static float maiorAnterior = -Integer.MAX_VALUE;
    public static Item[] maiorLista = new Item[QUANT];

    public static long totalPossibilities = 0;
    public static long currentTests = 0;
    public static long previousRecord = 0;
    public static Item[] usingList;
    
    public static AtomicBoolean evaluatingItems = new AtomicBoolean(false);
    
    public static long startTime;
    
    public static void evaluateItems(Item[] listaAtual)
    {
    	int size = QUANT < 6 ? mythicItems+legendaryItems : mythicItems;
    	Item[] list = mythicItemList;
    	int firstIndex;
        for(int i = 0;i < size;i++)
        {
        	firstIndex = i;
        	if(QUANT < 6)
        	{
        		list = i < mythicItems ? mythicItemList : legendaryItemList;
        		firstIndex = i < mythicItems ? i : i-mythicItems;
        		//list = combinedList;
        	}
            if(list[firstIndex] != null)
            {
                // adiciona na lista atual, remove da lista de itens
            	if(list[firstIndex] == null)
            		continue;
            	if(forcingItem[0] == null)
            	{
            		listaAtual[0] = list[firstIndex];
            		list[firstIndex] = null;
            	}
            	else
            	{
            		listaAtual[0] = forcingItem[0];
            	}
                System.out.println("Testing mythic " + listaAtual[0].name);

                // testa a combina��o
                if(QUANT == 1)
				{
					if(!evaluatingItems.get())
						return;
					validateBuild(listaAtual);
				}
                else
                {
                	listaAtual[0].beforeProcedure();
                	for(int j = 0;j < legendaryItemList.length;j++)
                	{
                		if(legendaryItemList[j] == null)
                			continue;
                		
                		if(forcingItem[1] == null)
                    	{
                    		listaAtual[1] = legendaryItemList[j];
                    		legendaryItemList[j] = null;
                    	}
                    	else
                    	{
                    		listaAtual[1] = forcingItem[1];
                    	}
                		
                		if(QUANT == 2)
        				{
    						if(!evaluatingItems.get())
    							return;
        					validateBuild(listaAtual);
        				}
                		else
                		{
                        	listaAtual[1].beforeProcedure();
                			for(int k = 0;k < legendaryItemList.length;k++)
                			{
                        		if(forcingItem[2] == null)
                            	{
                    				if(legendaryItemList[k] == null)
                    					continue;
                            		listaAtual[2] = legendaryItemList[k];
                            		legendaryItemList[k] = null;
                            	}
                            	else
                            	{
                            		listaAtual[2] = forcingItem[2];
                            	}
                				if(QUANT == 3)
                				{
            						if(!evaluatingItems.get())
            							return;
                					validateBuild(listaAtual);
                				}
                                else
                                {
                                	listaAtual[2].beforeProcedure();
                                	for(int l = 0;l < legendaryItemList.length;l++)
                                	{
                                		if(forcingItem[3] == null)
                                    	{
                                    		if(legendaryItemList[l] == null)
                                    			continue;
                                    		listaAtual[3] = legendaryItemList[l];
                                    		legendaryItemList[l] = null;
                                    	}
                                    	else
                                    	{
                                    		listaAtual[3] = forcingItem[3];
                                    	}
                                		if(QUANT == 4)
                        				{
                    						if(!evaluatingItems.get())
                    							return;
                        					validateBuild(listaAtual);
                        				}
                                		else
                                		{
                                        	listaAtual[3].beforeProcedure();
                                			for(int m = 0;m < legendaryItemList.length;m++)
                                			{
                                        		if(forcingItem[4] == null)
                                        		{
                                        			if(legendaryItemList[m] == null)
                                        				continue;
                                            		listaAtual[4] = legendaryItemList[m];
                                            		legendaryItemList[m] = null;
                                            	}
                                            	else
                                            	{
                                            		listaAtual[4] = forcingItem[4];
                                            	}
                                				if(QUANT == 5)
                                				{
                            						if(!evaluatingItems.get())
                            							return;
                                					validateBuild(listaAtual);
                                				}
                                				else
                                				{
                                                	listaAtual[4].beforeProcedure();
                                					for(int n = 0;n < legendaryItemList.length;n++)
                                					{
                                						if(!evaluatingItems.get())
                                							return;
                                                		if(forcingItem[5] == null)
                                                    	{
                                    						if(legendaryItemList[n] == null)
                                    							continue;
                                    						
                                                    		listaAtual[5] = legendaryItemList[n];
                                                    		legendaryItemList[n] = null;
                                                    	}
                                                    	else
                                                    	{
                                                    		listaAtual[5] = forcingItem[5];
                                                    	}
                                						validateBuild(listaAtual);
                                                        if(forcingItem[5] == null)
                                                        {
                                                    		legendaryItemList[n] = listaAtual[5];
                                                    		listaAtual[5] = null;
                                                        }
                                					}
                                                	listaAtual[4].afterProcedure();
                                				}
                                                if(forcingItem[4] == null)
                                                {
                                            		legendaryItemList[m] = listaAtual[4];
                                            		listaAtual[4] = null;
                                                }
                                			}
                                        	listaAtual[3].afterProcedure();
                                		}
                                        if(forcingItem[3] == null)
                                        {
                                    		legendaryItemList[l] = listaAtual[3];
                                    		listaAtual[3] = null;
                                        }
                                	}
                                	listaAtual[2].afterProcedure();
                                }
                                if(forcingItem[2] == null)
                                {
                            		legendaryItemList[k] = listaAtual[2];
                            		listaAtual[2] = null;
                                }
                			}
                        	listaAtual[1].afterProcedure();
                		}

                        if(forcingItem[1] == null)
                        {
                    		legendaryItemList[j] = listaAtual[1];
                    		listaAtual[1] = null;
                        }
                	}
                	listaAtual[0].afterProcedure();
                }
                if(forcingItem[0] == null)
                {
                	list[firstIndex] = listaAtual[0];
                	listaAtual[0] = null;
                }
            }
        }
    }
    
    public static void validateBuild(Item[] listaAtual)
    {
    	float status = calculateStatus(listaAtual, false);
    	if(validBuildConditions(listaAtual))
    	{
    		if(status > maiorAnterior)
    		{
    			int l = listaAtual.length;
    			System.out.println("-----------------------\nNova melhor combina��o ("+ (int)status +"):");
    			StringBuilder text = new StringBuilder();
    			for(int o = 0;o < l;o++)
    			{
    				maiorLista[o] = listaAtual[o];
    				System.out.print(listaAtual[o].name + (o == l-1 ? "" : ", "));
					try {
						MainScreen.vec[o] = ImageIO.read(new File(listaAtual[o].itemImg()));
						MainScreen.veclabel[o].setIcon(new ImageIcon(MainScreen.vec[o]));
						MainScreen.veclabel[o].setToolTipText(listaAtual[o].name);
					} catch (IOException e) {
						e.printStackTrace();
					}
    			}
    			System.out.println();
    			MainScreen.bestHeuristicText.setText(String.valueOf((int)status));
    			MainScreen.bestCombinationText.setText(text.toString());
    			maiorAnterior = status;
    		}
    	}
    	currentTests++;
    	if(currentTests % 1000000000000L == 0 || currentTests >= previousRecord * 10)
    	{
    		previousRecord = currentTests;
    		long time = (System.currentTimeMillis()-startTime)/1000L;
    		String text = String.format("%,d/%,d (%02d:%02d:%02d)", currentTests, totalPossibilities, time/3600L, time%3600L/60L, time%60L);
    		System.out.println("Tests:" + text);
    		MainScreen.testCountLabel.setText("Current tests:" + text);
    	}
    }
    
    public static boolean validBuildConditions(Item[] build)
    {
    	return RuntimeHeuristic.heuristic.isValidBuild(build, c);
    }
    
    public static float getBuildHeuristic(Item[] build)
    {
    	/*Champion target = new Champion();
    	target.armor = 100;
    	target.mr = 100;
    	target.health = 2400;
    	target.resetStatus();
    	for(Item i : listaAtual)
    	{
    		if(i != null)
    		{
    			i.resetStatus();
    			i.startEffect(c, target);
    		}
    	}
    	
    	int damage = 0;
    	damage += DamageTester.applyDamage(target, listaAtual, 1, 120, 0.9f, 1f, 0, 0, 0, 0, 3, false);
    	
    	return damage;*/
    	return RuntimeHeuristic.heuristic.getHeuristicValue(build, c);
    }
    
	public static void main(String[] args) {
		Main.initializeItemList();
		Main.initializeInstances();
		Main.applyItemList();
		SwingUtilities.invokeLater(new Runnable(){
            public void run(){
            	FlatGitHubDarkContrastIJTheme.setup();
                new MainScreen("League of Legends Build Calculator").runTest();
            }
        });
		c.ad = 97;
		c.armor = 87;
		c.asBase = 0.638f;
		c.asRatio = 1f;
		c.asExtraBase = 39;
		c.health = 1997;
		c.mana = 1268;
		c.mr = 38;
		c.ranged = true;
		
		/*startTime = System.currentTimeMillis();
		
		initializeItemList();
		removeUnnecessaryItems();
		initializeInstances();
		removeIrrelevantItems();
		sortItems();
		applyItemList();
		printSortedList();
		
		calculatePossibilities();
		
		evaluateItems(new Item[QUANT]);
		System.out.println("-----------------------");
		for(Item i : maiorLista)
		{
			if(i != null)
				System.out.println(i.name);
		}
		float status = calculateStatus(maiorLista, true);
		System.out.println("Total: " + (int)status);*/
	}
	
	public static void printSortedList()
	{
		System.out.println("............");
		System.out.println("Mythic item test sequence:");
		for(int i = 0;i < mythicItemList.length;i++)
		{
			System.out.println(mythicItemList[i].name);
		}
		System.out.println("............");
		System.out.println("Legendary item test sequence:");
		for(int i = 0;i < legendaryItemList.length;i++)
		{
			System.out.println(legendaryItemList[i].name);
		}
		System.out.println("............");
	}
	
	public static void calculatePossibilities()
	{
		long b = QUANT < 6 ? mythicItems + legendaryItems : mythicItems;
		for(int i = 0;i < QUANT-1;i++)
		{
			b *= legendaryItems - i;
		}

		System.out.println("Mythic count: " + mythicItems);
		System.out.println("Legendary count: " + legendaryItems);
		totalPossibilities = b;
	}
	
	public static void removeUnnecessaryItems()
	{
		itemClassList.remove(ArdentCenser.class);
		itemClassList.remove(ChemtechPutrifier.class);
		itemClassList.remove(MikaelBlessing.class);
		itemClassList.remove(Redemption.class);
		itemClassList.remove(StaffOfFlowingWater.class);
		itemClassList.remove(ZekeConvergence.class);

		itemClassList.remove(MoonstoneRenewer.class);
		itemClassList.remove(LocketOfTheIronSolari.class);
		itemClassList.remove(ShurelyaBattlesong.class);
		itemClassList.remove(Evenshroud.class);
		itemClassList.remove(ImperialMandate.class);
	}
	
	public static void removeIrrelevantItems()
	{
		Item[] build = new Item[6];
		float statusA, statusB;
		build[0] = new Everfrost();
		build[1] = new SunfireAegis();
		build[2] = new ImmortalShieldbow();
		build[3] = new ProwlerClaw();
		build[4] = new DuskbladeOfDraktharr();
		
		for(Iterator<Item> it = itemArrayList.iterator();it.hasNext();)
		{
			Item i = it.next();
			build[5] = null;
			statusA = calculateStatus(build, false);
			build[5] = i;
			statusB = calculateStatus(build, false);
			if(statusA >= statusB)
			{
				System.out.println("Removed irrelevant item: " + i.name);
				if(i.mythic)
					mythicItems--;
				else
					legendaryItems--;
				it.remove();
			}
		}
	}
	
	public static void initializeInstances()
	{
		itemArrayList.clear();
		mythicItems = 0;
		legendaryItems = 0;
		for(Class<? extends Item> c : itemClassList)
		{
			try
			{
				@SuppressWarnings("deprecation")
				Item instance = c.newInstance();
				if(instance.mythic)
					mythicItems++;
				else
					legendaryItems++;
				
				itemArrayList.add(instance);
			} catch (InstantiationException e)
			{
				e.printStackTrace();
				System.exit(0);
			} catch (IllegalAccessException e)
			{
				e.printStackTrace();
				System.exit(0);
			}
		}
	}
	
	public static void sortItems()
	{
		Item[] build = new Item[6];
		build[0] = new Everfrost();
		build[1] = new SunfireAegis();
		build[2] = new ImmortalShieldbow();
		build[3] = new ProwlerClaw();
		build[4] = new DuskbladeOfDraktharr();
		
		Collections.sort(itemArrayList, new Comparator<Item>() {
			@Override
			public int compare(Item o1, Item o2)
			{
				build[5] = o1;
				float statusA = calculateStatus(build, false);
				build[5] = o2;
				float statusB = calculateStatus(build, false);
				return statusB > statusA ? 1 : (statusB == statusA ? 0 : -1);
			}
		});
	}
	
	public static void applyItemList()
	{
		mythicItemList = new Item[mythicItems];
		legendaryItemList = new Item[legendaryItems];
		combinedList = new Item[legendaryItems+mythicItems];
		
		int a = 0, b = 0;
		for(Item i : itemArrayList)
		{
			if(i.mythic)
			{
				combinedList[a] = i;
				mythicItemList[a++] = i;
			}
			else
			{
				combinedList[b+mythicItems] = i;
				legendaryItemList[b++] = i;
			}
		} 
	}
	
	public static void initializeItemList()
	{
		itemClassList.clear();
		itemClassList.add(AbyssalMask.class);
		itemClassList.add(AnathemaChains.class);
		itemClassList.add(ArdentCenser.class);
		itemClassList.add(AxiomArc.class);
		itemClassList.add(BansheeVeil.class);
		itemClassList.add(BlackCleaver.class);
		itemClassList.add(BladeOfTheRuinedKing.class);
		itemClassList.add(Bloodthirster.class);
		itemClassList.add(ChempunkChainsword.class);
		itemClassList.add(ChemtechPutrifier.class);
		itemClassList.add(CosmicDrive.class);
		itemClassList.add(CrownOfTheShatteredQueen.class);
		itemClassList.add(DeadManPlate.class);
		itemClassList.add(DeathDance.class);
		itemClassList.add(DemonicEmbrace.class);
		itemClassList.add(DivineSunderer.class);
		itemClassList.add(DuskbladeOfDraktharr.class);
		itemClassList.add(Eclipse.class);
		itemClassList.add(EdgeOfNight.class);
		itemClassList.add(EssenceReaver.class);
		itemClassList.add(Evenshroud.class);
		itemClassList.add(Everfrost.class);
		itemClassList.add(Fimbulwinter.class);
		itemClassList.add(ForceOfNature.class);
		itemClassList.add(FrostfireGauntlet.class);
		itemClassList.add(FrozenHeart.class);
		itemClassList.add(Galeforce.class);
		itemClassList.add(GargoyleStoneplate.class);
		itemClassList.add(Goredrinker.class);
		itemClassList.add(GuardianAngel.class);
		itemClassList.add(GuinsooRageblade.class);
		itemClassList.add(HextechRocketbolt.class);
		itemClassList.add(HorizonFocus.class);
		itemClassList.add(Hullbreaker.class);
		itemClassList.add(ImmortalShieldbow.class);
		itemClassList.add(ImperialMandate.class);
		itemClassList.add(InfinityEdge.class);
		itemClassList.add(KnightVow.class);
		itemClassList.add(KrakenSlayer.class);
		itemClassList.add(LiandryAnguish.class);
		itemClassList.add(LichBane.class);
		itemClassList.add(LocketOfTheIronSolari.class);
		itemClassList.add(LordDominikRegards.class);
		itemClassList.add(LudenTempest.class);
		itemClassList.add(MawOfMalmortius.class);
		itemClassList.add(MejaiSoulstealer.class);
		itemClassList.add(MercurialScimitar.class);
		itemClassList.add(MikaelBlessing.class);
		itemClassList.add(MoonstoneRenewer.class);
		itemClassList.add(Morellonomicon.class);
		itemClassList.add(MortalReminder.class);
		itemClassList.add(Muramana.class);
		itemClassList.add(NashorTooth.class);
		itemClassList.add(NavoriQuickblades.class);
		itemClassList.add(NightHarvester.class);
		itemClassList.add(PhantomDancer.class);
		itemClassList.add(ProwlerClaw.class);
		itemClassList.add(RabadonDeathcap.class);
		itemClassList.add(RanduinOmen.class);
		itemClassList.add(RapidFirecannon.class);
		itemClassList.add(RavenousHydra.class);
		itemClassList.add(Redemption.class);
		itemClassList.add(Riftmaker.class);
		itemClassList.add(RunaanHurricane.class);
		itemClassList.add(RylaiCrystalScepter.class);
		itemClassList.add(SeraphEmbrace.class);
		itemClassList.add(SerpentFang.class);
		itemClassList.add(SeryldaGrudge.class);
		itemClassList.add(Shadowflame.class);
		itemClassList.add(ShurelyaBattlesong.class);
		itemClassList.add(SilvermereDawn.class);
		itemClassList.add(SpiritVisage.class);
		itemClassList.add(StaffOfFlowingWater.class);
		itemClassList.add(SterakGage.class);
		itemClassList.add(Stormrazor.class);
		itemClassList.add(Stridebreaker.class);
		itemClassList.add(SunfireAegis.class);
		itemClassList.add(TheCollector.class);
		itemClassList.add(Thornmail.class);
		itemClassList.add(TitanicHydra.class);
		itemClassList.add(TrinityForce.class);
		itemClassList.add(TurboChemtank.class);
		itemClassList.add(UmbralGlaive.class);
		itemClassList.add(VigilantWardstone.class);
		itemClassList.add(VoidStaff.class);
		itemClassList.add(WarmogArmor.class);
		itemClassList.add(WitsEnd.class);
		itemClassList.add(YoumuuGhostblade.class);
		itemClassList.add(ZekeConvergence.class);
		itemClassList.add(ZhonyaHourglass.class);
	}
	
    public static float calculateStatus(Item[] listaAtual, boolean debug)
    {
    	c.resetStatus();
    	int l = listaAtual.length;
    	for(int i = 0;i < l;i++)
    	{
    		if(listaAtual[i] == null)
    			continue;
    		
    		c.addAd(listaAtual[i].itemBaseAD());
    		c.addAp(listaAtual[i].itemBaseAP());
    		c.addArmor(listaAtual[i].itemBaseARMOR());
    		c.addAs(listaAtual[i].itemBaseAS());
    		c.addCdr(listaAtual[i].itemBaseCDR());
    		c.addCrit(listaAtual[i].itemBaseCRIT());
    		c.addFlatMagicPen(listaAtual[i].itemBaseFLATMAGICPEN());
    		c.addHealth(listaAtual[i].itemBaseHEALTH());
    		c.addLethality(listaAtual[i].itemBaseLETHALITY());
    		c.addLifesteal(listaAtual[i].itemBaseLIFESTEAL());
    		c.addMagicPen(listaAtual[i].itemBaseMAGICPEN());
    		c.addMana(listaAtual[i].itemBaseMANA());
    		c.addMr(listaAtual[i].itemBaseMR());
    		c.addOmnivamp(listaAtual[i].itemBaseOMNIVAMP());
    		c.addPhysPen(listaAtual[i].itemBasePHYSPEN());
    	}
    	
    	for(int i = 0;i < l;i++)
    	{
    		if(listaAtual[i] == null)
    			continue;
    		listaAtual[i].itemExtraStatus(c);
    	}
    	
    	return getBuildHeuristic(listaAtual);
    }
    
    public static boolean itemInBuild(Class<? extends Item> item, Item[] build)
    {
    	for(Item i : build)
    		if(i != null && i.getClass() == item)
    			return true;
    	
    	return false;
    }

	public static String checkForInvalidValues() {
		if(MejaiSoulstealer.mejaiStacks < 0 || MejaiSoulstealer.mejaiStacks > 25)
		{
			return "O número de stacks do Mejai está fora do alcance do item, favor inserir um valor no intervalo de 0 <= stacks <= 25 na aba de configurações.";
		}
		return null;
	}
}
