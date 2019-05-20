# RxNetWork
添加依赖
<br>
implementation 'com.github.yijigu7745:RxNetWork:0.35-alpha'
<br>
Project的build.gradle里需要添加<br>
allprojects {<br>
repositories {<br>
google()<br>
jcenter()<br>
maven { url 'https://jitpack.io' }<br>
}<br>
}<br>

在基Application里进行初始化：<br>
new RetrofitUtilsApplication().onCreate(this);
