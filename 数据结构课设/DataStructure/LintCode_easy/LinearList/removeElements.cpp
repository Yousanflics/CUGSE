/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */


class Solution {
public:
    /*
     * @param head: a ListNode
     * @param val: An integer
     * @return: a ListNode
     */
    ListNode * removeElements(ListNode * head, int val) {
        // write your code here
        if (head == NULL) {
            return head;
        }
        ListNode *newNode = new ListNode(0);
        newNode->next = head;
        head = newNode;
        while (head->next != NULL) {
         if (head->next->val == val) {
         head->next = head->next->next;
         }
        else if (head->next->val != val ) {
            head = head->next;
        }
    }
        return newNode->next;
}
};
