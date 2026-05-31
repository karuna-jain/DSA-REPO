# 🚀 Data Structures & Algorithms (DSA) Portfolio

Welcome to my **Data Structures and Algorithms (DSA)** repository. This repository showcases optimized solutions to core algorithmic problems, primarily sourced from LeetCode. Each solution is implemented in **Java** with an emphasis on **clean code, proper engineering principles, and optimal time & space complexity**.

---

## 🛠️ Repository Architecture

This repository is structured around **Algorithmic Patterns**. Grouping problems by patterns rather than individual topics helps build strong mental models for identifying solutions to new, unseen problems.

```text
DSA-REPO/
├── Arrays/
│   ├── TwoPointer/           # Left-right pointers, multi-pointer sorting, boundaries
│   ├── Kadanes/              # Maximum/minimum subarray sum optimization
│   ├── Sorting/              # Core sorting algorithm implementations
│   └── searching/            # Core searching algorithm implementations
├── Sliding_window/           # Dynamic & fixed-size subarray scanning
├── FastAndSlowPointers/       # Floyd's cycle detection, list manipulation
├── Trees/                    # Hierarchical traversal & binary search tree patterns
├── dp/                       # Memoization, tabulation, & string alignment
└── Backtrack/                # Recursive search spaces, permutations, & chess problems
```

---

## 📚 Interactive Problem Catalog

Below is a detailed inventory of the problems solved, grouped by pattern, highlighting the optimal complexities achieved.

### 1. Two-Pointer & Boundaries Pattern
Optimizing search/manipulation on sorted structures by moving pointers inward or using fast/slow scanners.

