# include <iostream>
# include <cstring>
# include <ctype.h>

int main()
{
    char * s = "abc3245 345fdf678 jdhfg945";
    char *p = s;
    char *end = s + strlen(s);
    int a[32], i, n = 0, m;
    char buffer[80];
    while (p < end)
    {
        if (isdigit(*p))
        {
            sscanf(p, "%d", &a[n]);
            m = sprintf(buffer, "%d", a[n]);
            if (m > 1)
            {
                p += strlen(buffer);
                n++;
            }
            else
            {
                p++;
            }
        }
        else
        {
            p++;
        }
    }
    printf("%d\n", n);
    for (i = 0; i < n; i++)
    {
        printf("%d\n", a[i]);
    }

    return 0;
}
