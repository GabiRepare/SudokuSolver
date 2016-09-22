package Game;

public class Zone 
{
	private Cell[] cell;
	
	public Zone (Cell[] cell)
	{
		this.cell = cell;
	}
	
	public Zone ()
	{
		//Do nothing
	}
	
	public void setCell (Cell[] cell)
	{
		this.cell = cell;
	}
	
	public Cell getCell(int n)
	{
		return cell[n];
	}
	
	public String toString()
	{
		String output = "";
		if (cell[0] != null)
		{
			String nl = System.getProperty("line.separator");
			output += nl + "_____" + nl;
			output += "|" + cell[0].getValue() + cell[1].getValue() + cell[2].getValue() + "|" + nl;
			output += "|" + cell[3].getValue() + cell[4].getValue() + cell[5].getValue() + "|" + nl;
			output += "|" + cell[6].getValue() + cell[7].getValue() + cell[8].getValue() + "|" + nl;
			output += "¯¯¯¯¯";
			output = output.replaceAll("0", " ");
		}
		else
		{
			output += "null";
		}
		return output;
	}
}
