/**
 * Sort Colors from Leetcode
 *
 * Write a function to sort a given integer array nums in-place (and without the built-in sort function),
 * where the array contains n integers that are either 0, 1, and 2 and represent the colors red, white, and blue.
 * Arrange the objects so that same-colored ones are adjacent, in the order of red, white, and blue (0, 1, 2).
 */

#include <stdio.h>

void sortColors(int *, int);
void swap(int *, int *);

int main(void)
{
    int nums[] = {2, 1, 2, 0, 1, 0, 1, 0, 1};
    int size = sizeof(nums) / sizeof(nums[0]);

    sortColors(nums, size);

    printf("Sorted Colors: ");

    for (size_t i = 0; i < size; i++)
    {
        printf("%d ", nums[i]);
    }

    printf("\n");

    return 0;
}

// Time Complexity: O(n)
// Space Complexity: O(1)
void sortColors(int *nums, int numsSize)
{
    int left = 0, right = numsSize - 1;
    size_t i = 0;

    while (i <= right)
    {
        if (nums[i] == 0)
        {
            // swap nums[i] and nums[left]
            swap(&nums[i], &nums[left]);
            left++;
            i++;
        }
        else if (nums[i] == 2)
        {
            // swap nums[i] and nums[right]
            swap(&nums[i], &nums[right]);
            right--;
        }
        else
        {
            // nums[i] == 1
            i++;
        }
    }
}

void swap(int *a, int *b)
{
    int temp = *a;
    *a = *b;
    *b = temp;
}
