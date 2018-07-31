#ifndef HUFFTREE_H
#define HUFFTREE_H
#include <QMap>
#include <QList>
#include <QString>
struct  Node
{
    Node *left;
    Node *right;
    int count;  //huffman key value
    char c;     //huffman key. Only leaf node is valid.
};//node to construct binary tree.

class HuffTree
{
public:
    HuffTree();
    void Generate(QMap<char,int> &map, QMap<char, QByteArray> &codeMap);
private:
    Node* FindMin(QList<Node *> &list);
    void GenerateCode(Node *node, QByteArray s, QMap<char, QByteArray> &codeMap);
};

#endif // HUFFTREE_H
