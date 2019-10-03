package view;

import java.awt.BorderLayout;



import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

import controller.AddPlayerListener;
import controller.PlaceBetPanelListener;
import controller.RemoveBetPanelListener;
import controller.RemovePlayerListener;
import controller.SpinPanelListener;
import controller.ToolbarListener;
//import controller.Controller;
import model.interfaces.GameEngine;

public class MainFrame extends JFrame
{
	private Toolbar toolbar;
	private AddPlayerPanel addPlayerPanel;
	private RemovePlayerPanel removePlayerPanel;
	private PlaceBetPanel placeBetPanel;
	private RemoveBetPanel removeBetPanel;
	private SpinPanel spinPanel;
	private CoinPanel coinPanel;
	private CoinPanel lastCoinsPanel;
	private SummaryPanel summaryPanel;
	private StatusBarPanel statusBarPanel;
	private GameEngine gameEngine;
	
	public MainFrame(GameEngine gameEngine)
	{
		super("Coin Game");


		setLayout(new BorderLayout());
		this.gameEngine = gameEngine;
		toolbar = new Toolbar();
		
		addPlayerPanel = new AddPlayerPanel();
		removePlayerPanel = new RemovePlayerPanel();
		placeBetPanel = new PlaceBetPanel();
		removeBetPanel = new RemoveBetPanel();
		spinPanel = new SpinPanel();
		coinPanel = new CoinPanel();
		statusBarPanel = new StatusBarPanel();
		summaryPanel = new SummaryPanel(gameEngine, statusBarPanel); 
		
		//set all side panels to invisible initially
		terminatePanels();
		
		setJMenuBar(createMenuBar());
		
		ToolbarListener toolbarListener = new ToolbarListener(toolbar, gameEngine, MainFrame.this);
		toolbar.setToolbarListener(toolbarListener);
		
		AddPlayerListener addPlayerListener = new AddPlayerListener(gameEngine, MainFrame.this, summaryPanel);
		addPlayerPanel.setAddPlayerListener(addPlayerListener);
		
		RemovePlayerListener removePlayerListener = new RemovePlayerListener(gameEngine, MainFrame.this, summaryPanel);
		removePlayerPanel.setRemovePlayerListener(removePlayerListener);
		
		PlaceBetPanelListener placeBetPanelListener = new PlaceBetPanelListener(gameEngine, MainFrame.this, summaryPanel);
		placeBetPanel.setPlaceBetPanelListener(placeBetPanelListener);
		
		RemoveBetPanelListener removeBetPanelListener = new RemoveBetPanelListener(gameEngine, MainFrame.this, summaryPanel);
		removeBetPanel.setRemoveBetPanelListener(removeBetPanelListener);
		
		SpinPanelListener spinPanelListener = new SpinPanelListener(gameEngine, MainFrame.this, summaryPanel);
		spinPanel.setSpinPanelListener(spinPanelListener);
		
		
		add(coinPanel, BorderLayout.CENTER);
		add(toolbar, BorderLayout.NORTH);
		add(summaryPanel, BorderLayout.SOUTH);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize (new Dimension(700, 500));
		setSize(750, 550);
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

	public RemovePlayerPanel getRemovePlayerPanel() 
	{
		return removePlayerPanel;
	}

	public PlaceBetPanel getPlaceBetPanel() 
	{
		return placeBetPanel;
	}

	public RemoveBetPanel getRemoveBetPanel() 
	{
		return removeBetPanel;
	}

	public GameEngine getGameEngine() 
	{
		return gameEngine;
	}

	public SpinPanel getSpinPanel() 
	{
		return spinPanel;
	}
	
	public SummaryPanel getSummaryPanel() 
	{
		return this.summaryPanel;
	}
	
	public void setCoinPanel(CoinPanel coinPanel)
	{
//		this.coinPanel.setVisible(false);
		this.coinPanel = coinPanel;
		this.coinPanel.setVisible(false);
		this.coinPanel.setVisible(true);
//		this.coinPanel.setVisible(false);
//		add(lastCoinsPanel, BorderLayout.CENTER); 
//		this.coinPanel.setVisible(true);
//		remove(this.coinPanel);
//		this.coinPanel = coinPanel;
//		add(this.coinPanel, BorderLayout.CENTER);
//		this.coinPanel.setVisible(true); 
//		this.coinPanel.repaint();
//		this.coinPanel.revalidate();
	}

	private JMenuBar createMenuBar()
	{
		JMenuBar menuBar= new JMenuBar(); 
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem exitItem = new JMenuItem("Exit");
//		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		
		JMenu windowMenu = new JMenu("Window");
		JMenu showMenu = new JMenu("Collapse Panels");
		JMenuItem showFormItem = new JMenuItem("All");
		showFormItem.setSelected(true);
		showMenu.add(showFormItem);
		windowMenu.add(showMenu);
		
		menuBar.add(fileMenu);
		menuBar.add(windowMenu);
		
		showFormItem.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
//				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) e.getSource();	
				terminatePanels();
			}
		});
		
		fileMenu.setMnemonic(KeyEvent.VK_F);
		
		//configure what the Exit menu item does
		exitItem.setMnemonic(KeyEvent.VK_X);
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		exitItem.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				//can put this in for data
//				String text = JOptionPane.showInputDialog(MainFrame.this, 
//						"Enter your username", "Username", JOptionPane.OK_OPTION | JOptionPane.INFORMATION_MESSAGE);
//				
				int action = JOptionPane.showConfirmDialog(MainFrame.this, 
						"Do you really want to exit?", "Confirm exit", JOptionPane.OK_CANCEL_OPTION);
				
				if(action == JOptionPane.OK_OPTION)
				{
					System.exit(0);
				}
				
			}
		});
		
		return menuBar; 
	}
	
	public void terminatePanels()
	{
		addPlayerPanel.setVisible(false);
		removePlayerPanel.setVisible(false);
		placeBetPanel.setVisible(false);
		removeBetPanel.setVisible(false);
		spinPanel.setVisible(false);
	}

}
