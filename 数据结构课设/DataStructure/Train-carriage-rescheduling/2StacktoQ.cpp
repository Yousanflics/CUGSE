//我们都知道队列是一种 先进先出(FIFO) 的数据结构， 栈是一种 后进先出(LIFO) 的数据结构，
//要用两个栈实现一个队列，可以这样设想：
//假设两个栈 stack1和stack2，最初的时候，stack1,stack2都为空栈。
//假设栈 stack1 为提供入队列的功能，栈 stack2 提供出队列的功能。
//入队列：
//1，如果栈 stack2 为空，直接将元素压入栈stack1 。
//2，如果栈 stack2 不为空，首先依次弹出栈 stack2 的元素，压入栈 stack1 中，再将要压入的元素压入栈 a 。
//出队列：
//1，如果栈 stack1 为空，直接弹出栈 stack2 的栈顶元素（如果栈b也为空，抛出异常）。
//2，如果栈 stack1不为空，则依次弹出栈 stack1 的元素，放入栈 stack2 中，再弹出栈 stack2 的栈顶元素
#include <iostream>  
#include <stack>  
  
using namespace std;  
  
template<class T>  
class CQueue  
{  
public:  
    T& push(T&);  
    T& front();  
    void pop();  
private:  
    stack<T*> m_stack1;  
    stack<T*> m_stack2;  
};  
  
template<class T>  
T& CQueue<T>:: push(T& t)  
{  
    if(m_stack2.empty())
    {
        m_stack1.push(&t); 
    }
}  
template<class T>  
T& CQueue<T>:: front()  //进队列
{  
    if (m_stack2.empty())  
    {  
        while (!m_stack1.empty())  
        {  
            m_stack2.push(m_stack1.top());  
            m_stack1.pop();  
        }  
    }  
    T* t = new T();  
    t = m_stack2.top();  //这个时候的输出是从stack1中辗转出来的
    return *t;  //返回的是一整个队列的地址也就是T类型的
}  
template<class T>  
void CQueue<T>:: pop()  //出队列
{  
    if (m_stack2.empty())  
    {  
        while (!m_stack1.empty())  
        {  
            m_stack2.push(m_stack1.top());  
            m_stack1.pop();  
        }  
    }  
    return m_stack2.pop(); //这个时候直接输出顶层的元素就是Q里面的FIFO 
}  
int main()  
{  
    CQueue<int> CQ;  
    int a = 1, b = 2, c = 3, d = 4;
    CQ.push(a);  
    CQ.push(b);  
    CQ.push(c);
    CQ.push(d);  
    cout << CQ.front() << "  ";

    //CQ.pop();  
    //cout << CQ.front() << "  ";  
    return 0;  
}  