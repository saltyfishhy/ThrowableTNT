[Install Jar File](https://github.com/saltyfishhy/ThrowableTNT/raw/master/out/artifacts/throwableTNT_jar/throwableTNT.jar)

# ThrowableTNT

A Minecraft Plugin that makes TNT throwable! Configurable! 

# Permissions

There are two permissions for this plugin: one is "tnt.admin" which lets any user with the permission reload the config, and the other is settable in the config, which allows users to get and throw tnt.

# Configuartion:

The config file has three properties: 

- leftOrRightClick
- commandPermission
- amountLimit

leftOrRightClick lets you change whether or not the TNT action is triggered on left click or on right click of tnt. The value must either be LEFT_CLICK or RIGHT_CLICK

commandPermission lets you change the permission to throw / get tnt. It can be anything you want, and in order to let people throw / get tnt you must give them this permission you set

amountLimit is the max amount of tnt that you can get with /gettnt. For example, by default the limit is 5, so I could only get 5. But if I set it to 7, I would be able to get 7, and so forth.

# Commands

This plugin has three commands:

- /tnttutorial
- /gettnt
- /tntreload

The first command sends some messages in chat that explains how a user may use the plugin. 
The second command lets a user get a piece of tnt. If they enter an amount following the command, they will receive that amount.
The final command lets somebody with the permission "tnt.admin" reload the config. 
