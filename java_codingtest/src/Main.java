
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


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
