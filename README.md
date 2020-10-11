# Food & Contacts app

Test project for <hidden>

## Major technologies

- Language: Kotlin
- Architecture: MVVM
- Android architecture components: ViewModel, LiveData, Room, coroutines
- Dependency injection: Dagger2
- Network: Retrofit, RxJava
- Testing: Espresso

### How it Works

- Fetches recipe list from the public api, stores locally (Room), combines with contact list (fetched in parallel), presents as a list sorted by name.
- Contact selection will try to make a call.
- Recipe selection will make another api request and display recipe details.

#### To be improved

- State change management
- Testing 
- Storing contacts locally (?)
- Animations and UI
