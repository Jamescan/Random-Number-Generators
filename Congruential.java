/* James Cannalte 
 * Elena Hafner
 * Congruential.java
 * 
 * Generates random numbers via Linear Congruential algorithm
 * 
 * Not suitable for cryptographic applications
 */


public class Congruential
{
	private int seed;
	private int modulus;
	private int a; // used for multiplication
	private int b; // used for addition
	
	/*
	 * Constructor
	 * Takes in seed, modulus, 'a' and 'b' linear values (see above)
	 */
	public Congruential(int s, int m, int a, int b)
	{
		this.seed = s;
		this.modulus = m;
		this.a = a;
		this.b = b;
	}
	
	/*
	 * Implementation of Algorithm
	 * Generates 'num' random numbers
	 */
	public int[] generate (int num)
	{
		int[] arr = new int[num];
		
		arr[0] = this.seed;
		
		for (int i = 1; i < arr.length; i++)
		{
			arr[i] = (this.seed * this.a + this.b) % this.modulus;
			this.seed = (this.seed * this.a + this.b) % this.modulus;
		}
		
		return arr;
	}
}
