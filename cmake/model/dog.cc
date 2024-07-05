#include "dog.h"
#include <stdio.h>


Dog::Dog(int a, int b) {
    age = a;
    height = b;
}

Dog::~Dog()
{
    printf("dog destruct age=%d\n", age);
}

void Dog::feifei() {
    printf("a dog fei fei=%d\n", age);
}