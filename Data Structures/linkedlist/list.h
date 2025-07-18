#pragma once

#include <stdbool.h>

typedef struct ListNode
{
    int value;
    struct ListNode *next;
} ListNode;

typedef struct DListNode
{
    int value;
    struct DListNode *previous;
    struct DListNode *next;
} DListNode;

// Singly Linked List
int findLength(ListNode *);
ListNode *deleteNode(ListNode *, int);
ListNode *fastandSlow(ListNode *);
ListNode *reverse(ListNode *);
ListNode *merge(ListNode *, ListNode *);
ListNode *delete(ListNode *, int);
bool hasCycle(ListNode *);
ListNode *insertEnd(ListNode *, int);
ListNode *insert(ListNode *, int, int);
bool search(ListNode *, int);
int length(ListNode *);
void printList(ListNode *);
void removeDuplicates(ListNode *);
ListNode *create(int);

// Doubly Linked List
DListNode *createNode(int);
DListNode *insertAtHead(DListNode *, int);
DListNode *insertAtTail(DListNode *, int);
DListNode *deleteByValue(DListNode *, int);
bool searchDList(DListNode *, int);
void printListForward(DListNode *);
void printListBackward(DListNode *);
void freeList(DListNode *);
