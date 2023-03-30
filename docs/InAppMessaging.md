## Installation

Add GoPush library dependency to your application `build.gradle`:

```groovy
implementation 'com.iwinback:gopush-messaging:1.0.0'
```


## Start messaging

```swift
InAppMessaging.go()
```

This will automatically trigger default `applicationOpen` event.


## Pause messaging

Pause InAppMessaging when user should not be interrupted with popups (don’t forget to unpause when application is ready to receive In-Apps again):

```swift
InAppMessaging.isPaused = true
```


## Listening for messages

```kotlin
InAppMessaging.addListener(object : InAppMessageListener {
    override fun onMessageDisplayed(message: InAppMessage) {
        // Message displayed
    }

    override fun onMessageFinished(message: InAppMessage) {
        // Message finished
    }

    override fun onEventReceived(message: InAppMessage, event: InAppMessageEvent) {
        // Handle In-App event
    }
});
```
