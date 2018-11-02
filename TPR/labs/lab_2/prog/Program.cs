using System;

namespace TPR_lab_2
{
    class Program
    {
        static void Main(string[] args)
        {

            MatrixLoader LoadedMatrixA1 = new MatrixLoader("A1.txt");
            MatrixLoader LoadedMatrixA2 = new MatrixLoader("A2.txt");

            MyMatrix A1 = new MyMatrix();
            MyMatrix A2 = new MyMatrix();

            A1.Matrix = MyMatrix.ConvertToBool(LoadedMatrixA1.Load());
            A2.Matrix = MyMatrix.ConvertToBool(LoadedMatrixA2.Load());

            Console.WriteLine("A1 - Матрица отношения строго предпочтения:");
            MyMatrix.PrintBoolMatrix(A1.Matrix, 'x');
            Console.WriteLine();

            Console.WriteLine("A2 - Матрица отношения эквивалентности:");
            MyMatrix.PrintBoolMatrix(A2.Matrix, 'x');
            Console.WriteLine();

            UsefulValues main_data = new UsefulValues(A1.Matrix, A2.Matrix);

            Console.WriteLine("Классы эквивалентности решений R(xi):");
            main_data.PrintAllEqualsClasses();
            Console.WriteLine();
            
            Console.WriteLine("Множество X/~ - уникальных классов эквивалентности kl решений :");
            main_data.PrintMatrixClassGroups('x');
            Console.WriteLine();

            Console.WriteLine("Значение функции полезности уникальных классов эквивалентности kl из мн. X/~:");
            main_data.PrintClassGroupsUsefulCoeficients();
            Console.WriteLine();
            Console.WriteLine();

            Console.WriteLine("Упорядочивание уникальных классов эквивалентности kl:");
            main_data.PrintComparableClassGroups();
            Console.WriteLine();
            Console.WriteLine();

            Console.WriteLine("Значения функции полезности решений U(xi):");
            main_data.SortAndPrintElementsUsefulCoefficients();
            Console.WriteLine();

            Console.WriteLine("Эффективные решения:");
            main_data.PrintMostUsefulElements();
        }
    }
}
