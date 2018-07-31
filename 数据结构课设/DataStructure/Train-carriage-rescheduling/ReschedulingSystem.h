#ifndef RESCHDEDULINGSTSTEM_HPP
#define RESCHDEDULINGSTSTEM_HPP value

#include <iostream>
#include <stack>
#define MAX_CARRIAGE_NUMBER 12
using namespace std;

class ReschedulingSystem
{
public:
	ReschedulingSystem();
	~ReschedulingSystem();
	
	void read(); //read the information from user
	void rescheduling(); //excute the system
	void print_current();
	void print(); //print the result of rescheduling

private:
    stack<int> rail_in;
    stack<int> rail_out;
    stack<int> *buffer;
    int number_buffers; // the number of buffers using
    int number_carriages; //the total number of carriages
    int step; 
    int count; // the current carriage that should leave
    int from;
    int to;
};

ReschedulingSystem::ReschedulingSystem() {
	buffer = NULL;
	number_buffers = 0;
	step = 0;
	count = 1;
	from = 0;
	to = 0;
}

ReschedulingSystem::~ReschedulingSystem() {
	if (buffer != NULL) {
		delete []buffer;
		buffer = NULL;
	}
}


void ReschedulingSystem::read() {
	int number;
	cout << "How many train carriages do you need to reschedul: ";
	cin >> number_carriages;

	while (number_carriages > MAX_CARRIAGE_NUMBER) {
		cout << "The number of carriages need to be less than " 
			 << MAX_CARRIAGE_NUMBER 
			 << endl;
		cout << "How many train carriages do you need to reschedul: ";
		cin >> number_carriages;	 
	}

	buffer = new stack<int> [number_carriages - 1];

	cout << "Please input the serial number of train carriages." << endl
		 << "--> " ;
	stack<int> tem;
	for (int i = 0; i < number_carriages; ++i) {
		cin >> number;
		rail_in.push(number);
	}

}

void ReschedulingSystem::rescheduling() {
	bool isInBuffers = false;
	print_current();
	while (rail_out.size() != number_carriages) {
		isInBuffers = false;

		//is the current number on the top of the rail
		if (rail_in.size() && rail_in.top() == count) {

			if(!(from == 0 && to == -1)){
				if(to != 0)
					print_current();
				from = 0;
				to = -1;
				step++;
			}

			rail_out.push(rail_in.top());
			rail_in.pop();

			++count;
		} else {
			//is the current number in one of the tops of buffers
			for (int i = 0; i < number_buffers; ++i) {
				if (buffer[i].size() && buffer[i].top() == count) {

					if(!(from == i + 1 && to == -1)){
						from = i + 1;
						to = -1;
						print_current();
						step++;
					}

					rail_out.push(buffer[i].top());
					buffer[i].pop();

					++count;

					isInBuffers = true;
					break;
				}
			}

			//put the top of the rail to the buffers
			if (!isInBuffers) {
				bool openNewBuffer = true; //do it need to open a new buffer rail
				for (int i = 0; i < number_buffers; ++i) {
					if (rail_in.top() && buffer[i].size() 
						&& rail_in.top() < buffer[i].top()) {

						if(!(from == 0 && to == (i + 1))){
							print_current();
							step++;
							from = 0;
							to = i + 1;
							
						}

						buffer[i].push(rail_in.top());
						rail_in.pop();

						openNewBuffer = false;
						break;
					}

					if (buffer[i].size() == 0) {
						if(!(from == 0 && to == (i + 1))){
							print_current();
							from = 0;
							to = i + 1;
							step++;
						}


						buffer[i].push(rail_in.top());
						rail_in.pop();

						openNewBuffer = false;
						break;						
					}
				}	

				//open a new buffer rail
				if (openNewBuffer) {
					if(!(from == 0 && to == number_buffers + 1)){
						if(to != 0){
							print_current();
						}
						from = 0;
						to = number_buffers + 1;
						step++;
					}
					buffer[number_buffers].push(rail_in.top());
					rail_in.pop();

					++number_buffers;
				}
			}
		}
	}
	print_current();
}

void ReschedulingSystem::print() {
	cout << endl
		 << number_buffers << " buffers is used" << endl
		 << step << " steps totally" << endl;


}
void ReschedulingSystem::print_current(){
	cout << "step" << step << endl;
	if(rail_in.empty()){
		cout << "rail_in : none";
	}
	else{
		cout << "rail_in : ";
		stack<int> tem1(rail_in);
		stack<int> tem;
		while(!tem1.empty()){	
			tem.push(tem1.top());
			tem1.pop();
		}
		while(!tem.empty()){
			cout << tem.top() << ' ';
			tem.pop();
		}
	}
	cout << endl;
	for(int i = 0; i < number_buffers; i++){
		cout << "stack" << i + 1 << " : ";
		if(buffer[i].empty()){
			cout << "none";
		}
		else{
			stack<int> tem1(buffer[i]);
			stack<int> tem;
			while(!tem1.empty()){	
			tem.push(tem1.top());
			tem1.pop();
			}
			while(!tem.empty()){
				cout << tem.top() << ' ';
				tem.pop();
			}
		}
		cout << endl;
	}
	cout << "rail_out : ";
	if(rail_out.empty()){
		cout << "none";
	}
	else{
		stack<int> tem1(rail_out);
		stack<int> tem;
		while(!tem1.empty()){	
			tem.push(tem1.top());
			tem1.pop();
		}
		while(!tem.empty()){
			cout << tem.top() << ' ';
			tem.pop();
		}
	}
	cout << endl;
	cout << "-----------------------------" << endl;
}
#endif