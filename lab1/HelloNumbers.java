public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
        while (x < 10) {
	    int localSum = 0;
	    for ( int y = 0; y <= x; y = y + 1)
		localSum = localSum + y;
            System.out.print(localSum + " ");
            x = x + 1;
        }
    }
}