# Property-Based Testing Lab
**SE 3004 Software Testing | Mugla Sitki Kocman University**

---

## Student Information

| | Student 1 | Student 2 |
|---|---|---|
| **Name** | | |
| **Student ID** | | |

> Fill in your names and IDs, then commit this file with your first push.

---

## Setup

### Requirements

| Tool | Version | Download |
|------|---------|----------|
| Java JDK | 17 or higher | [adoptium.net](https://adoptium.net) |
| Maven | 3.8 or higher | [maven.apache.org](https://maven.apache.org) |
| IntelliJ IDEA | Any recent version | [jetbrains.com/idea](https://www.jetbrains.com/idea/download) (Community Edition is free) |

### Steps

**1. Clone the repository:**
```bash
git clone https://gitlab.com/<your-project-url>
cd pbt-lab
```

**2. Open in IntelliJ IDEA:**
`File → Open → select the pbt-lab folder`

Wait for Maven to sync (downloads dependencies automatically — ~30 MB, one time only).

**3. Verify setup:**
```bash
mvn test -Dtest=Task1_WarmupTest
```
All 4 Task 1 tests should pass. If they do not, ask your instructor.

---

## Project Structure

```
pbt-lab/
  src/
    main/java/lab/
      StringUtils.java        <- Task 1 & 2  — DO NOT MODIFY
      BoundedStack.java       <- Task 3       — DO NOT MODIFY
      BuggyCalculator.java    <- Task 4       — DO NOT MODIFY
      FreeTask.java           <- Task 5       — DO NOT MODIFY
    test/java/lab/
      Task1_WarmupTest.java   <- your work
      Task2_CountOccurrencesTest.java
      Task3_BoundedStackTest.java
      Task4_BuggyCalculatorTest.java
      Task5_FreeTaskTest.java
  .gitlab-ci.yml              <- CI pipeline (do not modify)
  pom.xml                     <- Maven config (do not modify)
```

> **Only edit files inside `src/test/java/lab/`.**

---

## Tasks

| Task | File | Time | Type | Points |
|------|------|------|------|--------|
| Task 1 | `Task1_WarmupTest.java` | 20 min | Read & annotate | 2 pt |
| Task 2 | `Task2_CountOccurrencesTest.java` | 35 min | Guided — fill TODOs | 5 pt |
| Task 3 | `Task3_BoundedStackTest.java` | 40 min | Guided + Open | 5 pt |
| Task 4 | `Task4_BuggyCalculatorTest.java` | 35 min | Find the bugs | 5 pt |
| Task 5 | `Task5_FreeTaskTest.java` | 30 min | Free design | 3 pt |
| **Total** | | **3 hours** | | **20 pt** |

---

## Running Tests

**Run a single task:**
```bash
mvn test -Dtest=Task2_CountOccurrencesTest
```

**Run all tasks:**
```bash
mvn test
```

**Run from IntelliJ:**
Right-click any test class or method → Run

---

## CI/CD Pipeline

Every push triggers the GitLab CI pipeline automatically.

Check your results: **GitLab → your project → CI/CD → Pipelines**

| Job | Expected result |
|-----|----------------|
| compile | green |
| task1_warmup | green |
| task2_count_occurrences | green |
| task3_bounded_stack | green |
| task4_bug_detection | orange (allowed failure — bugs are intentional!) |
| task5_free_task | green |
| final_report | summary of all tasks |

> Task 4 showing orange is **expected and correct** — it means your properties successfully detected bugs in BuggyCalculator.

---

## Submission

### Step 1 — Push to GitLab (before the deadline)

```bash
git add .
git commit -m "Final submission"
git push
```

After pushing, go to **CI/CD → Pipelines** and take a screenshot of the results page.

### Step 2 — Submit to DYS

1. Clean your project:
   ```bash
   mvn clean
   ```
2. Create a ZIP of the entire `pbt-lab` folder.
3. Prepare a PDF report containing:
   - Screenshot of your GitLab pipeline results
   - **Task 4 bug report:** for each bug — which method, the counterexample jqwik reported, and an explanation of the bug
   - **Task 5 reflection:** which option you chose and why, your 3+ properties, and your `@Provide` / Combinators approach
4. Log in to **dys.mu.edu.tr**
5. Go to **SE 3004 Software Testing → Lab 03 Submission**
6. Upload your ZIP file and PDF report

> **Warning:** GitLab records the exact timestamp of every push. Pushing after the deadline counts as a late submission.

---

## Tips

- If jqwik finds a counterexample, read the `Sample` section carefully — it shows the exact input that broke your property.
- Use `@Property(tries = 200)` to speed up slow tests during development.
- Use `Assume.that(condition)` to filter inputs that do not satisfy preconditions.
- Explore the `Arbitraries` class — it has many useful methods beyond what the lecture covered.
- Switch driver/navigator roles after each task.
