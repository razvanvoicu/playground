int solution(vector<int> &A) {
    int N = A.size();
    long long sum = 0 ;
    long long permSum = ((long long)N)*(N+1)/2;
    vector<bool> occurred(N);
    for ( int i = 0 ; i < N ; i ++ ) {
    	if (A[i] <= 0 || A[i] > N) return 0;
    	if (!occurred[A[i]]) {
    		occurred[A[i]] = true;
    		sum += A[i] ;
    	}
    }
    return sum == permSum ;
}