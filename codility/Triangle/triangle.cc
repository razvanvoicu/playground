#include <algorithm>

int solution(vector<int>& A) {
	int N = A.size();
	sort(A.begin(),A.end());
	for ( int i = 0 ; i < N-2 ; i ++ ) {
		if ( (long long)A[i+2] < (long long)A[i] + A[i+1] ) return 1; 
	}
	return 0;
}