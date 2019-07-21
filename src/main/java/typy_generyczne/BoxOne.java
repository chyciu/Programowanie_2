package typy_generyczne;

public class BoxOne <T1, T2> {

    private T1 element1;
    private T2 element2;


    public BoxOne(T1 element1, T2 element2) {
        this.element1 = element1;
        this.element2 = element2;
    }

    public T1 getElement1() {
        return element1;
    }

    public T2 getElement2() {
        return element2;
    }


}

