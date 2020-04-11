package eltech.DM2020.BigNum;

import java.util.*;
import java.math.*;

/**
* Класс, который содержит интерфейс
* @version 0.01
* @author Семенов Алексей, Сычев Александр
* Главный SQA - Семенов Алексей
*/
public class Colloquium
{
	private static HashMap<String, Object> nums = new HashMap<String, Object>();
	private static Scanner in = new Scanner(System.in);
	private static final String SintaxisProblem = "Неверный синтаксис комманды";
	private static final String NotBigNumInDictProblem = " нет в списке чисел";
	private static final String DifTypeProblem = " должны быть одного типа";
	
	public static void start()
	{
		InterFace();
	}
	
	private static void InterFace()
	{
		Object buffNum;
		BigN buffBigN;
		BigZ buffBigZ;
		BigQ buffBigQ;
		String buffS;
		BigPolinom buffBigPolinom;
		boolean EXIT = false;
		int i;
		String[] cm;
		while(!EXIT)
		{
			System.out.print("Input: ");
			cm = in.nextLine().split(" ");
			if(!checkLegal(cm))
				continue;
			if(cm.length == 1)
			{
				buffS = cm[0];
				cm = new String[2];
				cm[1] = buffS;
			}
			switch(cm[1].toLowerCase())
			{
				case "exit":
				{
					EXIT = true;
					break;
				}
				case "input":
				{
					input(cm);
					break;
				}
				case "output":
				{
					System.out.println(nums.get(cm[0]));
					break;
				}
				case "add":
				{
					add(cm);
					break;
				}
				case "subtract":
				{
					subtract(cm);
					break;
				}
				case "multiply":
				{
					multiply(cm);
					break;
				}
				case "divide":
				{
					divide(cm);
					break;
				}
				default:
				{
					System.out.println("Нет такой комманды: " + cm[1]);
					break;
				}
			}
		}
	}
	
	private static boolean checkLegal(String[] cm)
	{
		boolean result = true;
		if(cm[0].equals("?") || cm[0].toLowerCase().equals("help"))
		{
			System.out.println(help());
			return false;
		}
		if(cm.length < 2 )
		{
			if(!cm[0].toLowerCase().equals("exit") && !cm[0].toLowerCase().equals("list"))
			{
				System.out.println(SintaxisProblem);
				return false;
			}
			else
				return true;
		}
		if(cm[1].toLowerCase().equals("input"))
		{
			if( cm.length == 4 && cm[2].toLowerCase().equals("as") && ( cm[3].equals("BigZ") || cm[3].equals("BigN") || cm[3].equals("BigQ") || cm[3].equals("BigPolinom") ) )
			{
				return true;
			}
			else
			{
				System.out.println(SintaxisProblem);
				return false;
			}
		}
		if(!nums.containsKey(cm[0]))
		{
			System.out.println(cm[0] + NotBigNumInDictProblem);
			result = false;
		}
		if(cm.length > 2)
		{			
			if( !nums.containsKey(cm[0]) )
			{
				System.out.println(cm[0] + NotBigNumInDictProblem);
				return false;
			}
			if( !nums.containsKey(cm[2]) )
			{
				System.out.println(cm[2] + NotBigNumInDictProblem);
				return false;
			}
			if( nums.get(cm[0]).getClass() != nums.get(cm[2]).getClass() )
			{
				System.out.println(cm[0] + ", " + cm[2] + DifTypeProblem);
				return false;
			}
		}

		
		return result;
	}
	
	private static String help()
	{
		String S = "Помощь?";
		return S;
	}
	
	private static void input(String[] cm)
	{
		String buffS;
		System.out.println("Введите число: ");
		buffS = in.nextLine();
		try
		{
			if(cm[3].equals("BigZ"))
				nums.put(cm[0], new BigZ(buffS));
			else if (cm[3].equals("BigN"))
				nums.put(cm[0], new BigN(buffS));
			else if(cm[3].equals("BigQ"))
				nums.put(cm[0], new BigQ(buffS));
			else if(cm[3].equals("BigPolinom"))
				nums.put(cm[0], new BigPolinom(buffS));
			else
				System.out.println("Error 404: Failed successfully...");
		}
		catch (Throwable t)
		{
			System.out.println(t);
		}
	}
	
