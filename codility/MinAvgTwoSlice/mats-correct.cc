#include <limits>
#include <algorithm>
#include <iostream>

using namespace std;

int solution(vector<int> &A) {
	int N = A.size();
	if ( N == 2 ) return 0;
	double minAvg = (A[0] + A[1]) / 2.0 ;
	int idx = 0 ;
	for ( int i = 2 ; i < N ; i ++ ) {
		double x = (A[i-1] + A[i]) / 2.0 ;
		double y = (A[i-2] + A[i-1] + A[i]) / 3.0 ;
		if ( x < minAvg ) {
			idx = i - 1 ;
			minAvg = x ;
		}
		if ( y < minAvg ) {
			idx = i - 2 ;
			minAvg = y ;
		}
	}
	return idx;
}

