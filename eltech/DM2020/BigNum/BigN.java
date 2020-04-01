package eltech.DM2020.BigNum;

import java.util.*;

	/**
	* Класс, который позволяет манипулировать с большими натуральными числами + {0}
	* @version 0.03
	* @author Сычев Александр, Яловега Никита
	*/
public class BigN
{
	/*Само число хранится в value - это список. В 0ой ячейке младший разряд, в 1 больше и т.д.
	Например, число 36004256360, в 0ой - 360, в 1ой - 256, во 2ой - 4, в 3ей - 36*/
	private ArrayList<Integer> value = new ArrayList<Integer>();

	private BigN(){}

	/**
	* Конструктор, с помощью которого можно ввести большое целое число
	* Если строка src пустая, то в value будет 0 элементов
	*
	* @param String src - представление большого натурального числа в виде строки
	*
	* @version 1.1
	* @author Сычев Александр
	*/
	public BigN(String src)
	{
		int n, i;
		n = src.length();
		if(n % Constants.digits == 1)
		{
			src = "00" + src;
			n+=2;
		}
		if(n % Constants.digits == 2)
		{
			src = "0" + src;
			n++;
		}
		for (i = 0; i < n-3 && Integer.valueOf(src.substring(i, i+3)) == 0; i+=3);
		src = src.substring(i, n);
		n = src.length();
		for(i = 0; i <= n-3; i+=3)
			value.add(Integer.valueOf(src.substring(i, i+3)));
		Collections.reverse(value);
	}

	/**
	* Сложение 2-x больших целых чисел. Вернёт при сложении НОВОЕ большое целое число
	*
    * @param BigN other - число, которое прибавляется к исходному
    * @return BigN result - новое число, получающееся в результате сложения
	*
	* @version 1
	* @author Сычев Александр
	*/
	public BigN add(BigN other)
	{
		BigN buffBigN = new BigN();
		int over, n1, n2, i, j, buff1, buff2, maxCell, n;
		maxCell = 1;
		for(i = 0; i < Constants.digits; i++)
			maxCell *= 10;
		n1 = this.value.size();
		n2 = other.value.size();
		n = Math.max(n1, n2);
		for(i = 0, j = 0, over = 0; i < n || j < n; over = (buff1 + buff2+over)/maxCell, i++, j++)
		{
			if (i < n1)
				buff1 = value.get(i);
			else
				buff1 = 0;
			if(j < n2)
				buff2 = other.value.get(j);
			else
				buff2 = 0;
			buffBigN.value.add((buff1 + buff2 + over) % maxCell);
		}
		if(over != 0)
			buffBigN.value.add(over);
		return buffBigN;
	}

    /**
    *  Разность двух больших натуральных чисел O(this.value.size())
    *
    *  @param BigN other - число, которое вычитаем из исходного
    *  @return BigN result - новое число, получающееся в результате вычитания
    *
    *  @version 0.1
    *  @author Яловега Никита
    */
    public BigN subtract(BigN other)
    {
        int base = 1000;
        int i, j, carry, cur;
        BigN result = this;

        if (this.isMoreOrEquals(other))
        {
            carry = 0;
            for (i = 0; i < other.value.size() || carry != 0; ++i)
            {
                cur = result.value.get(i) - (carry + (i < other.value.size() ? other.value.get(i) : 0));
                carry = cur < 0 ? 1 : 0;
                if (carry == 1)
                    result.value.set(i, cur+base);
                else
                    result.value.set(i, cur);
            }

            for (i = result.value.size()-1; result.value.get(i) == 0 && i > 0; --i)
                result.value.remove(i);
        }
        else
            throw new ArithmeticException("Вычитание невозможно в натуральных числах");
        return result;
    }

	/**
	* Вывод большого целого числа в виде строки
	* Если в value нуль элементов, то вернёт пустую строку
	*
    * @return Представление числа в виде строки
	*
	* @version 1
	* @author Сычев Александр
	*/
	@Override
	public String toString()
	{
		int i;
		Collections.reverse(value);
		StringBuilder builder = new StringBuilder();
		for(i = 0; i < value.size(); i++)
			if(i != 0)
				builder.append( value.get(i)>=100?value.get(i).toString():(value.get(i)>=10?"0"+value.get(i).toString():"00"+value.get(i).toString()) );
			else
				builder.append(value.get(i).toString());
		Collections.reverse(value);
		return builder.toString();
	}

    /**
     * Умножение двух больших натуральных чисел. O(this.value.size()*other.value.size())
     *
     * @param BigN other - число, на которое нужно умножить исходное
     * @return BigN result - новое число, получающееся в результате умножения
     *
     * @version 0.3333
     * @author Яловега Никита
     */
     public BigN multiply(BigN other)
     {
         int base = 1000;
         BigN result = new BigN();
         int i, j, carry, cur;

         for (i = 0; i < this.value.size() + other.value.size(); ++i)
             result.value.add(0);

        for (i = 0; i < this.value.size(); ++i)
             for (j = 0, carry = 0; j < other.value.size() || carry != 0; ++j)
             {
                 cur = result.value.get(i+j) + this.value.get(i) * (j < other.value.size() ? other.value.get(j) : 0) + carry;
                 result.value.set(i+j, cur % base);
                 carry = cur / base;
             }
			 
        for (i = result.value.size()-1; result.value.get(i) == 0 && i > 0; --i)
     	    result.value.remove(i);
        return result;
 	}

    /**
    * Сравнение двух больших натуральных чисел.
    *
    * @param BigN other - второе число для сравнения с исходным
    * @return int - 0 если равны, -1 если меньше other, 1 если больше other
    *
    * @version 1
    * @author Яловега Никита
    */
    public int compareTo(BigN other)
    {
        int i;
        if (this.value.size() > other.value.size())
            return 1;
        else if (this.value.size() < other.value.size())
            return -1;

        for(i = 0; i < this.value.size(); ++i)
            if (this.value.get(i) > other.value.get(i))
                return 1;
            else if (this.value.get(i) < other.value.get(i))
                return -1;

        return 0;
    }


    /**
    * @param BigN other
    * @return boolean - true если this больше other, иначе false
    *
    * @version 1
    * @author Яловега Никита
    */
    private boolean isMoreThan(BigN other) {
        return this.compareTo(other) > 0;
    }

    /**
    * @param BigN other
    * @return boolean - true если this меньше other, иначе false
    *
    * @version 1
    * @author Яловега Никита
    */
    private boolean isLessThan(BigN other) {
        return this.compareTo(other) < 0;
    }

    /**
    * @param BigN other
    * @return boolean - true если this больше или равен other, иначе false
    *
    * @version 1
    * @author Яловега Никита
    */
    private boolean isMoreOrEquals(BigN other) {
        return this.compareTo(other) >= 0;
    }

    /**
    * @param BigN other
    * @return boolean - true если this меньше или равен other, иначе false
    *
    * @version 1
    * @author Яловега Никита
    */
    private boolean isLessOrEquals(BigN other) {
        return this.compareTo(other) <= 0;
    }

    /**
    * @param BigN other
    * @return boolean - true если this равен other, иначе false
    *
    * @version 1
    * @author Яловега Никита
    */
    private boolean isEquals(BigN other) {
        return this.compareTo(other) == 0;
    }


    /**
    * Проверка большого числа на 0.
    *
    * @param BigN num - число для проверки
    * @return boolean - результат проверки
    *
    * @version 1
    * @author Яловега Никита
    */
    public boolean isZero()
    {
        return this.toString().equals("0");
    }
 }
