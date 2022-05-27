package view;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.lang.reflect.UndeclaredThrowableException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import bsh.EvalError;
import bsh.Interpreter;
import interfaces.CombinationCondition;
import interfaces.Heuristic;
import items.CrownOfTheShatteredQueen;
import items.HorizonFocus;
import items.Hullbreaker;
import items.Item;
import items.MejaiSoulstealer;
import items.Riftmaker;
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
    		"}";
    
    private static final String DEFAULT_CONDITION_CODE = "import items.*;\r\n" + 
    		"import main.*;\r\n" + 
    		"import interfaces.*;\r\n" + 
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
        tabsPane.removeAll();
        tabsPane.add("Heuristic", getHeuristicPane());
        initTabComponent(0);
        tabsPane.add("Settings", getSettingsPane());
        initTabComponent(1);
        tabsPane.add("Evaluation", getEvaluationPane());
        initTabComponent(2);
        
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
		
		JLabel conditionLabel = new JLabel("Condition function");
		
		JTextArea heuristicArea = new JTextArea(MainScreen.DEFAULT_HEURISTIC_CODE);
		JScrollPane heuristicField = new JScrollPane(heuristicArea);
		heuristicField.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		JTextArea conditionArea = new JTextArea(MainScreen.DEFAULT_CONDITION_CODE);
		JScrollPane conditionField = new JScrollPane(conditionArea);
		conditionField.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		runButton = new JButton("Run");
		runButton.addActionListener(e -> {
			Interpreter interpreter = new Interpreter();
			try
			{
				interpreter.eval(heuristicArea.getText());
				interpreter.eval(conditionArea.getText());
				RuntimeHeuristic.heuristic = (Heuristic)interpreter.eval("return (Heuristic)this;");
				RuntimeHeuristic.combinationCondition = (CombinationCondition)interpreter.eval("return (CombinationCondition)this;");
				Main.evaluatingItems.set(true);
				runButton.setEnabled(false);
				System.out.println("Threads:" + Thread.activeCount());
				lastEvaluationThread = new Thread() {
					@Override
					public void run()
					{
						try
						{
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

							tabsPane.setSelectedIndex(2);
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
		layout.putConstraint(SpringLayout.EAST, heuristicField, -8, SpringLayout.HORIZONTAL_CENTER, pane);

		layout.putConstraint(SpringLayout.WEST, conditionLabel, 0, SpringLayout.WEST, conditionField);
		layout.putConstraint(SpringLayout.SOUTH, conditionLabel, -8, SpringLayout.NORTH, conditionField);

		layout.putConstraint(SpringLayout.WEST, conditionField, 8, SpringLayout.HORIZONTAL_CENTER, pane);
		layout.putConstraint(SpringLayout.NORTH, conditionField, 32, SpringLayout.NORTH, pane);
		layout.putConstraint(SpringLayout.SOUTH, conditionField, -64, SpringLayout.SOUTH, pane);
		layout.putConstraint(SpringLayout.EAST, conditionField, -16, SpringLayout.EAST, pane);
		
		layout.putConstraint(SpringLayout.WEST, runButton, -32, SpringLayout.HORIZONTAL_CENTER, pane);
		layout.putConstraint(SpringLayout.EAST, runButton, 32, SpringLayout.HORIZONTAL_CENTER, pane);
		layout.putConstraint(SpringLayout.NORTH, runButton, -32, SpringLayout.SOUTH, runButton);
		layout.putConstraint(SpringLayout.SOUTH, runButton, -16, SpringLayout.SOUTH, pane);

		pane.add(heuristicField);
		pane.add(conditionField);
		pane.add(heuristicLabel);
		pane.add(conditionLabel);
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

		pane.add(itemQuant);
		pane.add(itemQuantText);
		pane.add(riftmaker);
		pane.add(mejaiLabel);
		pane.add(mejaiStacks);
		pane.add(crownShield);
		pane.add(horizonFocusPassive);
		pane.add(hullbreaker);
		
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

		layout.putConstraint(SpringLayout.WEST, bestCombinationText, 16, SpringLayout.WEST, pane);
		layout.putConstraint(SpringLayout.EAST, bestCombinationText, 256, SpringLayout.WEST, bestCombinationText);
		layout.putConstraint(SpringLayout.NORTH, bestCombinationText, 8, SpringLayout.SOUTH, bestCombinationLabel);
		layout.putConstraint(SpringLayout.SOUTH, bestCombinationText, 128, SpringLayout.NORTH, bestCombinationText);
		
		layout.putConstraint(SpringLayout.WEST, button, -32, SpringLayout.HORIZONTAL_CENTER, pane);
		layout.putConstraint(SpringLayout.EAST, button, 32, SpringLayout.HORIZONTAL_CENTER, pane);
		layout.putConstraint(SpringLayout.NORTH, button, -32, SpringLayout.SOUTH, button);
		layout.putConstraint(SpringLayout.SOUTH, button, -16, SpringLayout.SOUTH, pane);

		pane.add(bestHeuristicLabel);
		pane.add(testCountLabel);
		pane.add(bestCombinationLabel);
		pane.add(bestCombinationText);
		pane.add(bestHeuristicText);
		pane.add(button);
		
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
