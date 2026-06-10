# Upgrade Plan: UniversalSearchEngine (20260608145748)

- **Generated**: 2026-06-08 14:57
- **HEAD Branch**: N/A
- **HEAD Commit ID**: N/A

## Available Tools

**JDKs**
- JDK 21.0.8: C:\Users\tbunn\.jdk\jdk-21.0.8\bin (required by steps 1, 3, 4)

**Build Tools**
- Maven: **<TO_BE_INSTALLED>** (required by steps 1, 3, 4, 5)
- Maven Wrapper: none detected

## Guidelines

> Note: You can add any specific guidelines or constraints for the upgrade process here if needed, bullet points are preferred.

## Options

- Working branch: appmod/java-upgrade-20260608145748
- Run tests before and after the upgrade: true

## Upgrade Goals

- Upgrade Java runtime and compile target to Java 21

## Technology Stack

| Technology/Dependency | Current | Min Compatible | Why Incompatible |
| --------------------- | ------- | -------------- | ---------------- |
| Java | 8 (bytecode major version 52) | 21 | User requested latest LTS Java upgrade |
| Maven | not installed locally | 3.9.0 | Required to build and verify Java 21 upgrade |
| maven-compiler-plugin | not configured | 3.11.0 | Recommended for Java 21 compilation support |
| pdfbox | 3.0.2 | 3.0.2 | Compatible with Java 21 |
| poi-ooxml | 5.2.5 | 5.2.5 | Compatible with Java 21 |

## Derived Upgrades

- Configure Maven to use Java 21 for compilation via `maven.compiler.release`.
- Add or pin `maven-compiler-plugin` to `3.11.0` to ensure compatibility with Java 21.
- Install Maven 3.9+ on the system because no local Maven installation or wrapper was detected.

## Impact Analysis

### Dependency Changes

| File | Dependency | Current | Action | Target | Reason |
|------|------------|---------|--------|--------|--------|
| pom.xml | maven-compiler-plugin | absent | add | 3.11.0 | Required for Java 21 compilation support |
| pom.xml | maven.compiler.release | absent | add | 21 | Set compile target to Java 21 |

### Source Code Changes

No source code changes required for this upgrade. Existing Java syntax is compatible with Java 21.

### Configuration Changes

No application configuration files require changes for this runtime-only upgrade.

### CI/CD Changes

No CI/CD or wrapper files were detected in the repository. Maven installation must be provided externally.

### Risks & Warnings

- **No Maven installation detected**: Build and test verification cannot be executed until Maven 3.9+ is installed or a Maven wrapper is added.
- **No git repository detected**: Changes will not be version-controlled in this workspace.
- **No tests present**: Full validation will be based on compilation only; verify runtime behavior manually after upgrade if possible.
- **Baseline JDK not available**: Current project JDK 8 is not installed, so pre-upgrade baseline validation will be skipped.

## Upgrade Steps

- Step 1: Setup Environment
  - **Rationale**: Ensure the system has a JDK 21 installation and a Maven build tool so the upgrade can be verified.
  - **Changes to Make**: Install Maven 3.9+ if missing, and confirm Java 21 is available.
  - **Verification**: `mvn -version` or `.mvnw -version` (when wrapper exists)

- Step 2: Setup Baseline (skipped if base JDK unavailable)
  - **Rationale**: Capture current build/test status before the upgrade.
  - **Changes to Make**: None if baseline cannot be executed due to missing JDK 8.
  - **Verification**: `mvn clean compile test-compile -q && mvn clean test -q`

- Step 3: Upgrade Maven build configuration to Java 21
  - **Rationale**: Set the compile target to the latest LTS and ensure the compiler plugin is compatible with Java 21.
  - **Changes to Make**: Update `pom.xml` with `maven.compiler.release` set to `21` and configure `maven-compiler-plugin` version `3.11.0`.
  - **Verification**: `mvn clean test-compile -q`

- Step 4: CVE Validation & Fix
  - **Rationale**: Scan direct dependencies for known vulnerabilities and fix any reported CVEs.
  - **Changes to Make**: Run `mvn dependency:list -DexcludeTransitive=true`, scan direct dependencies, and upgrade patched versions if needed.
  - **Verification**: `mvn clean test-compile -q` and re-scan with `#appmod-validate-cves-for-java`

- Step 5: Final Validation
  - **Rationale**: Confirm the upgraded project compiles cleanly and the test suite passes under Java 21.
  - **Changes to Make**: Resolve any compile or test issues from the upgrade.
  - **Verification**: `mvn clean test -q`
