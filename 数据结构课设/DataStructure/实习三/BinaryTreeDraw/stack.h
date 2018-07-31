#ifndef STACK_H
#define STACK_H

#endif // STACK_H

template<class T>
struct node {
    T data;
    node<T> *link;
    node() : link(nullptr) {}
    node(T x) :data(x), link(nullptr) {}
    node(node<T> &x) { data = x.data; link = x.link; }
};

template<class T>
class stack {
public:
    stack() { first = new node<T>(); }
    ~stack() { clear(); delete first; }
    void push(T &x);
    void pop();
    T top();
    void clear();
    bool empty();
private:
    node<T> *first;
};

template<class T>
void stack<T>::push(T &x) {
    node<T> *newnode = new node<T>(x);
    newnode->link = first->link;
    first->link = newnode;
}

template<class T>
void stack<T>::pop() {
    if (first->link != nullptr) {
        node<T> *rec = first->link;
        first->link = rec->link;
        delete rec;
    }
}

template<class T>
T stack<T>::top() {
    return first->link->data;
}

template<class T>
void stack<T>::clear() {
    while (first->link != nullptr) {
        pop();
    }
}

template<class T>
bool stack<T>::empty() {
    return !first->link;
}
