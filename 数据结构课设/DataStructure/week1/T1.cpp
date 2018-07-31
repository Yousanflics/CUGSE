#include <assert.h>
#define MAX_SIZE 10
typedef int DataType;
typedef  struct SeqList{
    DataType array[MAX_SIZE];
    int size;
}SeqList, *PSeqList;

int Empty(PSeqList seq)     //判满
{
    if(seq->size > MAX_SIZE)
        return 1;
    else  return 0;
}

void Inverse(PSeqList seq){
    int i = 0;
    int temp = 0;
    assert(seq);
    if(Empty(seq)){
        return;
    }
    for(i = 0; i < (seq->size)/2; i++){  //反转从中间开始
        temp = seq->array[i];
        seq->array[i] = seq->array[seq->size-1-i]; //关键
        seq->array[seq->size-1-i] = temp;
    }
}
