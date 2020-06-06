package crackingthecodinginterview;

import java.util.ArrayList;
import java.util.List;

/**
 * Describe an algorithm to find the smallest one million numbers in one billion
 * numbers. Assume that the computer memory can hold all one billion numbers.
 * 
 * @author Daniane P. Gomes
 *
 */
public class Exercise18_6 {

	public void getSmallest(int numElements, List<Integer> numbers) {
		numbers = quickSort(0, numbers.size() - 1, numbers);
	}

	// TODO: not working
	private List<Integer> quickSort(int left, int right, List<Integer> numbers) {

		System.out.println("right - left : [" + left + "," + right + "] = " + (right - left));

		if ((right - left) <= 0) {
			return numbers;
		} else {

			int pivot = numbers.get(right);
			int locationPivot = partition(left, right, pivot, numbers);

			System.out.println("pivot " + pivot + " - location " + locationPivot);

			if (locationPivot - 1 > 0 && locationPivot - 1 < numbers.size()) {
				quickSort(left, locationPivot - 1, numbers);
			}

			if (locationPivot + 1 > 0 && locationPivot + 1 < numbers.size()) {
				quickSort(right, locationPivot + 1, numbers);
			}
		}

		return null;
	}

	private int partition(int left, int right, int pivot, List<Integer> numbers) {

		int leftPointer = left;
		int rightPointer = right;

		while (true) {

			while (numbers.get(leftPointer) < pivot) {
				System.out.println("\t while left [" + leftPointer + "] = " + numbers.get(leftPointer) + " < " + pivot);
				leftPointer++;
			}

			while (rightPointer >= 0 && numbers.get(rightPointer) > pivot) {
				System.out.println(
						"\t while right [" + rightPointer + "] = " + numbers.get(rightPointer) + " > " + pivot);
				rightPointer--;
			}
			System.out.println("\t leftPointer: " + leftPointer + " rightPointer " + rightPointer);

			if (leftPointer >= rightPointer) {
				break;
			} else {
				swap(leftPointer, rightPointer, numbers);
			}

		}

		swap(leftPointer, rightPointer, numbers);
		return leftPointer;
	}

	private void swap(int left, int right, List<Integer> numbers) {

		System.out.println("\t\t SWAP " + numbers.get(left) + " and " + numbers.get(right));
		Integer temp = numbers.get(left);
		numbers.set(left, numbers.get(right));
		numbers.set(right, temp);
		System.out.println(numbers);

	}

	public List<Integer> generateArray(int numberElements) {

		List<Integer> arr = new ArrayList<>();

		for (int i = 0; i < numberElements; i++) {
			arr.add((int) (Math.random() * 1000) + 10);
		}

		return arr;
	}

	public static void main(String[] args) {

		Exercise18_6 e = new Exercise18_6();

		List<Integer> arr = e.generateArray(10);
		System.out.println(arr);

		e.getSmallest(2, arr);

		System.out.println(arr);

	}

}
