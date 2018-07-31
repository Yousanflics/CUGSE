//
//	Project_1 Train carriage rescheduling
//	2017/9/22 
//
//


#include <iostream>
#include "ReschedulingSystem.hpp"
using namespace std;

int main() {

	ReschedulingSystem system;

	system.read();
	system.rescheduling();
	system.print();


	return 0;
}