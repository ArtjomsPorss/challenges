import java.util.ArrayList;
import java.util.List;

/**
 * given a several log files that contain execution intervals from all jobs executed on the server.
 * we are interested in learning times when system is busy
 * create a function that will process all intervals passed as parameter and generate output which shows when system is busy
 *
 * Assumptions:
 * assumed that data is parsed and is in format that can processed by your function
 * parsed data is passed into method you create
 *
 * data passed:
 * 1---5    10------17           29---------39
 *     5-7            19-21               37-----43
 *   3----8                  27----32
 *
 * sample output:
 * 1-------8 10------17 19-21     27-------------------------43
 */
public class ViaLogsIdentifyWhenSystemIsBusy {
    public static void main(String[] args) {

    }
}







class Solution {
    public static void main(String[] args) {
        List<List<String>> lists = new ArrayList(List.of("1---5",
                        "10------17",
                        "5-7",
                        "27----32",
                        "3----8",
                        "19-21",
                        "29---------39",
                        "37-----43").stream().map(e -> convertToListOfStrings(e))
                .sorted((o1, o2) -> {
                    int i = Integer.valueOf(o1.get(0));
                    int j = Integer.valueOf(o2.get(0));
                    if (i > j) {
                        return 1;
                    } else if (j > i) {
                        return -1;
                    } else {
                        return 0;
                    }
                }).toList());

//        lists.forEach(e -> System.out.println(e.get(0)));

        // iterate through numbers indefinitely
        // if a list starts with that position, start with it
        // add a number to output
        // if next is dash - add dash to output
        // if next is a number - that list completes - hold number
        // remove that list(done with it), go to next one
        // if next list starting number is before the number we hold, move to position that matches the held number
        //  if list is long enough, if its a num, repeat drop, if it's a - add a - and continue iterating
        // if list is short, drop list, go to next one
        // if next list starts later - add spaces until that number
        // no more lists then add last number and exit
        int lastPos = Integer.valueOf(lists.get(lists.size() - 1).get(lists.get(lists.size() - 1).size() - 1));

        List<String> output = new ArrayList<>();
        for (int i = 1; i <= lastPos ; ++i) {
            for (int j = 0; j < lists.size();) {
                List<String> lst = lists.get(j);
                for (int x = 0; x < lst.size(); ++x) {
                    // first ever, just add number
                    if (output.isEmpty()) {
                        output.add(lst.get(j));
                        ++i;
                        continue;
                    }
                    // if it's a dash, then add dash
                    if (lst.get(x).equals("-")) {
                        output.add("-");
                        ++i;
                    } else {
                        // if num is less than current size of output, continue to next element
                        if (Integer.valueOf(lst.get(x)) < output.size()) {
                            x += output.size() - Integer.valueOf(lst.get(x));
                            continue;
                        }
                        // if num is same as current size, add dash
                        if (Integer.valueOf(lst.get(x)) == output.size() + 1) {
                            output.add("-");
                        } else {
                            output.set(output.size() - 1, ""+ output.size());
                            // num is greater than current size, add spaces, increment i
                            int diff = Integer.valueOf(lst.get(x)) - output.size() - 1;
                            while (diff > 0) {
                                output.add(" ");
                                diff--;
                                i++;
                            }
                            output.add(lst.get(x));
                        }
                    }
                }
                // go to next list
                lists.remove(0);
            }
        }
        output.set(output.size() - 1, ""+ output.size());


        System.out.println(String.join("", output));
    }

    public static List<String> convertToListOfStrings(String sequence) {
        List<String> output = new ArrayList<>();
        String num = "";
        for (int i = 0; i < sequence.length(); ++i) {
            if (sequence.charAt(i) == '-') {
                if (!num.isBlank()) {
                    output.add(num);
                    num = "";
                }
                output.add("-");
            } else {
                num += sequence.charAt(i);
            }
        }
        output.add(num);
        return output;
    }
}