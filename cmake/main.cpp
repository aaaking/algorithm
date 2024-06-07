
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

    string path = string("/data/data/").append("a");
    printf("path=%s", path.c_str());

    string path2 = string("/data/data/") + ("b");
    printf("path2=%s", path2.c_str());

    int a = 0;
    if (a++ >= 1) {
        printf("\na=======%d", a);
    }
    int c = 0;
    if (++c >= 1) {
        printf("\nc=======%d", c);
    }

    int d = 0;
    printf("\nd=======%d", d++);

    int e = 0;
    printf("\ne=======%d", ++e);

    return 0;
}