package lib.posix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Posix {

    public static Double[][] stitchMovements(Object... args) {
        List<Double[]> list = new ArrayList<>();
        for (Object o : args) {
            if (o instanceof Double[]) {
                list.add((Double[]) o);
            }
            else if (o instanceof Double[][]) {
                list.addAll(Arrays.asList((Double[][]) o));
            }
        }
        return list.toArray(new Double[0][]);
    }

}
