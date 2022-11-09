// 内存反转以及交换两段连续(不连续)内存  https://blog.csdn.net/qing666888/article/details/51517028

#include <iostream>
using namespace std;

#define MAXBUFFERSIZE 100

void * reverseMemory(void *pMemory, const size_t memSize)
{
    if(NULL == pMemory) return pMemory;
    if(memSize < 2) return pMemory;

    char * beg = static_cast<char *>(pMemory);
    char * end = beg + memSize -1;
    for(; beg < end; ++beg, --end)
    {
        char memTmp = *beg;
        *beg = *end;
        *end = memTmp;
    }
    return pMemory;
}

// 交换两段连续的内存
void * swapAdjacentMemory(void *pMemory, const size_t headSize, const size_t totalSize)
{
    if(NULL == pMemory)    return pMemory;
    if(totalSize < 2)    return pMemory;
    if(headSize >= totalSize)    return pMemory;

    char* pStart = static_cast<char*>(pMemory);
    reverseMemory(pStart,headSize);
    reverseMemory(pStart+headSize,totalSize-headSize);
    reverseMemory(pStart,totalSize);
    return pMemory;
}

// 交换两段不连续的内存
void* swapNonAdjacentMemory(void *pMemory, const size_t headSize, const size_t endSize, const size_t totalSize)
{
    if(NULL == pMemory)    return pMemory;
    if(totalSize < 3)    return pMemory;
    if(headSize >= totalSize || endSize >= totalSize)    return pMemory;
    if(totalSize < (headSize+endSize))    return pMemory;

    char* pStart = static_cast<char *>(pMemory);
    reverseMemory(pStart,headSize);
    reverseMemory(pStart+headSize,totalSize-headSize-endSize);
    reverseMemory(pStart+totalSize-endSize,endSize);
    reverseMemory(pStart,totalSize);
    return pMemory;
}

int main()
{
	//这里我们将所有的测试用例放在一个字符串数组中，循环便可以测试所有的字符串
	//如果要修改字符串或增加新的测试用例，只需修改这个表即可

	static const char* stringArr[]=
	{
		"",
		"ab",
		"abc",
		"abc1234"
	};

	void * resultArr = malloc(MAXBUFFERSIZE); // store the test result
	if(NULL == resultArr) return 0;

	size_t arrLen = sizeof(stringArr)/sizeof(*stringArr);

	// test swapAdjacentMemory |
	for(size_t i = 0; i < arrLen; ++i)
	{
		memset(resultArr,0,MAXBUFFERSIZE);
		memcpy(resultArr,stringArr[i],strlen(stringArr[i]));
		swapAdjacentMemory(resultArr,2,strlen(*(stringArr+i)));
		printf("%s\n",resultArr);
	}

	// test swapNonAdjacentMemory
	for(size_t i = 0; i < arrLen; ++i)
	{
		memset(resultArr,0,MAXBUFFERSIZE);
		memcpy(resultArr,stringArr[i],strlen(stringArr[i]));
		swapNonAdjacentMemory(resultArr,2,2,strlen(stringArr[i]));
		printf("%s\n",resultArr);
	}
	free(resultArr);
	return 0;
}