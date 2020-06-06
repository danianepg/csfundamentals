package leetcode.arrays;

public class TrappingRainWaterPointers {

	public static int trap(int[] height) {

		if(height == null || height.length == 0) {
			return 0;
		}
		
		int waterTrapped = 0;
		int left = 0;
		int right = height.length-1;
		int leftMax = 0;
		int rightMax = 0;
		
		while(left < right) {
			
			System.out.println("\nleft " +left + " right " + right);
			System.out.println(height[left] + " < " +height[right]);
			
			if(height[left] < height[right]) {
				
				if(height[left] >= leftMax) {
					leftMax = height[left];
				} else {
					waterTrapped += leftMax - height[left];
					System.out.println("left waterTrapped " + waterTrapped);
				}
				left++;
			} else {
				if(height[right] >= rightMax) {
					rightMax = height[right]; 
				} else {
					waterTrapped += rightMax - height[right];
					System.out.println("right waterTrapped " + waterTrapped);
				}
				right--;
			}
			
			System.out.println("leftMax " + leftMax + " - rightMax " +rightMax);
		}


		return waterTrapped;
	}

	public static void main(String[] args) {
		int[] water = { 0, 1, 0, 0, 1, 0, 1, 3, 2, 1, 2, 1 };
//		int[] water = { 0, 1, 0, 2, 1, 0, 0, 0, 0, 0, 0, 0 };
		System.out.println(trap(water));
	}

}
