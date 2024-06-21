
#include <vector>
#include "model/person.hpp"
#include "model/dog.h"
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

    Dog aDog = Dog();
    printf("a dog age=%d height=%d\n", aDog.age, aDog.height);
    Dog bDog{1, 2};
    printf("b dog age=%d height=%d\n", bDog.age, bDog.height);
    Dog cDog(1, 2);
    printf("c dog age=%d height=%d\n", cDog.age, cDog.height);
    Dog dDog; // 不推荐
    printf("d dog age=%d height=%d\n", dDog.age, dDog.height);


    return 0;
}

