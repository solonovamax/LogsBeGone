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

Currently suppressed log messages:

- `Ignoring chunk since it's not in the view range: {}, {}`
- `Received passengers for unknown entity`
- `Unused frames in sprite {}: {}`
- `Missing model for variant: '{}'`/`Exception loading blockstate definition: '{}' missing model for variant: '{}'`
- `Trying to mark a block for PostProcessing @ {}, but this operation is not supported.`
- [Geckolib 3.x `Could not load animation: %s. Is it missing?`](https://github.com/bernie-g/geckolib/issues/654) (Because they refused to
  fix it themselves)
- Several `Exception occurred in netty pipeline` messages:
    - `io.netty.channel.unix.Errors$NativeIoException: (...) failed: Connection reset by peer`
    - `java.nio.channels.ClosedChannelException: null`
    - `io.netty.handler.timeout.ReadTimeoutException: null`
    - `java.net.SocketException: Connection reset`
