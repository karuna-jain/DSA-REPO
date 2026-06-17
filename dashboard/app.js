// App controller for DSA Portfolio Dashboard

// Heuristics for problem difficulties based on problem names
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

function getDifficulty(title) {
    const lowerTitle = title.toLowerCase();
    if (EASY_PROBLEMS.some(p => lowerTitle.includes(p))) {
        return 'Easy';
    }
    if (HARD_PROBLEMS.some(p => lowerTitle.includes(p))) {
        return 'Hard';
    }
    return 'Medium';
}

// State Management
let currentState = {
    selectedPatternIndex: -1, // -1 means Home/Dashboard
    searchQuery: '',
    difficultyFilter: 'all',
    currentViewedProblem: null,
    history: [] // stack to track views
};

// DOM Elements
const sidebarMenu = document.getElementById('sidebar-menu');
const searchInput = document.getElementById('search-input');
const viewContainer = document.getElementById('view-container');
const codeViewerContainer = document.getElementById('code-viewer-container');

// Stats Counters
const totalCountEl = document.getElementById('stat-total-count');
const easyCountEl = document.getElementById('stat-easy-count');
const mediumCountEl = document.getElementById('stat-medium-count');
const hardCountEl = document.getElementById('stat-hard-count');

// Initialize the app
function init() {
    computeAndRenderStats();
    renderSidebar();
    showHome();
    setupEventListeners();
}

function computeAndRenderStats() {
    let total = 0;
    let easy = 0;
    let medium = 0;
    let hard = 0;

    dsaData.forEach(pattern => {
        pattern.problems.forEach(prob => {
            total++;
            const diff = getDifficulty(prob.title);
            if (diff === 'Easy') easy++;
            else if (diff === 'Medium') medium++;
            else if (diff === 'Hard') hard++;
        });
    });

    totalCountEl.textContent = total;
    easyCountEl.textContent = easy;
    mediumCountEl.textContent = medium;
    hardCountEl.textContent = hard;
}

function renderSidebar() {
    sidebarMenu.innerHTML = '';
    
    // Add Home Item
    const homeItem = document.createElement('div');
    homeItem.className = `menu-item ${currentState.selectedPatternIndex === -1 ? 'active' : ''}`;
    homeItem.innerHTML = `
        <span class="menu-item-text"><i class="fas fa-home" style="margin-right: 8px;"></i> Dashboard Home</span>
        <span class="menu-item-badge" style="background: var(--accent-purple); color: white;">Stats</span>
    `;
    homeItem.addEventListener('click', () => {
        currentState.selectedPatternIndex = -1;
        currentState.currentViewedProblem = null;
        renderSidebar();
        showHome();
    });
    sidebarMenu.appendChild(homeItem);

    // Add Pattern Items
    dsaData.forEach((pattern, index) => {
        const item = document.createElement('div');
        item.className = `menu-item ${currentState.selectedPatternIndex === index ? 'active' : ''}`;
        
        // Shorten visual pattern names to be compact
        let displayName = pattern.name.replace(/^\d+\.\s+/, ''); // remove number prefix
        
        item.innerHTML = `
            <span class="menu-item-text" title="${pattern.name}">${displayName}</span>
            <span class="menu-item-badge">${pattern.problems.length}</span>
        `;
        
        item.addEventListener('click', () => {
            currentState.selectedPatternIndex = index;
            currentState.currentViewedProblem = null;
            renderSidebar();
            showPattern(index);
        });
        
        sidebarMenu.appendChild(item);
    });
}

function setupEventListeners() {
    searchInput.addEventListener('input', (e) => {
        currentState.searchQuery = e.target.value.trim().toLowerCase();
        currentState.currentViewedProblem = null;
        if (currentState.searchQuery !== '') {
            currentState.selectedPatternIndex = -2; // search view state
            renderSidebar();
            showSearchResults();
        } else {
            currentState.selectedPatternIndex = -1;
            renderSidebar();
            showHome();
        }
    });
}

