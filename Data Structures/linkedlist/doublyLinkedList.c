#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#include "list.h"

DListNode *createNode(int value)
{
    DListNode *newNode = (DListNode *)malloc(sizeof(DListNode));
    newNode->value = value;
    newNode->previous = NULL;
    newNode->next = NULL;

    return newNode;
}

DListNode *insertAtHead(DListNode *head, int value)
{
    DListNode *newNode = createNode(value);
    newNode->next = head;

    if (head)
    {
        head->previous = newNode;
    }

    return newNode;
}

DListNode *insertAtTail(DListNode *head, int value)
{
    DListNode *newNode = createNode(value);

    if (!head)
    {
        return newNode;
    }

    DListNode *current = head;

    while (current->next)
    {
        current = current->next;
    }

    current->next = newNode;
    newNode->previous = current;

    return head;
}

DListNode *deleteByValue(DListNode *head, int value)
{
    DListNode *current = head;

    while (current && current->value != value)
    {
        current = current->next;
    }

    if (!current)
    {
        return head;
    }

    if (current->previous)
    {
        current->previous->next = current->next;
    }
    else
    {
        head = current->next;
    }

    if (current->next)
    {
        current->next->previous = current->previous;
    }

    free(current);

    return head;
}

bool searchDList(DListNode *head, int value)
{
    DListNode *current = head;

    while (current)
    {
        if (current->value == value)
        {
            return true;
        }

        current = current->next;
    }

    return false;
}

void printListForward(DListNode *head)
{
    DListNode *current = head;

    while (current)
    {
        printf("%d ", current->value);
        current = current->next;
    }

    printf("\n");
}

void printListBackward(DListNode *head)
{
    DListNode *current = head;

    if (!current)
    {
        return;
    }

    while (current->next)
    {
        current = current->next;
    }

    while (current)
    {
        printf("%d ", current->value);
        current = current->previous;
    }

    printf("\n");
}

void freeList(DListNode *head)
{
    DListNode *current = head;

    while (current)
    {
        DListNode *temp = current;
        current = current->next;
        free(temp);
    }
}
