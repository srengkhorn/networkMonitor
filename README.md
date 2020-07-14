# NetworkMonitor

[![](https://jitpack.io/v/srengkhorn/networkMonitor.svg)](https://jitpack.io/#srengkhorn/networkMonitor)

## Installation
### Step 1. Add the JitPack repository to your build file. 
- Add it in your root build.gradle at the end of repositories:

```bash
allprojects {
  repositories {
		...
    maven { url 'https://jitpack.io' }
  }
 }
```
### Step 2. Add the dependency

```bash
dependencies {
  implementation 'com.github.srengkhorn:networkMonitor:latest_version'
}
```

## Usage
### 1. Declare networkMonitor Instance globally

```kotlin
class MainActivity : AppCompatActivity() {

  lateinit var networkMonitor: NetworkMonitor
  
  .....
}
```
### 2. Initialize 

```kotlin
........

override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
	
	networkMonitor = NetworkMonitor(this)
	...
}

.....
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)
