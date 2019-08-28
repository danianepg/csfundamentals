# Notes on CS Fundamentals && Java

## BigO Notation
- Indicate the performance of the algorithm.
- How well an algorithm scales as the amount of data increases.
- Not about speed but scalability.
- O(1) perfoms the same no matter how big data are
- O(n) worst case it will increase to the same size of data
- O(n²) for each item needs to go through the array twice. Ex.: bubble sort
- O(log n) as long as the amount of data increases, the rest of the amount of data takes less time. Ex.: binary search

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
| Add/Remove | Poor. Linear time O(n) | Good. Fixed time O(1) |
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

### Insertion Sort 
- O(n²)
- Set a marker for the first sorted section after the first element.
- Repeat:
	- select first unsorted
	- swap others to the right
	- advance marker
- Use for tiny arrays
	
### Bubble Sort
- Runtime O(n²). Memory O(1)
- Start at the beginning of the array and swap the first 2 elements if the first is greater than the second. Go to the next pair and so on.  

### Selection Sort
- Runtime O(n²). Memory O(1)
- Find the smallest element using linear scan and move it to the front swapping it. Then find the second smallest and move again. 

### Merge Sort
- Runtime O(n log(n)). Memory: depends.
- Divide & Conquer algorithm.
- Divide arrays in half, sort each of those halves and then merge them back together. Eventually you´re merging just 2 single elements  array.

### Quick Sort
- Runtime O(n log(n)) average or O(n²) worst. Memory O(log(n)).
- Pick up and element and partition the array. All the elements that are less than the partitioning come before than all elements that are greater than it. 

### Radix Sort
- Runtime O(kn)
- For integers and other data types. Iterate through each digit of the number, grouping numbers by each digit. Then sort each of these grouping by the next digit. Repeat sorting by each subsequent digit.

## Searching

### Binary Search
Look up for an element in a sorted array by first comparing x to the midpoint. Repeat until the array has  size 0.



