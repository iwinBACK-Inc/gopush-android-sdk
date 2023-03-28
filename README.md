
# Integration

1. Set up Firebase Cloud Messaging

Automatic:

Open your project in Android Studio. Navigate to **Tools -> Firebase -> Cloud Messaging -> Set up Firebase Cloud Messaging** (**[KOTLIN]** for kotlin projects) and link your application with your firebase project.


Manual:

Follow [this guide](https://firebase.google.com/docs/android/setup)



2. Add gopush library dependency to your application build.gradle:

```groovy
implementation 'com.iwinback:gopush:1.0.0'
```


3. Initialize SDK

```kotlin
val config = Config()
config.apiToken = "Your API Token"
GoPush.go(config, this)
```


4. Android 13 notification runtime permission


Make sure your application targets at least API level 33.
Add following code when your application is ready to request notifications permissions:

```kotlin
if (GoPush.shouldShowRequestNotificationPermissionRationale()) {
	showNotificationRationale()
} else {
	GoPush.requestNotificationPermission(this)
}

fun showNotificationRationale() {
	...
	GoPush.requestNotificationPermission(this)
}
```


5. Android notification icon.

Each application should provide a custom notification icon and notification color in the AndroidManifest.xml under the `<application>` tag:

```xml
<meta-data android:name="com.google.firebase.messaging.default_notification_icon"
    android:resource="@drawable/notification_icon_name" />
<meta-data   android:name="com.google.firebase.messaging.default_notification_color"
    android:resource="@color/notification_icon_color" />
```

Notification icon should be entirely white on a transparent background.


6. Notify GoPush SDK when firebase token changes.

Add custom `FirebaseMessagingService` and register it in the AndroidManifest.xml:

`YourMessagingService.kt`:

```kotlin
class YourMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        GoPush.setPushToken(this, token)
    }
}
```

`AndroidManifest.xml`:

```xml
<service
    android:name=".YourMessagingService"
    android:exported="false">
    <intent-filter>
        <action android:name="com.google.firebase.MESSAGING_EVENT" />
    </intent-filter>
</service>
```

# Track user session

Run the following code as soon as a user logs in:

```
GoPush.startUserSession(User(id = "user_id", email = "user email address", phoneNumber = "user phone number"))
```

Use following method to track anonymous sessions if no user is logged in yet or if the user logs out:

```kotlin
GoPush.startAnonymousSession()
```

# Segmentation

Users can be tagged with custom properties to be able to target specific audience segments:

GoPush.setIntTag(name = "age", 24)
GoPush.setStringTag(name = "favouritePet", value = "cat")


# Push notifications tracking

Add following code to track notification delivery stats:

```kotlin
config.exportDeliveryMetricsToBigQuery = true
```

# Notification open event

GoPush SDK starts application launcher activity by default. Notification open event can be handled in your `MainActivity` `onCreate`, `onNewIntent` methods:

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    GoPush.launchNotification?.let {
        // TODO: handle notification open event
    }
}

override fun onNewIntent(intent: Intent?) {
    super.onNewIntent(intent)

    GoPush.launchNotification?.let {
        // TODO: handle notification open event
    }
}
```

You can also specify a custom intent action in the notification payload. GoPush SDK will launch Activity with the provided action instead of the default launcher activity:

```xml
<activity
    android:name=".NotificationActivity"
    android:exported="false">
    <intent-filter>
        <action android:name="my.intent.action.NOTIFICATION_OPEN" />
        <category android:name="android.intent.category.DEFAULT" />
    </intent-filter>
</activity>
```