	private static void add(String[] cm)
	{
		if(nums.get(cm[0]).getClass() == BigZ.class)
			nums.put(cm[4], ( ( BigZ )nums.get(cm[0])).add( (BigZ)nums.get(cm[2]) ) ) ;
		else if (nums.get(cm[0]).getClass() == BigN.class)
			nums.put(cm[4], ( ( BigN )nums.get(cm[0])).add( (BigN)nums.get(cm[2]) ) ) ;
		else if(nums.get(cm[0]).getClass() == BigQ.class)
			nums.put(cm[4], ( ( BigQ )nums.get(cm[0])).add( (BigQ)nums.get(cm[2]) ) ) ;
		//else if(nums.get(cm[0]).getClass() == BigPolinom.class)
			//nums.put(cm[4], ( ( BigPolinom )nums.get(cm[0])).add( (BigPolinom)nums.get(cm[2]) ) ) ;
		else
			System.out.println("Error 404 in add: Failed successfully...");
	}
	
	private static void subtract(String[] cm)
	{
		try 
		{
			if(nums.get(cm[0]).getClass() == BigZ.class)
				nums.put(cm[4], ( ( BigZ )nums.get(cm[0])).subtract( (BigZ)nums.get(cm[2]) ) ) ;
			else if (nums.get(cm[0]).getClass() == BigN.class)
				nums.put(cm[4], ( ( BigN )nums.get(cm[0])).subtract( (BigN)nums.get(cm[2]) ) ) ;
			else if(nums.get(cm[0]).getClass() == BigQ.class)
				nums.put(cm[4], ( ( BigQ )nums.get(cm[0])).subtract( (BigQ)nums.get(cm[2]) ) ) ;
			//else if(nums.get(cm[0]).getClass() == BigPolinom.class)
				//nums.put(cm[4], ( ( BigPolinom )nums.get(cm[0])).subtract( (BigPolinom)nums.get(cm[2]) ) ) ;
			else
				System.out.println("Error 404 in subtract: Failed successfully...");
		}
		catch (Throwable t)
		{
			System.out.println(t);
		}
	}

	private static void multiply(String[] cm)
	{
		if(nums.get(cm[0]).getClass() == BigZ.class)
			nums.put(cm[4], ( ( BigZ )nums.get(cm[0])).multiply( (BigZ)nums.get(cm[2]) ) ) ;
		else if (nums.get(cm[0]).getClass() == BigN.class)
			nums.put(cm[4], ( ( BigN )nums.get(cm[0])).multiply( (BigN)nums.get(cm[2]) ) ) ;
		else if(nums.get(cm[0]).getClass() == BigQ.class)
			nums.put(cm[4], ( ( BigQ )nums.get(cm[0])).multiply( (BigQ)nums.get(cm[2]) ) ) ;
		//else if(nums.get(cm[0]).getClass() == BigPolinom.class)
			//nums.put(cm[4], ( ( BigPolinom )nums.get(cm[0])).multiply( (BigPolinom)nums.get(cm[2]) ) ) ;
		else
			System.out.println("Error 404 in multiply: Failed successfully...");
	}

	private static void divide(String[] cm)
	{
		try 
		{
			if(nums.get(cm[0]).getClass() == BigZ.class)
				nums.put(cm[4], ( ( BigZ )nums.get(cm[0])).divide( (BigZ)nums.get(cm[2]) ) ) ;
			else if (nums.get(cm[0]).getClass() == BigN.class)
				nums.put(cm[4], ( ( BigN )nums.get(cm[0])).divide( (BigN)nums.get(cm[2]) ) ) ;
			else if(nums.get(cm[0]).getClass() == BigQ.class)
				nums.put(cm[4], ( ( BigQ )nums.get(cm[0])).divide( (BigQ)nums.get(cm[2]) ) ) ;
			//else if(nums.get(cm[0]).getClass() == BigPolinom.class)
				//nums.put(cm[4], ( ( BigPolinom )nums.get(cm[0])).divide( (BigPolinom)nums.get(cm[2]) ) ) ;
			else
				System.out.println("Error 404 in divide: Failed successfully...");
		}
		catch (Throwable t)
		{
			System.out.println(t);
		}
	}
}







