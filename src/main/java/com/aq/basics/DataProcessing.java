package com.aq.basics;

import com.aq.bo.ListNode;
import com.aq.bo.Node;

import java.util.*;

public class DataProcessing{

    /**
     * @Description //优先队列，队列 PriorityQueue
     *  链接：https://leetcode-cn.com/problems/divide-array-in-sets-of-k-consecutive-numbers/solution/you-xian-dui-lie-by-liweiwei1419-2/
     * @Date 22:54 2019/12/22
     **/
    public boolean isPossibleDivide(int[] nums, int k) {
        int len = nums.length;
        if (len % k != 0) {
            return false;
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(len);
        for (int num : nums) {
            minHeap.offer(num);
        }

        while (!minHeap.isEmpty()) {
            Integer top = minHeap.poll();

            for (int i = 1; i < k; i++) {
                // 从 1 开始，正好需要移除 k - 1 个元素
                // i 正好就是相对于 top 的偏移
                if (!minHeap.remove(top + i)) {
                    // 如果移除失败，说明划分不存在，直接返回 false 即可
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @Date 23:49 2019/12/15
     * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
     * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
     * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
     * 你可以假设 nums1 和 nums2 不会同时为空。
     * 示例 :     * nums1 = [1, 2]     * nums2 = [3, 4]
     * 则中位数是 (2 + 3)/2 = 2.5
     *
     *@Description //TODO
     **/
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        return 0;
    }

    /**
     * @Date 16:26 2019/12/13
     * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * 输入: "abcabcbb"
     * 输出: 3
     *
     * @Description //TODO 并不是最优解，可参考官方示例
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
     * @Description //  放弃，太难了，得找规律，待智商恢复再回来瞧瞧，打个TODO标记 //@TODO
     **/
    public static int[] prisonAfterNDays(int[] cells, int N) {

        int[] newCells = new int[cells.length];

        while(N > 0) {
            for(int i = 1;i < cells.length-1; i++) {
                if(cells[i-1]==cells[i+1]){
                    newCells[i] = 1;
                }else {
                    newCells[i] = 0;
                }
            }
            newCells[0] = 0;
            newCells[newCells.length-1] = 0;
            for(int i = 0;i < newCells.length; i++) {
                cells[i] = newCells[i];
            }
            N--;
        }

        return newCells;
    }

    /**
     * @Date 14:21 2019/12/13
     * https://leetcode-cn.com/problems/copy-list-with-random-pointer/
     * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。要求返回这个链表的深拷贝。
     *
     * @Description  //@TODO
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

    public static String decodeString(String s) {

        List<Integer> index = new ArrayList<Integer>();

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length ; i++) {
            if(chars[i] == '['){
                index.add(i);
            }else if(chars[i] == ']'){
                index.add(i);
            }
        }

        if(index.size() <= 0) {
            return s;
        }

        /*String end = "";
        if(index.get(index.size()-1) < s.length()) {
            end = s.substring(index.get(index.size()-1)+1,s.length());
        }
        StringBuilder start = new StringBuilder("");
        start.append(s.substring(0,index.get(0)-1));*/

        index.forEach((e)-> System.out.println(e));
        for(int i = 0; i < index.size();i++){
            if(chars[index.get(i)]=='[' && chars[index.get(i)] != chars[index.get(i+1)]) {
                String item = s.substring(index.get(i)+1,index.get(i+1));
                char[] charsNew = Arrays.copyOf(chars, index.get(i));
                StringBuilder numStr = new StringBuilder("");
                int endIndex = 0;
                for (int j = charsNew.length-1; j >= 0; j--) {
                    if(Character.isDigit(charsNew[j])){
                        numStr.insert(0,charsNew[j]);
                        endIndex = j;
                    }else {
                        break;
                    }
                }
                System.out.println(numStr.toString());

//                char aChar = chars[index.get(i)-1];
                int num = Integer.parseInt(numStr.toString());

                StringBuilder stringBuilder = new StringBuilder("");

                System.out.println(item+"----"+num);
                for (int j = 0; j < num; j++) {
                    stringBuilder.append(item);
                }
                StringBuilder itemStrat = new StringBuilder(s.substring(0,endIndex));
                String itemEnd = s.substring(index.get(i+1)+1,s.length());

//                index.remove(i);
//                index.remove(i);
//                i=0;

//                index.forEach((e)-> System.out.println(e));

                itemStrat.append(stringBuilder).append(itemEnd);
                System.out.println(itemStrat.toString());
                return decodeString(itemStrat.toString());

            }/*else {
                i++;
            }*/
        }

//        stringBuilder.append(end);
//        return start.append(stringBuilder).toString();
        return s;
    }

    /**
     * @Description //给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
         * 输入：
         * A: [1,2,3,2,1]
         * B: [3,2,1,4,7]
         * 输出：3
         * 解释：
         * 长度最长的公共子数组是 [3, 2, 1] 。
     * @Date 18:33 2020/7/1
     * @Param [A, B]
     * @return int
     **/
    public static int findLength(int[] A, int[] B) {
        int maxCount = 0;
        int a = A.length, b = B.length;
        for (int i = 0; i < A.length; i++) {
            int minlen = Math.min(a-i, b);
            int max = toLength(A, B, i, 0, minlen);
            maxCount = Math.max(maxCount,max);
        }
        for (int i = 0; i < B.length; i++) {
            int minlen = Math.min(a, b-i);
            int max = toLength(A, B, 0, i, minlen);
            maxCount = Math.max(maxCount,max);
        }
        return maxCount;
        //存在很多边界条件，这种硬解法并不能根本解决问题。
        /*StringBuilder stringA = new StringBuilder().append(",");
        StringBuilder stringB = new StringBuilder().append(",");
        for (int i : A)
            stringA.append(i+",");
        for (int i : B)
            stringB.append(i+",");
        String s = stringA.toString();
        String s1 = stringB.toString();

        int maxLength = 0;
        int index = 0;
        while (index < A.length){
            for (int i = 0; i < A.length; i++) {
                if(i>=index) {
                    String substring = s.substring(index, i+1);
                    boolean equals = substring.substring(0, 1).equals(",");
                    if(s1.indexOf(substring)>-1&&equals&&!substring.equals(",")) {
                        int len = substring.substring(1,substring.length()).split(",").length+1;
                        maxLength = len>maxLength?len:maxLength;
                    }
                }
            }
            index++;
        }
        return maxLength;*/
    }
    public static int toLength(int[] a, int[] b, int str1, int str2, int len) {
        int k = 0;
        int strLen = 0;
        for (int i = 0; i < len; i++) {
            if(a[i+str1] == b[i+str2]) {
                k++;
            }else {
                k = 0;
            }
            strLen = Math.max(strLen,k);
        }
        return strLen;
    }


}

