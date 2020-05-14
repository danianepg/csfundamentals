package csfundamentals.crackingthecodinginterview;

import java.time.Duration;
import java.time.Instant;

/**
 * Write a method to count the number of 2s between 0 and n
 * @author Daniane P. Gomes
 *
 */
public class Exercise18_4 {
	
	// Brute force
	public int numberOf2sInRange(int n) {
		int count = 0;
		
		for(int i=2; i<=n; i++) {
			count += numberOf2s(i);
		}
		
		return count;
	}
	
	private int numberOf2s(int n) {
		int count = 0;
		
		while(n>0) {
			if(n%10 ==2) {
				count++;
			}
			
			n = n/10;
		}
		
		return count;
	}
	
	// Clever
	private int count2sInARangeAtDigit(int number, int d) {

		System.out.println("DIGIT " + d + " of number " +number);
		
		int powerOf10 = (int) Math.pow(10, d);
		int nextPowerOf10 = powerOf10 * 10;
		int right = number % powerOf10;
		
		int roudDown = number - number % nextPowerOf10;
		int roundUp = roudDown + nextPowerOf10;
		int digit = (number / powerOf10) % 10;
		
		System.out.println("\t powerOf10 "+ powerOf10);
		System.out.println("\t nextPowerOf10 "+ nextPowerOf10);
		System.out.println("\t right "+ right);
		System.out.println("\t roudDown "+ roudDown);
		System.out.println("\t roundUp "+ roundUp);
		System.out.println("\t digit "+ digit);
		
		if(digit < 2) {
			System.out.println("\t digit < 2 "+ (roudDown / 10));
			
			return roudDown / 10;
		} else if(digit == 2) {
			
			System.out.println("\t digit == 2 "+ (roudDown / 10 + right + 1));
			
			return roudDown / 10 + right + 1;
		} else {
			System.out.println("\t digit > 2 "+ (roundUp / 10));

			return roundUp / 10;
		}
		
	}
	
	public int count2sInRange(int number) {
		
		int count = 0;
		
		int len = String.valueOf(number).length();
		
		for(int digit = 0; digit < len; digit++) {
			count += count2sInARangeAtDigit(number, digit);
		}
		
		return count;
	}
	
	public static void main(String[] args) {
		
		Exercise18_4 c = new Exercise18_4();
		int number = 224;
		
		// Brute force solution
		Instant start = Instant.now();
		int count = c.numberOf2sInRange(number);
		Instant end = Instant.now();
		System.out.println("Brute force solution: "+ count + " occurences in " + Duration.between(start, end).toMillis() + " ms. ");
		
		// Clever solution
		start = Instant.now();
		count = c.count2sInRange(number);
		end = Instant.now();
		System.out.println("Math solution: "+ count + " occurences in " + Duration.between(start, end).toMillis() + " ms. ");
		
	}

}
