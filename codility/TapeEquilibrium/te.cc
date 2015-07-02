#include <algorithm>
#include <random>
#include <iostream>
#include <climits>

using namespace std;

int solution(vector<int> &A) {
	int N = A.size();

	long long sum = 0;
	for ( int i = 1 ; i < N ; i ++ ) sum += A[i];

	long long left = A[0], right = sum, m = INT_MAX ;
	for ( int P = 1 ; P < N ; P ++ ) {
		int d = abs(left - right);
		if ( m > d ) m = d ;
		left += A[P];
		right -= A[P] ;
	}

	return m ;

}

