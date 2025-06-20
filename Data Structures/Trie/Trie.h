#pragma once

#define ALPHABET_SIZE 26

typedef struct TrieNode
{
    struct TrieNode *children[ALPHABET_SIZE];
    bool isEndOfWord;
} TrieNode;

TrieNode *createNode();
void insert(TrieNode *, const char *);
bool search(TrieNode *, const char *);
bool deleteHelper(TrieNode *, const char *, int);
void delete(TrieNode *, const char *);
