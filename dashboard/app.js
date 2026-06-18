// AlgoPrep Platform State Controller & Database Layer

// Global Constants & Mock DB Lookups
const COMPANIES = ['Google', 'Amazon', 'Microsoft', 'Uber', 'Adobe', 'Meta', 'Netflix', 'Apple', 'Flipkart', 'Atlassian'];

const EASY_PROBLEMS = [
    'two sum', 'reverse', 'palindrome', 'remove duplicates', 'squares of', 
    'maximum average', 'happy number', 'middle of the', 'linked list cycle',
    'search insert', 'binary search', 'linear search', 'bubble sort', 
    'insertion sort', 'selection sort', 'next letter', 'minimum difference element',
    'find ceiling', 'find range', 'search in infinite'
];

const HARD_PROBLEMS = [
    'n-queen', 'edit distance', 'word wrap', 'trapping rain water', 
    'median of two', 'word ladder', 'boundary', 'first missing positive',
    'first k missing'
];

// Rich High-Fidelity Explanations for Featured Problems
const PROBLEM_DETAILS = {
    "Two Sum (Sorted)": {
        statement: "Given a 1-indexed array of integers `numbers` that is already sorted in non-decreasing order, find two numbers such that they add up to a specific `target` number. Return the indices of the two numbers.",
        recognition: "The input array is already sorted. Finding a target sum in a sorted range strongly suggests using a Two-Pointer strategy moving inwards from both boundaries.",
        intuition: "Instead of comparing all possible pairs in O(N^2), we place one pointer at the start and one at the end. By checking the sum: <br>- If sum matches target, we are done.<br>- If sum is too large, we decrement the right pointer (to reduce sum).<br>- If sum is too small, we increment the left pointer (to increase sum).",
        dryrun: [
            { title: "Initialize Pointers", desc: "For numbers = [2, 7, 11, 15], target = 9. Set left = 0 (val 2), right = 3 (val 15)." },
            { title: "First Comparison", desc: "Sum = numbers[left] + numbers[right] = 2 + 15 = 17. 17 > 9, so decrement right to index 2 (val 11)." },
            { title: "Second Comparison", desc: "Sum = 2 + 11 = 13. 13 > 9, so decrement right to index 1 (val 7)." },
            { title: "Third Comparison", desc: "Sum = 2 + 7 = 9. Match found! Return left + 1 (1) and right + 1 (2)." }
        ],
        alternatives: "1. **Brute Force**: Try all combinations using nested loops, which takes O(N^2) time.<br>2. **Binary Search**: For each element, search for its complement using binary search, which takes O(N log N) time.",
        mistakes: [
            "Returning 0-indexed indices instead of 1-indexed when the problem explicitly specifies 1-indexed constraints.",
            "Incorrectly updating pointers (e.g. incrementing left when sum is already too large)."
        ],
        followups: "What if the input array is not sorted? (Solution: Use a Hash Map to store seen complements in O(N) time and O(N) space)."
    },
    "Edit Distance": {
        statement: "Given two strings `word1` and `word2`, return the minimum number of operations required to convert `word1` to `word2`. You have three operations permitted on a word: Insert a character, Delete a character, or Replace a character.",
        recognition: "This is an optimization problem over two sequences with overlapping subproblems, making it a classic candidate for 2D Dynamic Programming.",
        intuition: "To convert word1[0..i] to word2[0..j], we look at the last characters:<br>- If they match: No operation needed, look at word1[0..i-1] and word2[0..j-1].<br>- If they mismatch: Try Insert, Delete, and Replace, and add 1 operation to the minimum of those paths.",
        dryrun: [
            { title: "DP Definition", desc: "Create a 2D matrix dp[m+1][n+1] where dp[i][j] represents steps to convert word1[0..i-1] to word2[0..j-1]." },
            { title: "Base Cases", desc: "If word1 is empty, we must insert all characters of word2. If word2 is empty, we must delete all characters of word1." },
            { title: "Transitions", desc: "If word1[i] == word2[j], dp[i][j] = dp[i-1][j-1]. Else, dp[i][j] = 1 + min(dp[i-1][j] (delete), dp[i][j-1] (insert), dp[i-1][j-1] (replace))." }
        ],
        alternatives: "1. **Brute Force Recursion**: Resolving without memoization takes exponential O(3^(M+N)) time.<br>2. **Space-Optimized DP**: Since we only need the previous row's results, we can optimize space to O(N) using two rows.",
        mistakes: [
            "Incorrect initialization of base cases for empty values.",
            "Off-by-one errors when mapping 1-indexed DP cells to 0-indexed string characters."
        ],
        followups: "Can you perform this conversion if certain operations have different costs? (Yes, adjust the weighting in the DP transition state)."
    },
    "N-Queens Puzzle": {
        statement: "The n-queens puzzle is the problem of placing `n` queens on an `n x n` chessboard such that no two queens attack each other. Return all distinct configurations.",
        recognition: "The problem asks for all valid configurations in a search space, which requires backtracking to explore possibilities row-by-row and backtrack upon conflicts.",
        intuition: "Place a queen row-by-row. For each row, iterate columns: check if placing the queen is safe (no conflicts in columns or diagonals). If safe, place and recurse to next row. If next row returns no solution, backtrack (remove queen) and try the next column.",
        dryrun: [
            { title: "Row 0", desc: "Place queen at (0, 0). Move to Row 1." },
            { title: "Row 1 Validation", desc: "Columns 0 and 1 are attacked by Queen (0, 0). Place queen at (1, 2). Move to Row 2." },
            { title: "Row 2 Conflict", desc: "No columns are safe for Row 2. Backtrack to Row 1 and relocate queen to (1, 3)." },
            { title: "Recursion Loop", desc: "Repeat column placements and backtracking until all N rows contain queens." }
        ],
        alternatives: "1. **Bitmasking Optimization**: Track column and diagonal availability using integers as bitmasks to make conflict check O(1).",
        mistakes: [
            "Failing to remove state changes (backtrack) when returning from recursion.",
            "Using nested loops to search full diagonals, which slows down the execution speed."
        ],
        followups: "Can you compute the total count of configurations without compiling the actual boards? (Yes, use N-Queens II logic)."
    },
    "Longest Palindromic Substring": {
        statement: "Given a string `s`, return the longest palindromic substring in `s`.",
        recognition: "A palindrome expands outward from its center. This lets us bypass DP table overhead by expanding around each index as a potential center.",
        intuition: "A palindrome can have an odd length (one center character, e.g., 'a') or an even length (two center characters, e.g., 'bb'). We loop through the string, treat each index as a center, expand outwards as long as boundaries match, and track the maximum width.",
        dryrun: [
            { title: "Step 1: Loop index i", desc: "For string 'babad', at i = 1 (char 'a')." },
            { title: "Odd Expansion", desc: "Expand around center (1, 1): 'a' -> 'bab' (bounds match). Next check: 'babad' (left out of bounds). Max odd width = 3." },
            { title: "Even Expansion", desc: "Expand around center (1, 2): 'ab' (mismatch). Width = 0." },
            { title: "Compare and Record", desc: "Max width (3) exceeds previous max. Record substring 'bab'." }
        ],
        alternatives: "1. **Dynamic Programming**: Boolean table `dp[i][j]` representing if `s[i..j]` is a palindrome. Time O(N^2), Space O(N^2).<br>2. **Manacher's Algorithm**: Advanced algorithm with O(N) time and space using palindrome symmetry.",
        mistakes: [
            "Not handling empty strings or string length < 1 correctly.",
            "Forgetting to check even-length centers, resulting in missing palindromes like 'cbbd' (even)."
        ],
        followups: "How would you solve this if you could remove at most K characters? (Requires LCS-based DP on reversed string)."
    }
};

// Curriculum & Learning Paths Database
const PATHS_DATA = [
    {
        id: "faang",
        title: "FAANG Prep Track (90-Day)",
        meta: "Advanced Pattern Mastery",
        icon: "fa-rocket",
        tasks: [
            "Master Two-Pointer & Boundaries",
            "Solve 3Sum and Container With Most Water",
            "Master Sliding Window Pattern",
            "Solve Longest Palindromic Substring",
            "In-place Reversal of LinkedList",
            "N-Queens Backtracking Puzzle"
        ]
    },
    {
        id: "placement",
        title: "Placement Crash Course (30-Day)",
        meta: "Essential Interview Prep",
        icon: "fa-graduation-cap",
        tasks: [
            "Review Basic Two Sum and Reverse Array",
            "Fast & Slow Pointers for Loop Detection",
            "Merge Intervals & Overlapping Ranges",
            "Dynamic Programming: Edit Distance"
        ]
    },
    {
        id: "foundation",
        title: "DSA Foundation Track",
        meta: "Core Structural Concepts",
        icon: "fa-project-diagram",
        tasks: [
            "Cyclic Sort for Missing Positives",
            "Tree BFS level-by-level traversal",
            "Matrix boundary spiral checks",
            "Learn Pattern Recognition Rules"
        ]
    }
];

// Encyclopedic Wiki Topics
const WIKI_DATA = [
    {
        title: "Sliding Window Pattern",
        concept: "The Sliding Window pattern is used to perform a search or optimization over a contiguous subarray or substring. Instead of recalculating values from scratch for every index, we maintain a running window sum or state and slide it by adding the new element and removing the old element.",
        complexity: "Time: O(N) | Space: O(1) auxiliary",
        template: `public int findMaxSumSubarray(int[] arr, int k) {\n    int maxSum = 0, windowSum = 0;\n    int start = 0;\n    for (int end = 0; end < arr.length; end++) {\n        windowSum += arr[end]; // Add next element\n        if (end >= k - 1) {\n            maxSum = Math.max(maxSum, windowSum);\n            windowSum -= arr[start]; // Subtract oldest element\n            start++; // Slide window\n        }\n    }\n    return maxSum;\n}`
    },
    {
        title: "Two-Pointer Strategy",
        concept: "Used primarily on sorted arrays or lists. Two pointers are initialized (typically at the start and end of the array) and move toward each other based on comparison results. This allows narrowing down the search space in a single pass.",
        complexity: "Time: O(N) | Space: O(1) auxiliary",
        template: `public boolean hasTargetSum(int[] arr, int target) {\n    int left = 0, right = arr.length - 1;\n    while (left < right) {\n        int sum = arr[left] + arr[right];\n        if (sum == target) return true;\n        else if (sum < target) left++;\n        else right--;\n    }\n    return false;\n}`
    },
    {
        title: "Fast & Slow Pointers",
        concept: "Also known as Floyd's Cycle Detection algorithm. It uses two pointers moving at different speeds (slow moves 1 step, fast moves 2 steps) to traverse a sequence. It is highly effective for detecting cycles in linked lists or cyclic arrays.",
        complexity: "Time: O(N) | Space: O(1) auxiliary",
        template: `public boolean hasCycle(ListNode head) {\n    if (head == null) return false;\n    ListNode slow = head, fast = head;\n    while (fast != null && fast.next != null) {\n        slow = slow.next;\n        fast = fast.next.next;\n        if (slow == fast) return true; // Cycle detected\n    }\n    return false;\n}`
    },
    {
        title: "Cyclic Sort Pattern",
        concept: "Used when the problem input involves numbers in a given range (e.g., [1, N]). It places each number at its correct index in-place. For example, the number '3' should be at index '2'. Once sorted, a single pass can easily identify missing or duplicate numbers.",
        complexity: "Time: O(N) | Space: O(1) in-place",
        template: `public void cyclicSort(int[] nums) {\n    int i = 0;\n    while (i < nums.length) {\n        int correct = nums[i] - 1;\n        if (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[correct]) {\n            int temp = nums[i];\n            nums[i] = nums[correct];\n            nums[correct] = temp;\n        } else {\n            i++;\n        }\n    }\n}`
    },
    {
        title: "In-place Reversal of LinkedList",
        concept: "Allows reversing a linked list or subsegment of a list in a single pass without allocating any auxiliary memory. It uses three pointers: previous, current, and next to adjust node pointers in-place.",
        complexity: "Time: O(N) | Space: O(1) auxiliary",
        template: `public ListNode reverse(ListNode head) {\n    ListNode prev = null;\n    ListNode curr = head;\n    while (curr != null) {\n        ListNode nextNode = curr.next;\n        curr.next = prev;\n        prev = curr;\n        curr = nextNode;\n    }\n    return prev;\n}`
    },
    {
        title: "Dynamic Programming (DP)",
        concept: "An algorithmic paradigm that solves a complex problem by breaking it into subproblems, solving each subproblem once, and storing their solutions (using memoization or tabulation) to avoid redundant computations.",
        complexity: "Time: O(N * M) | Space: O(N * M) or O(M) optimized",
        template: `public int minOperations(String w1, String w2) {\n    int m = w1.length(), n = w2.length();\n    int[][] dp = new int[m + 1][n + 1];\n    for (int i = 0; i <= m; i++) dp[i][0] = i;\n    for (int j = 0; j <= n; j++) dp[0][j] = j;\n    for (int i = 1; i <= m; i++) {\n        for (int j = 1; j <= n; j++) {\n            if (w1.charAt(i - 1) == w2.charAt(j - 1)) {\n                dp[i][j] = dp[i - 1][j - 1];\n            } else {\n                dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], \n                    Math.min(dp[i - 1][j], dp[i][j - 1]));\n            }\n        }\n    }\n    return dp[m][n];\n}`
    }
];

// Company Logicons Setup
const COMPANY_ICONS = {
    'Google': 'fab fa-google',
    'Amazon': 'fab fa-amazon',
    'Microsoft': 'fab fa-windows',
    'Uber': 'fas fa-car',
    'Adobe': 'fas fa-pen-fancy',
    'Meta': 'fab fa-facebook',
    'Netflix': 'fas fa-play',
    'Apple': 'fab fa-apple',
    'Flipkart': 'fas fa-shopping-bag',
    'Atlassian': 'fab fa-jira'
};

