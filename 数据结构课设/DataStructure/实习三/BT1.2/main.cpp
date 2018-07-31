//AB#DG###CE##F##
//#include<stdio.h>
#include<iostream>
using namespace std;

typedef char ElemType;
typedef struct BiTNode{//二叉树的定义
    ElemType data;
    struct BiTNode *lchild,*rchild;
}BiTNode,*BiTree;

BiTNode* CreateBiTree(BiTNode*);
void SwapLRtreePre(BiTNode*);
void SwapLRtreePost(BiTNode*);
void PreOrder(BiTNode*);
void InOrder(BiTNode*);
void Jundge(BiTNode*);

//----------------------------------------------------------

int main(int argc, char* argv[]){
    BiTNode* T= new BiTNode();
    T=CreateBiTree(T);

    Jundge(T);
    SwapLRtreePre(T);
    Jundge(T);

    Jundge(T);
    SwapLRtreePost(T);
    Jundge(T);

    return 0;
}

BiTNode* CreateBiTree(BiTNode* T){
    ElemType x;
    cin>>x;
    if(x=='#'){
        return NULL;
    }
    T= new BiTNode();
    T->data=x;
    T->lchild=CreateBiTree(T->lchild);
    T->rchild=CreateBiTree(T->rchild);
    return T;
}

//----------------------------------------------------------

//以“先序遍历”的思想交换左右子树
void SwapLRtreePre(BiTNode* T){
    BiTNode* temp;
    if(T==NULL){
        return;
    }
    temp=T->lchild;
    T->lchild=T->rchild;
    T->rchild=temp;
    SwapLRtreePre(T->lchild);
    SwapLRtreePre(T->rchild);
}

//以“后序遍历”的思想交换左右子树
void SwapLRtreePost(BiTNode* T){
    BiTNode* temp;
    if(T==NULL){
        return;
    }
    SwapLRtreePost(T->lchild);
    SwapLRtreePost(T->rchild);
    temp=T->lchild;
    T->lchild=T->rchild;
    T->rchild=temp;
}

//----------------------------------------------------------

void Jundge(BiTNode* T){//判断
    cout<<"---------------"<<endl;
    PreOrder(T);
    cout<<endl;
    InOrder(T);
    cout<<endl;
}

void PreOrder(BiTNode* T){//前序输出交换后的二叉树
    if(T==NULL){
        return;
    }
    cout<<T->data<<endl;
    PreOrder(T->lchild);
    PreOrder(T->rchild);
}

void InOrder(BiTNode* T){//中序输出交换后的二叉树
    if(T==NULL){
        return;
    }
    InOrder(T->lchild);
    cout<<T->data<<endl;
    InOrder(T->rchild);
}