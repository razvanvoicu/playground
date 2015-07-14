#include <limits>
#include <algorithm>
#include <iostream>

using namespace std;

int solution(vector<int> &A) {
	int N = A.size();
	double inf = (A[0] + A[1])/2.0;
  	double min2 = inf, min3 = inf;
  	int mi2 = 0, mi3 = 0;
  	for (int i = 0 ; i < N-1 ; i++) {
  		double x = (A[i] + A[i+1])/2.0;
  		if (x < min2) {
  			min2 = x;
  			mi2 = i;
  		}
  	}
  	for (int i = 0 ; i < N-2 ; i++) {
  		double x = (A[i] + A[i+1] + A[i+2])/3.0;
  		if ( x < min3 ) {
  			min3 = x;
  			mi3 = i;
  		}
  	}
  	if (min2 < min3) return mi2;
  	else return mi3;
}

int main() {
	double inf = numeric_limits<double>::infinity();
	cout << (-1 < inf) << " " << (1 > inf) << " " << (1 == inf) << " " << (inf < inf);
	return 0;
}