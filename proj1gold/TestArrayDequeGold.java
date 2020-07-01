import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    private ArrayDequeSolution<String> actionDeque = new ArrayDequeSolution<>();
    private String outputMessage() {
        String message = String.join("\n", actionDeque);
        return message;
    }

    @Test
    public void testDequeImplementation() {
        StudentArrayDeque<Integer> studentDeque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solutionDeque = new ArrayDequeSolution<>();

        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                studentDeque.addLast(i);
                solutionDeque.addLast(i);
                String action = String.format("addLast(%d)", i);
                actionDeque.addLast(action);
            } else {
                studentDeque.addFirst(i);
                solutionDeque.addFirst(i);
                String action = String.format("addFirst(%d)", i);
                actionDeque.addLast(action);
            }
        }

        while(solutionDeque.size() > 0 && studentDeque.size() > 0) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                Integer studentInt = studentDeque.removeLast();
                Integer solutionInt = solutionDeque.removeLast();
                String action = "removeLast()";
                actionDeque.addLast(action);
                assertEquals(this.outputMessage(), solutionInt, studentInt);
            } else {
                Integer studentInt = studentDeque.removeFirst();
                Integer solutionInt = solutionDeque.removeFirst();
                String action = "removeFirst()";
                actionDeque.addLast(action);
                assertEquals(this.outputMessage(), solutionInt, studentInt);
            }
        }
    }
}
