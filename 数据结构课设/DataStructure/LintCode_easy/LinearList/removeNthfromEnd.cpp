/**
 * Definition of ListNode
 * class ListNode {
 * public:
 *     int val;
 *     ListNode *next;
 *     ListNode(int val) {
 *         this->val = val;
 *         this->next = NULL;
 *     }
 * }
 */


class Solution {
public:
    /*
     * @param head: The first node of linked list.
     * @param n: An integer
     * @return: The head of linked list.
     */
    ListNode * removeNthFromEnd(ListNode * head, int n) {
        // write your code here
        ListNode *fast = head;
        ListNode *slow = head;
        if(head == NULL ){
            return NULL;
        }
        for(int i = 0; i < n; i++){
            fast = fast->next;
        }
        if(fast == NULL){
            return head->next;
        }
        while(fast->next!= NULL){
            fast = fast->next;
            slow = slow->next;
        }
        slow->next = slow->next->next;
        return head;
    }
};
