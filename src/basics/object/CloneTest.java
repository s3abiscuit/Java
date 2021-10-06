package basics.object;

public class CloneTest {
    public static void main(String[] args) {
        testObjectType();
    }

    private static void testObjectType() {
        Address address = new Address("address5");
        Student stu5 = new Student(5, "stu5", address);
        Student stu6 = (Student) stu5.clone();
        System.out.println(stu5);
        System.out.println(stu6);
        System.out.println("--------------------------------");
        address.setAddr("address55");
        System.out.println(stu5);
        System.out.println(stu6);
    }
}

class Student implements Cloneable {
    private int number;
    private String name;
    private Address address;

    public Student(int number, String name, Address address) {
        this.number = number;
        this.name = name;
        this.address = address;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public Object clone() {
        Student stu = null;
        try {
            stu = (Student) super.clone();
            if (address != null) {
                stu.address = (Address) address.clone();
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return stu;
    }

    @Override
    public String toString() {
        return super.toString() + " Student{" +
                "number= " + number +
                " name= " + name +
                " address " + address +
                '}';
    }
}

class Address implements Cloneable {
    private String addr;

    public Address(String addr) {
        this.addr = addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public Object clone() {
        Address address = null;
        try {
            address = (Address) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return address;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addr='" + addr + '\'' +
                '}';
    }
}
