## Track user session

Run the following code as soon as a user logs in:

```kotlin
GoPush.startUserSession(User(id = "user_id", email = "user email address", phoneNumber = "user phone number"))
```

Use the following method to track anonymous sessions if no user is logged in yet or if the user logs out:

```kotlin
GoPush.startAnonymousSession()
```

## Custom Tags

Users can be tagged with custom properties to be able to target specific audience segments:

```kotlin
GoPush.setIntTag(name = "age", 24)
GoPush.setStringTag(name = "favouritePet", value = "cat")
```
