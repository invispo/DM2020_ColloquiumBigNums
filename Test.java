import eltech.DM2020.BigNum.*;

public class Test
{
	public static void main(String[] args)
	{
		BigZ a = new BigZ("1000");
		BigZ b = new BigZ("-1");
		System.out.println(a.add(b).toString());
	}
}