using System;
using System.Collections.Generic;

namespace TPR_lab_2
{
   public class MyMatrix
    {
        public MyMatrix()
        {
           this.Matrix = null;
        }

        public MyMatrix(int[,] initMatrix)
        {
            Matrix = ConvertMatrix(initMatrix);
        }

        public List<List<bool>> Matrix { get; set; }

        public static List<List<bool>> ConvertMatrix(int[,] matrix)
        {
            List<List<bool>> tempMatrix = new List<List<bool>>();
            int rowCount = matrix.GetLength(0);
            for (int i = 0; i < rowCount; ++i)
            {
                tempMatrix.Add(new List<bool>());
                for (int j = 0; j < (matrix.Length / rowCount); ++j)
                {
                    tempMatrix[i].Add(Convert.ToBoolean(matrix[i,j]));
                }
            }

            return tempMatrix;
        }

        public static void PrintBoolMatrix(List<List<bool>> Matrix, char colRowChar = ' ')
        {
            Console.Write("    ");                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
            for(int i = 0; i < Matrix[0].Count; ++i)
            {
                Console.Write($"{colRowChar}{i + 1} ");
            }

            Console.WriteLine();

            for (int i = 0; i < Matrix.Count; ++i)
            {
                Console.Write($"{colRowChar}{i + 1} ");
                for (int j = 0; j < Matrix[i].Count; ++j)
                {
                    Console.Write($"{Convert.ToInt16(Matrix[i][j]), 2} ");
                }
                Console.WriteLine();
            }
        }

        public static List<List<bool>> ConvertToBool(List<List<int>> IntMatrix)
        {
            List<List<bool>> resultMatrix = new List<List<bool>>();


            for(int i = 0; i < IntMatrix.Count; ++i)
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
