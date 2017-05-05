package jianqiang.com.androidproject1;

//接口注入
public class Room4 implements IRoom {
    private Chair chair;
    private Desk desk;

    @Override
    public void bindDesk(Desk desk) {
        this.desk = desk;
    }

    @Override
    public void bindChair(Chair chair) {
        this.chair = chair;
    }
}