function showHome() {
    codeViewerContainer.style.display = 'none';
    viewContainer.style.display = 'flex';
    
    // Create categories overview
    let categoriesHtml = '';
    dsaData.forEach((pattern, idx) => {
        // Calculate counts
        let easy = 0, med = 0, hrd = 0;
        pattern.problems.forEach(p => {
            const diff = getDifficulty(p.title);
            if (diff === 'Easy') easy++;
            else if (diff === 'Medium') med++;
            else hrd++;
        });

        categoriesHtml += `
            <div class="stat-card" onclick="selectPattern(${idx})">
                <div class="stat-card-title">${pattern.name}</div>
                <div class="stat-card-value">${pattern.problems.length} <span style="font-size:14px; font-weight:400; color:var(--text-muted)">problems</span></div>
                <div style="margin-top: 10px; display:flex; gap: 8px; font-size:11px;">
                    <span style="color:var(--accent-emerald)">● ${easy}</span>
                    <span style="color:#f59e0b">● ${med}</span>
                    <span style="color:#ef4444">● ${hrd}</span>
                </div>
                <div class="stat-card-accent">
                    <i class="fas fa-project-diagram"></i>
                </div>
            </div>
        `;
    });

    // Pick 3 random or top-interesting problems to showcase as "Featured"
    const featuredProblems = [];
    if (dsaData.length > 5) {
        featuredProblems.push(dsaData[0].problems[0]); // Two sum
        featuredProblems.push(dsaData[4].problems[1]); // Edit distance
        featuredProblems.push(dsaData[12].problems[7]); // Longest palindrome
    }

    let featuredHtml = '';
    featuredProblems.forEach(prob => {
        if (!prob) return;
        const diff = getDifficulty(prob.title);
        const diffClass = `difficulty-${diff.toLowerCase()}`;
        featuredHtml += `
            <div class="problem-card" onclick="viewProblemByName('${prob.title.replace(/'/g, "\\'")}')">
                <div class="problem-card-header">
                    <span class="problem-title">${prob.title}</span>
                    <span class="difficulty-badge ${diffClass}">${diff}</span>
                </div>
                <div class="complexity-info">
                    <span class="complexity-item"><i class="fas fa-clock"></i> <code>${prob.timeComplexity}</code></span>
                    <span class="complexity-item"><i class="fas fa-memory"></i> <code>${prob.spaceComplexity}</code></span>
                </div>
                <div class="problem-approach">${prob.approach}</div>
                <div class="problem-card-footer">
                    <span class="solution-link">View Solution <i class="fas fa-arrow-right"></i></span>
                    <span class="source-badge"><i class="fas fa-external-link-alt"></i> code</span>
                </div>
            </div>
        `;
    });

    viewContainer.innerHTML = `
        <div class="welcome-container animated-fadeIn">
            <div class="hero-banner">
                <div class="hero-banner-title">Data Structures & Algorithms</div>
                <div class="hero-banner-desc">
                    Welcome to the interactive portfolio dashboard. Explore optimal solutions to 
                    ${totalCountEl.textContent} core algorithmic problems grouped by engineering patterns, all implemented in Java.
                </div>
                <div class="hero-stats">
                    <div class="hero-stat-item">
                        <span class="hero-stat-value">${dsaData.length}</span>
                        <span class="hero-stat-label">Patterns</span>
                    </div>
                    <div class="hero-stat-item" style="border-left: 1px solid rgba(255,255,255,0.1); padding-left: 24px;">
                        <span class="hero-stat-value">${totalCountEl.textContent}</span>
                        <span class="hero-stat-label">Solutions</span>
                    </div>
                    <div class="hero-stat-item" style="border-left: 1px solid rgba(255,255,255,0.1); padding-left: 24px;">
                        <span class="hero-stat-value">Java</span>
                        <span class="hero-stat-label">Language</span>
                    </div>
                </div>
            </div>

            <div class="recent-activities">
                <div class="recent-title"><i class="fas fa-star"></i> Featured Solutions</div>
                <div class="problems-grid">${featuredHtml}</div>
            </div>

            <div class="recent-activities" style="margin-top: 16px;">
                <div class="recent-title"><i class="fas fa-th-large"></i> Explore Algorithmic Patterns</div>
                <div class="dashboard-stats-grid">${categoriesHtml}</div>
            </div>
        </div>
    `;
}

function selectPattern(index) {
    currentState.selectedPatternIndex = index;
    renderSidebar();
    showPattern(index);
}

