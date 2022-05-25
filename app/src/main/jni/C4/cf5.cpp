#include <iostream>
#include <iomanip>
#include <string.h>
using namespace std;

class CRectangle {
	public :
		CRectangle(int x1, int y1, int x2, int y2);
		bool IsSquare();
		void PrintRectangle();
		int Area();
		void Move(int x, int y);

	private :
		int x1, y1, x2, y2;

};

CRectangle::CRectangle(int x1, int y1, int x2, int y2) {
	if (x1 == x2 || y1 == y2) {
		this->x1 = 0;
		this->y1 = 0;
		this->x2 = 1;
		this->y2 = 1;
	} else {
		this->x1 = x1;
		this->y1 = y1;
		this->x2 = x2;
		this->y2 = y2;
	}
}

bool CRectangle::IsSquare() {
	if (x2 - x1 == y2 - y1) {
		return 1;
	} else {
		return 0;
	}
}

void CRectangle::PrintRectangle() {
	//打印四点坐标
	cout << "(" << x1 << "," << y1 << ")" << endl;
	cout << "(" << x2 << "," << y1 << ")" << endl;
	cout << "(" << x2 << "," << y2 << ")" << endl;
	cout << "(" << x1 << "," << y2 << ")" << endl;
}

int CRectangle::Area() {
	return (x2 - x1) * (y2 - y1);
}

void CRectangle::Move(int x, int y) {
	x1 = x1 + x;
	x2 = x2 + x;
	y1 = y1 + y;
	y2 = y2 + y;
}

int main() {
	CRectangle *c = new CRectangle(1, 2, 3, 6);
	cout << c->Area() << endl;
	cout << c->IsSquare() << endl;
}
