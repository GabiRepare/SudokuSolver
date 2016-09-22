package Game;

public class Row 
{
	private Cell[] cell;
	
	public Row (Cell[] cell)
	{
		this.cell = cell;
	}
	
	public Row()
	{
		//Do nothing
	}
	
	public void setCell (Cell[] cell)
	{
		this.cell = cell;
	}
	
	public Cell getCell(int col)
	{
		return cell[col];
	}
	
	public String toString()
	{
		String output = "";
		if (cell[0] != null)
		{
			String nl = System.getProperty("line.separator");
			output += nl + "_____________" + nl;
			output += "|" + new Integer(cell[0].getValue()) + new Integer(cell[1].getValue()) + new Integer(cell[2].getValue());
			output += "|" + new Integer(cell[3].getValue()) + new Integer(cell[4].getValue()) + new Integer(cell[5].getValue());
			output += "|" + new Integer(cell[6].getValue()) + new Integer(cell[7].getValue()) + new Integer(cell[8].getValue()) +"|" + nl;
			output += "¯¯¯¯¯¯¯¯¯¯¯¯¯";
			output = output.replaceAll("0", " ");
		}
		else
		{
			output += "null";
		}
		return output;
	}
	
}
