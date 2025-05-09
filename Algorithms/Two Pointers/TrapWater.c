/**
 * Trapping Rain Water
 *
 * Write a function to calculate the total amount of water trapped between bars on an elevation map,
 * where each bar's width is 1. The input is given as an array of n non-negative integers height representing the height of each bar.
 */

#include <stdio.h>

int trappingWater(int *, int);

int main(void)
{
    int heights[] = {3, 4, 1, 2, 2, 5, 1, 0, 2};
    int size = sizeof(heights) / sizeof(heights[0]);
    int result = trappingWater(heights, size);

    printf("Water trapped: %d\n", result);

    return 0;
}

// Time: O(n)
// Space: O(1)
int trappingWater(int *heights, int heightsSize)
{
    if (heightsSize < 3)
    {
        return 0;
    }

    int left = 0;
    int right = heightsSize - 1;
    int leftMax = heights[left];
    int rightMax = heights[right];
    int count = 0;

    while (left + 1 < right)
    {
        if (rightMax > leftMax)
        {
            left++;

            if (heights[left] > leftMax)
            {
                leftMax = heights[left];
            }
            else
            {
                count += leftMax - heights[left];
            }
        }
        else
        {
            right--;

            if (heights[right] > rightMax)
            {
                rightMax = heights[right];
            }
            else
            {
                count += rightMax - heights[right];
            }
        }
    }

    return count;
}
