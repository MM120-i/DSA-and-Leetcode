/**
 * Container with Most Water LeetCode Problem.
 *
 *
 * Given an integer input array heights representing the heights of vertical lines,
 * write a function that returns the maximum area of water that can be contained
 * by two of the lines (and the x-axis). The function should take in an array of integers and return an integer.
 */

#include <stdio.h>

int min(int, int);
int max(int, int);
int maxArea(int[], int);

int main(void)
{
    int heights1[] = {3, 4, 1, 2, 2, 4, 1, 3, 2};
    int size1 = sizeof(heights1) / sizeof(heights1[0]);
    printf("Max area (ex 1); %d\n", maxArea(heights1, size1));

    int heights2[] = {1, 2, 1};
    int size2 = sizeof(heights2) / sizeof(heights2[0]);
    printf("Max area (ex2): %d\n", maxArea(heights2, size2));

    return 0;
}

int min(int a, int b)
{
    return a < b ? a : b;
}

int max(int a, int b)
{
    return a > b ? a : b;
}

int maxArea(int heights[], int size)
{
    int left = 0;
    int right = size - 1;
    int currentMax = 0;

    while (left < right)
    {
        int width = right - left;
        int height = min(heights[left], heights[right]);
        int currentArea = width * height;

        currentMax = max(currentMax, currentArea);

        if (heights[left] < heights[right])
        {
            left++;
        }
        else
        {
            right--;
        }
    }

    return currentMax;
}
