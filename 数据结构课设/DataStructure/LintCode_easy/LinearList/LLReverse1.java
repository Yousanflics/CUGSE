/**
 * Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }
 */


public class Solution {
    /*
     * @param head: n
     * @return: The new head of reversed linked list.
     */
    public ListNode reverse(ListNode head) {
        // write your code here
        ListNode revHead = null;
        ListNode p = head;
        ListNode prev = null;
        while(p!=null){
            ListNode pNext = p.next;
            if(pNext == null){
                revHead = p;
            }
            p.next = prev;
            prev = p;
            p = pNext;
        }
        return revHead;
    }
}
