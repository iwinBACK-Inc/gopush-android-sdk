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
