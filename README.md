# PBT Lab – Property-Based Testing
**Software Testing | Mugla Sitki Kocman University**

---

## Setup (do this first)

### Requirements
| Tool | Version |
|------|---------|
| Java JDK | 17 or higher |
| Maven | 3.8 or higher |
| IntelliJ IDEA | Any recent version (Community is free) |

### Steps
1. Clone this repository:
   ```bash
   git clone <your-repo-url>
   cd pbt-lab
   ```
2. Open the project in IntelliJ IDEA:
   `File → Open → select the pbt-lab folder`
3. Wait for IntelliJ to download dependencies (Maven sync).
4. Verify setup by running all tests:
   ```bash
   mvn test
   ```
   Task 1 tests should pass. Tasks 2–5 will show compile errors until you fill in the TODOs.

---

## Tasks

| Task | File | Time | Type |
|------|------|------|------|
| Task 1 | `Task1_WarmupTest.java` | 20 min | Read & annotate |
| Task 2 | `Task2_CountOccurrencesTest.java` | 35 min | Guided – fill TODOs |
| Task 3 | `Task3_BoundedStackTest.java` | 40 min | Guided + Open |
| Task 4 | `Task4_BuggyCalculatorTest.java` | 35 min | Find bugs |
| Task 5 | `Task5_FreeTaskTest.java` | 30 min | Free design |

All implementation classes are in `src/main/java/lab/`.  
All test classes are in `src/test/java/lab/`.  
**Do NOT modify the implementation classes.**

---

## Running Tests

**Run all tasks:**
```bash
mvn test
```

**Run a single task:**
```bash
mvn test -Dtest=Task2_CountOccurrencesTest
```

**Run from IntelliJ:**
Right-click on any test method or class → `Run`

---

## Submitting

Push your completed test files to your GitHub Classroom repository.  
GitHub Actions will run automatically and report your results.

```bash
git add src/test/java/lab/
git commit -m "Complete PBT lab tasks"
git push
```

Check the **Actions** tab on GitHub to see your test results.

---

## Tips
- If jqwik finds a counterexample, read the `Sample` section carefully — it shows the exact input that broke your property.
- Use `@Property(tries = 200)` to speed up slow tests during development.
- Use `Assume.that(condition)` to filter out inputs that don't satisfy your preconditions.
- The `Arbitraries` class has many useful methods — explore it!
