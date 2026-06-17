const fs = require('fs');
const path = require('path');

const repoDir = path.resolve(__dirname, '..');
const readmePath = path.join(repoDir, 'README.md');

function parseMarkdownLink(text) {
    if (!text) return { text: '', url: '' };
    const match = text.match(/\[([^\]]+)\]\(([^)]+)\)/);
    if (match) {
        return { text: match[1], url: match[2] };
    }
    return { text: text, url: '' };
}

function cleanMath(text) {
    if (!text) return '';
    return text.replace(/\$/g, '').trim();
}

function run() {
    if (!fs.existsSync(readmePath)) {
        console.error('README.md not found at', readmePath);
        process.exit(1);
    }

    const readmeContent = fs.readFileSync(readmePath, 'utf8');
    const lines = readmeContent.split(/\r?\n/);

    const patterns = [];
    let currentPattern = null;
    let inTable = false;
    let tableHeaders = [];

    for (let i = 0; i < lines.length; i++) {
        const line = lines[i].trim();

        // Detect Pattern headers (e.g., "### 1. Two-Pointer...") or other ### headings
        if (line.startsWith('###')) {
            const headerText = line.replace(/^###\s+/, '').trim();
            
            // We only care about patterns listed in the catalog, which usually start with a number or are DP/Trees/etc.
            if (headerText.includes('Pattern') || headerText.includes('Sorting & Searching') || headerText.includes('Dynamic Programming')) {
                currentPattern = {
                    name: headerText,
                    description: '',
                    problems: []
                };
                patterns.push(currentPattern);
                inTable = false;
                tableHeaders = [];
                
                // Try to find description in the next line
                if (i + 1 < lines.length && !lines[i + 1].trim().startsWith('|') && lines[i + 1].trim() !== '') {
                    currentPattern.description = lines[i + 1].trim();
                }
            } else {
                currentPattern = null;
            }
            continue;
        }

        if (currentPattern && line.startsWith('|')) {
            const parts = line.split('|').map(p => p.trim()).filter((_, idx, arr) => idx > 0 && idx < arr.length - 1);
            
            if (parts.length === 0) continue;

            // Check if it's separator row like |---|---| or |:---:|:---:|
            if (parts.every(p => /^:?-+:?$/.test(p) || p === '')) {
                inTable = true;
                continue;
            }

            // If we are not in table yet, this is the header row
            if (!inTable) {
                tableHeaders = parts.map(h => h.toLowerCase());
                continue;
            }

            // It's a data row!
            const rowData = {};
            tableHeaders.forEach((header, index) => {
                rowData[header] = parts[index] || '';
            });

            // Map standard keys depending on the headers present
            let problemTitle = '';
            let problemUrl = '';
            let codeFile = '';
            let relativePath = '';
            let timeComp = '';
            let spaceComp = '';
            let approachText = '';

            // 1. Title & URL
            const problemKey = tableHeaders.find(h => h.includes('problem') || h.includes('algorithm'));
            if (problemKey) {
                const parsed = parseMarkdownLink(rowData[problemKey]);
                problemTitle = parsed.text;
                problemUrl = parsed.url;
            }

            // 2. Source Link
            const sourceKey = tableHeaders.find(h => h.includes('source'));
            if (sourceKey) {
                const parsed = parseMarkdownLink(rowData[sourceKey]);
                problemUrl = parsed.url || problemUrl; // fallback if problem title had no link
            }

            // 3. Code Link
            const codeKey = tableHeaders.find(h => h.includes('code') || h.includes('solution'));
            if (codeKey) {
                const parsed = parseMarkdownLink(rowData[codeKey]);
                codeFile = parsed.text;
                relativePath = parsed.url;
            }

            // 4. Time Complexity
            const timeKey = tableHeaders.find(h => h.includes('time'));
            if (timeKey) {
                timeComp = cleanMath(rowData[timeKey]);
            }

            // 5. Space Complexity
            const spaceKey = tableHeaders.find(h => h.includes('space'));
            if (spaceKey) {
                spaceComp = cleanMath(rowData[spaceKey]);
            }

            // 6. Approach
            const approachKey = tableHeaders.find(h => h.includes('approach') || h.includes('key'));
            if (approachKey) {
                approachText = rowData[approachKey];
            }

            // Read Java Code
            let javaCode = '';
            if (relativePath) {
                // Decode URI if it has percentage symbols
                const decodedPath = decodeURIComponent(relativePath);
                const fullFilePath = path.join(repoDir, decodedPath);
                if (fs.existsSync(fullFilePath)) {
                    javaCode = fs.readFileSync(fullFilePath, 'utf8');
                } else {
                    console.warn(`Warning: File not found: ${fullFilePath}`);
                }
            }

            currentPattern.problems.push({
                title: problemTitle,
                sourceUrl: problemUrl,
                fileName: codeFile,
                filePath: relativePath,
                timeComplexity: timeComp || 'O(N)',
                spaceComplexity: spaceComp || 'O(1)',
                approach: approachText || 'Optimized representation',
                code: javaCode
            });
        } else if (line === '') {
            // Empty line
        }
    }

    // Write database to data.js
    const outputFilePath = path.join(__dirname, 'data.js');
    const dbContent = `// Auto-generated DSA Portfolio Database
const dsaData = ${JSON.stringify(patterns, null, 2)};
if (typeof module !== 'undefined') {
    module.exports = dsaData;
}
`;

    fs.writeFileSync(outputFilePath, dbContent, 'utf8');
    console.log(`Successfully compiled ${patterns.reduce((acc, p) => acc + p.problems.length, 0)} problems from README.md into ${outputFilePath}`);
}

run();
