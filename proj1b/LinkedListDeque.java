public class LinkedListDeque<T> implements Deque<T> {
    private int size;
    private dataNode sentinel;
    private class dataNode<T> {
        public T item;
        public dataNode next;
        public dataNode prev;
        public dataNode(T i, dataNode p, dataNode d) {
            item = i;
            next = d;
            prev = p;
        }
    }
    public LinkedListDeque() {
        size = 0;
        sentinel = new dataNode(-1, null, null );
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    @Override
    public void addFirst(T item) {
        dataNode newNode = new dataNode(item, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        dataNode newNode = new dataNode(item, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
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
        dataNode printPtr = sentinel.next;
        for(int i = 0; i < size; i += 1) {
            System.out.print(printPtr.item);
            System.out.print("");
            printPtr = printPtr.next;
        }
    }

    @Override
    public T removeFirst() {
        if(size == 0) {
            return null;
        }
        T val = (T) sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return val;
    }

    @Override
    public T removeLast() {
        if(size == 0) {
            return null;
        }
        T val = (T) sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return val;
    }

    @Override
    public T get(int index) {
        //iteratively get the index-th item
        if(index >= size) {
            return null;
        }
        dataNode ptr = sentinel.next;
        for(int i = 0; i < index; i += 1) {
            ptr = ptr.next;
        }
        T val = (T) ptr.item;
        return val;
    }
    public T getRecursive(int index) {
        if(index >= size) {
            return null;
        }
        return (T) getRecursiveHelper(index, sentinel.next);
    }
    private T getRecursiveHelper(int index, dataNode ptr) {
        if(index == 0) {
            return (T) ptr.item;
        }
        return (T) getRecursiveHelper(index - 1, ptr.next );
    }
}
