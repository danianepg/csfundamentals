package csfundamentals.crackingthecodinginterview;

/**
 * Write a function to swap a number in place (that is, without temporary variables).
 * @author Daniane P. Gomes
 *
 */
public class Exercise17_1 {

	public void swap(int a, int b) {

		System.out.println("Initial values: a=" + a + " b=" + b);
		
		a = a - b;
		b = a + b;
		a = b - a;
		
		System.out.println("After changes: a=" + a + " b=" + b);
			
	}

	public static void mains(String[] args) {
		
	}
	
	
}
