/**
 * Traingle Numbers from Leetcode
 *
 * Write a function to count the number of triplets in an integer array nums that could form the sides of a triangle.
 * The triplets do not need to be unique.
 *
 */

#include <stdio.h>
#include <stdlib.h>

int compare(const void *, const void *);
int triangleNums(int *, int);

int main(void)
{
    int nums[] = {11, 4, 9, 6, 15, 18};
    int size = sizeof(nums) / sizeof(nums[0]);

    int result = triangleNums(nums, size);
    printf("Number of valid traingles: %d\n", result);

    return 0;
}

// Compare function for qSort.
int compare(const void *a, const void *b)
{
    return (*(int *)a - *(int *)b);
}

/**
 * time: O(n^2)
 * space: O(1)
 */
int triangleNums(int *nums, int size)
{
    if (size < 3)
    {
        return 0;
    }

    qsort(nums, size, sizeof(int), compare);

    int count = 0;

    for (size_t i = size - 1; i >= 2; i--)
    {
        int left = 0;
        int right = i - 1;

        while (left < right)
        {
            if (nums[left] + nums[right] > nums[i])
            {
                count += right - left;
                right--;
            }
            else
            {
                left++;
            }
        }
    }

    return count;
}
