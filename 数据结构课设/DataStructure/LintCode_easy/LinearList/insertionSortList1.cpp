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
     * @return: The head of linked list.
     */
    ListNode * insertionSortList(ListNode * head) {
        // write your code here
        ListNode *dummy = new ListNode(0);
        ListNode *tmp, *p;
        while(head != NULL){
            p = dummy;
            while(p->next != NULL && p->next->val < head->val){
                p = p->next;
            }
            tmp = head->next;
            head->next = p->next;
            p->next = head;
            head = tmp;
        }
        return dummy->next;
    }
    
};
