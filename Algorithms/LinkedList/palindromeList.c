#include <stdlib.h>
#include <stdbool.h>

#include "list.h"

/**
 * Palindrome list from leetcode
 *
 * Given a reference of type ListNode which is the head of a singly linked list,
 * write a function to determine if the linked list is a palindrome.
 */
bool isPalindrome(ListNode *head)
{

    // Find the middle of the linked list using fast & slow pointers
    ListNode *slow = head;
    ListNode *fast = head;

    while (fast && fast->next)
    {
        fast = fast->next->next;
        slow = slow->next;
    }

    // Reverse second half of the list
    ListNode *previous = NULL;

    while (slow)
    {
        ListNode *next = slow->next; // save the next node so we have reference
        slow->next = previous;       // reverse pointer
        previous = slow;             // move pointers
        slow = next;
    }

    // check palindrome by comparing halves
    ListNode *first = head;
    ListNode *second = previous;

    while (second)
    {
        if (first->value != second->value)
        {
            return false;
        }

        first = first->next;
        second = second->next;
    }

    return true;
}
