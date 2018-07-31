#include <iostream>
using namespace std;
int triangle1(int n){
    if(n >= 1){

        for(int i = 0; i < n; i++){
            cout<<n<<" ";
        }
        cout<<"\n";
        triangle1(n - 1);
    }
}
int triangle2(int n, int m){
    if(m <= n){
        for(int i = 0; i < m; i++){
            cout<<m<<" ";
        }
        cout<<"\n";

    m ++;
    triangle2(n, m);}
}
int main()
{
    int a;
    int n;
    cout<<"请输入你选择的模式(0为倒三角，1为正三角)"<<endl;
    cin>>a;
    switch(a){
        case(0):{
            cout<<"请输入需要输出的行数： "<<endl;
            cin>>n;
            triangle1(n);
            break;
        }
        case(1):{
            cout<<"请输入需要输出的行数： "<<endl;
            cin>>n;
            triangle2(n, 1);
            break;
        }

    }

}
