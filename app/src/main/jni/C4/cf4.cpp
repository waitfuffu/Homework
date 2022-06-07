#include <iostream>
#include <iomanip>
#include <string.h>
using namespace std;

class WORD {
	public :
		WORD(char *str);
		void fun();
		void disp();
	private:
		char s[80];
		int c ;
};

WORD::WORD(char *str) {
	c = 0;
	for (int i = 0;; i++) {
		if (*(str + i) == '\0') {
			break;
		}
		s[i] = *(str + i);
	}
}
void WORD::fun() {
	for (int i = 0;; i++) {
		if (s[i] == ' ') {
			c++;
		}
		if (s[i] == '\0') {
			break;
		}
	}
}
void WORD::disp() {
	fun();
	cout << "单词个数为：" << c + 1 << endl;
	for (int i = 0;; i++) {
		if (s[i] == '\0') {
			break;
		}
		cout << s[i];
	}

}
int main() {
	char str[] = "shut the door after you.";
	WORD *w = new WORD(str);
	w->disp();
}

