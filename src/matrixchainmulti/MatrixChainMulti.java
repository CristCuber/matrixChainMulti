/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package matrixchainmulti;

import java.util.Scanner;

/**
 *
 * @author tanaichaikraveephan
 */
public class MatrixChainMulti {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.print("Enter a number of matrices: ");
        
        Scanner scan  = new Scanner(System.in);
        int n = scan.nextInt();
        
        int[] p = new int[n + 1];
        
        System.out.print("Size of A1: ");
        int a = scan.nextInt();
        int b = scan.nextInt();
        
        p[0] = a;
        p[1] = b;
        
        for(int i = 1; i < n; i++) {
            System.out.print("Size of A" + (i + 1) + ": ");
            a = scan.nextInt();
            b = scan.nextInt();
            
            if(a != p[i]) {
                i--;
                System.out.println("Matrix can't multiplication");
                continue;
            }
            else p[i + 1] = b;   
        }
        
        int[][] m = new int[n][n];
        int[][] s = new int[n][n];
        //int ii = 0, jj = n - 1;
        
        //for(int i = 0; i < p.length; i++) System.out.print(p[i] + " ");
        
        matrixChainOrder(p, m, s);
        
        /*for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) System.out.print(m[i][j] + " ");
        }*/
        
        System.out.print("The number of multiplications: " + m[0][n - 1] + " with "); 
        printSol(s);
        System.out.println();
        
        /*for(int kz = 0; kz < s.length; kz++) {
            for(int kx = 0; kx < s.length; kx++) {
                System.out.print(s[kz][kx] + " ");
            }
            System.out.println();
        }*/
    }
    
    static void matrixChainOrder(int[] p, int[][] m, int[][] s) {
        int n = p.length - 1;
        
        for(int i = 0; i < n; i++) m[i][i] = 0;
        
        for(int l = 1; l < n; l++) {
            for(int i = 0; i < n - l; i++) {
                int j = i + l;
                m[i][j] = Integer.MAX_VALUE;
                
                for(int k = i; k < j; k++) {
                    int q = m[i][k] + m[k + 1][j] + p[i]*p[k + 1]*p[j + 1];
                    
                    if(q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }
    }
    
    static void printSol(int[][] s) {
        printOptimalParens(s, 0, s.length - 1);
    }
   
    static void printOptimalParens(int[][] s, int i, int j) {
        if(i == j) System.out.print("A" + (i + 1));
        else {
            System.out.print("(");
            printOptimalParens(s, i, s[i][j]);
            System.out.print("*");
            printOptimalParens(s, s[i][j] + 1, j);
            System.out.print(")");
        }
    }
}
