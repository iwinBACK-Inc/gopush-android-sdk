
# Integration

1. Set up Firebase Cloud Messaging

Automatic:

Open your project in Android Studio. Navigate to **Tools -> Firebase -> Cloud Messaging -> Set up Firebase Cloud Messaging** (**[KOTLIN]** for kotlin projects) and link your application with your firebase project.


Manual:

Follow [this guide](https://firebase.google.com/docs/android/setup)



2. Add gopush library dependency to your application build.gradle:

```groovy
implementation files('libs/gopush.aar')
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

YourMessagingService.kt:

class YourMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        GoPush.setPushToken(this, token)
    }
}

AndroidManifest.xml:

<service
    android:name=".TestMessagingService"
    android:exported="false">
    <intent-filter>
        <action android:name="com.google.firebase.MESSAGING_EVENT" />
    </intent-filter>
</service>
