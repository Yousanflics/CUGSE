#include <iostream>
using namespace std;

//这里我们用最大堆，下面的提到的堆都是最大堆
//堆排序的关键是堆的操作：
//维护堆的操作：堆顶元素发生变化时，不断地将较小的元素“逐级下降”，使得堆顶永远是最大元素
//建堆的操作：将一个无序的数组不断地通过“维护堆的操作”建立一个堆。

//维护堆的操作
void max_heapfiy(int* a, int top_index, int heap_size)
{
	int left_index = top_index * 2 + 1;//左孩子的位置
	int right_index = top_index * 2 + 2;//右孩子的位置
	int largest_index = top_index;//记录最大元素的位置
	if (left_index <= heap_size && a[left_index] > a[largest_index])
	{
		largest_index = left_index;
	}
	if (right_index <= heap_size && a[right_index] > a[largest_index])
	{
		largest_index = right_index;
	}
	if (largest_index != top_index)
	{
		int temp = a[top_index];
		a[top_index] = a[largest_index];
		a[largest_index] = temp;
		max_heapfiy(a, largest_index, heap_size);
	}
}

//建堆的操作
void build_heap(int* a, int heap_size)
{
	//我们只需要对所有非叶子结点不断地进行“维护堆的操作”
	int index = (heap_size - 1) / 2;//最后一个非叶子结点的位置
	for (; index >= 0; index--)
		max_heapfiy(a, index, heap_size);
}

void heap_sort(int* a, int length)
{
	int heap_size = length - 1;
	//建堆
	build_heap(a, heap_size);
	//排序
	for (int i = 0; i < length; i++)
	{
		//提取堆顶元素，插入到有序部分
		int temp = a[0];
		a[0] = a[length - i - 1];
		a[length - i - 1] = temp;
		//提取完后，进行“维护堆的操作”
		max_heapfiy(a, 0, --heap_size);
	}
}
int main()
{
	int a[] = {
		454, 56, 454, 98, 5, -79879, 2, 2, 5, 45, 5, 4, 64, 5, 4646456, -565, 6, 8, 4, 0, 2
	};
	int length = sizeof(a) / sizeof(int);
	cout << "未排序的数组：" << endl;
	for (int i = 0; i < length; i++)
		cout << a[i] << " ";
	cout << endl;

	heap_sort(a, length);

	cout << "排序后的数组：" << endl;
	for (int i = 0; i < length; i++)
		cout << a[i] << " ";
	cout << endl;
	system("pause");

}
