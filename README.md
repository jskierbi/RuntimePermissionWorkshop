# Android Workshop for Runtime Permissions

Workshop will show you (by hand) how to migrage your app from API 22 to 23 and survive permission deny armageddon coming with Android Marshmallow release.

There are two examples - one using Location, and one with phone call feature. I recommend going with second one as it is more interesting (Lint doesn't underline it and help you handle SecurityException).

#How to start

Prepare phone or emulator with API >= 23 (Android M).
Connection to Internate in case you forgot to download SDK API = 22 and 23, emulator image, and for dependecies.

Location example start with [9664218](https://github.com/tajchert/RuntimePermissionWorkshop/commit/9664218ddb5e5d1005502d53d988682db454f5c6) commit.

Phone call (recommended) example start with [4ff6466](https://github.com/tajchert/RuntimePermissionWorkshop/commit/4ff64661d157491aa6ddddb7e9811cb1e75ac923) commit.

And proceed to next commits, read comments and look for DIY TODO marks with tasks what to try yourself (they are solved with next commit).

Good luck!

#Read at home

Links I found useful on Runtime Permission topic. Can be a good read after a workshop.


[Google Permission guide](https://developer.android.com/preview/features/runtime-permissions.html)

[Google Runtime Permission sample](https://github.com/googlesamples/android-RuntimePermissions?linkId=16169284)

[Sample with accessing Local Storage](https://github.com/ianhanniballake/LocalStorage/)

[Everything every Android Developer must know about new Android's Runtime Permission](http://inthecheesefactory.com/blog/things-you-need-to-know-about-android-m-permission-developer-edition/en)

[Understanding Permissions in Android M](http://code.tutsplus.com/articles/understanding-permissions-in-android-m--cms-24443)

[Exploring the new Android Permissions Model](https://medium.com/ribot-labs/exploring-the-new-android-permissions-model-ba1d5d6c0610#8de8)

#TODO

Add [PermissionsDispatcher](https://github.com/hotchemi/PermissionsDispatcher) library for easy asking using annotations.
