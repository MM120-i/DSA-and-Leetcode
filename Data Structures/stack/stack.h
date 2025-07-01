#include <stddef.h>

#pragma once

#define SIZE 5

typedef struct Stack
{
    size_t count;
    size_t capacity;
    void **data;
} stack_t;

// stack operations
stack_t *stackNew(size_t);
void stackPush(stack_t *, void *);
void *stackPop(stack_t *);
void stackFree(stack_t *);
void *stackPeek(stack_t *);
bool stackIsEmpty(stack_t *);
bool stackIsFull(stack_t *);
size_t stackSize(stack_t *);
size_t stackCapacity(stack_t *);
void stackClear(stack_t *);
void stackForEach(stack_t *, void (*func)(void *));
stack_t *stackClone(stack_t *);
bool stackContains(stack_t *, void *);
void stackReverse(stack_t *);
bool stackResize(stack_t *, size_t);
size_t stackToArray(stack_t *, void **, size_t);

// other
const char *boolToString(bool);
void printInt(void *);
