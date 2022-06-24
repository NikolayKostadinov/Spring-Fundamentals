package bg.softuni.intro.ioc;

public class Cat implements Animal{

    private String name;

    @Override
    public String getName() {
        return name;
    }

    public Cat setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String makeNoise() {
        return "Miau miau!";
    }


}
