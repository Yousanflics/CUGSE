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
     * @param head: A ListNode.
     * @return: A boolean.
     */
    
    bool isPalindrome(ListNode * head) {
        // write your code here
            if (head == NULL)
        {
            return true;
        }

        ListNode *p = head;
        ListNode *q = head;
        while (p->next != NULL && p->next->next != NULL)
        {
            p = p->next->next;
            q = q->next;
        }

        ListNode *r = reverseList(q->next);
        p = head;
        while (r != NULL)
        {
            if (p->val != r->val)
            {
                return false;
                break;
            }
            p = p->next;
            r = r->next;
        }

        return true;
    }
private:
    ListNode* reverseList(ListNode *head)
    {
        if (head == NULL)
        {
            return NULL;
        }

        ListNode *p = head->next;
        ListNode *q = head;
        q->next = NULL;
    
        while (p != NULL)
        {
            ListNode *next = p->next;
            p->next = q;
            q = p;
            p = next;
        }

        return q;
    }
    
};
