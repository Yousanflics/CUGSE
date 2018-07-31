"""
Definition of ListNode
class ListNode(object):

    def __init__(self, val, next=None):
        self.val = val
        self.next = next
"""


class Solution:
    """
    @param: head: The first node of linked list.
    @return: The head of linked list.
    """
    def insertionSortList(self, head):
        # write your code here
        if head == None:
            return head
        #first 为未排序链表的头节点
        first = head.next
        head.next = None
        #设置一个dummy链接在head的前面
        dummy = ListNode(0)
        dummy.next = head
        #设置一个尾节点和新值比较
        tail = head
        while first:
            #新值比尾节点要大则直接接上
            if tail.val < first.val:
                tmp = first
                tail.next = tmp
                first = first.next
                tail = tail.next
                tail.next = None
            #新值要是比尾节点小则从头开始遍历
            else:
                pre = dummy
                cur = dummy.next
                while cur:
                    if cur.val < first.next:
                        pre = pre.next
                        cur = cur.next
                    else:
                        tmp = first
                        first = first.next
                        tmp.next = cur
                        pre.next = tmp
                        break
        return dummy.next
