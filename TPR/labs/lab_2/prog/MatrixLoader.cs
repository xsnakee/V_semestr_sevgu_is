using System;
using System.Collections.Generic;
using System.IO;

namespace TPR_lab_2
{
    class MatrixLoader
    {
        public string FileName { get; private set; }
        protected StreamReader _fread { get; private set; }

        public MatrixLoader(string _filename)
        {
            FileName = _filename;
            try
            {
                _fread = new StreamReader(new FileStream(FileName, FileMode.Open));
            }
            catch (FileNotFoundException err)
            {
                Console.WriteLine(err);
                Environment.Exit(0);
            }
        }

        public List<List<int>> Load()
        {
            try
            {
                List<List<int>> matrix = new List<List<int>>();

                int matrixRowIndex = 0;
                while (!_fread.EndOfStream)
                {
                    string tempString = _fread.ReadLine();
                    matrix.Add(new List<int>());
                    string[] NumericStrings = tempString.Split(" ");
                    foreach (var num in NumericStrings)
                    {
                        int resultNum = 0;
                        if (Int32.TryParse(num, out resultNum))
                        {
                            matrix[matrixRowIndex].Add(resultNum);
                        }
                    }
                    ++matrixRowIndex;
                }
                return matrix;
            }
            catch (IOException err)
            {
                Console.WriteLine(err);
                return null;
            }

        }

        ~MatrixLoader()
        {
            if (_fread != null)
            {
                _fread.Close();
            }
        }
    }
}
