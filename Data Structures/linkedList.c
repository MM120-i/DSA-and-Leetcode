#include <stdio.h>
#include <stdlib.h>

void Insert(struct Node *, int x);
void Print(struct Node *);
void Delete(int n);
struct Node *Reverse(struct Node *);
void reverseRecursion(struct Node *);

struct Node
{
    int data;
    struct Node *next;
};

struct Node *head;

int main(void)
{
    struct Node *head = NULL; // empty
    printf("How many numbers?\n");
    int n, x;
    scanf("%d", &n);

    // Example usage
    for (size_t i = 0; i < n; i++)
    {
        printf("Enter number \n");
        scanf("%d", &x);
        Insert(head, x);
        Print(head);
    }

    return 0;
}

void Insert(struct Node *head, int x)
{
    struct Node *temp = (struct Node *)malloc(sizeof(struct Node));
    temp->data = x;
    temp->next = head;
    head = temp;
}

void Print(struct Node *head)
{
    printf("The list is: ");

    while (head != NULL)
    {
        printf(" %d", head->data);
        head = head->next;
    }

    printf("\n");
}

void Delete(int n)
{
    struct Node *temp1 = head;

    if (n == 1)
    {
        head = temp1->next; // head points to second node
        free(temp1);
        return;
    }

    for (size_t i = 0; i < n - 2; i++)
    {
        temp1 = temp1->next; // points to the n - 1th node
    }

    struct Node *temp2 = temp1->next; // nth node
    temp1->next = temp2->next;        // n + 1th node
    free(temp2);                      // delete temp2
}

// Reverse with iteration
struct Node *Reverse(struct Node *head)
{
    struct Node *current, *prev, *next;
    current = head;
    prev = NULL;

    while (current != NULL)
    {
        next = current->next;
        current->next = prev;
        prev = current;
        current = next;
    }

    head = prev;
    return head;
}

// Reverse with recursion
void reverseRecursion(struct Node *p)
{
    if (p == NULL)
    {
        printf("\n");
        return;
    }

    reverseRecursion(p->next);
    printf("%d ", p->data);
}
