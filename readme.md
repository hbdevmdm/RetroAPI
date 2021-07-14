# RetroAPI - HTTP client for Android and Java

RetroAPI is library to make network calls which totally relies on Retrofit.
We just created wrapper above retrofit library for some extra features like make secure request by converting data to encrypted form,
generating checksum of request so no middle man can attack on transport layer.
And the main thing is that you don’t need extra knowledge to make network requests ( just retrofit library knowledge is enough)
also if your project is already developed using retrofit then you just need to very small change to make existing code to be worked with encryption,
checksum and many more features.



# Features
1. Encrypt your parameter using some well known encryption algorithm.
2. Generate checksum of your request parameters so it will take about transport layer attacks.
3. Handle Response Globally so you can make some decisions based on response
4. Common parameter and headers - you can pass globally
5. To eliminate boilerplate code of object/array data issue (for CIT api only)

Download
--------

Grab via Maven:
```xml
<dependency>
  <groupId>com.hb.retroapi</groupId>
  <artifactId>retroapi/artifactId>
  <version>1.4</version>
  <type>pom</type>
</dependency>
```
or Gradle:
```groovy
implementation 'com.hb.retroapi:retroapi:1.4'
```
RootProject > build.gradle

```groovy
allprojects
{
    repositories {
          maven { url "https://dl.bintray.com/hbdevmdm/maven" }
          }
}
```


# How it works?

Build RetrofitClient Using below

```java
RetrofitClientBuilder()
    .baseUrl(BASE_URL)
    .connectionTimeout(30)
    .readTimeout(30)
    .addInterceptor(RequestInterceptor(object : RequestInterceptor.OnRequestInterceptor {
        override fun provideBodyMap(): HashMap<String, String> {
            return HashMap() // pass parameter that you want to attach as body for every api
        }

        override fun provideHeaderMap(): HashMap<String, String> {
            return HashMap() // pass parameter that you want to attach as header for every api
        }
    }))
    .addLogInterceptor(HttpLoggingInterceptor.Level.BODY)
    .addInterceptor(ResponseInterceptor(object : ResponseInterceptor.OnResponseCallback {
        override fun onNetworkResponse(response: Response) {
            // Here you can perform task based on any api response
            // Like if your token expired you can navigate user to login screen / authentication screen
        }
    }))
    .addEncryptionInterceptor("<KeyToEncrypt>",
        EncryptionStrategy.REQUEST_RESPONSE,
        true,
        "ws_checksum",
        excludeFromEncryption = arrayListOf("ws_token"))
    .addConverterFactory(GsonConverterFactory.create())
    .create(ApiService::class.java)
```
# Documentation
### RetroAPI is nothing but collection of interceptors to manage request in our own way.

## Collections

**1. RequestInterceptor** - This interceptor is used to add some common request header & body parameters. Like device information, token , authentication header and many more..
provideBodyMap() will include your map to your existing request params ( it’ll concern about your request method also like POST , GET , PUT , DELETE ...) <br />
ProvideHeaderMap() will include your map to your existing header <br />
if you’re using this interceptor and any logging interceptor then put your logging interceptor after this interceptor otherwise your logging interceptor will not include your parameter & header which added by this interceptor <br />

**2. ResponseInterceptor** -  This interceptor to handle network response globally. E.g if you’re getting token expired status code and you want to move user to login screen then this interceptor will be used. You can manage many more things using this interceptor. <br />

**3. SecureRequestInterceptor** - This interceptor is used to make your request secure. Secure in the way means applying encryption and checksum. This will achieve by adding addEncryptionInterceptor() method to retrofitClientBuilder. <br />
Parameters <br />
encryptionKey - provide encryption key here to encrypt your request e.g ABC@WS! <br />
encryptionStrategy - either you want to encrypt only request or response or both. If you encrypt only request then your parameter will only encrypt. If you apply encrypt to response then your response will decrypt using your encryption key. Applying both will encrypt the request and decrypt the response. So you don’t need to do extra coding to encrypt or decrypt. <br />
enableChecksum - whether you want to generate checksum for your request parameters or not <br />
checksumKey - while checksum is generated will attach to your request body with this key <br />
excludeFromChecksum - if you want to exclude some parameters while generating checksum you can specify list of keys here <br />
excludeFromEncryption -  if you want to exclude some parameters to be encrypted you can specify list of keys here <br />

**4. LogInterceptor** - This interceptor is used to dump log. (this used  httpLoggingInterceptor) using addLogInterceptor() method <br />

**5. DataConverterInterceptor** - This interceptor is mainly created for CIT api response. Because sometimes CIT api sends data as array if requested api fails which was intended to send object as data. So to eliminate boilerplate code this interceptor is used. To add this interceptor just add DataConverterInterceptor() as interceptor and provide annotation at your api service class with this annotation - @DataAsObjectResponse / @DataAsListResponse <br />


# License

```
Copyright 2017 HiddenBrains

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.