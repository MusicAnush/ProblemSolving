/*
* Written by Anush Narayanan.
* 2/19/2020
* */


package MatrixRotation;

import java.util.Scanner;

public class MatrixRotation {

    static int []m_range;
    static int [][]m_arr;
    static int m_matrixSize;

    MatrixRotation(int squareMatrixSize){
        m_range = new int[2];
        m_range[0] = 0;
        m_matrixSize = squareMatrixSize;
        m_range[1] = squareMatrixSize - 1;
        m_arr = new int[squareMatrixSize][squareMatrixSize];

    }
    public static void FillSquareMatrix(){
        Scanner sc = new Scanner(System.in);
        for(int row = 0; row < m_matrixSize; ++row){
            for(int col = 0; col < m_matrixSize; ++col){
                m_arr[row][col]=sc.nextInt();
            }
        }
    }
    public static void DisplayMatrix(){
        for(int row = 0; row < m_matrixSize; ++row){
            for(int col = 0; col < m_matrixSize; ++col){
                System.out.print(m_arr[row][col]+ " ");
            }
            System.out.println();
        }

    }

    public static int ComputeNumberOfIterationsOfRotationBasedOnDegereeOfRotation(int degeree){
        return (degeree%4) *(m_range[1] - m_range[0]);
    }

    public static void updateRange(){
        m_range[0] += 1;
        m_range[1] -= 1;
    }

    public static int LogicForShiftingLeftColumnValuesDown(){
        int row = m_range[0];
        int col = m_range[0];
        int temp1 = m_arr[row][col];
        int temp2 = -1;//not yet assigned!!!
        for(;row<m_range[1];++row){
            temp2 = m_arr[row + 1][col];
            m_arr[row + 1][col] = temp1;
            temp1 = temp2;
        }
        return temp1;

    }

    public static int LogicForShiftingBottomRowValuesRight(int valueReutrnedFromLogicForShiftingLeftColumnValuesDown){
        int temp1 = valueReutrnedFromLogicForShiftingLeftColumnValuesDown;
        int row = m_range[1];
        int temp2 = -1; //Not Yet Assigned!!!
        for(int col = m_range[0]; col < m_range[1]; ++col){
            temp2 = m_arr[row][col+1];
            m_arr[row][col+1] = temp1;
            temp1 = temp2;
        }
        return temp1;
    }

    public static int LogicForShiftingRightColumnValuesUp(int valueReturnedFromLogicForShiftingBottomRowValuesRight){
        int temp1 = valueReturnedFromLogicForShiftingBottomRowValuesRight;
        int temp2 = -1;//Not Yet assigned!!!
        int col = m_range[1];
        for(int row = m_range[1] - 1 ; row >=m_range[0] ; --row){
            temp2 = m_arr[row][col];
            m_arr[row][col] = temp1;
            temp1 = temp2;
        }
        return temp1;
    }

    public static void LogicForShiftingUpRowValuesLeft(int valueReturnedFromLogicForShiftingRightColumnValuesUp){
        int temp1 = valueReturnedFromLogicForShiftingRightColumnValuesUp;
        int row = m_range[0];
        int temp2 = -1; //Not Yet Assigned!!!
        for(int col = m_range[1] -1; col >=m_range[0] ; --col){
            temp2 = m_arr[row][col];
            m_arr[row][col] = temp1;
            temp1 = temp2;
        }

    }

    public static void PerformMatrixRotation(){
        Scanner sc = new Scanner(System.in);
        int matrixSize = -1;
        int degereeOfRotation = -1; //Not Yet Assigned!!!!
        System.out.println("Enter the Square Matrix size: ");
        matrixSize = sc.nextInt();
        System.out.println("Enter the degeree of Rotation (Integeral Multiples of 90): ");
        degereeOfRotation = sc.nextInt();
        MatrixRotation matrixRotation = new MatrixRotation(matrixSize);
        System.out.println("Enter the Matrix Values");
        MatrixRotation.FillSquareMatrix();
        MatrixRotation.DisplayMatrix();
        int noOfIterations = -1;//Not Yet Assigned!!!
        while(m_range[0]<m_range[1]){
            noOfIterations = MatrixRotation.ComputeNumberOfIterationsOfRotationBasedOnDegereeOfRotation(degereeOfRotation);
            for(int i=0; i < noOfIterations ; i++){
                MatrixRotation.LogicForShiftingUpRowValuesLeft(
                        MatrixRotation.LogicForShiftingRightColumnValuesUp(
                                MatrixRotation.LogicForShiftingBottomRowValuesRight(
                                        MatrixRotation.LogicForShiftingLeftColumnValuesDown())));
            }
            System.out.println("Outer :");
            MatrixRotation.DisplayMatrix();
            MatrixRotation.updateRange();
        }
        System.out.println("After " + degereeOfRotation*90 + " degeree rotation");
        MatrixRotation.DisplayMatrix();
    }
    public static void main(String[] args) {
        MatrixRotation.PerformMatrixRotation();
    }
}
