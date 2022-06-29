package view;

import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Vector;
import javax.swing.AbstractAction;

import javax.imageio.ImageIO;
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpringLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import javax.swing.event.MouseInputListener;

import org.jsoup.HttpStatusException;
import org.w3c.dom.events.MouseEvent;

import bsh.EvalError;
import bsh.Interpreter;
import interfaces.Heuristic;
import items.CrownOfTheShatteredQueen;
import items.HorizonFocus;
import items.Hullbreaker;
import items.Item;
import items.MejaiSoulstealer;
import items.Riftmaker;
import main.Champion;
import main.Main;

/*
 * Creating and using TabComponentsDemo example  
 */ 
public class MainScreen extends JFrame {    

	private static final long serialVersionUID = -8096486415526604772L;
    private final JTabbedPane tabsPane = new JTabbedPane();
    public static Thread lastEvaluationThread;
    
    public static JButton runButton;
    public static JLabel testCountLabel;
    public static JTextField bestHeuristicText;
    public static JTextArea bestCombinationText;
    public static BufferedImage[] vec = new BufferedImage[6];
    public static JLabel[] veclabel = new JLabel[6];
	public static BufferedImage[] vecCombinedList = new BufferedImage[90];
    public static JLabel[] vecLabelCombinedList = new JLabel[90];
    
    
    private static final String DEFAULT_HEURISTIC_CODE = 
    		"import items.*;\r\n" + 
    		"import main.*;\r\n" +
    		"import interfaces.*;\r\n" + 
    		"\r\n" + 
    		"Champion target = new Champion(2000, 80, 50);" +
    		"\r\n" + 
    		"public float getHeuristicValue(Item[] build, Champion c)\r\n" + 
    		"{\r\n" + 
    		"	/*\r\n"
    		+ "	target.resetStatus();\r\n" + 
    		"	target.addHealth(2500);\r\n" + 
    		"	target.addArmor(50);\r\n" + 
    		"	target.addMr(10000);\r\n" + 
    		"	for(Item i : build)\r\n" + 
    		"	{\r\n" + 
    		"		if(i != null)\r\n" + 
    		"		{\r\n" + 
    		"			i.resetStatus();\r\n" + 
    		"			i.startEffect(c, target);\r\n" + 
    		"		}\r\n" + 
    		"	}" + 
    		"	int damage = 0;\r\n" + 
    		"	damage += DamageTester.applyDamage(target, build, physMagicTrue, baseDamage, apScalar, adScalar, bonusAdScalar, healthScalar, armorScalar, mrScalar, applyEffects, critable);\r\n" + 
    		"	*/\r\n" +
    		"\r\n"+
    		"	return 0;\r\n" + 
    		"}" + 
    		"\r\n" + 
    		"public boolean isValidBuild(Item[] build, Champion c)\r\n" + 
    		"{\r\n" + 
    		"	return true;\r\n" + 
    		"}";
    
    public static boolean isRunning;
    
    public MainScreen(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initMenu();        
        add(tabsPane);        
    }
    
