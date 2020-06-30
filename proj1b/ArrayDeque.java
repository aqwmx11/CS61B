//import java.lang.reflect.Array;

public class ArrayDeque<T> implements Deque<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;

    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    //move the pointer to the next place, will be back to 0 if reaching end
    private int ptrForward(int ptr) {
        return (ptr + 1) % items.length;
    }
    private int ptrBackward(int ptr) {
        return (ptr - 1 + items.length) % items.length;
    }

    private void resize(int newSize) {
        T[] newArray = (T []) new Object[newSize];
        int oldIndex = ptrForward(nextFirst);
        for(int i = 0; i < size; i ++) {
            newArray[i] = items[oldIndex];
            oldIndex = ptrForward(oldIndex);
        }
        items = newArray;
        nextFirst = newSize - 1;
        nextLast = size;
    }

    @Override
    public void addFirst(T item) {
        if(size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = ptrBackward(nextFirst);
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if(size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = ptrForward(nextLast);
        size += 1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int index = ptrForward(nextFirst);
        for(int i = 0; i < size; i ++) {
            System.out.print(items[index] + "");
            index = ptrForward(index);
        }
    }

    @Override
    public T removeFirst() {
        if(size == 0) {
            return null;
        }
        T removeValue = items[ptrForward(nextFirst)];
        items[ptrForward(nextFirst)] = null;
        nextFirst = ptrForward(nextFirst);
        size -= 1;
        if(items.length >= 16 && size < (items.length / 4)) {
            resize(items.length / 2);
        }
        return removeValue;
    }

    @Override
    public T removeLast() {
        if(size == 0) {
            return null;
        }
        T removeValue = items[ptrBackward(nextLast)];
        items[ptrBackward(nextLast)] = null;
        nextLast = ptrBackward(nextLast);
        size -= 1;
        if(items.length >= 16 && size < (items.length / 4)) {
            resize(items.length / 2);
        }
        return removeValue;
    }

    @Override
    public T get(int index) {
        if(index >= size) {
            return null;
        }
        int ptr = ptrForward(nextFirst);
        return items[(ptr + index) % items.length];
    }

}
