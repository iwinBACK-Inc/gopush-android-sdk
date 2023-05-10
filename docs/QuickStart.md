## Firebase Setup

**Automatic:**

Open your project in Android Studio. Navigate to **Tools -> Firebase -> Cloud Messaging -> Set up Firebase Cloud Messaging** (**[KOTLIN]** for kotlin projects) and link your application with your firebase project.


**Manual:**

Follow [this guide](https://firebase.google.com/docs/android/setup)



## Installation

Add GoPush library dependency to your application `build.gradle`:

```groovy
implementation 'com.iwinback:gopush:1.0.0'
```


## Configure GoPush

```kotlin
val config = Config()
config.apiToken = "Your API Token"
GoPush.go(config, this)
```


## Notification Permission


Make sure your application targets at least API level 33.
Add the following code when your application is ready to request notifications permissions:

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


## Notification Icon.

Each application should provide a custom notification icon and notification color in the AndroidManifest.xml under the `<application>` tag:

```xml
<meta-data android:name="com.google.firebase.messaging.default_notification_icon"
    android:resource="@drawable/notification_icon_name" />
<meta-data   android:name="com.google.firebase.messaging.default_notification_color"
    android:resource="@color/notification_icon_color" />
```

Notification icon should be entirely white on a transparent background.



## FirebaseMessagingService

Notify GoPush SDK when firebase token changes.
Add custom `FirebaseMessagingService` and register it in the AndroidManifest.xml:

`YourMessagingService.kt`:

```kotlin
class YourMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        GoPush.handlePushNotificationReceived(this, message)
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


## Delivery Metrics (optional)

Enable export delivery metrics to BigQuery during GoPush configuration:

```kotlin
config.exportDeliveryMetricsToBigQuery = true
```
