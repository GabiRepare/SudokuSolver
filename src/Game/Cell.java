package Game;

public class Cell 
{
	private Zone zone;
	private Column column;
	private Row row;
	private int x;
	private int y;
	private int value;
	private Boolean[] possibilities;
	
	public Cell (int x, int y, Zone zone, Column column, Row row)
	{
		this.x = x;
		this.y = y;
		this.zone = zone;
		this.column = column;
		this.row = row;
		value = 0;
		possibilities = new Boolean[]{true, true, true, true, true, true, true, true, true};
	}
	
	public Cell (int x, int y, Zone zone, Column column, Row row, int value)
	{
		this.x = x;
		this.y = y;
		this.zone = zone;
		this.column = column;
		this.row = row;
		this.value = value;
		
		if (value > 0 && value < 10)
		{
			possibilities = null;
		}
		else if (value == 0)
		{
			possibilities = new Boolean[]{true, true, true, true, true, true, true, true, true};
		}
		else
		{
			throw new IllegalArgumentException(new Integer(value).toString() +" is not a valid value for a cell");
		}
	}
	
	public Cell (Zone zone, Column column, Row row, Boolean[] poss)
	{
		this.zone = zone;
		this.column = column;
		this.row = row;
		if (poss != null && poss.length == 9)
		{
			value = 0;
			possibilities = poss;
		}
		else
		{
			throw new IllegalArgumentException("Wrong possibility array");
		}
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public Boolean[] getPossibilities()
	{
		return possibilities;
	}
	
	public Zone getZone()
	{
		return zone;
	}
	
	public Row getRow()
	{
		return row;
	}
	
	public Column getColumn()
	{
		return column;
	}
	
	public void setValue(int value)
	{
		this.value = value;
			
		if (value > 0 && value < 10)
		{	
			if (possibilities[value - 1])
			{
				possibilities = null;
			}
			else
			{
				throw new IllegalArgumentException("Value not in the possibilities of the cell");
			}
		}
		else if (value == 0)
		{
			possibilities = new Boolean[]{true, true, true, true, true, true, true, true, true};
		}
	}
	
	public void setPossibilities(Boolean[] poss)
	{
		this.value = 0;
		possibilities = poss;
	}

	
	public String toString ()
	{
		String output = "(";
		output += x;
		output += ", ";
		output += y;
		output += ") Value: ";
		if (value == 0)
		{
			output = output + "empty Possibilities: ";
			
			for (int i = 0; i < 8; i++)
			{
				if (possibilities[i])
				{
					output = output + new Integer(i+1) + ", ";
				}
			}
			
			if (possibilities[8])
			{
				output = output + 9;
			}
		}
		else
		{
			output = output + value;
		}
		
		return output;
	}
}