// Interactive visual algorithms steps
// Interactive visual algorithms steps
const SIM_DATA = {
    'two-pointer': {
        name: "Two-Pointer Simulator",
        array: [1, 2, 4, 6, 8, 9, 14, 15],
        target: 15,
        steps: [
            {
                left: 0,
                right: 7,
                title: "Initialize Boundary Pointers",
                desc: "Place the left pointer at index 0 (val 1) and right pointer at index 7 (val 15). The current sum is 1 + 15 = 16. Since 16 > target (15), the sum is too large. We decrement the right pointer.",
                highlights: [0, 7],
                matched: false
            },
            {
                left: 0,
                right: 6,
                title: "Move Right Pointer Inward",
                desc: "Decremented right pointer to index 6 (val 14). The current sum is 1 + 14 = 15. The sum matches our target of 15! We have found the valid pair at indices [0, 6].",
                highlights: [0, 6],
                matched: true
            }
        ]
    },
    'sliding-window': {
        name: "Sliding Window Simulator",
        array: [2, 1, 5, 1, 3, 2],
        k: 3,
        steps: [
            {
                start: 0,
                end: 2,
                title: "Initialize Window",
                desc: "Build the initial window of size K=3 containing indices [0..2] (values: 2, 1, 5). The initial Window Sum is 2 + 1 + 5 = 8. The Max Sum is updated to 8.",
                highlights: [0, 1, 2],
                windowSum: 8,
                maxSum: 8,
                matched: false
            },
            {
                start: 1,
                end: 3,
                title: "Slide Window - Step 1",
                desc: "Slide the window rightwards by 1 position (indices [1..3], values: 1, 5, 1). We add index 3 (val 1) and subtract index 0 (val 2). New Window Sum = 8 - 2 + 1 = 7. Max Sum remains 8.",
                highlights: [1, 2, 3],
                windowSum: 7,
                maxSum: 8,
                matched: false
            },
            {
                start: 2,
                end: 4,
                title: "Slide Window - Step 2",
                desc: "Slide the window rightwards (indices [2..4], values: 5, 1, 3). Add index 4 (val 3) and subtract index 1 (val 1). New Window Sum = 7 - 1 + 3 = 9. Max Sum is updated to 9.",
                highlights: [2, 3, 4],
                windowSum: 9,
                maxSum: 9,
                matched: true
            },
            {
                start: 3,
                end: 5,
                title: "Slide Window - Step 3",
                desc: "Slide window to last elements (indices [3..5], values: 1, 3, 2). Add index 5 (val 2) and subtract index 2 (val 5). New Window Sum = 9 - 5 + 2 = 6. Max Sum remains 9. Done.",
                highlights: [3, 4, 5],
                windowSum: 6,
                maxSum: 9,
                matched: false
            }
        ]
    },
    'binary-search': {
        name: "Binary Search Simulator",
        array: [1, 3, 5, 7, 9, 11, 13, 15],
        target: 9,
        steps: [
            {
                low: 0,
                high: 7,
                mid: 3,
                title: "Initial Range Assessment",
                desc: "Initialize boundary search bounds: low = 0 (val 1), high = 7 (val 15). Calculate mid index = (low + high) / 2 = 3 (val 7). Since mid value 7 < target 9, target must lie in the right half. Adjust search bounds: low = mid + 1 = 4.",
                highlights: [3],
                bounds: [0, 1, 2, 3, 4, 5, 6, 7],
                matched: false
            },
            {
                low: 4,
                high: 7,
                mid: 5,
                title: "Search Right Half",
                desc: "Search range updated: low = 4 (val 9), high = 7 (val 15). Calculate mid index = (4 + 7) / 2 = 5 (val 11). Since mid value 11 > target 9, target must lie in the left half. Adjust bounds: high = mid - 1 = 4.",
                highlights: [5],
                bounds: [4, 5, 6, 7],
                matched: false
            },
            {
                low: 4,
                high: 4,
                mid: 4,
                title: "Target Value Match",
                desc: "Search range updated: low = 4 (val 9), high = 4 (val 9). Calculate mid index = 4 (val 9). The mid value matches our target! Return index 4.",
                highlights: [4],
                bounds: [4],
                matched: true
            }
        ]
    },
    'merge-intervals': {
        name: "Merge Intervals Simulator",
        intervals: [[1, 3], [2, 6], [8, 10], [15, 18]],
        steps: [
            {
                index: 0,
                title: "Initialize Merged List",
                desc: "Read the first interval [1, 3]. Since the merged list is empty, push [1, 3] as our starting merged interval.",
                activeInterval: [1, 3],
                merged: [[1, 3]],
                highlights: [0]
            },
            {
                index: 1,
                title: "Check Next Interval (Overlap)",
                desc: "Read next interval [2, 6]. Since its start (2) <= end of last merged interval (3), they overlap. Merge them: end of last merged interval becomes max(3, 6) = 6. Merged intervals: [[1, 6]].",
                activeInterval: [2, 6],
                merged: [[1, 6]],
                highlights: [0, 1]
            },
            {
                index: 2,
                title: "Check Next Interval (No Overlap)",
                desc: "Read next interval [8, 10]. Since its start (8) > end of last merged interval (6), they do not overlap. Push [8, 10] to merged list. Merged intervals: [[1, 6], [8, 10]].",
                activeInterval: [8, 10],
                merged: [[1, 6], [8, 10]],
                highlights: [0, 1, 2]
            },
            {
                index: 3,
                title: "Check Final Interval (No Overlap)",
                desc: "Read final interval [15, 18]. Since its start (15) > end of last merged interval (10), they do not overlap. Push [15, 18] to merged list. Final Merged list: [[1, 6], [8, 10], [15, 18]]. Process completed.",
                activeInterval: [15, 18],
                merged: [[1, 6], [8, 10], [15, 18]],
                highlights: [0, 1, 2, 3]
            }
        ]
    },
    'fast-slow-pointer': {
        name: "Fast & Slow Pointer Simulator",
        nodes: [1, 2, 3, 4, 5, 6],
        cycleConnection: { from: 5, to: 2 },
        steps: [
            {
                slow: 0,
                fast: 0,
                title: "Initialize Pointers",
                desc: "Place both the slow pointer (green) and the fast pointer (purple) at the head of the linked list (node 1).",
                highlights: [0]
            },
            {
                slow: 1,
                fast: 2,
                title: "First Advance Step",
                desc: "Slow pointer advances 1 step to node 2 (val 2). Fast pointer advances 2 steps to node 3 (val 3).",
                highlights: [1, 2]
            },
            {
                slow: 2,
                fast: 4,
                title: "Second Advance Step",
                desc: "Slow pointer advances 1 step to node 3 (val 3). Fast pointer advances 2 steps to node 5 (val 5).",
                highlights: [2, 4]
            },
            {
                slow: 3,
                fast: 2,
                title: "Fast Pointer Wraps Cycle",
                desc: "Slow pointer advances 1 step to node 4 (val 4). Fast pointer advances 2 steps: from node 5 to node 6 (val 6), and then wraps around the cycle back to node 3 (val 3).",
                highlights: [3, 2]
            },
            {
                slow: 4,
                fast: 4,
                title: "Pointers Meet - Cycle Detected!",
                desc: "Slow pointer advances 1 step to node 5 (val 5). Fast pointer advances 2 steps: from node 3 to node 5 (val 5). Both pointers meet at node 5! This proves a cycle exists.",
                highlights: [4],
                matched: true
            }
        ]
    },
    'tree-dfs': {
        name: "Tree DFS Simulator",
        tree: {
            id: 'A', val: 'A',
            left: {
                id: 'B', val: 'B',
                left: { id: 'D', val: 'D' },
                right: { id: 'E', val: 'E' }
            },
            right: {
                id: 'C', val: 'C',
                left: { id: 'F', val: 'F' }
            }
        },
        steps: [
            {
                currentNode: 'A',
                stack: ['C', 'B'],
                visited: ['A'],
                title: "Start DFS at Root",
                desc: "Visit root node A. Push its children C (right) and B (left) onto the stack so that B is processed first."
            },
            {
                currentNode: 'B',
                stack: ['C', 'E', 'D'],
                visited: ['A', 'B'],
                title: "Process Node B",
                desc: "Pop B from the stack and visit it. Push its children E and D onto the stack."
            },
            {
                currentNode: 'D',
                stack: ['C', 'E'],
                visited: ['A', 'B', 'D'],
                title: "Visit Leaf Node D",
                desc: "Pop leaf node D from stack and visit it. Since D has no children, the stack remains unchanged."
            },
            {
                currentNode: 'E',
                stack: ['C'],
                visited: ['A', 'B', 'D', 'E'],
                title: "Visit Leaf Node E",
                desc: "Pop leaf node E from stack and visit it. This completes the left subtree traversal."
            },
            {
                currentNode: 'C',
                stack: ['F'],
                visited: ['A', 'B', 'D', 'E', 'C'],
                title: "Process Node C",
                desc: "Pop node C from stack and visit it. Push its child node F onto the stack."
            },
            {
                currentNode: 'F',
                stack: [],
                visited: ['A', 'B', 'D', 'E', 'C', 'F'],
                title: "Visit Leaf Node F - Done",
                desc: "Pop leaf node F and visit it. The stack is now empty, indicating the traversal of all reachable nodes is complete."
            }
        ]
    },
    'tree-bfs': {
        name: "Tree BFS Simulator",
        tree: {
            id: 'A', val: 'A',
            left: {
                id: 'B', val: 'B',
                left: { id: 'D', val: 'D' },
                right: { id: 'E', val: 'E' }
            },
            right: {
                id: 'C', val: 'C',
                left: { id: 'F', val: 'F' }
            }
        },
        steps: [
            {
                currentNode: 'A',
                queue: ['B', 'C'],
                visited: ['A'],
                title: "Start BFS at Root",
                desc: "Enqueue root node A. Dequeue A, visit it, and enqueue its left child B and right child C."
            },
            {
                currentNode: 'B',
                queue: ['C', 'D', 'E'],
                visited: ['A', 'B'],
                title: "Process Node B",
                desc: "Dequeue node B and visit it. Enqueue B's children D and E. The queue now contains [C, D, E]."
            },
            {
                currentNode: 'C',
                queue: ['D', 'E', 'F'],
                visited: ['A', 'B', 'C'],
                title: "Process Node C",
                desc: "Dequeue node C and visit it. Enqueue C's child F. The queue now contains [D, E, F]."
            },
            {
                currentNode: 'D',
                queue: ['E', 'F'],
                visited: ['A', 'B', 'C', 'D'],
                title: "Process Node D",
                desc: "Dequeue leaf node D and visit it. D has no children to enqueue."
            },
            {
                currentNode: 'E',
                queue: ['F'],
                visited: ['A', 'B', 'C', 'D', 'E'],
                title: "Process Node E",
                desc: "Dequeue leaf node E and visit it. E has no children."
            },
            {
                currentNode: 'F',
                queue: [],
                visited: ['A', 'B', 'C', 'D', 'E', 'F'],
                title: "Process Node F - Done",
                desc: "Dequeue final leaf node F and visit it. The queue is empty. BFS traversal is complete."
            }
        ]
    },
    'graph-dfs': {
        name: "Graph DFS Simulator",
        graph: {
            nodes: ['A', 'B', 'C', 'D'],
            edges: [
                { u: 'A', v: 'B' },
                { u: 'A', v: 'C' },
                { u: 'B', v: 'D' },
                { u: 'C', v: 'D' }
            ]
        },
        steps: [
            {
                currentNode: 'A',
                stack: ['A'],
                visited: ['A'],
                title: "Push Root Node A",
                desc: "Start Graph DFS from node A. Push A onto the traversal stack and mark it as visited."
            },
            {
                currentNode: 'B',
                stack: ['A', 'B'],
                visited: ['A', 'B'],
                title: "Explore Neighbor B",
                desc: "From active node A, discover its unvisited neighbor B. Push B onto the stack and mark it as visited."
            },
            {
                currentNode: 'D',
                stack: ['A', 'B', 'D'],
                visited: ['A', 'B', 'D'],
                title: "Explore Neighbor D",
                desc: "From active node B, discover its unvisited neighbor D. Push D onto the stack and mark it as visited."
            },
            {
                currentNode: 'C',
                stack: ['A', 'B', 'D', 'C'],
                visited: ['A', 'B', 'D', 'C'],
                title: "Explore Neighbor C",
                desc: "From active node D, discover its unvisited neighbor C. Push C onto the stack and mark it as visited."
            },
            {
                currentNode: 'C',
                stack: ['A', 'B', 'D'],
                visited: ['A', 'B', 'D', 'C'],
                title: "Backtrack from C",
                desc: "Node C has no unvisited neighbors (A and D are already visited). Pop C from stack and backtrack to D."
            },
            {
                currentNode: 'D',
                stack: ['A', 'B'],
                visited: ['A', 'B', 'D', 'C'],
                title: "Backtrack from D",
                desc: "Node D has no other unvisited neighbors. Pop D from stack and backtrack to B."
            },
            {
                currentNode: 'B',
                stack: ['A'],
                visited: ['A', 'B', 'D', 'C'],
                title: "Backtrack from B - Done",
                desc: "Pop B from stack. Backtrack to A. All reachable nodes visited."
            }
        ]
    },
    'graph-bfs': {
        name: "Graph BFS Simulator",
        graph: {
            nodes: ['A', 'B', 'C', 'D'],
            edges: [
                { u: 'A', v: 'B' },
                { u: 'A', v: 'C' },
                { u: 'B', v: 'D' },
                { u: 'C', v: 'D' }
            ]
        },
        steps: [
            {
                currentNode: 'A',
                queue: ['A'],
                visited: ['A'],
                title: "Initialize Queue with A",
                desc: "Start Graph BFS from node A. Enqueue A and mark it as visited."
            },
            {
                currentNode: 'A',
                queue: ['B', 'C'],
                visited: ['A', 'B', 'C'],
                title: "Dequeue A, Enqueue Neighbors",
                desc: "Dequeue node A. Add its unvisited neighbors B and C to the queue, and mark them as visited."
            },
            {
                currentNode: 'B',
                queue: ['C', 'D'],
                visited: ['A', 'B', 'C', 'D'],
                title: "Dequeue B, Enqueue D",
                desc: "Dequeue node B. Add its unvisited neighbor D to the queue and mark it as visited."
            },
            {
                currentNode: 'C',
                queue: ['D'],
                visited: ['A', 'B', 'C', 'D'],
                title: "Dequeue C",
                desc: "Dequeue node C. Its neighbor D is already visited, so do nothing."
            },
            {
                currentNode: 'D',
                queue: [],
                visited: ['A', 'B', 'C', 'D'],
                title: "Dequeue D - Done",
                desc: "Dequeue node D. The queue is empty. BFS completed successfully."
            }
        ]
    },
    'dijkstra': {
        name: "Dijkstra's Simulator",
        graph: {
            nodes: ['A', 'B', 'C', 'D'],
            edges: [
                { u: 'A', v: 'B', w: 4 },
                { u: 'A', v: 'C', w: 2 },
                { u: 'B', v: 'D', w: 3 },
                { u: 'C', v: 'D', w: 1 }
            ]
        },
        steps: [
            {
                currentNode: 'A',
                distances: { A: 0, B: '∞', C: '∞', D: '∞' },
                pq: [{ node: 'A', dist: 0 }],
                title: "Initialize Dijkstra's",
                desc: "Set distance to start node A as 0, and all other nodes as infinity. Add A (0) to Priority Queue."
            },
            {
                currentNode: 'A',
                distances: { A: 0, B: 4, C: 2, D: '∞' },
                pq: [{ node: 'C', dist: 2 }, { node: 'B', dist: 4 }],
                title: "Relax Neighbors of A",
                desc: "Pop node A. Update neighbors: dist(B) = min(inf, 0 + 4) = 4, and dist(C) = min(inf, 0 + 2) = 2. Push B(4) and C(2) to PQ."
            },
            {
                currentNode: 'C',
                distances: { A: 0, B: 4, C: 2, D: 3 },
                pq: [{ node: 'D', dist: 3 }, { node: 'B', dist: 4 }],
                title: "Relax Neighbors of C",
                desc: "Pop C (min distance 2). Update neighbor D: dist(D) = min(inf, 2 + 1) = 3. Add D(3) to PQ."
            },
            {
                currentNode: 'D',
                distances: { A: 0, B: 4, C: 2, D: 3 },
                pq: [{ node: 'B', dist: 4 }],
                title: "Pop Node D",
                desc: "Pop node D (distance 3). Check neighbors: neighbor B distance through D is 3 + 3 = 6, which is larger than current B distance (4). No update."
            },
            {
                currentNode: 'B',
                distances: { A: 0, B: 4, C: 2, D: 3 },
                pq: [],
                title: "Pop Node B - Done",
                desc: "Pop node B (distance 4). PQ is empty. Shortest paths found: A:0, B:4, C:2, D:3."
            }
        ]
    },
    'heap': {
        name: "Min-Heap Simulator",
        heapArray: [12, 11, 13, 5, 6, 7],
        steps: [
            {
                array: [12, 11, 13, 5, 6, 7],
                activeIndices: [],
                title: "Initial Array Representation",
                desc: "Represent the array [12, 11, 13, 5, 6, 7] as a binary tree. We will heapify it bottom-up to form a valid Min-Heap."
            },
            {
                array: [12, 5, 13, 11, 6, 7],
                activeIndices: [1, 3],
                title: "Heapify Subtree at Index 1",
                desc: "Compare node 11 at index 1 with its child 5 at index 3. Swap them since 5 < 11."
            },
            {
                array: [5, 12, 13, 11, 6, 7],
                activeIndices: [0, 1],
                title: "Heapify Subtree at Root (Index 0)",
                desc: "Compare root node 12 with its children 5 (index 1) and 13 (index 2). The minimum is 5. Swap them."
            },
            {
                array: [5, 6, 13, 11, 12, 7],
                activeIndices: [1, 4],
                title: "Sift Down Root Successor",
                desc: "Node 12 has sifted to index 1. Compare it with its children 11 and 6. The minimum is 6. Swap them. Min-Heap complete!"
            }
        ]
    },
    'union-find': {
        name: "Union-Find (DSU) Simulator",
        elements: [0, 1, 2, 3, 4],
        steps: [
            {
                parent: [0, 1, 2, 3, 4],
                title: "Initialize Disjoint Sets",
                desc: "Create 5 singleton sets. Each element points to itself as its parent: parent = [0, 1, 2, 3, 4].",
                active: []
            },
            {
                parent: [0, 0, 2, 3, 4],
                title: "Perform Union(0, 1)",
                desc: "Find root of 0 (0) and root of 1 (1). Set parent of 1 to 0. Parents: [0, 0, 2, 3, 4].",
                active: [0, 1]
            },
            {
                parent: [0, 0, 2, 2, 4],
                title: "Perform Union(2, 3)",
                desc: "Find root of 2 (2) and root of 3 (3). Link 3 under 2. Parents: [0, 0, 2, 2, 4].",
                active: [2, 3]
            },
            {
                parent: [0, 0, 0, 2, 4],
                title: "Perform Union(1, 3)",
                desc: "Find root of 1 (0) and root of 3 (2). Set parent of 2 to 0. Parents: [0, 0, 0, 2, 4].",
                active: [1, 3]
            },
            {
                parent: [0, 0, 0, 0, 4],
                title: "Find(3) with Path Compression",
                desc: "Find root of 3: traverse 3 -> 2 -> 0. After finding root 0, compress the path by setting parent[3] = 0 directly. Parents: [0, 0, 0, 0, 4].",
                active: [3]
            }
        ]
    },
    'trie': {
        name: "Trie (Prefix Tree) Simulator",
        steps: [
            {
                nodes: { val: 'root', children: {} },
                title: "Empty Trie",
                desc: "Initialize a root node with an empty map of children."
            },
            {
                nodes: { val: 'root', children: { 'c': { val: 'c', children: {} } } },
                title: "Insert 'c' for 'cat'",
                desc: "Read character 'c' of word 'cat'. Root does not have child 'c', so we create a new 'c' node."
            },
            {
                nodes: { val: 'root', children: { 'c': { val: 'c', children: { 'a': { val: 'a', children: {} } } } } },
                title: "Insert 'a' for 'cat'",
                desc: "Read next character 'a'. Node 'c' does not have child 'a', so we create a new 'a' node."
            },
            {
                nodes: { val: 'root', children: { 'c': { val: 'c', children: { 'a': { val: 'a', children: { 't': { val: 't', isWord: true, children: {} } } } } } } },
                title: "Insert 't' for 'cat'",
                desc: "Read final character 't'. Create a new node 't' under 'a' and mark it as isWord = true. Insertion of 'cat' is complete."
            },
            {
                nodes: { val: 'root', children: { 'c': { val: 'c', children: { 'a': { val: 'a', children: { 't': { val: 't', isWord: true, children: {} }, 'r': { val: 'r', isWord: true, children: {} } } } } } } },
                title: "Insert 'car'",
                desc: "Insert word 'car'. Traverse 'c' -> 'a' (reused from 'cat'). For character 'r', since it doesn't exist under 'a', create a new 'r' node and mark it isWord = true."
            }
        ]
    },
    'dp-visualizer': {
        name: "DP Grid Simulator",
        grid: [
            ['', 'A', 'B'],
            ['A', 0, 0],
            ['B', 0, 0]
        ],
        steps: [
            {
                cell: null,
                gridState: [
                    [0, 0, 0],
                    [0, 0, 0],
                    [0, 0, 0]
                ],
                title: "Initialize DP Table",
                desc: "Finding LCS for s1 = 'AB' and s2 = 'AB'. We set up a 3x3 grid (representing length 0, 1, 2 for each string). Fill the base row and column with 0."
            },
            {
                cell: [1, 1],
                gridState: [
                    [0, 0, 0],
                    [0, 1, 0],
                    [0, 0, 0]
                ],
                title: "Process dp[1][1]",
                desc: "Compare s1[0] ('A') with s2[0] ('A'). Since they match, dp[1][1] = dp[0][0] + 1 = 0 + 1 = 1."
            },
            {
                cell: [1, 2],
                gridState: [
                    [0, 0, 0],
                    [0, 1, 1],
                    [0, 0, 0]
                ],
                title: "Process dp[1][2]",
                desc: "Compare s1[0] ('A') with s2[1] ('B'). Mismatch, so dp[1][2] = max(dp[0][2], dp[1][1]) = max(0, 1) = 1."
            },
            {
                cell: [2, 1],
                gridState: [
                    [0, 0, 0],
                    [0, 1, 1],
                    [0, 1, 0]
                ],
                title: "Process dp[2][1]",
                desc: "Compare s1[1] ('B') with s2[0] ('A'). Mismatch, so dp[2][1] = max(dp[1][1], dp[2][0]) = max(1, 0) = 1."
            },
            {
                cell: [2, 2],
                gridState: [
                    [0, 0, 0],
                    [0, 1, 1],
                    [0, 1, 2]
                ],
                title: "Process dp[2][2]",
                desc: "Compare s1[1] ('B') with s2[1] ('B'). Match, so dp[2][2] = dp[1][1] + 1 = 1 + 1 = 2. LCS length is 2."
            }
        ]
    },
    'backtracking': {
        name: "Backtracking Search Space Simulator",
        steps: [
            {
                state: '[]',
                choices: [1, 2],
                visited: [],
                title: "Root State",
                desc: "Find permutations of [1, 2]. Start with an empty decision state. Choices available: 1 and 2."
            },
            {
                state: '[1]',
                choices: [2],
                visited: [],
                title: "Choose 1",
                desc: "Choose number 1. Add it to the path. Choices left: 2. Recurse deeper."
            },
            {
                state: '[1, 2]',
                choices: [],
                visited: [],
                title: "Choose 2 - Permutation Found!",
                desc: "Choose number 2. No choices left. A leaf node in our decision tree has been reached. Save the permutation [1, 2]."
            },
            {
                state: '[1]',
                choices: [],
                visited: [2],
                title: "Backtrack to [1]",
                desc: "Backtrack: remove 2 from the path. Since there are no more choices at [1], pop 1 to backtrack to root."
            },
            {
                state: '[]',
                choices: [2],
                visited: [1],
                title: "Backtrack to Root",
                desc: "Backtrack to root. Now explore the next option: choice 2."
            },
            {
                state: '[2]',
                choices: [1],
                visited: [],
                title: "Choose 2",
                desc: "Choose number 2. Path becomes [2]. Choices left: 1."
            },
            {
                state: '[2, 1]',
                choices: [],
                visited: [],
                title: "Choose 1 - Permutation Found!",
                desc: "Choose number 1. Leaf reached. Save the permutation [2, 1]. Done."
            }
        ]
    },
    'lru-cache': {
        name: "LRU Cache Simulator",
        capacity: 3,
        steps: [
            {
                operation: "Put(1, 10)",
                cache: { 1: 10 },
                order: [1],
                evicted: null,
                title: "Insert Key 1",
                desc: "Cache has space. Insert key-value pair (1: 10). Key 1 is marked as Most Recently Used (MRU)."
            },
            {
                operation: "Put(2, 20)",
                cache: { 1: 10, 2: 20 },
                order: [2, 1],
                evicted: null,
                title: "Insert Key 2",
                desc: "Insert (2: 20). 2 becomes the MRU key at the head. Order: 2 (MRU) -> 1 (LRU)."
            },
            {
                operation: "Put(3, 30)",
                cache: { 1: 10, 2: 20, 3: 30 },
                order: [3, 2, 1],
                evicted: null,
                title: "Insert Key 3",
                desc: "Insert (3: 30). Cache is now at capacity. Order: 3 (MRU) -> 2 -> 1 (LRU)."
            },
            {
                operation: "Get(1)",
                cache: { 1: 10, 2: 20, 3: 30 },
                order: [1, 3, 2],
                evicted: null,
                title: "Cache Hit: Get(1)",
                desc: "Retrieve key 1. Cache hit! Move key 1 from tail to head as it is now the Most Recently Used. Order: 1 (MRU) -> 3 -> 2 (LRU)."
            },
            {
                operation: "Put(4, 40)",
                cache: { 1: 10, 3: 30, 4: 40 },
                order: [4, 1, 3],
                evicted: 2,
                title: "Cache Eviction: Put(4, 40)",
                desc: "Cache is full. Key 2 is the Least Recently Used (LRU). Evict key 2 from the cache, and insert key 4 at the head. Order: 4 (MRU) -> 1 -> 3 (LRU)."
            }
        ]
    }
};

