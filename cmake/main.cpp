
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

    Dog aDog = Dog(11, 11);
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

    Dog cpDog = Dog(aDog);
    printf("copy dog=%d %d\n", cpDog.age, cpDog.height);
    
    test();

    std::unique_ptr<Dog> uDog(new Dog(256));
    if (uDog) {
        printf("111111\n");
    } else {
        printf("0000\n");
    }
    std::optional<std::unique_ptr<Dog>> optDog = ((std::move(uDog)));
    optDog->get()->feifei();
    



    printf("\n----------------out main-------------------\n");
    return 0;
}





class A {
public:
    A() { std::cout << "A::A()" << std::endl; }
    ~A() { std::cout << "A::~A()" << std::endl; }
    void doSomething() { std::cout << "A::doSomething()" << std::endl; }
};



void test() {
    std::shared_ptr<Dog> aDog = std::make_shared<Dog>(101);
    std::shared_ptr<Dog> bDog = aDog;
    std::shared_ptr<Dog> cDog = bDog;
    printf("test  00 ===  use1=%ld use2=%ld use3=%ld\n", aDog.use_count(), bDog.use_count(), cDog.use_count());
    std::shared_ptr<Dog> dDog = std::move(cDog);
    printf("test  11 ===  use1=%ld use2=%ld use3=%ld\n", aDog.use_count(), bDog.use_count(), cDog.use_count());
    // aDog.reset(); // no necessary




    printf("\n\n");
    std::weak_ptr<A> wp;
    {
        std::shared_ptr<A> sp(new A);
        cout << "*sp==" << (sp) << endl;
        printf("usecnt=====s=%d w=%d\n", sp.use_count(), wp.use_count());
        wp = sp;
        printf("usecnt=====s=%d w=%d\n", sp.use_count(), wp.use_count());
        cout << "sp======" << sp << endl;
        std::shared_ptr<A> af1 = wp.lock();
        std::shared_ptr<A> af2 = wp.lock();
        std::shared_ptr<A> af3 = wp.lock();
        std::cout << "wp.lock() = " << wp.lock() << " usecnt=" << wp.use_count() << " " << sp.use_count() << std::endl;
    }
    std::cout << "wp.lock() = " << wp.lock() << std::endl;
    if (!wp.expired()) {
        std::cout << "A still exists." << std::endl;
        std::shared_ptr<A> sp = wp.lock();
        sp->doSomething();
    } else {
        std::cout << "A has been destroyed." << std::endl;
    }
    printf("\n\n");
}
