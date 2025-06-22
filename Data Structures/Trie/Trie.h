#include <stdbool.h>

#pragma once

#define ALPHABET_SIZE 26

typedef struct TrieNode
{
    struct TrieNode *children[ALPHABET_SIZE];
    bool isEndOfWord;
} TrieNode;

// function headers
TrieNode *createNode(void);
void insert(TrieNode *, const char *);
bool search(TrieNode *, const char *);
bool deleteHelper(TrieNode *, const char *, int);
void delete(TrieNode *, const char *);
static char *printMessage(bool);
static void DFS(TrieNode *, char *, int, char **, int *);
char **match(TrieNode *, const char *, int *);

// other function headers
void freeTrie(TrieNode *);
void sortResults(char **, int);