// Evolution chain data mapping
const EVOLUTION_CHAINS = {
    "Two Sum (Sorted)": ["Two Sum", "3Sum", "3Sum Closest", "4Sum"],
    "Edit Distance": ["LCS", "Edit Distance", "Distinct Subsequences", "Wildcard Matching"],
    "N-Queens Puzzle": ["Subset Sum", "Permutations", "N-Queens", "Sudoku Solver"],
    "Longest Palindromic Substring": ["Reverse String", "Valid Palindrome", "Longest Palindromic Substring", "Palindrome Partitioning"]
};

// AI Mentor hint library
const MENTOR_HINTS = {
    "Two Sum (Sorted)": [
        "Since the array is sorted, think about whether nested loops are redundant. Can we compare elements at the boundaries?",
        "If you sum numbers[left] and numbers[right] and it exceeds target, does incrementing left help? No, that only increases the sum. Try decrementing right instead.",
        "Correct! Decrementing right decreases the sum. Incrementing left increases it. Keep going until they meet."
    ],
    "Edit Distance": [
        "Think of the subproblems: w1[0..i] and w2[0..j]. If w1[i] == w2[j], the cost is w1[0..i-1] and w2[0..j-1] with no new operation.",
        "If they don't match, we have 3 choices: insert, delete, or replace. The cost is 1 + min of those options.",
        "Use a 2D grid of size (M+1) x (N+1) where dp[i][j] stores the min edits for w1[0..i-1] and w2[0..j-1]."
    ],
    "N-Queens Puzzle": [
        "This is a constraint satisfaction problem. You can place one queen in each row, checking for conflicts on columns and diagonals.",
        "Instead of scanning the board diagonals repeatedly in O(N), use boolean arrays to track which columns, main diagonals, and anti-diagonals are occupied.",
        "After placing a queen and recursing, make sure to undo the state change (backtrack) before trying the next column."
    ],
    "Longest Palindromic Substring": [
        "Every palindrome expands around its center. A string of length N has 2N-1 potential centers (odd and even length center spots).",
        "At each center index, expand outwards as long as s[left] == s[right]. Track the maximum length and starting index.",
        "Compare odd expansion centers (i, i) and even expansion centers (i, i+1) to find the absolute longest substring."
    ]
};

// State Manager
let state = {
    currentTab: 'dashboard', // 'dashboard' | 'roadmap' | 'paths' | 'wiki' | 'simulator' | 'interview' | 'recruiter' | 'patterns'
    selectedPatternIndex: 0,
    searchQuery: '',
    difficultyFilter: 'all',
    companyFilter: 'all',
    revisionFilter: 'all',
    showBookmarksOnly: false,
    
    // Loaded from LocalStorage
    bookmarks: JSON.parse(localStorage.getItem('algoprep_bookmarks')) || [],
    favorites: JSON.parse(localStorage.getItem('algoprep_favorites')) || [],
    revisions: JSON.parse(localStorage.getItem('algoprep_revisions')) || {}, // maps title to 'Mastered' | 'Needs Revision' | 'Not Practiced Recently'
    paths: JSON.parse(localStorage.getItem('algoprep_paths')) || {}, // checklist items
    
    currentProblem: null,
    selectedWikiIndex: 0,
    
    // Simulator states
    simType: 'two-pointer',
    simStep: 0,
    simIsPlaying: false,
    simInterval: null,
    
    // Interview states
    interviewActive: false,
    interviewTimer: 45 * 60,
    interviewTimerId: null,
    interviewDifficulty: 'Medium',
    interviewCompany: 'General',
    interviewQuestions: [],
    interviewResults: [false, false, false],
    interviewRound: 0,
    notes: ['', '', ''],
    
    hintCounts: {},
    expandedRoadmapPatterns: []
};

// Heuristics Helper functions
function getProblemDifficulty(title) {
    const lowerTitle = title.toLowerCase();
    if (EASY_PROBLEMS.some(p => lowerTitle.includes(p))) {
        return 'Easy';
    }
    if (HARD_PROBLEMS.some(p => lowerTitle.includes(p))) {
        return 'Hard';
    }
    return 'Medium';
}

function getProblemCompanies(title) {
    const code = title.split('').reduce((acc, char) => acc + char.charCodeAt(0), 0);
    const result = [];
    const count = 1 + (code % 3);
    for (let i = 0; i < count; i++) {
        const index = (code + i * 7) % COMPANIES.length;
        if (!result.includes(COMPANIES[index])) {
            result.push(COMPANIES[index]);
        }
    }
    return result;
}

function getProblemRevision(title) {
    if (state.revisions[title]) {
        return state.revisions[title];
    }
    const code = title.split('').reduce((acc, char) => acc + char.charCodeAt(0), 0);
    const statuses = ['Mastered', 'Needs Revision', 'Not Practiced Recently'];
    return statuses[code % statuses.length];
}

// Interactive flowchart nodes


// Render Managers
function renderSidebar() {
    const sidebarContainer = document.getElementById('sidebar-menu');
    sidebarContainer.innerHTML = '';

    // De-activate all global menu tabs
    document.querySelectorAll('.menu-item').forEach(item => item.classList.remove('active'));
    
    // Activate current menu tab in sidebar if applicable
    const activeTabEl = document.getElementById(`nav-${state.currentTab}`);
    if (activeTabEl) {
        activeTabEl.classList.add('active');
    }

    // Populate collapsible Patterns
    dsaData.forEach((pattern, index) => {
        const item = document.createElement('div');
        
        // Count solved %
        const total = pattern.problems.length;
        let masteredCount = 0;
        pattern.problems.forEach(p => {
            if (getProblemRevision(p.title) === 'Mastered') {
                masteredCount++;
            }
        });
        const pct = total > 0 ? Math.round((masteredCount / total) * 100) : 0;

        item.className = `menu-item ${state.currentTab === 'patterns' && state.selectedPatternIndex === index ? 'active' : ''}`;
        
        let displayName = pattern.name.replace(/^\d+\.\s+/, '');
        item.innerHTML = `
            <div style="display:flex; flex-direction:column; gap:2px;">
                <span class="menu-item-text" title="${pattern.name}">${displayName}</span>
                <span style="font-size:10px; color:var(--text-dark); font-weight:600;">Completion: ${pct}%</span>
            </div>
            <span class="menu-badge">${total}</span>
        `;
        
        item.addEventListener('click', () => {
            state.currentTab = 'patterns';
            state.selectedPatternIndex = index;
            state.currentProblem = null;
            renderSidebar();
            renderView();
        });

        sidebarContainer.appendChild(item);
    });
}

function updateGlobalStats() {
    let total = 0;
    let easy = 0, med = 0, hrd = 0;
    let mastered = 0;

    dsaData.forEach(pattern => {
        pattern.problems.forEach(prob => {
            total++;
            const diff = getProblemDifficulty(prob.title);
            if (diff === 'Easy') easy++;
            else if (diff === 'Medium') med++;
            else if (diff === 'Hard') hrd++;

            if (getProblemRevision(prob.title) === 'Mastered') {
                mastered++;
            }
        });
    });

    const completionPct = total > 0 ? Math.round((mastered / total) * 100) : 0;
    
    // Update top bar small counters
    document.getElementById('top-solved-count').textContent = total;
    document.getElementById('top-progress-percent').textContent = `${completionPct}%`;

    return { total, easy, med, hrd, mastered, completionPct };
}

function renderView() {
    // Clear simulator autoplay interval if leaving the simulator tab
    if (state.currentTab !== 'simulator' && state.simInterval) {
        clearInterval(state.simInterval);
        state.simInterval = null;
        state.simIsPlaying = false;
    }

    // Hide Solution view, show dynamic content
    document.getElementById('code-viewer-container').style.display = 'none';
    const viewContainer = document.getElementById('view-container');
    viewContainer.style.display = 'flex';

    if (state.currentTab === 'dashboard') {
        renderDashboardView(viewContainer);
    } else if (state.currentTab === 'roadmap') {
        renderRoadmapView(viewContainer);
    } else if (state.currentTab === 'paths') {
        renderPathsView(viewContainer);
    } else if (state.currentTab === 'wiki') {
        renderWikiView(viewContainer);
    } else if (state.currentTab === 'simulator') {
        renderSimulatorView(viewContainer);
    } else if (state.currentTab === 'interview') {
        renderInterviewView(viewContainer);
    } else if (state.currentTab === 'patterns') {
        renderPatternsView(viewContainer);
    }
}

