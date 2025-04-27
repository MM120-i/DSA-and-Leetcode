#include <stdio.h>
#include <stdbool.h>

bool isPairSum(int[], int, int);
bool twoSum(int[], int, int);

int main(void)
{
    int nums[] = {2, 7, 11, 15};
    int target = 9;
    int size = sizeof(nums) / sizeof(nums[0]);

    if (twoSum(nums, size, target))
    {
        printf("Found a pair that sums to %d.\n", target);
    }
    else
    {
        printf("No pair found that sums to %d.\n", target);
    }

    return 0;
}

// O(n^2)
bool isPairSum(int nums[], int size, int target)
{

    for (size_t i = 0; i < size; i++)
    {
        for (size_t j = i + 1; j < size; j++)
        {
            if (nums[i] + nums[j] == target)
            {
                return true;
            }
        }
    }

    return false;
}

// O(n)
bool twoSum(int nums[], int size, int target)
{
    int left = 0;
    int right = size - 1;

    while (left < right)
    {
        int currentSum = nums[left] + nums[right];

        if (currentSum == target)
        {
            return true;
        }

        if (currentSum < target)
        {
            left++;
        }
        else
        {
            right--;
        }
    }

    return false;
}
