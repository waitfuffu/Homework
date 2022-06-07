//
// Created by waitfuffu on 2022/5/19.
//

#include <iostream>
#include <iomanip>
#include <string.h>
using namespace std;
void med1(int number);
void med1For(int num);
int m2Strlen(char *s);
void m3Strcpy(char *s1);
void m4Reverse(char *s);
void med5();

int main() {

	/*	//C1 数字转英文
			med1(122);
	*/

	/*  //C2 求字符串长度
		char s[] = "qwertyyyu";
		cout<<m2Strlen(s);
	*/

	/*	//C3 拷贝字符串
		char s[] = "qwerrr";
		m3Strcpy(s);
	*/


	/*	//C4 反转字符串
		char s[] = "qwerrr";
		m4Reverse(s);
	*/
	/*	//C5 正整数是否左右对称
		med5();
	*/
	med5();
	return 0 ;
}

void med1(int number){
	int a = number/	100;
	int b = (number/10)%10;
	int c = number%10;
	med1For(a);
	cout <<"  " <<"hundred"<<" "<<"and  " ;
	med1For(b);
	cout <<"  " ;
	med1For(c);
}

void med1For(int num){
	char med1arr[10][7] = {"zero","one","two","three","four","five","six","seven","eight","nine"};
	char *p ;
	p= med1arr[num];

	for(;*p!='\0';p++){
		cout << *p ;
	}
}


int m2Strlen(char *s){
	int n = 0;
	for(;*s!='\0';s++,n++){
		cout << *s ;
	}
	cout<<endl;
	return n;
}

void m3Strcpy(char *s1){

	char ss2[m2Strlen(s1)+1];
	char *s2 = ss2;
	for(;*s1!='0';s1++,s2++){
		cout << *s1;
		*s2 = *s1;
	}
	cout<<endl;
	cout <<"s2 :";
	for(int i=0;i<m2Strlen(s1);i++){
		cout << ss2[i];
	}
}

void m4Reverse(char *s){
   int n = m2Strlen(s);
 //m2Strlen(s) 方法会打印一遍原始字符组
	char temp ,*p,*i,*j;
	i = s;
	p=s+((n-1)/2);
	j = s+n-1;
	for(;i<=p;i++,j--){
		temp = *i;
		*i = *j ;
		*j = temp;
	}

	cout << endl;
	for(int i=0;i<n;i++){
		cout << s[i];
	}
}

void med5(){
	int a, b;
	int c=0;
	while(1)
	{
		cin >> a ;
		b=a;
		while(a!=0)
		{
			c = c*10 + a%10;
			a =a/ 10;
		}
		if(b == c)
			cout <<"yes" <<endl;
		else
			cout <<"no" <<endl;
		c=0;
	}

}