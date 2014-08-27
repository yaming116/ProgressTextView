ProgressTextView
================

A progress view  use font TextView

![screenshot](https://lh4.googleusercontent.com/hxZq-ykWw5CdRVkusl5qkAsTQ2w-GmOQBQC-Q2LcHOA=w317-h563-no)


XML Usage
========

```xml
<com.github.yaming.progresstextview.ProgressTextView
        xmlns:custom="http://schemas.android.com/apk/res-auto"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        custom:anim="@anim/loading"
        custom:font="loading.ttf"
</com.github.yaming.progresstextview.ProgressTextView>
```

* `anim` - Optional - animation res file Default: `/res/anin/loading.xml`
* `font` - Optional - font file Default: `/assets/fonts/loading.ttf`
