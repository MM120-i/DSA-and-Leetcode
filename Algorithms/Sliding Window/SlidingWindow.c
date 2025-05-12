/**
 * Fruit Into Baskets from leetcode
 *
 * Variable length
 *
 * Write a function to calculate the maximum number of fruits you can collect from an integer array fruits,
 * where each element represents a type of fruit. You can start collecting fruits from any position in the array,
 * but you must stop once you encounter a third distinct type of fruit.
 * The goal is to find the longest subarray where at most two different types of fruits are collected.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int fruitIntoBaskets(int *, int);

int main(void)
{
    int fruits[] = {3, 3, 2, 1, 2, 1, 0};
    int size = sizeof(fruits) / sizeof(fruits[0]);

    printf("Max fruits: %d\n", fruitIntoBaskets(fruits, size));

    return 0;
}

int fruitIntoBaskets(int *fruits, int fruitsSize)
{
    int count[1001] = {0}; // using a fixed size array to simulate a hashmap cuz c doesnt have hashmaps
    int start = 0, max_fruit = 0, types = 0;

    for (size_t end = 0; end < fruitsSize; end++)
    {
        if (count[fruits[end]] == 0)
        {
            types++;
        }

        count[fruits[end]]++;

        while (types > 2)
        {
            count[fruits[start]]--;

            if (count[fruits[start]] == 0)
            {
                types--;
            }

            start++;
        }

        int windowSize = end - start + 1;

        if (windowSize > max_fruit)
        {
            max_fruit = windowSize;
        }
    }

    return max_fruit;
}
