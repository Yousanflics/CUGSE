#include "hufftree.h"

HuffTree::HuffTree()
{
}

void HuffTree::Generate(QMap<char, int> &map, QMap<char, QByteArray> &codeMap)
{
    QMap<char,int>::Iterator it;
    QList<Node *> list;
    Node * node;
    for(it=map.begin();it!=map.end();it++)
    {//initial all nodes with statistics info
        node=new Node();
        node->left=0;
        node->right=0;
        node->count=it.value();
        node->c=it.key();
        list.append(node);
    }
    Node *min1,*min2;
    while(list.size()>1)
    {//combine two minest nodes into one tree
        min1=this->FindMin(list);
        min2=this->FindMin(list);
        node =new Node();
        node->left=min1;
        node->right=min2;
        node->count=min1->count+min2->count;
        list.append(node);
    }
    codeMap.clear();
    this->GenerateCode(node,"",codeMap);
}
void HuffTree::GenerateCode(Node *node, QByteArray s, QMap<char, QByteArray> &codeMap)
{
    if(node->left==0)
    {
        codeMap.insert(node->c,s);
    }
    else
    {
        GenerateCode(node->left,s+"0",codeMap);
        GenerateCode(node->right,s+"1",codeMap);
    }
}

Node* HuffTree::FindMin(QList<Node *> &list)
{
    Node * min;
    int index=0;
    min=list.first();
    for(int i=1;i<list.size();i++)
    {
        if(min->count>list[i]->count)
        {
            min=list[i];
            index=i;
        }
    }
    list.removeAt(index);
    return min;
}
