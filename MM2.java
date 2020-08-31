

/*
 * Takes an input of two files with compatible matrixes and then prints the 
 * product of the two matrixes assuming that they are compatible. 
 */


import java.io.*;
import java.util.*;
import java.lang.Integer;

/**
 * @author wrkealey0
 * @version 21920200749
 */
public class MM2 {

    private static int colA;
    private static int colB;
    /**
     * As a result of using a scanner to read the inputs, it became more 
     * concise to write a method that clears the scanners so they 
     * can be reused.
     * @param reader is the Scanner to be cleared.
     * @param m is the File to load into the Scanner.
     * @return is the reset Scanner.
     */
    private static Scanner resetReader(Scanner reader, File m) throws IOException{
        reader.close();
        reader = null;
        reader = new Scanner(m);
        return reader;
    }

    /**
     * Determines the number of rows in an input matrix;
     * @param reader is the matrix to be read.
     * @return is the number of rows.
     */
    private static int getRows(Scanner reader){
        int count = 0;
        while (reader.hasNextLine()) {
            reader.nextLine();
            count++;
        }
        return count;
    }
    
    /**
     * Gets the number of ints in a matrix, so that we can divide this by the 
     * number of rows to see how many columns there are.
     * @param reader is the matrix to be read.
     * @return is the total number of ints in the matrix.
     */
    private static int getCount(Scanner reader) {
        int count = 0;
        while (reader.hasNext()) {
            reader.next();
            count++;
        }

        return count;

    }
    
    /**
     * Determines if matrix multiplication is possible.
     * @param fRows number of rows in first matrix.
     * @param sRows number of rows in second matrix.
     * @param fCols number of columns in first matrix.
     * @param sCols number of columns in second matrix.
     * @return true if fRows == sCols and sRows == fCols.
     */
    private static boolean isValid(int[][] a, int[][] b) {
        return(a.length==b[0].length);
    }
    

    
    
    /**
     * Uses a unorthodox use of both try/catch blocks and parseInt() to determine 
     * if a target is an integer.
     * @param s is the object to be compared.
     * @return true if integer, false if not.
     */
    private static boolean isInt(String s){
        try{
            Integer.parseInt(s);
            return true;
        } catch (Exception notARealException){
            return false;
        }
    } 
    
