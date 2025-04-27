/**
 * Move Zeros from Leetcode
 *
 * Given an integer array nums, write a function to rearrange the array by moving all zeros
 * to the end while keeping the order of non-zero elements unchanged. Perform this operation
 * in-place without creating a copy of the array.
 *
 */

#include <stdio.h>

void moveZeros(int *, int);

int main(void)
{
    int nums[] = {2, 0, 4, 0, 9};
    int size = sizeof(nums) / sizeof(nums[0]);

    moveZeros(nums, size);

    printf("Array after moving zeros: \n");

    for (size_t i = 0; i < size; i++)
    {
        printf("%d ", nums[i]);
    }

    printf("\n");

    return 0;
}

/**
 * time: O(n)
 * space: O(1)
 */
void moveZeros(int *nums, int size)
{
    int nextNonZero = 0;

    for (size_t i = 0; i < size; i++)
    {
        if (nums[i] != 0)
        {
            // Swap nums[nextNonZero] and nums[i].
            int temp = nums[nextNonZero];
            nums[nextNonZero] = nums[i];
            nums[i] = temp;
            nextNonZero++;
        }
    }
}
