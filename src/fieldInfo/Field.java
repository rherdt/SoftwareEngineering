package fieldInfo;

public class Field
{
	private char[][] set;

	public Field(int rows, int columns)
	{
		if (rows <= 0 || rows > 100 || columns <= 0 || columns > 100)
			throw new IllegalArgumentException("Rows or Columns out of Bounds");

		set = new char[rows][columns];
	}

	public char[][] getSet()
	{
		return set;
	}

	public void setSet(int row, int column, char val)
	{
		this.set[row][column] = val;
	}

	public void printSet()
	{
		for (int x = 0; x < set.length; x++)
		{
			for (int i = 0; i < set[0].length; i++)
			{
				System.out.print(set[x][i]);
			}
			System.out.println();
		}
	}

	public void convert()
	{
		int mineCount;

		char[][] bufferArray = new char[set.length + 2][set[0].length + 2];

		for (int i = 0; i < bufferArray[0].length; i++)
		{
			bufferArray[0][i] = '.';
			bufferArray[bufferArray.length - 1][i] = '.';
		}

		for (int t = 1; t < bufferArray.length - 1; t++)
		{
			bufferArray[t][0] = '.';
			bufferArray[t][bufferArray.length - 1] = '.';
			for (int i = 1; i < bufferArray[0].length - 1; i++)
			{
				bufferArray[t][i] = set[t - 1][i - 1];
			}
		}

		for (int x = 0; x < set.length; x++)
		{

			for (int i = 0; i < set[0].length; i++)
			{
				mineCount = 0;

				if (set[x][i] == '*')
				{

				} 
				else
				{
					if (this.isMine(x - 1, i - 1))
						mineCount++;
					if (this.isMine(x - 1, i))
						mineCount++;
					if (this.isMine(x - 1, i + 1))
						mineCount++;
					if (this.isMine(x, i - 1))
						mineCount++;
					if (this.isMine(x, i + 1))
						mineCount++;
					if (this.isMine(x + 1, i - 1))
						mineCount++;
					if (this.isMine(x + 1, i))
						mineCount++;
					if (this.isMine(x + 1, i + 1))
						mineCount++;

					this.setSet(x, i, (char) (mineCount + 48));

				}

			}
		}
	}

	private boolean isMine(int row, int column)
	{

		try
		{
			if (set[row][column] == '*')
				return true;
			else
			{
				return false;
			}

		} catch (IndexOutOfBoundsException e)
		{
			return false;
		}
	}

}
