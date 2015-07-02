
int solution(int X, vector<int> &A) {
	int N = A.size();
	vector<bool> occurred(X);
	long long sum = 0;
	long long limit = ((long long)X)*(X+1)/2;
	for ( int i = 0 ; i < N ; i++) {
		if ( !occurred[A[i]] ) {
			occurred[A[i]] = true;
			sum += A[i];
		}
		if (sum == limit) return i; 
	}
	return -1;
}