    public void runTest() {
    	Main.initializeInstances();
    	Main.applyItemList();
    	Main.initializeItemList();
        tabsPane.removeAll();
        tabsPane.add("Heuristic", getHeuristicPane());
        initTabComponent(0);
        tabsPane.add("Settings", getSettingsPane());
        initTabComponent(1);
        tabsPane.add("Force Items", getForceItemPane());
        initTabComponent(2);
        tabsPane.add("Evaluation", getEvaluationPane());
        initTabComponent(3);

        
        tabsPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        setSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private Component getHeuristicPane()
	{
		Container pane = new Container();
		SpringLayout layout = new SpringLayout();
		pane.setLayout(layout);

		JLabel heuristicLabel = new JLabel("Heuristic function");
		
		JTextArea heuristicArea = new JTextArea(MainScreen.DEFAULT_HEURISTIC_CODE);
		heuristicArea.setTabSize(2);

		final UndoManager undoManager = new UndoManager();
		JScrollPane heuristicField = new JScrollPane(heuristicArea);
		heuristicField.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    
		heuristicArea.getDocument().addUndoableEditListener(e -> {
			undoManager.addEdit(e.getEdit());
		});
		heuristicArea.getActionMap().put("Undo", new AbstractAction("Undo") {
			private static final long serialVersionUID = -5851127238270968020L;

			public void actionPerformed(ActionEvent evt) {
				try {
					if (undoManager.canUndo()) {
						undoManager.undo();
					}
				} catch (CannotUndoException e) {
				}
			}
		});
		heuristicArea.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "Undo");
    
		runButton = new JButton("Run");
		final JFrame screenFrame = this;
		runButton.addActionListener(e -> {
			Interpreter interpreter = new Interpreter();
			try
			{
				interpreter.eval(heuristicArea.getText());
				RuntimeHeuristic.heuristic = (Heuristic)interpreter.eval("return (Heuristic)this;");
				Main.evaluatingItems.set(true);
				runButton.setEnabled(false);
				System.out.println("Threads:" + Thread.activeCount());
				lastEvaluationThread = new Thread() {
					@Override
					public void run()
					{
						try
						{
							String err;
							if((err = Main.checkForInvalidValues()) != null)
							{
								JOptionPane.showMessageDialog(screenFrame, err, "Um erro ocorreu", JOptionPane.ERROR_MESSAGE);
								return;
							}
							
							Main.maiorAnterior = -Integer.MAX_VALUE;
							Main.startTime = System.currentTimeMillis();
							Main.currentTests = 0L;
							Main.previousRecord = 0L;
							Main.initializeItemList();
							Main.removeUnnecessaryItems();
							Main.initializeInstances();
							Main.removeIrrelevantItems();
							Main.sortItems();
							Main.applyItemList();
							Main.printSortedList();

							Main.calculatePossibilities();

							tabsPane.setSelectedIndex(3);
							Main.evaluateItems(new Item[Main.QUANT]);
						}
						catch(UndeclaredThrowableException e1)
						{
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, e1.getCause().getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
							tabsPane.setSelectedIndex(0);
						}
						Main.evaluatingItems.set(false);
						runButton.setEnabled(true);
						

			    		long time = (System.currentTimeMillis()-Main.startTime)/1000L;
			    		String text = String.format("(%02d:%02d:%02d)", time/3600L, time%3600L/60L, time%60L);
						MainScreen.testCountLabel.setText("Current tests: FINISHED " + text); 
					}
				};
				lastEvaluationThread.setPriority(10);
				lastEvaluationThread.start();
			} catch (EvalError e1)
			{
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				Main.evaluatingItems.set(false);
				runButton.setEnabled(true);
			}
		});

		layout.putConstraint(SpringLayout.WEST, heuristicLabel, 0, SpringLayout.WEST, heuristicField);
		layout.putConstraint(SpringLayout.SOUTH, heuristicLabel, -8, SpringLayout.NORTH, heuristicField);
		
		layout.putConstraint(SpringLayout.WEST, heuristicField, 16, SpringLayout.WEST, pane);
		layout.putConstraint(SpringLayout.NORTH, heuristicField, 32, SpringLayout.NORTH, pane);
		layout.putConstraint(SpringLayout.SOUTH, heuristicField, -64, SpringLayout.SOUTH, pane);
		layout.putConstraint(SpringLayout.EAST, heuristicField, -8, SpringLayout.EAST, pane);
		
		layout.putConstraint(SpringLayout.WEST, runButton, -32, SpringLayout.HORIZONTAL_CENTER, pane);
		layout.putConstraint(SpringLayout.EAST, runButton, 32, SpringLayout.HORIZONTAL_CENTER, pane);
		layout.putConstraint(SpringLayout.NORTH, runButton, -32, SpringLayout.SOUTH, runButton);
		layout.putConstraint(SpringLayout.SOUTH, runButton, -16, SpringLayout.SOUTH, pane);

		pane.add(heuristicField);
		pane.add(heuristicLabel);
		pane.add(runButton);
		
		return pane;
	}
    
