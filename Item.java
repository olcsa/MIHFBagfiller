/**
 * Created by Olcsa on 2017.10.13..
 */
public class Item {
    private int number;
    private int dim1;
    private int dim2;

    public Item(int number, int dim1, int dim2) {
        this.number = number;
        this.dim1 = dim1;
        this.dim2 = dim2;
    }

    public int getDim1() {
        return dim1;
    }

    public int getDim2() {
        return dim2;
    }

    public int getNumber() {
        return number;
    }

    public void rotateItem(){
        int tmp = dim1;
        dim1 = dim2;
        dim2 = tmp;
    }
}
