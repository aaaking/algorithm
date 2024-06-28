
#include <vector>
#include "model/person.hpp"
#include "model/dog.h"
#include "model/Cat.h"
#include "util/util.hpp"

using namespace std;

#define AWESOME_CACHE_ERROR_CODES(X, Y)                                          \
    X(0,        kResultOK,                      "kResultOK",            "No error at all!") \
    X(-8000,    kHodorErrorCodeMin,     "kHodorErrorCodeMin",     "目前Hodor相关的error不会小于这个值") \
    Y("CacheException related, kResultCacheExceptionStart ~ kResultCacheExceptionEnd 都是cache相关的exception") \
    X(-1101,    kResultFFmpegAdapterInnerError,     "kResultFFmpegAdapterInnerError",       "Hodor 适配层内部错误")

#define AWESOME_CACHE_ERROR_ENUM(ID, NAME, ERR_MSG, ERR_DESC) NAME = ID,
#define DIVIDER_AND_COMMENT(a)

typedef enum AwesomeCacheErrorCode {
    AWESOME_CACHE_ERROR_CODES(AWESOME_CACHE_ERROR_ENUM, DIVIDER_AND_COMMENT)
} AwesomeCacheErrorCode;

enum class Color : char {
    Red = 'r',
    Green = 'g',
    Blue = 'b',
};

int fs;

static Dog cDog;

void test();

int main() {
    cDog.age = 200;
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

    printf("fs=%d\n", fs);

    Dog aDog = Dog(11);
    printf("a dog age=%d height=%d\n", aDog.age, aDog.height);
    Dog bDog{22, 2};
    printf("b dog age=%d height=%d\n", bDog.age, bDog.height);
    Dog cDog(33, 2);
    printf("c dog age=%d height=%d\n", cDog.age, cDog.height);
    Dog dDog; // 不推荐
    printf("d dog age=%d height=%d\n", dDog.age, dDog.height);

    shared_ptr<Dog> aShareDog = make_shared<Dog>(55, 3);
    printf("e shared dog age=%d height=%d ptr=%p isnull=%d \n", aShareDog->age, aShareDog->height, &aShareDog, aShareDog == nullptr);
    aShareDog.reset(); // 后面可以接续调用reset接口，但是不能调用age等接口，会报错: Segmentation fault
    printf("e shared dog after reset ptr=%p isnull=%d \n", &aShareDog, aShareDog == nullptr);


    string str = "hello world";
    size_t pos = str.rfind('o'); // 搜索最后一个‘o’
    if (pos != string::npos) {
        cout << "最后一个'o'的位置是：" << pos << endl; // 输出7
    }

    str = "hello world";
    pos = str.rfind('o', 5); // 搜索5号位置前（含5号）最后一个'o'
    if (pos != string::npos) {
        cout << "5号位置前最后一个'o'的位置是: " << pos << endl; // 输出4
    }

    str = "hello world world";
    pos = str.find("wor", 16, 3); // 从10号位置前（含10号位置） 查找3个字符"wor"

    if (pos != string::npos) {
        cout << "10号位置前 查找3个字符\"wor\"的位置是: " << pos << endl; // 输出6
    }
    
    test();

    return 0;
}

void test() {
    std::shared_ptr<Dog> aDog = std::make_shared<Dog>(101);
    // aDog.reset(); // no necessary
}