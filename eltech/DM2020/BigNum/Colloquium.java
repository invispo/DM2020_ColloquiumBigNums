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
	private static final String SintaxisProblem = "Неверный синтаксис комманды (попробуйте ввести help)";
	private static final String NotBigNumInDictProblem = " нет в списке чисел (попробуйте использовать input as)";
	private static final String DifTypeProblem = " должны быть одного типа (попробуйте использовать toBig[N, Z, Q, Polinom])";
	
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
				case "quit":{}
				case "exit":
				{
					EXIT = true;
					break;
				}
				case "in":{}
				case "input":
				{
					input(cm);
					break;
				}
				case "out":{}
				case "print":{}
				case "output":
				{
					System.out.println(nums.get(cm[0]));
					break;
				}
				case "ls":{}
				case "list":
				{
					list();
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
				case "mod":
				{
					mod(cm);
					break;
				}
				case "compare":{}
				case "cmp":{}
				case "compareto":
				{
					compareTo(cm);
					break;
				}
				case "tobign":
				{
					toBigN(cm);
					break;
				}
				case "tobigz":
				{
					toBigZ(cm);
					break;
				}
				case "tobigq":
				{
					toBigQ(cm);
					break;
				}
				case "tobigpolinom":
				{
					toBigPolinom(cm);
					break;
				}
				case "iszero":
				{
					isZero(cm);
					break;
				}
				case "gcd":
				{
					gcd(cm);
					break;
				}
				case "lcm":
				{
					lcm(cm);
					break;
				}
				case "multiplyby10x":
				{
					multiplyBy10x(cm);
					break;
				}
				case "subtructbyk":
				{
					subtructByK(cm);
					break;
				}
				case "dividebyotherten":
				{
					divideByOtherTen(cm);
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
	
/*
За эту черту не надо заступать. Там не на что смотреть
========================================================================================================================================================================================*/
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
			if(cm[0].toLowerCase().equals("exit") || cm[0].toLowerCase().equals("list") || cm[0].toLowerCase().equals("ls") || cm[0].toLowerCase().equals("quit"))
			{
				return true;
			}
			else
			{
				System.out.println(SintaxisProblem);
				return false;
			}
		}
		if(cm[1].toLowerCase().equals("input") || cm[1].toLowerCase().equals("in"))
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
		if(cm[1].toLowerCase().equals("tobign") || cm[1].toLowerCase().equals("tobigz") || cm[1].toLowerCase().equals("tobigq") || cm[1].toLowerCase().equals("tobigpolinom"))
		{
			if(cm.length != 4)
			{
				System.out.println(SintaxisProblem);
				return false;
			}
			else
				return true;
		}
		if( cm[1].toLowerCase().equals("gcd") || cm[1].toLowerCase().equals("lcm") || cm[1].toLowerCase().equals("multiplyby10x") || cm[1].toLowerCase().equals("subtructbyk") || cm[1].toLowerCase().equals("dividebyotherten"))
		{
			if(cm[1].toLowerCase().equals("multiplyby10x")) // a multiplyBy10x [число] to c
			{
				if(nums.get(cm[0]).getClass() == BigN.class)
				{
					if(cm.length == 5)
						return true;
					else
					{
						System.out.println(SintaxisProblem);
						return false;
					}
				}
				else
				{
					System.out.println("Комманда " + cm[1] + " только для натуральных чисел + {0} (BigN)");
					return false;
				}
			}
			if( nums.get(cm[0]).getClass() != BigN.class || nums.get(cm[2]).getClass() != BigN.class )
			{
				result = false;
				System.out.println("Комманда " + cm[1] + " только для натуральных чисел + {0} (BigN)");
			}
			if(result && cm[1].toLowerCase().equals("subtructbyk"))
			{
				if(cm.length == 6 && nums.containsKey(cm[2]) && nums.containsKey(cm[3]) && nums.get(cm[0]).getClass() == nums.get(cm[2]).getClass() && nums.get(cm[0]).getClass() == nums.get(cm[3]).getClass()) //a subtructByK b d to c
					return true;
				else
				{
					System.out.println(SintaxisProblem);
					return false;
				}
			}
			if(result && cm[1].toLowerCase().equals("dividebyotherten"))
			{
				if(cm.length == 6 && nums.containsKey(cm[2]) && nums.get(cm[0]).getClass() == nums.get(cm[2]).getClass() ) //a divideByOtherTen b [число] to c
					return true;
				else
				{
					System.out.println(SintaxisProblem);
					return false;
				}
			}
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
		String S = "\nЧтобы ввести полином надо выполнить комманду:\na input as BigPolinom\nПосле этого можно ввести, напрмер это:\n(35255)*x^6 + 1524634x^4+ (732/-2612)x^5 +(2623/36324)x^3+(-52163/2521)x^7 + (-51268235)x^2 +132152*x + (-1513262/-15612)\nВведи ещё 1 полином\nb input as BigPolinom\nДалее можешь выполнять действия, например:\na add b to c\nили\na subtract b to c\nили\na multiply b to c\nили\na divide b to c\nили\na mod b to c\nПосле этого можно вывести результат:\nc output\n(в windows можно вставлять в консоль с помощью ПКМ или нажать в верхнем левом углу -> изменить -> вставить)\nexit = выйти\n\n";
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
		else if(nums.get(cm[0]).getClass() == BigPolinom.class)
			nums.put(cm[4], ( ( BigPolinom )nums.get(cm[0])).add( (BigPolinom)nums.get(cm[2]) ) ) ;
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
			else if(nums.get(cm[0]).getClass() == BigPolinom.class)
				nums.put(cm[4], ( ( BigPolinom )nums.get(cm[0])).subtract( (BigPolinom)nums.get(cm[2]) ) ) ;
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
		else if(nums.get(cm[0]).getClass() == BigPolinom.class)
			nums.put(cm[4], ( ( BigPolinom )nums.get(cm[0])).multiply( (BigPolinom)nums.get(cm[2]) ) ) ;
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
			else if(nums.get(cm[0]).getClass() == BigPolinom.class)
				nums.put(cm[4], ( ( BigPolinom )nums.get(cm[0])).divide( (BigPolinom)nums.get(cm[2]) ) ) ;
			else
				System.out.println("Error 404 in divide: Failed successfully...");
		}
		catch (Throwable t)
		{
			System.out.println(t);
		}
	}
	
	private static void mod(String[] cm)
	{
		try 
		{
			if(nums.get(cm[0]).getClass() == BigZ.class)
				nums.put(cm[4], ( ( BigZ )nums.get(cm[0])).mod( (BigZ)nums.get(cm[2]) ) ) ;
			else if (nums.get(cm[0]).getClass() == BigN.class)
				nums.put(cm[4], ( ( BigN )nums.get(cm[0])).mod( (BigN)nums.get(cm[2]) ) ) ;
			else if(nums.get(cm[0]).getClass() == BigQ.class)
				System.out.println("Операции взятия по модулю для BigQ нет");
			else if(nums.get(cm[0]).getClass() == BigPolinom.class)
				nums.put(cm[4], ( ( BigPolinom )nums.get(cm[0])).mod( (BigPolinom)nums.get(cm[2]) ) ) ;
			else
				System.out.println("Error 404 in mod: Failed successfully...");
		}
		catch (Throwable t)
		{
			System.out.println(t);
		}
	}
	
	private static void compareTo(String[] cm)
	{
		int buff;
		String buffS = "";
		try 
		{
			if(nums.get(cm[0]).getClass() == BigZ.class)
			{
				buff = (( BigZ )nums.get(cm[0])).compareTo( (BigZ)nums.get(cm[2]) );
				buffS += cm[0];
				if(buff == 0)
					buffS += " равно ";
				else if(buff >= 1)
					buffS += " больше, чем ";
				else if(buff <= -1)
					buffS += " меньше, чем ";
				buffS += cm[2];
			}
			else if (nums.get(cm[0]).getClass() == BigN.class)
			{
				buff = (( BigN )nums.get(cm[0])).compareTo( (BigN)nums.get(cm[2]) );
				buffS += cm[0];
				if(buff == 0)
					buffS += " равно ";
				else if(buff >= 1)
					buffS += " больше, чем ";
				else if(buff <= -1)
					buffS += " меньше, чем ";
				buffS += cm[2];
			}
			else if(nums.get(cm[0]).getClass() == BigQ.class)
			{
				buff = (( BigQ )nums.get(cm[0])).compareTo( (BigQ)nums.get(cm[2]) );
				buffS += cm[0];
				if(buff == 0)
					buffS += " равно ";
				else if(buff >= 1)
					buffS += " больше, чем ";
				else if(buff <= -1)
					buffS += " меньше, чем ";
				buffS += cm[2];
			}
			else if(nums.get(cm[0]).getClass() == BigPolinom.class)
			{
				buff = (( BigPolinom )nums.get(cm[0])).compareTo( (BigPolinom)nums.get(cm[2]) );
				buffS += "Степень полинома " + cm[0];
				if(buff == 0)
					buffS += " равна степени полинома ";
				else if(buff >= 1)
					buffS += " больше, чем степень полинома ";
				else if(buff <= -1)
					buffS += " меньше, чем степень полинома ";
				buffS += cm[2];
			}
			else
				System.out.println("Error 404 in compareTo: Failed successfully...");
			System.out.println(buffS);
		}
		catch (Throwable t)
		{
			System.out.println(t);
		}
	}

	private static void toBigN(String[] cm)
	{
		try
		{
			if(nums.get(cm[0]).getClass() == BigZ.class)
				nums.put(cm[3], ( ( BigZ )nums.get(cm[0])).toBigN() ) ;
			else if (nums.get(cm[0]).getClass() == BigN.class)
				System.out.println("Число " + cm[0] + " уже BigN");
			else if(nums.get(cm[0]).getClass() == BigQ.class)
				nums.put(cm[3], ( ( BigQ )nums.get(cm[0])).toBigN() ) ;
			else if(nums.get(cm[0]).getClass() == BigPolinom.class)
				nums.put(cm[3], ( ( BigPolinom )nums.get(cm[0])).toBigN() ) ;
			else
				System.out.println("Error 404 in toBigN: Failed successfully...");
		}
		catch (Throwable t)
		{
			System.out.println(t);
		}
	}
	
	private static void toBigZ(String[] cm)
	{
		try 
		{
			if(nums.get(cm[0]).getClass() == BigZ.class)
				System.out.println("Число " + cm[0] + " уже BigZ");
			else if (nums.get(cm[0]).getClass() == BigN.class)
				nums.put(cm[3], ( ( BigN )nums.get(cm[0])).toBigZ() ) ;
			else if(nums.get(cm[0]).getClass() == BigQ.class)
				nums.put(cm[3], ( ( BigQ )nums.get(cm[0])).toBigZ() ) ;
			else if(nums.get(cm[0]).getClass() == BigPolinom.class)
				nums.put(cm[3], ( ( BigPolinom )nums.get(cm[0])).toBigZ() ) ;
			else
				System.out.println("Error 404 in toBigZ: Failed successfully...");
		}
		catch (Throwable t)
		{
			System.out.println(t);
		}
	}
	
	private static void toBigQ(String[] cm)
	{
		try 
		{
			if(nums.get(cm[0]).getClass() == BigZ.class)
				nums.put(cm[3], ( ( BigZ )nums.get(cm[0])).toBigQ() ) ;
			else if (nums.get(cm[0]).getClass() == BigN.class)
				nums.put(cm[3], ( ( BigN )nums.get(cm[0])).toBigQ() ) ;
			else if(nums.get(cm[0]).getClass() == BigQ.class)
				System.out.println("Число " + cm[0] + " уже BigQ");
			else if(nums.get(cm[0]).getClass() == BigPolinom.class)
				nums.put(cm[3], ( ( BigPolinom )nums.get(cm[0])).toBigQ() ) ;
			else
				System.out.println("Error 404 in toBigQ: Failed successfully...");
		}
		catch (Throwable t)
		{
			System.out.println(t);
		}
	}
	
	private static void toBigPolinom(String[] cm)
	{
		try 
		{
			if(nums.get(cm[0]).getClass() == BigZ.class)
				nums.put(cm[3], ( ( BigZ )nums.get(cm[0])).toBigPolinom() ) ;
			else if (nums.get(cm[0]).getClass() == BigN.class)
				nums.put(cm[3], ( ( BigN )nums.get(cm[0])).toBigPolinom() ) ;
			else if(nums.get(cm[0]).getClass() == BigQ.class)
				nums.put(cm[3], ( ( BigQ )nums.get(cm[0])).toBigPolinom() ) ;
			else if(nums.get(cm[0]).getClass() == BigPolinom.class)
				System.out.println("Многочлен " + cm[0] + " уже BigPolinom");
			else
				System.out.println("Error 404 in toBigPolinom: Failed successfully...");
		}
		catch (Throwable t)
		{
			System.out.println(t);
		}
	}
	
	private static void isZero(String[] cm)
	{
		boolean buff = false;
		if(nums.get(cm[0]).getClass() == BigZ.class)
			buff = (( BigZ )nums.get(cm[0])).isZero();
		else if (nums.get(cm[0]).getClass() == BigN.class)
			buff = (( BigN )nums.get(cm[0])).isZero();
		else if(nums.get(cm[0]).getClass() == BigQ.class)
			buff = (( BigQ )nums.get(cm[0])).isZero();
		else if(nums.get(cm[0]).getClass() == BigPolinom.class)
			buff = (( BigPolinom )nums.get(cm[0])).isZero();
		else
			System.out.println("Error 404 in isZero: Failed successfully...");
		System.out.println( buff );
	}
	
	private static void gcd(String[] cm)
	{
		try 
		{
			if (nums.get(cm[0]).getClass() == BigN.class)
				nums.put(cm[4], ( ( BigN )nums.get(cm[0])).gcd( (BigN)nums.get(cm[2]) ) ) ;
			else
				System.out.println("Error 404 in gcd: Failed successfully...");
		}
		catch (Throwable t)
		{
			System.out.println(t);
		}
	}
	
	private static void lcm(String[] cm)
	{
		try 
		{
			if (nums.get(cm[0]).getClass() == BigN.class)
				nums.put(cm[4], ( ( BigN )nums.get(cm[0])).lcm( (BigN)nums.get(cm[2]) ) ) ;
			else
				System.out.println("Error 404 in lcm: Failed successfully...");
		}
		catch (Throwable t)
		{
			System.out.println(t);
		}
	}
	
	private static void multiplyBy10x(String[] cm)
	{
		try 
		{
			if (nums.get(cm[0]).getClass() == BigN.class)
				nums.put(cm[4], ( ( BigN )nums.get(cm[0])).multiplyBy10x( Integer.valueOf(cm[2]) ) ) ;
			else
				System.out.println("Error 404 in multiplyBy10x: Failed successfully...");
		}
		catch (Throwable t)
		{
			System.out.println(t);
		}
	}
	
	private static void subtructByK(String[] cm) //a subtructByK b d to c
	{
		try 
		{
			if (nums.get(cm[0]).getClass() == BigN.class)
				nums.put(cm[5], ( ( BigN )nums.get(cm[0])).subtructByK( (BigN)nums.get(cm[2]), (BigN)nums.get(cm[3]) ) ) ;
			else
				System.out.println("Error 404 in subtructByK: Failed successfully...");
		}
		catch (Throwable t)
		{
			System.out.println(t);
		}
	}
	
	private static void divideByOtherTen(String[] cm) //a divideByOtherTen b [число] to c
	{
		try 
		{
			if (nums.get(cm[0]).getClass() == BigN.class)
				nums.put(cm[5], ( ( BigN )nums.get(cm[0])).divideByOtherTen( (BigN)nums.get(cm[2]), Integer.valueOf(cm[3]) ) ) ;
			else
				System.out.println("Error 404 in divideByOtherTen: Failed successfully...");
		}
		catch (Throwable t)
		{
			System.out.println(t);
		}
	}

	private static void list()
	{
		String buffS;
		String buffSClass;
		if(nums.isEmpty())
		{
			System.out.println("Введенный чисел пока что нет (используйте input)");
			return;
		}
		for( String kkey : nums.keySet() )
		{
			buffS = nums.get(kkey).toString();
			buffSClass = nums.get(kkey).getClass().toString();
			System.out.println("Число " + kkey + " - это  " + buffSClass.substring(buffSClass.lastIndexOf(".")+1, buffSClass.length()) + ": " + (buffS.length() > 80 ? buffS.substring(0, 80) + "...": buffS) );
		}
	}
}







