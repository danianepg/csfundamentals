# Notes on Computer Science Fundamentals X Java

## BigO Notation
- Indicate the performance of the algorithm.
- How well an algorithm scales as the amount of data increases.
- Not about speed but scalability.

## Data Structures

### LinkedList
 - Linked lists in Java implement Deque (double ended queue). 
 - Inefficient to do search access.
 - Sequential access.

### Arrays
 - Direct access: O(1)
 
### Arrays x Linked Lists 
|  | Arrays | Linked Lists |
|--|--|--|
| Direct access | Good. Fixed time O(1) | Poor. Linear time O(n) |
| Add/Remove | Poor. Linear time O(n) | Good. Fixed time (1) |
| Search | O(n) linear search; <br> O(log n) binary search | O(n) linear search |

### Stacks 
 - LIFO
 - pop(): remove and return item
 - peek(): get the element but doesn't return it
 - push(): insert element

### Queues
- FIFO

### Associative Arrays (HashMap, HashTable, ConcurrentHashMap)
- Way of describe data structures that have pairs of keys and values. 
- Hash functions are one-way

#### HashTable
- For multiple threads applications.
- Highly efficient lookup
- Has problems with collision
- To avoid collision,the array size must be the same size as the amount of data
- To not have big arrays, we can store a LinkedList at the index
- We can implement the HashTable with a binary search tree and guarantee O(log n) lookup time since the tree is balanced

#### HashMap
- Faster than HashTable because is made for single thread applications.

### Set
- Unordered collection
- No index, sequence or key
- No duplicates
- Fast lookup
- Implementation in Java: HashSet

### Trees
- Imposes that for all nodes, the left children are less than or equals to the current node, which is less than all the right nodes
- Can be balanced or unbalanced
- Full vs complete: all leaves are at the bottom of the tree and all non-leaf nodes have exactly two children. (rare)

#### Binary Trees
- Has at maximum 2 child nodes
- Used to implement Binary Search Tree (BST)
- Naturally stays sorted
- Left child must be lower than the parent
- Right child must be greater than the parent
- Can´t have duplicates
- Implementation in Java: TreeMap (self balanced)

| BST | HashTable |
|--|--|
| Fast insert, fast retrieval | Fast insertion, fast retrieval |
| Stay sorted. Iterate elements in sequence | Retrieval not in guaranteed order |

###  Heaps
- Are implemented as binary trees
- Min heap: lowest value at the top (child > parent)
- Max heap: greatest value at the top (parent > child)
- Not fully sorted data structure
- Does less reorganization than binary trees
- Implementation in Java: PriorityQueue

### Graphs
- Collections of nodes where any node can connect to any and multiple nodes.
- Direct or indirect direction
- Can be weighted
- No implementation in Java because it is a general concept. Implementation is specific for different types
- LinkedList, Tree, Heaps are kind of graphs.
 
### Data Structures Comparison

|  | Strengths | Weaknesses |
|--|--|--|
| Arrays |  direct index; <br> easy to create and use | sorting and searching; <br>inserting and deleting - particularly if not at the start or end |
| LinkedLists |  insert/delete; <br> iterating through the collection | direct access; <br> searching and sorting |
| Stacks and Queues | designed for LIFO/FIFO | direct access; <br> searching and sorting |
| Hash Tables | speed of insertion and deletion; <br> speed of acccess | some overhead; retrieving in a sorted order; searching for specific value |
| Sets | checking if an object is in a collection; avoiding duplicates | direct access; <br> everything else |
| Binary Search Trees | speed of insertion and deletions; speed of access; maitaining sorted order | some overhead |

## Sorting

### Bubble Sort
### Selection Sort
### Merge Sort
### Quick Sort
### Radix Sort



