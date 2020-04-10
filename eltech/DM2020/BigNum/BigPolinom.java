package eltech.DM2020.BigNum;

import java.util.*;

/**
* Класс, который позволяет манипулировать с полиномами с рациональными коэффициентами
* @version 0.01
* @author Ванеев Андрей, Сычев Александр
*/
public class BigPolinom
{
		/*Само число хранится в value - это список. В 0ой ячейке младший разряд, в 1 больше и т.д.
	Например, число 36004256360, в 0ой - 360, в 1ой - 256, во 2ой - 4, в 3ей - 36*/
	private ArrayList<BigQ> value = new ArrayList<BigQ>();
	
	private BigPolinom() {}
}