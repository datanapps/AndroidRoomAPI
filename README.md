# AndroidRoomAPI ( androidx + KOTLIN + android ROOM Database)

[![See](https://img.youtube.com/vi/hOvG8Q208_I/0.jpg)](https://www.youtube.com/watch?v=hOvG8Q208_I)



** This sample created Android application which have latest library androidx, android room api and kotlin.**

### Here few steps to move go with androidx room api in kotline room api.

### 1. Implement all dependency in application gradle : 


    dependencies {
        implementation fileTree(dir: 'libs', include: ['*.jar'])
        implementation 'com.android.support:appcompat-v7:28.0.0'
        implementation 'com.android.support.constraint:constraint-layout:1.1.3'
        implementation 'com.android.support:design:28.0.0'

    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:design:28.0.0'

    // app compact
    implementation "androidx.appcompat:appcompat:1.1.0-alpha04"

    // constraint layout
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'

    // recycle view
    implementation 'androidx.recyclerview:recyclerview:1.1.0-alpha04'

    // ROOM API
    implementation "androidx.room:room-runtime:2.0.0"
    kapt "androidx.room:room-compiler:2.0.0"


    // Run programm in background
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.1.1"

    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.2'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
    }
    
    .......
    
    apply plugin: 'kotlin-android-extensions'
    apply plugin: 'kotlin-android'
    apply plugin: "kotlin-kapt"
    
    
  ###Create an entity means a table (here i did for book)
  
      package datanapps.roomapi.roomdatabase

    import androidx.room.Entity
    import androidx.room.PrimaryKey

    @Entity
    class Book {
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0
        var bookTitle: String? = null
        var authorName: String? = null
        var publishedYear: Int = 0
    }
    
    ###Create an DAO class means a medium to access table record (here i did for BookDAO)
    
    package datanapps.roomapi.roomdatabase


    import androidx.lifecycle.LiveData
    import androidx.lifecycle.MutableLiveData
    import androidx.room.Dao
    import androidx.room.Delete
    import androidx.room.Insert
    import androidx.room.Query
    import androidx.room.Update

    @Dao
    interface DaoBookAccess {

    @Insert
    fun insertTask(note: Book): Long?


    /* @Query("SELECT * FROM Book ORDER BY created_at desc")
    LiveData<List<Book>> fetchAllTasks();*/

    @Query("SELECT * FROM Book")
    fun fetchAllTasks(): LiveData<List<Book>>


    @Query("SELECT * FROM Book WHERE id =:taskId")
    fun getTask(taskId: Int): LiveData<Book>


    @Update
    fun updateTask(note: Book)


    @Delete
    fun deleteTask(note: Book)
    }
    
   ###Finally create a table for database means create a table for application
   
       package datanapps.roomapi.roomdatabase

    import androidx.room.Database
    import androidx.room.RoomDatabase

    @Database(entities = [Book::class], version = 1, exportSchema = false)
    abstract class AppDB : RoomDatabase() {
        abstract fun daoBookAccess(): DaoBookAccess

    }
    
    
    
### In Application we perform CRUD Operation

Delete : 

    val noteRepository = BookRepository(applicationContext)
        noteRepository.deleteBook(book)
           
 Insert : 
 
    val noteRepository = BookRepository(applicationContext)
        var book = Book()
            book.bookTitle = "History-books-for-Kids"
            book.authorName = "unknown"
            book.publishedYear = 2000
            noteRepository.insertTask(book)
    
 Fetch All Record : 
 
     val noteRepository = BookRepository(applicationContext)
            noteRepository.getAllBookList.observe(this, androidx.lifecycle.Observer {
                booksAdapter.updateBookList(it)
            })
