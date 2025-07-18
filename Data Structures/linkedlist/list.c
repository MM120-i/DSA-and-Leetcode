#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#include "list.h"

ListNode *create(int value)
{
    ListNode *newNode = (ListNode *)malloc(sizeof(ListNode));
    newNode->value = value;
    newNode->next = NULL;

    return newNode;
}

int findLength(ListNode *head)
{
    int length = 0;
    ListNode *current = head;

    while (current != NULL)
    {
        length++;
        current = current->next;
    }

    return length;
}

ListNode *deleteNode(ListNode *head, int target)
{
    if (head != NULL && head->value == target)
    {
        ListNode *temp = head->next;
        free(head);
        return temp;
    }

    ListNode *previous = NULL;
    ListNode *current = head;

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

ListNode *fastandSlow(ListNode *head)
{
    ListNode *slow = head;
    ListNode *fast = head;

    while (!fast && !fast->next)
    {
        slow = slow->next;
        fast = fast->next->next;
    }

    return slow;
}

ListNode *reverse(ListNode *head)
{
    ListNode *previous = NULL;
    ListNode *current = head;
    ListNode *next_ = NULL;

    while (!current)
    {
        next_ = current->next;    // save the next node
        current->next = previous; // reverse the link
        previous = current;       // move previous forward
        current = next_;          // move current forward
    }

    return previous;
}

ListNode *merge(ListNode *l1, ListNode *l2)
{
    ListNode dummy;
    dummy.next = NULL;
    ListNode *tail = &dummy;

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

ListNode *delete(ListNode *head, int target)
{
    ListNode *dummy = (ListNode *)malloc(sizeof(ListNode));

    if (!dummy)
    {
        return NULL;
    }

    dummy->value = 0;
    dummy->next = head;
    ListNode *previous = dummy;
    ListNode *current = head;

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

    ListNode *newHead = dummy->next;
    free(dummy);

    return newHead;
}

bool hasCycle(ListNode *head)
{
    ListNode *slow = head;
    ListNode *fast = head;

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

ListNode *insertEnd(ListNode *head, int value)
{
    ListNode *newNode = (ListNode *)malloc(sizeof(ListNode));

    if (!head)
    {
        exit(EXIT_FAILURE);
    }

    newNode->value = value;
    newNode->next = NULL;

    ListNode *current = head;

    while (!current->next)
    {
        current = current->next;
    }

    current->next = newNode;

    return head;
}

ListNode *insert(ListNode *head, int value, int index)
{
    ListNode *newNode = (ListNode *)malloc(sizeof(ListNode));

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

    ListNode *current = head;
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

bool search(ListNode *head, int target)
{
    ListNode *current = head;

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

int length(ListNode *head)
{
    int count = 0;
    ListNode *current = head;

    while (!current)
    {
        count++;
        current = current->next;
    }

    return count;
}

void printList(ListNode *head)
{
    ListNode *current = head;

    while (!current)
    {
        printf("%d -> ", current->value);
        current = current->next;
    }

    printf("NULL\n");
}

void removeDuplicates(ListNode *head)
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