function showPattern(index) {
    codeViewerContainer.style.display = 'none';
    viewContainer.style.display = 'flex';

    const pattern = dsaData[index];
    if (!pattern) return;

    let problemsHtml = '';
    pattern.problems.forEach(prob => {
        const diff = getDifficulty(prob.title);
        const diffClass = `difficulty-${diff.toLowerCase()}`;
        
        problemsHtml += `
            <div class="problem-card animate-card" onclick="viewProblem(${index}, '${prob.title.replace(/'/g, "\\'")}')">
                <div class="problem-card-header">
                    <span class="problem-title">${prob.title}</span>
                    <span class="difficulty-badge ${diffClass}">${diff}</span>
                </div>
                <div class="complexity-info">
                    <span class="complexity-item"><i class="fas fa-clock"></i> <code>${prob.timeComplexity}</code></span>
                    <span class="complexity-item"><i class="fas fa-memory"></i> <code>${prob.spaceComplexity}</code></span>
                </div>
                <div class="problem-approach">${prob.approach}</div>
                <div class="problem-card-footer">
                    <span class="solution-link">View Solution <i class="fas fa-arrow-right"></i></span>
                    <span class="source-badge"><i class="fas fa-code"></i> ${prob.fileName}</span>
                </div>
            </div>
        `;
    });

    if (pattern.problems.length === 0) {
        problemsHtml = `
            <div class="empty-state">
                <div class="empty-state-icon"><i class="fas fa-folder-open"></i></div>
                <div class="empty-state-title">No Problems Found</div>
                <div class="empty-state-desc">This pattern section currently has no solutions listed.</div>
            </div>
        `;
    }

    viewContainer.innerHTML = `
        <div class="pattern-view-container animated-fadeIn" style="display:flex; flex-direction:column; gap:24px;">
            <div class="pattern-header">
                <div class="pattern-title">${pattern.name}</div>
                <div class="pattern-description">${pattern.description || 'Optimized algorithmic pattern solver.'}</div>
            </div>
            <div class="problems-grid">${problemsHtml}</div>
        </div>
    `;
}

function showSearchResults() {
    codeViewerContainer.style.display = 'none';
    viewContainer.style.display = 'flex';

    let matchingHtml = '';
    let matchCount = 0;

    dsaData.forEach((pattern, pIdx) => {
        pattern.problems.forEach(prob => {
            const matchTitle = prob.title.toLowerCase().includes(currentState.searchQuery);
            const matchCode = prob.code && prob.code.toLowerCase().includes(currentState.searchQuery);
            const matchApproach = prob.approach.toLowerCase().includes(currentState.searchQuery);
            const matchFile = prob.fileName.toLowerCase().includes(currentState.searchQuery);
            const matchPattern = pattern.name.toLowerCase().includes(currentState.searchQuery);

            if (matchTitle || matchCode || matchApproach || matchFile || matchPattern) {
                matchCount++;
                const diff = getDifficulty(prob.title);
                const diffClass = `difficulty-${diff.toLowerCase()}`;
                
                matchingHtml += `
                    <div class="problem-card" onclick="viewProblem(${pIdx}, '${prob.title.replace(/'/g, "\\'")}')">
                        <div class="problem-card-header">
                            <span class="problem-title">${prob.title}</span>
                            <span class="difficulty-badge ${diffClass}">${diff}</span>
                        </div>
                        <div style="font-size:12px; color:var(--accent-purple); font-weight:600; text-transform:uppercase;">
                            ${pattern.name.replace(/^\d+\.\s+/, '')}
                        </div>
                        <div class="complexity-info">
                            <span class="complexity-item"><i class="fas fa-clock"></i> <code>${prob.timeComplexity}</code></span>
                            <span class="complexity-item"><i class="fas fa-memory"></i> <code>${prob.spaceComplexity}</code></span>
                        </div>
                        <div class="problem-approach">${prob.approach}</div>
                        <div class="problem-card-footer">
                            <span class="solution-link">View Solution <i class="fas fa-arrow-right"></i></span>
                            <span class="source-badge"><i class="fas fa-code"></i> ${prob.fileName}</span>
                        </div>
                    </div>
                `;
            }
        });
    });

    if (matchCount === 0) {
        matchingHtml = `
            <div class="empty-state">
                <div class="empty-state-icon"><i class="fas fa-search"></i></div>
                <div class="empty-state-title">No Search Results</div>
                <div class="empty-state-desc">We couldn't find any match for "${currentState.searchQuery}". Try other keywords.</div>
            </div>
        `;
    }

    viewContainer.innerHTML = `
        <div class="search-view-container animated-fadeIn" style="display:flex; flex-direction:column; gap:24px;">
            <div class="pattern-header">
                <div class="pattern-title">Search Results</div>
                <div class="pattern-description">Showing ${matchCount} matches found for "${currentState.searchQuery}"</div>
            </div>
            <div class="problems-grid">${matchingHtml}</div>
        </div>
    `;
}

