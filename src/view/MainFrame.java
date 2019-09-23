package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

import controller.Controller;

public class MainFrame extends JFrame
{
	private JPanel coinPanel;
	private Toolbar toolbar;
	private AddPlayerPanel addPlayerPanel;
	private RemovePlayerPanel removePlayerPanel;
	private PlaceBetPanel placeBetPanel;
	private RemoveBetPanel removeBetPanel;
	private Controller controller;
	
	public MainFrame()
	{
		super("Coin Game");
		
		setLayout(new BorderLayout());
		
		coinPanel = new JPanel(); 
		toolbar = new Toolbar();
		controller = new Controller();
		
		addPlayerPanel = new AddPlayerPanel();
		removePlayerPanel = new RemovePlayerPanel();
		placeBetPanel = new PlaceBetPanel();
		removeBetPanel = new RemoveBetPanel();
		
		//set all panels to invisible initially
		addPlayerPanel.setVisible(false);
		removePlayerPanel.setVisible(false);
		placeBetPanel.setVisible(false);
		removeBetPanel.setVisible(false);
		
		setJMenuBar(createMenuBar());
		
		toolbar.setToolbarListener(new ToolbarListener()
		{

			@Override
			public void toolbarEventOccurred(JButton button) 
			{
				if(button == toolbar.getAddPlayerButton())
				{
					add(addPlayerPanel, BorderLayout.EAST);
					addPlayerPanel.setVisible(true);
					removePlayerPanel.setVisible(false);
					placeBetPanel.setVisible(false);
					removeBetPanel.setVisible(false);
				}
				else if (button == toolbar.getRemovePlayerButton())
				{
					add(removePlayerPanel, BorderLayout.EAST);
					removePlayerPanel.setVisible(true);
					addPlayerPanel.setVisible(false);
					placeBetPanel.setVisible(false);
					removeBetPanel.setVisible(false);
					removePlayerPanel.showPlayers(controller.getPlayers());
				}
				else if (button == toolbar.getPlaceBetButton())
				{
					add(placeBetPanel, BorderLayout.EAST);
					placeBetPanel.setVisible(true);
					removePlayerPanel.setVisible(false);
					addPlayerPanel.setVisible(false);
					removeBetPanel.setVisible(false);
					placeBetPanel.showPlayers(controller.getPlayers());	
				}
				else if (button == toolbar.getRemoveBetButton())
				{
					add(removeBetPanel, BorderLayout.EAST);
					removeBetPanel.setVisible(true);
					placeBetPanel.setVisible(false);
					removePlayerPanel.setVisible(false);
					addPlayerPanel.setVisible(false);
					removeBetPanel.showPlayers(controller.getPlayers());					
				}
			}
		});
		
		addPlayerPanel.setAddPlayerListener(new AddPlayerListener()
		{

			@Override
			public void addPlayerEventOccurred(AddPlayerEvent e) 
			{
				controller.addPlayer(e);
				JOptionPane.showMessageDialog(MainFrame.this,
				        "Player: " + e.getId() + " added successfully", "Player Added",
				        JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		removePlayerPanel.setRemovePlayerListener(new RemovePlayerListener() 
		{
			@Override
			public void removePlayerEventOccurred(String id) 
			{
				controller.removePlayer(id);
				if(!id.equals("No players added") || !id.isEmpty()) //slight bug if not refreshed
				{
					JOptionPane.showMessageDialog(MainFrame.this,
					        "Player: " + id + " removed successfully", "Player Removed",
					        JOptionPane.INFORMATION_MESSAGE);	
				}
				else
				{
					JOptionPane.showMessageDialog(MainFrame.this,
					        "No players to remove", "Player Removed",
					        JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		placeBetPanel.setPlaceBetPanelListener(new PlaceBetPanelListener() 
		{
			@Override
			public void placeBetPanelEventOccurred(PlaceBetPanelEvent e) 
			{
				controller.placeBet(e);
				JOptionPane.showMessageDialog(MainFrame.this,
				        "Bet successfully placed for Player:"  + e.getId(), "Bet Placed",
				        JOptionPane.INFORMATION_MESSAGE);
			}
				
		});
		
		removeBetPanel.setRemoveBetPanelListener(new RemoveBetPanelListener() 
		{
			@Override
			public void removeBetPanelEventOccurred(String id) 
			{
				controller.removeBet(id);
				JOptionPane.showMessageDialog(MainFrame.this,
				        "Bet successfully removed for Player:"  + id, "Bet Placed",
				        JOptionPane.INFORMATION_MESSAGE);	
			}
		});
		
		
		add(coinPanel, BorderLayout.CENTER);
		add(toolbar, BorderLayout.NORTH);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize (new Dimension(650, 450));
		setSize(700, 500);
		setVisible(true);
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
