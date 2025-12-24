# OrangeHRM Test Automation Framework

A comprehensive end-to-end test automation framework for OrangeHRM application built with **Selenium WebDriver**, **Java**, and **TestNG**.

---

## Table of Contents

- [Overview](#overview)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Test Reports](#test-reports)
- [Modules Covered](#modules-covered)
- [Design Patterns](#design-patterns)
- [CI/CD Integration](#cicd-integration)
- [Contributing](#contributing)
- [Author](#author)

---

## Overview

This project is a robust test automation framework designed to validate the functionality of the **OrangeHRM** Human Resource Management System. The framework covers all major modules including Admin, PIM, Recruitment, Leave, Time, Performance, Claim, Buzz, Dashboard, Directory, and Maintenance.

**Key Highlights:**
- 200+ automated test cases
- Page Object Model (POM) design pattern
- Fluent Interface for readable test scripts
- Allure reporting integration
- Cross-browser testing support
- CI/CD ready with GitHub Actions

---

## Tech Stack

| Technology | Purpose |
|------------|---------|
| Java 11+ | Programming Language |
| Selenium WebDriver 4.x | Browser Automation |
| TestNG 7.x | Test Framework |
| Maven | Build & Dependency Management |
| Allure | Test Reporting |
| WebDriverManager | Browser Driver Management |
| GitHub Actions | CI/CD Pipeline |

---

## Project Structure

```
orangeHRM/
├── src/
│   └── test/
│       └── java/
│           └── project_orangehrm/
│               ├── Base/
│               │   ├── BasePage.java
│               │   └── BaseTest.java
│               ├── Pages/
│               │   ├── CommonPage.java
│               │   ├── LoginPage.java
│               │   ├── DashboardPage.java
│               │   ├── AdminPage.java
│               │   ├── PIMPage.java
│               │   ├── RecruitmentPage.java
│               │   ├── LeavePage.java
│               │   ├── TimePage.java
│               │   ├── PerformancePage.java
│               │   ├── ClaimPage.java
│               │   ├── BuzzPage.java
│               │   ├── DirectoryPage.java
│               │   ├── MaintenancePage.java
│               │   └── MyInfoPage.java
│               ├── Test/
│               │   ├── LoginTest.java
│               │   ├── AdminJobTest.java
│               │   ├── AdminUserManagementTest.java
│               │   ├── AdminOrganizationTest.java
│               │   ├── PIM_Employee_Test.java
│               │   ├── PIM_EmployeeDetails_Test.java
│               │   ├── Recruitment_CandidateWorkflow_Test.java
│               │   ├── Leave_ApplyLeave_Test.java
│               │   ├── Time_Attendance_Test.java
│               │   ├── Performance_Configuration_Test.java
│               │   ├── Claim_Operations_Test.java
│               │   ├── Buzz_Newsfeed_Test.java
│               │   ├── Dashboard_Widgets_Test.java
│               │   ├── Directory_Test.java
│               │   ├── Maintenance_PurgeRecords_Test.java
│               │   └── ... (47 test files)
│               ├── TestCleaning/
│               │   ├── AdminTestCleaning.java
│               │   ├── PIMTestCleaning.java
│               │   ├── RecruitmentTestCleaning.java
│               │   ├── PerformanceTestCleaning.java
│               │   ├── ClaimTestCleaning.java
│               │   ├── TimeTestCleaning.java
│               │   └── BuzzTestCleaning.java
│               └── Utils/
│                   └── AllureAttachment.java
├── .github/
│   └── workflows/
│       └── maven.yml
├── target/
│   └── allure-results/
├── testng.xml
├── pom.xml
└── README.md
```

---

## Features

### Framework Features
- **Page Object Model (POM)** - Clean separation between test logic and page interactions
- **Fluent Interface** - Method chaining for readable and maintainable tests
- **Smart Wait Mechanism** - FluentWait with configurable timeout and polling
- **Dynamic Locators** - Flexible XPath patterns for dynamic elements
- **Safe Delete** - Handles cleanup gracefully even if data doesn't exist
- **Screenshot on Failure** - Automatic screenshot capture on test failures
- **Allure Reporting** - Detailed and interactive test reports

### Test Features
- **Data-Driven Testing** - Parameterized tests for multiple data sets
- **Test Dependencies** - Proper test execution order with priorities
- **Cleanup Tests** - Dedicated test cleaning module for data cleanup
- **Cross-Browser Support** - Chrome, Firefox, Edge support
- **Parallel Execution** - Configurable parallel test execution

---

## Prerequisites

Before running the tests, ensure you have the following installed:

- **Java JDK 11** or higher
- **Maven 3.6+**
- **Git**
- **Chrome/Firefox/Edge** browser

---

## Installation

1. **Clone the repository:**
```bash
git clone https://github.com/your-username/orangeHRM.git
cd orangeHRM
```

2. **Install dependencies:**
```bash
mvn clean install -DskipTests
```

3. **Verify installation:**
```bash
mvn -version
java -version
```

---

## Configuration

### Browser Configuration
Edit `BaseTest.java` to change the default browser:

```java
// Options: chrome, firefox, edge
String browser = System.getProperty("browser", "chrome");
```

### Test Environment
The framework is configured to run against:
```
URL: https://opensource-demo.orangehrmlive.com/
Username: Admin
Password: admin123
```

---

## Running Tests

### Run All Tests
```bash
mvn clean test
```

### Run Specific Test Suite
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

### Run Specific Test Class
```bash
mvn clean test -Dtest=LoginTest
```

### Run with Browser Selection
```bash
mvn clean test -Dbrowser=chrome
mvn clean test -Dbrowser=firefox
mvn clean test -Dbrowser=edge
```

### Run Tests in Parallel
```bash
mvn clean test -Dparallel=methods -DthreadCount=4
```

---

## Test Reports

### Generate Allure Report
```bash
# Run tests first
mvn clean test

# Generate and open report
mvn allure:serve
```

### View Report
After running `mvn allure:serve`, the report will automatically open in your browser at `http://localhost:port`

### Report Features
- Test execution summary
- Pass/Fail/Skip statistics
- Execution timeline
- Test case details with steps
- Screenshots on failure
- Environment information

---

## Modules Covered

### 1. Admin Module
| Sub-Module | Test Coverage |
|------------|---------------|
| User Management | Add, Edit, Delete, Search Users |
| Job | Job Titles, Pay Grades, Employment Status, Job Categories, Work Shifts |
| Organization | General Info, Locations, Structure |
| Qualifications | Skills, Education, Licenses, Languages, Memberships |
| Nationalities | Add, Edit, Delete Nationalities |
| Configuration | Email Subscriptions, Localization |

### 2. PIM Module
| Sub-Module | Test Coverage |
|------------|---------------|
| Employee List | Add, Edit, Delete, Search Employees |
| Add Employee | Create with Login Details |
| Employee Details | Personal, Contact, Emergency, Dependents, Immigration, Job, Salary, Qualifications |
| Configuration | Custom Fields, Reporting Methods, Termination Reasons |
| Reports | Employee Reports |

### 3. Recruitment Module
| Sub-Module | Test Coverage |
|------------|---------------|
| Vacancies | Create, Edit, Delete Vacancies |
| Candidates | Add, Search, Filter Candidates |
| Candidate Workflow | Shortlist, Interview, Offer, Hire, Reject |

### 4. Leave Module
| Sub-Module | Test Coverage |
|------------|---------------|
| Apply Leave | Submit Leave Requests |
| My Leave | View Leave History |
| Entitlements | Add, View Entitlements |
| Leave List | Admin Leave Management |
| Configuration | Leave Types, Work Week, Holidays |

### 5. Time Module
| Sub-Module | Test Coverage |
|------------|---------------|
| Timesheets | My Timesheets, Employee Timesheets |
| Attendance | Punch In/Out, My Records, Employee Records |
| Project Info | Customers, Projects |
| Reports | Project Reports, Attendance Reports |

### 6. Performance Module
| Sub-Module | Test Coverage |
|------------|---------------|
| Configure | KPIs, Trackers |
| Manage Reviews | Create, Activate Reviews |
| My Reviews | Self Evaluation |
| Employee Tracker | Track Employee Performance |

### 7. Claim Module
| Sub-Module | Test Coverage |
|------------|---------------|
| Submit Claim | Create, Edit Claims |
| My Claims | View Claim History |
| Employee Claims | Admin Claim Management |
| Configuration | Events, Expense Types |

### 8. Buzz Module
| Feature | Test Coverage |
|---------|---------------|
| Newsfeed | Create Posts, Like, Comment |
| Post Management | Edit, Delete Posts |

### 9. Dashboard Module
| Feature | Test Coverage |
|---------|---------------|
| Widgets | Quick Launch, Employees on Leave |
| User Actions | Logout, Profile |

### 10. Directory Module
| Feature | Test Coverage |
|---------|---------------|
| Search | Search by Name, Job Title, Location |
| View | Employee Directory Cards |

### 11. Maintenance Module
| Sub-Module | Test Coverage |
|------------|---------------|
| Purge Records | Employee Records, Candidate Records |
| Access Records | Download Employee Data |
| Authentication | Password Verification |

---

## Design Patterns

### Page Object Model (POM)
Each page in the application has a corresponding Page class:

```java
public class AdminPage extends CommonPage {
    
    public AdminPage navigateToSection(String section) {
        // Implementation
        return this;
    }
    
    public AdminPage clickToAdd() {
        add();
        return this;
    }
}
```

### Fluent Interface
Methods return `this` for chaining:

```java
adminPage
    .navigateToSection("Job", "Job Titles")
    .clickToAdd()
    .typeInDynamicField("Job Title", "QA Engineer")
    .clickSave()
    .verifySuccessMessage();
```

### Base Page Pattern
Common functionality in `BasePage.java`:
- Wait mechanisms
- Click methods
- Type methods
- Assertion methods

### Factory Pattern
Dynamic locator generation:

```java
public By getLocator(String pattern, String value) {
    return By.xpath(String.format(pattern, value));
}
```

---

## CI/CD Integration

### GitHub Actions
The project includes a GitHub Actions workflow (`.github/workflows/maven.yml`):

```yaml
name: OrangeHRM E2E Tests

on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]
  schedule:
    - cron: '0 6 * * *'

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - name: Set up JDK 11
        uses: actions/setup-java@v4
        with:
          java-version: '11'
          distribution: 'temurin'
      - name: Run Tests
        run: mvn clean test
      - name: Generate Allure Report
        run: mvn allure:report
      - name: Upload Allure Results
        uses: actions/upload-artifact@v4
        with:
          name: allure-results
          path: target/allure-results
```

### Pipeline Stages
1. **Build** - Compile and resolve dependencies
2. **Test** - Execute test suite
3. **Report** - Generate Allure report
4. **Artifact** - Upload test results

---

## Test Execution Order

The `testng.xml` is organized for proper test execution:

```xml
<suite name="OrangeHRM_E2E_Suite">
    <!-- Step 1: Admin Foundation -->
    <test name="Step1_Admin_Foundation">
        <classes>
            <class name="project_orangehrm.Test.LoginTest"/>
            <class name="project_orangehrm.Test.AdminJobTest"/>
            <!-- More admin tests -->
        </classes>
    </test>
    
    <!-- Step 2: PIM Foundation -->
    <test name="Step2_PIM_Foundation">
        <!-- PIM tests -->
    </test>
    
    <!-- Step 3-6: Other modules -->
    
    <!-- Step 7: Cleanup -->
    <test name="Step7_Cleanup">
        <classes>
            <class name="project_orangehrm.TestCleaning.AdminTestCleaning"/>
            <!-- More cleanup tests -->
        </classes>
    </test>
</suite>
```

---

## Key Methods

### Safe Delete (Handles Missing Data)
```java
public void safeDelete(By deleteButton, By confirmButton) {
    try {
        clickWhenReady(deleteButton);
        clickWhenReady(confirmButton);
    } catch (TimeoutException | NoSuchElementException e) {
        // Element not found - already deleted
    }
}
```

### Conditional Actions
```java
public void conditionalTypeAndClick(By field, String text, By button) {
    if (driver.findElements(field).size() > 0) {
        typeText(field, text);
        clickWhenReady(button);
    }
}
```

### Dynamic Dropdown Selection
```java
public void selectDropdownOption(String dropdownName, String option) {
    clickWhenReady(getLocator(DROPDOWN_PATTERN, dropdownName));
    clickWhenReady(getLocator(OPTION_PATTERN, option));
}
```

---

## Best Practices Implemented

1. **DRY Principle** - Reusable methods in BasePage and CommonPage
2. **Single Responsibility** - Each page class handles one page
3. **Explicit Waits** - FluentWait for reliable element interactions
4. **Descriptive Naming** - Clear method and variable names
5. **Test Independence** - Each test can run independently
6. **Data Cleanup** - Dedicated cleanup tests prevent data pollution
7. **Error Handling** - Graceful handling of missing elements
8. **Logging** - Console output for debugging

---

## Troubleshooting

### Common Issues

**Issue: Tests fail with timeout**
```
Solution: Increase wait timeout in BasePage.java
```

**Issue: Element not clickable**
```
Solution: Use clickJS() method for JavaScript click
```

**Issue: Stale element reference**
```
Solution: FluentWait is configured to handle this automatically
```

**Issue: Browser not launching**
```
Solution: Ensure WebDriverManager has internet access
```

---

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/new-test`)
3. Commit changes (`git commit -m 'Add new test'`)
4. Push to branch (`git push origin feature/new-test`)
5. Open a Pull Request

---

## Author

- GitHub: https://github.com/m1mohammed1
- LinkedIn: https://www.linkedin.com/in/mohammed-hesham-57a3533a1/

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## Acknowledgments

- OrangeHRM for providing the demo application
- Selenium community for excellent documentation
- TestNG team for the powerful test framework
- Allure for beautiful reporting

---

**Happy Testing!** 🚀
