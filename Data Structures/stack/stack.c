#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stddef.h>
#include <assert.h>
#include <stdbool.h>

#include "stack.h"

int main(void)
{
    // test cases
    stack_t *stack = stackNew(2);

    if (!stack)
    {
        goto A;
    }

    int a = 1, b = 2, c = 3, d = 4;

    stackPush(stack, &a);
    stackPush(stack, &b);
    stackPush(stack, &c);

    // stackIsFull, stackSize, stackCapacity
    printf("Is full? %s\n", boolToString(stackIsFull(stack)));
    printf("Size: %zu\n", stackSize(stack));
    printf("Capacity: %zu\n", stackCapacity(stack));

    // stackContains
    printf("Contains &b? %s\n", boolToString(stackContains(stack, &b)));
    printf("Contains &d? %s\n", boolToString(stackContains(stack, &d)));

    // stackForEach
    printf("Stack elements: ");
    stackForEach(stack, printInt);
    printf("\n");

    // stackClone
    stack_t *clone = stackClone(stack);

    if (!clone)
    {
        goto A;
    }

    printf("Cloned stack elements: ");
    stackForEach(clone, printInt);
    printf("\n");

    // stackReverse
    stackReverse(stack);
    printf("Reversed stack elements: ");
    stackForEach(stack, printInt);
    printf("\n");

    // stackResize
    printf("Resizing stack to capacity 5: %s\n", boolToString(stackResize(stack, 5)));
    printf("New capacity: %zu\n", stackCapacity(stack));

    // stack to array
    void *array[SIZE];
    size_t n = stackToArray(stack, array, SIZE);
    printf("Stack to array: ");

    for (size_t i = 0; i < n; i++)
    {
        printf("%d ", *(int *)array[i]);
    }

    printf("\n");

    // stackClear
    stackClear(stack);
    printf("After clear, size: %zu, is empty? %s\n", stackSize(stack), boolToString(stackIsEmpty(stack)));

    stackFree(stack);
    stackFree(clone);

    return 0;

A:
    printf("Failed to create a stack. \n");
    return 1;
}

/**
 * Converts a boolean value to its string representation.
 */
const char *boolToString(bool b)
{
    return b ? "true" : "false";
}

/**
 * Prints the integer value pointed to by the given pointer.
 */
void printInt(void *ptr)
{
    printf("%d ", *(int *)ptr);
}

/**
 * @brief Creates a new stack with the specified capacity.
 *
 * Allocates memory for a new stack_t structure and its underlying data array.
 * Initializes the stack's count to 0 and sets its capacity.
 *
 * @param capacity The maximum number of elements the stack can hold.
 * @return Pointer to the newly created stack_t structure, or NULL if memory allocation fails.
 */
stack_t *stackNew(size_t capacity)
{
    stack_t *newStack = (stack_t *)malloc(sizeof(stack_t));

    if (!newStack)
    {
        return NULL;
    }

    newStack->count = 0;
    newStack->capacity = capacity;
    newStack->data = malloc(sizeof(void *) * capacity);

    if (newStack->data == NULL)
    {
        free(newStack);
        return NULL;
    }

    return newStack;
}

/**
 * @brief Pushes an object onto the stack.
 *
 * This function adds a new object to the top of the stack. If the stack's current
 * capacity is reached, it dynamically resizes the underlying data array to accommodate
 * more elements. If memory allocation fails during resizing, the function exits the program.
 *
 * @param stack Pointer to the stack structure.
 * @param obj Pointer to the object to be pushed onto the stack.
 */
void stackPush(stack_t *stack, void *obj)
{
    if (stack->count == stack->capacity)
    {
        stack->capacity *= 2;
        stack->data = realloc(stack->data, sizeof(void *) * stack->capacity);

        if (!stack->data)
        {
            stack->capacity /= 2;
            exit(EXIT_FAILURE);
        }
    }

    stack->data[stack->count] = obj;
    stack->count++;
}

/**
 * @brief Removes and returns the top element from the stack.
 *
 * This function decrements the stack's count and returns the element
 * at the top of the stack. If the stack is empty, it returns NULL.
 *
 * @param stack Pointer to the stack structure.
 * @return Pointer to the popped element, or NULL if the stack is empty.
 */
void *stackPop(stack_t *stack)
{
    if (stack->count == 0)
    {
        return NULL;
    }

    stack->count--;
    return stack->data[stack->count];
}

/**
 * @brief Frees the memory allocated for a stack and its data.
 *
 * This function checks if the provided stack pointer is not NULL.
 * If the stack exists, it attempts to free the memory allocated for the stack's data
 * (if it exists), and then frees the stack structure itself.
 *
 * @param stack Pointer to the stack_t structure to be freed.
 */
void stackFree(stack_t *stack)
{
    if (!stack)
    {
        return;
    }

    if (!stack->data)
    {
        free(stack->data);
    }

    free(stack);
}

/**
 * Returns a pointer to the top element of the stack without removing it.
 */
