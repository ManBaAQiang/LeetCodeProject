package com.aq.basics;

import com.aq.bo.ListNode;
import com.aq.bo.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataProcessing{


    /**
     * @Date 16:26 2019/12/13
     * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * 输入: "abcabcbb"
     * 输出: 3
     *
     * @Description //TODO
     **/
    public static int lengthOfLongestSubstring(String s) {

        List<Integer> lens = new ArrayList<>();
        char[] chars = s.toCharArray();
        /*if (chars.length==1)
            return 1;*/

        String str2 = "";
        for(int i = 0; i <chars.length; i++) {
            s = String.valueOf(chars[i]);
            for(int y = i+1; y <chars.length; y++) {
                str2 = String.valueOf(chars[y]);
                if(s.contains(str2)) {
                    break;
                }
                s += str2;
            }
            lens.add(s.length());
        }

        /*int strLength = 0;
        for (int i : lens) {
            if(i > strLength)
                strLength = i;
        }*/
        int strLength = Collections.max(lens);

        return strLength;
    }

    /**
     * @Date 16:22 2019/12/13
     * https://leetcode-cn.com/problems/prison-cells-after-n-days/
     * 8 间牢房排成一排，每间牢房不是有人住就是空着。每天，无论牢房是被占用或空置，都会根据以下规则进行更改：
     * 如果一间牢房的两个相邻的房间都被占用或都是空的，那么该牢房就会被占用。否则，它就会被空置。
     * 由于监狱中的牢房排成一行，所以行中的第一个和最后一个房间无法有两个相邻的房间。
     * 如果第 i 间牢房被占用，则 cell[i]==1，否则 cell[i]==0。根据监狱的初始状态，在 N 天后返回监狱的状况（和上述 N 种变化）。
     * 输入：cells = [0,1,0,1,1,0,0,1], N = 7
     * 输出：[0,0,1,1,0,0,0,0]
     * @Description //TODO
     **/
    public static int[] prisonAfterNDays(int[] cells, int N) {

        return null;
    }

    /**
     * @Date 14:21 2019/12/13
     * https://leetcode-cn.com/problems/copy-list-with-random-pointer/
     * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。要求返回这个链表的深拷贝。
     *
     * @Description
     **/
    public static Node copyRandomList(Node head) {

        return null;
    }

    /**
     * @Date 10:37 2019/12/13
        https://leetcode-cn.com/problems/add-two-numbers/
        给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
        如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
        您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
        示例：
        输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
        输出：7 -> 0 -> 8
        原因：342 + 465 = 807
     * @Description 计算逻辑：
         将各个节点的数据相加，十位进一
    **/
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode listNode = new ListNode(0);
        ListNode listNode1 = listNode;
        int item = 0;
        while (l1 != null || l2 != null || item != 0) {
            int num1 = l1 == null ? 0 : l1.val;
            int num2 = l2 == null ? 0 : l2.val;

            int sum = num1 + num2 + item;

            int bit = sum % 10;
            item = sum / 10;

            ListNode sumNode = new ListNode(bit);
            listNode1.next = sumNode;
            listNode1 = sumNode;

            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }

        return listNode.next;
    }
}