// --------------------------------------------------------------------
// SECTION 1 & 2: DASHBOARD VIEW
// --------------------------------------------------------------------
function renderDashboardView(container) {
    const stats = updateGlobalStats();

    // Generate Heatmap Activity Blocks
    let heatmapHtml = '';
    for (let w = 0; w < 26; w++) {
        let colHtml = '';
        for (let d = 0; d < 7; d++) {
            // deterministic active levels using day grid indexing
            const index = w * 7 + d;
            const levelVal = (index % 11 === 0) ? 'level-4' : 
                             (index % 7 === 0) ? 'level-3' : 
                             (index % 5 === 0) ? 'level-2' : 
                             (index % 3 === 0) ? 'level-1' : '';
            const countText = levelVal === 'level-4' ? '4 problems solved' :
                              levelVal === 'level-3' ? '3 problems solved' :
                              levelVal === 'level-2' ? '2 problems solved' :
                              levelVal === 'level-1' ? '1 problem solved' : 'No activity';
            colHtml += `<div class="heatmap-day ${levelVal}" title="Day ${index + 1}: ${countText}"></div>`;
        }
        heatmapHtml += `<div class="heatmap-col">${colHtml}</div>`;
    }

    // Pick Featured Problems (e.g., Two Sum, Edit Distance, N-Queens, Longest Palindromic Substring)
    let featuredHtml = '';
    const featuredTitles = ["Two Sum (Sorted)", "Edit Distance", "N-Queens Puzzle", "Longest Palindromic Substring"];
    
    dsaData.forEach((pattern, pIdx) => {
        pattern.problems.forEach(prob => {
            if (featuredTitles.includes(prob.title)) {
                featuredHtml += renderProblemCardHtml(prob, pIdx);
            }
        });
    });

    // Circular Progress stroke calculations: diameter 120, radius 60, circ = 377
    const strokeOffset = 377 - (377 * stats.completionPct) / 100;

    container.innerHTML = `
        <div class="welcome-container animated-fadeIn">
            
            <!-- Hero Branding Section -->
            <div class="hero-branding">
                <div class="hero-profile-info">
                    <span class="hero-title">DSA Learning & Portfolio Portal</span>
                    <h1 class="hero-name">DSA Practice Hub</h1>
                    <p class="hero-desc">
                        A curated dashboard containing Java solutions, interactive visual step simulators, cheatsheets, and mock interviews to master algorithmic patterns.
                    </p>
                    <div class="hero-social-links">
                        <a href="https://github.com" target="_blank" class="social-btn primary"><i class="fab fa-github"></i> Repository</a>
                    </div>
                </div>

                <!-- Circular Progress SVG -->
                <div class="hero-progress-radial" title="Overall Mastery Progress">
                    <svg width="130" height="130" class="progress-ring-svg">
                        <defs>
                            <linearGradient id="progress-gradient" x1="0%" y1="0%" x2="100%" y2="100%">
                                <stop offset="0%" stop-color="var(--accent-cyan)" />
                                <stop offset="100%" stop-color="var(--accent-purple)" />
                            </linearGradient>
                        </defs>
                        <circle class="progress-ring-circle-bg" cx="65" cy="65" r="60" />
                        <circle class="progress-ring-circle" cx="65" cy="65" r="60" style="stroke-dashoffset: ${strokeOffset};" />
                    </svg>
                    <div class="radial-percentage-text">
                        <span>${stats.completionPct}%</span>
                        <span class="radial-lbl">Mastery</span>
                    </div>
                </div>
            </div>

            <!-- Stats Counters Cards Row -->
            <div class="stats-cards-row">
                <div class="dashboard-stat-card">
                    <div class="stat-info">
                        <span class="stat-label">Total Solved</span>
                        <span class="stat-value" id="count-total">${stats.total}</span>
                    </div>
                    <div class="stat-icon-box"><i class="fas fa-layer-group"></i></div>
                </div>
                <div class="dashboard-stat-card" style="border-color: rgba(16, 185, 129, 0.2);">
                    <div class="stat-info">
                        <span class="stat-label" style="color:var(--accent-emerald);">Easy</span>
                        <span class="stat-value" style="color:var(--accent-emerald);">${stats.easy}</span>
                    </div>
                    <div class="stat-icon-box" style="color:var(--accent-emerald);"><i class="fas fa-feather"></i></div>
                </div>
                <div class="dashboard-stat-card" style="border-color: rgba(245, 158, 11, 0.2);">
                    <div class="stat-info">
                        <span class="stat-label" style="color:#f59e0b;">Medium</span>
                        <span class="stat-value" style="color:#f59e0b;">${stats.med}</span>
                    </div>
                    <div class="stat-icon-box" style="color:#f59e0b;"><i class="fas fa-balance-scale"></i></div>
                </div>
                <div class="dashboard-stat-card" style="border-color: rgba(239, 68, 68, 0.2);">
                    <div class="stat-info">
                        <span class="stat-label" style="color:#ef4444;">Hard</span>
                        <span class="stat-value" style="color:#ef4444;">${stats.hrd}</span>
                    </div>
                    <div class="stat-icon-box" style="color:#ef4444;"><i class="fas fa-skull-crossbones"></i></div>
                </div>
            </div>

            <!-- Analytics Row: Heatmap & Progress Bars -->
            <div class="analytics-row" style="grid-template-columns: repeat(auto-fit, minmax(285px, 1fr));">
                
                <!-- Git-style Heatmap Calendar -->
                <div class="analytics-panel">
                    <div class="panel-title"><i class="fas fa-calendar-alt"></i> Consistency Practice Heatmap</div>
                    <div class="heatmap-grid">${heatmapHtml}</div>
                    <div class="heatmap-info">
                        <span>Consistency Level: Less active ➔ highly active</span>
                        <div style="display:flex; gap:4px; align-items:center;">
                            <div class="heatmap-day" style="width:10px; height:10px;"></div>
                            <div class="heatmap-day level-1" style="width:10px; height:10px;"></div>
                            <div class="heatmap-day level-2" style="width:10px; height:10px;"></div>
                            <div class="heatmap-day level-3" style="width:10px; height:10px;"></div>
                            <div class="heatmap-day level-4" style="width:10px; height:10px;"></div>
                        </div>
                    </div>
                </div>

                <!-- Revision & Roadmap Progress -->
                <div class="analytics-panel">
                    <div class="panel-title"><i class="fas fa-tasks"></i> Revision Tracker</div>
                    <div class="timeline-flex">
                        <div class="timeline-bar-row">
                            <span class="timeline-bar-label" style="color:var(--accent-emerald);">Mastered</span>
                            <div class="timeline-bar-wrapper">
                                <div class="timeline-bar-inner" style="width: ${stats.total > 0 ? (stats.mastered / stats.total * 100) : 0}%; background: var(--accent-emerald);"></div>
                            </div>
                            <span class="timeline-bar-value">${stats.mastered}</span>
                        </div>
                        <div class="timeline-bar-row">
                            <span class="timeline-bar-label" style="color:#f59e0b;">Needs Revision</span>
                            <div class="timeline-bar-wrapper">
                                <div class="timeline-bar-inner" style="width: 25%; background: #f59e0b;"></div>
                            </div>
                            <span class="timeline-bar-value">18</span>
                        </div>
                        <div class="timeline-bar-row">
                            <span class="timeline-bar-label" style="color:#ef4444;">Unpracticed</span>
                            <div class="timeline-bar-wrapper">
                                <div class="timeline-bar-inner" style="width: 15%; background: #ef4444;"></div>
                            </div>
                            <span class="timeline-bar-value">12</span>
                        </div>
                    </div>
                </div>

                <!-- Category Readiness Chart -->
                <div class="analytics-panel">
                    <div class="panel-title"><i class="fas fa-chart-line"></i> Category Readiness</div>
                    <div class="readiness-chart-container" style="height: 220px;">
                        <canvas id="readinessChart"></canvas>
                    </div>
                </div>

            </div>

            <!-- Featured Solutions Header -->
            <div class="recent-activities">
                <div class="panel-title" style="border:none; padding:0; margin-bottom:16px;">
                    <i class="fas fa-star"></i> Featured Algorithmic Implementations
                </div>
                <div class="problems-grid">${featuredHtml}</div>
            </div>

        </div>
    `;
    
    // Trigger number counts animation
    animateCounter('count-total', 0, stats.total, 800);
    // Render radar chart
    renderReadinessChart();
}

function animateCounter(id, start, end, duration) {
    const obj = document.getElementById(id);
    if (!obj) return;
    let startTimestamp = null;
    const step = (timestamp) => {
        if (!startTimestamp) startTimestamp = timestamp;
        const progress = Math.min((timestamp - startTimestamp) / duration, 1);
        obj.innerHTML = Math.floor(progress * (end - start) + start);
        if (progress < 1) {
            window.requestAnimationFrame(step);
        }
    };
    window.requestAnimationFrame(step);
}

// --------------------------------------------------------------------
// SECTION 3: ROADMAP VIEW
// --------------------------------------------------------------------
function renderRoadmapView(container) {
    let nodesHtml = '';
    dsaData.forEach((pattern, index) => {
        const total = pattern.problems.length;
        let mastered = 0;
        pattern.problems.forEach(p => {
            if (getProblemRevision(p.title) === 'Mastered') mastered++;
        });

        const pct = total > 0 ? Math.round((mastered / total) * 100) : 0;
        const status = pct === 100 ? 'completed' : pct > 0 ? 'in-progress' : 'not-started';
        const statusLabel = pct === 100 ? 'Completed' : pct > 0 ? 'In Progress' : 'Not Started';

        const isExpanded = state.expandedRoadmapPatterns && state.expandedRoadmapPatterns.includes(index);
        
        let problemsListHtml = '';
        if (isExpanded) {
            problemsListHtml = `<div class="roadmap-problems-list animated-fadeIn">`;
            pattern.problems.forEach(prob => {
                const diff = getProblemDifficulty(prob.title);
                const diffClass = `difficulty-${diff.toLowerCase()}`;
                const rev = getProblemRevision(prob.title);
                const statusBullet = rev === 'Mastered' ? '🟢' : rev === 'Needs Revision' ? '🟡' : '🔴';
                problemsListHtml += `
                    <div class="roadmap-problem-item" onclick="openDetailedSolution(${index}, '${prob.title.replace(/'/g, "\\'")}')">
                        <div class="roadmap-problem-left">
                            <span class="roadmap-problem-status-bullet">${statusBullet}</span>
                            <span class="roadmap-problem-title">${prob.title}</span>
                        </div>
                        <div class="roadmap-problem-right">
                            <span class="difficulty-badge ${diffClass}">${diff}</span>
                            <span class="roadmap-problem-complexity"><i class="fas fa-clock"></i> ${prob.timeComplexity}</span>
                            <span class="roadmap-problem-complexity"><i class="fas fa-memory"></i> ${prob.spaceComplexity}</span>
                        </div>
                    </div>
                `;
            });
            problemsListHtml += `</div>`;
        }

        nodesHtml += `
            <div class="roadmap-node ${status}">
                <div class="roadmap-node-dot"></div>
                <div style="display: flex; flex-direction: column; flex: 1; gap: 12px;">
                    <div class="roadmap-node-card ${isExpanded ? 'expanded' : ''}" onclick="toggleRoadmapPattern(${index})" style="cursor: pointer;">
                        <div class="roadmap-node-info">
                            <span class="roadmap-node-status">${statusLabel}</span>
                            <div class="roadmap-node-title">${pattern.name}</div>
                            <span style="font-size:12px; color:var(--text-muted); margin-top:4px;">${total} core algorithmic problems</span>
                        </div>
                        <div class="roadmap-node-progress">
                            <div class="roadmap-node-bar">
                                <div class="roadmap-node-bar-fill" style="width: ${pct}%;"></div>
                            </div>
                            <span class="roadmap-node-percent">${pct}%</span>
                            <i class="fas fa-chevron-down roadmap-node-chevron ${isExpanded ? 'expanded-chevron' : ''}"></i>
                        </div>
                    </div>
                    ${problemsListHtml}
                </div>
            </div>
        `;
    });

    container.innerHTML = `
        <div class="roadmap-view-container animated-fadeIn" style="display:flex; flex-direction:column; gap:24px;">
            <div class="pattern-header">
                <h1 class="pattern-title">Pattern Mastery Roadmap</h1>
                <p class="pattern-description">
                    Progress path mapping completion rates across structural algorithmic models. Grouping problem solving by pattern helps build index recognition speeds for coding interviews.
                </p>
            </div>
            <div class="roadmap-timeline">
                ${nodesHtml}
            </div>
        </div>
    `;
}

function toggleRoadmapPattern(index) {
    if (!state.expandedRoadmapPatterns) {
        state.expandedRoadmapPatterns = [];
    }
    const pos = state.expandedRoadmapPatterns.indexOf(index);
    if (pos === -1) {
        state.expandedRoadmapPatterns.push(index);
    } else {
        state.expandedRoadmapPatterns.splice(pos, 1);
    }
    renderView();
}

// --------------------------------------------------------------------
// SECTION 7: CHEATSHEETS VIEW
// --------------------------------------------------------------------
function renderCheatsheetsView(container) {
    const cheatsheets = [
        {
            title: "Sliding Window Pattern",
            desc: "Tracks subsegments of sequences (arrays/strings). Used for subarray operations under constraints.",
            usecases: "Min/Max sum subarray, longest substring with distinct characters.",
            blueprint: `int left = 0, right = 0;\nwhile (right < array.length) {\n    // Expand window\n    add(array[right]);\n    while (conditionViolated) {\n        // Shrink window\n        remove(array[left]);\n        left++;\n    }\n    // Update result\n    right++;\n}`
        },
        {
            title: "Two-Pointer Strategy",
            desc: "Iterates data structures using boundary indices moving inwards or secondary pointers tracking slots.",
            usecases: "Sorted pair sum, reversing structures, Dutch National Flag partitioning.",
            blueprint: `int left = 0, right = array.length - 1;\nwhile (left < right) {\n    int sum = array[left] + array[right];\n    if (sum == target) return result;\n    else if (sum < target) left++;\n    else right--;\n}`
        },
        {
            title: "Fast & Slow Pointers (Cycle Detection)",
            desc: "Moves two pointers at different speeds (1x and 2x) through linked list structures.",
            usecases: "Loop identification, cycle starts, calculating middle node in list.",
            blueprint: `Node slow = head, fast = head;\nwhile (fast != null && fast.next != null) {\n    slow = slow.next;\n    fast = fast.next.next;\n    if (slow == fast) return true; // Cycle detected\n}`
        },
        {
            title: "Cyclic Sort Pattern",
            desc: "Places elements in unsorted ranges containing values [1, N] into their correct indices [val - 1] in O(N).",
            usecases: "Duplicate numbers, missing positive ranges, mismatch indexes.",
            blueprint: `int i = 0;\nwhile (i < nums.length) {\n    int correctIndex = nums[i] - 1;\n    if (nums[i] != nums[correctIndex]) {\n        swap(nums, i, correctIndex);\n    } else {\n        i++;\n    }\n}`
        }
    ];

    let sheetsHtml = '';
    cheatsheets.forEach(sheet => {
        sheetsHtml += `
            <div class="cheatsheet-card animated-fadeIn">
                <div class="cheatsheet-header">
                    <i class="fas fa-file-code cheatsheet-icon"></i>
                    <h2 class="cheatsheet-title">${sheet.title}</h2>
                </div>
                <div style="font-size:13.5px; color:var(--text-muted); line-height:1.5; display:flex; flex-direction:column; gap:8px;">
                    <div><strong>Key Concept:</strong> ${sheet.desc}</div>
                    <div><strong>When to use:</strong> ${sheet.usecases}</div>
                </div>
                <div class="cheatsheet-blueprint">
                    <pre><code>${sheet.blueprint}</code></pre>
                </div>
            </div>
        `;
    });

    container.innerHTML = `
        <div class="cheatsheets-view-container animated-fadeIn" style="display:flex; flex-direction:column; gap:24px;">
            <div class="pattern-header">
                <h1 class="pattern-title">Algorithmic Cheatsheets</h1>
                <p class="pattern-description">
                    Quick reference cards containing implementation templates and structural logic templates for core patterns.
                </p>
            </div>
            <div class="cheatsheet-grid">
                ${sheetsHtml}
            </div>
        </div>
    `;
}





// --------------------------------------------------------------------
// SECTION 4: PATTERN PROBLEMS VIEW
// --------------------------------------------------------------------
function renderPatternsView(container) {
    const pattern = dsaData[state.selectedPatternIndex];
    if (!pattern) return;

    let problemsHtml = '';
    
    // Filter problems based on states
    let filteredProblems = pattern.problems.filter(prob => {
        // Search filter
        if (state.searchQuery !== '') {
            const mTitle = prob.title.toLowerCase().includes(state.searchQuery);
            const mCode = prob.code && prob.code.toLowerCase().includes(state.searchQuery);
            const mApproach = prob.approach.toLowerCase().includes(state.searchQuery);
            const mPattern = pattern.name.toLowerCase().includes(state.searchQuery);
            if (!mTitle && !mCode && !mApproach && !mPattern) return false;
        }

        // Difficulty filter
        if (state.difficultyFilter !== 'all') {
            const diff = getProblemDifficulty(prob.title).toLowerCase();
            if (diff !== state.difficultyFilter) return false;
        }

        // Company filter
        if (state.companyFilter !== 'all') {
            const comps = getProblemCompanies(prob.title).map(c => c.toLowerCase());
            if (!comps.includes(state.companyFilter)) return false;
        }

        // Revision status filter
        if (state.revisionFilter !== 'all') {
            const rev = getProblemRevision(prob.title).toLowerCase().replace(/\s+/g, '');
            const targetFilter = state.revisionFilter.replace(/\s+/g, '');
            if (rev !== targetFilter) return false;
        }

        // Bookmarks only
        if (state.showBookmarksOnly) {
            if (!state.bookmarks.includes(prob.title)) return false;
        }

        return true;
    });

    filteredProblems.forEach(prob => {
        problemsHtml += renderProblemCardHtml(prob, state.selectedPatternIndex);
    });

    if (filteredProblems.length === 0) {
        problemsHtml = `
            <div class="empty-state">
                <div class="empty-state-icon"><i class="fas fa-filter"></i></div>
                <div class="empty-state-title">No Solutions Match Filters</div>
                <div class="empty-state-desc">Try clearing search inputs or adjusting selection parameters.</div>
            </div>
        `;
    }

    container.innerHTML = `
        <div class="pattern-view-container animated-fadeIn" style="display:flex; flex-direction:column; gap:24px;">
            <div class="pattern-header">
                <h1 class="pattern-title">${pattern.name}</h1>
                <p class="pattern-description">${pattern.description || 'Optimized pattern traversal solutions.'}</p>
            </div>
            <div class="problems-grid">${problemsHtml}</div>
        </div>
    `;
}

