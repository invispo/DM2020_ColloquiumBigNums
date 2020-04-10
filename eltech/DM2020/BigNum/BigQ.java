package eltech.DM2020.BigNum;

import java.util.*;

/**
* Класс, который позволяет манипулировать с большими рациональными числами
* @version 0.01
* @author Аюпов Ренат
*/
public class BigQ
{
	private BigZ p; // Числитель
	private BigZ q; // Знаменатель
	
	private BigQ(){}
	
	/**
	* Конструктор, с помощью которого можно ввести большое рациональное число
	* Если p и q  не инициализированны, то бросит исключение
	*
	* @param BigZ p, q - целые числа: p - числитель, q - знаменатель
	*
	* @version 1.0
	* @author Аюпов Ренат
	*/
	public BigQ(BigZ p, BigZ q) throws IllegalArgumentException, ArithmeticException
	{
		if(p == null || q == null)
			throw new IllegalArgumentException("Неверный аргумент: числа должны быть инициализированны\n");
		this.p = p;
		this.q = q;
		if( q.equals( new BigZ("0") ) )
			throw new ArithmeticException("В знаменателе не может быть нуля\n");
	}
		
	/**
	* Конструктор, с помощью которого можно ввести большое рациональное число
	* Если строка src пустая или null, то бросит исключение
	*
	* @param String src - строка, представляющая большое рациональное число. Её вид должен быть такой: "[числитель]/[знаменатель]". Например: -2357982579/-5617929
	*
	* @version 1.0
	* @author Сычев Александр
	*/
	public BigQ(String src) throws IllegalArgumentException, ArithmeticException
	{
		int SlashIndex;
		if(src == null)
			throw new IllegalArgumentException("Неверный аргумент: строка не может быть не инициализированной\n");
		if(src.equals(""))
			throw new IllegalArgumentException("Неверный аргумент: строка не может быть пустой\n");
		src = src.trim();
		SlashIndex = src.indexOf("/");
		if (SlashIndex == -1)
			throw new IllegalArgumentException("Неверный аргумент: нет знака дроби\n");
		this.p = new BigZ( src.substring(0, SlashIndex) );
		this.q = new BigZ( src.substring(SlashIndex+1, src.length()) );
		if( q.equals( new BigZ("0") ) )
			throw new ArithmeticException("В знаменателе не может быть нуля\n");
	}
	
	/**
	* Вывод большого рационального числа в виде строки
	*
    * @return String - представление числа в виде строки
	*
	* @version 1
	* @author Сычев Александр
	*/
	@Override
	public String toString()
	{
		return this.p.toString() + "/" + q.toString();
	}
	
	/**
	* Проверка знака большого рациональное числа
	*
    * @return boolean - знак рационального числа
	*
	* @version 1
	* @author Сычев Александр
	*/
	public boolean checkPositive()
	{
		return !(p.checkPositive() ^ q.checkPositive());
	}
}