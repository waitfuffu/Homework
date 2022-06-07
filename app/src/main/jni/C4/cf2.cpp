#include <iostream>
#include <iomanip>
#include <string.h>
using namespace std;

class Stu {
	public :
		Stu(int num, char name[10], char sex );
		void disPlay();
	private:
		int num ;
		char name[10] ;
		char sex ;
};
Stu::Stu(int num,	char name[10], char sex ) {
	this->num = num;
	strcpy(this->name, name);	//复制
	this->sex = sex ;
}

void Stu::disPlay() {
	cout << this->num << endl << this->name << endl << this->sex ;
}

int main() {
	Stu *t = new Stu(1001, "zs", 'n');
	t->disPlay();
}
