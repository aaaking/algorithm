
#include <vector>
#include "model/person.hpp"
#include "model/dog.h"
#include "model/Cat.h"
#include "util/util.hpp"

using namespace std;

enum class Color : char {
    Red = 'r',
    Green = 'g',
    Blue = 'b',
};

void test();

int main() {
    printf("\n----------------in main-------------------\n");

    string path = string("/data/data/").append("a"); // path=/data/data/a
    string path2 = string("/data/data/") + ("b"); // path2=/data/data/b

    Dog aDog = Dog(11);
    Dog bDog{22, 2};
    Dog cDog(33, 2);
    Dog dDog; // 不推荐

    shared_ptr<Dog> aShareDog = make_shared<Dog>(55, 3);
    printf("e shared dog age=%d height=%d ptr=%p isnull=%d \n", aShareDog->age, aShareDog->height, &aShareDog, aShareDog == nullptr);
    aShareDog.reset(); // 等价于aShareDog = nullptr;    后面可以接续调用reset接口，但是不能调用age等接口，会报错: Segmentation fault

    string str = "hello world";
    size_t pos = str.rfind('o'); // 搜索最后一个‘o’  输出7
    str = "hello world";
    pos = str.rfind('o', 5); // 搜索5号位置前（含5号）最后一个'o'   输出4
    str = "hello world world";
    pos = str.find("wor", 16, 3); // 从10号位置前（含10号位置） 查找3个字符"wor"  输出6
    
    test();


    printf("\n----------------out main-------------------\n");
    return 0;
}

void test() {
    std::shared_ptr<Dog> aDog = std::make_shared<Dog>(101);
    std::shared_ptr<Dog> bDog = aDog;
    std::shared_ptr<Dog> cDog = bDog;
    printf("test  00 ===  use1=%ld use2=%ld use3=%ld\n", aDog.use_count(), bDog.use_count(), cDog.use_count());
    std::shared_ptr<Dog> dDog = std::move(cDog);
    printf("test  11 ===  use1=%ld use2=%ld use3=%ld\n", aDog.use_count(), bDog.use_count(), cDog.use_count());
    // aDog.reset(); // no necessary
}