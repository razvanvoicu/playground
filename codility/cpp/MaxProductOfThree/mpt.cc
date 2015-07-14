#include <algorithm>

int solution(vector<int>& A) {
	sort(A.begin(),A.end());
	int N = A.size();
	int x = A[0] * A[1] *A[N-1] ;
	int y = A[N-3] * A[N-2] * A[N-1] ;
	return max(x,y);
}