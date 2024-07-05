

#pragma once;



/*
在C++11中，可以使用 `default` 关键字来显式地要求编译器生成一个函数的默认实现。这种方式常常被用于声明或定义特殊函数，比如拷贝构造函数、赋值运算符、析构函数等。

具体来说，以下函数的后面可以加上 `= default;`：

1. 默认构造函数：`MyClass() = default;`
2. 拷贝构造函数：`MyClass(const MyClass&) = default;`
3. 赋值运算符：`MyClass& operator=(const MyClass&) = default;`
4. 移动构造函数：`MyClass(MyClass&&) = default;`
5. 移动赋值运算符：`MyClass& operator=(MyClass&&) = default;`
6. 析构函数：`virtual ~MyClass() = default;`

需要注意的是，我们只能在特定条件下将特殊函数标记为默认的：

1. 默认构造函数和析构函数可以在任何时候都标记为默认的。
2. 拷贝构造函数、赋值运算符和移动构造函数只能在类没有定义任何一个构造函数或赋值运算符的情况下标记为默认的。如果你自己定义了任何一个构造函数或赋值运算符，那么你需要将其定义为“显式”的。
3. 移动赋值运算符也必须满足上述要求，但实际上在某些情况下可以自动调用移动构造函数来生成一个实现。 
 
*/

class Dog {
private:
    /* data */
public:
    int age;
    int height;
    bool dead;
    Dog(const Dog&) = default;// 2. 拷贝构造函数：`MyClass(const MyClass&) = default;`
    Dog(int a = 0, int b = 0);
    ~Dog();

    void feifei();
};