    /** 
     * Shows the matrix in String form.d
     * @param matrix is the matrix to convert to String.
     * @param rows is the number of Rows in the matrix.
     * @param cols is the number of columns in the matrix.
     * @return a String of the matrix.
     */
    private static String presentMatrix(int[][] matrix, int rows, int cols){
        String out = "";
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                out+=matrix[i][j]+ " ";
            }
                out+="\n";
        }
        return out;
    }
        
    /**
     * Converts scanner input into an arrayList of Strings. The Strings are cleaned 
     * using a regex to help prevent non-int values by filtering them out.
     * @param reader is a Scanner to be read from.
     * @return is an ArrayList representing the "cleaned" data from a Scanner.
     */
    private static ArrayList<String> getStrings(Scanner reader){
        ArrayList<String> o = new ArrayList<String>();
        while (reader.hasNextLine()){ 
    //Removes every non-int in the input line, unfortunately, this does mean doubles cannot be read.
            String in = reader.nextLine().replaceAll("[^0-9-\\s]", "");
            String in2 = in.replaceAll("- ", "");
            //String in2 = in.replaceAll("\\.", "");
            if (in2.charAt(0)==' '){
                o.add(in2.replaceFirst("\\s", "")); //Removes leading empty spaces.
            } else {
            o.add(in2);
            }
        }
        return o;
    }
    
    
    /**
     * Counts the number of rows in a String ArrayList
     * @param in is an ArrayList of Strings
     * @return is the number of Rows in the ArrayList.
     */
    private static int getRows(ArrayList<String> in){
        int rows = 0;
        for(String s: in){
            rows++;
        }
        return rows;
    }
    
    
    private static int[] stringToIntArray(String sT){
        String[] s = sT.split("\\s");
        int c = s.length;
        int[] h = new int[c];
        for (int i = 0; i < c; i++){
            h[i] = Integer.parseInt(s[i]);
        }
        return h;
    }
    
    
    /**
     * Converts the string arrays into a matrix of int values.
     * @param in is a String ArrayList version of the matrix with clean data.
     * @return is a matrix of the data.
     */
    private static int[][] convertToMatrix(ArrayList<String> in){
        
        int r = getRows(in);
        int[][] out = new int[r][];
        int i = 0;
        for (String s: in){
            out[i] = stringToIntArray(s);
            i++;
        }
       return out;
    }
    
    
    /**
     * Makes printing out each line of a matrix possible without knowing the column size.
     * @param k is an input element of a matrix.
     * @return is a string of that element.
     */
    private static String getString(int[] k){
        String out = "";
        for(int i = 0; i < k.length; i++){
            out+= k[i]+" ";
        }
        return out;
    }
    
    /**
     * An integrity constraint to ensure that each row in a matrix has the 
     * same number of columns
     * @param in is a matrix to be checked
     * @return true if all rows have the same number of columns.
     */
    private static boolean colCheck(int[][] in){
       int cont = in[0].length;
       boolean check = true;
       for(int[] s: in){
           if(cont - s.length != 0){
               check = false;
           }
       }
       return check;
    }

    private static int[][] multiplyMatrix(int[][] a, int[][] b){
        int aRows = a.length;
        int bCols = b[0].length;
        int[][] ans = new int[aRows][bCols];
          
        for (int i = 0; i < aRows; i++){
            
            for (int j = 0; j < bCols; j++){
                int tot = 0;
                for (int k = 0; k < b.length; k++){
                    tot = tot+a[i][k]*b[k][j];
                }
                ans[i][j] = tot;
            }
        }
       
        return ans;
    }
    
    
    
    public static void main(String[] args) throws IOException{
        
            File m1 = new File("matrixA.txt");
            File m2 = new File("matrixB.txt");

            Scanner m1Reader = new Scanner(m1);
            Scanner m2Reader = new Scanner(m2);

          

         
            
            
           
            
            m2Reader = resetReader(m2Reader, m2);
            m1Reader = resetReader(m1Reader, m1);
            
            ArrayList<String> t2 = getStrings(m2Reader);
            
            //System.out.println();
            
            ArrayList<String> t1 = getStrings(m1Reader);
            
            int fRows = getRows(t1);
            int sRows = getRows(t2);
            
            int[][] matrix1 = convertToMatrix(t1);
            int[][] matrix2 = convertToMatrix(t2);
            
        
            
           
            m1Reader.close();
            m2Reader.close();
            
            if(colCheck(matrix2)&&isValid(matrix1, matrix2)){
            
            int[][] answer = multiplyMatrix(matrix1, matrix2);
            
            File out = new File("matrixAnswer.txt" );
            
            String fPath = out.getAbsolutePath();
            
            if (out.createNewFile() == true){
                System.out.println("File \"matrixAnswer.txt\" has been created "
                        + "at: "+fPath);
            }
 
            
            FileWriter fWrite = new FileWriter(out);
            
              for(int[] mar: answer){
                    fWrite.write(getString(mar));
                    fWrite.append("\n");
                }
              
            fWrite.close();      
            } else if (!colCheck(matrix2)){
                System.out.println("Matrix B does not have a consistent number of"
                        + " elements in each row. Please check input.");
                for(int i = 0; i < matrix2.length; i++){
                    int t = i+1;
                    int j = matrix2[i].length;
                    System.out.println("MatrixB row: "+t+" has "+j+" elements.");
                }
            } else if (!colCheck(matrix1)){
                System.out.println("Matrix A does not have a consistent number of"
                        + " elements in each row. Please check input.");
                for(int i = 0; i < matrix1.length; i++){
                    int t = i+1;
                    int j = matrix1[i].length;
                    System.out.println("MatrixB row: "+t+" has "+j+" elements.");
                }
            } else if (!isValid(matrix1, matrix2)){
                System.out.println("Column and Row numbers mismatch between matrixes"
                        + ", please check the inputs and try again.\n"
                        + "Matrix A must have the same number of rows as Matrix B has columns."
                        + "\n"
                        + "Matrix A has: "+matrix1.length+" rows, and Matrix B has"
                        +matrix2[0].length+" columns.\n");
                        
            } else {
                System.out.println("An unexpected error has occurred, please check inputs");
            }
    }
}
