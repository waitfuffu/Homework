#include <iostream>
#include <iomanip>
using namespace std;

void med1() {
	//1）输入一个正整数，判断这个数是否为素数？
	//除了1和本身 不能有其他因数
	int m ;
	cout << "请输入一个正整数>1" << '\n';
	cin >> m ;
	if (m > 1) {
		for (int i = 2; i < m - 1; i++) {
			if (m % i == 0) {
				if (i < m) {
					cout << "false";
					break;
				} else cout << "true";
			}
		}
	} else cout << "error";
}


void  med2() {
	//2找出1~1000内的所有素数
	for (int i = 1; i <= 1000; i++) {
		if (i > 1) {
			if (i == 2) {
				cout << i << '\n';
			}
			for (int j = 2; j <= i - 1; j++) {
				if (i % j == 0) {
					if (j < i) {
						break;
					}
				} else {
					cout << i << '\n';
					break;
				}
			}
		}
	}
}

void med3() {
	//3输入两个数，计算最大公约数？
	int m, n, k;
	cin >> m;
	cin >> n;
	k = m % n;
	while (k != 0) {
		m = n;
		n = k;
		k = m % n;
	}
	cout << n ;
}

void med4() {
	//计算1~100之间的偶数和
	int s = 0;
	for (int i = 1; i < 100; i++) {
		if (i % 2 == 0) {
			s = s + i;
		}
	}
	cout << s ;
}

void med5() {
	//med1 同题
}

void med6(int n) {
	//输入一个正整数，将其转换为二进制显示
//int n ;
//cin >> n ;	将n作为参数传入
	int in = n / 2;
	int out = n % 2;
	if (n == 0) {
		return;
	}
	med6(in);//进行递归
	cout << out ;
}



void med7() {
	//7找出1~1000之间的所有水仙花数
	int m, a, b, c;
	m = 100;
	while (m < 1000) {
		a = m / 100;
		b = m / 10 % 10;
		c = m % 10;
		if (m == a * a * a + b * b * b + c * c * c) {
			cout << m << '\n';
		}
		m++;
	}
}

void med8() {
	//8输入三个数，按照从小到大排序
	int a, b, c;
	cin >> a >> b >> c ;
	if (a > b) {
		if (c > a) {
			cout << c;
		} else cout << a;
	} else {
		if (c > b) {
			cout << c;
		} else cout << b;
	}
}


void med9() {
	//*每次+2   空格每次-1
	//最多11*     5空格
	int m = 5; //空格数，每次-1
	int n = 1;	//每次+2

	while (m >= 0) {
		for (int i = 1; i <= m; i++) {
			cout << " ";
		}
		for (int i = 1; i <= n; i++) {
			cout << "*";
		}
		cout << '\n';
		m--;
		n += 2;
	}
}


void med10() {
	//输入年、月、日，判断是当年的第几天
	int y, m, d, sumDay;
	cin >> y >> m >> d;
	if (m < 1 || m > 12 || d > 30 || d < 1) {
		cout << "err";
	} else {
		sumDay = m * 30 + d;
		cout << y << "年" << m << "月" << d << "日" << "为当年的第" << sumDay << "天";
	}

}
int main() {
	//med1
	//med2
	//med3
	//med4
	//med5
	//med6(100);
	//med7();
	//med8();
	//med9();
	//med10();
	return 0 ;
}


