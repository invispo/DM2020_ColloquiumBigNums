package eltech.DM2020.BigNum;

import java.util.*;

/**
* Класс, который позволяет манипулировать с полиномами с рациональными коэффициентами
* @version 0.01
* @author Ванеев Андрей, Сычев Александр
*/
public class BigPolinom
{
	/*Сами коэффициенты в полиноме - это рациональные числа и они хранятся в factors - это список. В 0ой ячейке младший коэффициент при x^0, в 1 - при x^1 и т. д.
	Например: (-21521/261)x^3 + x^2 + 21121x + (16125/126), в 0ой - 16125/126, в 1ой - 21121, во 2ой - 1, в 3ей - -21521/261*/
	private ArrayList<BigQ> factors = new ArrayList<BigQ>();

	private BigPolinom() {}

	/**
	* Конструктор, с помощью которого можно инициализировать полином
	* Если строка src пустая или null, то бросит исключение
	* Может принять строку такого вида:
	* (35255)*x^6 + 1524634x^4+ (732/-2612)x^5 +(2623/36324)x^3+(-52163/2521)x^7 + (-51268235)x^2 +132152*x^1 + (-1513262/-15612), тут 132152*x^1 может также выглядить как 132152*x
	*
	* @param String src - представление полинома в виде строки
	*
	* @version 1
	* @author Семенов Алексей
	*/
	public BigPolinom(String src)
	{
		int n, thisPower, maxpower = 0;
		String substr;
		String[] str, powers;
		if(src == null)
			throw new IllegalArgumentException("Неверный аргумент: строка не может быть не инициализированной\n");
		if(src.equals(""))
			throw new IllegalArgumentException("Неверный аргумент: строка не может быть пустой\n");
		src = src.trim();
		src = src.replace("*", "");
		src = src.replace(")", "");
		src = src.replace("(", "");
		str = src.split("[+]");
		for(n = 0; n < str.length; n++)
		{
			thisPower = 0;
			if(str[n].indexOf("x^") != -1)
			{
				powers = str[n].split("x");
				if(powers.length > 1)
					thisPower = Integer.parseInt(powers[1].substring(1,powers[1].length()).trim());
			}
			else if(str[n].indexOf("x") != -1)
				thisPower = 1;
			if(thisPower > maxpower) maxpower = thisPower;
			//System.out.println(thisPower);
		}
		for(n = 0; n < maxpower+1; n++) factors.add(null);

		//System.out.println(maxpower);
		for(n = 0; n < str.length; n++)
		{
			thisPower = 0;
			if(str[n].indexOf("x^") != -1)
			{
				powers = str[n].split("x");
				if(powers.length > 1)
					thisPower = Integer.parseInt(powers[1].substring(1,powers[1].length()).trim());
				factors.set(thisPower, new BigQ(powers[0].trim()));
			}
			else if(str[n].indexOf("x") != -1)
			{
				powers = str[n].split("x");
				thisPower = 1;
				factors.set(thisPower, new BigQ(powers[0].trim()));
			}
			else
				factors.set(0, new BigQ(str[n].trim()));
		}

		for(n = 0; n < maxpower+1; n++)
		{
			if(factors.get(n) == null)
				factors.set(n, new BigQ("0"));
		}

		/*for(n = 0; n < maxpower+1; n++)			//Вывод степеней от 0 до maxpower
			System.out.println(factors.get(n));*/
	}

	/**
	* Конструктор, с помощью которого можно инициализировать полином
	* Если рациональное число one == null, то бросит исключение
	*
	* @param BigQ one - большое рациональное число
	*
	* @version 1
	* @author Сычев Александр
	*/
	public BigPolinom(BigQ one) throws IllegalArgumentException
	{
		if(one == null)
			throw new IllegalArgumentException("Неверный аргумент: большое рациональное число должно быть инициализированно\n");
		factors.add(one.clone());
	}

