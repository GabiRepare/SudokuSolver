package Game;

public class Column 
{
	private Cell[] cell;
	
	public Column (Cell[] cell)
	{
		this.cell = cell;
	}
	
	public Column ()
	{
		//Do nothing
	}
	
	public void setCell (Cell[] cell)
	{
		this.cell = cell;
	}
	
	public Cell getCell (int row)
	{
		return cell[row];
	}
	
	public String toString()
	{
		String output = "";
		if (cell[0] != null)
		{
			String nl = System.getProperty("line.separator");
			output += nl + " _" + nl;
			output += "|" + cell[0].getValue() + "|" + nl;
			output += "|" + cell[1].getValue() + "|" + nl;
			output += "|" + cell[2].getValue() + "|" + nl;
			output += "|-|" + nl;
			output += "|" + cell[3].getValue() + "|" + nl;
			output += "|" + cell[4].getValue() + "|" + nl;
			output += "|" + cell[5].getValue() + "|" + nl;
			output += "|-|" + nl;
			output += "|" + cell[6].getValue() + "|" + nl;
			output += "|" + cell[7].getValue() + "|" + nl;
			output += "|" + cell[8].getValue() + "|" + nl;
			output += " ¯";
			output = output.replaceAll("0", " ");
		}
		else
		{
			output += "null";
		}
		
		return output;
	}
}
