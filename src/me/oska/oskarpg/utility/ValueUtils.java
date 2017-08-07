package me.oska.oskarpg.utility;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class ValueUtils 
{
	public static double round(double value, int places) 
	{
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	public static boolean chance(int chance)
	{
		Random rnd = new Random();
		int chnace2 = rnd.nextInt(100);
		if (chnace2 > chance)
		{
			return false;
		}
		return true;
	}
}
