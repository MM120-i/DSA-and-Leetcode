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

/**
 * Remove nth node from leetcode
 *
 * Given a reference head of type ListNode that is the head node of a singly linked list and an integer n,
 * write a function that removes the n-th node from the end of the list and returns the head of the modified list.
 */
ListNode *removeNthNode(ListNode *head, int n)
{
    ListNode *dummy = createNode(0);
    dummy->next = head;

    ListNode *fast = dummy;
    ListNode *slow = dummy;

    // move fast pointer n + 1 steps ahead
    for (size_t i = 0; i <= n; i++)
    {
        fast = fast->next;
    }

    while (fast)
    {
        fast = fast->next;
        slow = slow->next;
    }

    ListNode *toDelete = slow->next;
    slow->next = slow->next->next;
    free(toDelete);

    ListNode *result = dummy->next;
    free(dummy);

    return result;
}

/**
 * Reorder List from leetcode
 *
 * Given a reference head of type ListNode that is the head of a singly linked list,
 * reorder the list in-place such that the nodes are reordered to form the following pattern:
 */
void reorder(ListNode *head)
{
    if (!head || !head->next)
    {
        return;
    }

    // finding the middle of the list with fast/slow pointers
    ListNode *slow = head;
    ListNode *fast = head;

    while (fast->next && fast->next->next)
    {
        slow = slow->next;
        fast = fast->next->next;
    }

    // reverse the second half starting from slow->next
    ListNode *secondHalf = reverse(slow->next);
    slow->next = NULL; // cut the list into two halves

    // merge the two halves alternatively
    ListNode *firstHalf = head;

    while (secondHalf)
    {
        ListNode *firstNext = firstHalf->next; // store the next node
        ListNode *secondNext = secondHalf->next;
        firstHalf->next = secondHalf; // link the first to second
        secondHalf->next = firstNext; // link the second to first's next
        firstHalf = firstNext;        // Move to the next nodes
        secondHalf = secondNext;
    }
}

/**
 * Swap Node in Pairs
 *
 * Given a reference head of type ListNode that is the head of a singly linked list, write a function to swap every two adjacent nodes and return its head.
 * You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
 */
ListNode *swapPairs(ListNode *head)
{
    // dummy node
    ListNode *dummy = createNode(0);
    dummy->next = head;
    ListNode *previous = dummy;

    // process pairs while both nodes exist
    while (previous->next && previous->next->next)
    {
        // Identify the two nodes to swap
        ListNode *first = previous->next;
        ListNode *second = previous->next->next;

        // swap by adjusting pointers
        previous->next = second;    // link previous to second node
        first->next = second->next; // link first to node after second
        second->next = first;       // link second to first

        // move previous to the end of swapped pair for next iteration
        previous = first;
    }

    ListNode *result = dummy->next;
    free(dummy);

    return result;
}
