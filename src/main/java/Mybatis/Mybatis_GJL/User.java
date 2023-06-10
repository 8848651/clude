package Mybatis.Mybatis_GJL;

public class User {
    //学号
    private String StudentNumber;
    //姓名
    private String name;
    //密码
    private String password;


    public User() {
    }
    public User(String StudentNumber) {
        this.StudentNumber = StudentNumber;
    }

    public User(String StudentNumber, String name, String password) {
        this.StudentNumber = StudentNumber;
        this.name = name;
        this.password = password;
    }

    /**
     * 获取
     * @return StudentNumber
     */
    public String getStudentNumber() {
        return StudentNumber;
    }

    /**
     * 设置
     * @param StudentNumber
     */
    public void setStudentNumber(String StudentNumber) {
        this.StudentNumber = StudentNumber;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{StudentNumber = " + StudentNumber + ", name = " + name + ", password = " + password + "}";
    }
}
