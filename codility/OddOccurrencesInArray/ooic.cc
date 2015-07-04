int solution(vector<int> &A) {
	int N = A.size();
	int x = 0 ;
	for ( int i = 0 ; i < N ; i++ ) x ^= A[i] ;
	return x ;
}