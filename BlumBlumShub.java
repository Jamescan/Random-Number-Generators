/* James Cannalte 
 * Elena Hafner
 * BlumBlumShub.java
 *
 * Generates random bits via Blum-Blum-Shub algorithm
 */


public class BlumBlumShub
{
	// Instance variables
	private int p;
	private int q;
	private int s0;
	private int size;
	private long n;
	
	/* 
	 * Constructor
	 * 
	 * Precondition: p, q are large primes congruent to 3 modulo 4
	 * s0 is the initial seed
	 * 
	 */
	public BlumBlumShub(int p, int q, int s0, int size)
	{
		this.p = p;
		this.q = q;
		this.s0 = s0;
		this.size = size;
		this.n = p*q;
	}
	
	/*
	 * Returns 'size' number of pseudo-random digits
	 * according to the Blum-Blum-Shub algorithm 
	 */
	public int[] generate()
	{
		int b=-1; // current bit
		
		int result[] = new int[size];
		
		for (int i = 0; i < result.length; i++)
		{
			if (b == -1)
			{
				result[i] = this.s0 % 2;
			}
			else
			{
				result[i] = b;
			}
			this.s0 = (int)(Math.pow(this.s0, 2) % this.n);
			b = this.s0 % 2;			
		}
		return result;
	}
}
