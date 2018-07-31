#include <iostream>

#include <algorithm>

#include <cstdio>

using namespace std;



const int MAX_SIZE = 10005;



bool cmp(int a, int b)

{

    if(a < b)

        return true;

    else

        return false;

}



int cal(int str[], int n)

{

    int sum = 0,i,mid;

    mid = str[n / 2];

    for(i = 0; i < n; i++)

        sum += abs(str[i] - mid);

    return sum;

}



int main()

{

    int x[MAX_SIZE],y[MAX_SIZE];

    int i, n;

    while(scanf("%d",&n) != EOF)

    {

        for(i = 0; i < n; i++)

            scanf("%d%d",x + i, y + i);

        sort(x ,x + n, cmp);// 升序排列

        sort(y, y + n, cmp);

        printf("%d\n",cal(x, n) + cal(y, n));

    }

    return 0;

}
