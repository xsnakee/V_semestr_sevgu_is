using System;
using System.Collections.Generic;

namespace TPR_lab_2
{
    class UsefulValues
    {
        public UsefulValues(List<List<bool>> A1, List<List<bool>> A2)
        {
            MatrixA1 = A1;
            MatrixA2 = A2;
            EqualClassesMatrix = null;
            ClassGroupUsefulCoeficientVector = null;
            SortedClassGroupsIndexes = null;
            ElementsUsefulCoefficients = null;
            MostUsefulElementsIndexes = null;
            Calculate();
        }

        public List<List<bool>> MatrixA1 { get; private set; }
        public List<List<bool>> MatrixA2 { get; private set; }
        public List<List<int>> EqualClassesMatrix { get; private set; }
        public List<double> ClassGroupUsefulCoeficientVector { get; private set; }
        private List<int> SortedClassGroupsIndexes { get; set; }
        private List<double> ElementsUsefulCoefficients { get; set; }
        private List<int> MostUsefulElementsIndexes { get; set; }

        private void Calculate()
        {
            EqualClassesMatrix = SearchEqualsClasses();
            ClassGroupUsefulCoeficientVector = CalculateClassGroupUsefulCoefficient();
            SortedClassGroupsIndexes = CompareClassesVector();
            ElementsUsefulCoefficients = CalculateElementsCoefficients();
            MostUsefulElementsIndexes = SearchMostUsefulElements();
        }


        private List<List<int>> SearchEqualsClasses()
        {
            List<List<int>> EqualsClassMatrix = new List<List<int>>();

            bool[] tempVector = new bool[this.MatrixA2.Count];
            List<bool> answerVector = new List<bool>(tempVector);

            MyMatrix.FillSameValue(answerVector, false);

            int currentRowPosition = 0;
            for (int i = 0; i < this.MatrixA2.Count; ++i)
            {

                if (answerVector[i] == false)
                {
                    EqualsClassMatrix.Add(new List<int>());
                    for (int j = 0; j < this.MatrixA2.Count; ++j)
                    {
                        if (this.MatrixA2[i][j] == true)
                        {
                            EqualsClassMatrix[currentRowPosition].Add(j);
                            answerVector[i] = answerVector[j] = true;
                        }
                    }
                    ++currentRowPosition;
                }
            }
            return EqualsClassMatrix;
        }
       
        private double GetDoubleVal(double a, double b)
        {
            return (a + b) / 2.0 ;
        }

        private List<double> CalculateClassGroupUsefulCoefficient()
        {
            List<double> result = new List<double> { 0.0 };

            for (int i = 1; i < EqualClassesMatrix.Count; ++i)
            {
                double coeficient = 0.0;
                int IndexOfBiggestClass = -1;
                int IndexOfSmallestClass = -1;
                for (int j = 0; j < i; ++j)
                {
                    if (MatrixA1[EqualClassesMatrix[i][0]][EqualClassesMatrix[j][0]] == true)
                    {
                        if (IndexOfSmallestClass == -1)
                        {
                            IndexOfSmallestClass = j;
                        } else
                        {
                            IndexOfSmallestClass = result[IndexOfSmallestClass] < result[j] ? IndexOfSmallestClass : j;
                        }
                    }
                    else if (MatrixA1[EqualClassesMatrix[j][0]][EqualClassesMatrix[i][0]] == true)
                    {
                        if (IndexOfBiggestClass == -1)
                        {
                            IndexOfBiggestClass = j;
                        }
                        else
                        {
                            IndexOfBiggestClass = result[IndexOfBiggestClass] > result[j] ? IndexOfSmallestClass : j;
                        }
                    }    
                }
                if (IndexOfBiggestClass != -1 && IndexOfSmallestClass != -1)
                {
                    coeficient = GetDoubleVal(result[IndexOfSmallestClass], result[IndexOfBiggestClass]);
                }
                else if (IndexOfSmallestClass == -1 && IndexOfBiggestClass != -1)
                {
                    coeficient = -(double)(i + 1);
                }
                else
                {
                    coeficient = (double)(i + 1);
                }
                result.Add(coeficient);
            }
            return result;
        }

