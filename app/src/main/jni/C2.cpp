#include <iostream>
#include <iomanip>
#include <string.h>
using namespace std;
//使用前 方法进行声明
int med1(int n, int arr[n]);
int med2();
void med3(int arr[10]);
void med4Trans(int n, int base);
int med5(int a, int b);
int med5(int a, int b, int c);
int med6Reverse(unsigned int s);
int med3Count = 0;

void med7(int n = 1, int sum = 0) { //形参赋默认值
	//第一次 n =1 ,sum =0
	if (sum > 1000) {
		cout << n - 1 ;
		return ;
	}
	sum = sum + (n * 10 + 2);
	n++;
	med7(n, sum);
}

int main() {

	/*  //med1
	    int arr[5] ={1,4,6,9,2};
		int a =med1(5,arr);

	*/

	/*	//med2
		int avg =med2();
		cout << avg ;
	*/

	/*	//med3
		int a[10] ={0,1,2,3,4,5,6,7,8,9};
		med3(a);
	*/
	/*	//med4
		med4Trans(30,2);
	*/
	/*	//med5
		cout << med5(4,7)<<endl;
		cout << med5(2,3,4);
	*/

	/*	//med6
		cout << med6Reverse(1234);
	*/

	med7();
	return 0 ;
}

int med1(int n, int arr[n]) {
//	统计出具有n个元素的一维整数数组中大于等于所有元素平均值的元素个数
	int count = 0;
	int sum = 0, avg = 0;
	for (int i = 0; i < n; i++) {
		sum += arr[i];
	}
	avg = sum / n;
	for (int i = 0; i < n; i++) {
		if (arr[i] >= avg) {
			count++;
		}
	}
	return count;
}

int med2() {
	int arr[3][4];
	int sum = 0, avg = 0, temp = 0, count = 0;
	for (int i = 0; i <= 2; i++) {
		for (int j = 0; j <= 3; j++) {
			cin >> temp;
			arr[i][j] = temp;
			sum += temp;
			count++;
		}
	}
	avg = sum / count;
	return avg;
}


void med3(int arr[10]) {
	//递归9次即可
	if (med3Count == 9) {
		return ;
	}
	for (int i = 9; i <= 1; i++) {
		if (arr[i] > arr[i - 1]) {
			int temp = arr[i];
			arr[i] = arr[i - 1];
			arr[i - 1] = temp;
		}
	}
	med3(arr);//递归
}


void med4Trans(int n, int base) {
	int r[100];
	int i, j = 0;

	//对十进制数进行除以二取余并以此存放到列表中
	for ( i = 0; n > 0; i++) {
		r[i] = n % base;
		n = n / base;
		j++;
	}

	//依次取出从列表的最后一位到第一位余数
	cout << "转为二进制为：" << endl;
	for (j = j - 1; j >= 0; j--) {
		cout << r[j];
	}

}


int med5(int a, int b) {
	if (a > b) return a ;
	else return b ;
}


int med5(int a, int b, int c) {
	if (a > b) {
		if (a > c) return a ;
		else return c ;
	} else {
		if (b > c) return b;
		else return c;
	}
}

int med6Reverse(unsigned int s) {
	int a, b, c, d, result;
	//1234
	a = s / 1000;
	b = (s / 100) % 10;
	c = (s / 10) % 10;
	d = s % 10;
	result = d * 1000 + c * 100 + b * 10 + a;
	return result;
}


