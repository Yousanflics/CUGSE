#include<cstdio>
#include<iostream>
using namespace std;
char c[5011];
int f[5001][5001];

inline int dp(int x,int y)  //x is from left side, y is from right side
{
    if(f[x][y]!=0)
        return f[x][y];
    if(x>=y)
        return 0;
    if(c[x]!=c[y]){
        f[x][y]=min(dp(x,y-1),dp(x+1,y))+1;} //take Dynamic programming into consideration
    else
        f[x][y]=dp(x+1,y-1);
    return f[x][y];
}
int main()
{
     int n;
     scanf("%d\n",&n);
     for(int i=1;i<=n;i++) {
         scanf("%c",&c[i]);
     }
     printf("%d\n",dp(1,n));
}
