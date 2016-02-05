package ms_code.Math381;

import java.io.*;
import java.util.*;

/**
 * Created by saketp on 1/30/2016.
 */
public class AbrrevClient {
    public static void main(String[] args) {
        ArrayList<NutritionalFacts> list = new ArrayList<>();
                //                     up cal     lb cal        up sod     lb carbs     lb fiber  lb prot    lb calc    lb iron   lb vitamin c  fat lb  fat ub    ph lb       ub sugar  lb magnes   lb potas
         String[] twentyConstraints = {"<= 2800", " >= 2300", " <= 2000", ">= 120", " >= 15", " >= 50", " >= 1000 ", "  >= 15 ", " >= 90 ", ">= 70", "<= 100",   ">= 1000", " <= 40", " >= 400",  " >= 4000",
                //zinc ub    copper ub  mangan ub   sele ub    thia ub     ribo ub     niacin ub  panto_acid ub  vitamin b6
                 " <= 40 ", " <= 2 ", " <= 2.3",   "<= 55",   "<= 1.0 ", " <= 1.1",  " <= 80 " ,  " <= 6",     " <= 10"};

        int[] duplicateTwentyConstraints = { 0, 8}; //upper and lower bounds for fat and calorie intake
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("abrrev_data_large.txt")));
            String line = "";
            line = br.readLine(); //ignore the first line
            while((line = br.readLine()) != null) {
                String[] comps = line.split("\t");
                NutritionalFacts nf = constructNf(comps);
                list.add(nf);
            }
            int[] chosenNoWater = {4946, 4311, 4688, 8671, 8425, 4950, 8423, 5313, 1992, 3612, 8404, 2121};
            int[] chosenWater = {6416, 6311, 7758, 8763, 505, 6082, 725, 8625, 6421, 8652, 8753, 3613, 4306, 999};

            //This will print out the chosen foods depending on the variation of the project
            for(int i: chosenNoWater) {
                System.out.println("x" + i + " = " + list.get(i - 1).getItemName());
            }
            double[][] matrix = new double[list.size()][23];
            int counter = 0;

            for(int i = 0; i < list.size(); i++) {
                double[] array = list.get(i).returnArray();
                matrix[counter++] = array;

            }
            generateConstraints(matrix, twentyConstraints, duplicateTwentyConstraints, "constraintsAbrrev20.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Takes an array of Strings and constructs a NutritionFacts object
    private static NutritionalFacts constructNf(String[] comps) {
        if(comps.length < 25) {
            return null;
        }
        int id = Integer.parseInt(comps[0]);
        String name = comps[1];
        double water = convertToDouble(comps[2]);
        double calories = convertToDouble(comps[3]);
        double protein = convertToDouble(comps[4]);
        double fat = convertToDouble(comps[5]);
        double carbs = convertToDouble(comps[7]);
        double fiber = convertToDouble(comps[8]);
        double sugar = convertToDouble(comps[9]);
        double calcium = convertToDouble(comps[10]);
        double iron = convertToDouble(comps[11]);
        double magnesium = convertToDouble(comps[12]);
        double phosp = convertToDouble(comps[13]);
        double potassium = convertToDouble(comps[14]);
        double sodium = convertToDouble(comps[15]);
        double zinc = convertToDouble(comps[16]);
        double copper = convertToDouble(comps[17]);
        double manganese = convertToDouble(comps[18]);
        double selenium = convertToDouble(comps[19]);
        double vitC = convertToDouble(comps[20]);
        double thiamin = convertToDouble(comps[21]);
        double riboflavin = convertToDouble(comps[22]);
        double niacin = convertToDouble(comps[23]);
        double panto_acid = convertToDouble(comps[24]);
        double vitB6 = convertToDouble(comps[25]);
        return new NutritionalFacts(name, id, calories, sodium, carbs, fiber, protein, calcium, iron, vitC, fat, phosp, water, sugar, magnesium, potassium, zinc, copper, manganese, selenium, thiamin, riboflavin, niacin, panto_acid, vitB6);
    }

    //Converts a String to a Double
    public static double convertToDouble(String s) {
        if(!s.equals("")) {
            return Double.parseDouble(s);
        }
        return 0;
    }

    //Prints the matrix containing the food data
    public static void printMatrix(double[][] matrix) {
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    //This method will generate the constraints given a matrix indicating the food details, an array values
    //indicating the constraints, an array indices indicating which indices are to duplicated for both
    //a lower and upper bound, and an output file
    public static void generateConstraints(double[][] matrix, String[] values, int[] indices, String outputFile) {
        try {
            BufferedWriter fw = new BufferedWriter(new FileWriter(new File(outputFile)));
            int numCols = matrix[0].length;
            int numRows = matrix.length;
            int arrayCounter = 0; //pointer for the indices array
            int valuesPointer = 0; //pointer for the constraints array
            StringBuilder constraintsDef = new StringBuilder();
            //Write the min
            constraintsDef.append("min: ");
            for(int i = 1; i <= numRows; i++) {
                constraintsDef.append("x" + i);
                if(i != numRows) {
                    constraintsDef.append(" + ");
                }
            }
            //write the constraints
            //System.out.println("Num Rows: " + numRows + " Num Cols: " + numCols);
            fw.write(constraintsDef.toString() + ";\n");
            for(int i = 0; i < numCols; i++) {
                StringBuilder resultString = new StringBuilder();
                for (int j = 0; j < numRows; j++) {
                    resultString.append(matrix[j][i] + "x" + (j + 1));
                    if(j != numRows - 1) {
                        resultString.append(" + ");
                    }
                }
                if(arrayCounter < indices.length && indices[arrayCounter] == i) {
                    //have two constraints
                    //System.out.println(resultString + values[valuesPointer]);
                    fw.write(resultString.toString() + values[valuesPointer++] + ";\n");
                    arrayCounter++;
                }
                //System.out.println(resultString.toString() + values[valuesPointer]);
                fw.write(resultString.toString() + values[valuesPointer++] + ";\n" );
                //constraints for enforcing positivity
            }
            for(int i = 1; i < numRows; i++) {
                fw.write("x" + i + " >= 0;\n");
            }
            //This parts adds comments indicating the actual english name for each x_i term
            /*fw.write("*//*");
            for(int i = 1; i <= numRows; i++) {
                fw.write("x" + i + " = " + cats.get(i - 1) +  "\n");
            }
            fw.write("*//*");*/
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
