/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode modifiedList(int[] nums, ListNode head) {
        int maxValue = -1;
        for (int value : nums) {
            if (value > maxValue) {
                maxValue = value;
            }
        }
        boolean[] isPresent = new boolean[maxValue + 1];
        for (int value : nums) {
            isPresent[value] = true;
        }
        ListNode dummyHead = new ListNode();
        ListNode tail = dummyHead;
        while (head != null) {
            if (head.val >= isPresent.length || !isPresent[head.val]) {
                tail.next = head;
                tail = tail.next;
            }
            head = head.next;
        }
        tail.next = null;
        return dummyHead.next;
    }
}