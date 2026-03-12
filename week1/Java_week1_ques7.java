Problem 7: Autocomplete System for Search Engine
Scenario: Build a Google-like autocomplete that suggests queries as users type, based on 10
million previous search queries and their popularity.
Problem Statement: Create an autocomplete system that:
● Stores search queries with frequency counts
● Returns top 10 suggestions for any prefix in <50ms
● Updates frequencies based on new searches
● Handles typos and suggests corrections
● Optimizes for memory (10M queries × avg 30 characters)
Concepts Covered:
● Hash table for query frequency storage
● String hashing techniques
● Performance benchmarking (prefix search)
● Space complexity optimization
Hints:
// HashMap<query, frequency> for global stats
// Trie + HashMap hybrid for prefix matching
// Cache popular prefix results
// Use min-heap for top K results
```

**Use Cases:**
- Google search autocomplete
- Amazon product search suggestions
- IDE code completion
**Sample Input/Output:**
```
search("jav") →
1. " tutorial" (1,234,567 searches)
2. "script" (987,654 searches)
3. " download" (456,789 searches)
...
updateFrequency(" 21 features") → Frequency: 1 → 2 → 3 (trending)
