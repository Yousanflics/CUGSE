#include <iostream>
#define MAX 20001
#define MAXLEN 99999999999
using namespace std;
int min(int,int);

int main()
{
    cout<<"请输入硬币的种类"<<endl;
    int n;                          // 输入 硬币种数并检测
    cin>>n;
    if(n<1 || n>10)
        cout<<"您输入的币种不符合范围，请在1～10中进行选择"<<endl;
        return 0;

    int *t = new int[n+1];          // t[n+1]存放各种硬币的面值
    int *coins = new int[n+1];      // coins[n+1]存放各种硬币的个数

    int i,j,k;                      // i,j,k为循环变量
    for(i=1;i<n+1;i++)              // 输入 各种硬币面值t[i] 各种硬币个数coins[i]
    {
        cin>>t[i]>>coins[i];
    }


    int m, dp[20002];               // dp[20002]存放一系列子找零问题的结果
    cin>>m;
    for(i=1;i<m+1;i++)              // 因为要取最小，因此要初始化为(大大地 ：drastically)数
        dp[i] = MAXLEN;

    dp[0] = 0;

    for(i=1;i<=n;i++)               // 各种硬币
        for(j=1;j<=coins[i];j++)    // 各种硬币的个数
            for(k=m;k>=t[i];k--)
            {                       // 动态转移方程
                dp[k] = min(dp[k-t[i]]+1,dp[k]);
            }

    cout<<dp[m]<<endl;              // dp[m]即为所求结果
    return 0;
}
int min(int a,int b)
{
    return (a<b?a:b);
}
