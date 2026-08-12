# Fix Circular Dependency between :core:designsystem and :core:preferences

The project has a circular dependency because `:core:designsystem` depends on `:core:preferences` (for `LocalAppState`) and `:core:preferences` depends on `:core:designsystem` (for `AppTheme`).

## Proposed Changes

### [core:preferences](file:///home/name/Desktop/coroutines-flows/core/preferences)

#### [NEW] [AppTheme.kt](file:///home/name/Desktop/coroutines-flows/core/preferences/src/main/java/com/example/core/preferences/model/AppTheme.kt)
Move the `AppTheme` enum class here to break the dependency on `:core:designsystem`.

#### [MODIFY] [AppState.kt](file:///home/name/Desktop/coroutines-flows/core/preferences/src/main/java/com/example/core/preferences/AppState.kt)
Update `AppTheme` import.

#### [MODIFY] [PreferencesDataSource.kt](file:///home/name/Desktop/coroutines-flows/core/preferences/src/main/java/com/example/core/preferences/datasourcedatastore/PreferencesDataSource.kt)
Update `AppTheme` import.

#### [MODIFY] [build.gradle.kts](file:///home/name/Desktop/coroutines-flows/core/preferences/build.gradle.kts)
Remove `implementation(project(":core:designsystem"))`.

---

### [core:designsystem](file:///home/name/Desktop/coroutines-flows/core/designsystem)

#### [DELETE] [AppTheme.kt](file:///home/name/Desktop/coroutines-flows/core/designsystem/src/main/java/com/example/designsystem/theme/thememodel/AppTheme.kt)
Remove the old location of `AppTheme`.

#### [MODIFY] [Theme.kt](file:///home/name/Desktop/coroutines-flows/core/designsystem/src/main/java/com/example/designsystem/theme/Theme.kt)
Update `AppTheme` import.

#### [MODIFY] [ModalDrowerContent.kt](file:///home/name/Desktop/coroutines-flows/core/designsystem/src/main/java/com/example/designsystem/components/ModalDrowerContent.kt)
Update `LocalAppState` import (if needed, though it seems it's already using a package that might change).

---

### Other Modules

#### [MODIFY] [MainActivity.kt](file:///home/name/Desktop/coroutines-flows/app/src/main/java/com/example/coroutinesflows/MainActivity.kt)
Update `AppTheme` import.

#### [MODIFY] [Settings.kt](file:///home/name/Desktop/coroutines-flows/feature/user-settings/ui/src/main/java/com/example/feature/usersettings/ui/screen/Settings.kt)
Update `AppTheme` import.

## Verification Plan

### Automated Tests
- Run `./gradlew :app:assembleDebug` to ensure the project builds without circular dependencies.
