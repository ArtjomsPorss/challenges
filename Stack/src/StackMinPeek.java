import java.util.Stack;

/**
 * create a stack that has a low latency peek method that returns the lowest value in the stack
 */
public class StackMinPeek {
    public static void main(String[] args) {
        System.out.println("Hello world!");

    }
}






class Solution {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        var customStack = new CustomStack();
        customStack.push(1);
        customStack.push(0);
        customStack.push(5);
        customStack.push(3);
        customStack.push(2);
        System.out.println(customStack.peek());
        System.out.println(customStack.minPeek());
        customStack.pop();
        System.out.println(customStack.peek());
        System.out.println(customStack.minPeek());
        customStack.pop();
        System.out.println(customStack.peek());
        System.out.println(customStack.minPeek());
        customStack.pop();
        System.out.println(customStack.peek());
        System.out.println(customStack.minPeek());
        customStack.pop();
        System.out.println(customStack.peek());
        System.out.println(customStack.minPeek());
        customStack.pop();
    }
}

/**
 * the idea is the first item is added to the stack irrespective of whether it's lowest or not
 * the second item is then checked against existing item
 */
class CustomStack {
    Stack<Integer> all = new Stack<>();
    Stack<Integer> min = new Stack<>();

    public Integer peek() {
        return all.peek();
    }

    public Integer push(Integer item) {
        all.push(item);
        if (!min.empty() && min.peek() > item || min.empty()) {
            min.push(item);
        }
        return item;
    }

    public Integer pop() {
        Integer item = all.pop();
        if (min.peek() == item) {
            min.pop();
        }
        return item;
    }

    public Integer minPeek() {
        if (min.empty()) {
            return null;
        }
        return min.peek();
    }
}

//class CustomStack<T> extends Stack {
//    private Stack<T> min = new Stack<>();
//    private Stack<T> all = new Stack<>();
//
//    @Override
//    public T push(T item) {
//        all.push(item);
//        if (all.peek() > item) {
//
//        }
//    }
//
//}

