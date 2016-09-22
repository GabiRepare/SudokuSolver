package GUI;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import Game.Player;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.JToggleButton;

import java.awt.Font;

import javax.swing.SwingConstants;


public class GUI extends JFrame implements ActionListener, MouseListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Player player;
	private JPanel contentPane;
	private JPanel[][] cell;
	private JLabel[][] label;
	private JPanel[] zone;
	private JToggleButton[] toggle;
	private JButton btnAlg1;
	private JButton btnAlg2;
	private JButton btnAlg3;
	private JButton btnAlg4;
	private JButton btnAutoBasic;
	private JButton btnAutoAdv;
	private JButton btnAbout;
	private JButton btnReset;
	private JButton btnQuit;
	private JPanel selectedCell = null;
	private int selectedCellX = 0;
	private int selectedCellY = 0;
	private Boolean[] highlight;
	
	

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GUI frame = new GUI();
//					
//					try {
//					      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//					    } catch (ClassNotFoundException e) {
//					      e.printStackTrace();
//					    } catch (InstantiationException e) {
//					      e.printStackTrace();
//					    } catch (IllegalAccessException e) {
//					      e.printStackTrace();
//					    } catch (UnsupportedLookAndFeelException e) {
//					      e.printStackTrace();
//					    }
//					
//					SwingUtilities.updateComponentTreeUI(frame);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public GUI() 
	{
		setTitle("Sudoku Solver");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 559, 604);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel board = new JPanel();
		board.setLayout(new GridLayout(3,3));
		contentPane.add(board, BorderLayout.CENTER);
		
		highlight = new Boolean[]{false, false, false, false, false, false, false, false, false};
		
		//Zone Creator
		zone = new JPanel[9];
		for (int i = 0; i < 9 ; i++)
		{
			zone[i] = new JPanel();
			zone[i].setBorder(new LineBorder(new Color(0,0,0)));
			zone[i].setLayout(new GridLayout(3,3));
			board.add(zone[i]);
			
		}
		
//		JPanel panel = new JPanel();
//		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
//		