function renderProblemCardHtml(prob, patternIdx) {
    const diff = getProblemDifficulty(prob.title);
    const diffClass = `difficulty-${diff.toLowerCase()}`;
    const companies = getProblemCompanies(prob.title);
    const revisionStatus = getProblemRevision(prob.title);
    
    // Icon state toggles
    const isBookmarked = state.bookmarks.includes(prob.title) ? 'active' : '';
    const isFavorite = state.favorites.includes(prob.title) ? 'active' : '';
    
    // Status color bullet
    const statusBullet = revisionStatus === 'Mastered' ? '🟢' : revisionStatus === 'Needs Revision' ? '🟡' : '🔴';

    return `
        <div class="enhanced-card animate-card">
            <div class="card-top-row">
                <div class="card-title-box">
                    <span class="card-pattern-name">${dsaData[patternIdx].name.replace(/^\d+\.\s+/, '')}</span>
                    <h3 class="card-title" onclick="openDetailedSolution(${patternIdx}, '${prob.title.replace(/'/g, "\\'")}')">${prob.title}</h3>
                </div>
                <div class="card-badge-group">
                    <span class="difficulty-badge ${diffClass}">${diff}</span>
                    <span class="revision-badge" onclick="toggleRevision('${prob.title.replace(/'/g, "\\'")}', event)" title="Toggle Revision Status">
                        ${statusBullet} ${revisionStatus}
                    </span>
                </div>
            </div>

            <div class="card-complexity">
                <span class="card-comp-item"><i class="fas fa-clock"></i>Time: <code>${prob.timeComplexity}</code></span>
                <span class="card-comp-item"><i class="fas fa-memory"></i>Space: <code>${prob.spaceComplexity}</code></span>
            </div>

            <p class="card-approach" onclick="openDetailedSolution(${patternIdx}, '${prob.title.replace(/'/g, "\\'")}')">
                ${prob.approach}
            </p>

            <div class="card-companies">
                ${companies.map(c => `<span class="company-tag">${c}</span>`).join('')}
            </div>

            <div class="card-footer">
                <div class="card-actions-btn">
                    <button class="icon-action-btn book-btn ${isBookmarked}" onclick="toggleBookmark('${prob.title.replace(/'/g, "\\'")}', event)" title="Bookmark Problem">
                        <i class="${isBookmarked ? 'fas' : 'far'} fa-bookmark"></i>
                    </button>
                    <button class="icon-action-btn fav-btn ${isFavorite}" onclick="toggleFavorite('${prob.title.replace(/'/g, "\\'")}', event)" title="Mark as Favorite">
                        <i class="${isFavorite ? 'fas' : 'far'} fa-heart"></i>
                    </button>
                </div>
                <span class="card-action-link" onclick="openDetailedSolution(${patternIdx}, '${prob.title.replace(/'/g, "\\'")}')">
                    Solution details <i class="fas fa-chevron-right"></i>
                </span>
            </div>
        </div>
    `;
}

// --------------------------------------------------------------------
// TAB & EVENT TRIGGER CONTROLLERS
// --------------------------------------------------------------------
function toggleBookmark(title, event) {
    if (event) event.stopPropagation();
    const idx = state.bookmarks.indexOf(title);
    if (idx === -1) {
        state.bookmarks.push(title);
    } else {
        state.bookmarks.splice(idx, 1);
    }
    localStorage.setItem('algoprep_bookmarks', JSON.stringify(state.bookmarks));
    renderSidebar();
    renderView();
}

function toggleFavorite(title, event) {
    if (event) event.stopPropagation();
    const idx = state.favorites.indexOf(title);
    if (idx === -1) {
        state.favorites.push(title);
    } else {
        state.favorites.splice(idx, 1);
    }
    localStorage.setItem('algoprep_favorites', JSON.stringify(state.favorites));
    renderSidebar();
    renderView();
}

function toggleRevision(title, event) {
    if (event) event.stopPropagation();
    const current = getProblemRevision(title);
    let nextStatus = 'Mastered';
    if (current === 'Mastered') nextStatus = 'Needs Revision';
    else if (current === 'Needs Revision') nextStatus = 'Not Practiced Recently';
    
    state.revisions[title] = nextStatus;
    localStorage.setItem('algoprep_revisions', JSON.stringify(state.revisions));
    renderSidebar();
    renderView();
}

// Global Search Results across all patterns
function renderSearchResults() {
    codeViewerContainer.style.display = 'none';
    const viewContainer = document.getElementById('view-container');
    viewContainer.style.display = 'flex';

    let matchingHtml = '';
    let matchCount = 0;

    dsaData.forEach((pattern, pIdx) => {
        pattern.problems.forEach(prob => {
            const mTitle = prob.title.toLowerCase().includes(state.searchQuery);
            const mCode = prob.code && prob.code.toLowerCase().includes(state.searchQuery);
            const mApproach = prob.approach.toLowerCase().includes(state.searchQuery);
            const mPattern = pattern.name.toLowerCase().includes(state.searchQuery);

            if (mTitle || mCode || mApproach || mPattern) {
                // Apply filters
                if (state.difficultyFilter !== 'all') {
                    const diff = getProblemDifficulty(prob.title).toLowerCase();
                    if (diff !== state.difficultyFilter) return;
                }
                if (state.companyFilter !== 'all') {
                    const comps = getProblemCompanies(prob.title).map(c => c.toLowerCase());
                    if (!comps.includes(state.companyFilter)) return;
                }
                if (state.revisionFilter !== 'all') {
                    const rev = getProblemRevision(prob.title).toLowerCase().replace(/\s+/g, '');
                    const targetFilter = state.revisionFilter.replace(/\s+/g, '');
                    if (rev !== targetFilter) return;
                }
                if (state.showBookmarksOnly && !state.bookmarks.includes(prob.title)) return;

                matchCount++;
                matchingHtml += renderProblemCardHtml(prob, pIdx);
            }
        });
    });

    if (matchCount === 0) {
        matchingHtml = `
            <div class="empty-state">
                <div class="empty-state-icon"><i class="fas fa-search"></i></div>
                <div class="empty-state-title">No Search Results Found</div>
                <div class="empty-state-desc">Adjust keywords or selections to expand the result.</div>
            </div>
        `;
    }

    viewContainer.innerHTML = `
        <div class="search-view-container animated-fadeIn" style="display:flex; flex-direction:column; gap:24px;">
            <div class="pattern-header">
                <h1 class="pattern-title">Search Results</h1>
                <p class="pattern-description">Showing ${matchCount} matches across all patterns for query "${state.searchQuery}"</p>
            </div>
            <div class="problems-grid">${matchingHtml}</div>
        </div>
    `;
}

// --------------------------------------------------------------------
// PROBLEM DETAILS SCREEN COMPONENT
// --------------------------------------------------------------------
function openDetailedSolution(patternIdx, problemTitle) {
    const pattern = dsaData[patternIdx];
    if (!pattern) return;
    const problem = pattern.problems.find(p => p.title === problemTitle);
    if (!problem) return;

    state.currentProblem = problem;

    // Reset AI Mentor Chat Log
    const chatLog = document.getElementById('mentor-chat-log');
    if (chatLog) {
        chatLog.innerHTML = `
            <div class="mentor-msg bot">
                Hello! I am your AI DSA Mentor. Let's work through this problem together. 
                If you are stuck, click the button below to get progressive hints.
            </div>
        `;
    }
    state.hintCounts[problem.title] = 0;

    // Toggle Screen views
    document.getElementById('view-container').style.display = 'none';
    const detailPanel = document.getElementById('code-viewer-container');
    detailPanel.style.display = 'grid';

    // Set Code viewer panel header
    document.getElementById('editor-filename').textContent = problem.fileName;
    const codePre = document.getElementById('editor-code');
    codePre.textContent = problem.code || '// Solution implementation code details not loaded.';
    Prism.highlightElement(codePre);

    // Resolve details using lookup or generate dynamically
    const details = PROBLEM_DETAILS[problem.title] || compileDynamicDetails(problem, pattern.name);

    // Populate Overview Tab Content
    const diff = getProblemDifficulty(problem.title);
    const revisionStatus = getProblemRevision(problem.title);
    const revBullet = revisionStatus === 'Mastered' ? '🟢' : revisionStatus === 'Needs Revision' ? '🟡' : '🔴';

    document.getElementById('detail-difficulty').className = `difficulty-badge difficulty-${diff.toLowerCase()}`;
    document.getElementById('detail-difficulty').textContent = diff;
    
    document.getElementById('detail-revision-badge').textContent = `${revBullet} ${revisionStatus}`;
    document.getElementById('detail-title').textContent = problem.title;
    document.getElementById('detail-pattern').textContent = `Pattern: ${pattern.name}`;
    document.getElementById('detail-time').textContent = problem.timeComplexity;
    document.getElementById('detail-space').textContent = problem.spaceComplexity;
    
    document.getElementById('detail-statement').innerHTML = details.statement;
    document.getElementById('detail-approach').innerHTML = details.approach;
    document.getElementById('detail-pattern-recognition').innerHTML = details.recognition;

    // Populate LeetCode source URL link
    const sourceLinkEl = document.getElementById('detail-source-link');
    if (problem.sourceUrl) {
        sourceLinkEl.style.display = 'inline-flex';
        sourceLinkEl.href = problem.sourceUrl;
    } else {
        sourceLinkEl.style.display = 'none';
    }

    // Populate Trace/Dry-Run Tab Content
    let timelineHtml = '';
    details.dryrun.forEach((step, idx) => {
        timelineHtml += `
            <div class="dryrun-step">
                <div class="dryrun-step-title">Step ${idx + 1}: ${step.title}</div>
                <div class="dryrun-step-desc">${step.desc}</div>
            </div>
        `;
    });
    document.getElementById('detail-dry-run-timeline').innerHTML = timelineHtml;
    document.getElementById('detail-alternatives').innerHTML = details.alternatives;

    // Populate Interview Tab Content
    const companies = getProblemCompanies(problem.title);
    document.getElementById('detail-companies').innerHTML = companies.map(c => `<span class="company-tag" style="padding:6px 12px; font-size:12px;">${c}</span>`).join('');
    
    document.getElementById('detail-mistakes').innerHTML = details.mistakes.map(m => `<li>${m}</li>`).join('');
    document.getElementById('detail-followups').innerHTML = details.followups;

    // Populate Problem Progression Evolution Chain
    const evolutionContainer = document.getElementById('detail-evolution-chain');
    if (evolutionContainer) {
        const chain = EVOLUTION_CHAINS[problem.title] || [problem.title, problem.title + " II", problem.title + " III"];
        let evolutionHtml = '';
        chain.forEach((node, idx) => {
            const isActive = node === problem.title ? 'active' : '';
            evolutionHtml += `<div class="evolution-node ${isActive}">${node}</div>`;
            if (idx < chain.length - 1) {
                evolutionHtml += `<div class="evolution-arrow"><i class="fas fa-chevron-right"></i></div>`;
            }
        });
        evolutionContainer.innerHTML = evolutionHtml;
    }

    // Default to Overview Tab
    switchDetailTab('tab-overview');
}

function compileDynamicDetails(problem, patternName) {
    const diff = getProblemDifficulty(problem.title);
    const time = problem.timeComplexity || 'O(N)';
    const space = problem.spaceComplexity || 'O(1)';
    return {
        statement: `Given the input parameters, write an optimized program to resolve **${problem.title}** conforming to structural performance constraints.`,
        recognition: `We use the **${patternName}** because the problem structure contains properties like cyclic ranges, window bounds, or tree depth levels which align with this template.`,
        approach: `We resolve the problem utilizing the core approach: *${problem.approach}*. This provides an efficient linear or logarithmic run execution.`,
        dryrun: [
            { title: "Initialization", desc: `Load input values, verify non-null boundaries, and initialize state pointers.` },
            { title: "Iterative Traversal", desc: `Scan the elements using complexity ${time} time. Verify constraints at each index.` },
            { title: "Return Result", desc: `Output the computed answer. Verify auxiliary memory space conforms to ${space}.` }
        ],
        alternatives: `1. **Brute Force Option**: Compare all combinations recursively or with nested iteration. Takes O(N^2) time.<br>2. **Hash-Based Lookup**: Uses auxiliary mapping lookup to trade space for execution speed.`,
        mistakes: [
            "Failing to validate null references or empty bounds.",
            "Off-by-one errors during boundary index pointer movements."
        ],
        followups: `Can you solve this problem with O(1) space? (Verify if in-place pointer modifications are possible).`
    };
}

function switchDetailTab(tabId) {
    // De-activate all tab buttons and panes
    document.querySelectorAll('.details-tabs .tab-btn').forEach(btn => btn.classList.remove('active'));
    document.querySelectorAll('.code-details-panel .tab-pane').forEach(pane => pane.classList.remove('active'));

    // Find active button and activate it
    const activeBtn = Array.from(document.querySelectorAll('.details-tabs .tab-btn')).find(b => b.getAttribute('onclick').includes(tabId));
    if (activeBtn) activeBtn.classList.add('active');

    // Show active pane
    const targetPane = document.getElementById(tabId);
    if (targetPane) targetPane.classList.add('active');
}

function closeCodeViewer() {
    document.getElementById('code-viewer-container').style.display = 'none';
    document.getElementById('view-container').style.display = 'flex';
    state.currentProblem = null;
    renderView();
}

function copyCode() {
    if (!state.currentProblem || !state.currentProblem.code) return;
    const copyBtn = document.getElementById('editor-copy-btn');
    navigator.clipboard.writeText(state.currentProblem.code)
        .then(() => {
            copyBtn.innerHTML = '<i class="fas fa-check"></i> Copied!';
            setTimeout(() => {
                copyBtn.innerHTML = '<i class="far fa-copy"></i> Copy';
            }, 2000);
        });
}

function downloadSolution() {
    if (!state.currentProblem) return;
    const element = document.createElement("a");
    const file = new Blob([state.currentProblem.code], { type: 'text/plain' });
    element.href = URL.createObjectURL(file);
    element.download = state.currentProblem.fileName;
    document.body.appendChild(element);
    element.click();
    document.body.removeChild(element);
}

function toggleExpandCode() {
    const panel = document.querySelector('.code-editor-panel');
    const expandBtn = document.getElementById('editor-expand-btn');
    panel.classList.toggle('expanded');
    
    if (panel.classList.contains('expanded')) {
        expandBtn.innerHTML = '<i class="fas fa-compress-alt"></i> Compress';
    } else {
        expandBtn.innerHTML = '<i class="fas fa-expand-alt"></i> Expand';
    }
}

// Navigation Listeners Setup
function registerNavTab(tabId) {
    const navEl = document.getElementById(`nav-${tabId}`);
    if (navEl) {
        navEl.addEventListener('click', () => {
            state.currentTab = tabId;
            state.currentProblem = null;
            renderSidebar();
            renderView();
        });
    }
}

function initNavigation() {
    const tabs = ['dashboard', 'roadmap', 'paths', 'wiki', 'simulator', 'interview'];
    tabs.forEach(registerNavTab);

    // Advanced search filter triggers
    document.getElementById('filter-difficulty').addEventListener('change', (e) => {
        state.difficultyFilter = e.target.value;
        if (state.currentTab === 'patterns') renderView();
        else if (state.searchQuery !== '') renderSearchResults();
    });

    document.getElementById('filter-company').addEventListener('change', (e) => {
        state.companyFilter = e.target.value;
        if (state.currentTab === 'patterns') renderView();
        else if (state.searchQuery !== '') renderSearchResults();
    });

    document.getElementById('filter-status').addEventListener('change', (e) => {
        state.revisionFilter = e.target.value;
        if (state.currentTab === 'patterns') renderView();
        else if (state.searchQuery !== '') renderSearchResults();
    });

    // Bookmark toggle btn
    const bookBtn = document.getElementById('btn-bookmarks');
    bookBtn.addEventListener('click', () => {
        state.showBookmarksOnly = !state.showBookmarksOnly;
        bookBtn.classList.toggle('active', state.showBookmarksOnly);
        if (state.currentTab === 'patterns') renderView();
        else if (state.searchQuery !== '') renderSearchResults();
    });

    // Global Search Event
    const searchInput = document.getElementById('search-input');
    searchInput.addEventListener('input', (e) => {
        state.searchQuery = e.target.value.trim().toLowerCase();
        if (state.searchQuery !== '') {
            renderSearchResults();
        } else {
            renderView();
        }
    });
}

// ====================================================================
// ADVANCED VIEWS & CONTROLLERS: PATHS, WIKI, COMPANIES, SIMULATORS & INTERVIEWS
// ====================================================================

