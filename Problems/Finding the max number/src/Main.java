import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static int findMaxByIterator(Iterator<Integer> iterator) {
        // write your code here
        int integer = 0;
        while (iterator.hasNext()){
            Integer next = iterator.next();
            if (next > integer){
                integer = next;
            }
        }
        return integer;
    }

    /* Do not change code below */
    public static void main(String[] args) {

        final Scanner scanner = new Scanner(System.in);

        final List<Integer> list = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        System.out.println(findMaxByIterator(list.iterator()));
    }
}