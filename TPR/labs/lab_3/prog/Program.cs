using System;
using System.Collections.Generic;

namespace TPR_lab3
{
    class Program
    {
        static void Main(string[] args)
        {
            MatrixLoader LoadedOrderInformationMatrix = new MatrixLoader("A1.txt");
            MatrixLoader LoadedMarksMatrix = new MatrixLoader("A2.txt");

            MyMatrix<int> InformationMatrix = new MyMatrix<int>(LoadedOrderInformationMatrix.Load());
            MyMatrix<int> MarksMatrix = new MyMatrix<int>(LoadedMarksMatrix.Load());

            Console.WriteLine("Матрица количественной информации о важности критериев:");
            MyMatrix<int>.PrintMatrix(InformationMatrix.Matrix, "k", "k");

            Console.WriteLine(Environment.NewLine);
            Console.WriteLine("Матрица скалярных оценок:");
            MyMatrix<int>.PrintMatrix(MarksMatrix.Matrix, "x", "k");
            
            Console.WriteLine("");
            N_dimesion_model nDimModel = new N_dimesion_model(InformationMatrix);

            Console.WriteLine("N-dimesion model:");
            Console.Write("N = {");
            N_dimesion_model.PrintVector(nDimModel.VectorOfMarksCount);
            Console.WriteLine("}");

            Console.WriteLine("");
            MyMatrix<int> extendedModel = nDimModel.GetExtendedModel(MarksMatrix);
            MyMatrix<int>.PrintMatrix(extendedModel.Matrix,"Kx");

            Console.WriteLine("");
            N_dimesion_model.SortMatrix(extendedModel);
            MyMatrix<int>.PrintMatrix(extendedModel.Matrix, "Kx");

            List<int> rootsVector = N_dimesion_model.FindRoots(extendedModel);

            Console.WriteLine("");
            Console.WriteLine("Prime vector");
            N_dimesion_model.PrintRoots(rootsVector, "x");

            Console.WriteLine("");
        }
    }
}
