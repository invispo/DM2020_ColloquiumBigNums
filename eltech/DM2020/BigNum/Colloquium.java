package eltech.DM2020.BigNum;

import java.util.*;
import java.math.*;
import java.lang.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;

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
		System.out.println("\tЕсли вы запускаете программу впервые, то советуем ввести комманду \"help\" или \"?\"");
		InterFace();
	}
	
	/**
	* Интерфес
	*
	* @version 0.00000000000000000000000000000000000000000000000000000052681922
	*
	* @author не важно
	*/
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
			System.out.print("> ");
			cm = in.nextLine().split(" ");
			try
			{
			
				cm = format(cm);
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
					case "q":{}
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
					case "+":{}
					case "add":
					{
						add(cm);
						break;
					}
					case "-":{}
					case "subtract":
					{
						subtract(cm);
						break;
					}
					case "*":{}
					case "multiply":
					{
						multiply(cm);
						break;
					}
					case "/":{}
					case "divide":
					{
						divide(cm);
						break;
					}
					case "%":{}
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
					case "abs":
					{
						abs(cm);
						break;
					}
					case "ispositive":{}
					case "checkpositive":
					{
						checkPositive(cm);
						break;
					}
					case "getcoefathighestdegree":
					{
						getCoefAtHighestDegree(cm);
						break;
					}
					case "derivative":
					{
						derivative(cm);
						break;
					}
					case "multiplybyxpowk":
					{
						multiplyByXpowK(cm);
						break;
					}
					case "getdegree":
					{
						if(nums.get(cm[0]).getClass() == BigPolinom.class)
							System.out.println( ((BigPolinom)nums.get(cm[0])).getDegree() );
						else
							System.out.println(cm[1] + " только для полиномов");
						break;
					}
					case "gcdandlcm":
					{
						if(nums.get(cm[0]).getClass() == BigPolinom.class)
							System.out.println( ((BigPolinom)nums.get(cm[0])).gcdAndLcm() );
						else
							System.out.println(cm[1] + " только для полиномов");
						break;
					}
					case "rootstosimple":
					{
						rootsToSimple(cm);
						break;
					}
					default:
					{
						System.out.println("Нет такой комманды: " + cm[1]);
						break;
					}
				}
			}
			catch(Throwable t)
			{
				System.out.println(SintaxisProblem);
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
			help();
			return false;
		}
		if(cm.length < 2 )
		{
			if(cm[0].toLowerCase().equals("exit") || cm[0].toLowerCase().equals("list") || cm[0].toLowerCase().equals("ls") || cm[0].toLowerCase().equals("quit") || cm[0].toLowerCase().equals("q"))
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
		if( cm[1].toLowerCase().equals("lcm") || cm[1].toLowerCase().equals("multiplyby10x") || cm[1].toLowerCase().equals("subtructbyk") || cm[1].toLowerCase().equals("dividebyotherten"))
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
		if(cm[1].toLowerCase().equals("multiplybyxpowk")) // a multiplyByXpowK [число] to c
		{
			if(nums.get(cm[0]).getClass() == BigPolinom.class)
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
				System.out.println("Комманда " + cm[1] + " только для полиномов (BigPolinom)");
				return false;
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

	private static String[] format(String[] cm)
	{
		String buffS;
		String[] result;
		if(cm.length == 1)
			result = cm;
		else if(cm[1].toLowerCase().equals("abs") || cm[1].toLowerCase().equals("getcoefathighestdegree") || cm[1].toLowerCase().equals("derivative") || cm[1].toLowerCase().equals("rootstosimple")) // a abs to c ---> a abs a to c
		{
			if(cm.length != 4)
			{
				cm[1] = "None";
				System.out.println(SintaxisProblem);
				return cm;
			}
			result = new String[5];
			result[0] = cm[0]; result[1] = cm[1]; result[2] = cm[0]; result[3] = cm[2]; result[4] = cm[3];
		}
		else
			result = cm;
		return result;
	}
	
	private static void help()
	{
		String line;
		try (BufferedReader inFile = new BufferedReader(new InputStreamReader( new FileInputStream("ReadMe.txt"), "UTF-8")))
		{
			while ( (line = inFile.readLine()) != null )
				System.out.println(line);
		}
		catch(Throwable t)
		{
			System.out.println(t);
		}
	}
	
	private static void input(String[] cm)
	{
		String buffS;
		System.out.println("Вводите: ");
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
	
	private static void checkPositive(String[] cm)
	{
		boolean buff = false;
		if(nums.get(cm[0]).getClass() == BigZ.class)
			System.out.println( (( BigZ )nums.get(cm[0])).checkPositive() );
		else if (nums.get(cm[0]).getClass() == BigN.class)
			System.out.println( true );
		else if(nums.get(cm[0]).getClass() == BigQ.class)
			System.out.println( (( BigQ )nums.get(cm[0])).checkPositive() );
		else if(nums.get(cm[0]).getClass() == BigPolinom.class)
			System.out.println("Для полиномов такое не делается");
		else
			System.out.println("Error 404 in checkPositive: Failed successfully...");
	}
	
	private static void abs(String[] cm) // a abs a to c
	{
		try 
		{
			if(nums.get(cm[0]).getClass() == BigZ.class)
				nums.put(cm[4], ( ( BigZ )nums.get(cm[0])).abs() ) ;
			else if (nums.get(cm[0]).getClass() == BigN.class)
				nums.put(cm[4], ( ( BigN )nums.get(cm[0])).clone() ) ;
			else if(nums.get(cm[0]).getClass() == BigQ.class)
				nums.put(cm[4], ( ( BigQ )nums.get(cm[0])).abs() ) ;
			else if(nums.get(cm[0]).getClass() == BigPolinom.class)
				System.out.println("Для полиномов такое не делается");
			else
				System.out.println("Error 404 in abs: Failed successfully...");
		}
		catch (Throwable t)
		{
			System.out.println(t);
		}
	}
	
	private static void derivative(String[] cm) // a derivative a to c
	{
		try 
		{
			if(nums.get(cm[0]).getClass() == BigPolinom.class)
				nums.put(cm[4], ( ( BigPolinom )nums.get(cm[0])).derivative() ) ;
			else
				System.out.println(cm[1] + " только для полиномов");
		}
		catch (Throwable t)
		{
			System.out.println(t);
		}
	}
	
	private static void rootsToSimple(String[] cm) // a rootsToSimple a to c
	{
		try 
		{
			if(nums.get(cm[0]).getClass() == BigPolinom.class)
				nums.put(cm[4], ( ( BigPolinom )nums.get(cm[0])).rootsToSimple() ) ;
			else
				System.out.println(cm[1] + " только для полиномов");
		}
		catch (Throwable t)
		{
			System.out.println(t);
		}
	}
	
	private static void getCoefAtHighestDegree(String[] cm) // a getCoefAtHighestDegree a to c
	{
		try 
		{
			if(nums.get(cm[0]).getClass() == BigPolinom.class)
				nums.put(cm[4], ( ( BigPolinom )nums.get(cm[0])).getCoefAtHighestDegree() ) ;
			else
				System.out.println(cm[1] + " только для полиномов");
		}
		catch (Throwable t)
		{
			System.out.println(t);
		}
	}
	
	private static void gcd(String[] cm)
	{
		try 
		{
			if (nums.get(cm[0]).getClass() == BigN.class)
				nums.put(cm[4], ( ( BigN )nums.get(cm[0])).gcd( (BigN)nums.get(cm[2]) ) ) ;
			else if(nums.get(cm[0]).getClass() == BigPolinom.class)
				nums.put(cm[4], ( ( BigPolinom )nums.get(cm[0])).gcd( (BigPolinom)nums.get(cm[2]) ) ) ;
			else if(nums.get(cm[0]).getClass() == BigZ.class)
				nums.put(cm[4], ( ( BigZ )nums.get(cm[0])).gcd( (BigZ)nums.get(cm[2]) ) ) ;
			else if (nums.get(cm[0]).getClass() == BigQ.class)
				System.out.println("Не определено для рациональных чисел");
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
	
	private static void multiplyByXpowK(String[] cm)
	{
		try 
		{
			if (nums.get(cm[0]).getClass() == BigPolinom.class)
				nums.put(cm[4], ( ( BigPolinom )nums.get(cm[0])).multiplyByXpowK( Integer.valueOf(cm[2]) ) ) ;
			else
				System.out.println("Error 404 in multiplyByXpowK: Failed successfully...");
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







