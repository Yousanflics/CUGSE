#include <iostream>
using namespace std;
typedef struct LNode
{
    LNode(int d, LNode *ptr = NULL) : data(d), next(ptr) {}//LNode构造函数，数据初始化；
    int data;
    LNode *next;//尾指针
}Node;

//链表输出
void outputList(Node *head) {
    Node *p = head;
    while (p != NULL) {
        cout << p->data << " ";
        p = p->next;//依次将链表后面的元素进行遍历
    }
    cout << endl;//一次输出结束后转行
}
// 转置链表的递归方法实现
Node* inverse(Node *head)
{
    // 异常判断(NULL==head)结束条件(NULL==head->nex),
    // 即head为最后一个节点时，将该节点返回，即为转置链表的头节点。
    if ( (head == NULL) || (head->next == NULL) )
        return head;
    // 递归后续链表（即以head->next为首节点的链表）
    Node *NewHead = inverse(head->next);//递归的过程中压栈一直递归到最后一个节点然后释放，也就是从尾输出即FILO
    // 上一步执行完后，head->next为后续链表的末尾节点，
    //所以让head->next的next指向当前节点head
    head->next->next = head;
    // 当前节点的next指向NULL
    head->next = NULL;
    // 返回后续链表的头指针
    return NewHead;
}
int main()
{
    // 创建链表
    Node n5(5);//调用struct LNode中的构造函数
    Node n4(4, &n5);//get address of the n5. since the argument of the construction is more than one
    Node n3(3, &n4);
    Node n2(2, &n3);
    Node n1(1, &n2);

    // 输出原始链表
    outputList(&n1);

    // 调用转置函数
    Node *pNew = inverse(&n1);

    // 输出转置后的链表
    outputList(pNew);

    return 0;
}
