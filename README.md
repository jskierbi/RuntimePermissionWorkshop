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

#TODO

Add [Nammu](https://github.com/tajchert/Nammu) library for monitoring permissions.

Add [PermissionsDispatcher](https://github.com/hotchemi/PermissionsDispatcher) library for easy asking using annotations.
