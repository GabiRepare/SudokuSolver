package Game;

public class Board 
{
	private Zone[] zone;
	private Column[] column;
	private Row[] row;
	private Cell[][] cell;
	
	public Board ()
	{
		zone = new Zone[9];
		column = new Column[9];
		row = new Row[9];
		cell = new Cell[9][9];
		
		//create zones, rows and columns
		for (int i = 0; i < 9; i++)
		{
			zone[i] = new Zone();
			column[i] = new Column();
			row[i] = new Row();
		}
		
		//create cells
		for (int x = 0; x < 9; x++)
		{
			for (int y = 0; y < 9; y++)
			{
				int currentZone = 3*(y/3)+x/3;
				cell[x][y] = new Cell(x, y, zone[currentZone], column[x], row[y]);
			}
		}
		
		//setup zones, rows and columns
		for (int i = 0; i < 9; i++)
		{
			int zoneX = i%3;
			int zoneY = i/3;
			zone[i].setCell(new Cell[]{cell[zoneX*3][zoneY*3],   cell[zoneX*3+1][zoneY*3],   cell[zoneX*3+2][zoneY*3],
									   cell[zoneX*3][zoneY*3+1], cell[zoneX*3+1][zoneY*3+1], cell[zoneX*3+2][zoneY*3+1],
									   cell[zoneX*3][zoneY*3+2], cell[zoneX*3+1][zoneY*3+2], cell[zoneX*3+2][zoneY*3+2]});
			column[i].setCell(cell[i]);
			row[i].setCell(new Cell[]{cell[0][i], cell[1][i], cell[2][i],
									  cell[3][i], cell[4][i], cell[5][i],
									  cell[6][i], cell[7][i], cell[8][i]});
		}
	}
	
	public Column getColumn(int n)
	{
		return column[n];
	}
	
	public Row getRow(int n)
	{
		return row[n];
	}
	
	public Zone getZone(int n)
	{
		return zone[n];
	}
	
	public Cell getCell(int x, int y)
	{
		return cell[x][y];
	}
	
	public Board clone()
	{
		Board output = new Board();
		for(int y = 0; y < 9; y++){
			for (int x = 0; x < 9; x++){
				if (cell[x][y].getValue() == 0){
					output.getCell(x, y).setPossibilities(cell[x][y].getPossibilities().clone());
				}
				else{
					output.getCell(x, y).setValue(cell[x][y].getValue());
				}
			}
		}
		return output;
	}
	
	public String toString()
	{
		String output = "";
		
		if (cell[0][0] != null)
		{
			String nl = System.getProperty("line.separator");
			
			output += " ___________" + nl;
			
			for (int y = 0; y < 9; y++)
			{
				
				for (int x = 0; x < 9; x++)
				{
					if (x%3 == 0)
					{
						output += "|";
					}
					output += cell[x][y].getValue();
				}
				output += "|" + nl;
				
				if ((y+1)%3 == 0 && y != 8)
				{
					output += " -----------" + nl;
				}
			}
			output += " ¯¯¯¯¯¯¯¯¯¯¯";
			output = output.replaceAll("0"," ");
		}
		else
		{
			output += "null";
		}
		
		return output;
	}
}
