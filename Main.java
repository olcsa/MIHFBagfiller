import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public enum InputType{
        ITEMS, BAGSIZE, ITEMSIZE
    }

    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        int itemsSize = 0;
        int[] bagdims = new int[2];

        InputType intype = InputType.BAGSIZE;
        int itemnumber = 1;

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String line;
        try {
            while ((line = input.readLine()) != null) {
                if(line.matches("")) break;
                String[] attribs = line.split("\t");
                switch (intype){
                    case BAGSIZE:
                        bagdims[0] = Integer.valueOf(attribs[0]);
                        bagdims[1] = Integer.valueOf(attribs[1]);
                        intype = InputType.ITEMSIZE;
                        break;

                    case ITEMSIZE:
                        itemsSize = Integer.valueOf(attribs[0]);
                        intype = InputType.ITEMS;
                        break;

                    case ITEMS:
                        items.add(new Item(itemnumber, Integer.valueOf(attribs[0]), Integer.valueOf(attribs[1])));
                        itemnumber++;
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        Bag bag = new Bag(bagdims[0], bagdims[1], items, itemsSize);

        try {
            bag.orderMagicBag();
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(bag.toString());

    }
}

