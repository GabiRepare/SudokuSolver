package Game;

import java.util.Arrays;

import GUI.GUI;

public class Player 
{
	private GUI gui;
	private Board board;
	private Boolean changeFlag = false;
	private Boolean isGamePossible = true;
	
	public Player (GUI gui)
	{
		this.gui = gui;
		board = new Board();
		
		for (int x = 0; x < 9; x++)
		{
			for (int y = 0; y < 9 ; y++)
			{
				gui.setPossibilities(x, y, new Boolean[]{true, true, true, true, true, true, true, true, true});
			}
		}
	}
	
	public Player ()
	{
		gui = null;
		board = new Board();
	}
	
	
	//****************************
	//****   Public methods   ****
	//****************************
	
	//Find cell with single possibility
	public void alg1 ()
	{
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				if (board.getCell(x, y).getValue() == 0) {
					Boolean[] poss = board.getCell(x, y).getPossibilities();
					int count = 0;
					int n = 0;

					for (int i = 0; i < 9; i++) {
						if (poss[i]) {
							n = i;
							count++;
						}
					}

					if (count == 1) {
						setCell(x, y, n + 1);
					}
				}
			}
		}
	}
	
	//Find Column, row and zone with with single possibility for a number
	public void alg2 ()
	{
		//Scan each number
		for (int i = 0; i < 9; i++){
			//Scan each column, row, zone
			for (int j = 0; j < 9; j++) {
				int cCount = 0;
				int cCell = 0;
				int rCount = 0;
				int rCell = 0;
				int zCount = 0;
				int zCell = 0;
				
				//Scan each cell in column, row, zone
				for (int n = 0; n < 9; n++) {
					if (board.getColumn(j).getCell(n).getValue() == 0 &&
						board.getColumn(j).getCell(n).getPossibilities()[i]) {
						
						cCount++;
						cCell = n;
					}
					
					if (board.getRow(j).getCell(n).getValue() == 0 &&
						board.getRow(j).getCell(n).getPossibilities()[i]) {
						
						rCount++;
						rCell = n;
					}
					
					if (board.getZone(j).getCell(n).getValue() == 0 &&
						board.getZone(j).getCell(n).getPossibilities()[i]) {
						
						zCount++;
						zCell = n;
					}
				}
				
				if (cCount == 1) {
					setCell(j, cCell, i+1);
				}
				
				if (rCount == 1) {
					setCell(rCell, j, i+1);
				}
				
				if (zCount == 1) {
					setCell((j%3)*3+(zCell%3),(j/3)*3+(zCell/3),i+1);
				}
			}
		}
		
	}
	
	//Find twin or triplets in possibilities of a zone, row or column
	public void alg3 ()
	{
		
	}
	
	public void alg4 ()
	{
		
	}
	
	public void autoPlayBasic ()
	{
		changeFlag = false;
		do{
			changeFlag = false;
			do{
				changeFlag = false;
				do{
					changeFlag = false;			
					do{
						changeFlag = false;
						alg1();
					}while (changeFlag);				
					alg2();
				}while(changeFlag);			
				alg3();
			}while (changeFlag);
		}while(changeFlag);
	}
	
	public void autoPlayAdv ()
	{
		long startTime = System.currentTimeMillis();
		autoPlayBasic();
		recursiveAutoPlayStarter(20);
		long endTime = System.currentTimeMillis();
		System.out.println(new Long(endTime - startTime).toString() + "ms");
	}
	
	public Boolean isPossible ()
	{
		isGamePossible = isGamePossible();
		
		if (gui != null && !isGamePossible){
			gui.gameImpossiblePopUp();
		}	
		
		return isGamePossible;
	}
	
	public Boolean isGameWon ()
	{
		Boolean output = true;
		
		for (int y = 0; y < 9; y++)
		{
			for (int x = 0; x < 9; x++)
			{
				if (board.getCell(x, y).getValue() == 0)
				{
					output = false;
					x = 9;
					y = 9;
				}
			}
		}
		
		if (gui != null && output){
			gui.gameWinnedPopUp();
		}
		
		return output;
	}
	
	public Board getBoard()
	{
		return board;
	}
	
	public void setBoard (Board b)
	{
		board = b;
		
		if (gui != null){
			for (int y = 0; y < 9; y++){
				for (int x = 0; x < 9; x++){
					Cell c = board.getCell(x, y);
					if (c.getValue() == 0){
						gui.setPossibilities(x, y, c.getPossibilities());
					}
					else{
						gui.setNumber(x, y, c.getValue());
					}
				}
			}
		}
	}
	
	public void setCell(int x, int y, int value)
	{
		if (x < 0 || x > 8 || y < 0 || y > 8 || value < 0 || value > 9)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			try
			{
				if (board.getCell(x, y).getValue() == 0)
				{
					board.getCell(x, y).setValue(value);
					if (gui != null){
						gui.setNumber(x, y, value);
					}
					removePossColumn(x, y, value);
					removePossRow(x, y, value);
					removePossZone(x, y, value);
					changeFlag = true;
					isGamePossible = isGamePossible();
					
				}
				else
				{
					deleteCell(x, y);;
					setCell(x, y, value);
				}
				
			}
			catch (IllegalArgumentException e)
			{
				//Do nothing
			}
		}		
	}
	
	public void deleteCell (int x, int y) {
		Cell dCell = board.getCell(x, y);
		if (dCell.getValue() != 0)
		{
			Zone z = dCell.getZone();
			Column c = dCell.getColumn();
			Row r = dCell.getRow();
			dCell.setValue(0);
			for (int i = 0; i < 9; i++) {
				if (z.getCell(i).getValue() == 0) {
					reCalculatePossibilities(z.getCell(i));
				}
				if (c.getCell(i).getValue() == 0) {
					reCalculatePossibilities(c.getCell(i));
				}
				if (r.getCell(i).getValue() == 0) {
					reCalculatePossibilities(r.getCell(i));
				}
			}
		}
	}
	
	public void resetGame ()
	{
		for (int y = 0; y < 9; y++)
		{
			for (int x = 0; x < 9; x++)
			{
				board.getCell(x, y).setPossibilities(new Boolean[] {true, true, true, true, true, true, true, true, true});
				if (gui != null){
					gui.setPossibilities(x, y, new Boolean[] {true, true, true, true, true, true, true, true, true});
				}
			}
		}
		
		changeFlag = false;
		isGamePossible = true;
	}
	
	//*****************************
	//****   Private methods   ****
	//*****************************
	
	private void recursiveAutoPlayStarter (int depth)
	{
		if (!isGameWon() && isGamePossible){
			Player[] newP = getVariant(this);
			int successPlayer = -1;
			Boolean dontKnowDetected = false;

			for (int i = 0; i < newP.length; i++){
				try{
					Player result = recursiveAutoPlay(newP[i],depth);
					if (result != null){
						successPlayer = i;
						newP[successPlayer] = result;
					}
				}
				catch (MaxGameDepthReachedException e)
				{
					dontKnowDetected = true;
				}
			}

			if (successPlayer != -1){
				for (int y = 0; y < 9; y++){
					for (int x = 0; x < 9; x++){
						if (board.getCell(x, y).getValue() == 0){
							Board successB = newP[successPlayer].getBoard();
							setCell(x,y,successB.getCell(x, y).getValue());
						}
					}
				}
			}
			else if (!dontKnowDetected){
				isGamePossible = false;
			}
			else {
				//Dont know, do nothing
				if (gui != null){
					gui.autoNoResultPopUp();
				}
			}
		}
	}
	
	//Variable depth recursive method
	private Player recursiveAutoPlay (Player p, int depth) throws MaxGameDepthReachedException
	{
		Board b = p.getBoard();
		Player output = null;
		p.autoPlayBasic();
		if (p.isGameWon())
		{
			output = p;
		}
		else if (!p.isPossible())
		{
			//Do nothing, output will be null
		}
		else if (depth > 1)
		{
			Player[] newP = getVariant(p);
			int successBoard = -1;
			Boolean dontKnowDetected = false;
			
			for (int i = 0; i < newP.length; i++){
				try{
					Player result = recursiveAutoPlay(newP[i],depth - 1);
					if (result != null){
						successBoard = i;
						newP[successBoard] = result;
					}
				}
				catch (MaxGameDepthReachedException e)
				{
					dontKnowDetected = true;
				}
			}
			
			if (successBoard != -1){
				output = newP[successBoard];
			}
			else if (!dontKnowDetected){
				output = null;
			}
			else {
				throw new MaxGameDepthReachedException();
			}
		}
		else if (depth == 1) //Base Case
		{
			throw new MaxGameDepthReachedException();
		}
		return output;
	}
	
	private Player[] getVariant (Player p)
	{
		Board b = p.getBoard();
		Player[] output;
		int lowest = 9;
		Cell lowCell = null;
		
		//Find cell with lowest number off possibilities
		for (int y = 0; y < 9; y++){
			for (int x = 0; x < 9; x++){
				if (b.getCell(x, y).getValue() == 0){
					Boolean[] poss = b.getCell(x, y).getPossibilities();
					int sum = 0;

					for (int i = 0; i < 9; i++){
						if (poss[i]){
							sum++;
						}
					}

					if (sum < lowest){
						lowest = sum;
						lowCell = b.getCell(x, y);
					}
				}
			}
		}
		
		output = new Player[lowest];
		int count = 0;
		
		//Create new player for each possibilities of the minimal cell
		for (int i = 0; i < 9; i++){
			if (lowCell.getPossibilities()[i]){
				Board newBorn = b.clone();
				Player newP = new Player();
				newP.setBoard(newBorn);
				newP.setCell(lowCell.getX(), lowCell.getY(), i+1);
				output[count] = newP;
				count++;
			}
		}
		
		return output;
	}
	
	private void setPossibilities (Boolean[] poss, Cell c)
	{
		c.setPossibilities(poss);
		if (gui != null){
			gui.setPossibilities(c.getX(), c.getY(), poss);
		}
		changeFlag = true;
		isGamePossible = isGamePossible();
	}
	
	private void reCalculatePossibilities (Cell c)
	{
		Boolean[] poss = new Boolean[]{true, true, true, true, true, true, true, true, true};
		
		for (int i = 0; i < 9; i++) {
			if (c.getRow().getCell(i).getValue() != 0) {
				poss[c.getRow().getCell(i).getValue() - 1] = false;
			}
			
			if (c.getColumn().getCell(i).getValue() != 0) {
				poss[c.getColumn().getCell(i).getValue() - 1] = false;
			}
			
			if (c.getZone().getCell(i).getValue() != 0) {
				poss[c.getZone().getCell(i).getValue() - 1] = false;
			}
		}
		
		c.setPossibilities(poss);
		if (gui != null){
			gui.setPossibilities(c.getX(), c.getY(), poss);
		}
	}
	
	private Boolean isGamePossible ()
	{
		Boolean output = true;
		
		for (int y = 0; y < 9; y++)
		{
			for (int x = 0; x < 9; x++)
			{
				if (board.getCell(x, y).getValue() == 0
					&&Arrays.equals(board.getCell(x, y).getPossibilities(),new Boolean[]{false, false, false, false, false, false, false, false, false}))
				{
					output = false;
					x = 9;
					y = 9;
				}
					
			}
		}
		
		return output;
	}
	
	private void removePossColumn (int col, int row, int value)
	{
		Column c = board.getColumn(col);
		
		for (int i = 0; i < 9; i++)
		{
			if (c.getCell(i).getValue() == 0 && c.getCell(i).getPossibilities()[value - 1])
			{
				Boolean [] poss = c.getCell(i).getPossibilities();
				poss[value - 1] = false;
				if (gui != null){
					gui.setPossibilities(col, i, poss);		
				}
			}
		}
	}
	
	private void removePossRow (int col, int row, int value)
	{
		Row r = board.getRow(row);
		
		for (int i = 0; i < 9; i++)
		{
			if (r.getCell(i).getValue() == 0 && r.getCell(i).getPossibilities()[value - 1])
			{
				Boolean[] poss = r.getCell(i).getPossibilities();
				poss[value - 1] = false;
				if (gui != null){
					gui.setPossibilities(i, row, poss);
				}
			}
		}
	}
	
	private void removePossZone (int col, int row, int value)
	{
		int zoneNum = 3*(row/3)+col/3;
		Zone z = board.getZone(zoneNum);
		
		for (int i = 0; i < 9 ; i++)
		{
			if (z.getCell(i).getValue() == 0 && z.getCell(i).getPossibilities()[value - 1])
			{
				Boolean[] poss = z.getCell(i).getPossibilities();
				poss[value - 1] = false;
				if (gui != null){
					gui.setPossibilities((zoneNum%3)*3 + i%3, (zoneNum/3)*3+i/3, poss);
				}
			}
		}
	}
}
