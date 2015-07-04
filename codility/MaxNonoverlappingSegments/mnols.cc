int solution(vector<int> &A, vector<int> &B) {
    int N = A.size();
    int rightLim = -1, count = 0;
    for ( int i = 0 ; i < N ; i++ ) {
        if ( A[i] > rightLim ) {
            rightLim = B[i];
            count ++ ;
        }
    }
    return count ;
}