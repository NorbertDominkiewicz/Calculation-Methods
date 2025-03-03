import java.util.ArrayList;

public class Matrix {
    public static void gaussMethod(ArrayList<ArrayList<Double>> lists) {
        ArrayList<ArrayList<Double>> rowEchelonForm = makeRowEchelonForm(lists);
        ArrayList<String> vectorEquation = new ArrayList<>();
        for (int i = 0; i < rowEchelonForm.size(); i++) {
            vectorEquation.add("x"+i);
        }
        doEquation(rowEchelonForm, vectorEquation);
    }

    public static void writeOut(ArrayList<ArrayList<Double>> lists) {
        for (ArrayList<Double> row : lists) {
            for (Double value : row) {
                System.out.print(" " + value + " ");
            }
            System.out.println();
        }
    }

    private static void setValue(ArrayList<ArrayList<Double>> lists, int i, int j, double value) {
        lists.get(i).set(j, value);
    }

    private static ArrayList<ArrayList<Double>> makeRowEchelonForm(ArrayList<ArrayList<Double>> lists) {
        int rows = lists.size();
        int columns = lists.getFirst().size();

        ArrayList<ArrayList<Double>> workTabel = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            ArrayList<Double> row = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                row.add(lists.get(i).get(j));
            }
            workTabel.add(row);
        }

        int k = 0;
        while (k < rows - 1) {
            double saveValue;
            int i = k + 1;
            while (i < rows) {
                saveValue = workTabel.get(i).get(k);
                int j = k;
                while (j < columns) {
                    if (i == 1) {
                        setValue(workTabel, 0, j, workTabel.getFirst().get(j));
                    }
                    double result = workTabel.get(i).get(j) - (workTabel.get(k).get(j)) * saveValue / workTabel.get(k).get(k);
                    setValue(workTabel, i, j, result);
                    j++;
                }
                i++;
            }
            lists = workTabel;
            k++;
        }
        return workTabel;
    }

    private static void doEquation(ArrayList<ArrayList<Double>> lists, ArrayList<String> vectorEquation) {
        int rows = lists.size();
        int columns = lists.getFirst().size();
        double[] results = new double[rows];

        for (int i = rows - 1; i >= 0; i--) {
            double sum = lists.get(i).get(columns - 1);
            for (int j = i + 1; j < columns - 1; j++) {
                sum -= lists.get(i).get(j) * results[j];
            }
            if (lists.get(i).get(i) == 0) {
                throw new ArithmeticException("Can't divide by 0. Row: " + i);
            }
            results[i] = sum / lists.get(i).get(i);
        }

        for (int i = 0; i < results.length; i++) {
            System.out.println(vectorEquation.get(i)+" = "+results[i]);
        }
    }
}
