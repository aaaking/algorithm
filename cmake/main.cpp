#include <iostream>
#include <vector>
#include <string>

using namespace std;

void func(string &str) {

    str += "a";
}

int main() {
    vector<string> b = {"str", "ghjhk", "2"};
    for (int i = 0; i < b.size(); i++) {
        string a = b[i];
        func(a);
    }
    cout << "success" << endl;

    return 0;
}