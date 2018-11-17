using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TPR_lab3
{
    public class N_dimesion_model
    {
        public List<int> VectorOfMarksCount { get; set; }

        public N_dimesion_model(MyMatrix<int> InformationMatrix)
        {
            VectorOfMarksCount = CalcNdimensionModel(InformationMatrix);
        }

        private List<int> CalcNdimensionModel(MyMatrix<int> InformationMatrix)
        {
            List<int> tempVector = new List<int>(InformationMatrix.Matrix.Count);
            for (int k = 0; k < tempVector.Capacity; ++k)
            {
                tempVector.Add(0);
            }

            //Search zero row
            int? zeroRowNumber = null;
            
            for(int i = 0; i < InformationMatrix.Matrix.Count; ++i)
            {
                bool isZeroRow = false;
                for(int j = 0; j < InformationMatrix.Matrix.Count; ++j)
                {
                    if (InformationMatrix.Matrix[i][j] != 0)
                    {
                        isZeroRow = false;
                        break;
                    }

                    isZeroRow = true;
                }
                if (isZeroRow)
                {
                    calculateCoefficient(InformationMatrix, i, tempVector);
                    break;
                }

                
            }



            return tempVector;
        }

        private void calculateCoefficient(MyMatrix<int> informationMatrix, int colNumber, List<int> tempVector,
                int result = 1)
        {
            tempVector[colNumber] = result;

            for (int i = 0; i < informationMatrix.Matrix.Count; ++i)
            {
                if (informationMatrix.Matrix[i][colNumber] != 0)
                {
                    int tempResult = result * informationMatrix.Matrix[i][colNumber];
                    calculateCoefficient(informationMatrix, i, tempVector, tempResult);
                }
            }
        }

        public MyMatrix<int> GetExtendedModel(MyMatrix<int> marksMatrix)
        {

            int extendedModelColSize = VectorOfMarksCount.Sum();

            MyMatrix<int> newMatrix = new MyMatrix<int>();

            for (int i = 0; i < marksMatrix.Matrix.Count; ++i)
            {
                newMatrix.Matrix.Add(new List<int>());

                for (int j = 0; j < VectorOfMarksCount.Count; ++j)
                {
                    for (int k = 0; k < VectorOfMarksCount[j]; ++k)
                    {
                        newMatrix.Matrix[i].Add(marksMatrix.Matrix[i][j]);
                    }
                }
            }
            
            return newMatrix;
        }

        public static void SortMatrix(MyMatrix<int> extendMatrix)
        {
            for (int i = 0; i < extendMatrix.Matrix.Count; ++i)
            {
                extendMatrix.Matrix[i].Sort((x1, x2) => { return x2 - x1; });
            }
        }

        public static List<int> FindRoots(MyMatrix<int> extendMatrix)
        {
            var resultVector = new List<int>();
            for (int i = 0; i < extendMatrix.Matrix.Count; ++i)
            {
                resultVector.Add(1);
            }

            for (int i = 0; i < extendMatrix.Matrix.Count - 1; ++i)
            {
                if (resultVector[i] != 0)
                {
                    for (int j = i + 1; j < extendMatrix.Matrix.Count; ++j)
                    {
                        if (resultVector[j] != 0)
                        {
                            cmpMarksVectors(extendMatrix, i, j, resultVector);
                        }
                    }

                }
                    
            }


            return resultVector;
        }


        private static void cmpMarksVectors(MyMatrix<int> extendMatrix, int vec1Index, int vec2Index, List<int> rootsVector)
        {
            int size = extendMatrix.Matrix[vec1Index].Count;

            bool vec1IsRoot = true;
            bool vec2IsRoot = true;

            for (int i = 0; i < size; ++i)
            {
                if (vec1IsRoot)
                {
                    if (extendMatrix.Matrix[vec1Index][i] < extendMatrix.Matrix[vec2Index][i])
                    {
                        vec1IsRoot = false;
                    }
                }
                
                if (vec2IsRoot)
                {
                    if (extendMatrix.Matrix[vec2Index][i] < extendMatrix.Matrix[vec1Index][i])
                    {
                        vec2IsRoot = false;
                    }
                }

                if (!vec1IsRoot && !vec2IsRoot)
                {
                    break;
                }
            }

            if (vec1IsRoot && !vec2IsRoot)
            {
                rootsVector[vec2Index] = 0;
            } else if (!vec1IsRoot && vec2IsRoot)
            {
                rootsVector[vec1Index] = 0;
            }

        }

        public static void PrintVector(List<int> rootVector, string fillStr = " ")
        {
            for (int i = 0; i < rootVector.Count; ++i)
            {
               Console.Write($"{fillStr}{rootVector[i]} ");
            }
        }

        public static void PrintRoots(List<int> rootVector, string fillStr = " ")
        {
            for (int i = 0; i < rootVector.Count; ++i)
            {
                if (rootVector[i] != 0)
                {
                    Console.Write($" {fillStr}{i + 1} ");
                }
            }
        }
    }
}
