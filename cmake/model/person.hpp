#include <string>

using namespace std;

class Person {
public:
    int age;
    string name;
    bool dead;
    Person(int ageP = 1, string nameP = "zhoujiyun", bool deadP = false) {
        age = ageP;
        name = nameP;
        dead = deadP;
    }
};