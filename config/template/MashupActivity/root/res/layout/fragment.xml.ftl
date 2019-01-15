<?xml version="1.0" encoding="utf-8"?>
<layout>

    <data>
        <variable
                name="viewmodel"
                type="${featurePackageName}.vm.${viewModelClass}"
                />
    </data>

    <RelativeLayout
            xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:app="http://schemas.android.com/apk/res-auto"
            xmlns:tools="http://schemas.android.com/tools"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:clickable="true"
            android:focusable="true"
            android:fitsSystemWindows="true"
            tools:context="${featurePackageName}.ui.${fragmentClass}"
            >

    </RelativeLayout>
</layout>