        private List<int> CompareClassesVector()
        {
            List<int> result = new List<int>();
            bool[] flagArray = new bool[ClassGroupUsefulCoeficientVector.Count];
            List<bool> flagVector = new List<bool>(flagArray);

            MyMatrix.FillSameValue(flagVector, false);

            for (int i = 0; i < (ClassGroupUsefulCoeficientVector.Count); ++i)
            {

                    int max = 0;
                    for(; max < flagVector.Count; ++max)
                    {
                        if (flagVector[max] == false)
                        {
                            break;
                        }
                    }

                    for (int j = 0; j < ClassGroupUsefulCoeficientVector.Count; ++j)
                    {
                        if (flagVector[j] == false)
                        {
                            if (ClassGroupUsefulCoeficientVector[max] < ClassGroupUsefulCoeficientVector[j])
                            {
                                max = j;
                            }
                        }
                    }
                    flagVector[max] = true;
                    result.Add(max);                
            }

            return result;
        }

        private List<double> CalculateElementsCoefficients()
        {
            List<double> result = new List<double>();

            for (int i = 0; i < MatrixA2.Count; ++i)
            {
                for (int j = 0; j < EqualClassesMatrix.Count; ++j)
                {
                    if (EqualClassesMatrix[j].Contains(i))
                    {
                        result.Add(ClassGroupUsefulCoeficientVector[j]);
                    }
                }    
            }
            return result;
        }

        private List<int> SearchMostUsefulElements()
        {
            List<int> result = new List<int>();

            for (int j = 0; j < ElementsUsefulCoefficients.Count; ++j)
            {
                    if (ElementsUsefulCoefficients[j] == ClassGroupUsefulCoeficientVector[SortedClassGroupsIndexes[0]])
                    {
                    result.Add(j);
                    }
            }
            return result;
        }

        public void PrintMatrixClassGroups(char colRowChar = ' ')
        {
            int counter = 0;
            foreach (var i in EqualClassesMatrix)
            {
                Console.Write($"k{counter++ + 1} -> {{");
                foreach (int j in i)
                {
                    Console.Write($"{colRowChar}{j + 1} ");
                }
                Console.WriteLine("}");
            }
        }
        public void PrintClassGroupsUsefulCoeficients()
        {
            for (int i = 0; i < ClassGroupUsefulCoeficientVector.Count; ++i)
            {
                Console.Write($"U(k{i + 1}) = {ClassGroupUsefulCoeficientVector[i]};  ");
            }
        }
        public void PrintAllEqualsClasses()
        {

            for (int i = 0; i < MatrixA2.Count; ++i)
            {
                Console.Write($"R(x{i + 1}) = {{");
                for (int j = 0; j < MatrixA2[i].Count; ++j)
                {
                    if (MatrixA2[i][j] != false)
                    {
                        Console.Write($"x{j + 1} ");
                    }
                }
                Console.WriteLine("}");
            }
        }
        public void PrintComparableClassGroups()
        {
            for(int i = 0; i < SortedClassGroupsIndexes.Count; ++i)
            {
                if (i != 0)
                {
                    Console.Write(" >' ");
                }
                Console.Write($"k{SortedClassGroupsIndexes[i] + 1}");
            }
        }
        public void SortAndPrintElementsUsefulCoefficients()
        {
            for (int i = 0; i < ClassGroupUsefulCoeficientVector.Count; ++i)
            {
                double groupCoeff = ClassGroupUsefulCoeficientVector[SortedClassGroupsIndexes[i]];
                for (int j = 0; j < ElementsUsefulCoefficients.Count; ++j)
                {                    
                    if (ElementsUsefulCoefficients[j] == groupCoeff)
                    {
                        Console.WriteLine($"U(x{j + 1}) = {groupCoeff}");
                    }
                }
            }
        }
        public void PrintMostUsefulElements()
        {
            for(int i = 0; i < MostUsefulElementsIndexes.Count; ++i)
            {
                Console.Write($"x{MostUsefulElementsIndexes[i] + 1} ");
            }
        }
    }
}
