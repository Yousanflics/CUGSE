#include "stdafx.h"
#include <fstream>
using namespace std;
struct Wood{
	int length;
	int weight;
	bool b;
	Wood():b(false){};
	bool operator <=(Wood w){
		if(length<=w.length&&weight<=w.weight)
			return true;
		else
			return false;
	}
	bool operator ==(Wood w){
		if(length==w.length&&weight==w.weight)
			return true;
		else
			return false;
	}
	bool operator !=(Wood w){
		if(length!=w.length||weight!=w.weight)
			return true;
		else
			return false;
	}
	void operator =(Wood w){
		length=w.length;
		weight=w.weight;
	}
};



void Swap(Wood &w1,Wood &w2){
	Wood w;
	w=w1;
	w1=w2;
	w2=w;
};



void IsLike(Wood w[],int n,bool b){
	int i=0;
	int j=i+1;
	if(b==false){    
		while(i<n){
			if(w[i].length==w[i+1].length)
				if(w[i].weight>w[i+1].weight){
					Swap(w[i],w[i+1]);
					i=0;
				};
			i++;
		}
	}
	else{    
		while(i<n){
			if(w[i].weight==w[i+1].weight)
				if(w[i].length>w[i+1].length){
					Swap(w[i],w[i+1]);
					i=0;
				};
			i++;
		}
	}
};



void LengthSort(Wood w[],int l,int r){
	if(l>=r)
		return;
	int i=l;    
	int j=r+1;   
	Wood pivot=w[l];
	while(true){
		do{      
			i+=1;
		}while(w[i].length<pivot.length&&i<r);
		do{     
			j-=1;
		}while(w[j].length>pivot.length&&j>l);
		if(i>=j)break;
		Swap(w[i],w[j]);     
	}
	
   w[l]=w[j];
   w[j]= pivot;   
   LengthSort(w,l,j-1);   
   LengthSort(w,j+1,r);    
};


void WSort(Wood w[],int l,int r){
	if(l>=r)
		return;
	int i=l;    
	int j=r+1;   
	Wood pivot=w[l];
	while(true){
		do{      
			i+=1;
		}while(w[i].weight<pivot.weight&&i<r);
		do{     
			j-=1;
		}while(w[j].weight>pivot.weight&&j>l);
		if(i>=j)break;
		Swap(w[i],w[j]);     
	}
	
   w[l]=w[j];
   w[j]= pivot;   
   WSort(w,l,j-1);     
   WSort(w,j+1,r);    
};



void WeightSort(Wood w[],Wood w1[],Wood w2[],int n){   
	int i=0;
	int j=0;
	int k=0;
	int x=0;
	int count1=0;
	while(x<n){
		if(i>=n){
			j=0;
			k=0;
			while(w1[k].b==true)    
				k++;
			i=k;   
		}
		while(w1[i].b==true)
			i++;
		while(w2[j]!=w1[i]&&j<n-1)
			j++;
		if(w2[j]==w1[i]){
			count1=j+1;  
			w1[i].b=true;
			w[x]=w2[j];
		    x++;
		    i++;
		}
		else{   
			j=count1;
			i++;
		}
	}
};



int WTime(Wood w[],int n){
	int i=1;
	int j=0;
	while(j<n-1){
		if(w[j]<=w[j+1])
			j++;
		else{
			i++;
			j++;
		}
	}
	return i;
};



void OutPut(Wood w[],int n,ofstream &os){
	int t=WTime(w,n);
	os<<t<<"\n";
	int i=0;
	while(i<n){
		os<<"("<<w[i].length<<","<<w[i].weight<<") ";
		i++;
	}
	os<<"\n";
};



void Creat(CString cfile){
	ofstream os("���.txt");
	CStdioFile file;
	file.Open(cfile,CFile::modeRead);
	CString cs;
	file.ReadString(cs);

	int pos=0;
	int i=_ttoi(cs.Tokenize(" ",pos));  
	while(file.ReadString(cs)){
	    pos=0;
	    int count=_ttoi(cs.Tokenize(" ",pos));  
		Wood *wood=new Wood[count+1];
		pos=0;
		file.ReadString(cs);
		int j=0;
		while(j<count){      
			wood[j].length=_ttoi(cs.Tokenize(" ",pos));
			wood[j].weight=_ttoi(cs.Tokenize(" ",pos));
			j++;
		}    
		Wood *w1=new Wood[count+1]; 
		Wood *w2=new Wood[count+1];   
		int c=0;
		while(c<count){
			w1[c]=wood[c];
			w2[c]=wood[c];
			c++;
		}
		LengthSort(w1,0,count-1);
		WSort(w2,0,count-1);
		IsLike(w2,count,true);
		WeightSort(wood,w1,w2,count);
	    OutPut(wood,count,os);
		delete []w1;
		delete []w2;
	    delete []wood;
	}
};
