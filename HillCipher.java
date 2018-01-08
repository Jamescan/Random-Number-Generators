/* James Cannalte 
 * Elena Hafner
 * HillCipher.java
 * 
 * Implements the Hill Cipher
 */

public class HillCipher
{
	private int[][] key; // n x n matrix
	private String plaintext; 
	private String ciphertext;
	private int[][] pmatrix;
	private int n;
	
	
	/* 
	 * Constructor
	 * Takes in n x n matrix key and plaintext 
	 * to encrypt
	 * 
	 */
	public HillCipher(int[][] k, String toEncrypt)
	{
		this.key = k;
		this.plaintext = toEncrypt.toLowerCase();
		this.n = key.length;
		this.ciphertext="";
	}
	
	
	/*
	 * Algorithm
	 * Steps:
	 * 		1) Convert English to numbers mod 26
	 * 		2) Pad with zeros as necessary 
	 * 		3) Organize numbers into n-rowed matrix
	 * 		4) Multiply key with result from step 3
	 * 		5) Convert back to ciphertext
	 * 
	 */
	public String encrypt()
	{
		int numZeros; // number of zeros to add
		
		numZeros = (plaintext.length() % n);
		
		if (numZeros != 0)
		{
			numZeros = n - numZeros;
		}
		
		// Array with one letter element chars
		// Example: converts plaintext "buckmulligan"
		// into [1 20 2 10 12 20 ... 6 0 13]
		int[] arr = new int[this.plaintext.length()+numZeros];
		
		for (int i = 0; i < arr.length; i++)
		{
			if (i < arr.length - numZeros)
			{
				// Take advantage of ASCII encoding
				arr[i] = this.plaintext.charAt(i) - 'a';
			}
			else
			{
				arr[i] = 0;
			}
		}
		
		// Now convert into 2D array based on size n
		if (plaintext.length()%n == 0)
		{
			pmatrix = new int[n][plaintext.length()/n];
		}
		else
		{
			pmatrix = new int[n][plaintext.length()/n+1];
		}
	    
	    for (int c = 0; c < pmatrix[0].length; c++)
	    {
	    	for (int r = 0; r < pmatrix.length; r++)
	    	{
	    		pmatrix[r][c] = arr[r+c*pmatrix.length];
	    	}
	    }
	
		// Now, multiply key * pmatrix. Be sure to convert mod 26
		int[][] result = new int[key.length][pmatrix[0].length];
		
		for (int i = 0; i < key.length; i++) 
		{ 
		    for (int j = 0; j < pmatrix[0].length; j++) 
		    { 
		        for (int k = 0; k < key[0].length; k++) 
		        { 
		            result[i][j] += (key[i][k] * pmatrix[k][j]);
		        }
		    }
		}
		
		// Convert to mod 26
		for (int i = 0; i < result.length; i++)
		{
			for (int j = 0; j < result[0].length; j++)
			{
				while (result[i][j] >= 26)
				{
					result[i][j] = result[i][j]-26;
				}
				while (result[i][j] < 0)
				{
					result[i][j] = result[i][j]+26;
				}
			}
		}
						
		for (int c = 0; c < result[0].length; c++)
		{
			for (int r = 0; r < result.length; r++)
			{
				this.ciphertext += (char)(result[r][c] + 97);
			}
		}
		
		return ciphertext;
	}	
}
