package eltech.DM2020.BigNum;

import java.util.*;

/**
* Класс, который позволяет манипулировать с полиномами с рациональными коэффициентами
* @version 0.01
* @author Ванеев Андрей, Сычев Александр
*/
public class BigPolinom extends BigNumber
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
	* @author 
	*/
	public BigPolinom(String src)
	{
		if(src == null)
			throw new IllegalArgumentException("Неверный аргумент: строка не может быть не инициализированной\n");
		if(src.equals(""))
			throw new IllegalArgumentException("Неверный аргумент: строка не может быть пустой\n");
		src = src.trim();
		src = src.replace("*", "");
		/*Прочитай описание к BigQ.toString(), конструктор BigQ(String src) и описание выше
		...Building...*/
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
		factors.add(one);
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
		/*Прочитай описание к BigQ.toString(), конструктор BigQ(String src) и описание выше
		...Building...*/
		return "";
	}
}





