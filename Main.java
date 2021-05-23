package com.company;

import java.util.Locale;
import java.util.Scanner;

class vector
{
    double x;

    double y;

    public vector(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
}

public class Main
{

    static void MinusCoefficient (double A[][], int first, int second, double coeff)
    {
        for (int i = 0; i <A[0].length; i++)
        {
            A[first][i] -= A[second][i]*coeff;
        }
    }

    static void replace (double[][] A, int first, int second)
    {
        double c = 0;

        for (int i = 0; i <A[0].length; i++)
        {
            c = A[second][i];

            A[second][i] = A[first][i];

            A[first][i] = c;
        }
    }

    static void divide (double[][] A, int index, double coeff)
    {
        for (int i = 0; i <A[0].length; i++)
        {
            A[index][i] /= coeff;
        }
    }

    static vector sum (vector a, vector b) {return new vector(a.x+b.x, a.y+b.y);}

    static vector multiply (vector a, double b)
    {
        return new vector(a.x*b, a.y*b);
    }

    public static void main(String[] args)
    {
        Locale.setDefault(Locale.US);

        Scanner in = new Scanner(System.in);

        vector v1 = new vector(in.nextDouble(), in.nextDouble());

        vector d1 = new vector(in.nextDouble(), in.nextDouble());

        vector v2 = new vector(in.nextDouble(), in.nextDouble());

        vector d2 = new vector(in.nextDouble(), in.nextDouble());

        int n = 2;

        double[][] A = new double[n][n+1];

        A[0][0] = d1.x;
        A[0][1] = -d2.x;
        A[0][2] = v2.x-v1.x;

        A[1][0] = d1.y;
        A[1][1] = -d2.y;
        A[1][2] = v2.y-v1.y;

        int d = 0;

        for (int i = 0; i <n; i++)
        {
            if (A[i][i] == 0)
            {
                d = 0;

                for (int j = i+1; j <n; j++)
                {
                    if (A[j][i] != 0)
                    {
                        replace(A, i, j);

                        d = 1;

                        break;
                    }
                }

                if (d == 0)
                {
                    System.out.println("NOTSINGLE");

                    System.exit(0);
                }
            }

            for (int j = 0; j <i; j++)
            {
                MinusCoefficient(A, j, i, A[j][i]/A[i][i]);

                divide(A, i, A[i][i]);
            }
            for (int j = i+1; j <n; j++)
            {
                MinusCoefficient(A, j, i, A[j][i]/A[i][i]);

                divide(A, i, A[i][i]);
            }
        }

        System.out.println(sum(v1, multiply(d1, A[0][n])).x + " " + sum(v1, multiply(d1, A[0][n])).y);
    }
}
