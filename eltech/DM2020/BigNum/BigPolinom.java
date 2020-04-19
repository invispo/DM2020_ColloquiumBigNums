package eltech.DM2020.BigNum;

import java.util.*;

/**
* Класс, который позволяет манипулировать с полиномами с рациональными коэффициентами
* @version 0.01
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
	* @version 2
	* @author Семенов Алексей
	*/
	public BigPolinom(String src)
	{
		int n, thisPower, maxpower = 0, i;
		String[] str, powers, temp;
		ArrayList<String> arrStr = new ArrayList<String>();
		if(src == null)
			throw new IllegalArgumentException("Неверный аргумент: строка не может быть не инициализированной\n");
		if(src.equals(""))
			throw new IllegalArgumentException("Неверный аргумент: строка не может быть пустой\n");
		src = src.trim();
		if(src.charAt(src.length()-1) == 'x')
			src += " ";
		src = src.replace("*", "");
		src = src.replace(")", "");
		src = src.replace("(", "");
		src = src.replace("-", "+-");
		src = src.replace("++", "+");
		src = src.replace("/+", "/");
		str = src.split("[+]");
		for(n = 0; n < str.length; n++)
		{
			if(!str[n].trim().equals(""))
			{
				if(str[n].trim().equals("x"))
					str[n] = "1x";
				powers = str[n].split("x");
				if(powers[0].trim().equals("-") || powers[0].trim().equals(""))
					powers[0] += "1";
				if(powers.length == 1)
				{
					if(str[n].indexOf("x") != -1)
						arrStr.add(powers[0] + "x");
					else
						arrStr.add(powers[0]);
				}
				else if(powers[0].trim().equals("") && powers[1].trim().equals(""))
					arrStr.add("1x");
				else if(powers.length > 1)
					arrStr.add(powers[0] + "x" + powers[1].trim());
			}
		}
		for(n = 0; n < arrStr.size(); n++)
		{
			thisPower = 0;
			if(arrStr.get(n).indexOf("x^") != -1)
			{
				powers = arrStr.get(n).split("x");
				if(powers.length > 1)
				{
					thisPower = Integer.parseInt(powers[1].substring(1, powers[1].trim().length()));
				}
			}
			else if(arrStr.get(n).indexOf("x") != -1)
				thisPower = 1;
			if(thisPower > maxpower) maxpower = thisPower;
		}
		for(n = 0; n < maxpower+1; n++) factors.add(null);
		
		for(n = 0; n < arrStr.size(); n++)
		{
			thisPower = 0;
			if(arrStr.get(n).indexOf("x^") != -1)
			{
				powers = arrStr.get(n).split("x");
				if(powers.length > 1)
				{
					thisPower = Integer.parseInt(powers[1].substring(1,powers[1].trim().length()));
				}
				factors.set(thisPower, new BigQ( powers[0].trim() ));
			}
			else if(arrStr.get(n).indexOf("x") != -1)
			{
				powers = arrStr.get(n).split("x");
				thisPower = 1;
				factors.set(thisPower, new BigQ(powers[0].trim()));
			}
			else
				factors.set(0, new BigQ(arrStr.get(n).trim()));
		}
		
		for(n = 0; n < maxpower+1; n++)
		{
			if(factors.get(n) == null)
				factors.set(n, new BigQ("0"));
		}
	}

	/**
	* Конструктор, с помощью которого можно инициализировать полином
	* Если рациональное число one == null, то бросит исключение
	*
	* @param BigQ one - большое рациональное число
	*
	* @version 1
	* @author
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
	* @author
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
	* @author
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
    * @author
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
    * @author
    */
    public BigZ toBigZ() throws ArithmeticException
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
    * @author
    */
    public BigQ toBigQ() throws ArithmeticException
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
    * @author
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

    /**
    * Получение старшей степени полинома
    *
    * @return int - 0 максимальная степень полинома
    *
    * @version 1
    * @author Батищев Игорь
    */
    public int getDegree()
    {
		return this.factors.size()-1;
    }

    /**
    * Получение коэффициента при старшей степени полинома
    *
    * @return BigQ - коэффициент при старшей степени полинома
    *
    * @version 1
    * @author Батищев Игорь
    */
    public BigQ getCoefAtHighestDegree()
    {
		return this.factors.get(this.getDegree()).clone();
    }

	/**
    * Производная многочлена
    *
    * @return BigPolinom result - производная
    *
    * @version 1
    * @author
    */
    public BigPolinom derivative()
    {
		int i, n;
		BigPolinom result = new BigPolinom();
		n = this.factors.size();
		if(n == 1)
		{
			result.factors.add(new BigQ("0"));
			return result;
		}
		for (i = 1; i < n; i++)
			result.factors.add( this.factors.get(i).multiply( new BigQ( Integer.valueOf(i).toString() ) ) );
		return result;
    }

	/**
    * Умножение многочлена на x^k
    *
    * @return BigPolinom result - полином, умноженный на x^k
    *
    * @version 1
    * @author
    */
    public BigPolinom multiplyByXpowK(int k) throws ArithmeticException
    {

		int i, n, j;
		if(k < 0)
			throw new ArithmeticException("Полином нельзя умножать на x^k, где k = " + k + " - отрицательное число\n");
		BigPolinom result = new BigPolinom();
		n = this.factors.size();
		for(i = 0; i < k; i++)
			result.factors.add( new BigQ("0") );
		for (j = 0; j < n; j++)
			result.factors.add( this.factors.get(j).clone() );
		return result;
    }

	/**
    * Клонирование объекта
	*
    * @return копию BigPolinom
    *
    * @version 1
    * @author
    */
	@Override
	public BigPolinom clone()
	{
		int i, n;
		BigPolinom result = new BigPolinom();
		n = this.factors.size();
		for(i = 0; i < n; i++)
			result.factors.add( this.factors.get(i).clone() );
		return result;
	}

	/**
    * Сложение полиномов
	*
	* @param BigPolinom other - второй полином, который прибавляется
	*
    * @return BigPolinom result - результат сложения
    *
    * @version 3
    * @author Яловега Никита, Ванеев Андрей, Семенов Алексей
    */
	public BigPolinom add(BigPolinom other)
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
            result.factors.set(i, temp1.add(temp2));
        }

        for (i = result.factors.size()-1; i >= 0 && result.factors.get(i).isZero(); i--)
            result.factors.remove(i);

        return result;
	}

	/**
    * Вычитания полиномов
	*
	* @param BigPolinom other - второй полином, который вычитания
	*
    * @return BigPolinom result - результат вычитания
    *
    * @version 1
    * @author Яловега Никита
    */
	public BigPolinom subtract(BigPolinom other)
	{
        return this.add(other.multiplyByK(new BigQ("-1")));
	}

	/**
    * Умножение полиномов
	*
	* @param BigPolinom other - второй полином, на который умножается исходный
	*
    * @return BigPolinom result - результат вычитания
    *
    * @version 1
    * @author Яловега Никита
    */
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

	/**
    * Метод, который необходим для методов divide и mod
	*
	* @param BigPolinom other - второй полином, на который делится исходный
	*
    * @return BigPolinom result - результат деления
    *
    * @version 1
    * @author Яловега Никита
    */
	private Case divideUniversal(BigPolinom other)
	{
        int i,j;
        BigPolinom q = new BigPolinom();
        BigPolinom temp_pol = new BigPolinom();
        BigPolinom r = this.clone();
        BigQ temp = new BigQ("0/1");
        BigQ zero = new BigQ("0/1");

        if (this.factors.size() >= other.factors.size())
        {

            for(i = 0; i <= this.factors.size()-other.factors.size(); ++i)
                q.factors.add(zero);

        	while (r.factors.size() > 0 && r.factors.size() >= other.factors.size())
            {
                temp = (r.factors.get(r.factors.size()-1)).divide(other.factors.get(other.factors.size()-1));

                for(i = 0; i <= r.factors.size()-other.factors.size(); ++i)
                    temp_pol.factors.add(zero);

                temp_pol.factors.set(r.factors.size()-other.factors.size(), temp);

                q = q.add(temp_pol);
        		    r = r.subtract(other.multiply(temp_pol));
                temp_pol.factors.clear();
        	}

        }
    	return new Case(q, r);
	}

	/**
    * Деление полиномов с остатком
	*
	* @param BigPolinom other - второй полином, на который делится исходный
	*
    * @return BigPolinom result - результат деления
    *
    * @version 1
    * @author Яловега Никита
    */
	public BigPolinom divide(BigPolinom other)
	{
		return this.divideUniversal(other).getFirst();
	}

	/**
    * Вычисление остатка при делении полиномов
	*
	* @param BigPolinom other - второй полином, на который делится исходный
	*
    * @return BigPolinom result - остаток от деления
    *
    * @version 1
    * @author Яловега Никита
    */
	public BigPolinom mod(BigPolinom other)
	{
		return this.divideUniversal(other).getSecond();
	}

	/**
    * Умножение полинома на рациональное число
	*
	* @param BigQ x - число, на которое умножается исходный полином
	*
    * @return BigPolinom result - результат умножения полинома на число
    *
    * @version 2
    * @author Ванеев Андрей
    */
	public BigPolinom multiplyByK(BigQ x)
	{
		int i, n;
		BigPolinom result = new BigPolinom();
		n = this.factors.size();
		for (i = 0; i < n; i++)
			result.factors.add(this.factors.get(i).multiply(x) );
		return result;
	}

	/**
    * Класс, который необходим для метода divideUniversal
	*
    * @version 1
    * @author Яловега Никита
    */
	private class Case
	{
		private BigPolinom first;
		private BigPolinom second;

		public Case(BigPolinom first, BigPolinom second)
		{
			this.first = first;
			this.second = second;
		}

		public BigPolinom getFirst()
		{
			return first;
		}

		public BigPolinom getSecond()
		{
			return second;
		}
	}

	public String gcdAndLcm()
	{
		int i;
		String resultString = "";
		BigQ temp = new BigQ("0/1");
		BigQ temp2 = new BigQ("0/1");
		BigPolinom result = this.clone();
		for (i = 0; i < factors.size(); i++)
		{
			if(temp.isZero() && !this.factors.get(i).isZero())
			{
				temp.getP().setNumber(this.factors.get(i).getP().getNumber());
				temp.getQ().setNumber(this.factors.get(i).getQ().getNumber());
			}
			else
			{
				temp.getP().setNumber(temp.getP().getNumber().gcd(this.factors.get(i).getP().getNumber()));
				temp.getQ().setNumber(temp.getQ().getNumber().lcm(this.factors.get(i).getQ().getNumber()));
			}
		}
		for (i = 0; i < factors.size(); i++)
		{
			temp2 = this.factors.get(i).divide(temp);
			result.factors.set(i,temp2.reduce());
		}
		resultString = temp.toString() + "(" + result.toString() + ")";
		return resultString;
	}
	
	/**
    * НОД многочленов
	*
	* @param BigPolinom other - второй многочлен, для поиска НОД с первым
	*
	* @return BigPolinom result - НОД многочленов
	*
    * @version 1
    * @author 
    */
	public BigPolinom gcd(BigPolinom other)
	{
		BigPolinom buffThis = this.clone();
        BigPolinom buffOther = other.clone();
		BigPolinom result = buffOther;
		String resString;
		if(buffThis.compareTo(buffOther) < 0)
		{
			result = buffThis.clone();
			buffThis = buffOther;
			buffOther = result;
		}
		while (buffOther.factors.size() != 0)
        {
            result = buffOther.clone();
			buffOther = buffThis.mod(buffOther);
			buffThis = result;
        }
		resString = result.gcdAndLcm();
		result = new BigPolinom( resString.substring(resString.indexOf("("), resString.length()) );
		return result;
	}
	
	/**
    * Метод перевода кратных корней в простые
	*
    * @version 1
    * @author 
    */
	public BigPolinom rootsToSimple()
	{
		BigPolinom result = new BigPolinom();
		BigQ zero = new BigQ("0/1");
		int minPower = this.factors.size(), n;
		for(n = 0; n < minPower; n++)
		{
			if(!this.factors.get(n).isZero())
				minPower = n;
		}
		if(minPower > 1)
		{
			for(n = 0; n < this.factors.size()-minPower+1; n++)
				result.factors.add(zero);
			for(n = minPower; n < this.factors.size(); n++)
				result.factors.set(n-minPower+1, this.factors.get(n));
		}
		else
			result = this.clone();
		return result;
	}
}
