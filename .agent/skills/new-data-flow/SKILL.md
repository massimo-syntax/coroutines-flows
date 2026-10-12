---
name: new-data-flow
description: Workflow for adding a new end-to-end feature flow in this project, covering data layer (mock server in .remote.server, Endpoints.kt, RemoteDatabase.kt, interceptor, Retrofit service, repository impl), domain layer (models, repository interface), presentation layer (ViewModel & Composable screen based on sample blueprint), navigation (NavKey, NavEntry, Navigation.kt), and feature list registration (NavigateableItems.kt).
---

# New Data Flow Implementation Skill

This skill provides step-by-step instructions for adding a new feature or data flow into this project (`coroutines-flows`). Follow this structured architecture checklist whenever adding a new endpoint and corresponding screen.

---

## Architectural Overview & Data Flow Checklist

When creating a new data flow in this project, changes span across the following modules in order:

```
[ :data ] Server Mock (server/HttpsWwwServerCom, Endpoints, RemoteDatabase) -> Interceptor -> ApiService -> RepositoryImpl
    │
[ :domain ] Model & Repository Interface
    │
[ :feature:coroutines:api ] NavKey
    │
[ :presentation ] ViewModel & Screen (Blueprint: sample/MyViewmodel.kt & sample/MyScreen.kt)
    │
[ :feature:coroutines:impl ] NavEntry
    │
[ :core:navigation ] Navigation.kt (Verify route registered)
    │
[ :feature:featurelist:ui ] NavigateableItems.kt (Add FeatureItem)
```

---

## Step-by-Step Implementation Guide

### 1. Domain Layer (`:domain`)
First, define the core domain model and repository contract.

* **Domain Model**: `domain/src/main/java/com/example/domain/model/YourFeatureModel.kt`
  ```kotlin
  package com.example.domain.model

  data class YourFeatureModel(
      val id: String,
      val title: String,
      val description: String
  )
  ```

* **Repository Interface**: `domain/src/main/java/com/example/domain/repository/CoroutinesRepository.kt`
  Add the new suspended function contract:
  ```kotlin
  interface CoroutinesRepository {
      // Existing methods...
      suspend fun getYourFeatureData(): List<YourFeatureModel>
  }
  ```

---

### 2. Data Layer (`:data`)
Configure the mock network endpoint and implement the repository.

* **Endpoint Constants**: `data/src/main/java/com/example/data/remote/server/Endpoints.kt`
  - Add your endpoint route path constant:
    ```kotlin
    const val YOUR_FEATURE_ENDPOINT = "your_feature_endpoint"
    ```

* **Mock Database Payload**: `data/src/main/java/com/example/data/remote/server/RemoteDatabase.kt`
  - Add mock payload data list if needed:
    ```kotlin
    val yourFeatureMockList: List<YourFeatureModel> = listOf(...)
    ```

* **Mock Server Routing**: `data/src/main/java/com/example/data/remote/server/HttpsWwwServerCom.kt`
  - Handle path in `routeResponse(endpoint: String)`:
    ```kotlin
    YOUR_FEATURE_ENDPOINT -> {
        val gson = Gson()
        return gson.toJson(yourFeatureMockList)
    }
    ```

* **Mock Interceptor**: `data/src/main/java/com/example/data/remote/MockInterceptor.kt`
  - **Crucial**: Add route path matching inside `when(path)` to assign `response = server.routeResponse(path)` and set status `code = 200` (otherwise OkHttp defaults to 404):
    ```kotlin
    YOUR_FEATURE_ENDPOINT -> {
        response = server.routeResponse(path)
        code = 200
    }
    ```

* **Retrofit Service**: `data/src/main/java/com/example/data/remote/MockApiService.kt`
  - Add Retrofit `@GET` endpoint using constant from `Endpoints.kt`:
    ```kotlin
    @GET(YOUR_FEATURE_ENDPOINT)
    suspend fun getYourFeatureData(): List<YourFeatureModel>
    ```

* **Repository Implementation**: `data/src/main/java/com/example/data/repository/CoroutinesRepositoryImpl.kt`
  - Implement function with `secureNetworkRequest`:
    ```kotlin
    override suspend fun getYourFeatureData(): List<YourFeatureModel> =
        secureNetworkRequest { apiService.getYourFeatureData() }
    ```

---

### 3. Navigation Key (`:feature:coroutines:api`)
Define the Navigation 3 `NavKey` for your screen destination.

* **File**: `feature/coroutines/api/src/main/java/com/example/feature/coroutines/api/CoroutinesNavKeys.kt`
  ```kotlin
  @Serializable
  data object YourFeatureNavKey : NavKey
  ```

