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
	
	private BigZ(){}
	
	/**
	* Конструктор, с помощью которого можно ввести большое рациональное число
	* @version 1.0
	* @author Аюпов Ренат
	*/
	public BigQ(BigZ p, BigZ q)
	{
		this.p = p;
		this.q = q;
	}
	
	/**
	* Проверка знача большого рациональное числа
	* @version 1
	* @author Сычев Александр
	*/
	public checkPositive()
	{
		return !(p.checkPositive() ^ q.checkPositive());
	}
}