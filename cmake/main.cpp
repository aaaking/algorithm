
#include <vector>
#include "model/person.hpp"
#include "util/util.hpp"

using namespace std;

int main() {
    std::vector<std::string> b = {"str"};
    for (int i = 0; i < b.size(); i++) {
        string a = b[i];
        func(a);
        cout << a << endl;
    }

    Person zhouyu(1);
    cout << "age=" << zhouyu.age << " name=" << zhouyu.name << " dead=" << zhouyu.dead << endl;

    string path = string("/data/data/").append("a");
    printf("path=%s\n", path.c_str());

    string path2 = string("/data/data/") + ("b");
    printf("path2=%s\n", path2.c_str());

    return 0;
}