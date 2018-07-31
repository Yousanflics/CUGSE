//Feray.cpp
//Feray
// Created by Yousanflics on 2017/9/13.
//
#include <fstream>
#include <iostream>
using namespace std;
struct Feray
{
    int a;
    int b;
    Feray *next;
};
int main()
{
    int n,k = 1;
    Feray *head,*p,*t,*p1,*p2;
    cin>>n;
    head = new Feray;
    head->next = NULL;
    t = head;
    p = new Feray;
    p->a = 0;
    p->b = 1;
    p->next = t->next;//add p node
    t->next = p;
    t = p;
    p = new Feray;
    p->a = 1;
    p->b = 1;
    p->next = t->next;
    t->next = p;
    t = p;
    while(k == 1)
    {
        p1 = head->next;
        p2 = p1->next;
        p = NULL;
        while(p2 != NULL)
        {
            if((p1->b + p2->b)<=n)
            {
                p = new Feray;
                p->a = p1->a+p2->a;
                p->b = p1->b+p2->b;
                p->next = p1->next;
                p1->next = p;
            }
            p1 = p2;
            p2 = p2->next;
        }
        if(p == NULL)
            k = 0;
    }
    k = 0;
    p = head->next;
    ofstream outfile;
    outfile.open("result.txt");
    if(outfile.is_open()) {
        while (p != NULL) {
            k++;
            if (k < 10)
                outfile << p->a << "/" << p->b << endl;
            else if (k == 10) {
                outfile << p->a << "/" << p->b << endl;
                k = 0;
            }
            p = p->next;


        }
    }
    else{
        cout<<" Can not open the file"<<endl;
    }
    return 0;

}
