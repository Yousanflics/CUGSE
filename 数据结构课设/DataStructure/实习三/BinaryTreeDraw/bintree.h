#ifndef BINTREE_H
#define BINTREE_H

#endif // BINTREE_H

#include "stack.h"
#include <string.h>

template<class T>
struct BinTreeNode {
    T data;
    BinTreeNode *lChild, *Parent, *rChild;
    BinTreeNode() :lChild(nullptr), rChild(nullptr), Parent(nullptr) {}
    BinTreeNode(T x) :data(x), lChild(nullptr), rChild(nullptr), Parent(nullptr) {}
};



template<class T>
class BinTree {
public:
    typedef void(*visit)(BinTreeNode<T> *);
    BinTree() :root(nullptr) {}
    BinTree(T x) :root(nullptr), Label(x) {}
    int size() { return size(root); }
    int height() { return height(root); }
    BinTreeNode<T> *getroot(){return root;}
    void preorder(visit t) { preorder(t, root); }
    void inorder(visit t) { inorder(t, root); }
    void postorder(visit t) { postorder(t, root); }
    void level(visit t);
    void destory(){postorder(destoryNode);root = nullptr;}
    void CreateBinTree(T *p,int vis,int lit) { CreateBinTree(p,root,vis,lit); }
    void SwapTree() { SwapTree(root); }
    bool Find(T x,visit t) {
        if (Find(x, root,t))return true;
        else return false;
    }
    void GetTree(T *x, T *y){
        destory();
        int xvis=0;
        GetTree(x,y,xvis, root);
    }
protected:
    static void destoryNode(BinTreeNode<T> *subTree){delete subTree;}
    void GetTree(T *x, T *y, int &xvis,BinTreeNode<T> *&subTree);
    bool Find(T x,BinTreeNode<T> *subTree,visit t);
    void CreateBinTree(T *p, BinTreeNode<T> *&subTree,int &vis,int lit);
    int size(BinTreeNode<T> *subTree);
    int height(BinTreeNode<T> *subTree);
    void preorder(visit t, BinTreeNode<T> *subTree);
    void inorder(visit t, BinTreeNode<T> *subTree);
    void postorder(visit t, BinTreeNode<T> *subTree);
    void SwapTree(BinTreeNode<T> *subTree);
    BinTreeNode<T> *root;
    T Label;
};

template<class T>
int BinTree<T>::size(BinTreeNode<T> *subTree) {
    if (!subTree)return 0;
    return 1 + size(subTree->lChild) + size(subTree->rChild);
}

template<class T>
int BinTree<T>::height(BinTreeNode<T> *subTree) {
    if (!subTree)return 0;
    return height(subTree->lChild) > height(subTree->rChild) ? height(subTree->lChild) + 1 : height(subTree->rChild) + 1;//高度。。。。。
}

template<class T>
void BinTree<T>::preorder(visit t, BinTreeNode<T> *subTree) {
    stack<BinTreeNode<T> *> p;
    while (1) {
        while (subTree) {
            t(subTree);
            if (subTree->rChild) {
                p.push(subTree->rChild);
            }
            subTree = subTree->lChild;
        }
        if (p.empty())break;
        subTree = p.top(); p.pop();
    }
}

template<class T>
void BinTree<T>::inorder(visit t, BinTreeNode<T> *subTree) {
    stack<BinTreeNode<T>*> p;
    while (1) {
        if (subTree) {
            p.push(subTree);
            subTree = subTree->lChild;
        }
        else if (!p.empty()) {
            subTree = p.top(); p.pop();
            t(subTree);
            subTree = subTree->rChild;
        }
        else {
            break;
        }
    }
}

template<class T>
void BinTree<T>::postorder(visit t, BinTreeNode<T> *subTree) {//有点不会
    if (subTree) {
        postorder(t, subTree->lChild);
        postorder(t, subTree->rChild);
        t(subTree);
    }
}

template<class T>
void BinTree<T>::CreateBinTree(T *p, BinTreeNode<T> *&subTree,int &vis,int lit) {
    T item;
    if (vis != lit) {
        item = p[vis];
        if (item != Label) {
            subTree = new BinTreeNode<T>(item);
            if (subTree == nullptr) {}
            CreateBinTree(p, subTree->lChild, ++vis, lit);
            CreateBinTree(p, subTree->rChild, ++vis, lit);
        }
        else subTree = nullptr;
    }
}

template<class T>
void BinTree<T>::SwapTree(BinTreeNode<T> *subTree) {
    if (subTree) {
        BinTreeNode<T> *fu = subTree->lChild;
        subTree->lChild = subTree->rChild;
        subTree->rChild = fu;
        SwapTree(subTree->lChild);
        SwapTree(subTree->rChild);
    }
}

template<class T>
bool BinTree<T>::Find(T x,BinTreeNode<T> *subTree,visit t) {
    if (!subTree)return false;
    if (subTree->data == x)return true;
    if (Find(x, subTree->lChild,t)||Find(x,subTree->rChild,t)) {
        t(subTree);
        return true;
    }
    return false;
}

template<class T>
void BinTree<T>::GetTree(T *x, T *y, int &xvis, BinTreeNode<T> *&subTree) {
    int xlit = strlen(x);
    int ylit = strlen(y);
    if (xvis != xlit) {
        int i;
        for (i = 0; i<ylit; i++) {
            if (x[xvis] == y[i])break;
        }
        subTree = new BinTreeNode<T>(x[xvis]);
        if (i) {
            T *ly = new T[i + 1];
            for (int j = 0; j<i; j++) {
                ly[j] = y[j];
            }
            ly[i] = '\0';
            GetTree(x, ly, ++xvis, subTree->lChild);
        }
        if (i != ylit - 1) {
            T *ry = new T[ylit - i];
            for (int j = 0; j<ylit-i-1; j++) {
                ry[j] = y[i+1+j];
            }
            ry[ylit - i - 1] = '\0';
            GetTree(x, ry, ++xvis, subTree->rChild);
        }
    }
}
