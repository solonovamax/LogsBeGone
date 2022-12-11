# Logs Be Gone

Don't like to see your logs spammed with messages like this?

```
[23:21:46] [Render thread/WARN]: Ignoring chunk since it's not in the view range: 71, 65
[23:21:46] [Render thread/WARN]: Ignoring chunk since it's not in the view range: 71, 66
[23:21:46] [Render thread/WARN]: Ignoring chunk since it's not in the view range: 71, 67
[23:21:46] [Render thread/WARN]: Ignoring chunk since it's not in the view range: 97, 41
[23:21:46] [Render thread/WARN]: Ignoring chunk since it's not in the view range: 71, 68
[23:21:46] [Render thread/WARN]: Ignoring chunk since it's not in the view range: 71, 69
[23:21:46] [Render thread/WARN]: Ignoring chunk since it's not in the view range: 98, 41
[23:21:46] [Render thread/WARN]: Ignoring chunk since it's not in the view range: 99, 41
[23:21:46] [Render thread/WARN]: Ignoring chunk since it's not in the view range: 71, 70
[23:21:46] [Render thread/WARN]: Ignoring chunk since it's not in the view range: 71, 71
[23:21:46] [Render thread/WARN]: Ignoring chunk since it's not in the view range: 100, 41
[23:21:46] [Render thread/WARN]: Ignoring chunk since it's not in the view range: 101, 41
[23:21:46] [Render thread/WARN]: Ignoring chunk since it's not in the view range: 102, 41
[23:21:46] [Render thread/WARN]: Ignoring chunk since it's not in the view range: 103, 41
[23:21:46] [Render thread/WARN]: Ignoring chunk since it's not in the view range: 71, 72
[23:21:46] [Render thread/WARN]: Ignoring chunk since it's not in the view range: 71, 73
[23:21:46] [Render thread/WARN]: Ignoring chunk since it's not in the view range: 71, 74
[23:21:46] [Render thread/WARN]: Ignoring chunk since it's not in the view range: 72, 75
[23:21:46] [Render thread/WARN]: Ignoring chunk since it's not in the view range: 73, 75
[23:21:46] [Render thread/WARN]: Ignoring chunk since it's not in the view range: 74, 75
[23:21:46] [Render thread/WARN]: Ignoring chunk since it's not in the view range: 75, 75
[23:21:46] [Render thread/WARN]: Ignoring chunk since it's not in the view range: 76, 75
[23:21:46] [Render thread/WARN]: Ignoring chunk since it's not in the view range: 77, 75
```

Then this mod is for you!

It removes that logging message and several others!! amazing!!

Currently removed messages:

- "Ignoring chunk since it's not in the view range"
- "Trying to mark a block for PostProcessing @ {}, but this operation is not supported."

Note: This mod doesn't simply filter the log messages, it actually stops them from being logged in the first place.
This is beneficial, because those specific log messages can be very spammy and will actually incur a performance hit
because of how often they are logged.