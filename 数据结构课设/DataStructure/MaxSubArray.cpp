#include <iostream>
using namespace std;
int MaxSub(int *a, int n){
    int ThisSum = 0;
        int MaxSum = 0;
	    for(int i = 0; i < n; i++) {
	            ThisSum += a[i];

		            if (ThisSum > MaxSum) {
			                MaxSum = ThisSum;
					        }
						        else if (ThisSum < 0) {
							            ThisSum = 0;
								            }
									        }
										    return MaxSum;
										    }
										    int main(){
										        int a[8] = {-1,3,-2,4,-6,1,6,-1};
											    cout<<MaxSub(a,8);
											    }
