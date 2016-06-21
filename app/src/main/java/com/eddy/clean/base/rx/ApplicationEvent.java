package com.eddy.clean.base.rx;

/**app的生命周期event
 * Created by liutao on 2016/6/12.
 */
public enum ApplicationEvent{
    ONCREATE,//创建的时候
    ONTERMINATE,//程序终止的时候,貌似无反应
    ONLOWMEMORY,//低内存的时候
    ONTRIMMENORY  //程序内存清理的时候
}
