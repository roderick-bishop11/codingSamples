import java.util.ArrayList;

public class MethodClass {

	public static <E extends Comparable<E>> E findMax(E[][] list) {
		E max = list[0][0];
		for(int i = 0; i < list.length-1; i++) {
			for(int j = 0; j < list.length;j++) {
				if(list[i+1][j].compareTo(list[i][j]) > 0) {
					max = list[i+1][j];
				}
			}
		}
		return max;
	}
	
	public static <E extends Comparable<E>> int binarySearch(E[] list, E key) {
		int low = 0;
		int high = list.length -1;
		
		while(high>= low) {
			int mid = (low+high)/2;
			if (key.compareTo(list[mid])<0) {
				high = mid-1;
			}
			else if (key.compareTo(list[mid]) == 0)
				return mid;
			else
				low = mid + 1;
		}
			
		return-1;
	}
	
	public static <E> void shuffle(ArrayList<E> list) {
				for(int i = 0; i< list.size();i++) {
					int element = (int)(Math.random()*list.size());
					E temp = list.get(i);
					list.set(i, list.get(element));
					list.set(element, temp);
// You could also use Collections.shuffle(list);
		}
	}
	
	
	public static <E extends Comparable<E>>void sort(ArrayList<E> list){
		E temp;
		for(int i = 0; i < list.size()-1;i++) {
		for(int j = 0; j<list.size(); j++) {
			int currentIndex = j; 
			int nextIndex = j+1;
			if(list.get(nextIndex).compareTo(list.get(currentIndex)) > 0){
				temp = list.get(currentIndex);
				list.set(currentIndex, list.get(nextIndex));
				list.set(nextIndex, temp);
			}
		}
	}
	}
	
}
