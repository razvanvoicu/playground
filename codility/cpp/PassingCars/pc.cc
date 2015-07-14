int solution(vector<int> &A) {
    int N = A.size();
    int right = 0;
    long long pairs = 0;
    for ( int i = 0 ; i < N ; i ++ ) {
    	if (A[i]) pairs += right;
    	else right ++ ;
    	if (pairs > 1000000000L) return -1;
    }
    return pairs;
}