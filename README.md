# jpmorgan-interview
Technical test for the JPMorgan - Senior Andorid Engineer post

The application consist on two screens:
- First screen: List of albums
- Second screen: Detail of a selected album

## Architecture
I use MVVM architecture:

### Views:
The app has only one activity (MainActivity) and I use the Navigation component to handle the transitions between fragments.
I also use DataBinding to populate the content of the layouts.

### View Models:
Each View has its own View Model with LiveData objects so the views can observe changes on the data.
In order to fetch the data and manipulate it so it can be served to the views, I wrap the buisness logic inside UseCase classes.

### Model
#### Domain
There are two UseCases that represents the buisness logic of the applilcation:
- **GetAlbumsUseCase**: Fetch the list of albums from the repository layer. It takes into account the Internet connection in order to choose the correct data provider
- **GetSingleAlbumUseCase**: Fetch one single album from the repository layer. I have decided to only use the local repository because the JSON returned by the REST API is the same for list and detail. If it was the case, I would have added more logic to the use case, choosing the data repository depending on the network connection.
The use cases are being executed asyncrhonously using the UseCaseHandler class and a ThreadPoolExecutor.

#### Repository
There are two repositories, one to use when there is network connection and one when the device is offline:
- **RemoteDataRepository**: Fetch the data from a REST API using the Retrofit library. The queries are syncrhonous because the remote repository is only accessed from the use cases.
- **LocalDataRepository**: Fetch the data from a local SQLite Data Base. I decided to use ROOM for the simplicity and the popularity.

#### Data
There is only one data representation: Album.

## Test
I tried to create every component as uncoupled as I could, so testing is easier. I don't have much experience testing so pretty much verything I've done is the result of investigating in order to complete this task.
I don't have experience with dagger so I have done my own dependency injection with a Singleton class that handles all the component's instantiation.


## Improvements
- I could have use Dagger or any other depdency injection (I've heard great things about [Koin](https://github.com/InsertKoinIO/koin))
- I could have added some images to the albums using any library like Picasso but the REST API does not include any link.
- I have decided not to save the sate of the AlbumDetailFragment (the album id). I wanted to use [ AbstractSavedStateVMFactory](https://developer.android.com/topic/libraries/architecture/viewmodel-savedstate) but I didn't have time to do a proper research.
- It could be nice if the RecyclerView on the album list could change the LayoutManager (Linear to Grid) with a button. I didn't have time to do it but it will only required a new LiveData object on the view model and new ViewHolders on the adapter.
- The last thing I would have love to try is the new [ViewPager2](https://developer.android.com/jetpack/androidx/releases/viewpager2) to be able to navigate through the albums once you are on the detail view.

## Binaries
Download the [apk](https://www.dropbox.com/s/gx2qr9qi0f8klf5/AlbumsJPM.apk?dl=0) from my Dropbox

