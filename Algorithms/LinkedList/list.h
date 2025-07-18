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
int findLength(struct ListNode *);
struct ListNode *deleteNode(struct ListNode *, int);
struct ListNode *fastandSlow(struct ListNode *);
struct ListNode *reverse(struct ListNode *);
struct ListNode *merge(struct ListNode *, struct ListNode *);
struct ListNode *delete(struct ListNode *, int);
bool hasCycle(struct ListNode *);
struct ListNode *insertEnd(struct ListNode *, int);
struct ListNode *insert(struct ListNode *, int, int);
bool search(struct ListNode *, int);
int length(struct ListNode *);
void printList(struct ListNode *);
void removeDuplicates(struct ListNode *);

// Leetcode Problem: List Palindrome
bool isPalindrome(ListNode *);

// Doubly Linked List
DListNode *createNode(int);
DListNode *insertAtHead(DListNode *, int);
DListNode *insertAtTail(DListNode *, int);
DListNode *deleteByValue(DListNode *, int);
bool searchDList(DListNode *, int);
void printListForward(DListNode *);
void printListBackward(DListNode *);
void freeList(DListNode *);
