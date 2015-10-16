/************************************************
 * if ( (x > 0 && x < 1) && (y > 0 && y < 1) ) {*
 *    System.out.println(true);                 *
 * }                                            *
 * else System.out.println(false);              *
 ************************************************/
public class ex1_1_5 {
    public static void main(String[] args) {
        double x, y;
        x = 0.5;
        y = 0.6;
        if ( (x > 0 && x < 1) && (y > 0 && y < 1) ) {
            System.out.println(true);
        }
        else System.out.println(false);
    }
}
