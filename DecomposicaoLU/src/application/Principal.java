package application;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n;
		
		System.out.println("!!Para resolver usando o método de decomposição LU a matriz deve ser quadrada!!");
		System.out.println("Qual a ordem da matriz quadrada: (sem contar os termos indepencentes)");
		n = sc.nextInt() ;
		
		
		double [][] A = new double[n][n];
		double [][] U = new double[n][n];
		double [][] L = new double[n][n];
		
		//formando a matriz A
		for(int i = 0; i < n ; i++) {
			for(int j = 0; j < n ; j++) {
				System.out.println("Qual elemento na linha [" + i + "] e coluna ["  + j + "] ");
				A[i][j]  = sc.nextDouble();
			}
		}
		
		
		//impimindo a matriz A
		System.out.println("Matriz A:");
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0; j < n; j++) {
				System.out.print("[ " + A[i][j] + " ]" );
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println("Quais são os " + n + " termos independentes: ");
		int[] b = new int[n];
		
		for(int i = 0 ; i < n ; i++) {
			System.out.println((i + 1) + "° termo independente: ");
			b[i] = sc.nextInt();
		}
		
		System.out.println("Vetor dos termos independentes: ");
		for(int i = 0; i < n ; i++) {
			System.out.println(" b[ " + i + "] = [" + b[i] + "]");
		}
		
		//matriz U é uma cópia da matriz A por enquanto
		for(int i = 0 ; i < A.length ; i++) {
			for(int j = 0; j < A.length ; j++) {
				U[i][j] = A[i][j];
			}
		}
		
		//matiz L é uma matriz identidade
		for(int i = 0; i < L.length ; i++) {
			for(int j = 0; j < L.length ; j++) {
				if(i == j) {
					L[i][j] = 1;
				}
				else {
					L[i][j] = 0;
				}
			}
		}
		
		//Transformar U em matriz triangular superior e receber os multiplicadores na matriz L
		for(int j = 0; j < n - 1; j++) {
			if(U[j][j] == 0) {
				System.out.println("Não é possivel resolver a decomposição!!");
			}
			for(int i = j + 1; i < n; i++) {
				L[i][j] = U[i][j] / U[j][j];
				U[i][j] = 0;
				for(int k = j + 1 ; k < n; k++) {
					U[i][k] = U[i][k] - (L[i][j] * U[j][k]);
					
				}
			}
			
			
		}
		
		//imprimindo matriz L
		System.out.println("Matriz L:");
		for(int i = 0; i < L.length; i++) {
			for(int j = 0; j < L.length ; j++) {
				 System.out.print("[" + L[i][j] + " ]");
			 }
			 System.out.println();
		 }
		
		
		 //imprimindo matriz U
		 System.out.println("Matriz U:");
		 for(int i = 0; i < U.length; i++) {
			 for(int j = 0; j < U.length ; j++) {
				 System.out.print("[" + U[i][j] + " ]");
			 }
			 System.out.println();
		 }
		 
		 
		 //resolver Ly = b
		 double[] y = new double[n];
		 for(int i = 0; i < n; i++) {
			 y[i] = b[i];
			 for(int j = 0; j < i ; j++) {
				 y[i] -= L[i][j] * y[j];
				 
			 }
			 y[i] = y[i] / L[i][i];
			 System.out.println("y" + i + " = " + y[i]);
		 }
		 
		 //resolver Ux = y
		 System.out.println("O sistema solução é:");
		 double[] x = new double[n];
		 for(int i = n - 1; i >= 0 ; i--) {
			 x[i] = y[i];
			 for(int j = i + 1; j < n; j++) {
				 x[i] -= U[i][j] * x[j];
			 }
			 x[i] = x[i] / U[i][i];
			 System.out.println("x" + i + " = " + x[i]);
		 }
		
		sc.close();
	}
}