function viewProblem(patternIdx, problemTitle) {
    const pattern = dsaData[patternIdx];
    if (!pattern) return;
    
    const problem = pattern.problems.find(p => p.title === problemTitle);
    if (!problem) return;

    currentState.currentViewedProblem = problem;
    
    // Hide regular view, show code viewer
    viewContainer.style.display = 'none';
    codeViewerContainer.style.display = 'grid';

    // Populate Details Panel
    const diff = getDifficulty(problem.title);
    document.getElementById('code-details-content').innerHTML = `
        <button class="back-button" onclick="closeCodeViewer()"><i class="fas fa-arrow-left"></i> Back to list</button>
        
        <div style="display:flex; flex-direction:column; gap:8px; margin-top:10px;">
            <span class="difficulty-badge difficulty-${diff.toLowerCase()}" style="align-self: flex-start;">${diff}</span>
            <h1 class="code-title">${problem.title}</h1>
            <span style="font-size:13px; color:var(--text-dark); font-weight:600; text-transform:uppercase;">
                Pattern: ${pattern.name}
            </span>
        </div>

        <div class="code-meta-group">
            <div class="meta-row">
                <span class="meta-label">Time Complexity</span>
                <span class="meta-val"><code>${problem.timeComplexity}</code></span>
            </div>
            <div class="meta-row">
                <span class="meta-label">Space Complexity</span>
                <span class="meta-val"><code>${problem.spaceComplexity}</code></span>
            </div>
            <div class="meta-row">
                <span class="meta-label">Source File</span>
                <span class="meta-val" style="font-family:var(--font-mono); font-size:13px;">${problem.fileName}</span>
            </div>
        </div>

        <div class="approach-box">
            <div class="approach-title"><i class="fas fa-lightbulb"></i> Core Logic Approach</div>
            <div class="approach-content">${problem.approach}</div>
        </div>

        ${problem.sourceUrl ? `
            <a href="${problem.sourceUrl}" target="_blank" class="source-button">
                <i class="fab fa-chrome"></i> View Problem on LeetCode / Source <i class="fas fa-external-link-alt" style="font-size:10px; margin-left:auto;"></i>
            </a>
        ` : ''}

        <div style="margin-top:auto; padding-top:20px; border-top:1px solid var(--border-color); font-size:12px; color:var(--text-dark); display:flex; flex-direction:column; gap:6px;">
            <div><strong>Execution Command:</strong></div>
            <code style="font-family:var(--font-mono); background:rgba(255,255,255,0.03); padding:8px; border-radius:6px; border:1px solid var(--border-color); color:var(--text-muted); font-size:11px;">
                java -cp "${pattern.name.includes('/') ? pattern.name : (pattern.filePath ? pattern.filePath.split('/')[0] : 'String')}" ${problem.fileName.replace('.java', '')}
            </code>
        </div>
    `;

    // Populate Code Panel
    document.getElementById('editor-filename').textContent = problem.fileName;
    
    const codePre = document.getElementById('editor-code');
    // Escape code tags
    codePre.textContent = problem.code || '// Solution code not found or empty.';
    
    // Highlight Code
    Prism.highlightElement(codePre);
}

function viewProblemByName(name) {
    for (let i = 0; i < dsaData.length; i++) {
        const p = dsaData[i];
        const prob = p.problems.find(pr => pr.title === name);
        if (prob) {
            viewProblem(i, name);
            break;
        }
    }
}

function closeCodeViewer() {
    codeViewerContainer.style.display = 'none';
    viewContainer.style.display = 'flex';
    currentState.currentViewedProblem = null;
    
    // Go back to whatever state we had
    if (currentState.selectedPatternIndex === -1) {
        showHome();
    } else if (currentState.selectedPatternIndex === -2) {
        showSearchResults();
    } else {
        showPattern(currentState.selectedPatternIndex);
    }
}

function copyCode() {
    if (!currentState.currentViewedProblem || !currentState.currentViewedProblem.code) return;
    
    const copyBtn = document.getElementById('editor-copy-btn');
    navigator.clipboard.writeText(currentState.currentViewedProblem.code)
        .then(() => {
            copyBtn.classList.add('copy-success');
            copyBtn.innerHTML = '<i class="fas fa-check"></i> Copied!';
            setTimeout(() => {
                copyBtn.classList.remove('copy-success');
                copyBtn.innerHTML = '<i class="far fa-copy"></i> Copy Code';
            }, 2000);
        })
        .catch(err => {
            console.error('Failed to copy: ', err);
        });
}

// Start application
window.addEventListener('DOMContentLoaded', init);
