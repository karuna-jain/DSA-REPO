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

// State Manager
let state = {
    currentTab: 'dashboard', // 'dashboard' | 'roadmap' | 'cheatsheets' | 'decision-tree' | 'recruiter' | 'patterns'
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
    
    currentProblem: null
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
const DECISION_TREE = {
    start: {
        text: "What is the primary data structure of your input?",
        options: [
            { text: "Linear (Array, String, Linked List)", next: "linear" },
            { text: "Hierarchical (Tree, Graph)", next: "hierarchy" },
            { text: "Intervals / Ranges", next: "intervals" },
            { text: "Multi-dimensional Grid (Matrix)", next: "matrix" }
        ]
    },
    linear: {
        text: "Are you searching for contiguous subarrays or substrings?",
        options: [
            { text: "Yes", next: "contiguous" },
            { text: "No", next: "linear_non_contiguous" }
        ]
    },
    contiguous: {
        text: "Is the size of the target window/subarray dynamic based on a condition?",
        options: [
            { text: "Yes, expands/shrinks dynamic bounds", result: "Sliding Window Pattern" },
            { text: "No, target size is fixed / comparing bounds", result: "Two-Pointer Pattern" }
        ]
    },
    linear_non_contiguous: {
        text: "Is the input sequence sorted?",
        options: [
            { text: "Yes", next: "linear_sorted" },
            { text: "No", next: "linear_unsorted" }
        ]
    },
    linear_sorted: {
        text: "Do you need to search an element/boundary in logarithmic time O(log N)?",
        options: [
            { text: "Yes", result: "Modified Binary Search Pattern" },
            { text: "No", result: "Two-Pointer Pattern" }
        ]
    },
    linear_unsorted: {
        text: "Are the elements numbers within a specified range [1, N]?",
        options: [
            { text: "Yes", result: "Cyclic Sort Pattern" },
            { text: "No", next: "list_type" }
        ]
    },
    list_type: {
        text: "Is the input list a Linked List?",
        options: [
            { text: "Yes, requires pointer redirection", result: "In-place Reversal of a Linked List Pattern" },
            { text: "No, standard array/list traversal", result: "Fast & Slow Pointer Pattern" }
        ]
    },
    hierarchy: {
        text: "Do you need to traverse the nodes level-by-level (horizontally)?",
        options: [
            { text: "Yes", result: "Tree BFS Traversal Pattern" },
            { text: "No (Depth / Path-based search)", result: "Trees / Backtracking Pattern" }
        ]
    },
    intervals: {
        text: "Do you need to merge overlapping ranges or calculate intersections?",
        options: [
            { text: "Yes", result: "Merge Intervals Pattern" },
            { text: "No (Optimization / Alignment)", result: "Dynamic Programming (DP)" }
        ]
    },
    matrix: {
        text: "Do you need to search step-by-step or traverse in a spiral boundary?",
        options: [
            { text: "Yes", result: "Matrix Pattern" },
            { text: "No (Recursive Grid Search)", result: "Backtracking / DFS" }
        ]
    }
};

let currentTreeNode = 'start';

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
    // Hide Solution view, show dynamic content
    document.getElementById('code-viewer-container').style.display = 'none';
    const viewContainer = document.getElementById('view-container');
    viewContainer.style.display = 'flex';

    if (state.currentTab === 'dashboard') {
        renderDashboardView(viewContainer);
    } else if (state.currentTab === 'roadmap') {
        renderRoadmapView(viewContainer);
    } else if (state.currentTab === 'cheatsheets') {
        renderCheatsheetsView(viewContainer);
    } else if (state.currentTab === 'decision-tree') {
        renderDecisionTreeView(viewContainer);
    } else if (state.currentTab === 'recruiter') {
        renderRecruiterView(viewContainer);
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
    const featuredTitles = ["Two Sum (Sorted)", "Edit Distance", "N-Queens Puzzle", "Longest Palindrome in a string. [Longest palindromic Substring]"];
    
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
                    <span class="hero-title">Java Backend Developer | DSA Enthusiast</span>
                    <h1 class="hero-name">Karuna Jain</h1>
                    <p class="hero-desc">
                        Building robust problem-solving frameworks through structural, pattern-based algorithms. 
                        This platform represents active skill verification across core interview topics.
                    </p>
                    <div class="hero-social-links">
                        <a href="https://github.com/karuna-jain" target="_blank" class="social-btn primary"><i class="fab fa-github"></i> GitHub Profile</a>
                        <a href="https://linkedin.com/in/karuna-jain" target="_blank" class="social-btn"><i class="fab fa-linkedin"></i> LinkedIn</a>
                        <a href="#" class="social-btn" onclick="alert('Resume download triggered')"><i class="fas fa-file-download"></i> Download Resume</a>
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
            <div class="analytics-row">
                
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

        nodesHtml += `
            <div class="roadmap-node ${status}">
                <div class="roadmap-node-dot"></div>
                <div class="roadmap-node-card">
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
                    </div>
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
// SECTION 8: INTERACTIVE DECISION TREE VIEW
// --------------------------------------------------------------------
function renderDecisionTreeView(container) {
    const node = typeof currentTreeNode === 'object' ? currentTreeNode : DECISION_TREE[currentTreeNode];
    let contentHtml = '';

    if (node.result) {
        contentHtml = `
            <div class="tree-result-box animated-fadeIn">
                <div class="tree-result-title"><i class="fas fa-trophy"></i> Suggested Pattern</div>
                <div class="tree-result-val">${node.result}</div>
                <p style="font-size:13px; color:var(--text-muted); margin-top:8px; max-width:400px;">
                    This classification is determined by your input data characteristics and search parameters.
                </p>
            </div>
            <button class="tree-btn no" onclick="resetDecisionTree()" style="margin-top:16px;">
                <i class="fas fa-redo"></i> Start Over
            </button>
        `;
    } else {
        contentHtml = `
            <div class="tree-question animated-fadeIn">${node.text}</div>
            <div class="tree-options">
                ${node.options.map((opt, idx) => `
                    <button class="tree-btn ${idx === 0 ? 'yes' : 'no'}" onclick="advanceDecisionTree('${opt.next || ''}', '${opt.result || ''}')">
                        ${opt.text}
                    </button>
                `).join('')}
            </div>
            ${currentTreeNode !== 'start' ? `
                <button class="back-button" onclick="resetDecisionTree()" style="margin-top:24px; align-self:center;">
                    <i class="fas fa-redo"></i> Reset Quiz
                </button>
            ` : ''}
        `;
    }

    container.innerHTML = `
        <div class="decision-tree-view-container animated-fadeIn" style="display:flex; flex-direction:column; gap:24px;">
            <div class="pattern-header">
                <h1 class="pattern-title">Pattern Matcher Decision Tree</h1>
                <p class="pattern-description">
                    Unsure which coding pattern to use? Go through our classification workflow to identify the optimal pattern for your problem constraints.
                </p>
            </div>
            <div class="tree-container">
                <div style="font-size:36px; color:var(--accent-cyan); margin-bottom:8px;"><i class="fas fa-network-wired"></i></div>
                ${contentHtml}
            </div>
        </div>
    `;
}

function advanceDecisionTree(next, result) {
    if (result) {
        currentTreeNode = { result: result };
    } else {
        currentTreeNode = next;
    }
    renderView();
}

function resetDecisionTree() {
    currentTreeNode = 'start';
    renderView();
}

// --------------------------------------------------------------------
// SECTION 13: RECRUITER PORTAL VIEW
// --------------------------------------------------------------------
function renderRecruiterView(container) {
    const stats = updateGlobalStats();

    container.innerHTML = `
        <div class="recruiter-container animated-fadeIn">
            
            <!-- Bio Profile Card -->
            <div class="recruiter-profile-card">
                <img src="https://images.unsplash.com/photo-1607799279861-4dd421887fb3?w=200&auto=format&fit=crop&q=80" alt="Karuna Jain" class="recruiter-avatar">
                <div>
                    <h2 style="font-size:24px; font-weight:800; color:#ffffff;">Karuna Jain</h2>
                    <span style="font-size:13px; font-weight:600; color:var(--accent-cyan); text-transform:uppercase; letter-spacing:1px;">Java Backend Developer</span>
                </div>
                <p style="font-size:13.5px; color:var(--text-muted); line-height:1.6;">
                    Specialized in backend APIs, thread safety, microservices, and database optimization. Actively solving algorithms to verify optimal code complexity execution.
                </p>
                <div style="display:flex; flex-direction:column; gap:10px; width:100%;">
                    <a href="https://github.com/karuna-jain" target="_blank" class="social-btn primary" style="justify-content:center;"><i class="fab fa-github"></i> GitHub</a>
                    <a href="https://linkedin.com/in/karuna-jain" target="_blank" class="social-btn" style="justify-content:center;"><i class="fab fa-linkedin"></i> LinkedIn</a>
                    <a href="mailto:karunajain@example.com" class="social-btn" style="justify-content:center;"><i class="fas fa-envelope"></i> Email Developer</a>
                </div>
            </div>

            <!-- Developer Capability Grid -->
            <div class="recruiter-details-card">
                <div>
                    <h3 style="font-size:18px; font-weight:700; color:#ffffff; margin-bottom:8px;">Software Engineering Stats</h3>
                    <p style="font-size:13px; color:var(--text-muted); line-height:1.5;">
                        A summary of current algorithmic pattern coverage and technical specifications.
                    </p>
                </div>

                <div class="stats-cards-row" style="margin-bottom:0; grid-template-columns: repeat(auto-fit, minmax(130px, 1fr));">
                    <div class="dashboard-stat-card" style="padding:16px;">
                        <div class="stat-info">
                            <span class="stat-label" style="font-size:10px;">Total Solved</span>
                            <span class="stat-value" style="font-size:22px;">${stats.total}</span>
                        </div>
                    </div>
                    <div class="dashboard-stat-card" style="padding:16px;">
                        <div class="stat-info">
                            <span class="stat-label" style="font-size:10px;">Mastered</span>
                            <span class="stat-value" style="font-size:22px; color:var(--accent-emerald);">${stats.mastered}</span>
                        </div>
                    </div>
                    <div class="dashboard-stat-card" style="padding:16px;">
                        <div class="stat-info">
                            <span class="stat-label" style="font-size:10px;">Tech Stack</span>
                            <span class="stat-value" style="font-size:22px; color:var(--accent-cyan);">Java</span>
                        </div>
                    </div>
                </div>

                <div>
                    <h4 style="font-size:14px; font-weight:700; text-transform:uppercase; color:var(--text-dark); letter-spacing:0.5px; margin-bottom:12px;">Primary Skills</h4>
                    <div class="recruiter-skills-grid">
                        <div class="skill-pill"><i class="fab fa-java"></i> Java Core / OOP</div>
                        <div class="skill-pill"><i class="fas fa-server"></i> Spring Boot API</div>
                        <div class="skill-pill"><i class="fas fa-database"></i> MySQL / SQL</div>
                        <div class="skill-pill"><i class="fab fa-react"></i> React UI</div>
                        <div class="skill-pill"><i class="fas fa-brain"></i> DSA Optimization</div>
                        <div class="skill-pill"><i class="fas fa-project-diagram"></i> System Design</div>
                    </div>
                </div>

                <div>
                    <h4 style="font-size:14px; font-weight:700; text-transform:uppercase; color:var(--text-dark); letter-spacing:0.5px; margin-bottom:8px;">Portfolio Focus</h4>
                    <p style="font-size:13.5px; color:var(--text-muted); line-height:1.6;">
                        Each solution in the dashboard features preconfigured test execution setups, time and space complexity evaluations, code-tracing breakdowns, and recursive boundary conditions.
                    </p>
                </div>
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
    const tabs = ['dashboard', 'roadmap', 'cheatsheets', 'decision-tree', 'recruiter'];
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

// Init App on Load
window.addEventListener('DOMContentLoaded', () => {
    initNavigation();
    renderSidebar();
    renderView();
});
