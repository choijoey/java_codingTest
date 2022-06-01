import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<>();
		
		map.put("key","subject");
		map.put("word","AAA");
		
		System.out.println(map);
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