| # | Problem | Source Link | Solution Code | Optimal Time | Space | Core Approach |
|---|---------|-------------|---------------|:------------:|:-----:|--------------|
| 1 | Two Sum (Sorted) | [LeetCode](https://leetcode.com/problems/two-sum/) | [TwoSum.java](Arrays/TwoPointer/TwoSum.java) | $O(N)$ | $O(1)$ | Left & right pointers moving inward |
| 2 | Reverse Array | [Custom](https://leetcode.com/) | [reverse.java](Arrays/TwoPointer/reverse.java) | $O(N)$ | $O(1)$ | Swapping elements from outer boundaries inward |
| 3 | Remove Duplicates from Sorted Array | [LeetCode 26](https://leetcode.com/problems/remove-duplicates-from-sorted-array/) | [RemoveDuplicates.java](Arrays/TwoPointer/RemoveDuplicates.java) | $O(N)$ | $O(1)$ | Slow pointer tracking unique slot, fast scanning |
| 4 | Squares of a Sorted Array | [LeetCode 977](https://leetcode.com/problems/squares-of-a-sorted-array/) | [SquaresSortedArray.java](Arrays/TwoPointer/SquaresSortedArray.java) | $O(N)$ | $O(N)$ | Compare absolute values at boundaries, fill output from end |
| 5 | 3Sum | [LeetCode 15](https://leetcode.com/problems/3sum/) | [ThreeSum.java](Arrays/TwoPointer/ThreeSum.java) | $O(N^2)$ | $O(\log N)$ | Sort, loop first element, then two-pointer remaining |
| 6 | 3Sum Closest | [LeetCode 16](https://leetcode.com/problems/3sum-closest/) | [ThreeSumClosest.java](Arrays/TwoPointer/ThreeSumClosest.java) | $O(N^2)$ | $O(\log N)$ | Sort, search pairs, track minimum absolute difference |
| 7 | 4Sum | [LeetCode 18](https://leetcode.com/problems/4sum/) | [FourSum.java](Arrays/TwoPointer/FourSum.java) | $O(N^3)$ | $O(\log N)$ | Sort, double loops, two-pointer inner pairs with long overflow checks |
| 8 | Container With Most Water | [LeetCode 11](https://leetcode.com/problems/container-with-most-water/) | [ContainerWithMostWater.java](Arrays/TwoPointer/ContainerWithMostWater.java) | $O(N)$ | $O(1)$ | Two pointers at boundaries, shrink shorter line |
| 9 | Triplets with Smaller Sum | [LeetCode 259](https://leetcode.com/problems/3sum-smaller/) | [ThreeSumSmaller.java](Arrays/TwoPointer/ThreeSumSmaller.java) | $O(N^2)$ | $O(\log N)$ | Sort, if sum < target, add `right - left` possible options |
| 10 | Sort Colors (Dutch National Flag) | [LeetCode 75](https://leetcode.com/problems/sort-colors/) | [DutchNationalFlag.java](Arrays/TwoPointer/DutchNationalFlag.java) | $O(N)$ | $O(1)$ | Three pointers (low, mid, high) grouping 0s, 1s, and 2s in 1 pass |
| 11 | Backspace String Compare | [LeetCode 844](https://leetcode.com/problems/backspace-string-compare/) | [BackspaceCompare.java](Arrays/TwoPointer/BackspaceCompare.java) | $O(N+M)$ | $O(1)$ | Two pointers scanning right-to-left, tracking backspace counts |
| 12 | Trapping Rain Water | [LeetCode 42](https://leetcode.com/problems/trapping-rain-water/) | [TrappingRainWater.java](Arrays/TwoPointer/TrappingRainWater.java) | $O(N)$ | $O(1)$ | Two pointers tracking leftMax and rightMax boundaries |
| 13 | Minimum Size Subarray Sum | [LeetCode 209](https://leetcode.com/problems/minimum-size-subarray-sum/) | [MinSizeSubarraySum.java](Arrays/TwoPointer/MinSizeSubarraySum.java) | $O(N)$ | $O(1)$ | Sliding window, expand right, shrink left when sum $\ge$ target |

### 2. Fast & Slow Pointer Pattern
Detecting cycles, cycle entry points, or finding middle nodes of linear structures.

| # | Problem | Source Link | Solution Code | Optimal Time | Space | Core Approach |
|---|---------|-------------|---------------|:------------:|:-----:|--------------|
| 1 | Linked List Cycle | [LeetCode 141](https://leetcode.com/problems/linked-list-cycle/) | [LinkedListCycle.java](FastAndSlowPointers/LinkedListCycle.java) | $O(N)$ | $O(1)$ | Tortoise & Hare meeting detection |
| 2 | Middle of the Linked List | [LeetCode 876](https://leetcode.com/problems/middle-of-the-linked-list/) | [MiddleOfLinkedList.java](FastAndSlowPointers/MiddleOfLinkedList.java) | $O(N)$ | $O(1)$ | Fast moves 2x steps, slow moves 1x steps |
| 3 | Happy Number | [LeetCode 202](https://leetcode.com/problems/happy-number/) | [HappyNumber.java](FastAndSlowPointers/HappyNumber.java) | $O(\log N)$ | $O(1)$ | Fast & slow pointers tracking state transition cycle |
| 4 | Find Cycle Start in Linked List | [LeetCode 142](https://leetcode.com/problems/linked-list-cycle-ii/) | [LinkedListCycleStart.java](FastAndSlowPointers/LinkedListCycleStart.java) | $O(N)$ | $O(1)$ | Floyd's cycle detection + head reset and linear meeting |
| 5 | Palindrome Linked List | [LeetCode 234](https://leetcode.com/problems/palindrome-linked-list/) | [PalindromeLinkedList.java](FastAndSlowPointers/PalindromeLinkedList.java) | $O(N)$ | $O(1)$ | Midpoint split, reverse second half, comparison, list restore |
| 6 | Rearrange Linked List (Odd-Even) | [LeetCode 328](https://leetcode.com/problems/odd-even-linked-list/) | [OddEvenLinkedList.java](FastAndSlowPointers/OddEvenLinkedList.java) | $O(N)$ | $O(1)$ | Interleaved linking of odd/even pointers, end-linking |

### 3. Sliding Window Pattern
Tracking a subsegment of an array/string that dynamically expands or shrinks based on criteria.

| # | Problem | Source Link | Solution Code | Optimal Time | Space | Core Approach |
|---|---------|-------------|---------------|:------------:|:-----:|--------------|
| 1 | Fruit Into Baskets | [LeetCode 904](https://leetcode.com/problems/fruit-into-baskets/) | [fruits_basket.java](Sliding_window/fruits_basket.java) | $O(N)$ | $O(1)$ | Map frequency tracker within window of at most 2 keys |
| 2 | Longest Repeating Character Replacement | [LeetCode 424](https://leetcode.com/problems/longest-repeating-character-replacement/) | [repeating_char.java](Sliding_window/repeating_char.java) | $O(N)$ | $O(1)$ | Keep track of max frequency char in dynamic window |
| 3 | Maximum Average Subarray I | [LeetCode 643](https://leetcode.com/problems/maximum-average-subarray-i/) | [subarray_sum.java](Sliding_window/subarray_sum.java) | $O(N)$ | $O(1)$ | Fixed size sliding window summation |

### 4. Core Sorting & Searching
Classic base implementations of sorting and binary/linear search.

| Topic | Algorithm | Code Link | Time Complexity (Avg) | Space Complexity |
|-------|-----------|-----------|:---------------------:|:----------------:|
| Sorting | Bubble Sort | [BubbleSort.java](Arrays/Sorting/BubbleSort.java) | $O(N^2)$ | $O(1)$ |
| Sorting | Insertion Sort | [Insertion.java](Arrays/Sorting/Insertion.java) | $O(N^2)$ | $O(1)$ |
| Sorting | Selection Sort | [SelectionSort.java](Arrays/Sorting/SelectionSort.java) | $O(N^2)$ | $O(1)$ |
| Sorting | Merge Sort | [mergeSort.java](Arrays/Sorting/mergeSort.java) | $O(N \log N)$ | $O(N)$ |
| Sorting | Quick Sort | [quickSort.java](Arrays/Sorting/quickSort.java) | $O(N \log N)$ | $O(\log N)$ |
| Searching | Binary Search | [BinarySearch.java](Arrays/searching/BinarySearch.java) | $O(\log N)$ | $O(1)$ |
| Searching | Linear Search | [LinearSearch.java](Arrays/searching/LinearSearch.java) | $O(N)$ | $O(1)$ |

### 5. Dynamic Programming, Trees & Backtracking
Complex recursive optimization problems, hierarchical representations, and state space searches.

| Pattern | Problem | Source Link | Code Link | Key Approach |
|---------|---------|-------------|-----------|--------------|
| Trees | Vertical Order Sum | [LeetCode 987](https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/) | [verticalSum.java](Trees/verticalSum.java) | TreeMap tracking of horizontal distance coordinates |
| DP | Edit Distance | [LeetCode 72](https://leetcode.com/problems/edit-distance/) | [edit_distance.java](dp/edit_distance.java) | Bottom-up 2D grid dynamic programming |
| DP | Word Wrap | [GeeksforGeeks](https://www.geeksforgeeks.org/word-wrap-problem-dp-19/) | [wordWrap.java](dp/wordWrap.java) | Recursion + Memoization computing minimum extra space squares |
| Backtrack | N-Queens Puzzle | [LeetCode 51](https://leetcode.com/problems/n-queens/) | [NQueen.java](Backtrack/NQueen.java) | Row-by-row recursive placement checking column/diagonal safety |
| Backtrack | Permutations | [LeetCode 46](https://leetcode.com/problems/permutations/) | [permutation.java](Backtrack/permutation.java) | Backtracking swap-based permutation space exploration |

---

## 💻 Setup & Local Execution Guide

To run any of the solutions locally on your system, follow these simple terminal commands.

### Prerequisites
Make sure you have the Java Development Kit (JDK 11 or higher) installed. You can check your version with:
```bash
java -version
```

### Steps to Run

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/karuna-jain/DSA-REPO.git
   cd DSA-REPO
   ```

2. **Compile the desired file**:
   For example, to compile `DutchNationalFlag.java` under `Arrays/TwoPointer/`:
   ```bash
   javac Arrays/TwoPointer/DutchNationalFlag.java
   ```

3. **Run the compiled file**:
   Since the Java files do not contain package declarations, run them by specifying the classpath:
   ```bash
   java -cp Arrays/TwoPointer DutchNationalFlag
   ```

---

## ✨ Code Quality Standards

Every solution in this repository conforms to these core engineering standards:
* **Zero Libraries When Auxiliary**: Native, basic data structures are preferred to show true logic mechanics.
* **In-place Operations**: Optimized for $O(1)$ auxiliary space wherever possible.
* **Readable Pointer Naming**: Avoids overly obscure names; uses descriptive pointers (`low`/`mid`/`high`, `left`/`right`, `slow`/`fast`).
* **Self-Contained Executables**: All solution classes feature a `main` method with diverse, preconfigured test cases for easy execution and instant validation.

---
*Developed with ❤️ by Karuna Jain.*
