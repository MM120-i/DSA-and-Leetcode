#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

#include "Trie.h"

int main(void)
{
    TrieNode *root = createNode();
    char *words[] = {"apple", "app", "apartment", "ap", "apricot"};
    int length = sizeof(words) / sizeof(words[0]);

    for (size_t i = 0; i < length; i++)
    {
        insert(root, words[i]);
    }

    printf("%s\n", printMessage(search(root, "apple")));
    printf("%s\n", printMessage(search(root, "apartment")));
    printf("%s\n", printMessage(search(root, "appl")));
    printf("Deleting the word 'app'\n");

    delete(root, "app");

    printf("%s\n", printMessage(search(root, "app")));

    int size;
    char **matches = match(root, "ap", &size);

    printf("Words matching 'ap': \n");

    for (size_t i = 0; i < size; i++)
    {
        printf(" - %s\n", matches[i]);
        free(matches[i]);
    }

    free(matches);

    return 0;
}

static char *printMessage(bool flag)
{
    if (flag)
    {
        return "true";
    }

    return "false";
}

/**
 * Creating a new Trie Node
 */
TrieNode *createNode(void)
{
    TrieNode *node = (TrieNode *)malloc(sizeof(TrieNode));
    node->isEndOfWord = false;

    if (!node)
    {
        perror("Memory allocation failed while creating a node :(");
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
            }

            return true;
        }
    }

    return false;
}

void delete(TrieNode *root, const char *word)
{
    deleteHelper(root, word, 0);
}

static void DFS(TrieNode *node, char *prefix, int length, char **results, int *count)
{
    if (node->isEndOfWord)
    {
        prefix[length] = '\0';
        results[*count] = strdup(prefix);
        (*count)++;
    }

    for (size_t i = 0; i < ALPHABET_SIZE; i++)
    {
        if (node->children[i])
        {
            prefix[length] = 'a' + i;
            DFS(node->children[i], prefix, length + 1, results, count);
        }
    }
}

char **match(TrieNode *root, const char *prefix, int *returnSize)
{
    TrieNode *node = root;
    int length = 0;

    // Traverse the trie to find the prefix node
    for (size_t i = 0; prefix[i] != '\0'; i++)
    {
        int index = prefix[i] - 'a';

        if (!node->children[index])
        {
            *returnSize = 0;
            return NULL; // Prefix not found
        }

        node = node->children[index];
        length++;
    }

    char **results = (char **)malloc(1000 * sizeof(char *));
    char *currentPrefix = (char *)malloc(100 * sizeof(char));

    if (results == NULL || currentPrefix == NULL)
    {
        perror("Memory allocation failed while matching.");
        exit(EXIT_FAILURE);
    }

    strcpy(currentPrefix, prefix);

    *returnSize = 0;
    DFS(node, currentPrefix, strlen(prefix), results, returnSize);

    free(currentPrefix);
    return results;
}

void freeTrie(TrieNode *root)
{
    if (!root)
    {
        return;
    }

    for (size_t i = 0; i < ALPHABET_SIZE; i++)
    {
        if (root->children[i])
        {
            freeTrie(root->children[i]);
        }
    }

    free(root);
}

void sortResults(char **arr, int size)
{
    for (size_t i = 0; i < size - 1; i++)
    {
        for (size_t j = i + 1; j < size; j++)
        {
            if (strcmp(arr[i], arr[j]) > 0)
            {
                char *temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
    }
}