---

### 4. Presentation Layer (`:presentation`)
Create the ViewModel and Composable Screen under `presentation/src/main/java/com/example/presentation/coroutines/screen/yourfeature/`.
> **Blueprint Reference**: Use `:presentation` `.sample` (`MyViewmodel.kt` and `MyScreen.kt`) as a pattern template.

* **ViewModel**: `YourFeatureViewModel.kt`
  ```kotlin
  package com.example.presentation.coroutines.screen.yourfeature

  import androidx.lifecycle.ViewModel
  import androidx.lifecycle.viewModelScope
  import com.example.coroutinesflows.presentation.flows.uistate.MyUIState
  import com.example.domain.model.YourFeatureModel
  import com.example.domain.repository.CoroutinesRepository
  import dagger.hilt.android.lifecycle.HiltViewModel
  import jakarta.inject.Inject
  import kotlinx.coroutines.flow.MutableStateFlow
  import kotlinx.coroutines.flow.asStateFlow
  import kotlinx.coroutines.launch

  @HiltViewModel
  class YourFeatureViewModel @Inject constructor(
      private val repository: CoroutinesRepository
  ) : ViewModel() {

      private val _uiState = MutableStateFlow<MyUIState<List<YourFeatureModel>>>(MyUIState.Loading)
      val uiState = _uiState.asStateFlow()

      fun fetchData() {
          viewModelScope.launch {
              try {
                  _uiState.value = MyUIState.Success(repository.getYourFeatureData())
              } catch (e: Exception) {
                  _uiState.value = MyUIState.Error(e.message ?: "Unknown Error")
              }
          }
      }
  }
  ```

* **Screen Composable**: `YourFeatureScreen.kt`
  ```kotlin
  package com.example.presentation.coroutines.screen.yourfeature

  import androidx.compose.foundation.layout.Column
  import androidx.compose.foundation.layout.statusBarsPadding
  import androidx.compose.material3.Button
  import androidx.compose.material3.Text
  import androidx.compose.runtime.Composable
  import androidx.compose.runtime.getValue
  import androidx.compose.ui.Modifier
  import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
  import androidx.lifecycle.compose.collectAsStateWithLifecycle
  import com.example.coroutinesflows.presentation.flows.uistate.MyUIState

  @Composable
  fun YourFeatureScreen(
      onBackPressed: () -> Unit = {},
      viewModel: YourFeatureViewModel = hiltViewModel()
  ) {
      val state by viewModel.uiState.collectAsStateWithLifecycle()

      Column(Modifier.statusBarsPadding()) {
          when (state) {
              is MyUIState.Loading -> Text("Loading...")
              is MyUIState.Error -> Text("Error: ${(state as MyUIState.Error).error}")
              is MyUIState.Success -> {
                  val items = (state as MyUIState.Success).data
                  items.forEach { Text(it.title) }
              }
          }

          Button(onClick = { viewModel.fetchData() }) {
              Text("Fetch Data")
          }
      }
  }
  ```

---

### 5. Navigation Entry (`:feature:coroutines:impl`) & Core Navigation (`:core:navigation`)

* **Nav Entry**: `feature/coroutines/impl/src/main/java/com/example/feature/coroutines/impl/CoroutinesNavEntries.kt`
  Register your destination inside `CoroutinesRoutes`:
  ```kotlin
  entry<YourFeatureNavKey> {
      YourFeatureScreen(
          onBackPressed = { backStack.removeLastOrNull() }
      )
  }
  ```

* **Core Navigation**: `core/navigation/src/main/java/com/example/navigation/Navigation.kt`
  Verify `CoroutinesRoutes(backStack)` is present inside `entryProvider`.

---

### 6. Feature List UI Item (`:feature:featurelist:ui`)

* **File**: `feature/featurelist/ui/src/main/java/com/example/featurelist/ui/NavigateableItems.kt`
  Add a new `FeatureItem` entry to `navigableItems`:
  ```kotlin
  FeatureItem(
      type = SelectedCategory.Coroutines,
      title = "Your Feature Title",
      subtitle = "Optional Subtitle",
      description = "Your Feature Description",
      navKey = YourFeatureNavKey
  )
  ```

---

## Verification Steps
1. Run `./gradlew assembleDebug` or build project.
2. Launch app and check feature entry in the home list screen (`NavigateableItems.kt`).
3. Tap item to verify navigation to `YourFeatureScreen`.
4. Click button to verify mock server data flow (`HttpsWwwServerCom` -> `MockInterceptor` -> `MockApiService` -> `CoroutinesRepositoryImpl` -> `ViewModel` -> `Screen`).
