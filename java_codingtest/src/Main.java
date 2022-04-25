
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int test1[] = {1,2,2,3,3,3,4,6,7};
		System.out.println(lowerBound(test1, 4));
		System.out.println(upperBound(test1, 4));
		
		System.out.println(lowerBound(test1, 3));
		System.out.println(upperBound(test1, 3));
		
		System.out.println(lowerBound(test1, 5));
		System.out.println(upperBound(test1, 5));
		
		System.out.println(lowerBound(test1, 8));
		System.out.println(upperBound(test1, 8));
		
		System.out.println(lowerBound(test1, 0));
		System.out.println(upperBound(test1, 0));

	}

	   public static int lowerBound(int[] array,  int value) {
	        int low = 0;
	        int high = array.length;
	        while (low < high) {
	            final int mid = low + (high - low)/2;
	            if (value <= array[mid]) {
	                high = mid;
	            } else {
	                low = mid + 1;
	            }
	        }
	        return low;
	    }

	   public static int upperBound(int[] array, int value) {
	        int low = 0;
	        int high = array.length;
	        while (low < high) {
	            final int mid = low + (high - low)/2;
	            if (value >= array[mid]) {
	                low = mid + 1;
	            } else {
	                high = mid;
	            }
	        }
	        return low;
	    }

}