    private Component getSettingsPane()
	{
		Container pane = new Container();
		SpringLayout layout = new SpringLayout();
		pane.setLayout(layout);

		JLabel itemQuant = new JLabel("Item count");
		JTextField itemQuantText = new JTextField(String.valueOf(Main.QUANT));
		itemQuantText.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0)
			{
				this.updateMejaiStacks();
			}

			@Override
			public void insertUpdate(DocumentEvent arg0)
			{
				this.updateMejaiStacks();
			}

			@Override
			public void removeUpdate(DocumentEvent arg0)
			{
				this.updateMejaiStacks();
			}
			
			private void updateMejaiStacks()
			{
				try
				{
					Main.QUANT = Integer.parseInt(itemQuantText.getText());
				}
				catch (Exception ex)
				{
					Main.QUANT = 6;
				}
			}
		});
		
		JCheckBox riftmaker = new JCheckBox("Riftmaker stacked");
		riftmaker.setSelected(Riftmaker.fullyStacked);
		riftmaker.addActionListener(e -> {
			Riftmaker.fullyStacked = riftmaker.isSelected();
		});
		
		JLabel mejaiLabel = new JLabel("Mejai stacks");
		JTextField mejaiStacks = new JTextField(String.valueOf(MejaiSoulstealer.mejaiStacks));
		mejaiStacks.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0)
			{
				this.updateMejaiStacks();
			}

			@Override
			public void insertUpdate(DocumentEvent arg0)
			{
				this.updateMejaiStacks();
			}

			@Override
			public void removeUpdate(DocumentEvent arg0)
			{
				this.updateMejaiStacks();
			}
			
			private void updateMejaiStacks()
			{
				try
				{
					MejaiSoulstealer.mejaiStacks = Integer.parseInt(mejaiStacks.getText());
				}
				catch (Exception ex)
				{
					MejaiSoulstealer.mejaiStacks = 25;
				}
			}
		});
		
		JCheckBox crownShield = new JCheckBox("Crown Queen shield");
		crownShield.setSelected(CrownOfTheShatteredQueen.enableShield);
		crownShield.addActionListener(e -> {
			CrownOfTheShatteredQueen.enableShield = crownShield.isSelected();
		});
		
		JCheckBox horizonFocusPassive = new JCheckBox("Horizon Focus passive");
		horizonFocusPassive.setSelected(HorizonFocus.enablePassive);
		horizonFocusPassive.addActionListener(e -> {
			HorizonFocus.enablePassive = horizonFocusPassive.isSelected();
		});
		
		JCheckBox hullbreaker = new JCheckBox("Hullbreaker w/ team");
		hullbreaker.setSelected(Hullbreaker.teamTogether);
		hullbreaker.addActionListener(e -> {
			Hullbreaker.teamTogether = hullbreaker.isSelected();
		});
		
		/*
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
	public float critMult;*/
		JLabel championNameLabel = new JLabel("Name: ");
		JTextField championName = new JTextField("Kayle");
		JButton pullButton = new JButton("Pull Data");

		JLabel asBaseLabel = new JLabel("Base AS: ");
		JTextField asBase = new JTextField("");
		
		JLabel asRatioLabel = new JLabel("AS Ratio: ");
		JTextField asRatio = new JTextField("");

		JLabel asExtraBaseLabel = new JLabel("Base Bonus AS: ");
		JTextField asExtraBase = new JTextField("");

		JLabel healthLabel = new JLabel("Health: ");
		JTextField health = new JTextField("");

		JLabel manaLabel = new JLabel("Mana: ");
		JTextField mana = new JTextField("");

		JLabel armorLabel = new JLabel("Armor: ");
		JTextField armor = new JTextField("");

		JLabel mrLabel = new JLabel("Magic Resistance: ");
		JTextField mr = new JTextField("");

		JLabel rangedLabel = new JLabel("Ranged: ");
		JCheckBox ranged = new JCheckBox();
		
		JButton applyButton = new JButton("Apply");
		applyButton.addActionListener(e -> {
			try
			{
				Main.c.asBase = Float.parseFloat(asBase.getText());
			} catch(Exception ex)
			{
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Could not parse AS Base.\n\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			try
			{
				Main.c.asRatio = Float.parseFloat(asRatio.getText());
			} catch(Exception ex)
			{
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Could not parse AS Ratio.\n\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			try
			{
				Main.c.asExtraBase = Integer.parseInt(asExtraBase.getText());
			} catch(Exception ex)
			{
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Could not parse Base Bonus AS.\n\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			try
			{
				Main.c.health = Integer.parseInt(health.getText());
			} catch(Exception ex)
			{
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Could not parse Health.\n\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			try
			{
				Main.c.mana = Integer.parseInt(mana.getText());
			} catch(Exception ex)
			{
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Could not parse Mana.\n\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			try
			{
				Main.c.armor = Integer.parseInt(armor.getText());
			} catch(Exception ex)
			{
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Could not parse Armor.\n\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			try
			{
				Main.c.mr = Integer.parseInt(mr.getText());
			} catch(Exception ex)
			{
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Could not parse Magic Resistance.\n\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Main.c.ranged = ranged.isSelected();
			JOptionPane.showMessageDialog(this, "Champion data applied!", "Success", JOptionPane.INFORMATION_MESSAGE);
		});

		ActionListener listener = e -> {
			championName.setEnabled(false);
			asBase.setEnabled(false);
			asRatio.setEnabled(false);
			asExtraBase.setEnabled(false);
			health.setEnabled(false);
			mana.setEnabled(false);
			armor.setEnabled(false);
			mr.setEnabled(false);
			ranged.setEnabled(false);
			applyButton.setEnabled(false);
			new Thread(() -> {
				try
				{
					Main.c = new Champion();
					Champion c = Champion.tryGetChampionData(championName.getText());
					Main.c = c;
					JOptionPane.showMessageDialog(this, "Champion data applied!", "Success", JOptionPane.INFORMATION_MESSAGE);
				}
				catch(HttpStatusException ex)
				{
					ex.printStackTrace();
					JOptionPane.showMessageDialog(this, "Could not retrieve LoL Wiki data for champion named [" + championName.getText() + "].\n\n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
					JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				finally
				{
					championName.setEnabled(true);
					asBase.setText(String.valueOf(Main.c.asBase));
					asBase.setEnabled(true);
					asRatio.setText(String.valueOf(Main.c.asRatio));
					asRatio.setEnabled(true);
					asExtraBase.setText(String.valueOf(Main.c.asExtraBase));
					asExtraBase.setEnabled(true);
					health.setText(String.valueOf(Main.c.health));
					health.setEnabled(true);
					mana.setText(String.valueOf(Main.c.mana));
					mana.setEnabled(true);
					armor.setText(String.valueOf(Main.c.armor));
					armor.setEnabled(true);
					mr.setText(String.valueOf(Main.c.mr));
					mr.setEnabled(true);
					ranged.setSelected(Main.c.ranged);
					ranged.setEnabled(true);
					applyButton.setEnabled(true);
				}
			}).start();
		};
		championName.addActionListener(listener);
		pullButton.addActionListener(listener);
		layout.putConstraint(SpringLayout.WEST, itemQuant, 16, SpringLayout.WEST, pane);
		layout.putConstraint(SpringLayout.NORTH, itemQuant, 16, SpringLayout.NORTH, pane);

		layout.putConstraint(SpringLayout.WEST, itemQuantText, 8, SpringLayout.EAST, itemQuant);
		layout.putConstraint(SpringLayout.NORTH, itemQuantText, -8, SpringLayout.NORTH, itemQuant);
		layout.putConstraint(SpringLayout.SOUTH, itemQuantText, 8, SpringLayout.SOUTH, itemQuant);
		layout.putConstraint(SpringLayout.EAST, itemQuantText, 32, SpringLayout.WEST, itemQuantText);

		layout.putConstraint(SpringLayout.WEST, riftmaker, 16, SpringLayout.WEST, pane);
		layout.putConstraint(SpringLayout.NORTH, riftmaker, 24, SpringLayout.NORTH, itemQuant);

		layout.putConstraint(SpringLayout.WEST, mejaiLabel, 16, SpringLayout.WEST, pane);
		layout.putConstraint(SpringLayout.NORTH, mejaiLabel, 16, SpringLayout.SOUTH, riftmaker);

		layout.putConstraint(SpringLayout.WEST, mejaiStacks, 8, SpringLayout.EAST, mejaiLabel);
		layout.putConstraint(SpringLayout.NORTH, mejaiStacks, -8, SpringLayout.NORTH, mejaiLabel);
		layout.putConstraint(SpringLayout.SOUTH, mejaiStacks, 8, SpringLayout.SOUTH, mejaiLabel);
		layout.putConstraint(SpringLayout.EAST, mejaiStacks, 32, SpringLayout.WEST, mejaiStacks);

		layout.putConstraint(SpringLayout.WEST, crownShield, 16, SpringLayout.WEST, pane);
		layout.putConstraint(SpringLayout.NORTH, crownShield, 16, SpringLayout.SOUTH, mejaiLabel);

		layout.putConstraint(SpringLayout.WEST, horizonFocusPassive, 16, SpringLayout.WEST, pane);
		layout.putConstraint(SpringLayout.NORTH, horizonFocusPassive, 16, SpringLayout.SOUTH, crownShield);

		layout.putConstraint(SpringLayout.WEST, hullbreaker, 16, SpringLayout.WEST, pane);
		layout.putConstraint(SpringLayout.NORTH, hullbreaker, 16, SpringLayout.SOUTH, horizonFocusPassive);
		
		layout.putConstraint(SpringLayout.WEST, championName, 256, SpringLayout.EAST, itemQuant);
		layout.putConstraint(SpringLayout.NORTH, championName, 0, SpringLayout.NORTH, itemQuant);
		layout.putConstraint(SpringLayout.EAST, championName, 128, SpringLayout.WEST, championName);
		
		layout.putConstraint(SpringLayout.WEST, pullButton, 8, SpringLayout.EAST, championName);
		layout.putConstraint(SpringLayout.NORTH, pullButton, 0, SpringLayout.NORTH, championName);
		layout.putConstraint(SpringLayout.SOUTH, pullButton, 0, SpringLayout.SOUTH, championName);

		layout.putConstraint(SpringLayout.EAST, championNameLabel, -8, SpringLayout.WEST, championName);
		layout.putConstraint(SpringLayout.SOUTH, championNameLabel, -4, SpringLayout.SOUTH, championName);
		
		layout.putConstraint(SpringLayout.WEST, asBase, 0, SpringLayout.WEST, championName);
		layout.putConstraint(SpringLayout.NORTH, asBase, 4, SpringLayout.SOUTH, championName);
		layout.putConstraint(SpringLayout.EAST, asBase, 64, SpringLayout.WEST, asBase);

		layout.putConstraint(SpringLayout.EAST, asBaseLabel, -8, SpringLayout.WEST, asBase);
		layout.putConstraint(SpringLayout.SOUTH, asBaseLabel, -4, SpringLayout.SOUTH, asBase);
		
		layout.putConstraint(SpringLayout.WEST, asRatio, 0, SpringLayout.WEST, asBase);
		layout.putConstraint(SpringLayout.NORTH, asRatio, 4, SpringLayout.SOUTH, asBase);
		layout.putConstraint(SpringLayout.EAST, asRatio, 64, SpringLayout.WEST, asRatio);

		layout.putConstraint(SpringLayout.EAST, asRatioLabel, -8, SpringLayout.WEST, asRatio);
		layout.putConstraint(SpringLayout.SOUTH, asRatioLabel, -4, SpringLayout.SOUTH, asRatio);
		
		layout.putConstraint(SpringLayout.WEST, asExtraBase, 0, SpringLayout.WEST, asRatio);
		layout.putConstraint(SpringLayout.NORTH, asExtraBase, 4, SpringLayout.SOUTH, asRatio);
		layout.putConstraint(SpringLayout.EAST, asExtraBase, 64, SpringLayout.WEST, asExtraBase);

		layout.putConstraint(SpringLayout.EAST, asExtraBaseLabel, -8, SpringLayout.WEST, asExtraBase);
		layout.putConstraint(SpringLayout.SOUTH, asExtraBaseLabel, -4, SpringLayout.SOUTH, asExtraBase);
		
		layout.putConstraint(SpringLayout.WEST, health, 0, SpringLayout.WEST, asExtraBase);
		layout.putConstraint(SpringLayout.NORTH, health, 4, SpringLayout.SOUTH, asExtraBase);
		layout.putConstraint(SpringLayout.EAST, health, 64, SpringLayout.WEST, health);

		layout.putConstraint(SpringLayout.EAST, healthLabel, -8, SpringLayout.WEST, health);
		layout.putConstraint(SpringLayout.SOUTH, healthLabel, -4, SpringLayout.SOUTH, health);
		
		layout.putConstraint(SpringLayout.WEST, mana, 0, SpringLayout.WEST, health);
		layout.putConstraint(SpringLayout.NORTH, mana, 4, SpringLayout.SOUTH, health);
		layout.putConstraint(SpringLayout.EAST, mana, 64, SpringLayout.WEST, mana);

		layout.putConstraint(SpringLayout.EAST, manaLabel, -8, SpringLayout.WEST, mana);
		layout.putConstraint(SpringLayout.SOUTH, manaLabel, -4, SpringLayout.SOUTH, mana);
		
		layout.putConstraint(SpringLayout.WEST, armor, 0, SpringLayout.WEST, mana);
		layout.putConstraint(SpringLayout.NORTH, armor, 4, SpringLayout.SOUTH, mana);
		layout.putConstraint(SpringLayout.EAST, armor, 64, SpringLayout.WEST, armor);

		layout.putConstraint(SpringLayout.EAST, armorLabel, -8, SpringLayout.WEST, armor);
		layout.putConstraint(SpringLayout.SOUTH, armorLabel, -4, SpringLayout.SOUTH, armor);
		
		layout.putConstraint(SpringLayout.WEST, mr, 0, SpringLayout.WEST, armor);
		layout.putConstraint(SpringLayout.NORTH, mr, 4, SpringLayout.SOUTH, armor);
		layout.putConstraint(SpringLayout.EAST, mr, 64, SpringLayout.WEST, mr);

		layout.putConstraint(SpringLayout.EAST, mrLabel, -8, SpringLayout.WEST, mr);
		layout.putConstraint(SpringLayout.SOUTH, mrLabel, -4, SpringLayout.SOUTH, mr);
		
		layout.putConstraint(SpringLayout.WEST, ranged, 0, SpringLayout.WEST, mr);
		layout.putConstraint(SpringLayout.NORTH, ranged, 4, SpringLayout.SOUTH, mr);

		layout.putConstraint(SpringLayout.EAST, rangedLabel, -8, SpringLayout.WEST, ranged);
		layout.putConstraint(SpringLayout.SOUTH, rangedLabel, -4, SpringLayout.SOUTH, ranged);

		layout.putConstraint(SpringLayout.WEST, applyButton, 0, SpringLayout.WEST, championName);
		layout.putConstraint(SpringLayout.NORTH, applyButton, 4, SpringLayout.SOUTH, ranged);

		pane.add(itemQuant);
		pane.add(itemQuantText);
		pane.add(riftmaker);
		pane.add(mejaiLabel);
		pane.add(mejaiStacks);
		pane.add(crownShield);
		pane.add(horizonFocusPassive);
		pane.add(hullbreaker);
		pane.add(championName);
		pane.add(championNameLabel);
		pane.add(pullButton);
		pane.add(asBase);
		pane.add(asBaseLabel);
		pane.add(asRatio);
		pane.add(asRatioLabel);
		pane.add(asExtraBase);
		pane.add(asExtraBaseLabel);
		pane.add(health);
		pane.add(healthLabel);
		pane.add(mana);
		pane.add(manaLabel);
		pane.add(armor);
		pane.add(armorLabel);
		pane.add(mr);
		pane.add(mrLabel);
		pane.add(ranged);
		pane.add(rangedLabel);
		pane.add(applyButton);
		
		return pane;
	}
    
    private Component getEvaluationPane()
	{
		Container pane = new Container();
		SpringLayout layout = new SpringLayout();
		pane.setLayout(layout);

		JLabel bestHeuristicLabel = new JLabel("Best heuristic:");
		testCountLabel = new JLabel("Current tests: 0/0");
		JLabel bestCombinationLabel = new JLabel("Best combination:");
		
		bestHeuristicText = new JTextField("0");
		bestHeuristicText.setEnabled(false);
		
		bestCombinationText = new JTextArea();
		bestCombinationText.setEnabled(false);
		
		for(int i = 0; i < 6; i++) {
				veclabel[i] = new JLabel();
		}
		
		JButton button = new JButton("Stop");
		button.addActionListener(e -> {
			if(lastEvaluationThread != null)
			{
				lastEvaluationThread.interrupt();
				runButton.setEnabled(true);
				Main.evaluatingItems.set(false);
			}
		});

		layout.putConstraint(SpringLayout.WEST, testCountLabel, 16, SpringLayout.WEST, pane);
		layout.putConstraint(SpringLayout.NORTH, testCountLabel, 16, SpringLayout.NORTH, pane);

		layout.putConstraint(SpringLayout.WEST, bestHeuristicLabel, 16, SpringLayout.WEST, pane);
		layout.putConstraint(SpringLayout.NORTH, bestHeuristicLabel, 16, SpringLayout.SOUTH, testCountLabel);

		layout.putConstraint(SpringLayout.WEST, bestHeuristicText, 16, SpringLayout.EAST, bestHeuristicLabel);
		layout.putConstraint(SpringLayout.EAST, bestHeuristicText, 128, SpringLayout.WEST, bestHeuristicText);
		layout.putConstraint(SpringLayout.NORTH, bestHeuristicText, -8, SpringLayout.NORTH, bestHeuristicLabel);
		layout.putConstraint(SpringLayout.SOUTH, bestHeuristicText, 8, SpringLayout.SOUTH, bestHeuristicLabel);

		layout.putConstraint(SpringLayout.WEST, bestCombinationLabel, 16, SpringLayout.WEST, pane);
		layout.putConstraint(SpringLayout.NORTH, bestCombinationLabel, 16, SpringLayout.SOUTH, bestHeuristicLabel);

		layout.putConstraint(SpringLayout.WEST, veclabel[0], 16, SpringLayout.WEST, pane);
		layout.putConstraint(SpringLayout.NORTH, veclabel[0], 8, SpringLayout.SOUTH, bestCombinationLabel);
		
		for(int i = 1; i < 6; i++) {
			layout.putConstraint(SpringLayout.WEST, veclabel[i], 16, SpringLayout.EAST, veclabel[i-1]);
			layout.putConstraint(SpringLayout.NORTH, veclabel[i], 0, SpringLayout.NORTH, veclabel[i-1]);
		}
		
		layout.putConstraint(SpringLayout.WEST, button, -32, SpringLayout.HORIZONTAL_CENTER, pane);
		layout.putConstraint(SpringLayout.EAST, button, 32, SpringLayout.HORIZONTAL_CENTER, pane);
		layout.putConstraint(SpringLayout.NORTH, button, -32, SpringLayout.SOUTH, button);
		layout.putConstraint(SpringLayout.SOUTH, button, -16, SpringLayout.SOUTH, pane);

		pane.add(bestHeuristicLabel);
		pane.add(testCountLabel);
		pane.add(bestCombinationLabel);
		for (int i = 0; i < 6; i++) {
			pane.add(veclabel[i]);
		}
		pane.add(bestHeuristicText);
		pane.add(button);
		
		return pane;
	}

	private Component getForceItemPane() {
		final Item[] fixItemList = new Item[Main.combinedList.length];
		boolean[] forcing = new boolean[fixItemList.length];
		System.arraycopy(Main.combinedList, 0, fixItemList, 0, fixItemList.length);
    	Container pane = new Container();
		FlowLayout layout = new FlowLayout();
		pane.setLayout(layout);
		
		for(int i = 0; i < fixItemList.length; i++) {
			vecLabelCombinedList[i] = new JLabel();
		}
		
		/*for(int i = 1; i < 90; i++) {
			layout.putConstraint(SpringLayout.WEST, vecLabelCombinedList[i], 16, SpringLayout.EAST, vecLabelCombinedList[i-1]);
			layout.putConstraint(SpringLayout.NORTH, vecLabelCombinedList[i], 0, SpringLayout.NORTH, vecLabelCombinedList[i-1]);
		}*/
		
		for (int i = 1; i < fixItemList.length; i++) {
			System.out.println(Main.itemArrayList.get(i).itemImg());
			try {
				MainScreen.vecCombinedList[i] = ImageIO.read(new File(fixItemList[i].itemImg()));
				MainScreen.vecLabelCombinedList[i].setIcon(new ImageIcon(GrayFilter.createDisabledImage(MainScreen.vecCombinedList[i])));
				MainScreen.vecLabelCombinedList[i].setToolTipText(fixItemList[i].name);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		for (int i = 0; i < fixItemList.length; i++) {
			vecLabelCombinedList[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			final int index = i;
			vecLabelCombinedList[i].addMouseListener(new MouseInputListener() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					System.out.println("Selecionado o item " + fixItemList[index]);
					forcing[index] = !forcing[index];
					if(forcing[index])
					{
						MainScreen.vecLabelCombinedList[index].setIcon(new ImageIcon(MainScreen.vecCombinedList[index]));
						for(int i = 0;i < 6;i++)
						{
							if(Main.forcingItem[i] == null)
							{
								System.out.println("Forçando item " + fixItemList[index].name + " no slot " + (i+1));
								Main.forcingItem[i] = fixItemList[index];
								break;
							}
						}
					}
					else
					{
						MainScreen.vecLabelCombinedList[index].setIcon(new ImageIcon(GrayFilter.createDisabledImage(MainScreen.vecCombinedList[index])));
						for(int i = 0;i < 6;i++)
						{
							if(Main.forcingItem[i] == fixItemList[index])
							{
								Main.forcingItem[i] = null;
								break;
							}
						}
					}
				}

				@Override
				public void mousePressed(java.awt.event.MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(java.awt.event.MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(java.awt.event.MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(java.awt.event.MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseDragged(java.awt.event.MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseMoved(java.awt.event.MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
			pane.add(vecLabelCombinedList[i]);
		}
		
		return pane;
    }

	private void initTabComponent(int i) {
        tabsPane.setTabComponentAt(i, null);
    }    

    //Setting menu
    
    private void initMenu() {
        /*JMenuBar menuBar = new JMenuBar();
        scrollLayoutItem = new JCheckBoxMenuItem("Set ScrollLayout");
        scrollLayoutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_MASK));
        scrollLayoutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (pane.getTabLayoutPolicy() == JTabbedPane.WRAP_TAB_LAYOUT) {
                    pane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
                } else {
                    pane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
                }
            }
        });
        JMenuItem resetItem = new JMenuItem("Reset JTabbedPane");
        resetItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.ALT_MASK));
        resetItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                runTest();
            }
        });*/
    }
}
