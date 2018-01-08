/* James Cannalte 
 * Elena Hafner
 * Driver.java
 * 
 * Driver class for all PRNGs.
 * Used to gather initial input from the user
 * for each generator
 */


import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Driver 
{

	public static void main(String[] args)
	{
	//	RunCongruential();
		
		int[] random = RunBlumBlumShub();
					
		RunHillCipher(random);
		
	//	RunFeedbackShift();
	}
	
	// Driver for the Feedback Shift generator
	public static ArrayList<Integer> RunFeedbackShift()
	{
		Scanner scan = new Scanner(System.in);
		ArrayList<Integer> seed = new ArrayList<Integer>();
		
		System.out.println("Feedback Shift Generator \n");
		System.out.print("How many starting coefficients? ");
		int length = scan.nextInt();
		int length2 = length;
		
		int[] coefficients = new int[length2];
		
		while (length > 0)
		{
			System.out.print("Enter next initial seed: ");
			int next = scan.nextInt();
			seed.add(next);
			length--;
		}
		
		int i = 0;
		while (i < length2)
		{
			System.out.print("Enter next starting coefficient: ");
			coefficients[i] = scan.nextInt();
			
			i++;
		}
		
		System.out.print("Enter modulus: " );
		int mod = scan.nextInt();
		
		System.out.print("How many numbers to generate? ");
		int num = scan.nextInt();
		
		FeedbackShift x = new FeedbackShift(seed, coefficients, mod);
		ArrayList<Integer> result = x.generate(num);
		
		for (int j = 0; j < result.size(); j++)
		{
			System.out.print(result.get(j) + " ");
		}
		System.out.println();
		
		return result;
	}
	
	// Driver for the Congruential generator
	public static int[] RunCongruential()
	{
		Scanner scan = new Scanner(System.in);
		int seed, modulus, a, b, num;
		
		System.out.println("Linear Congruential Generator\n");

		System.out.print("Enter modulus: ");
		modulus = scan.nextInt();
		
		System.out.print("Enter initial seed: ");
		seed = scan.nextInt();
		
		System.out.print("Enter a (multiplicative scale factor): ");
		a = scan.nextInt();
		
		System.out.print("Enter b (additive scale factor): ");
		b = scan.nextInt();
		
		System.out.print("How many numbers to generate? ");
		num = scan.nextInt();
		
		Congruential x = new Congruential(seed, modulus, a, b);
		int[] ran = x.generate(num);
		printIntArray(ran);
		return ran;
	}
	
	// Driver for the Hill Cipher, requires a key k
	public static void RunHillCipher(int[] k)
	{
		Scanner scan = new Scanner(System.in);
		
		System.out.print("\nEnter plaintext for Hill Cipher: ");
		String plaintext = scan.nextLine();
		
		int n = (int)Math.sqrt(k.length);
		System.out.println("We will use a " + n + " by " + n + " matrix for the key");
		
		// Convert key array into n * n matrix
		int[][] key = new int[n][n];
		
		for (int c = 0; c < key[0].length; c++)
		{
			for (int r = 0; r < key.length; r++)
			{
				key[r][c] = k[r+c*key.length];
			}
		}
		
		System.out.print("Key: ");
		printInt2DArray(key);
		
		
		HillCipher h = new HillCipher(key, plaintext);
		String ciphertext = h.encrypt();
		System.out.println("Ciphertext is: " + ciphertext);
	}
	
	// Driver for the Blum-Blum-Shub Algorithm
	public static int[] RunBlumBlumShub()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Blum-Blum-Shub Generator\n");
		System.out.print("Enter first prime, p: ");
		int p = scan.nextInt();
		
		while (p%4 != 3)
		{
			System.out.print("p must be 3 mod 4. Enter p: ");
			p = scan.nextInt();
		}
		
		System.out.print("\nEnter second prime, q: ");
		int q = scan.nextInt();
		
		while (q%4 != 3)
		{
			System.out.print("q must be 3 mod 4. Enter q: ");
			q = scan.nextInt();
		}
		
		System.out.print("\nEnter initial seed: ");
		int seed = scan.nextInt();
		
		while (seed <= 0)
		{
			System.out.print("Invalid seed. Enter again: ");
			seed = scan.nextInt();
		}
		
		System.out.print("\nEnter number of random bits to generate. Must be a perfect square: ");
		int size = scan.nextInt();
		
		while (size <= 0)
		{
			System.out.print("Invalid size. Enter again: ");
			size = scan.nextInt();
		}
				
		BlumBlumShub Gen_1 = new BlumBlumShub(p, q, seed, size);
		
		int result[] = Gen_1.generate();

		printIntArray(result);
		return(result);
	}

	// Prints a 1 Dimensional Array of ints nicely
	public static void printIntArray(int[] arr)
	{
		System.out.println();
		for (int i = 0; i < arr.length; i++)
		{
			System.out.print(arr[i] + " ");
		}
	}
	
	// Prints a 2 Dimensional Array of longs nicely
	public static void printLongArray(long[] arr)
	{
		System.out.println();
		for (int i = 0; i < arr.length; i++)
		{
			System.out.print(arr[i] + " ");
		}
	}
	
	// Prints a 2 Dimensional Array of integers nicely 
	public static void printInt2DArray(int[][] array)
	{		
		System.out.println();
	    for (int i = 0; i < array.length; i++)
	    {
	    	for (int j = 0; j < array[0].length; j++)
	    	{
	    		System.out.print(array[i][j] + " ");
	    	}
	    	System.out.println();
	    }
	}
}
