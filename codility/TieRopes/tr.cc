int solution(int K, vector<int> &A) {
    int N = A.size();
    int count = 0; long long currentLength = 0;
    for ( int i = 0 ; i < N ; i ++ ) {
        currentLength += A[i] ;
        if ( currentLength >= K ) {
            count ++ ;
            currentLength = 0 ;
        }
    }
    return count;
}
