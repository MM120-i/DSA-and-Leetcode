/**
 * 3 Sum Leetcode problem.
 *
 *
 * Given an input integer array nums, write a function to find all unique triplets [nums[i], nums[j], nums[k]]
 * such that i, j, and k are distinct indices, and the sum of nums[i], nums[j], and nums[k] equals zero.
 * Ensure that the resulting list does not contain any duplicate triplets.
 */

#include <stdio.h>
#include <stdlib.h>

int compare(const void *, const void *);
void threeSum(int *, int);

int main(void)
{
    int nums[] = {-1, 0, 1, 2, -1, -1};
    int size = sizeof(nums) / sizeof(nums[0]);

    printf("Unique triplets that sum to 0: \n");
    threeSum(nums, size);

    return 0;
}

// Compare function for qSort.
int compare(const void *a, const void *b)
{
    return (*(int *)a - *(int *)b);
}

// Function to find and print all unique triplets.
void threeSum(int *nums, int size)
{
    if (size < 3)
    {
        return;
    }

    // Sort the array
    qsort(nums, size, sizeof(int), compare);

    for (size_t i = 0; i < size - 2; i++)
    {
        // skip the duplicates for the first element
        if (i > 0 && nums[i] == nums[i - 1])
        {
            continue;
        }

        int left = i + 1;
        int right = size - 1;

        while (left < right)
        {
            int total = nums[i] + nums[left] + nums[right];

            if (total < 0)
            {
                left++;
            }
            else if (total > 0)
            {
                right--;
            }
            else
            {
                printf("[%d, %d, %d]\n", nums[i], nums[left], nums[right]);

                // skip duplicates for left:
                while (left < right && nums[left] == nums[left + 1])
                {
                    left++;
                }

                // skip duplicates for right:
                while (left < right && nums[left] == nums[right - 1])
                {
                    right--;
                }

                // Move both pointers inward.
                left++;
                right--;
            }
        }
    }
}
