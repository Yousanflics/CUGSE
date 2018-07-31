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
        if(head == NULL){
            return head;
        }
        ListNode *newHead = new ListNode(INT_MIN);
        newHead->next = head;
        ListNode *alSort, *noSort, *p, *q;
        alSort = newHead->next;
        newHead->next = NULL;
        while(alSort != NULL){
            p = newHead;
            noSort = newHead->next;
             while (noSort != NULL && noSort->val <= alSort->val) {
                p = noSort;
                noSort = noSort->next;
            }
            q = alSort->next;
            alSort->next = p->next;
            p->next = alSort;
            alSort = q;
        }
        return newHead->next;
        }
    
};
