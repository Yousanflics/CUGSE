/**
*Definition for ListNode.
*public class ListNode{
*	int val;
*	ListNode next;
*	ListNode(int val){
*	   this.val = val;
*          this.next = null;
*       }
*}
*/



public class Solution{
	/*
	 *@param head:n
	 *@return: The new head of reversed Liked list.
	 */
	public ListNode reverse(ListNode head){
		if(head == null || head.next == null)
			return head;
		ListNode a = head.next;
		head.next = null;
		ListNode newList = reverse(a);
		a.next = head;
		return newList;
	}
}
