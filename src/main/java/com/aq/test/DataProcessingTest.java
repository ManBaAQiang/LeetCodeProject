package com.aq.test;

import com.aq.basics.DataProcessing;
import com.aq.bo.ListNode;

/**
 * @Description :
 **/
public class DataProcessingTest {

    public static void main(String[] args) {
//        testAddTwoNumbers();
//        testLengthOfLongestSubstring();
//        testPrisonAfterNDays();


    }

    public static int findNumbers(int[] nums) {
        int sum = 0;
        for(int i=0; i<nums.length; i++) {
            int count = 0;
            while (nums[i] > 0) {
                nums[i] = nums[i] / 10;
                count++;
            }
            if(count != 0 && count%2 == 0) {
                sum++;
            }
        }
        return sum;
    }


    private static void testPrisonAfterNDays() {
        int[] cells = {1,0,0,1,0,0,1,0};
        int[] ints = DataProcessing.prisonAfterNDays(cells, 3);
        for (int n : ints) {
            System.out.println(n);
        }
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
