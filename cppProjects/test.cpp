#include <iostream>
using namespace std;

class OrderOfFive {
   public:
      int operator=(const int n) {
        int result = 1;
        for (int i = 0; i < n; i++) {
            result *= 5;
        }
        return result;
      }
};
int main() {
   OrderOfFive OrderOfFive;
   cout << "1=" << (OrderOfFive=1) << endl;
   cout << "2=" << (OrderOfFive=2) << endl;
   cout << "3=" << (OrderOfFive=3) << endl;
   cout << "4=" << (OrderOfFive=4) << endl;
   cout << "5=" << (OrderOfFive=5) << endl;
   return 0;
}
