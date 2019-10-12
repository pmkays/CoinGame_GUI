package view;

import java.awt.BorderLayout;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

import controller.AddPlayerPanelListener;
import controller.MenuBarActionListener;
import controller.PlaceBetPanelListener;
import controller.ToolbarViewModel;
import model.interfaces.GameEngine;

public class MainFrame extends JFrame
{
	private Toolbar toolbar;
	private AddPlayerPanel addPlayerPanel;
	private PlaceBetPanel placeBetPanel;
	private CoinPanel coinPanel;
	private CoinPanel lastCoinsPanel;
	private SummaryPanel summaryPanel;
	private StatusBarPanel statusBarPanel;
	private SidePanels sidePanels; 
	private GameEngine gameEngine;
	
	public MainFrame(GameEngine gameEngine)
	{
		super("Coin Game");

		setLayout(new BorderLayout());
		this.gameEngine = gameEngine;
		toolbar = new Toolbar();
		toolbar.showPlayers(gameEngine.getAllPlayers());
		
		addPlayerPanel = new AddPlayerPanel();
		placeBetPanel = new PlaceBetPanel(toolbar);
		coinPanel = new CoinPanel(toolbar, this);
		lastCoinsPanel = new CoinPanel(toolbar, this);
		statusBarPanel = new StatusBarPanel();
		summaryPanel = new SummaryPanel(gameEngine, statusBarPanel); 
		sidePanels = new SidePanels(placeBetPanel, addPlayerPanel);

		setJMenuBar(createMenuBar());
		
		ToolbarViewModel toolbarListener = new ToolbarViewModel(toolbar, gameEngine, summaryPanel, MainFrame.this);
		toolbar.setToolbarListener(toolbarListener);
		
		AddPlayerPanelListener addPlayerListener = new AddPlayerPanelListener(gameEngine, MainFrame.this, summaryPanel, toolbar);
		addPlayerPanel.setAddPlayerListener(addPlayerListener);
		
		PlaceBetPanelListener placeBetPanelListener = new PlaceBetPanelListener(gameEngine, MainFrame.this, summaryPanel, toolbar);
		placeBetPanel.setPlaceBetPanelListener(placeBetPanelListener);
		
		add(coinPanel, BorderLayout.CENTER);
		add(toolbar, BorderLayout.NORTH);
		add(summaryPanel, BorderLayout.SOUTH);
		add(sidePanels, BorderLayout.EAST);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize (new Dimension(775, 765));
		setSize(800, 800);
		setVisible(true);
		
	}
	
	public CoinPanel getCoinPanel() 
	{
		return coinPanel;
	}

	public Toolbar getToolbar() 
	{
		return toolbar;
	}

	public AddPlayerPanel getAddPlayerPanel() 
	{
		return addPlayerPanel;
	}

	public PlaceBetPanel getPlaceBetPanel() 
	{
		return placeBetPanel;
	}

	public GameEngine getGameEngine() 
	{
		return gameEngine;
	}

	public SummaryPanel getSummaryPanel() 
	{
		return this.summaryPanel;
	}
	
	public void setCoinPanel(CoinPanel coinPanel)
	{
		this.coinPanel = coinPanel;
	}

	private JMenuBar createMenuBar()
	{
		JMenuBar menuBar= new JMenuBar(); 
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem exitItem = new JMenuItem("Exit");
		MenuBarActionListener menubarActionListener = new MenuBarActionListener(this);
		fileMenu.add(exitItem);
		
		menuBar.add(fileMenu);
		
		//configure what the Exit menu item does
		exitItem.setMnemonic(KeyEvent.VK_X);
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		exitItem.addActionListener(menubarActionListener);
		return menuBar; 
	}

	public CoinPanel getLastCoinsPanel() 
	{
		return lastCoinsPanel;
	}

}
