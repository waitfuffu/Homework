#include <iostream>
#include <iomanip>
#include <string.h>
using namespace std;

class TDate {
	public :
		friend void disPlay(TDate *t);
		//默认参数值
		TDate(int y = 0, int m = 0, int d = 0);
		TDate() {
		}

	private:
		int Month, Day, Year ;
};

TDate ::TDate(int y, int m, int d) {
	this->Year = y;
	this->Month = m;
	this->Day = d;
}

void disPlay(TDate *t) {
	cout << t->Year << t->Month << t->Day;
}


int main() {
	TDate *t = new TDate(2022, 5, 25);
	disPlay(t);
}
