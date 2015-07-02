int solution(vector<int> &A) {
    int N = A.size();
    vector<bool> occurred(N+1);
    for ( int i = 0 ; i < N ; i++ ) {
    	if (A[i] > 0 && A[i] <= N)
    		occurred[A[i]] = true ;
    }
    for ( int i = 1 ; i <= N ; i ++ )
    	if ( !occurred[i] ) return i;
    return N+1;
}
