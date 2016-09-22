package main;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import GUI.GUI;
import Game.Player;

public class SudokuSolver 
{
	private static GUI gui;
	private static Player player;
	
	public static void main(String[] args)
	{
		
		try {
			gui = new GUI();
			player = new Player(gui);
			gui.setPlayer(player);
			//test();
			
			
			
			try {
			      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			    } catch (ClassNotFoundException e) {
			      e.printStackTrace();
			    } catch (InstantiationException e) {
			      e.printStackTrace();
			    } catch (IllegalAccessException e) {
			      e.printStackTrace();
			    } catch (UnsupportedLookAndFeelException e) {
			      e.printStackTrace();
			    }
			
			SwingUtilities.updateComponentTreeUI(gui);
			gui.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void test (){
		//TESTING
				//*********************************************!!!!!!!!!!!!!**********!!!!!!!!!!************!!!!!!!!!!!***********!!!!!!!!!!
				//Case 1
				player.setCell(0,0,8);
				player.setCell(1,2,7);
				player.setCell(2,1,3);
				player.setCell(3,1,6);
				player.setCell(4,2,9);
				player.setCell(6,2,2);
				player.setCell(1,3,5);
				player.setCell(3,5,1);
				player.setCell(4,4,4);
				player.setCell(5,3,7);
				player.setCell(5,4,5);
				player.setCell(6,4,7);
				player.setCell(7,5,3);
				player.setCell(1,8,9);
				player.setCell(2,6,1);
				player.setCell(2,7,8);
				player.setCell(3,7,5);
				player.setCell(6,8,4);
				player.setCell(7,6,6);
				player.setCell(7,7,1);
				player.setCell(8,6,8);
				
				//case 2
//				player.setCell(0,1,4);
//				player.setCell(1,2,3);
//				player.setCell(3,0,8);
//				player.setCell(3,1,7);
//				player.setCell(4,1,2);
//				player.setCell(4,2,5);
//				player.setCell(6,0,6);
//				player.setCell(7,0,4);
//				player.setCell(8,0,1);
//				player.setCell(0,4,7);
//				player.setCell(1,3,6);
//				player.setCell(2,3,1);
//				player.setCell(2,4,9);
//				player.setCell(6,4,1);
//				player.setCell(6,5,2);
//				player.setCell(7,5,8);
//				player.setCell(8,4,6);
//				player.setCell(0,8,6);
//				player.setCell(1,8,9);
//				player.setCell(2,8,3);
//				player.setCell(4,6,1);
//				player.setCell(4,7,4);
//				player.setCell(5,7,8);
//				player.setCell(5,8,2);
//				player.setCell(7,6,2);
//				player.setCell(8,7,3);
	}
	
}
