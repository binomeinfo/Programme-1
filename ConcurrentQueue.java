import java.util.ArrayList;
import java.util.concurrent.locks.*;

public class ConcurrentQueue<E> {
	final int capacity;
	private final ArrayList<E> elements;
	private int head;
	private int fillingSize;
	private final Lock lock;
	
	ConcurrentQueue (int capacity1){
		 this.capacity = capacity1;
		 this.elements = new ArrayList<E>(capacity);
		 for (int i = 0; i < capacity;i++) {
			 this.elements.add(null);
		 }
		 this.head = this.fillingSize = 0;
		 this.lock = new ReentrantLock();
	}
	E take() {
		/*Il faut gérer le cas où la liste est vide, qui n'est pas géré pour l'instant!*/
		while (fillingSize==0) {};
		lock.lock();
		try {
			E e = elements.get(head);
			fillingSize--;
			head = (head+1)%capacity ;
			return e;
		} finally {lock.unlock();}
	}
	
	void put(E element) throws Exception {
		lock.lock();
		try {
			if (fillingSize == capacity) {
				throw new Exception();
			}
			elements.set((head+fillingSize)%capacity, element);
			fillingSize++;
		} finally {lock.unlock();}
	}
}
