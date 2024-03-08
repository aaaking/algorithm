
#include <vector>
#include "model/person.hpp"
#include "util/util.hpp"

using namespace std;

int main() {
    std::vector<std::string> b = {"str", "ghjhk", "2"};
    for (int i = 0; i < b.size(); i++) {
        string a = b[i];
        func(a);
        cout << a << endl;
    }
    cout << "success" << endl;

    Person zhouyu(1);
    cout << "age=" << zhouyu.age << " name=" << zhouyu.name << " dead=" << zhouyu.dead << endl;

    return 0;
}