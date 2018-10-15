package proxy;

public class IUserImp implements IUser {
    @Override
    public String getName() {
        System.out.println("getName");
        return null;
    }

    @Override
    public int getAge() {
        System.out.println("getAge");
        return 0;
    }
}
