/**
* Definition for singly-linked list
* struct ListNode{
*	int val;
*	ListNode *next;
* 	ListNode(int x):val(x),next(NULL){}
*};
*/
class Solution{
public:
/*
* @param head: a ListNode
* @return: a ListNode
*/
ListNode * swapPairs(ListNode *head){
	//write your code here
	ListNode *p = head;
	if(head == NULL){
	return head;
	}
	while(p != NULL && p->next != NULL){
	int tmp = p->val;
	p->val = p->next->val;
	p->next->val = tmp;
	p = p->next->next;
	}
	return head;
}
};
