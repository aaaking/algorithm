package datastructor;

import java.util.Deque;
import java.util.LinkedList;

// 剑指 Offer 59 - II. 队列的最大值 https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof/
public class MaxQueue {
    Deque<Integer> datas;
    Deque<Integer> helper;
    public MaxQueue() {
        datas = new LinkedList<>();
        helper = new LinkedList<>();
    }

    public int max_value() {
        return helper.isEmpty() ? -1 : helper.peekFirst();
    }

    public void push_back(int value) {
        datas.addLast(value);
        while (!helper.isEmpty() && helper.peekLast() < value) {
            helper.pollLast();
        }
        helper.addLast(value);
    }

    public int pop_front() {
        if (datas.isEmpty()) {
            return -1;
        }
        int top = datas.pollFirst();
        if (!helper.isEmpty() && helper.peekFirst() == top) {
            helper.pollFirst();
        }
        return top;
    }
}
