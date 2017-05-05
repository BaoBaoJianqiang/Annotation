package jianqiang.com.androidproject1;

//构造函数注入
public class Room3 {
    private Chair chair;
    private Desk desk;

    public Room3(Chair chair, Desk desk) {
        this.chair = chair;
        this.desk = desk;
    }
}
