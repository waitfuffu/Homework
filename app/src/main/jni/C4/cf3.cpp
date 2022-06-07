#include <iostream>
#include <iomanip>
#include <string.h>
using namespace std;

class NUM {
	public :
		NUM();
		void fun();
		void disPlay();
	private :
		int a[50];
		int c ;
};

NUM::NUM() {
	this->c = 0;
}
void NUM::fun() {
	for (int i = 1; i <= 100; i++) {
		if ((i % 7 == 0 || i % 9 == 0) && !(i % 7 == 0 && i % 9 == 0)) {
			//存入
			a[c] = i;
			c++;
		}
	}
}
void NUM::disPlay() {
	fun();
	int n = 0 ;
	for (int i = 0; i <c; i++, n++) {
		if (a[i] > 100) {
			break;
		}
		if (n == 5) {
			cout << endl;
			n = 0;
		}
		cout << a[i]  << "  ";
	}
}
int main() {
	NUM *test = new NUM();
	test->disPlay();
}


