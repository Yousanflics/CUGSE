#include <iostream>
using namespace std;

template<typename T>
struct BiTreeNode
{
    T data;
    BiTreeNode *Lchild, *Rchild;
    BiTreeNode() :Lchild(NULL), Rchild(NULL){}//构造函数将左右都指向NULL
    BiTreeNode(const T& d, BiTreeNode *lchild = NULL, BiTreeNode *rchild = NULL) :data(d), Lchild(lchild), Rchild(rchild){}//构造函数初始化各项数据
};

template<typename T>
class BiTree
{
public:
    BiTree(){ /*root = NULL;*/ }
    ~BiTree(){ /*makeEmpty();*/ }
    void makeEmpty(BiTreeNode<T>* &root);
    void createBiTree(BiTreeNode<T>* &root);
    void output(BiTreeNode<T> *root);
    void preOrder(BiTreeNode<T>* &root);//前序
    void inOrder(BiTreeNode<T>* &root);//中序
    void postOrder(BiTreeNode<T>* &root);//后序
};

template<typename T>
void BiTree<T>::createBiTree(BiTreeNode<T>* &root)//前序创建二叉树
{
    char ch;
    cin >> ch;
    if (ch == '#')
    {
        root = NULL;
    }
    else
    {
        root = new BiTreeNode<T>(ch);
        createBiTree(root->Lchild);
        createBiTree(root->Rchild);
    }
}

template<typename T>
void BiTree<T>::output(BiTreeNode<T> *root)//节点输出的实现
{
    if (root != NULL)
    {
        cout << root->data << " ";
        output(root->Lchild);
        output(root->Rchild);
    }
}

template<typename T>
void BiTree<T>::makeEmpty(BiTreeNode<T>* &root)//置空函数实现
{

}

template<typename T>
void BiTree<T>::preOrder(BiTreeNode<T>* &root)//前序排列
{
    if (root)
    {
        cout << root->data << " ";
        preOrder(root->Lchild);
        preOrder(root->Rchild);
    }
}

template<typename T>
void BiTree<T>::inOrder(BiTreeNode<T>* &root)//中序排列
{
    if (root)
    {
        preOrder(root->Lchild);
        cout << root->data << " ";
        preOrder(root->Rchild);
    }
}

template<typename T>
void BiTree<T>::postOrder(BiTreeNode<T>* &root)//后序排列
{
    if (root)
    {
        postOrder(root->Lchild);
        postOrder(root->Rchild);
        cout << root->data << " ";
    }
}


int main()
{
    BiTreeNode<char> * R=NULL;
    BiTree<char> * bt = new BiTree<char>();
    bt->createBiTree(R);
    cout << "输出结果是：" << endl;
    bt->output(R);
    cout << endl;
    cout << "先根遍历：" << endl;
    bt->preOrder(R);
    cout << endl;
    cout << "中根遍历：" << endl;
    bt->inOrder(R);
    cout << endl;
    cout << "后根遍历：" << endl;
    bt->postOrder(R);
    cout << endl;
    while (1)
    {
    }
}