//		board.add(panel);
//		panel.setLayout(new GridLayout(3,3));
		
		//Cell creator
		cell = new JPanel[9][9];
		label = new JLabel[9][9];
		for (int y = 0 ; y < 9 ; y++)
		{
			for (int x = 0; x < 9 ; x++)
			{
				cell[x][y] = new JPanel();
				cell[x][y].setBackground(Color.WHITE);
				cell[x][y].setBorder(new LineBorder(new Color(0, 0, 0)));
				cell[x][y].setLayout(new BorderLayout(0,0));
				int currentZone = 3*(y/3)+x/3;
				zone[currentZone].add(cell[x][y]);
				
				label[x][y] = new JLabel("");
				label[x][y].setHorizontalAlignment(SwingConstants.CENTER);
				cell[x][y].add(label[x][y], BorderLayout.CENTER);
				cell[x][y].addMouseListener(this);
			}
		}
		

		

		
		
		JPanel upToolPanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) upToolPanel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		upToolPanel.setBorder(new TitledBorder(null, "Highlight", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(upToolPanel, BorderLayout.NORTH);
		
		//Toggle Button creation
		toggle = new JToggleButton[9];
		
		for (int i = 0 ; i < 9; i++)
		{
			toggle[i] = new JToggleButton(new Integer(i+1).toString());
			toggle[i].setFont(new Font("Tahoma", Font.BOLD, 30));
			upToolPanel.add(toggle[i]);
			toggle[i].addActionListener(this);
			toggle[i].addKeyListener(this);
		}

		
		JPanel sideButtonPanel = new JPanel();
		sideButtonPanel.setBorder(new CompoundBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Algorithm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), null));
		contentPane.add(sideButtonPanel, BorderLayout.EAST);
		sideButtonPanel.setLayout(new BoxLayout(sideButtonPanel, BoxLayout.Y_AXIS));
		
		btnAlg1 = new JButton("Alg 1");
		sideButtonPanel.add(btnAlg1);
		btnAlg1.addActionListener(this);
		btnAlg1.addKeyListener(this);
		
		btnAlg2 = new JButton("Alg 2");
		sideButtonPanel.add(btnAlg2);
		btnAlg2.addActionListener(this);
		btnAlg2.addKeyListener(this);
		
		btnAlg3 = new JButton("Alg 3");
		sideButtonPanel.add(btnAlg3);
		btnAlg3.addActionListener(this);
		btnAlg3.addKeyListener(this);
		
		btnAlg4 = new JButton("Alg 4");
		sideButtonPanel.add(btnAlg4);
		btnAlg4.addActionListener(this);
		btnAlg4.addKeyListener(this);
		
		btnAutoBasic = new JButton("Auto");
		sideButtonPanel.add(btnAutoBasic);
		btnAutoBasic.addActionListener(this);
		btnAutoBasic.addKeyListener(this);
		
		btnAutoAdv = new JButton("Auto Adv");
		sideButtonPanel.add(btnAutoAdv);
		btnAutoAdv.addActionListener(this);
		btnAutoAdv.addKeyListener(this);
		
		//make all buttons in sidePanel the same size
		for (int i = 0; i < sideButtonPanel.getComponentCount(); i++){
			Dimension d = sideButtonPanel.getComponent(i).getPreferredSize();
			Dimension newD =  new Dimension(sideButtonPanel.getPreferredSize().width - 17, d.height);
			sideButtonPanel.getComponent(i).setMaximumSize(newD);
		}
		
		JPanel BottomControlsPanel = new JPanel();
		contentPane.add(BottomControlsPanel, BorderLayout.SOUTH);
		BottomControlsPanel.setLayout(new BorderLayout(0, 0));
		
		btnAbout = new JButton("About");
		BottomControlsPanel.add(btnAbout, BorderLayout.WEST);
		btnAbout.addActionListener(this);
		btnAbout.addKeyListener(this);
		
		JPanel bottomRightControls = new JPanel();
		bottomRightControls.setBorder(null);
		BottomControlsPanel.add(bottomRightControls, BorderLayout.EAST);
		bottomRightControls.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		btnReset = new JButton("Reset");
		bottomRightControls.add(btnReset, BorderLayout.CENTER);
		btnReset.addActionListener(this);
		btnReset.addKeyListener(this);
		
		btnQuit = new JButton("Quit");
		bottomRightControls.add(btnQuit, BorderLayout.EAST);
		btnQuit.addActionListener(this);
		btnQuit.addKeyListener(this);
	}
	
	//*****************************
	//****   Private Methods   ****
	//*****************************  +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	private void cellClicked(int x, int y)
	{
		if (selectedCell == null)
		{
			selectedCell = cell[x][y];
			selectedCellX = x;
			selectedCellY = y;
			cell[x][y].setBorder(new LineBorder(Color.RED, 2));			
		}
		else if (selectedCell == cell[x][y])
		{
			selectedCell = null;
			cell[x][y].setBorder(new LineBorder(new Color(0, 0, 0)));
		}
		else
		{
			selectedCell.setBorder(new LineBorder(new Color(0, 0, 0)));
			selectedCell = cell[x][y];
			selectedCellX = x;
			selectedCellY = y;
			cell[x][y].setBorder(new LineBorder(Color.RED, 2));
		}
	}
	
	
	private void toggleClicked(int x)
	{
		if (highlight[x])
		{
			highlight[x] = false;
		}
		else
		{
			highlight[x] = true;
		}
		
		refreshHihlight();
	}
	
	
	private void refreshHihlight()
	{
		for (int y = 0; y < 9; y++)
		{
			for (int x = 0; x < 9; x++)
			{
				Boolean needHighlight = false;
				
				for (int i = 0; i < 9; i++)
				{
					if (highlight[i])
					{
						if (label[x][y].getText().contains(new Integer(i+1).toString()))
						{
							needHighlight = true;
						}
					}
				}
				
				if (needHighlight)
				{
					cell[x][y].setBackground(Color.YELLOW);
				}
				else
				{
					cell[x][y].setBackground(Color.WHITE);
				}
			}
		}
	}

	//****************************
	//****   Public Methods   ****
	//****************************  ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	public void setPlayer(Player player)
	{
		this.player = player;
	}
	
	public void setNumber(int x, int y, int n)
	{
		label[x][y].setText(new Integer(n).toString());
		label[x][y].setFont(new Font("Tahoma", Font.PLAIN, 45));
		refreshHihlight();
	}
	
	public void gameWinnedPopUp ()
	{
		JOptionPane.showMessageDialog(this, "Sudoku completed!","Game Notification",JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void gameImpossiblePopUp ()
	{
		JOptionPane.showMessageDialog(this, "This sudoku is impossible!", "Game Notification", JOptionPane.WARNING_MESSAGE);
	}
	
	public void autoNoResultPopUp ()
	{
		JOptionPane.showMessageDialog(this, "Auto adv has'nt found a solution to this sudoku", "Game Notification", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void setPossibilities(int x, int y, Boolean[] poss)
	{
		String text = "<html>";
		for (int i = 0; i < 9 ; i++)
		{
			if (poss[i])
			{
				text = text + new Integer(i+1).toString();
			}
			else
			{
				text = text + "&nbsp;&nbsp;";
			}
			
			if ((i+1)%3 == 0 && i != 8)
			{
				text = text + "<br>";
			}
			else if(i != 8)
			{
				text = text + "<&ensp ";
			}
		}
		
		label[x][y].setText(text);
		label[x][y].setFont(new Font("Tahoma", Font.PLAIN, 12));
		refreshHihlight();
	}
	
	
	
	//************************
	//****   Overrides    ****
	//************************  ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++	

	@Override
	public void actionPerformed(ActionEvent e) {
		//Find the object associated with the event
		Boolean isFound = false;
		Object source = e.getSource();
		
		
		//Test for toggle		
		for (int i=0; i < 9; i++)
		{
			if (source == toggle[i])
			{
				toggleClicked(i);
				isFound = true; 
				i = 9;
			}
		}
		
		if (!isFound)
		{
			if (source == btnAlg1)
			{
				player.alg1();
				player.isGameWon();
				player.isPossible();
			}
			else if (source == btnAlg2)
			{
				player.alg2();
				player.isGameWon();
				player.isPossible();
			}
			else if (source == btnAlg3)
			{
				player.alg3();
				player.isGameWon();
				player.isPossible();
			}
			else if (source == btnAlg4)
			{
				player.alg4();
				player.isGameWon();
				player.isPossible();
			}
			else if (source == btnAutoBasic)
			{
				player.autoPlayBasic();
				player.isGameWon();
				player.isPossible();
			}
			else if (source == btnAutoAdv)
			{
				player.autoPlayAdv();
				player.isGameWon();
				player.isPossible();
			}
			else if (source == btnAbout)
			{
				String nl = System.getProperty("line.separator");
				String message = "\u00a9" + "Gabriel Lalonde" + nl;
				message += "Program created in July 2015";
				JOptionPane.showMessageDialog(this, message ,"About",JOptionPane.INFORMATION_MESSAGE);
			}
			else if (source == btnQuit)
			{
				System.exit(0);
			}
			else if (source == btnReset)
			{
				player.resetGame();
			}
			else
			{
				
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{		
		//Do nothing
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		//Do nothing
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		//Do nothing 
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		//Test for cell
		Object source = e.getSource();

		for (int x=0; x < 9; x++)
		{
			for (int y=0; y < 9; y++)
			{
				if (source == cell[x][y])
				{
					cellClicked(x,y);
					x = 9;
					y = 9;
				}
			}
		}
						
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		//Do nothing
		
	}

	@Override
	public void keyPressed(KeyEvent e)
	 {		if (e.getKeyCode() == KeyEvent.VK_ESCAPE  && selectedCell != null)
		{
			selectedCell.setBorder(new LineBorder(new Color(0,0,0)));
			selectedCell = null;
		}
		else if (selectedCell != null && (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_KP_LEFT))
		{
			if (selectedCellX > 0)
			{
				selectedCell.setBorder(new LineBorder(new Color(0, 0, 0)));
				selectedCellX--;
				selectedCell = cell[selectedCellX][selectedCellY];
				selectedCell.setBorder(new LineBorder(Color.RED, 2));
			}
		}
		else if (selectedCell != null && (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_KP_RIGHT))
		{
			if (selectedCellX < 8)
			{
				selectedCell.setBorder(new LineBorder(new Color(0, 0, 0)));
				selectedCellX++;
				selectedCell = cell[selectedCellX][selectedCellY];
				selectedCell.setBorder(new LineBorder(Color.RED, 2));
			}
		}
		else if (selectedCell != null && (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_KP_UP))
		{
			if (selectedCellY > 0)
			{
				selectedCell.setBorder(new LineBorder(new Color(0, 0, 0)));
				selectedCellY--;
				selectedCell = cell[selectedCellX][selectedCellY];
				selectedCell.setBorder(new LineBorder(Color.RED, 2));
			}
		}
		else if (selectedCell != null && (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_KP_DOWN))
		{
			if (selectedCellY < 8)
			{
				selectedCell.setBorder(new LineBorder(new Color(0, 0, 0)));
				selectedCellY++;
				selectedCell = cell[selectedCellX][selectedCellY];
				selectedCell.setBorder(new LineBorder(Color.RED, 2));
			}
		}
		else if (selectedCell != null && (e.getKeyCode() == KeyEvent.VK_DELETE))
		{
			
			player.deleteCell(selectedCellX, selectedCellY);
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{	
		char input  = e.getKeyChar();
		
		if (selectedCell != null)
		{	
			switch (input)
			{
			case '1': player.setCell(selectedCellX, selectedCellY, 1); break;
			case '2': player.setCell(selectedCellX, selectedCellY, 2); break;
			case '3': player.setCell(selectedCellX, selectedCellY, 3); break;
			case '4': player.setCell(selectedCellX, selectedCellY, 4); break;
			case '5': player.setCell(selectedCellX, selectedCellY, 5); break;
			case '6': player.setCell(selectedCellX, selectedCellY, 6); break;
			case '7': player.setCell(selectedCellX, selectedCellY, 7); break;
			case '8': player.setCell(selectedCellX, selectedCellY, 8); break;
			case '9': player.setCell(selectedCellX, selectedCellY, 9); break;
			case '0': player.deleteCell(selectedCellX, selectedCellY); break;	//Need verification	
			}
			
			player.isGameWon();
			player.isPossible();
		}
	}

}
