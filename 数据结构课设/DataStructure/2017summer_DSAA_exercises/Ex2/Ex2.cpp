#include <iostream>
#include <cstdio>
#include <cstring>
int findStr(char *str, char *substr) //比较函数，参数是字符串指针
{
    int  n = 0;
    char  *p, *r;
    while (*str)  //
    {
        p = str;
        r = substr;
        while (*r)
            if (*r == *p)//从str字符串的左侧第一字母比较，如果相同继续下一个字母
            {
                p++;
                r++;
            }
            else
            {
                break;
            }
        if (*r == '\0')//比较到substr的结尾，相同则继续下一次
            n++;
        str++;
    }
    return n;
}

int main()
{
    char str[81], substr[10];
    int n;
    printf("输入原字符串:");
    gets(str);//输入字符串str
    printf("输入子字符串(字串的数目务必大于2):");
    gets(substr);//输入字符串substr
    //puts(str);//输出字符串
    //puts(substr);//输出字符串
     printf("n=%d\n", findStr(str, substr));       //比较字符串并返回次数

}
