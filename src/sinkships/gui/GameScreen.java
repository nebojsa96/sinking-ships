package sinkships.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;


public class GameScreen extends JFrame{

	private JPanel contentPane;
	protected JRadioButton rbServer;
	protected JRadioButton rbClient;
	protected JTextField ipField;
	protected JTextField portField;
	protected JTextField nameField;
	protected JButton btnConnect;
	protected JButton btnPlay;
	protected JButton btnFire;
	protected JLabel lbInfo;
	
	private JPanel gamePanel;
	protected ButtonTable myTable;
	protected ButtonTable enemyTable;
	
	private JPanel chatPanel;
	protected JTextField msgField;
	protected JTextPane chatWindow;
	
	public Color sysColor = Color.BLACK;  //BOJE U CHAT-U
	public Color senderColor = Color.BLUE;
	public Color receiverColor = Color.RED;
	
	
	public GameScreen(String title) {
		setTitle(title);
		setResizable(false);
		setBounds(100, 100, 576, 490);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
//		setMainScreen(); 
//		setGamePanel();
//		setChatPanel();
//
//		pack();
//		setVisible(true);
//		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		
	}
	
	protected void setMainScreen(){
		gamePanel = new JPanel();	
		chatPanel = new JPanel();
		
		final ButtonGroup buttonGroup = new ButtonGroup();
		rbServer = new JRadioButton("Server");
		buttonGroup.add(rbServer);
		rbClient = new JRadioButton("Client");
		buttonGroup.add(rbClient);
		
		JLabel lb1 = new JLabel("IP Address:");
		ipField = new JTextField();
		ipField.setColumns(10);
		
		JLabel lbl2 = new JLabel("Port:");
		portField = new JTextField();
		portField.setText("6066");
		portField.setColumns(10);
		
		JLabel lb3 = new JLabel("Ime:");
		nameField = new JTextField();
		nameField.setColumns(10);
		
		btnConnect = new JButton("Connect");
		//btnConnect.setBackground(Color.yellow);
		btnPlay = new JButton("PLAY!");
		btnPlay.setEnabled(false);
		
		lbInfo = new JLabel();
		
		JLabel lblPritisniteDaPosaljete = new JLabel("Pritisnite <ENTER> da posaljete poruku");
		
		btnFire = new JButton("FIRE");
		btnFire.setEnabled(false);
		
		
		//GROUP LAYOUT MANAGER
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lbInfo)
							.addPreferredGap(ComponentPlacement.RELATED, 383, Short.MAX_VALUE)
							.addComponent(btnFire, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
						.addComponent(gamePanel, GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(rbServer)
								.addComponent(rbClient))
							.addGap(121)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lb1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(ipField, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lb3)
									.addGap(18)
									.addComponent(nameField)))
							.addGap(18)
							.addComponent(lbl2)
							.addGap(4)
							.addComponent(portField, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPritisniteDaPosaljete)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(chatPanel, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(btnPlay, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
									.addComponent(btnConnect, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rbServer, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lb1)
						.addComponent(ipField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbl2, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(portField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnConnect, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rbClient)
						.addComponent(lb3)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPlay))
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(gamePanel, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addComponent(lbInfo))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnFire, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))))
						.addComponent(chatPanel, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPritisniteDaPosaljete))
		);
		
		contentPane.setLayout(gl_contentPane);
	}
	
	protected void setChatPanel(){
		chatPanel.setLayout(new BorderLayout(0, 0));
		
		msgField = new JTextField();
		msgField.setColumns(3);
		chatPanel.add(msgField, BorderLayout.SOUTH);
		msgField.setEditable(false);
		
		chatWindow = new JTextPane();
		chatWindow.setFocusable(false);
		JScrollPane scrollPane = new JScrollPane(chatWindow);
		chatPanel.add(scrollPane, BorderLayout.CENTER);
	}
	
	protected void setGamePanel(){

		gamePanel.setLayout(new GridLayout(1, 2, 5, 5));
		
		JPanel leftTable = new JPanel(new GridLayout(10,10));
		leftTable.setBorder(BorderFactory.createTitledBorder("moja tabla")/*new EmptyBorder(0, 0, 0, 5)*/);

		myTable = new ButtonTable(leftTable, 20, 20);
		gamePanel.add(leftTable);
		
		JPanel rightTable = new JPanel(new GridLayout(10,10));
		rightTable.setBorder(BorderFactory.createTitledBorder("protivnikova tabla")/*new EmptyBorder(0, 5, 0, 0)*/);
	
		enemyTable = new ButtonTable(rightTable, 20, 20);
		gamePanel.add(rightTable);
		
	}
}
