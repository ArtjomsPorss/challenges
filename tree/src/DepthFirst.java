import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Given a list of integers,
 * find how many of them can combine together using bitwise & operator to a value of > 0
 * then return the count, how many of them can combine this way,
 * E.G.:
 * given a list of integers  [ 7, 4, 11, 8, 3 ]
 * the longest combination that results to value greater than 0 is
 * 7 & 11 & 3 == 3
 * so, the total count of numbers from the array used in this calculation is 3 <== 7 and 11 and 3
 * and the algorithm must return 3
 *
 */
public class DepthFirst {
    public static void main(String[] args) {

    }



}


class Solution {
    public static void main(String[] args) {
        solution();
    }
    public static void solution() {
        System.out.println(7 & 11 & 3);
        System.out.println(0 & 11);
        List<Integer> intList = new ArrayList<>(List.of(7, 4, 11, 8, 3));
        int longest = longest(intList, 0, null);
        System.out.printf("longest subsequence: %d\n", longest);
    }
    /**
     * previous & current > 0, call again with result
     *  increment counter
     * otherwise, return counter
     */
    private static int longest(List<Integer> intList, final int previousCounter, final Integer previous) {
        int currentCounter = 0;
        for (int i = 0; i < intList.size(); ++i) {
            List<Integer> newList = new ArrayList<>(intList);
            Integer current = newList.remove(i);
            int result = 0;
            if(Objects.nonNull(previous)) {
                result = current & previous;
            } else {
                result = current;
            }
            if (result <= 0) { continue; }
            else {
                currentCounter = previousCounter + 1;
                // try next
                int returningCounter = longest(newList, currentCounter, result);
                currentCounter = returningCounter > currentCounter ? returningCounter : currentCounter;
            }
            // result is 0, continue
            // call next

        }
        return currentCounter;
    }
}