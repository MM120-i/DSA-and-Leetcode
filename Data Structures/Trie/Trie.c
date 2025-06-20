#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

#include "Trie.h"

int main(void)
{
    TrieNode *root = createNode();

    insert(root, "apple");
    insert(root, "app");
    insert(root, "apartment");

    printf("%s\n", search(root, "apple") ? "true" : "false");
    printf("%s\n", search(root, "apartment") ? "true" : "false");
    printf("%s\n", search(root, "appl") ? "true" : "false");
    printf("Deleting the word 'app'\n");

    delete(root, "app");

    printf("%s\n", search(root, "app") ? "true" : "false");

    return 0;
}

/**
 * Creating a new Trie Node
 */
TrieNode *createNode()
{
    TrieNode *node = (TrieNode *)malloc(sizeof(TrieNode));
    node->isEndOfWord = false;

    if (!node)
    {
        printf("Memory allocation failed while creating a node :(");
        exit(EXIT_FAILURE);
    }

    for (size_t i = 0; i < ALPHABET_SIZE; i++)
    {
        node->children[i] = NULL;
    }

    return node;
}

void insert(TrieNode *root, const char *word)
{
    TrieNode *node = root;

    for (size_t i = 0; word[i] != '\0'; i++)
    {
        int index = word[i] - 'a';

        if (!node->children[index])
        {
            node->children[index] = createNode();
        }

        node = node->children[index];
    }

    node->isEndOfWord = true;
}

bool search(TrieNode *root, const char *word)
{
    TrieNode *node = root;

    for (size_t i = 0; word[i] != '\0'; i++)
    {
        int index = word[i] - 'a';

        if (!node->children[index])
        {
            return false;
        }

        node = node->children[index];
    }

    return node->isEndOfWord;
}

bool deleteHelper(TrieNode *node, const char *word, int depth)
{
    if (!node)
    {
        return false;
    }

    // Base case: Reaching the end of word
    if (depth == strlen(word))
    {
        if (!node->isEndOfWord)
        {
            return false;
        }

        node->isEndOfWord = false;

        // if node has no children we can delete it
        for (size_t i = 0; i < ALPHABET_SIZE; i++)
        {
            // checking if the node has children then we cant delete
            if (node->children[i])
            {
                return false;
            }
        }

        return true;
    }

    int index = word[depth] - 'a';

    if (!node->children[index])
    {
        return false;
    }

    bool shouldDeleteChild = deleteHelper(node->children[index], word, depth + 1);

    if (shouldDeleteChild)
    {
        free(node->children[index]);
        node->children[index] = NULL;

        // checking if current node is still needed
        if (!node->isEndOfWord)
        {
            for (size_t i = 0; i < ALPHABET_SIZE; i++)
            {
                if (node->children[i])
                {
                    return false;
                }

                return true;
            }
        }
    }

    return false;
}

void delete(TrieNode *root, const char *word)
{
    deleteHelper(root, word, 0);
}
