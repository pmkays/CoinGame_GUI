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
import controller.ToolbarListener;
//import controller.Controller;
import model.GameEngineImpl;

public class MainFrame extends JFrame
{
	private SpinPanel coinPanel;
	private Toolbar toolbar;
	private AddPlayerPanel addPlayerPanel;
	private RemovePlayerPanel removePlayerPanel;
	private PlaceBetPanel placeBetPanel;
	private RemoveBetPanel removeBetPanel;
	private SpinPanel spinPanel;
	private GameEngineImpl gameEngine;
	
	public MainFrame()
	{
		super("Coin Game");
		
		setLayout(new BorderLayout());
		gameEngine = new GameEngineImpl();
		toolbar = new Toolbar();
		
		addPlayerPanel = new AddPlayerPanel();
		removePlayerPanel = new RemovePlayerPanel();
		placeBetPanel = new PlaceBetPanel();
		removeBetPanel = new RemoveBetPanel();
		spinPanel = new SpinPanel();
		
		//set all panels to invisible initially
		addPlayerPanel.setVisible(false);
		removePlayerPanel.setVisible(false);
		placeBetPanel.setVisible(false);
		removeBetPanel.setVisible(false);
		spinPanel.setVisible(false);
		
		setJMenuBar(createMenuBar());
		
		ToolbarListener toolbarListener = new ToolbarListener(toolbar, gameEngine, MainFrame.this);
		toolbar.setToolbarListener(toolbarListener);
		
		AddPlayerListener addPlayerListener = new AddPlayerListener(gameEngine, MainFrame.this);
		addPlayerPanel.setAddPlayerListener(addPlayerListener);
		
		RemovePlayerListener removePlayerListener = new RemovePlayerListener(gameEngine, MainFrame.this);
		removePlayerPanel.setRemovePlayerListener(removePlayerListener);
		
		PlaceBetPanelListener placeBetPanelListener = new PlaceBetPanelListener(gameEngine, MainFrame.this);
		placeBetPanel.setPlaceBetPanelListener(placeBetPanelListener);
		
		RemoveBetPanelListener removeBetPanelListener = new RemoveBetPanelListener(gameEngine, MainFrame.this);
		removeBetPanel.setRemoveBetPanelListener(removeBetPanelListener);
		
		SpinPanelListener spinPanelListener = new SpinPanelListener(gameEngine, MainFrame.this);
		spinPanel.setSpinPanelListener(spinPanelListener);
		
		
		add(coinPanel, BorderLayout.CENTER);
		add(toolbar, BorderLayout.NORTH);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize (new Dimension(650, 450));
		setSize(700, 500);
		setVisible(true);
	}
	
	public JPanel getCoinPanel() 
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

	public GameEngineImpl getGameEngine() 
	{
		return gameEngine;
	}

	public SpinPanel getSpinPanel() 
	{
		return spinPanel;
	}

	private JMenuBar createMenuBar()
	{
		JMenuBar menuBar= new JMenuBar(); 
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem exitItem = new JMenuItem("Exit");
//		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		
		JMenu windowMenu = new JMenu("Window");
		JMenu showMenu = new JMenu("Show");
		JMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
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
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) e.getSource();	
				addPlayerPanel.setVisible(menuItem.isSelected());
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

}
