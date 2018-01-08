/* James Cannalte 
 * Elena Hafner
 * FeedbackShift.java
 * 
 * Computes random numbers via Feedback Shift algorithm
 * 
 * Not suitable for cryptographic applications
 */

import java.util.ArrayList;


public class FeedbackShift
{
	private ArrayList<Integer> seed;
	private int[] coefficients;
	private int modulus;
	
	
	/*
	 * Constructor
	 * Takes in seed, list of coefficients, and modulus value
	 * 
	 */
	public FeedbackShift(ArrayList<Integer> s, int c[], int m)
	{
		this.coefficients = c;
		this.seed = s;
		this.modulus = m;
	}
	
	/*
	 * Generates 'num' random numbers via calls to
	 * generateNext() method; updates seed values as 
	 * necessary
	 * 
	 */
	public ArrayList<Integer> generate(int num)
	{
		int i = 0; 
		while (i < num)
		{
			this.seed.add(generateNext());
			i++;
		}
		return this.seed;
	}
	
	/*
	 * Generates the next random number to be placed
	 * in the ever-growing list of seeds
	 * 
	 */
	public int generateNext()
	{
		int i = this.seed.size()-1;
		int result = 0;
		int num = this.coefficients.length;
		
		for (int j = 0; j < num; j++)
		{
			result += this.coefficients[j] * this.seed.get(i-j);
		}
		result = result % this.modulus;
		
		return result;
	}
}
