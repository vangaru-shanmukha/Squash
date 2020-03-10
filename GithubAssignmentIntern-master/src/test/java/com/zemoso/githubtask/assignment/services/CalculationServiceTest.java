package com.zemoso.githubtask.assignment.services;

import com.zemoso.githubtask.assignment.services.impl.PostfixCalculationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CalculationServiceTest {

	private CalculationService calculationService;

	@Before
	public void setup() {
		calculationService = new PostfixCalculationService();
	}

	@Test
	public void testResultInt() {
		// testing the multiplication operation
		Assert.assertEquals("Check the multiplication logic", 36, calculationService.calculateInt("6*6"));
		Assert.assertEquals("Check the multiplication logic for floating numbers", 36,
				calculationService.calculateInt("6.0000*6.00000"));
		Assert.assertEquals("Check the multiplication logic for long numbers", 36,
				calculationService.calculateInt("00000000006*000000006"));
		Assert.assertEquals("Check the multiplication logic", -36, calculationService.calculateInt("6*(-6)"));

		// testing the division operation
		Assert.assertEquals("Check the division logic", -1, calculationService.calculateInt("6/0"));
		Assert.assertEquals("Check the division logic", -3, calculationService.calculateInt("6 / -2"));
		Assert.assertEquals("Check the division logic", -4, calculationService.calculateInt("-6   /   +1.30"));
		Assert.assertEquals("Check the divide by zero exception", -1, calculationService.calculateInt("6/0"));

		// testing the addition operation
		Assert.assertEquals("Check the addition logic", 6, calculationService.calculateInt("3+3"));
		Assert.assertEquals("Check the addition logic", 6, calculationService.calculateInt("+3.0+3.0"));
		Assert.assertEquals("Check the overflow condition", -1,
				calculationService.calculateInt("300000000000   +  30000"));
		Assert.assertEquals("Check the addition logic", 0,
				calculationService.calculateInt("3.00000000000   +  -3.0000"));

		// testing the subtraction operation
		Assert.assertEquals("Check the subtraction logic", 0, calculationService.calculateInt("3-3"));
		Assert.assertEquals("Check the subtraction logic", 6, calculationService.calculateInt("+3.0-(-3.0)"));
		Assert.assertEquals("Check the overflow condition", -1,
				calculationService.calculateInt("300000000000   -  30000"));
		Assert.assertEquals("Check the subtraction logic", 0,
				calculationService.calculateInt("3.00000000000   -  +3.0000"));

		// testing the invalid operand
		Assert.assertEquals("Check the unsupported operators", -1,
				calculationService.calculateInt("3.00000000000   %  +3.0000"));
		Assert.assertEquals("Check the overall logic", 100, calculationService.calculateInt("100 * ( 2 + 12 ) / 14"));

	}

	@Test
	public void testResultFloat() {

		// testing the multiplication operation
		Assert.assertEquals("Check the multiplication logic", 43.83472442626953,
				calculationService.calculateFloat("6.567*6.675"), 0f);
		Assert.assertEquals("Check the multiplication logic for numbers with trailing zeros", 67.3960189819336,
				calculationService.calculateFloat("00000000006.982345*000000009.652347"), 0f);
		Assert.assertEquals("Check the multiplication logic", -20.752338409423828,
				calculationService.calculateFloat("3.458723*(-6)"), 0f);

		// testing the division operation
		Assert.assertEquals("Check the divide by zero exception", -1, calculationService.calculateFloat("6.0000/0"),
				0f);
		Assert.assertEquals("Check the division logic", -2.452066421508789,
				calculationService.calculateFloat("6.56786 / -2.6785"), 0f);
		Assert.assertEquals("Check the division logic", -4.615384578704834,
				calculationService.calculateFloat("-6   /   +1.30"), 0f);

		// testing the addition operation
		Assert.assertEquals("Check the addition logic", 12.933239936828613,
				calculationService.calculateFloat("3.0567+9.87654"), 0f);
		Assert.assertEquals("Check the addition logic", 6, calculationService.calculateFloat("+3.0+3.0"), 0f);
		Assert.assertEquals("Check the overflow condition", 3.00000018432E11,
				calculationService.calculateFloat("300000000000   +  30000"), 0f);
		Assert.assertEquals("Check the addition logic", 0,
				calculationService.calculateFloat("3.00000000000   +  -3.0000"), 0f);

		// testing the subtraction operation
		Assert.assertEquals("Check the subtraction logic", -6.070000171661377,
				calculationService.calculateFloat("3.56-9.63"), 0f);
		Assert.assertEquals("Check the subtraction logic", 6, calculationService.calculateFloat("+3.0-(-3.0)"), 0f);
		Assert.assertEquals("Check the overflow condition", 2.99999985664E11,
				calculationService.calculateFloat("300000000000   -  30000"), 0f);
		Assert.assertEquals("Check the subtraction logic", 0,
				calculationService.calculateFloat("3.00000000000   -  +3.0000"), 0f);

		// checking the overall operations
		Assert.assertEquals("Check the overall logic", 100,
				calculationService.calculateFloat("100.0000 * ( 2.00 + 12 ) / 14"), 0f);
	}

	@Test
	public void testResultLong() {
		// testing the multiplication operation
		Assert.assertEquals("Check the multiplication logic", 43, calculationService.calculateLong("6.567*6.675"));
		Assert.assertEquals("Check the multiplication logic for numbers with trailing zeros", 67396016813715L,
				calculationService.calculateLong("00000000006982345*000000009652347"));
		Assert.assertEquals("Check the multiplication logic", -241683572694746144L,
				calculationService.calculateLong("3458723*(-69876533245)"));

		// testing the division operation
		Assert.assertEquals("Check the division logic", -1,
				calculationService.calculateLong("600000000000000000000000000/0"));
		Assert.assertEquals("Check the division logic", -3, calculationService.calculateLong("69876476 / -20987665"));
		Assert.assertEquals("Check the division logic", -3,
				calculationService.calculateLong("-60987890   /   +19876754"));

		// testing the addition operation
		Assert.assertEquals("Check the addition logic", 13831281850239L,
				calculationService.calculateLong("3984998376475+9846283473764"));
		Assert.assertEquals("Check the addition logic", 394357038479933L,
				calculationService.calculateLong("+394357038479930+3.0"));
		Assert.assertEquals("Check the overflow condition", 9223372036854775807L,
				calculationService.calculateLong("300000000000094598739   +  30000"));
		Assert.assertEquals("Check the addition logic", 0,
				calculationService.calculateLong("3.00000000000   +  -3.000093743962"));

		// testing the subtraction operation
		Assert.assertEquals("Check the subtraction logic", 30655468749071L,
				calculationService.calculateLong("30985347988943-329879239872"));
		Assert.assertEquals("Check the subtraction logic", 6,
				calculationService.calculateLong("+3.0-(-3.098273498273)"));
		Assert.assertEquals("Check the overflow condition", 9223372036854775807L,
				calculationService.calculateLong("300000000000908989023   -  30000"));
		Assert.assertEquals("Check the subtraction logic", 0,
				calculationService.calculateLong("3.00000000000   -  +3.987923479000"));

		Assert.assertEquals(43000000000000000L,
				calculationService.calculateLong("3000000000000000+4000000000000000000/100"));
	}

	@Test
	public void testResultDouble() {

		// testing the multiplication operation
		Assert.assertEquals("Check the multiplication logic", 43.834725,
				calculationService.calculateDouble("6.567*6.675"), 0);
		Assert.assertEquals("Check the multiplication logic for numbers with trailing zeros", 67.39601681371501,
				calculationService.calculateDouble("00000000006.982345*000000009.652347"), 0);
		Assert.assertEquals("Check the multiplication logic", -20.752338,
				calculationService.calculateDouble("3.458723*(-6)"), 0);

		// testing the division operation
		Assert.assertEquals("Check the divide by zero exception", -1, calculationService.calculateDouble("6.0000/0"),
				0);
		Assert.assertEquals("Check the division logic", -2.452066455105469,
				calculationService.calculateDouble("6.56786 / -2.6785"), 0);
		Assert.assertEquals("Check the division logic", -4.615384615384615,
				calculationService.calculateDouble("-6   /   +1.30"), 0);

		// testing the addition operation
		Assert.assertEquals("Check the addition logic", 12.933240000000001,
				calculationService.calculateDouble("3.0567+9.87654"), 0f);
		Assert.assertEquals("Check the addition logic", 6, calculationService.calculateDouble("+3.0+3.0"), 0f);
		Assert.assertEquals("Check the overflow condition", 3.0000003E11,
				calculationService.calculateDouble("300000000000   +  30000"), 0f);
		Assert.assertEquals("Check the addition logic", 0,
				calculationService.calculateDouble("3.00000000000   +  -3.0000"), 0f);

		// testing the subtraction operation
		Assert.assertEquals("Check the subtraction logic", -6.07, calculationService.calculateDouble("3.56-9.63"), 0f);
		Assert.assertEquals("Check the subtraction logic", 6, calculationService.calculateDouble("+3.0-(-3.0)"), 0f);
		Assert.assertEquals("Check the overflow condition", 2.9999997E11,
				calculationService.calculateDouble("300000000000   -  30000"), 0f);
		Assert.assertEquals("Check the subtraction logic", 0,
				calculationService.calculateDouble("3.00000000000   -  +3.0000"), 0f);

		Assert.assertEquals(15.889013321622365,
				calculationService.calculateDouble("6.89998989777836487+8.989023423844"), 0);
	}

}
