package csfundamentals.leetcode;

import java.util.Stack;

public class TrappingRainWaterStack {

	public static int trap(int[] height) {

		Stack<Integer> stack = new Stack<>();
		int waterTrapped = 0;

		for (int i = 0; i < height.length; i++) {

			int current = height[i];

			System.out.println("\ni=" + i + " - current " + current );
			
			if(!stack.isEmpty()) {
				System.out.println(" height de "+stack.peek());
			}

			while (!stack.isEmpty() && current > height[stack.peek()]) {
				Integer middle = stack.pop();

				if (stack.isEmpty()) {
					break;
				}
				
				Integer leftBorder = stack.peek();
				
				System.out.println("\tmiddle " + middle);
				Integer distance = (i - leftBorder) - 1;
				System.out.println("\tdistance " + distance +" =====> (i "+i+" - leftBorder "+leftBorder+")");

				Integer heightElement = Math.min(current, height[leftBorder]) - height[middle];
				System.out.println("\theightElement " + heightElement+" =====> min entre "+current+" e" + height[leftBorder] + " - " +height[middle]);
				
				waterTrapped += distance * heightElement;
				System.out.println("\twaterTrapped " + waterTrapped);

			}

			stack.add(i);
			System.out.println("ADD " +i);

		}

		return waterTrapped;
	}

	public static void main(String[] args) {
		int[] water = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		System.out.println(trap(water));
	}

}
