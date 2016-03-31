package inputHandling;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import javax.activation.UnsupportedDataTypeException;

import fieldInfo.*; 

public class InputService 
{
	private ArrayList<Field> fieldList;
	
	public InputService()
	{
		fieldList = new ArrayList<Field>();
	}

	public ArrayList<Field> getFieldList() 
	{
		return fieldList;
	}
	
	public void addField(Field f)
	{
		if(f != null)
			this.fieldList.add(f);
	}
	
	public void createFields()
	{
		Scanner kb = new Scanner(System.in);
		System.out.println("Enter Fields:");
		String input = "";
		
		do 
		{
			try 
			{
				int rows, columns;
				rows = kb.nextInt();
				columns = kb.nextInt();
				if(rows == 0 && columns == 0)
					return;
				Field tempField = new Field(rows, columns);
				
				String currentLine = "";
				kb.nextLine();
				for(int x = 0; x < rows; x++)
				{
					currentLine = kb.nextLine();
					currentLine = currentLine.replaceAll("\\s+","");
					
					if(currentLine.length() != columns)
						throw new IllegalArgumentException();
					
					for(int i = 0; i < columns; i++)
					{
						char ch = currentLine.charAt(i);
						
						if(ch != '.' && ch != '*')
							throw new UnsupportedDataTypeException();
						tempField.setSet(x, i, currentLine.charAt(i));
					}
				}
				fieldList.add(tempField);
			} 
			catch (InputMismatchException e) 
			{
				System.out.println("Error: Input Mismatch. Exiting now. ErrorCode:  " + e.getStackTrace());
				kb.close();
				return;
			}
			catch (IllegalArgumentException e) 
			{
				System.out.println("Error: Illegal row or column length. Exiting now. ErrorCode:  " + e.getStackTrace());
				kb.close();
				return;
			}
			catch (UnsupportedDataTypeException e) {
				System.out.println("Error: Illegal argument in row. Exiting now. ErrorCode:  " + e.getStackTrace());
				kb.close();
				return;
			
			
			}
			
		} while (!(input.contains("0")));
		
		kb.close();
	}

	
	public void printFieldList()
	{
		Iterator<Field> fieldIterator = fieldList.iterator();
		int x = 1;
		while(fieldIterator.hasNext())
		{
			System.out.println("Field #" + x + ":");
			Field tempField = fieldIterator.next();
			tempField.convert();
			tempField.printSet();
			System.out.println();
			x++;
		}
	}
	
}