void *stackPeek(stack_t *s)
{
    if (!s || s->count == 0)
    {
        return NULL;
    }

    return s->data[s->count - 1];
}

/**
 * Checks if the stack is empty.
 */
bool stackIsEmpty(stack_t *s)
{
    return (!s || s->count == 0);
}

/**
 * returns the stack size
 */
size_t stackSize(stack_t *s)
{
    return s ? s->count : 0;
}

/**
 * returns the stack's capacity
 */
size_t stackCapacity(stack_t *s)
{
    return s ? s->capacity : 0;
}

/**
 * Clears the stack
 */
void stackClear(stack_t *s)
{
    if (s)
    {
        s->count = 0;
    }
}

/**
 * @brief Applies a given function to each element in the stack from top to bottom.
 *
 * This function iterates over the elements of the stack, starting from the top (most recently added element)
 * down to the bottom, and applies the provided function to each element.
 *
 * @param stack Pointer to the stack structure to iterate over.
 * @param func Function pointer to apply to each element. The function should accept a void pointer to an element.
 *
 * @note If either @p stack or @p func is NULL, the function returns immediately without performing any action.
 */
void stackForEach(stack_t *stack, void (*func)(void *))
{
    if (!stack || !func)
    {
        return;
    }

    for (size_t i = stack->count; i > 0; i--)
    {
        func(stack->data[i - 1]);
    }
}

/**
 * @brief Creates a clone (deep copy) of the given stack.
 *
 * This function allocates a new stack with the same capacity as the input stack,
 * and copies all elements and the count from the original stack to the new one.
 *
 * @param stack Pointer to the stack to be cloned.
 * @return Pointer to the newly cloned stack, or NULL if the input stack is NULL
 *         or memory allocation fails.
 */
stack_t *stackClone(stack_t *stack)
{
    if (!stack)
    {
        return NULL;
    }

    stack_t *copy = stackNew(stack->capacity);

    if (!copy)
    {
        return NULL;
    }

    for (size_t i = 0; i < stack->count; i++)
    {
        copy->data[i] = stack->data[i];
    }

    copy->count = stack->count;

    return copy;
}

/**
 * @brief Checks if a given object exists in the stack.
 *
 * Iterates through the stack and compares each element with the provided object pointer.
 *
 * @param stack Pointer to the stack to search.
 * @param obj Pointer to the object to search for.
 * @return true if the object is found in the stack, false otherwise.
 */
bool stackContains(stack_t *stack, void *obj)
{
    if (!stack)
    {
        return NULL;
    }

    for (size_t i = 0; i < stack->count; i++)
    {
        if (stack->data[i] == obj)
        {
            return true;
        }
    }

    return false;
}

/**
 * @brief Reverses the order of elements in the given stack.
 *
 * This function reverses the elements stored in the stack in-place.
 * It swaps elements from the start and end of the stack's data array,
 * moving towards the center, until all elements are reversed.
 *
 * @param stack Pointer to the stack_t structure to be reversed.
 *              If the pointer is NULL, the function does nothing.
 */
void stackReverse(stack_t *stack)
{
    if (!stack)
    {
        return;
    }

    for (size_t i = 0, j = stack->count - 1; i < j; i++, j--)
    {
        void *temp = stack->data[i];
        stack->data[i] = stack->data[j];
        stack->data[j] = temp;
    }
}

/**
 * @brief Resizes the capacity of the given stack.
 *
 * Attempts to change the capacity of the stack to the specified new capacity.
 * If the new capacity is less than the current number of elements in the stack,
 * or if the stack pointer is NULL, the function returns false.
 * On success, reallocates the internal data array to the new capacity and updates
 * the stack's capacity field.
 *
 * @param stack Pointer to the stack to resize.
 * @param newCapcity The desired new capacity for the stack.
 * @return true if the resize operation was successful, false otherwise.
 */
bool stackResize(stack_t *stack, size_t newCapcity)
{
    if (!stack || newCapcity < stack->count)
    {
        return false;
    }

    void **newData = realloc(stack->data, sizeof(void *) * newCapcity);

    if (!newData)
    {
        return false;
    }

    stack->data = newData;
    stack->capacity = newCapcity;

    return true;
}

/**
 * @brief Copies elements from a stack into an array up to a maximum number.
 *
 * This function copies up to 'max' elements from the given stack into the provided array.
 * The elements are copied in the order they are stored internally in the stack (typically
 * from the bottom of the stack to the top, depending on stack implementation).
 *
 * @param stack Pointer to the stack_t structure to copy elements from.
 * @param array Pointer to an array of void* where the stack elements will be copied.
 * @param max The maximum number of elements to copy into the array.
 * @return The number of elements actually copied to the array. Returns 0 if stack or array is NULL.
 */
size_t stackToArray(stack_t *stack, void **array, size_t max)
{
    if (!stack || !array)
    {
        return 0;
    }

    size_t n = (stack->count < max) ? stack->count : max;

    for (size_t i = 0; i < n; i++)
    {
        array[i] = stack->data[i];
    }

    return n;
}
