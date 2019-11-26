# MinTimetable
MinTimeTable is Customizable library to generate **Timetable** of University.   
If you only add a course, **the course time is automatically calculated** and added to the timetable.   
(default 09:00 ~ 16:00)

### Author Information
----   
Timetable Library for Android Development   
Author : Mint Park / Seoul, South Korea   
Email : nasamk3@gmail.com   
Newest Version : 1.1.3 (JitPack)  
  
![Platform](https://img.shields.io/badge/Platform-Android-orange.svg)
![API](https://img.shields.io/badge/API-19%2B-green.svg)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## Screenshot   

#### Portrait & Landscape Timetable   
![screenshot](./screenshot_1.png)   

## Installation
### JitPack
MinTimeTable is available through JitPack, to install it simply add the following line to your Gradle:   
```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
```groovy
implementation 'com.github.islandparadise14:Mintable:1.1.3'
```

## Usage   
### Add View
```xml
<com.islandparadise14.mintable.MinTimeTableView
                android:id="@+id/table"
                android:layout_width="match_parent"
                android:layout_height="wrap_content" />
```

### Day Symbol Definition   
```kotlin
private val day = arrayOf("Mon", "Tue", "Wen", "Thu", "Fri")  
```
### Make Table
override onWindowFocusChanged because we know the size of the view after onCreate is finished.
If you don't want to use this method, see the optimization options below.
```kotlin
override fun onWindowFocusChanged(hasFocus: Boolean) {
    super.onWindowFocusChanged(hasFocus)
    table.initTable(day)
}
```
![screenshot](./screenshot_2.png){: width="100"}

### Add Schedules
```kotlin
private val scheduleList: ArrayList<ScheduleEntity> = ArrayList()
```
```kotlin
val schedule = ScheduleEntity(
                  32, //originId
                  "Database", //scheduleName
                  "IT Building 301", //roomInfo
                  ScheduleDay.TUESDAY, //ScheduleDay object (MONDAY ~ SUNDAY)
                  "8:20", //startTime
                  "10:30", //endTime
                  "#73fcae68", //backgroundColor (optional)
                  "#000000" //textcolor (optional)
                )
```
```kotlin
scheduleList.add(schedule)
```
```kotlin
override fun onWindowFocusChanged(hasFocus: Boolean) {
    super.onWindowFocusChanged(hasFocus)
    table.initTable(day)
    table.updateSchedules(scheduleList)
}
```
![screenshot](./screenshot_3.png){: width="100"}

If you want to start on Sunday,
use 'ScheduleDayOption.${weekday}' (SUNDAY ~ SATURDAY)

## Optimization Option
Make the view fullWidth

![screenshot](./screenshot_4.png){: width="100"}

add attribute 'isFullWidth' (default: false)
```xml
<com.islandparadise14.mintable.MinTimeTableView
                android:id="@+id/table"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                app:isFullWidth="true" />
```
then you don't need override onWindowFocusChanged

if you want to add padding using optimization option, add attribute 'widthPadding' (default: 0)
```xml
<com.islandparadise14.mintable.MinTimeTableView
                android:id="@+id/table"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                app:isFullWidth="true"
                app:widthPadding="20" />
```

## More Options
### Add Listener
ScheduleEntity has onClickListener
```kotlin
schedule.setOnClickListener(View.OnClickListener {
    //do something
})
```
if you need originId in Listener, you can use OnScheduleClickListener
```kotlin
schedule.setOnScheduleClickListener(
    object :ScheduleEntity.OnScheduleClickListener {
        override fun scheduleClicked(originId: Int) {
            //do something
        }
    }
)
```

### Length options
#### Length
baseSetting(topMenuHeight: Int, leftMenuWidth: Int, cellHeight: Int)
```kotlin
table.baseSetting(30, 40, 60) //default (20, 30, 50)
```

#### Rate
ratioCellSetting(topMenuHeight: Int, leftMenuWidth: Int, cellRatio: Float)
```kotlin
table.ratioCellSetting(20, 30, 1.5f)
```

### Border Option
add attribute 'radius_option' ( none | left | right | round )
```xml
<com.islandparadise14.mintable.MinTimeTableView
                android:id="@+id/table"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                app:radius_option="left" />
```

![screenshot](./screenshot_5.png) 

### Color options
add attribute 'cellColor', 'lineColor', 'menuColor'
```xml
<com.islandparadise14.mintable.MinTimeTableView
                android:id="@+id/table"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                app:cellColor="@color/black"
                app:lineColor="@color/colorAccent"
                app:menuColor="@color/colorPrimary" />
```

![screenshot](./screenshot_6.png)

