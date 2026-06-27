# 🚀 Data Structures & Algorithms (DSA) Portfolio

Welcome to my **Data Structures and Algorithms (DSA)** repository. This repository showcases optimized solutions to core algorithmic problems, primarily sourced from LeetCode. Each solution is implemented in **Java** with an emphasis on **clean code, proper engineering principles, and optimal time & space complexity**.

💻 **Interactive Web Dashboard**: Open [dashboard/index.html](dashboard/index.html) directly in your browser to search, filter, and visually explore all solutions, statistics, and Java source code with an integrated dark-themed code viewer!

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
│       └── BinarySearch/     # Modified binary search patterns (ceil, range, rotated, peak)
├── Sliding_window/           # Dynamic & fixed-size subarray scanning
├── FastAndSlowPointers/       # Floyd's cycle detection, list manipulation
├── InPlaceLinkedListReversal/ # In-place linked list reversal & rotation patterns
├── CyclicSort/               # In-place range sorting, missing/duplicate number detection
├── MergeIntervals/           # Interval merging, insertions, and intersections
├── Trees/                    # Hierarchical traversal & binary search tree patterns
│   └── BFS/                  # Breadth-first level-order traversals & patterns
├── dp/                       # Memoization, tabulation, & string alignment
├── Backtrack/                # Recursive search spaces, permutations, & chess problems
├── Matrix/                   # 2D grid traversals, searching, and sorting patterns
└── String/                   # String manipulation, rotations, shuffles, and patterns
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
| 14 | Next Permutation | [LeetCode 31](https://leetcode.com/problems/next-permutation/) | [NextPermutation.java](Arrays/TwoPointer/NextPermutation.java) | $O(N)$ | $O(1)$ | Find pivot, find successor, swap, and reverse suffix |
| 15 | Pair with Given Difference | [GeeksforGeeks](https://www.geeksforgeeks.org/find-a-pair-with-the-given-difference/) | [PairWithDifference.java](Arrays/TwoPointer/PairWithDifference.java) | $O(N \log N)$ | $O(1)$ | Sort, two-pointer scan with pointer adjustment based on difference |
| 16 | Four Elements Sum to Given Value | [GeeksforGeeks](https://www.geeksforgeeks.org/find-four-elements-that-sum-to-a-given-value/) | [FourSumElements.java](Arrays/TwoPointer/FourSumElements.java) | $O(N^2)$ | $O(N^2)$ | HashMap-based pair sums finding target - current pair sum |

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
| 7 | Find the Duplicate Number (Floyd's) | [LeetCode 287](https://leetcode.com/problems/find-the-duplicate-number/) | [FindDuplicateFloyd.java](FastAndSlowPointers/FindDuplicateFloyd.java) | $O(N)$ | $O(1)$ | Fast & slow pointers meeting in array index cycle |
| 8 | Detect and Remove Loop in Linked List | [GeeksforGeeks](https://www.geeksforgeeks.org/detect-and-remove-loop-in-a-linked-list/) | [RemoveLinkedListCycle.java](FastAndSlowPointers/RemoveLinkedListCycle.java) | $O(N)$ | $O(1)$ | Floyd's cycle detection + head reset and breaking the cycle |

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
| Searching | Binary Search | [BinarySearch.java](Arrays/searching/BinarySearch/BinarySearch.java) | $O(\log N)$ | $O(1)$ |
| Searching | Linear Search | [LinearSearch.java](Arrays/searching/LinearSearch.java) | $O(N)$ | $O(1)$ |
| Searching | [Min/Max of Array (Min Comparisons)](https://www.geeksforgeeks.org/maximum-and-minimum-in-an-array/) | [MinMaxArray.java](Arrays/searching/MinMaxArray.java) | $O(N)$ | $O(1)$ |
| Searching | [Repeating and Missing Number](https://www.geeksforgeeks.org/find-a-repeating-and-a-missing-number/) | [RepeatingAndMissing.java](Arrays/searching/RepeatingAndMissing.java) | $O(N)$ | $O(1)$ |
| Searching | [Majority Element](https://leetcode.com/problems/majority-element/) | [MajorityElement.java](Arrays/searching/MajorityElement.java) | $O(N)$ | $O(1)$ |
| Searching | [Search Adjacent Differ by at Most K](https://www.geeksforgeeks.org/searching-array-adjacent-differ-k/) | [SearchAdjacentDifferByK.java](Arrays/searching/SearchAdjacentDifferByK.java) | $O(N)$ | $O(1)$ |
| Searching | [Product Array Puzzle](https://www.geeksforgeeks.org/a-product-array-puzzle/) | [ProductArrayPuzzle.java](Arrays/ProductArrayPuzzle.java) | $O(N)$ | $O(1)$ |
| Sorting | Merge Sorted Arrays (In-Place) | [MergeSortedArrays.java](Arrays/MergeSortedArrays.java) | $O((N + M) \log(N + M))$ | $O(1)$ |
| Sorting | Count Inversions | [CountInversion.java](Arrays/Sorting/CountInversion.java) | $O(N \log N)$ | $O(N)$ |
| Sorting | [Sort by Set Bit Count](https://www.geeksforgeeks.org/sort-an-array-according-to-count-of-set-bits/) | [SortBySetBitCount.java](Arrays/Sorting/SortBySetBitCount.java) | $O(N \log N)$ | $O(N)$ |

### 5. Dynamic Programming, Trees & Backtracking
Complex recursive optimization problems, hierarchical representations, and state space searches.

| Pattern | Problem | Source Link | Code Link | Key Approach |
|---------|---------|-------------|-----------|--------------|
| Trees | Vertical Order Sum | [LeetCode 987](https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/) | [verticalSum.java](Trees/verticalSum.java) | TreeMap tracking of horizontal distance coordinates |
| DP | Edit Distance | [LeetCode 72](https://leetcode.com/problems/edit-distance/) | [edit_distance.java](dp/edit_distance.java) | Bottom-up 2D grid dynamic programming |
| DP | Word Wrap | [GeeksforGeeks](https://www.geeksforgeeks.org/word-wrap-problem-dp-19/) | [wordWrap.java](dp/wordWrap.java) | Recursion + Memoization computing minimum extra space squares |
| DP | Count Palindromic Subsequences | [GeeksforGeeks](https://www.geeksforgeeks.org/count-palindromic-subsequence-given-string/) | [CountPalindromicSubsequences.java](dp/CountPalindromicSubsequences.java) | 2D dynamic programming table matching boundaries |
| DP | Word Break | [LeetCode 139](https://leetcode.com/problems/word-break/) | [WordBreak.java](dp/WordBreak.java) | 1D dynamic programming tracking segmented word suffixes |
| DP | Longest Common Subsequence | [LeetCode 1143](https://leetcode.com/problems/longest-common-subsequence/) | [LongestCommonSubsequence.java](dp/LongestCommonSubsequence.java) | Bottom-up 2D grid dynamic programming tabulation |
| DP | Max Sum Non-Adjacent Elements | [GeeksforGeeks](https://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent/) | [MaxSumNonAdjacent.java](dp/MaxSumNonAdjacent.java) | Tabulation DP optimized to two state variables (include/exclude choices) |
| Backtrack | N-Queens Puzzle | [LeetCode 51](https://leetcode.com/problems/n-queens/) | [NQueen.java](Backtrack/NQueen.java) | Row-by-row recursive placement checking column/diagonal safety |
| Backtrack | Permutations | [LeetCode 46](https://leetcode.com/problems/permutations/) | [permutation.java](Backtrack/permutation.java) | Backtracking swap-based permutation space exploration |
| Backtrack | Subset Sums | [GeeksforGeeks](https://practice.geeksforgeeks.org/problems/subset-sums2234/1) | [SubsetSums.java](Backtrack/SubsetSums.java) | Recursive backtracking generating sums for all pick/don't-pick decisions |

### 6. Cyclic Sort Pattern
In-place range sorting and mismatch scanning for optimal $O(N)$ time and $O(1)$ space range queries.

| # | Problem | Source Link | Solution Code | Optimal Time | Space | Core Approach |
|---|---------|-------------|---------------|:------------:|:-----:|--------------|
| 1 | Cyclic Sort | [LeetCode Practice](https://leetcode.com/problems/missing-number/) | [CyclicSort.java](CyclicSort/CyclicSort.java) | $O(N)$ | $O(1)$ | Swap elements to their correct index `val - 1` |
| 2 | Find the Missing Number | [LeetCode 268](https://leetcode.com/problems/missing-number/) | [MissingNumber.java](CyclicSort/MissingNumber.java) | $O(N)$ | $O(1)$ | Swap `val` to index `val`, return first index mismatch or `N` |
| 3 | Find All Missing Numbers | [LeetCode 448](https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/) | [FindAllMissingNumbers.java](CyclicSort/FindAllMissingNumbers.java) | $O(N)$ | $O(1)$ | Sort range `[1, N]`, return all indices `i` where `nums[i] != i + 1` |
| 4 | Find the Duplicate Number | [LeetCode 287](https://leetcode.com/problems/find-the-duplicate-number/) | [FindDuplicate.java](CyclicSort/FindDuplicate.java) | $O(N)$ | $O(1)$ | In-place swap; return value if target slot already contains it |
| 5 | Find All Duplicates | [LeetCode 442](https://leetcode.com/problems/find-all-duplicates-in-an-array/) | [FindAllDuplicates.java](CyclicSort/FindAllDuplicates.java) | $O(N)$ | $O(1)$ | Cyclic sort `[1, N]`, collect `nums[i]` where `nums[i] != i + 1` |
| 6 | Find Duplicate & Missing Number | [LeetCode 645](https://leetcode.com/problems/set-mismatch/) | [FindDuplicateAndMissing.java](CyclicSort/FindDuplicateAndMissing.java) | $O(N)$ | $O(1)$ | Sort range `[1, N]`, index mismatch `i` gives duplicate `nums[i]` and missing `i + 1` |
| 7 | Find Smallest Missing Positive | [LeetCode 41](https://leetcode.com/problems/first-missing-positive/) | [FirstMissingPositive.java](CyclicSort/FirstMissingPositive.java) | $O(N)$ | $O(1)$ | Cyclic sort positive numbers `[1, N]`, return first index mismatch `i + 1` |
| 8 | First K Missing Positive Numbers | [Grokking (LC 41 variant)](https://leetcode.com/problems/first-missing-positive/) | [FirstKMissingPositive.java](CyclicSort/FirstKMissingPositive.java) | $O(N + K)$ | $O(N)$ | Cyclic sort `[1, N]`, scan for mismatches and beyond, tracking seen elements |

### 7. In-place Reversal of a Linked List Pattern
Iterative pointer redirection for memory-efficient $O(1)$ space manipulation of linear list elements.

| # | Problem | Source Link | Solution Code | Optimal Time | Space | Core Approach |
|---|---------|-------------|---------------|:------------:|:-----:|--------------|
| 1 | Reverse a Linked List | [LeetCode 206](https://leetcode.com/problems/reverse-linked-list/) | [ReverseLinkedList.java](InPlaceLinkedListReversal/ReverseLinkedList.java) | $O(N)$ | $O(1)$ | Iterative & recursive in-place reversal |
| 2 | Reverse a Sub-list (m to n) | [LeetCode 92](https://leetcode.com/problems/reverse-linked-list-ii/) | [ReverseSubList.java](InPlaceLinkedListReversal/ReverseSubList.java) | $O(N)$ | $O(1)$ | Traverse to index left-1, reverse sub-list, reconnect ends |
| 3 | Reverse Every K-element Sub-list | [LeetCode 25](https://leetcode.com/problems/reverse-nodes-in-k-group/) | [ReverseKGroup.java](InPlaceLinkedListReversal/ReverseKGroup.java) | $O(N)$ | $O(1)$ | Count k elements, reverse them, repeat iteratively, keep leftovers as is |
| 4 | Reverse Alternating K-element Sub-list | [Grokking Pattern](https://leetcode.com/) | [ReverseAlternatingKGroup.java](InPlaceLinkedListReversal/ReverseAlternatingKGroup.java) | $O(N)$ | $O(1)$ | Alternating phases: reverse k nodes, skip k nodes, repeat |
| 5 | Rotate a Linked List | [LeetCode 61](https://leetcode.com/problems/rotate-list/) | [RotateList.java](InPlaceLinkedListReversal/RotateList.java) | $O(N)$ | $O(1)$ | Form circular list, advance len - (k % len) - 1, split circle |
| 6 | Reverse Nodes in Even Length Groups | [LeetCode 2074](https://leetcode.com/problems/reverse-nodes-in-even-length-groups/) | [ReverseEvenLengthGroups.java](InPlaceLinkedListReversal/ReverseEvenLengthGroups.java) | $O(N)$ | $O(1)$ | Incrementally size groups (1, 2, 3...); reverse if group size is even |
| 7 | Swap Nodes in Pairs | [LeetCode 24](https://leetcode.com/problems/swap-nodes-in-pairs/) | [SwapPairs.java](InPlaceLinkedListReversal/SwapPairs.java) | $O(N)$ | $O(1)$ | Swap adjacent nodes iteratively in pairs (k=2) |
| 8 | Reverse Linked List in Groups of Size K (GFG) | [GeeksforGeeks](https://practice.geeksforgeeks.org/problems/reverse-a-linked-list-in-groups-of-given-size/1) | [ReverseKGroupGFG.java](InPlaceLinkedListReversal/ReverseKGroupGFG.java) | $O(N)$ | $O(1)$ | Reverse every group of size k, including leftover nodes |

### 8. Merge Intervals Pattern
Sorting or managing interval lists to solve overlaps, scheduling, or range coverage queries.

| # | Problem | Source Link | Solution Code | Optimal Time | Space | Core Approach |
|---|---------|-------------|---------------|:------------:|:-----:|--------------|
| 1 | Merge Overlapping Intervals | [LeetCode](https://leetcode.com/problems/merge-intervals/) | [MergeIntervals.java](MergeIntervals/MergeIntervals.java) | $O(N \log N)$ | $O(N)$ | Sort by start time, merge overlapping bounds linearly |
| 2 | Insert Interval | [LeetCode](https://leetcode.com/problems/insert-interval/) | [InsertInterval.java](MergeIntervals/InsertInterval.java) | $O(N)$ | $O(N)$ | Linear pass: add left-side, merge overlapping, add right-side |
| 3 | Intervals Intersection | [LeetCode](https://leetcode.com/problems/interval-list-intersections/) | [IntervalIntersection.java](MergeIntervals/IntervalIntersection.java) | $O(N + M)$ | $O(N + M)$ | Two-pointer scan, find overlap start/end, advance smaller end |

### 9. Tree BFS Traversal Pattern
Level-by-level traversal using a queue to process nodes horizontally across each depth level.

| # | Problem | Source Link | Solution Code | Optimal Time | Space | Core Approach |
|---|---------|-------------|---------------|:------------:|:-----:|--------------|
| 1 | Binary Tree Level Order Traversal | [LeetCode 102](https://leetcode.com/problems/binary-tree-level-order-traversal/) | [LevelOrderTraversal.java](Trees/BFS/LevelOrderTraversal.java) | $O(N)$ | $O(N)$ | Queue-based level size iteration, collecting nodes level-by-level |
| 2 | Reverse Level Order Traversal | [LeetCode 107](https://leetcode.com/problems/binary-tree-level-order-traversal-ii/) | [ReverseLevelOrderTraversal.java](Trees/BFS/ReverseLevelOrderTraversal.java) | $O(N)$ | $O(N)$ | Queue-based level-order traversal, prepending levels to list |
| 3 | Zigzag Level Order Traversal | [LeetCode 103](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/) | [ZigzagLevelOrderTraversal.java](Trees/BFS/ZigzagLevelOrderTraversal.java) | $O(N)$ | $O(N)$ | Alternate inserting values at front/back of level list using a direction flag |
| 4 | Level Averages in Binary Tree | [LeetCode 637](https://leetcode.com/problems/average-of-levels-in-binary-tree/) | [LevelAverages.java](Trees/BFS/LevelAverages.java) | $O(N)$ | $O(N)$ | Compute average of each level using double accumulator to avoid overflow |
| 5 | Minimum Depth of Binary Tree | [LeetCode 111](https://leetcode.com/problems/minimum-depth-of-binary-tree/) | [MinDepthBFS.java](Trees/BFS/MinDepthBFS.java) | $O(N)$ | $O(N)$ | Find first leaf node level-by-level using BFS traversal for early termination |
| 6 | Level Order Successor | [Grokking Pattern](https://leetcode.com/) | [LevelOrderSuccessor.java](Trees/BFS/LevelOrderSuccessor.java) | $O(N)$ | $O(N)$ | Queue-based level-order search returning the next item when target is hit |
| 7 | Connect Level Order Siblings | [LeetCode 116](https://leetcode.com/problems/populating-next-right-pointers-in-each-node/) | [ConnectLevelOrderSiblings.java](Trees/BFS/ConnectLevelOrderSiblings.java) | $O(N)$ | $O(N)$ | Use BFS queue level sizes to point each node to its right sibling on the same level |
| 8 | Connect All Level Order Siblings | [LeetCode 117](https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/) | [ConnectAllLevelOrderSiblings.java](Trees/BFS/ConnectAllLevelOrderSiblings.java) | $O(N)$ | $O(N)$ | Iterative BFS queue traversal linking previous node next pointer to current node across levels |
| 9 | Right View of Binary Tree | [LeetCode 199](https://leetcode.com/problems/binary-tree-right-side-view/) | [RightViewBinaryTree.java](Trees/BFS/RightViewBinaryTree.java) | $O(N)$ | $O(N)$ | Level-by-level BFS collecting only the last element of each queue level |
| 10 | Tree Boundary | [LeetCode 545](https://leetcode.com/problems/boundary-of-binary-tree/) | [TreeBoundary.java](Trees/BFS/TreeBoundary.java) | $O(N)$ | $O(N)$ | Traverse left boundary, recursively collect leaves, and traverse right boundary in reverse |
| 11 | Word Ladder (BFS on Graph) | [LeetCode 127](https://leetcode.com/problems/word-ladder/) | [WordLadder.java](Trees/BFS/WordLadder.java) | $O(M^2 \cdot N)$ | $O(M^2 \cdot N)$ | Graph-based BFS mutating each word character to find shortest transformation sequence |
| 12 | Vertical Order Traversal | [LeetCode 987](https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/) | [VerticalOrderTraversal.java](Trees/BFS/VerticalOrderTraversal.java) | $O(N \log N)$ | $O(N)$ | Store coordinates during BFS, sort by column/row/value, and group by column |

### 10. Modified Binary Search Pattern
Searching sorted spaces, rotated segments, boundaries, or finding optimization answers in logarithmic time.

| # | Problem | Source Link | Solution Code | Optimal Time | Space | Core Approach |
|---|---------|-------------|---------------|:------------:|:-----:|--------------|
| 1 | Binary Search (Classic) | [LeetCode 704](https://leetcode.com/problems/binary-search/) | [BinarySearch.java](Arrays/searching/BinarySearch/BinarySearch.java) | $O(\log N)$ | $O(1)$ | Traditional mid check and pointer adjustment |
| 2 | Find Ceiling of a Number | [LeetCode 35](https://leetcode.com/problems/search-insert-position/) | [SearchInsertPosition.java](Arrays/searching/BinarySearch/SearchInsertPosition.java) | $O(\log N)$ | $O(1)$ | Standard binary search; return `low` if target not found |
| 3 | Next Letter | [LeetCode 744](https://leetcode.com/problems/find-smallest-letter-greater-than-target/) | [NextLetter.java](Arrays/searching/BinarySearch/NextLetter.java) | $O(\log N)$ | $O(1)$ | Binary search with circular index mapping (`low % length`) |
| 4 | Find Range of a Number | [LeetCode 34](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/) | [FindRange.java](Arrays/searching/BinarySearch/FindRange.java) | $O(\log N)$ | $O(1)$ | Double binary search finding leftmost and rightmost boundaries |
| 5 | Search in Infinite Sorted Array | [LeetCode 702 (Premium)](https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/) | [SearchInfiniteSortedArray.java](Arrays/searching/BinarySearch/SearchInfiniteSortedArray.java) | $O(\log K)$ | $O(1)$ | Exponential bounds expansion to determine range, then binary search |
| 6 | Minimum Difference Element | [LeetCode 658 (Closest)](https://leetcode.com/problems/find-k-closest-elements/) | [MinimumDifferenceElement.java](Arrays/searching/BinarySearch/MinimumDifferenceElement.java) | $O(\log N)$ | $O(1)$ | Find closest element using boundary checks at loop termination |
| 7 | Search in Rotated Sorted Array | [LeetCode 33](https://leetcode.com/problems/search-in-rotated-sorted-array/) | [SearchRotatedArray.java](Arrays/searching/BinarySearch/SearchRotatedArray.java) | $O(\log N)$ | $O(1)$ | Binary search comparing boundaries to find and search sorted half |
| 8 | Search in Rotated Array II | [LeetCode 81](https://leetcode.com/problems/search-in-rotated-sorted-array-ii/) | [SearchRotatedArrayII.java](Arrays/searching/BinarySearch/SearchRotatedArrayII.java) | $O(N)$ | $O(1)$ | Handle duplicate edge elements by shrinking bounds, then binary search |
| 9 | Find Minimum in Rotated Sorted Array | [LeetCode 153](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/) | [FindMinRotatedArray.java](Arrays/searching/BinarySearch/FindMinRotatedArray.java) | $O(\log N)$ | $O(1)$ | Binary search moving towards the unsorted side |
| 10| Search a 2D Matrix | [LeetCode 74](https://leetcode.com/problems/search-a-2d-matrix/) | [Search2DMatrix.java](Arrays/searching/BinarySearch/Search2DMatrix.java) | $O(\log(M \cdot N))$ | $O(1)$ | Flat virtual index mapping to row/col indices |
| 11| Find Peak Element | [LeetCode 162](https://leetcode.com/problems/find-peak-element/) | [FindPeakElement.java](Arrays/searching/BinarySearch/FindPeakElement.java) | $O(\log N)$ | $O(1)$ | Search in direction of ascending slope |
| 12| Koko Eating Bananas | [LeetCode 875](https://leetcode.com/problems/koko-eating-bananas/) | [KokoEatingBananas.java](Arrays/searching/BinarySearch/KokoEatingBananas.java) | $O(N \log(\max(P)))$ | $O(1)$ | Binary search over eating rates, validating speeds |
| 13| Median of Two Sorted Arrays | [LeetCode 4](https://leetcode.com/problems/median-of-two-sorted-arrays/) | [MedianTwoSortedArrays.java](Arrays/searching/BinarySearch/MedianTwoSortedArrays.java) | $O(\log(\min(M, N)))$ | $O(1)$ | Binary search on partitions of smaller array to balance sizes |
| 14| Find a Fixed Point (Value equal to index) | [GeeksforGeeks](https://www.geeksforgeeks.org/find-a-fixed-point-in-a-given-array/) | [FixedPoint.java](Arrays/searching/BinarySearch/FixedPoint.java) | $O(\log N)$ | $O(1)$ | Binary search comparing element and its index |
| 15| Square root of an integer | [LeetCode 69](https://leetcode.com/problems/sqrtx/) | [Sqrt.java](Arrays/searching/BinarySearch/Sqrt.java) | $O(\log X)$ | $O(1)$ | Binary search using division check mid <= x/mid to avoid overflow |
| 16| Kth Smallest Number Again | [HackerEarth](https://www.hackerearth.com/practice/algorithms/searching/binary-search/practice-problems/algorithm/kth-smallest-number-again-2/) | [KthSmallestNumberAgain.java](Arrays/searching/BinarySearch/KthSmallestNumberAgain.java) | $O(N \log N + Q \log M)$ | $O(N)$ | Merge overlapping intervals, then query K-th element using binary search on prefix sums of interval sizes |
| 17| Find Pivot Element in Sorted Array | [GeeksforGeeks](https://www.geeksforgeeks.org/search-an-element-in-a-sorted-and-pivoted-array/) | [FindPivot.java](Arrays/searching/BinarySearch/FindPivot.java) | $O(\log N)$ | $O(1)$ | Binary search for inflection point (largest value) where order drops, with duplicate handling |
| 18| K-th Element of Two Sorted Arrays | [GeeksforGeeks](https://www.geeksforgeeks.org/k-th-element-two-sorted-arrays/) | [KthElementTwoSortedArrays.java](Arrays/searching/BinarySearch/KthElementTwoSortedArrays.java) | $O(\log(\min(M, N)))$ | $O(1)$ | Binary search on partition cut of smaller array to balance elements |
| 19| Aggressive Cows | [SPOJ](https://www.spoj.com/problems/AGGRCOW/) | [AggressiveCows.java](Arrays/searching/BinarySearch/AggressiveCows.java) | $O(N \log N + N \log(\text{max\_dist}))$ | $O(1)$ | Sort stalls, then binary search on distance range with greedy placement validation |
| 20| Book Allocation Problem | [GeeksforGeeks](https://www.geeksforgeeks.org/book-allocation-problem/) | [BookAllocation.java](Arrays/searching/BinarySearch/BookAllocation.java) | $O(N \log(\text{sum\_pages}))$ | $O(1)$ | Binary search on pages allocation limit with contiguous student allocation checks |

### 11. Kadane's Algorithm Pattern
Maximum and minimum contiguous subarray sum optimization.

| # | Problem | Source Link | Solution Code | Optimal Time | Space | Core Approach |
|---|---------|-------------|---------------|:------------:|:-----:|--------------|
| 1 | Maximum Subarray Sum | [LeetCode 53](https://leetcode.com/problems/maximum-subarray/) | [Kadanes.java](Arrays/Kadanes/Kadanes.java) | $O(N)$ | $O(1)$ | Single pass local/global sum optimization |
| 2 | Minimum Subarray Sum | [Custom](https://leetcode.com/) | [minSum.java](Arrays/Kadanes/minSum.java) | $O(N)$ | $O(1)$ | Single pass local/global sum optimization (minimization) |

### 12. Matrix Pattern
Grid operations, spiral traversals, boundary search, and multidimensional sorting.

| # | Problem | Source Link | Solution Code | Optimal Time | Space | Core Approach |
|---|---------|-------------|---------------|:------------:|:-----:|--------------|
| 1 | Spiral Traversal on a Matrix | [LeetCode 54](https://leetcode.com/problems/spiral-matrix/) | [SpiralTraversal.java](Matrix/SpiralTraversal.java) | $O(M \cdot N)$ | $O(1)$ | Boundary tracking (top, bottom, left, right) and spiral loop |
| 2 | Search an Element in a Matrix | [LeetCode 74](https://leetcode.com/problems/search-a-2d-matrix/) | [SearchMatrix.java](Matrix/SearchMatrix.java) | $O(\log(M \cdot N))$ | $O(1)$ | Virtual 1D array binary search & Staircase search alternatives |
| 3 | Find Median in a Row-wise Sorted Matrix | [GeeksforGeeks](https://www.geeksforgeeks.org/find-median-in-a-row-wise-sorted-matrix/) | [MedianRowWiseSorted.java](Matrix/MedianRowWiseSorted.java) | $O(R \cdot \log C \cdot \log(\text{max}-\text{min}))$ | $O(1)$ | Binary search over value range, counting elements with upper-bound search |
| 4 | Find Row with Maximum no. of 1's | [GeeksforGeeks](https://www.geeksforgeeks.org/find-the-row-with-maximum-number-1s/) | [RowWithMaxOnes.java](Matrix/RowWithMaxOnes.java) | $O(R + C)$ | $O(1)$ | Staircase search starting from top-right, moving left on 1, down on 0 |
| 5 | Print Elements in Sorted Order | [GeeksforGeeks](https://www.geeksforgeeks.org/print-elements-sorted-order-row-column-wise-sorted-matrix/) | [PrintSortedMatrix.java](Matrix/PrintSortedMatrix.java) | $O(R \cdot C \cdot \log R)$ | $O(R)$ | Merge sorted rows using a Min-Heap (Priority Queue) |

### 13. String Manipulation Pattern
Optimal string search, manipulation, verification, and run-length patterns.

| # | Problem | Source Link | Solution Code | Optimal Time | Space | Core Approach |
|---|---------|-------------|---------------|:------------:|:-----:|--------------|
| 1 | Reverse a String | [LeetCode 344](https://leetcode.com/problems/reverse-string/) | [ReverseString.java](String/ReverseString.java) | $O(N)$ | $O(1)$ | Two-pointer swap from boundaries inward |
| 2 | Check String Palindrome | [LeetCode 125](https://leetcode.com/problems/valid-palindrome/) | [PalindromeCheck.java](String/PalindromeCheck.java) | $O(N)$ | $O(1)$ | Two-pointer inward alphanumeric comparison |
| 3 | Find Duplicate Characters | [Custom](https://leetcode.com/) | [DuplicateCharacters.java](String/DuplicateCharacters.java) | $O(N)$ | $O(1)$ | Hash-map or frequency array character occurrence scanning |
| 4 | Why Strings are Immutable in Java? | [Conceptual](String/StringImmutability.java) | [StringImmutability.java](String/StringImmutability.java) | N/A | N/A | Explanation & demo of memory pool, security, thread safety, and hash caching |
| 5 | Check String Rotation | [LeetCode 796](https://leetcode.com/problems/rotate-string/) | [StringRotation.java](String/StringRotation.java) | $O(N)$ | $O(N)$ | Checking if s2 is a substring of concatenated s1 + s1 |
| 6 | Check Valid Shuffle / Interleave | [LeetCode 97](https://leetcode.com/problems/interleaving-string/) | [ValidShuffle.java](String/ValidShuffle.java) | $O(M \cdot N)$ | $O(M \cdot N)$ | 2D dynamic programming tracking character interleaving |
| 7 | Count and Say | [LeetCode 38](https://leetcode.com/problems/count-and-say/) | [CountAndSay.java](String/CountAndSay.java) | $O(2^N)$ | $O(2^N)$ | Iterative run-length encoding tracking count and character |
| 8 | Longest Palindromic Substring | [LeetCode 5](https://leetcode.com/problems/longest-palindromic-substring/) | [LongestPalindromicSubstring.java](String/LongestPalindromicSubstring.java) | $O(N^2)$ | $O(1)$ | Expand around odd/even indices tracking boundaries |
| 9 | Longest Recurring Subsequence | [GeeksforGeeks](https://www.geeksforgeeks.org/longest-recurring-subsequence/) | [LongestRecurringSubsequence.java](String/LongestRecurringSubsequence.java) | $O(N^2)$ | $O(N^2)$ | Dynamic programming using LCS on the string with itself, ensuring index $i \neq j$ |
| 10 | Print all Subsequences | [GeeksforGeeks](https://www.geeksforgeeks.org/print-subsequences-string/) | [PrintSubsequences.java](String/PrintSubsequences.java) | $O(2^N)$ | $O(N)$ | Recursive backtracking with pick/don't pick choices |
| 11 | Print all Permutations | [LeetCode 46](https://leetcode.com/problems/permutations/) | [StringPermutations.java](String/StringPermutations.java) | $O(N \cdot N!)$ | $O(N)$ | Backtracking swap-based character exploration with duplicate skipping |
| 12 | Split Binary String | [GeeksforGeeks](https://www.geeksforgeeks.org/split-the-binary-string-into-substrings-with-equal-number-of-0s-and-1s/) | [SplitBinaryString.java](String/SplitBinaryString.java) | $O(N)$ | $O(1)$ | Greedy linear scan tracking balanced count of 0s and 1s |
| 13 | Mobile Numeric Keypad Sequence | [GeeksforGeeks](https://www.geeksforgeeks.org/convert-sentence-equivalent-mobile-numeric-keypad-sequence/) | [MobileNumericKeypad.java](String/MobileNumericKeypad.java) | $O(N)$ | $O(N)$ | Static mapping of characters to keypad sequences |
| 14 | Minimum Bracket Reversals | [GeeksforGeeks](https://www.geeksforgeeks.org/minimum-number-of-bracket-reversals-needed-to-make-an-expression-balanced/) | [BracketReversals.java](String/BracketReversals.java) | $O(N)$ | $O(1)$ | Counter-based tracking of unmatched open/close brackets |
| 15 | Next Greater Number (Same Digits) | [LeetCode 556](https://leetcode.com/problems/next-greater-element-iii/) | [NextGreaterNumber.java](String/NextGreaterNumber.java) | $O(D)$ | $O(D)$ | Next permutation algorithm on digits |
| 16 | Balanced Parentheses | [LeetCode 20](https://leetcode.com/problems/valid-parentheses/) | [BalancedParenthesis.java](String/BalancedParenthesis.java) | $O(N)$ | $O(N)$ | Stack-based matching of open and close brackets |
| 17 | Rabin-Karp Algorithm | [GeeksforGeeks](https://www.geeksforgeeks.org/rabin-karp-algorithm-for-pattern-searching/) | [RabinKarp.java](String/RabinKarp.java) | $O(N + M)$ | $O(1)$ | Pattern searching using rolling hash matching |
| 18 | KMP Algorithm | [LeetCode 28](https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/) | [KMP.java](String/KMP.java) | $O(N + M)$ | $O(M)$ | Pattern searching using precomputed prefix suffix table |
| 19 | Longest Common Prefix | [LeetCode 14](https://leetcode.com/problems/longest-common-prefix/) | [LongestCommonPrefix.java](String/LongestCommonPrefix.java) | $O(N \cdot M)$ | $O(1)$ | Vertical scanning comparing characters of all strings |
| 20 | Alternate Binary String Flips | [GeeksforGeeks](https://www.geeksforgeeks.org/number-flips-make-binary-string-alternate/) | [AlternateBinaryString.java](String/AlternateBinaryString.java) | $O(N)$ | $O(1)$ | Compare with target alternating patterns and minimize flips |
| 21 | First Repeated Word | [GeeksforGeeks](https://www.geeksforgeeks.org/find-first-repeated-word-string/) | [FirstRepeatedWord.java](String/FirstRepeatedWord.java) | $O(N)$ | $O(K)$ | Split string into words and find the first duplicate using HashSet |
| 22 | Bracket Balancing Swaps | [GeeksforGeeks](https://www.geeksforgeeks.org/minimum-swaps-bracket-balancing/) | [BracketBalancing.java](String/BracketBalancing.java) | $O(N)$ | $O(1)$ | Greedy tracking of open/close bracket counts and imbalance |

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
   git clone https://github.com/username/DSA-REPO.git
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
*Developed with ❤️ by DSA Learner.*
