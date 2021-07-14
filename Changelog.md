
v1.0.4
=======
- added encryption strategy to retrofitclientbuilder
 so if you want to encrypt only request or response or both now you can do.
e.g RetrofitClientBuilder(key,encyptionstrategy) by default it will take EncryptionStrategy.REQUEST_RESPONSE if you invoke this method
and if you not invoking method EncryptionStrategy.NONE will used.


``` java enum class EncryptionStrategy {
    NONE,
    REQUEST,
    RESPONSE,
    REQUEST_RESPONSE,
}
```

v1.2
=======
- Fixed multipart text body issue [MAJOR]