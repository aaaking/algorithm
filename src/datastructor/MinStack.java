package com.huawei.rtc.rtcmanage.del.datastructor;

import java.util.Deque;
import java.util.LinkedList;

/**
 * description
 *
 * @since : 2022/4/15 15:46
 */
// https://leetcode-cn.com/problems/min-stack/   力扣155最小栈
public class MinStack {
    /**
     * 在代码实现的时候有两种方式：
     * 1、辅助栈和数据栈同步
     * 特点：编码简单，不用考虑一些边界情况，就有一点不好：辅助栈可能会存一些“不必要”的元素。
     * 2、辅助栈和数据栈不同步
     * 特点：由“辅助栈和数据栈同步”的思想，我们知道，当数据栈进来的数越来越大的时候，我们要在辅助栈顶放置和当前辅助栈顶一样的元素，这样做有点“浪费”。基于这一点，我们做一些“优化”，但是在编码上就要注意一些边界条件。
     * （1）辅助栈为空的时候，必须放入新进来的数；
     * （2）新来的数小于或者等于辅助栈栈顶元素的时候，才放入，特别注意这里“等于”要考虑进去，因为出栈的时候，连续的、相等的并且是最小值的元素要同步出栈；
     * （3）出栈的时候，辅助栈的栈顶元素等于数据栈的栈顶元素，才出栈。
     * <p>
     * 总结一下：出栈时，最小值出栈才同步；入栈时，最小值入栈才同步。
     */
    Deque<Integer> xStack;
    Deque<Integer> minStack;

    public MinStack() {
        xStack = new LinkedList<Integer>();
        minStack = new LinkedList<Integer>();
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int x) {
        xStack.push(x);
        minStack.push(Math.min(minStack.peek(), x));
    }

    public void pop() {
        xStack.pop();
        minStack.pop();
    }

    public int top() {
        return xStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
