#include <iostream>
#include <stack>
using namespace std;
stack<int> s;
int fib(int n)
{
if(n = 0)
  return 0;
if(n == 1 || n == 2)
return 1;
else
{
int i = 2;
int tmp1,tmp2;
while(i++ < n)
{
tmp1 = s.top();
s.pop();
tmp2 = s.top();
s.push(tmp1);
s.push(tmp1+tmp2);
}
return s.top();
}
}
int main()
{
s.push(1);
s.push(1);
int n;
cin>>n;
cout<<fib(n)<<endl;
return 0;
}
