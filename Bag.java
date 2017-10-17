import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olcsa on 2017.10.13..
 */
public class Bag {
    private int dim1;
    private int dim2;
    private int[][] bag;
    private List<Item> itemHolder;
    private int itemCount;


    Bag(int dim1, int dim2, List<Item> itemHolder, int itemCount) {
        this.dim1 = dim1;
        this.dim2 = dim2;
        this.itemHolder = itemHolder;
        this.itemCount = itemCount;
        bag = new int[dim1][dim2];
        for (int i = 0; i < dim1; i++) {
            for (int j = 0; j < dim2; j++) {
                bag[i][j] = 0;
            }
        }
    }

    void orderMagicBag() throws Exception{
        Item item;
        for (int i = 0; i < itemCount; i++) {
            item = findMaxDimItem();
            itemHolder.remove(item);

            Point point = findBestFreeSpaceForItem(item);
            if (point == null){
                item.rotateItem();
                point = findBestFreeSpaceForItem(item);
                if (point == null){
                    throw new Exception("Hiba az elhelyezésnél");
                }
            }

            putInTheBag(item, point);
            System.out.println(this.toString() + "\n");
        }
    }

    //region orderMagicBag segedfuggvenyek
    /**
     * megkeresi a legnagyobb dimenzioju itemek kozul a legnagyobbat
     * pl ((7,2), (1,7), (4,3))-bol (7,2).
     */
    private Item findMaxDimItem(){
        List<Item> tmp = new ArrayList<>();
        int maxdim = 1;
        Item biggest;

        for (Item item: itemHolder) {
            if(item.getDim1() > maxdim || item.getDim2() > maxdim){ //Ha valamelyik dimenzio nagyobb mint a max akkor maxdim legyen az a dimenzio
                maxdim = item.getDim1() >= item.getDim2() ? item.getDim1() : item.getDim2();
            }
        }
        for (Item item: itemHolder) {
            if (item.getDim1() == maxdim || item.getDim2() == maxdim)
                tmp.add(item);
        }
        biggest = tmp.get(0);
        for (Item item: tmp){//Ha az item terulete nagyobb mint a biggest akkor az legyen a biggest
            if((item.getDim1()*item.getDim2()) > (biggest.getDim1()*biggest.getDim2())){
                biggest = item;
            }
        }
        return biggest;
    }

    private Point findBestFreeSpaceForItem(Item item){
        for (int i = 0; i < dim1 - item.getDim1() + 1; i++) {          // csak a dim1-itemdim1ig megy(csak igy fer bele)
            for (int j = 0; j < dim2 - item.getDim2() + 1; j++) {      // Ez a két ciklus megtalál egy helyet
                if(isItemFit(item, new Point(i, j))){
                    return new Point(i,j);
                }
            }
        }
        return null;
    }

    /**
     *ellenőrzi hogy a megtalalt ponthoz belefer-e az item
     **/
    private boolean isItemFit(Item item, Point p){
        //ellenőrzöm hogy nem log e ki az item a bagbol
        if(dim1 < (p.x + item.getDim1()) || dim2 < (p.y + item.getDim2())){
            return false;
        }

        for (int i = p.x; i < (p.x + item.getDim1()); i++) {
            for (int j = p.y; j < (p.y + item.getDim2()); j++) {
                if(bag[i][j] != 0){
                    return false;
                }
            }
        }

        return true;
    }

    private void putInTheBag(Item item, Point p){
        for (int i = p.x; i < p.x + item.getDim1(); i++) {
            for (int j = p.y; j < p.y + item.getDim2(); j++) {
                bag[i][j] = item.getNumber();
            }
        }
    }
    //endregion

    @Override
    public String toString(){
        String out = "";
        for (int i = 0; i < dim1; i++) {
            for (int j = 0; j < dim2; j++) {
                if(j != dim2-1)
                    out += (bag[i][j]) + "\t";
                else
                    out += bag[i][j];
            }
            out += "\n";
        }
        return out;
    }

    private class Point{
        public int x;
        public int y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
