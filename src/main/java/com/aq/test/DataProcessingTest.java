package com.aq.test;

import com.aq.basics.DataProcessing;
import com.aq.bo.ListNode;

/**
 * @Description :
 **/
public class DataProcessingTest {

    public static void main(String[] args) {
//        testAddTwoNumbers();

        testLengthOfLongestSubstring();
    }

    private static void testLengthOfLongestSubstring() {
        System.out.println(DataProcessing.lengthOfLongestSubstring("pwwkew"));
    }

    private static void testAddTwoNumbers() {
        ListNode listNode = new ListNode(2);
        listNode.next = new ListNode(4);
        listNode.next.next = new ListNode(3);

        ListNode listNode2 = new ListNode(5);
        listNode2.next = new ListNode(6);
        listNode2.next.next = new ListNode(4);

        ListNode result = DataProcessing.addTwoNumbers(listNode, listNode2);
        while (true) {
            if(result != null) {
                System.out.println(result.val);
                result = result.next;
            }else {
                break;
            }
        }
    }
}
