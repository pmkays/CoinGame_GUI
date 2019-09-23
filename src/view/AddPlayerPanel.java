package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class AddPlayerPanel extends JPanel 
{
	private JLabel idLabel; 
	private JLabel nameLabel;
	private JLabel pointsLabel;
	private JTextField idField; 
	private JTextField nameField;
	private JTextField pointsField; 
	private JButton okButton;
	
	private AddPlayerListener addPlayerListener;
	 
	public AddPlayerPanel()
	{
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
		Border innerBorder = BorderFactory.createTitledBorder("Add Player");
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		//set up individual components
		okButton = new JButton("Add");
		
		idLabel = new JLabel("Player ID: ");
		nameLabel = new JLabel("Player Name: ");
		
		idField = new JTextField(10);
		nameField = new JTextField(10);
		
		pointsLabel = new JLabel("Initial Points: ");
		pointsField = new JTextField(10);

		okButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String id = idField.getText();
				String name = nameField.getText();
				String points = pointsField.getText();
				
				AddPlayerEvent ev = new AddPlayerEvent(this, id, name, points);
				
				if (addPlayerListener != null)
				{
					addPlayerListener.addPlayerEventOccurred(ev);
				}
			}	
		});

		this.layoutComponents();
	}
	
	public void layoutComponents()
	{
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		//first row
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0; 
		gc.gridy = 0;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(3,3,3,5);
		add(idLabel, gc);
		
		gc.gridx = 1;
//		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		add(idField, gc);
		
		//next row
		gc.gridy++; //on 1
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0; 
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(3,3,3,5);
		add(nameLabel, gc);
		
		gc.gridx = 1; 
		gc.anchor = GridBagConstraints.LINE_START; //moves it to the top
		add(nameField, gc);
		
		//next row
		gc.gridy++; //on 2
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(3,3,0,5);
		add(pointsLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(pointsField, gc);
		
		//final row fix OK BUTTON
		gc.gridy++; //on 2
		gc.weightx = 1;
		gc.weighty = 2;
		
		gc.gridx = 1;
		add(okButton, gc);
	}

	public void setAddPlayerListener(AddPlayerListener addPlayerListener) 
	{
		this.addPlayerListener = addPlayerListener;
	}

}