function renderReadinessChart() {
    const ctx = document.getElementById('readinessChart');
    if (!ctx) return;

    // Calculate completions per pattern
    const labels = [];
    const data = [];

    dsaData.forEach(pattern => {
        const shortName = pattern.name.replace(/^\d+\.\s+/, '').replace(/\s+Pattern$/, '');
        labels.push(shortName);

        const total = pattern.problems.length;
        let mastered = 0;
        pattern.problems.forEach(p => {
            if (getProblemRevision(p.title) === 'Mastered') mastered++;
        });
        const pct = total > 0 ? Math.round((mastered / total) * 100) : 0;
        data.push(pct);
    });

    new Chart(ctx.getContext('2d'), {
        type: 'radar',
        data: {
            labels: labels,
            datasets: [{
                label: 'Mastery Level %',
                data: data,
                backgroundColor: 'rgba(6, 182, 212, 0.15)',
                borderColor: 'rgba(6, 182, 212, 0.85)',
                pointBackgroundColor: 'rgba(6, 182, 212, 1)',
                pointBorderColor: '#0f172a',
                pointHoverBackgroundColor: '#fff',
                pointHoverBorderColor: 'rgba(6, 182, 212, 1)',
                borderWidth: 2
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            scales: {
                r: {
                    angleLines: {
                        color: 'rgba(255, 255, 255, 0.1)'
                    },
                    grid: {
                        color: 'rgba(255, 255, 255, 0.08)'
                    },
                    pointLabels: {
                        color: 'rgba(255, 255, 255, 0.6)',
                        font: {
                            family: 'Outfit, Inter, sans-serif',
                            size: 9,
                            weight: '600'
                        }
                    },
                    ticks: {
                        backdropColor: 'transparent',
                        color: 'rgba(255, 255, 255, 0.3)',
                        font: {
                            size: 8
                        },
                        stepSize: 20
                    },
                    suggestedMin: 0,
                    suggestedMax: 100
                }
            },
            plugins: {
                legend: {
                    display: false
                }
            }
        }
    });
}

function renderPathsView(container) {
    let pathsGridHtml = '';
    
    PATHS_DATA.forEach(path => {
        // Calculate progress percentage
        let checkedCount = 0;
        path.tasks.forEach((task, idx) => {
            const key = `${path.id}_${idx}`;
            if (state.paths[key]) checkedCount++;
        });
        const totalCount = path.tasks.length;
        const progressPct = totalCount > 0 ? Math.round((checkedCount / totalCount) * 100) : 0;

        let checklistHtml = '';
        path.tasks.forEach((task, idx) => {
            const key = `${path.id}_${idx}`;
            const isChecked = state.paths[key] ? 'checked' : '';
            checklistHtml += `
                <label class="checklist-item">
                    <input type="checkbox" ${isChecked} onchange="togglePathTask('${path.id}', ${idx})">
                    <span style="${isChecked ? 'text-decoration:line-through; color:var(--text-dark);' : ''}">${task}</span>
                </label>
            `;
        });

        pathsGridHtml += `
            <div class="path-card">
                <div class="path-icon-header">
                    <span class="path-icon"><i class="fas ${path.icon}"></i></span>
                    <div>
                        <span class="path-meta">${path.meta}</span>
                        <h2 class="path-title">${path.title}</h2>
                    </div>
                </div>
                
                <div class="path-progress-container">
                    <div style="display:flex; justify-content:space-between; font-size:12px; font-weight:600; color:var(--text-muted);">
                        <span>Track Progress</span>
                        <span>${progressPct}%</span>
                    </div>
                    <div class="path-progress-bar">
                        <div class="path-progress-fill" style="width:${progressPct}%;"></div>
                    </div>
                </div>

                <div class="path-checklist">
                    ${checklistHtml}
                </div>
            </div>
        `;
    });

    container.innerHTML = `
        <div class="paths-view-container animated-fadeIn" style="display:flex; flex-direction:column; gap:24px;">
            <div class="pattern-header">
                <h1 class="pattern-title">Structured Learning Paths</h1>
                <p class="pattern-description">
                    Follow structured timelines to prepare for interviews. Track your completion rate as you solve and check off milestones.
                </p>
            </div>
            
            <div class="paths-grid">
                ${pathsGridHtml}
            </div>
        </div>
    `;
}

function togglePathTask(pathId, taskIdx) {
    const key = `${pathId}_${taskIdx}`;
    state.paths[key] = !state.paths[key];
    localStorage.setItem('algoprep_paths', JSON.stringify(state.paths));
    renderView(); // re-render to update progress bars
}

function renderWikiView(container) {
    let wikiMenuHtml = '';
    WIKI_DATA.forEach((item, idx) => {
        const isActive = idx === state.selectedWikiIndex ? 'active' : '';
        wikiMenuHtml += `
            <div class="wiki-menu-item ${isActive}" onclick="selectWikiTopic(${idx})">
                ${item.title}
            </div>
        `;
    });

    const topic = WIKI_DATA[state.selectedWikiIndex] || WIKI_DATA[0];

    container.innerHTML = `
        <div class="wiki-container animated-fadeIn">
            
            <!-- Left Wiki Navigation Menu -->
            <div class="wiki-sidebar">
                <div style="font-size:11px; text-transform:uppercase; font-weight:700; color:var(--text-dark); letter-spacing:0.5px; margin-bottom:8px; padding-left:14px;">Wiki Directory</div>
                ${wikiMenuHtml}
            </div>

            <!-- Right Content Panel -->
            <div class="wiki-content-panel">
                <span class="wiki-section-lbl">Concept Deep-Dive</span>
                <h1 class="wiki-title" style="margin-top:-8px;">${topic.title}</h1>
                <p class="wiki-text">
                    ${topic.concept}
                </p>

                <div style="background:rgba(255,255,255,0.01); border:1px solid var(--border-color); padding:16px 20px; border-radius:12px;">
                    <div class="wiki-section-lbl" style="font-size:10px;">Complexity Scaling</div>
                    <div style="font-size:14px; font-weight:700; color:#ffffff; margin-top:4px;">${topic.complexity}</div>
                </div>

                <div>
                    <h4 class="wiki-section-lbl" style="margin-bottom:12px;">Optimal Implementation Blueprint</h4>
                    <div class="code-pre-wrapper" style="border:1px solid var(--border-color); border-radius:12px; overflow:hidden;">
                        <pre style="margin:0;"><code id="wiki-code" class="language-java">${topic.template}</code></pre>
                    </div>
                </div>
            </div>

        </div>
    `;

    // Highlight blueprint code using PrismJS
    const codeEl = document.getElementById('wiki-code');
    if (codeEl) {
        Prism.highlightElement(codeEl);
    }
}

function selectWikiTopic(idx) {
    state.selectedWikiIndex = idx;
    renderView();
}



function renderSimulatorView(container) {
    const data = SIM_DATA[state.simType];
    if (!data) return;

    const step = data.steps[state.simStep];
    if (!step) return;

    // Render Nodes based on Simulator Type
    let nodesHtml = '';
    if (state.simType === 'two-pointer' || state.simType === 'sliding-window' || state.simType === 'binary-search') {
        data.array.forEach((val, idx) => {
            const isHighlighted = step.highlights && step.highlights.includes(idx);
            let highlightClass = '';
            if (isHighlighted) {
                highlightClass = step.matched ? 'matched' : 'highlighted';
            }

            // Compile overlapping pointer labels
            let pointerHtml = '';
            if (state.simType === 'two-pointer') {
                if (idx === step.left && idx === step.right) {
                    pointerHtml = `<div class="sim-pointer" style="color:var(--accent-cyan);"><i class="fas fa-arrow-up"></i>L & R</div>`;
                } else if (idx === step.left) {
                    pointerHtml = `<div class="sim-pointer" style="color:var(--accent-cyan);"><i class="fas fa-arrow-up"></i>Left</div>`;
                } else if (idx === step.right) {
                    pointerHtml = `<div class="sim-pointer" style="color:var(--accent-purple);"><i class="fas fa-arrow-up"></i>Right</div>`;
                }
            } else if (state.simType === 'sliding-window') {
                if (idx === step.start && idx === step.end) {
                    pointerHtml = `<div class="sim-pointer" style="color:var(--accent-cyan);"><i class="fas fa-arrow-up"></i>S & E</div>`;
                } else if (idx === step.start) {
                    pointerHtml = `<div class="sim-pointer" style="color:var(--accent-cyan);"><i class="fas fa-arrow-up"></i>Start</div>`;
                } else if (idx === step.end) {
                    pointerHtml = `<div class="sim-pointer" style="color:var(--accent-purple);"><i class="fas fa-arrow-up"></i>End</div>`;
                }
            } else if (state.simType === 'binary-search') {
                if (idx === step.low && idx === step.high && idx === step.mid) {
                    pointerHtml = `<div class="sim-pointer" style="color:#ffffff;"><i class="fas fa-arrow-up"></i>All</div>`;
                } else if (idx === step.low && idx === step.mid) {
                    pointerHtml = `<div class="sim-pointer" style="color:var(--accent-cyan);"><i class="fas fa-arrow-up"></i>L & M</div>`;
                } else if (idx === step.high && idx === step.mid) {
                    pointerHtml = `<div class="sim-pointer" style="color:var(--accent-purple);"><i class="fas fa-arrow-up"></i>M & H</div>`;
                } else if (idx === step.low && idx === step.high) {
                    pointerHtml = `<div class="sim-pointer" style="color:var(--accent-cyan);"><i class="fas fa-arrow-up"></i>L & H</div>`;
                } else if (idx === step.mid) {
                    pointerHtml = `<div class="sim-pointer" style="color:var(--accent-amber);"><i class="fas fa-arrow-up"></i>Mid</div>`;
                } else if (idx === step.low) {
                    pointerHtml = `<div class="sim-pointer" style="color:var(--accent-cyan);"><i class="fas fa-arrow-up"></i>Low</div>`;
                } else if (idx === step.high) {
                    pointerHtml = `<div class="sim-pointer" style="color:var(--accent-purple);"><i class="fas fa-arrow-up"></i>High</div>`;
                }
            }

            nodesHtml += `
                <div class="sim-array-node ${highlightClass}">
                    ${val}
                    ${pointerHtml}
                </div>
            `;
        });
        nodesHtml = `<div class="sim-array-container">${nodesHtml}</div>`;
    } else if (state.simType === 'merge-intervals') {
        nodesHtml = '<div class="sim-intervals-container">';
        data.intervals.forEach((interval, idx) => {
            const isHighlighted = step.highlights && step.highlights.includes(idx);
            let barClass = 'sim-interval-bar';
            if (isHighlighted) {
                barClass += (idx === step.index) ? ' active' : ' merged';
            }
            const start = interval[0];
            const end = interval[1];
            nodesHtml += `
                <div class="sim-interval-row">
                    <span class="sim-interval-label">Interval ${idx + 1}</span>
                    <div class="sim-interval-bar-container">
                        <div class="${barClass}" style="left: ${start * 5}%; width: ${(end - start) * 5}%;">
                            [${start}, ${end}]
                        </div>
                    </div>
                </div>
            `;
        });
        nodesHtml += '</div>';
    } else if (state.simType === 'fast-slow-pointer') {
        nodesHtml = '<div class="sim-list-container">';
        data.nodes.forEach((val, idx) => {
            let nodeClass = 'sim-list-node';
            if (idx === step.slow && idx === step.fast) {
                nodeClass += ' highlighted-both';
            } else if (idx === step.slow) {
                nodeClass += ' highlighted-slow';
            } else if (idx === step.fast) {
                nodeClass += ' highlighted-fast';
            }

            let label = '';
            if (idx === step.slow && idx === step.fast) {
                label = '<div class="sim-pointer" style="color:var(--accent-amber);"><i class="fas fa-arrow-up"></i>S&F</div>';
            } else if (idx === step.slow) {
                label = '<div class="sim-pointer" style="color:var(--accent-emerald);"><i class="fas fa-arrow-up"></i>Slow</div>';
            } else if (idx === step.fast) {
                label = '<div class="sim-pointer" style="color:var(--accent-purple);"><i class="fas fa-arrow-up"></i>Fast</div>';
            }

            nodesHtml += `
                <div class="${nodeClass}">
                    ${val}
                    ${label}
                </div>
            `;
            if (idx < data.nodes.length - 1) {
                nodesHtml += '<div class="sim-list-arrow"><i class="fas fa-long-arrow-alt-right"></i></div>';
            } else {
                nodesHtml += `
                    <div class="sim-list-arrow" style="color:var(--accent-purple); font-size:12px; font-weight:800; margin-left:8px;">
                        <i class="fas fa-undo"></i> points to ${data.cycleConnection.to + 1}
                    </div>
                `;
            }
        });
        nodesHtml += '</div>';
    } else if (state.simType === 'tree-dfs' || state.simType === 'tree-bfs') {
        const isVisited = (nodeId) => step.visited && step.visited.includes(nodeId);
        const isActive = (nodeId) => step.currentNode === nodeId;

        const getNodeClass = (nodeId) => {
            if (isActive(nodeId)) return 'sim-tree-node active';
            if (isVisited(nodeId)) return 'sim-tree-node visited';
            return 'sim-tree-node';
        };

        nodesHtml = `
            <div class="sim-tree-container">
                <div class="sim-tree-level">
                    <div class="${getNodeClass('A')}">A</div>
                </div>
                <div class="sim-tree-level" style="gap:120px; margin-top:8px;">
                    <div class="${getNodeClass('B')}">B</div>
                    <div class="${getNodeClass('C')}">C</div>
                </div>
                <div class="sim-tree-level" style="gap:60px; margin-top:8px;">
                    <div class="${getNodeClass('D')}">D</div>
                    <div class="${getNodeClass('E')}">E</div>
                    <div class="${getNodeClass('F')}">F</div>
                </div>
            </div>
        `;
    } else if (state.simType === 'graph-dfs' || state.simType === 'graph-bfs' || state.simType === 'dijkstra') {
        const activeNode = step.currentNode;
        const visitedNodes = step.visited || (step.distances ? Object.keys(step.distances).filter(k => step.distances[k] !== '∞') : []);

        const getGraphNodeClass = (nodeId) => {
            if (activeNode === nodeId) return 'sim-graph-node active';
            if (visitedNodes.includes(nodeId)) return 'sim-graph-node visited';
            return 'sim-graph-node';
        };

        let graphCanvasHtml = `
            <div class="sim-graph-canvas" style="width:100%; height:180px; position:relative;">
                <div class="${getGraphNodeClass('A')}" style="left:10%; top:35%;">A</div>
                <div class="${getGraphNodeClass('B')}" style="left:40%; top:10%;">B</div>
                <div class="${getGraphNodeClass('C')}" style="left:40%; top:60%;">C</div>
                <div class="${getGraphNodeClass('D')}" style="left:70%; top:35%;">D</div>
                
                ${state.simType === 'dijkstra' ? `
                    <div style="position:absolute; left:22%; top:20%; font-size:10px; color:var(--text-muted);">w: 4</div>
                    <div style="position:absolute; left:22%; top:55%; font-size:10px; color:var(--text-muted);">w: 2</div>
                    <div style="position:absolute; left:58%; top:20%; font-size:10px; color:var(--text-muted);">w: 3</div>
                    <div style="position:absolute; left:58%; top:55%; font-size:10px; color:var(--text-muted);">w: 1</div>
                ` : ''}
            </div>
        `;

        let infoTitle = '';
        let infoContent = '';
        if (state.simType === 'graph-dfs') {
            infoTitle = 'DFS Traversal Stack';
            infoContent = `Stack: [ ${step.stack.join(' -> ')} ]`;
        } else if (state.simType === 'graph-bfs') {
            infoTitle = 'BFS Traversal Queue';
            infoContent = `Queue: [ ${step.queue.join(' <- ')} ]`;
        } else if (state.simType === 'dijkstra') {
            infoTitle = "Dijkstra's Path Costs";
            infoContent = Object.keys(step.distances).map(k => `<div>Node ${k}: <strong>${step.distances[k]}</strong></div>`).join('');
        }

        nodesHtml = `
            <div class="sim-graph-container">
                ${graphCanvasHtml}
                <div class="sim-graph-info">
                    <span class="wiki-section-lbl" style="font-size:10px; margin-bottom:4px;">${infoTitle}</span>
                    <div style="color:#ffffff; font-weight:700; margin-top:4px;">${infoContent}</div>
                    <div style="font-size:11px; color:var(--text-muted); margin-top:8px;">Visited: [ ${visitedNodes.join(', ')} ]</div>
                </div>
            </div>
        `;
    } else if (state.simType === 'heap') {
        nodesHtml = '<div class="sim-array-container">';
        step.array.forEach((val, idx) => {
            const isActive = step.activeIndices && step.activeIndices.includes(idx);
            let nodeClass = 'sim-array-node';
            if (isActive) {
                nodeClass += ' highlighted';
            }
            nodesHtml += `
                <div class="${nodeClass}">
                    ${val}
                    <div style="font-size:9px; position:absolute; bottom:2px; color:var(--text-dark);">i:${idx}</div>
                </div>
            `;
        });
        nodesHtml += '</div>';
    } else if (state.simType === 'union-find') {
        nodesHtml = '<div class="sim-array-container" style="flex-wrap:wrap; justify-content:center;">';
        step.parent.forEach((p, idx) => {
            const isActive = step.active && step.active.includes(idx);
            let nodeClass = 'sim-array-node';
            if (isActive) nodeClass += ' highlighted';
            nodesHtml += `
                <div class="${nodeClass}" style="flex-direction:column; gap:2px; height:60px; width:65px;">
                    <span style="font-size:16px;">${idx}</span>
                    <span style="font-size:10px; color:var(--accent-cyan);">P: ${p}</span>
                </div>
            `;
        });
        nodesHtml += '</div>';
    } else if (state.simType === 'trie') {
        nodesHtml = `
            <div class="sim-tree-container">
                <div class="sim-tree-level">
                    <div class="sim-tree-node active" style="border-radius:8px; width:auto; padding:4px 12px;">Root</div>
                </div>
                ${step.title.includes("Insert 'c'") || step.title.includes("Insert 'a'") || step.title.includes("Insert 't'") || step.title.includes("Insert 'car'") ? `
                    <div class="sim-tree-level" style="margin-top:12px;">
                        <div class="sim-tree-node visited">c</div>
                    </div>
                ` : ''}
                ${step.title.includes("Insert 'a'") || step.title.includes("Insert 't'") || step.title.includes("Insert 'car'") ? `
                    <div class="sim-tree-level" style="margin-top:12px;">
                        <div class="sim-tree-node visited">a</div>
                    </div>
                ` : ''}
                ${step.title.includes("Insert 't'") || step.title.includes("Insert 'car'") ? `
                    <div class="sim-tree-level" style="margin-top:12px; gap:24px;">
                        <div class="sim-tree-node visited" style="border-color:var(--accent-emerald);">t <span style="font-size:8px; display:block; color:var(--accent-emerald);">[word]</span></div>
                        ${step.title.includes("Insert 'car'") ? `<div class="sim-tree-node active" style="border-color:var(--accent-emerald);">r <span style="font-size:8px; display:block; color:var(--accent-emerald);">[word]</span></div>` : ''}
                    </div>
                ` : ''}
            </div>
        `;
    } else if (state.simType === 'dp-visualizer') {
        let tableRows = '';
        tableRows += `
            <tr>
                <th></th>
                <th>''</th>
                <th>A</th>
                <th>B</th>
            </tr>
        `;
        const rowChars = ['', 'A', 'B'];
        for (let i = 0; i < 3; i++) {
            let rowHtml = `<tr><th>${rowChars[i] || "''"}</th>`;
            for (let j = 0; j < 3; j++) {
                const isActive = step.cell && step.cell[0] === i && step.cell[1] === j;
                const cellClass = isActive ? 'class="active"' : '';
                const cellVal = step.gridState[i][j];
                rowHtml += `<td ${cellClass}>${cellVal}</td>`;
            }
            rowHtml += '</tr>';
            tableRows += rowHtml;
        }

        nodesHtml = `
            <table class="sim-dp-table">
                ${tableRows}
            </table>
        `;
    } else if (state.simType === 'backtracking') {
        nodesHtml = `
            <div style="display:flex; flex-direction:column; gap:16px; align-items:center; width:100%;">
                <div style="background:rgba(255,255,255,0.02); border:1px solid var(--border-color); padding:16px 24px; border-radius:12px; text-align:center;">
                    <span class="wiki-section-lbl" style="font-size:10px;">Path (State)</span>
                    <div style="font-size:24px; font-weight:800; color:var(--accent-cyan); margin-top:4px;">${step.state}</div>
                </div>
                <div style="display:flex; gap:24px; justify-content:center;">
                    <div>
                        <span class="wiki-section-lbl" style="font-size:10px;">Available Choices</span>
                        <div style="font-size:16px; font-weight:700; color:#ffffff; margin-top:4px;">[ ${step.choices.join(', ')} ]</div>
                    </div>
                    <div style="border-left:1px solid var(--border-color); padding-left:24px;">
                        <span class="wiki-section-lbl" style="font-size:10px;">Backtracked/Visited</span>
                        <div style="font-size:16px; font-weight:700; color:var(--text-dark); margin-top:4px;">[ ${step.visited.join(', ')} ]</div>
                    </div>
                </div>
            </div>
        `;
    } else if (state.simType === 'lru-cache') {
        let slotsHtml = '';
        const order = step.order;
        for (let idx = 0; idx < data.capacity; idx++) {
            const key = order[idx];
            const hasVal = key !== undefined;
            const value = hasVal ? step.cache[key] : '-';
            
            let slotClass = 'sim-lru-slot';
            let badgeHtml = '';
            if (hasVal) {
                if (idx === 0) {
                    slotClass += ' mru';
                    badgeHtml = '<span class="sim-lru-badge mru">MRU</span>';
                } else if (idx === order.length - 1) {
                    slotClass += ' lru';
                    badgeHtml = '<span class="sim-lru-badge lru">LRU</span>';
                }
            }

            slotsHtml += `
                <div class="${slotClass}">
                    ${badgeHtml}
                    <span class="sim-lru-lbl">Slot ${idx + 1}</span>
                    <span class="sim-lru-val">${hasVal ? `Key ${key}: ${value}` : 'Empty'}</span>
                </div>
            `;
        }

        nodesHtml = `
            <div class="sim-lru-container">
                <div style="display:flex; justify-content:space-between; font-size:13px; font-weight:600; color:var(--text-muted); margin-bottom:8px;">
                    <span>Operation: <strong style="color:var(--accent-cyan);">${step.operation}</strong></span>
                    ${step.evicted ? `<span style="color:#ef4444;"><i class="fas fa-trash-alt"></i> Evicted Key: ${step.evicted}</span>` : ''}
                </div>
                <div class="sim-lru-slots">
                    ${slotsHtml}
                </div>
            </div>
        `;
    }

    // Render variables
    let varsHtml = '';
    if (state.simType === 'two-pointer') {
        varsHtml = `
            <div>Target Sum: <span style="color:var(--accent-cyan); font-weight:800;">${data.target}</span></div>
            <div style="border-left:1px solid var(--border-color); padding-left:16px;">Current Sum: <span style="color:${step.matched ? 'var(--accent-emerald)' : '#ffffff'}; font-weight:800;">${data.array[step.left] + data.array[step.right]}</span></div>
            <div style="border-left:1px solid var(--border-color); padding-left:16px;">Status: <span style="color:${step.matched ? 'var(--accent-emerald)' : 'var(--accent-cyan)'}; font-weight:800;">${step.matched ? 'MATCH FOUND!' : 'SEARCHING...'}</span></div>
        `;
    } else if (state.simType === 'sliding-window') {
        varsHtml = `
            <div>Window Size (K): <span style="color:var(--accent-cyan); font-weight:800;">${data.k}</span></div>
            <div style="border-left:1px solid var(--border-color); padding-left:16px;">Window Sum: <span style="color:#ffffff; font-weight:800;">${step.windowSum}</span></div>
            <div style="border-left:1px solid var(--border-color); padding-left:16px;">Max Sum: <span style="color:var(--accent-emerald); font-weight:800;">${step.maxSum}</span></div>
        `;
    } else if (state.simType === 'binary-search') {
        varsHtml = `
            <div>Target: <span style="color:var(--accent-cyan); font-weight:800;">${data.target}</span></div>
            <div style="border-left:1px solid var(--border-color); padding-left:16px;">Mid Value: <span style="color:var(--accent-amber); font-weight:800;">${data.array[step.mid]}</span></div>
            <div style="border-left:1px solid var(--border-color); padding-left:16px;">Status: <span style="color:${step.matched ? 'var(--accent-emerald)' : 'var(--accent-cyan)'}; font-weight:800;">${step.matched ? 'TARGET FOUND!' : 'SEARCHING...'}</span></div>
        `;
    } else if (state.simType === 'merge-intervals') {
        varsHtml = `
            <div>Current Interval: <span style="color:var(--accent-purple); font-weight:800;">[${step.activeInterval.join(', ')}]</span></div>
            <div style="border-left:1px solid var(--border-color); padding-left:16px;">Total Merged: <span style="color:var(--accent-emerald); font-weight:800;">${step.merged.length}</span></div>
        `;
    } else if (state.simType === 'fast-slow-pointer') {
        varsHtml = `
            <div>Slow index: <span style="color:var(--accent-emerald); font-weight:800;">${step.slow}</span></div>
            <div style="border-left:1px solid var(--border-color); padding-left:16px;">Fast index: <span style="color:var(--accent-purple); font-weight:800;">${step.fast}</span></div>
            <div style="border-left:1px solid var(--border-color); padding-left:16px;">Status: <span style="color:${step.matched ? 'var(--accent-emerald)' : 'var(--accent-cyan)'}; font-weight:800;">${step.matched ? 'LOOP DETECTED!' : 'TRACING LOOP...'}</span></div>
        `;
    } else if (state.simType === 'tree-dfs' || state.simType === 'tree-bfs') {
        const listName = state.simType === 'tree-dfs' ? 'Stack' : 'Queue';
        const listVal = state.simType === 'tree-dfs' ? step.stack : step.queue;
        varsHtml = `
            <div>Active Node: <span style="color:var(--accent-cyan); font-weight:800;">${step.currentNode}</span></div>
            <div style="border-left:1px solid var(--border-color); padding-left:16px;">Traversal ${listName}: <span style="color:#ffffff; font-weight:800;">[ ${listVal.join(', ')} ]</span></div>
        `;
    } else if (state.simType === 'graph-dfs' || state.simType === 'graph-bfs' || state.simType === 'dijkstra') {
        varsHtml = `
            <div>Active Vertex: <span style="color:var(--accent-cyan); font-weight:800;">${step.currentNode}</span></div>
            <div style="border-left:1px solid var(--border-color); padding-left:16px;">Target Category: <span style="color:#ffffff; font-weight:800;">Graph Operations</span></div>
        `;
    } else if (state.simType === 'heap') {
        varsHtml = `
            <div>Size: <span style="color:var(--accent-cyan); font-weight:800;">${step.array.length}</span></div>
            <div style="border-left:1px solid var(--border-color); padding-left:16px;">Heap Property: <span style="color:var(--accent-emerald); font-weight:800;">Min-Heapify</span></div>
        `;
    } else if (state.simType === 'union-find') {
        varsHtml = `
            <div>Sets Count: <span style="color:var(--accent-cyan); font-weight:800;">5</span></div>
            <div style="border-left:1px solid var(--border-color); padding-left:16px;">DSU Operation: <span style="color:var(--accent-purple); font-weight:800;">Equivalence Grouping</span></div>
        `;
    } else if (state.simType === 'trie') {
        varsHtml = `
            <div>Structure: <span style="color:var(--accent-cyan); font-weight:800;">Prefix Trie</span></div>
            <div style="border-left:1px solid var(--border-color); padding-left:16px;">Key Operations: <span style="color:var(--accent-emerald); font-weight:800;">Insertion / Search</span></div>
        `;
    } else if (state.simType === 'dp-visualizer') {
        varsHtml = `
            <div>Active Cell: <span style="color:var(--accent-cyan); font-weight:800;">${step.cell ? `dp[${step.cell.join(', ')}]` : 'None'}</span></div>
            <div style="border-left:1px solid var(--border-color); padding-left:16px;">DP Space: <span style="color:var(--accent-purple); font-weight:800;">O(N * M) Grid</span></div>
        `;
    } else if (state.simType === 'backtracking') {
        varsHtml = `
            <div>Decisions Path: <span style="color:var(--accent-cyan); font-weight:800;">${step.state}</span></div>
            <div style="border-left:1px solid var(--border-color); padding-left:16px;">State Space: <span style="color:var(--accent-emerald); font-weight:800;">Permutations</span></div>
        `;
    } else if (state.simType === 'lru-cache') {
        varsHtml = `
            <div>Capacity: <span style="color:var(--accent-cyan); font-weight:800;">${data.capacity}</span></div>
            <div style="border-left:1px solid var(--border-color); padding-left:16px;">Current Size: <span style="color:#ffffff; font-weight:800;">${Object.keys(step.cache).length}</span></div>
            <div style="border-left:1px solid var(--border-color); padding-left:16px;">Hit/Miss Status: <span style="color:${step.operation.includes('Get') ? 'var(--accent-emerald)' : 'var(--accent-cyan)'}; font-weight:800;">${step.operation}</span></div>
        `;
    }

    container.innerHTML = `
        <div class="simulator-view-container animated-fadeIn">
            
            <div class="pattern-header">
                <h1 class="pattern-title">Interactive Visual Simulator</h1>
                <p class="pattern-description">Trace patterns step-by-step to understand boundary variables, expanding windows, and logarithmic divisions visually.</p>
            </div>

            <!-- Simulator Layout -->
            <div class="sim-layout">
                
                <!-- Left Sidebar Picker -->
                <div class="sim-sidebar">
                    <div class="sim-category">
                        <span class="sim-category-title">Arrays</span>
                        <button class="sim-sidebar-btn ${state.simType === 'two-pointer' ? 'active' : ''}" onclick="selectSimType('two-pointer')">Two Pointer</button>
                        <button class="sim-sidebar-btn ${state.simType === 'sliding-window' ? 'active' : ''}" onclick="selectSimType('sliding-window')">Sliding Window</button>
                        <button class="sim-sidebar-btn ${state.simType === 'binary-search' ? 'active' : ''}" onclick="selectSimType('binary-search')">Binary Search</button>
                        <button class="sim-sidebar-btn ${state.simType === 'merge-intervals' ? 'active' : ''}" onclick="selectSimType('merge-intervals')">Merge Intervals</button>
                    </div>
                    <div class="sim-category">
                        <span class="sim-category-title">Linked List</span>
                        <button class="sim-sidebar-btn ${state.simType === 'fast-slow-pointer' ? 'active' : ''}" onclick="selectSimType('fast-slow-pointer')">Fast & Slow Pointer</button>
                    </div>
                    <div class="sim-category">
                        <span class="sim-category-title">Trees</span>
                        <button class="sim-sidebar-btn ${state.simType === 'tree-dfs' ? 'active' : ''}" onclick="selectSimType('tree-dfs')">Tree DFS</button>
                        <button class="sim-sidebar-btn ${state.simType === 'tree-bfs' ? 'active' : ''}" onclick="selectSimType('tree-bfs')">Tree BFS</button>
                    </div>
                    <div class="sim-category">
                        <span class="sim-category-title">Graphs</span>
                        <button class="sim-sidebar-btn ${state.simType === 'graph-dfs' ? 'active' : ''}" onclick="selectSimType('graph-dfs')">Graph DFS</button>
                        <button class="sim-sidebar-btn ${state.simType === 'graph-bfs' ? 'active' : ''}" onclick="selectSimType('graph-bfs')">Graph BFS</button>
                        <button class="sim-sidebar-btn ${state.simType === 'dijkstra' ? 'active' : ''}" onclick="selectSimType('dijkstra')">Dijkstra's Path</button>
                    </div>
                    <div class="sim-category">
                        <span class="sim-category-title">Advanced</span>
                        <button class="sim-sidebar-btn ${state.simType === 'heap' ? 'active' : ''}" onclick="selectSimType('heap')">Min-Heap</button>
                        <button class="sim-sidebar-btn ${state.simType === 'union-find' ? 'active' : ''}" onclick="selectSimType('union-find')">Union Find (DSU)</button>
                        <button class="sim-sidebar-btn ${state.simType === 'trie' ? 'active' : ''}" onclick="selectSimType('trie')">Trie Prefix Tree</button>
                        <button class="sim-sidebar-btn ${state.simType === 'dp-visualizer' ? 'active' : ''}" onclick="selectSimType('dp-visualizer')">DP Visualizer</button>
                        <button class="sim-sidebar-btn ${state.simType === 'backtracking' ? 'active' : ''}" onclick="selectSimType('backtracking')">Backtracking</button>
                        <button class="sim-sidebar-btn ${state.simType === 'lru-cache' ? 'active' : ''}" onclick="selectSimType('lru-cache')">LRU Cache</button>
                    </div>
                </div>

                <!-- Right Canvas Area -->
                <div style="display:flex; flex-direction:column; gap:24px; flex:1; min-width:0;">
                    
                    <!-- Controls Bar -->
                    <div class="simulator-controls" style="margin-top:0;">
                        <h2 style="font-size:16px; font-weight:800; color:#ffffff; margin:0;">${data.name}</h2>
                        <div class="sim-btn-group">
                            <button class="sim-btn" onclick="prevSimStep()"><i class="fas fa-step-backward"></i> Prev</button>
                            <button class="sim-btn" onclick="togglePlaySim()">
                                <i class="fas ${state.simIsPlaying ? 'fa-pause' : 'fa-play'}"></i> ${state.simIsPlaying ? 'Pause' : 'Play'}
                            </button>
                            <button class="sim-btn" onclick="nextSimStep()"><i class="fas fa-step-forward"></i> Next</button>
                            <button class="sim-btn" onclick="resetSim()"><i class="fas fa-undo"></i> Reset</button>
                        </div>
                    </div>

                    <!-- Visual Canvas -->
                    <div class="simulator-canvas" style="padding: 36px 20px;">
                        ${nodesHtml}

                        <!-- Info Variables Box -->
                        <div style="display:flex; gap:16px; background:rgba(255,255,255,0.01); border:1px solid var(--border-color); padding:12px 24px; border-radius:12px; font-size:13px; font-weight:600; flex-wrap:wrap; justify-content:center; width:100%; box-sizing:border-box;">
                            ${varsHtml}
                        </div>

                        <!-- Step Description Box -->
                        <div class="sim-status-box">
                            <div class="sim-step-title">${step.title}</div>
                            <p class="sim-step-desc" style="max-width:550px; margin: 0 auto; line-height:1.5;">${step.desc}</p>
                        </div>
                    </div>

                </div>

            </div>

        </div>
    `;
}

function selectSimType(type) {
    if (state.simInterval) {
        clearInterval(state.simInterval);
        state.simInterval = null;
    }
    state.simType = type;
    state.simStep = 0;
    state.simIsPlaying = false;
    renderView();
}

function prevSimStep() {
    if (state.simStep > 0) {
        state.simStep--;
        renderView();
    }
}

function nextSimStep() {
    const data = SIM_DATA[state.simType];
    if (state.simStep < data.steps.length - 1) {
        state.simStep++;
        renderView();
    } else {
        if (state.simIsPlaying) {
            togglePlaySim();
        }
    }
}

function resetSim() {
    if (state.simInterval) {
        clearInterval(state.simInterval);
        state.simInterval = null;
    }
    state.simStep = 0;
    state.simIsPlaying = false;
    renderView();
}

function togglePlaySim() {
    state.simIsPlaying = !state.simIsPlaying;
    
    if (state.simIsPlaying) {
        const data = SIM_DATA[state.simType];
        if (state.simStep >= data.steps.length - 1) {
            state.simStep = 0;
        }
        
        state.simInterval = setInterval(() => {
            nextSimStep();
        }, 2000);
    } else {
        if (state.simInterval) {
            clearInterval(state.simInterval);
            state.simInterval = null;
        }
    }
    
    renderView();
}

function renderInterviewView(container) {
    if (state.interviewActive) {
        const activeProb = state.interviewQuestions[state.interviewRound];
        if (!activeProb) {
            container.innerHTML = `<div>Error loading interview round problem.</div>`;
            return;
        }

        let roundListHtml = '';
        state.interviewQuestions.forEach((prob, idx) => {
            const isActive = idx === state.interviewRound ? 'active' : '';
            const statusLabel = state.interviewResults[idx] ? '🟢 Solved' : '⚪ Pending';
            roundListHtml += `
                <div class="wiki-menu-item ${isActive}" onclick="selectInterviewRound(${idx})" style="display:flex; justify-content:space-between; align-items:center;">
                    <span>Round ${idx + 1}: ${prob.title.length > 20 ? prob.title.substring(0, 18) + '...' : prob.title}</span>
                    <span style="font-size:11px;">${statusLabel}</span>
                </div>
            `;
        });

        container.innerHTML = `
            <div class="wiki-container animated-fadeIn" style="grid-template-columns: 280px 1fr; margin-top:0;">
                
                <!-- Sidebar of Rounds -->
                <div class="wiki-sidebar" style="justify-content: flex-start; gap: 16px;">
                    <div>
                        <div class="timer-box" style="width:100%; justify-content:center; box-sizing:border-box;">
                            <i class="fas fa-clock"></i> <span id="interview-countdown">${formatTime(state.interviewTimer)}</span>
                        </div>
                    </div>
                    <div style="font-size:11px; text-transform:uppercase; font-weight:700; color:var(--text-dark); letter-spacing:0.5px;">Interview Rounds</div>
                    <div style="display:flex; flex-direction:column; gap:6px;">
                        ${roundListHtml}
                    </div>
                    <button class="sim-btn" onclick="endMockInterview()" style="margin-top:auto; border-color:#ef4444; color:#ef4444; width:100%;">
                        <i class="fas fa-times-circle"></i> End & Submit
                    </button>
                </div>

                <!-- Problem Console -->
                <div class="wiki-content-panel">
                    <div class="company-header" style="justify-content:flex-start; gap:8px; align-items:center;">
                        <span class="difficulty-badge difficulty-${getProblemDifficulty(activeProb.title).toLowerCase()}">${getProblemDifficulty(activeProb.title)}</span>
                        <span class="company-stats-pill" style="background:rgba(255,255,255,0.05); color:#ffffff; padding:4px 8px;">Round ${state.interviewRound + 1}</span>
                    </div>

                    <h1 class="wiki-title">${activeProb.title}</h1>

                    <div>
                        <h4 class="wiki-section-lbl">Complexity Target</h4>
                        <div style="display:flex; gap:16px; margin-top:8px;">
                            <span style="font-size:13.5px; color:var(--text-muted);"><i class="fas fa-clock" style="color:var(--accent-cyan);"></i> Time: <code>${activeProb.timeComplexity}</code></span>
                            <span style="font-size:13.5px; color:var(--text-muted);"><i class="fas fa-memory" style="color:var(--accent-cyan);"></i> Space: <code>${activeProb.spaceComplexity}</code></span>
                        </div>
                    </div>

                    <div>
                        <h4 class="wiki-section-lbl">Problem Description & Constraints</h4>
                        <p class="wiki-text" style="margin-top:8px; line-height:1.6;">
                            ${PROBLEM_DETAILS[activeProb.title]?.statement || `Implement an optimal solution to resolve the coding patterns constraints for <strong>${activeProb.title}</strong>.`}
                        </p>
                    </div>

                    <!-- Sandbox Textarea for Recruiter Sandbox -->
                    <div>
                        <h4 class="wiki-section-lbl">Sandbox / Interviewer Notes</h4>
                        <textarea id="interview-notes" class="chat-input" style="width:100%; height:120px; border:1px solid var(--border-color); border-radius:12px; margin-top:8px; padding:12px; background:rgba(7, 9, 19, 0.4); resize:none; font-family:var(--font-mono); font-size:13px; line-height:1.5;" placeholder="Draft your approach, pseudo-code, or runtime complexity analysis here...">${state.notes[state.interviewRound]}</textarea>
                    </div>

                    <div style="display:flex; gap:12px; justify-content:space-between; flex-wrap:wrap; margin-top:12px;">
                        <div style="display:flex; gap:12px;">
                            <button class="sim-btn" onclick="openDetailedSolution(0, '${activeProb.title.replace(/'/g, "\\'")}')" style="border-color:var(--accent-cyan); color:var(--accent-cyan);">
                                <i class="fas fa-lightbulb"></i> View Hints / AI Support
                            </button>
                        </div>
                        <div style="display:flex; gap:12px;">
                            <button class="sim-btn" onclick="toggleInterviewResult(${state.interviewRound}, false)" style="border-color:#ef4444; color:${!state.interviewResults[state.interviewRound] ? '#ffffff' : '#ef4444'}; background:${!state.interviewResults[state.interviewRound] ? '#ef4444' : 'transparent'};">
                                <i class="fas fa-times"></i> Mark Failed
                            </button>
                            <button class="sim-btn" onclick="toggleInterviewResult(${state.interviewRound}, true)" style="border-color:var(--accent-emerald); color:${state.interviewResults[state.interviewRound] ? '#ffffff' : 'var(--accent-emerald)'}; background:${state.interviewResults[state.interviewRound] ? 'var(--accent-emerald)' : 'transparent'};">
                                <i class="fas fa-check"></i> Mark Solved
                            </button>
                        </div>
                    </div>
                </div>

            </div>
        `;
    } else {
        // Setup Screen
        container.innerHTML = `
            <div class="interview-setup-box animated-fadeIn">
                <div style="font-size:36px; color:var(--accent-cyan); align-self:center;"><i class="fas fa-user-clock"></i></div>
                <h2 style="font-size:22px; font-weight:800; color:#ffffff; text-align:center;">Start Coding Mock Interview</h2>
                <p style="font-size:13.5px; color:var(--text-muted); text-align:center; line-height:1.6;">
                    Test your memory and logic speed. We compile 3 randomized rounds matching your specifications under a 45-minute strict countdown timer.
                </p>

                <div style="display:flex; flex-direction:column; gap:8px;">
                    <label style="font-size:12px; font-weight:700; text-transform:uppercase; color:var(--text-dark); letter-spacing:0.5px;">Difficulty Tier</label>
                    <select id="interview-diff-select" class="filter-select" style="width:100%; padding:10px 14px; background:rgba(255,255,255,0.02); border:1px solid var(--border-color); border-radius:8px; color:#ffffff;">
                        <option value="All">All Difficulties</option>
                        <option value="Easy">Easy</option>
                        <option value="Medium">Medium</option>
                        <option value="Hard">Hard</option>
                    </select>
                </div>

                <div style="display:flex; flex-direction:column; gap:8px;">
                    <label style="font-size:12px; font-weight:700; text-transform:uppercase; color:var(--text-dark); letter-spacing:0.5px;">Company Curriculum</label>
                    <select id="interview-comp-select" class="filter-select" style="width:100%; padding:10px 14px; background:rgba(255,255,255,0.02); border:1px solid var(--border-color); border-radius:8px; color:#ffffff;">
                        <option value="General">General (All Companies)</option>
                        ${COMPANIES.map(c => `<option value="${c}">${c}</option>`).join('')}
                    </select>
                </div>

                <button class="sim-btn primary" onclick="startMockInterview()" style="width:100%; padding:12px 18px;">
                    <i class="fas fa-play"></i> Start 45-Min Interview Session
                </button>
            </div>
        `;
    }
}

function startMockInterview() {
    const diff = document.getElementById('interview-diff-select').value;
    const company = document.getElementById('interview-comp-select').value;

    state.interviewDifficulty = diff;
    state.interviewCompany = company;

    // Filter problems by difficulty
    let candidateProblems = [];
    dsaData.forEach(pattern => {
        pattern.problems.forEach(prob => {
            const pDiff = getProblemDifficulty(prob.title);
            const pComps = getProblemCompanies(prob.title).map(c => c.toLowerCase());
            
            let match = true;
            if (diff !== 'All' && pDiff !== diff) match = false;
            if (company !== 'General' && !pComps.includes(company.toLowerCase())) match = false;

            if (match) {
                candidateProblems.push(prob);
            }
        });
    });

    // If we have fewer than 3 matching problems, fall back to matching by difficulty only
    if (candidateProblems.length < 3) {
        candidateProblems = [];
        dsaData.forEach(pattern => {
            pattern.problems.forEach(prob => {
                const pDiff = getProblemDifficulty(prob.title);
                if (diff === 'All' || pDiff === diff) {
                    candidateProblems.push(prob);
                }
            });
        });
    }

    if (candidateProblems.length < 3) {
        candidateProblems = [];
        dsaData.forEach(pattern => {
            pattern.problems.forEach(prob => {
                candidateProblems.push(prob);
            });
        });
    }

    // Pick 3 random distinct problems
    const selected = [];
    const tempCandidates = [...candidateProblems];
    for (let i = 0; i < 3; i++) {
        if (tempCandidates.length === 0) break;
        const idx = Math.floor(Math.random() * tempCandidates.length);
        selected.push(tempCandidates.splice(idx, 1)[0]);
    }

    state.interviewQuestions = selected;
    state.interviewActive = true;
    state.interviewTimer = 45 * 60; // 45 minutes
    state.interviewRound = 0;
    state.interviewResults = [false, false, false];
    state.notes = ['', '', ''];

    // Start timer interval
    if (state.interviewTimerId) clearInterval(state.interviewTimerId);
    state.interviewTimerId = setInterval(() => {
        state.interviewTimer--;
        if (state.interviewTimer <= 0) {
            clearInterval(state.interviewTimerId);
            endMockInterview();
        } else {
            const timerEl = document.getElementById('interview-countdown');
            if (timerEl) {
                timerEl.textContent = formatTime(state.interviewTimer);
                if (state.interviewTimer < 300) {
                    timerEl.parentElement.classList.add('pulse');
                }
            }
        }
    }, 1000);

    renderView();
}

function formatTime(sec) {
    const m = Math.floor(sec / 60);
    const s = sec % 60;
    return `${m}:${s < 10 ? '0' : ''}${s}`;
}

function selectInterviewRound(idx) {
    const notesArea = document.getElementById('interview-notes');
    if (notesArea) {
        state.notes[state.interviewRound] = notesArea.value;
    }

    state.interviewRound = idx;
    renderView();
}

function toggleInterviewResult(idx, outcome) {
    state.interviewResults[idx] = outcome;
    renderView();
}

function endMockInterview() {
    if (state.interviewTimerId) {
        clearInterval(state.interviewTimerId);
        state.interviewTimerId = null;
    }
    state.interviewActive = false;
    
    const notesArea = document.getElementById('interview-notes');
    if (notesArea) {
        state.notes[state.interviewRound] = notesArea.value;
    }

    const viewContainer = document.getElementById('view-container');
    viewContainer.style.display = 'flex';

    const solvedCount = state.interviewResults.filter(Boolean).length;
    const scorePct = Math.round((solvedCount / 3) * 100);

    let summaryHtml = `
        <div class="interview-setup-box animated-fadeIn" style="max-width: 700px; text-align:center;">
            <div style="font-size: 48px; color: ${scorePct >= 66 ? 'var(--accent-emerald)' : scorePct >= 33 ? 'var(--accent-amber)' : '#ef4444'}; margin-bottom:12px;">
                <i class="fas ${scorePct >= 66 ? 'fa-medal' : 'fa-graduation-cap'}"></i>
            </div>
            <h2 style="font-size:24px; font-weight:800; color:#ffffff;">Interview Session Completed</h2>
            <p style="font-size:14px; color:var(--text-muted); margin-bottom:16px;">
                Difficulty: <strong>${state.interviewDifficulty}</strong> | Company: <strong>${state.interviewCompany}</strong>
            </p>

            <div style="background: rgba(255,255,255,0.02); border:1px solid var(--border-color); padding: 24px; border-radius:16px; margin-bottom:16px;">
                <div style="font-size:12px; text-transform:uppercase; color:var(--text-dark); font-weight:700; letter-spacing:0.5px;">Success Rate</div>
                <div style="font-size:36px; font-weight:800; color:#ffffff; margin: 8px 0;">${scorePct}%</div>
                <div style="font-size:13.5px; color:var(--text-muted);">${solvedCount} out of 3 problems completed successfully</div>
            </div>

            <div style="display:flex; flex-direction:column; gap:12px; text-align:left; margin-bottom:24px;">
                ${state.interviewQuestions.map((prob, idx) => `
                    <div style="display:flex; justify-content:space-between; align-items:center; background:rgba(0,0,0,0.1); border:1px solid var(--border-color); padding:12px 18px; border-radius:10px;">
                        <div>
                            <div style="font-size:14px; font-weight:700; color:#ffffff;">Round ${idx + 1}: ${prob.title}</div>
                            <div style="font-size:12px; color:var(--text-muted);">Complexity: Time ${prob.timeComplexity} | Space ${prob.spaceComplexity}</div>
                        </div>
                        <span style="font-weight:700; font-size:12px; text-transform:uppercase; padding: 4px 8px; border-radius:6px; background:${state.interviewResults[idx] ? 'rgba(16,185,129,0.1)' : 'rgba(239,68,68,0.1)'}; color:${state.interviewResults[idx] ? 'var(--accent-emerald)' : '#ef4444'};">
                            ${state.interviewResults[idx] ? 'Solved' : 'Failed/Skipped'}
                        </span>
                    </div>
                `).join('')}
            </div>

            <button class="sim-btn primary" onclick="state.currentTab = 'interview'; renderView();" style="align-self:center;">
                <i class="fas fa-redo"></i> Start New Interview
            </button>
        </div>
    `;

    viewContainer.innerHTML = summaryHtml;
}

function triggerMentorHint() {
    if (!state.currentProblem) return;
    const title = state.currentProblem.title;
    
    if (!state.hintCounts) {
        state.hintCounts = {};
    }
    if (state.hintCounts[title] === undefined) {
        state.hintCounts[title] = 0;
    }
    
    const count = state.hintCounts[title];
    const hints = MENTOR_HINTS[title] || [
        `Analyze the time complexity constraints. We want to aim for ${state.currentProblem.timeComplexity || 'O(N)'}.`,
        `The optimal approach is: ${state.currentProblem.approach || 'Linear Scan'}. Think about how to set up the boundary parameters.`,
        `Verify edge cases like empty arrays, single element inputs, or targets that do not exist.`
    ];
    
    if (count >= 3) {
        appendMentorMessage("bot", "I have given you all the hints for this problem! Try reviewing the solution code in the editor pane to see the full implementation.");
        return;
    }
    
    appendMentorMessage("bot", `<strong>💡 Hint ${count + 1}:</strong> ${hints[count]}`);
    state.hintCounts[title] = count + 1;
}

function appendMentorMessage(sender, text) {
    const chatLog = document.getElementById('mentor-chat-log');
    if (!chatLog) return;
    
    const msg = document.createElement('div');
    msg.className = `mentor-msg ${sender}`;
    msg.innerHTML = text;
    chatLog.appendChild(msg);
    chatLog.scrollTop = chatLog.scrollHeight;
}

function sendMentorMessage() {
    const input = document.getElementById('mentor-chat-input');
    if (!input || input.value.trim() === '') return;
    
    const userText = input.value.trim();
    appendMentorMessage("user", userText);
    input.value = '';
    
    // Simulate AI Mentor reply
    setTimeout(() => {
        let reply = "That's a good question! To optimize this solution, consider standard complexity thresholds. Can you explain your thoughts on how to avoid nested loops?";
        const lowerText = userText.toLowerCase();
        if (lowerText.includes("time") || lowerText.includes("slow") || lowerText.includes("fast")) {
            reply = "Time complexity here is " + (state.currentProblem ? state.currentProblem.timeComplexity : "O(N)") + ". We achieve this by pruning redundant branches or using pointers to reduce nested scans.";
        } else if (lowerText.includes("space") || lowerText.includes("memory")) {
            reply = "Space complexity is " + (state.currentProblem ? state.currentProblem.spaceComplexity : "O(1)") + ". We optimize this by reusing the input array or list pointers directly rather than creating auxiliary data structures.";
        } else if (lowerText.includes("java") || lowerText.includes("code")) {
            reply = "In Java, pay attention to data type ranges (like using `long` for potential overflows) and edge cases like checking if the array length is zero.";
        } else if (lowerText.includes("help") || lowerText.includes("how")) {
            reply = "Let's break it down: look at the template in the cheatsheet or wiki section. What part of the loop condition seems unclear?";
        }
        appendMentorMessage("bot", reply);
    }, 800);
}

function initMobileSidebar() {
    const toggleBtn = document.getElementById('sidebar-toggle');
    const sidebar = document.querySelector('aside.sidebar');
    const mainContent = document.querySelector('main.main-content');

    if (toggleBtn && sidebar) {
        toggleBtn.addEventListener('click', (e) => {
            e.stopPropagation();
            sidebar.classList.toggle('active');
        });

        if (mainContent) {
            mainContent.addEventListener('click', () => {
                if (window.innerWidth <= 768 && sidebar.classList.contains('active')) {
                    sidebar.classList.remove('active');
                }
            });
        }

        sidebar.addEventListener('click', (e) => {
            const clickedMenuItem = e.target.closest('.menu-item');
            if (clickedMenuItem && window.innerWidth <= 768) {
                sidebar.classList.remove('active');
            }
        });
    }
}

// Init App on Load
window.addEventListener('DOMContentLoaded', () => {
    initNavigation();
    initMobileSidebar();
    renderSidebar();
    renderView();
});
