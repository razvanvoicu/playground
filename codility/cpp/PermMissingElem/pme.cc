int solution(vector<int> &A) {
	int N = A.size();
	long long sum = 0L;
	for ( int i = 0 ; i < N ; i++ ) sum += A[i];
	return ((long long)N+1)*(N+2)/2 -sum;
}