	/**
	* Вывод полинома в виде строки
	* Выводиться должно так:
	* (15123/2152)x^4 + (-1512/623)x^3 + 152623x^2 + (-21512)x + 12516
	*
    * @return String - представление полинома в виде строки
	*
	* @version 1
	* @author Сычев Александр
	*/
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		int i, n;
		boolean NotZero = false;
		String buffS;
		Collections.reverse(factors);
		n = factors.size();
		for(i = 0; i < n; i++)
		{
			if(!factors.get(i).isZero())
			{
				NotZero = true;
				buffS = factors.get(i).toString();
				if(!factors.get(i).checkPositive() || buffS.indexOf("/") != -1)
					buffS = "(" + buffS + ")";
				if(n-i-1 == 0) //n-i-1 == 0
					builder.append(buffS + " + ");
				else if(n-i-1 == 1) //n-i-1 == 1
					builder.append(buffS + "x" + " + ");
				else
					builder.append(buffS + "x^" + (n-i-1) + " + ");
			}
		}
		if(NotZero == false)
			return "0";
		Collections.reverse(factors);
		buffS = builder.toString();
		return buffS.substring(0, buffS.length() - 3);
	}

	/**
	* Проверяет является ли многочлен нулём
	*
    * @return boolean - true, если числитель многочлен имеет степень 0 и коэффициент при нулевой степени равен нулю; иначе false
	*
	* @version 1
	* @author Сычев Александр
	*/
	public boolean isZero()
	{
		return factors.size() == 1 && factors.get(0).isZero();
	}

	/**
    * Конвертация в BigN
	* Если BigPolinom степени больше нуля или знаменатель коэффициента при нулевой степени не равен единице, или коэффициент при нулевой степени отрицательный, то бросит исключение
    *
    * @return BigN result - натуральное число
    *
    * @version 1
    * @author Сычев Александр
    */
    public BigN toBigN() throws ArithmeticException
    {
		if(factors.size() > 1)
			throw new ArithmeticException("Нельзя перевести полином степени большей 0 в натуральное + {0}\n");
		return this.factors.get(0).toBigN();
    }

	/**
    * Конвертация в BigZ
	* Если BigPolinom степени больше нуля или знаменатель коэффициента при нулевой степени не равен единице, то бросит исключение
    *
    * @return BigZ result - целое число
    *
    * @version 1
    * @author Сычев Александр
    */
    public BigZ toBigZ()
    {
		if(factors.size() > 1)
			throw new ArithmeticException("Нельзя перевести полином степени большей 0 в целое число\n");
		return this.factors.get(0).toBigZ();
    }

	/**
    * Конвертация в BigQ
	* Если BigPolinom степени больше нуля, то бросит исключение
    *
    * @return BigQ result - рациональное число
    *
    * @version 1
    * @author Сычев Александр
    */
    public BigQ toBigQ()
    {
		if(factors.size() > 1)
			throw new ArithmeticException("Нельзя перевести полином степени большей 0 в рациональное число\n");
		return this.factors.get(0).clone();
    }

    /**
    * Сравнение двух полиномов
    *
    * @param BigPolinom other - второй полином для сравнения с исходным
    * @return int - 0 если степени полиномов равны, -1 если степень исходного полинома меньше other, иначе 1
    *
    * @version 1
    * @author Сычев Александр
    */
    public int compareTo(BigPolinom other)
    {
		int buff = this.factors.size() - other.factors.size();
		if(buff == 0)
			return 0;
		else if (buff > 0)
			return 1;
		else
			return -1;
    }

	//Сложение двух полиномов
	public BigPolinom add(BigPolinom other)
	{
		int i;
		BigPolinom result = new BigPolinom();
		i = 0;
		for (i = 0; i < factors.size() && i < other.factors.size() ; i++)
			result.factors.set(i, this.factors.get(i).multiply(other.factors.get(i)) );
		while (i < this.factors.size())
		{
			result.factors.set(i, this.factors.get(i) );
			i++;
		}
		while (i < other.factors.size())
		{
			result.factors.set(i, other.factors.get(i) );
			i++;
		}
		return result;
	}


    public BigPolinom subtract(BigPolinom other)
	{
        int i;
        BigQ temp1 = new BigQ("0/1");
        BigQ temp2 = new BigQ("0/1");
        BigQ zero = new BigQ("0/1");
		BigPolinom result = new BigPolinom();

        for (i = 0; i < this.factors.size() || i < other.factors.size(); ++i)
            result.factors.add(zero);

        for (i = 0; i < this.factors.size() || i < other.factors.size(); i++)
        {
            temp1 = (i < this.factors.size() ? this.factors.get(i) : zero);
            temp2 = (i < other.factors.size() ? other.factors.get(i) : zero);
            result.factors.set(i, temp1.subtract(temp2));
        }

        return result;
    }

	//Умножение двух полиномов
	public BigPolinom multiply(BigPolinom other)
	{
        int i,j;
        BigPolinom result = new BigPolinom();
        BigQ zero = new BigQ("0/1");
        for(i = 0; i < this.factors.size()+other.factors.size(); ++i)
            result.factors.add(zero);

        for(i = 0; i < this.factors.size(); ++i)
            for(j = 0; j < other.factors.size(); ++j)
                result.factors.set(i+j, result.factors.get(i+j).add(this.factors.get(i).multiply(other.factors.get(j))));

        return result;
	}

	//Деление двух полиномов
	public BigPolinom divide(BigPolinom other)
	{
		return null;
	}

	//Деление по модулю двух полиномов
	public BigPolinom mod(BigPolinom other)
	{
		return null;
	}

	//Умножение полинома на число
	public BigPolinom multiplyByK(BigQ p)
	{
		int i;
		BigPolinom result = new BigPolinom();
		for (i = 0; i < factors.size(); i++)
			result.factors.set(i, this.factors.get(i).multiply(p) );
		return result;
	}

}
