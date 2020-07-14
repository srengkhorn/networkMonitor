# NetworkMonitor

[![](https://jitpack.io/v/srengkhorn/networkMonitor.svg)](https://jitpack.io/#srengkhorn/networkMonitor)

# Screenshot
<img src="https://techkh.com/wp-content/uploads/2020/07/demo_01.png" width="25%"> <img src="https://techkh.com/wp-content/uploads/2020/07/demo_02.png" width="25%"> <img src="https://media0.giphy.com/media/KZSSxGymfQgQWPEAu6/giphy.gif" width="25%">

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
- STEP 1: Declare
- STEP 2: Initialize instance and its listener
- STEP 3 & STEP 4: Implement methods
- STEP 5: Display & custom warning dialog
- STEP 6: Register networkMonitor
- STEP 7: Unregister networkMonitor
```kotlin
class MainActivity : AppCompatActivity(), NetworkMonitor.ConnectionListener {

    // STEP 1
    lateinit var networkMonitor: NetworkMonitor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

	 // STEP 2
         networkMonitor = NetworkMonitor(this)
         networkMonitor.setNetworkListener(this)
         networkMonitor.start()
    }

    // STEP 3
    override fun isConnected() {
        networkMonitor.hideAlert()
        statusNetwork.text = "Internet Connected"
    }
    
    // STEP 4
    override fun isNotConnected() {
        statusNetwork.text = "Internet is NOT Connected"
	// STEP 5
        networkMonitor.showAlert()
        networkMonitor.setBackgroundMessage("#FFFFFF")
        networkMonitor.onDialogActionListener(object: NetworkMonitor.DialogListener{
            override fun onCancel() {
                Toast.makeText(applicationContext, "Clicked Cancel", Toast.LENGTH_SHORT).show()
            }

            override fun onTryAgain() {
                Toast.makeText(applicationContext, "Clicked Try", Toast.LENGTH_SHORT).show()
            }
        })
    }

    // STEP 6
    override fun onResume() {
        super.onResume()
        networkMonitor.register()
    }

    // STEP 7
    override fun onStop() {
        super.onStop()
        networkMonitor.unregister()
    }
}
```
## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.
Please make sure to update tests as appropriate.
## License
[MIT](https://choosealicense.com/licenses/mit/)
