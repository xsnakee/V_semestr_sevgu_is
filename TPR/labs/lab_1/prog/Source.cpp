#include <iostream>
#include <fstream>
#include <vector>
using namespace std;

void loadMatrixFromFile(string fileName, vector<vector<bool>> &matrix);
void printBoolMatrix(vector<vector<bool>> boolMatrix);
void printResultMatrix(const vector<vector<int>> &matr);
void searchRootsOrder(const vector<vector<bool>> &mainMatrix, vector<vector<int>> &resultMatrix);
//*
int main(int argc, char *argv[]) {
	
		vector<vector<bool>> matr;

		loadMatrixFromFile("1.txt", matr);
		cout << "Source matrix: " << endl;
		printBoolMatrix(matr);
		cout << endl;

		vector<vector<int>> recSolutions;

		searchRootsOrder(matr, recSolutions);

		printResultMatrix(recSolutions);
	
   	return 0;
}

void searchRootsOrder(const vector<vector<bool>> &mainMatrix, vector<vector<int>> &resultMatrix) {
	int rootAmount = mainMatrix.back().size();

	vector<bool> MaxR(rootAmount, 0);

	int solutionsAmount = rootAmount;

	while (solutionsAmount > 0) {
		resultMatrix.push_back(vector<int>());//create new stage
		for (int j = 0; j < rootAmount; ++j) {
			if (!MaxR[j]) {
				bool colIsIndependence = true;
				for (int i = 0; i < rootAmount; ++i) {
					if ((mainMatrix[i][j] != 0) && (!MaxR[i])) {
						colIsIndependence = false;
						break;
					}
				}
				if (colIsIndependence) {
					resultMatrix.back().push_back(j);//added recommended root
				}
			}
		}
		int solutionsAmount = resultMatrix.back().size();

		if (!solutionsAmount) {
			resultMatrix.pop_back();
			return;
		}

		for (int a = 0; a < solutionsAmount; ++a) {
			MaxR[resultMatrix.back()[a]] = true;
		}

		solutionsAmount -= solutionsAmount;
	};
}

void printResultMatrix(const vector<vector<int>> &matr) {
	if (matr.back().size() == 0) {
		cout << "Source roots are not exists";
		return;
	}
	for (int i = 0; i < matr.size(); ++i) {
		cout << "Stage #" << i << endl;

		for (int j = 0; j < matr[i].size(); ++j) {
			cout << "x" << matr[i][j] + 1 << " ";
		}
		/*
		if (matr[i].size() > 1) {
			cout << " - It's roots not copmarable";
		}
		*/
		cout << endl;
	}
}

void loadMatrixFromFile(string fileName, vector<vector<bool>> &matrix) {
	ifstream fileStream	= ifstream(fileName, ios::in);

	if (fileStream) {
		int rootAmount = 0;
		fileStream >> rootAmount;

		bool tempValue = false;
		for (int j = 0; j < rootAmount; ++j) {
			matrix.push_back(vector<bool>());
			for (int i = 0; i < rootAmount; ++i) {
				fileStream >> tempValue;
				matrix.back().push_back(tempValue);
			}
		}
		fileStream.close();
	}
	else {
		exit(1);
	}

}

void printBoolMatrix(vector<vector<bool>> boolMatrix) {
	for (int i = 0; i < boolMatrix.size(); ++i) {
		for (int j = 0; j < boolMatrix[i].size(); ++j) {
			cout << boolMatrix[i][j] + 1 << " ";
		}
		cout << endl;
	}
}
//*/