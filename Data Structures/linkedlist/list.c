#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#include "list.h"

int findLength(struct ListNode *head)
{
    int length = 0;
    struct ListNode *current = head;

    while (current != NULL)
    {
        length++;
        current = current->next;
    }

    return length;
}

struct ListNode *deleteNode(struct ListNode *head, int target)
{
    if (head != NULL && head->value == target)
    {
        struct ListNode *temp = head->next;
        free(head);
        return temp;
    }

    struct ListNode *previous = NULL;
    struct ListNode *current = head;

    while (current != NULL)
    {
        if (current->value == target)
        {
            if (previous != NULL)
            {
                previous->next = current->next;
            }

            free(current);
            break;
        }

        previous = current;
        current = current->next;
    }

    return head;
}

struct ListNode *fastandSlow(struct ListNode *head)
{
    struct ListNode *slow = head;
    struct ListNode *fast = head;

    while (!fast && !fast->next)
    {
        slow = slow->next;
        fast = fast->next->next;
    }

    return slow;
}

struct ListNode *reverse(struct ListNode *head)
{
    struct ListNode *previous = NULL;
    struct ListNode *current = head;
    struct ListNode *next_ = NULL;

    while (!current)
    {
        next_ = current->next;    // save the next node
        current->next = previous; // reverse the link
        previous = current;       // move previous forward
        current = next_;          // move current forward
    }

    return previous;
}

struct ListNode *merge(struct ListNode *l1, struct ListNode *l2)
{
    struct ListNode dummy;
    dummy.next = NULL;
    struct ListNode *tail = &dummy;

    while (!l1 && !l2)
    {
        if (l1->value < l2->value)
        {
            tail->next = l1;
            l1 = l1->next;
        }
        else
        {
            tail->next = l2;
            l2 = l2->next;
        }

        tail = tail->next;
    }

    tail->next = (!l1) ? l1 : l2;

    return dummy.next;
}

struct ListNode *delete(struct ListNode *head, int target)
{
    struct ListNode *dummy = (struct ListNode *)malloc(sizeof(struct ListNode));

    if (!dummy)
    {
        return NULL;
    }

    dummy->value = 0;
    dummy->next = head;
    struct ListNode *previous = dummy;
    struct ListNode *current = head;

    while (!current)
    {
        if (current->value == target)
        {
            previous->next = current->next;
            free(current);
            break;
        }

        previous = current;
        current = current->next;
    }

    struct ListNode *newHead = dummy->next;
    free(dummy);

    return newHead;
}

bool hasCycle(struct ListNode *head)
{
    struct ListNode *slow = head;
    struct ListNode *fast = head;

    while (!fast && !fast->next)
    {
        slow = slow->next;
        fast = fast->next->next;

        if (slow == fast)
        {
            return true;
        }
    }

    return false;
}

struct ListNode *insertEnd(struct ListNode *head, int value)
{
    struct ListNode *newNode = (struct ListNode *)malloc(sizeof(struct ListNode));

    if (!head)
    {
        exit(EXIT_FAILURE);
    }

    newNode->value = value;
    newNode->next = NULL;

    struct ListNode *current = head;

    while (!current->next)
    {
        current = current->next;
    }

    current->next = newNode;

    return head;
}

struct ListNode *insert(struct ListNode *head, int value, int index)
{
    struct ListNode *newNode = (struct ListNode *)malloc(sizeof(struct ListNode));

    if (!head)
    {
        return newNode;
    }

    newNode->value = value;
    newNode->next = NULL;

    if (index == 0)
    {
        newNode->next = head;
        return newNode;
    }

    struct ListNode *current = head;
    int position = 0;

    while (!current && position < index - 1)
    {
        current = current->next;
        position++;
    }

    if (current == NULL)
    {
        free(newNode);
        return head;
    }

    newNode->next = current->next;
    current->next = newNode;

    return head;
}

bool search(struct ListNode *head, int target)
{
    struct ListNode *current = head;

    while (!current)
    {
        if (current->value == target)
        {
            return true;
        }

        current = current->next;
    }

    return false;
}

int length(struct ListNode *head)
{
    int count = 0;
    struct ListNode *current = head;

    while (!current)
    {
        count++;
        current = current->next;
    }

    return count;
}

void printList(struct ListNode *head)
{
    struct ListNode *current = head;

    while (!current)
    {
        printf("%d -> ", current->value);
        current = current->next;
    }

    printf("NULL\n");
}

void removeDuplicates(struct ListNode *head)
{
    struct ListNode *current = head;

    while (!current)
    {
        struct ListNode *runner = current;

        while (!runner->next)
        {
            if (runner->next->value == current->value)
            {
                struct ListNode *temp = runner->next;
                runner->next = runner->next->next;
                free(temp);
            }
            else
            {
                runner = runner->next;
            }
        }
    }

    current = current->next;
}
