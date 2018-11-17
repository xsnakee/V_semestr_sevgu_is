using System;
using System.Collections.Generic;

namespace TPR_lab3
{
    public class MyMatrix<T>
    {
        public MyMatrix()
        {
            this.Matrix = new List<List<T>>();
        }

        public MyMatrix(T[,] initMatrix)
        {
            Matrix = ConvertToMatrix(initMatrix);
        }

        public MyMatrix(List<List<T>> initMatrix)
        {
            Matrix = initMatrix;
        }

        public MyMatrix(MyMatrix<T> initMatrix)
        {
            this.Matrix = new List<List<T>>();
            for (int i = 0; i < initMatrix.Matrix.Count; ++i)
            {
                this.Matrix.Add(initMatrix.Matrix[i]);
            }
        }

        public List<List<T>> Matrix { get; set; }

        public static List<List<T>> ConvertToMatrix(T[,] matrix)
        {
            List<List<T>> tempMatrix = new List<List<T>>();
            int rowCount = matrix.GetLength(0);
            for (int i = 0; i < rowCount; ++i)
            {
                tempMatrix.Add(new List<T>());
                for (int j = 0; j < (matrix.Length / rowCount); ++j)
                {
                    tempMatrix[i].Add(matrix[i, j]);
                }
            }

            return tempMatrix;
        }

        public static void PrintMatrix(List<List<T>> Matrix, string rowChar = " ", string colChar = " ")
        {
            Console.Write("    ");
            for (int i = 0; i < Matrix[0].Count; ++i)
            {
                if (colChar != " ")
                {
                    Console.Write($"{colChar}{i + 1} ");
                }
            }

            Console.WriteLine();

            for (int i = 0; i < Matrix.Count; ++i)
            {
                Console.Write($"{rowChar}{i + 1} ");
                for (int j = 0; j < Matrix[i].Count; ++j)
                {
                    Console.Write($"{Convert.ToInt16(Matrix[i][j]),2} ");
                }
                Console.WriteLine();
            }
        }

        public static List<List<bool>> ConvertToBool(List<List<int>> IntMatrix)
        {
            List<List<bool>> resultMatrix = new List<List<bool>>();
            
            for (int i = 0; i < IntMatrix.Count; ++i)
            {
                resultMatrix.Add(new List<bool>());
                for (int j = 0; j < IntMatrix[i].Count; ++j)
                {
                    resultMatrix[i].Add((IntMatrix[i][j] > 0) ? true : false);
                }
            }
            return resultMatrix;
        }

        public static void FillSameValue(List<bool> vector, bool value)
        {
            for (int i = 0; i < vector.Count; ++i)
            {
                vector[i] = value;
            }
        }

    }
}
